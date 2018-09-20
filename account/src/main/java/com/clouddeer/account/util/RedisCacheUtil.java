package com.clouddeer.account.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisCacheUtil.class);

    @Autowired
    RedisTemplate redisTemplate;

    /**
     *
     * @Description: 为null对象时，设置一分钟缓存
     * @param <T>
     * @param <K>
     * @param t 设置对象
     * @param redisCacheKeyEnum 枚举类型
     * @param k
     */
    @SuppressWarnings("unchecked")
    public <T, K> void setValueOneMinute(T t, RedisCacheKeyEnum redisCacheKeyEnum, K... k) {
        String key = null;
        try {
            key = generateKey(redisCacheKeyEnum, k);
            redisTemplate.opsForValue().set(key,t,1, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("setValue is error key is " + key, e);
        }
    }

    /**
     *
     * @Description: 在缓存中设置对象
     * @param <T>
     * @param <V>
     * @param t 设置对象
     * @param redisCacheKeyEnum 枚举类型
     * @param v 变长参数
     */
    @SuppressWarnings("unchecked")
    public <T, V> void setValue(T t, RedisCacheKeyEnum redisCacheKeyEnum, V... v) {
        String key = null;
        try {
            key = generateKey(redisCacheKeyEnum, v);
            redisTemplate.opsForValue().set(key,t);
        } catch (Exception e) {
            log.error("setValue is error key is " + key+":"+t, e);
        }
    }

    /**
     *
     * @Description: 在缓存中获取对象
     * @param <T>
     * @param <V>
     * @param redisCacheKeyEnum 枚举类型
     * @param v 变长参数
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T, V> T getValue(RedisCacheKeyEnum redisCacheKeyEnum, V... v) {
        String key = null;
        try {
            key = generateKey(redisCacheKeyEnum, v);
            return (T)redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("getValue is error key is " + key, e);
            return null;
        }
    }


    /**
     *
     * @Description: 移除对象
     * @param redisCacheKeyEnum
     */
    public <K> void remove(RedisCacheKeyEnum redisCacheKeyEnum,K... k) {
        String key = generateKey(redisCacheKeyEnum,k);
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("remove is error key is " + key, e);
        }

    }

    /**
     *
     * @Description: 通过枚举跟参数生成缓存所需要的key
     * @param <K>
     * @param redisCacheKeyEnum 枚举类型
     * @param k 变长参数
     * @return
     */
    @SuppressWarnings("unchecked")
    public <K> String generateKey(RedisCacheKeyEnum redisCacheKeyEnum, K... k) {
        if (k == null || k.length == 0) {
            return redisCacheKeyEnum.getKey();
        }
        Object[] s = new Object[k.length];
        for (int i = 0; i < k.length; i++) {
            if (null != k[i]) {
                s[i] = String.valueOf(k[i]);
            }
        }
        return MessageFormat.format(redisCacheKeyEnum.getKey(), s);
    }

    public boolean lock(String key, int expireSeconds)
    {
        if ((null == key) || ("".equals(key.trim()))) {
            return false;
        }
        return (Boolean) this.redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Boolean nx = connection.setNX(key.getBytes(), (System.currentTimeMillis() + "").getBytes());
                if (!nx.booleanValue()) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                    nx = connection.setNX(key.getBytes(), (System.currentTimeMillis() + "").getBytes());
                }
                if (nx.booleanValue())
                    connection.expire(key.getBytes(), expireSeconds);
                return nx;
            }
        });
    }

    public boolean lock(String key)
    {
        return lock(key, 300);
    }

    public boolean unLock(String key)
    {
        if ((null == key) || ("".equals(key.trim()))){
            return false;
        }
        try
        {
            this.redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
