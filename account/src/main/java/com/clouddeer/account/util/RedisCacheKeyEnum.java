package com.clouddeer.account.util;

public enum RedisCacheKeyEnum{

    weixin_cookie_pre("weixin_cookie_pre_{0}", 60 * 30, "微信cookie"),

    redis_test("redis_test_{0}", 60, "测试");

    /* 缓存key */
    private String key;

    /* 缓存时间 */
    private int second;

    /* 缓存中文说明 */
    private String comment;

    /**
     * 构造函数
     *
     * @param key
     * @param second
     * @param comment
     */
    private RedisCacheKeyEnum(String key, int second, String comment) {
        this.key = key;
        this.second = second;
        this.comment = comment;
    }

    /**
     * getter
     */
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(60);
        sb.append(this.getKey()).append(" \t\t ")
                .append(this.getSecond())
                .append(" \t ").append(this.getComment());
        return sb.toString();
    }
}
