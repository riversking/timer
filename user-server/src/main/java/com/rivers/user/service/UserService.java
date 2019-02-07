package com.rivers.user.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.core.util.ExceptionUtil;
import com.rivers.user.api.dto.UserDto;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.api.entity.SysUserRoleModel;
import com.rivers.user.mapper.SysRoleDao;
import com.rivers.user.mapper.SysRoleMenuDao;
import com.rivers.user.mapper.SysUserDao;
import com.rivers.user.mapper.SysUserRoleDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author riversking
 */
@Service
@Log4j2
public class UserService extends ServiceImpl<SysUserDao, SysUserModel> {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    private static String DEL_FLAG = "del_flag";


    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDto userDto) {
        SysUserModel sysUserModel = new SysUserModel();
        sysUserModel.setUsername(userDto.getUsername());
        sysUserModel.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        sysUserModel.setPhone(userDto.getPhone());
        sysUserModel.setAvatar(userDto.getAvatar());
        sysUserModel.setSalt(UUID.randomUUID().toString());
        sysUserModel.setCreateUser(userDto.getCreateUser());
        sysUserModel.setUpdateUser(userDto.getUpdateUser());
        sysUserDao.insert(sysUserModel);
        userDto.getRoleIds().forEach(i -> {
            SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
            sysUserRoleModel.setRoleId(i);
            log.info("userId {}", sysUserModel.getId());
            sysUserRoleModel.setUserId(sysUserModel.getId());
            sysUserRoleDao.insert(sysUserRoleModel);
        });
    }

    public IPage<SysUserModel> getUserPage(UserDto userDto) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(userDto.getUsername())) {
            wrapper.eq("username",userDto.getUsername());
        }
        if (StrUtil.isNotBlank(userDto.getPhone())) {
            wrapper.eq("phone",userDto.getPhone());
        }
        if (StrUtil.isNotBlank(userDto.getCreateTime())) {
            wrapper.ge("create_time", userDto.getCreateTime());
        }
        if (StrUtil.isNotBlank(userDto.getUpdateTime())) {
            wrapper.le("update_time", userDto.getUpdateTime());
        }
        wrapper.eq(DEL_FLAG, 0);
        Page<SysUserModel> page = new Page<>(userDto.getPage(), userDto.getPageSize());
        IPage<SysUserModel> sysUserPage = sysUserDao.selectPage(page, wrapper);
        sysUserPage.getRecords().forEach(i -> {
            List<Integer> idList = sysUserRoleDao.selectRoleId(i.getId());
            List<SysRoleModel> roleModels = sysRoleDao.selectBatchIds(idList);
            i.setSysRoleModels(roleModels);
        });
        return sysUserPage;
    }

    public SysUserModel getUserDetail(UserDto userDto) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq(DEL_FLAG, 0);
        wrapper.eq("username", userDto.getUsername());
        SysUserModel user = sysUserDao.selectOne(wrapper);
        if (new BCryptPasswordEncoder().matches(userDto.getPassword(), user.getPassword())) {
            log.info("is Right {}", userDto.getPassword());
            return user;
        }
        return new SysUserModel();
    }

    public SysUserModel getUserById(Integer id) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq(DEL_FLAG, 0);
        wrapper.eq("id", id);
        SysUserModel user = sysUserDao.selectOne(wrapper);
        List<Integer> idList = sysUserRoleDao.selectRoleId(id);
        user.setRoleIds(idList);
        return user;
    }

    public List<SysUserModel> getUserByName(UserDto user) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(user.getUsername()) || StrUtil.isNotBlank(user.getPhone())) {
            wrapper.eq("username", user.getUsername()).or().eq("phone", user.getPhone());
        }
        wrapper.eq(DEL_FLAG, 0);
        return sysUserDao.selectList(wrapper);
    }

    public void deleteById(Integer id) {
        try {
            sysUserDao.deleteById(id);
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("101005", "删除失败");
        }
    }
}
