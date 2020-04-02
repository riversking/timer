package com.rivers.user.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.rivers.core.util.ExceptionUtil;
import com.rivers.user.api.dto.UserDto;
import com.rivers.user.api.dto.UserInfo;
import com.rivers.user.api.entity.SysMenuModel;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.api.entity.SysUserRoleModel;
import com.rivers.user.mapper.SysMenuDao;
import com.rivers.user.mapper.SysRoleDao;
import com.rivers.user.mapper.SysUserDao;
import com.rivers.user.mapper.SysUserRoleDao;
import com.rivers.user.util.ExcelUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author riverskingking
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
    private SysMenuDao sysMenuDao;

    private static String IS_DELETE = "is_delete";


    /**
     * 添加用户
     *
     * @param userDto userDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDto userDto) {
        SysUserModel sysUserModel = new SysUserModel();
        sysUserModel.setUsername(userDto.getUsername());
        sysUserModel.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        sysUserModel.setPhone(userDto.getPhone());
        sysUserModel.setAvatar(userDto.getAvatar());
        sysUserModel.setSalt(UUID.randomUUID().toString());
        sysUserModel.setCreateUser(userDto.getCreateUser());
        sysUserModel.setUpdateUser(userDto.getCreateUser());
        sysUserDao.insert(sysUserModel);
        saveUserRole(userDto, sysUserModel);
    }

    /**
     * 分页查询用户
     *
     * @param userDto userDto
     * @return IPage
     */
    public IPage<SysUserModel> getUserPage(UserDto userDto) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(userDto.getUsername())) {
            wrapper.eq("username", userDto.getUsername());
        }
        if (StrUtil.isNotBlank(userDto.getPhone())) {
            wrapper.eq("phone", userDto.getPhone());
        }
        if (StrUtil.isNotBlank(userDto.getStartDate()) && StrUtil.isNotBlank(userDto.getEndDate())) {
            wrapper.ge("create_time", userDto.getStartDate())
                    .le("create_time", userDto.getEndDate());
        }
        wrapper.eq(IS_DELETE, 0);
        wrapper.select(SysUserModel.class, info -> !"password".equals(info.getColumn()));
        Page<SysUserModel> page = new Page<>(userDto.getPage(), userDto.getPageSize());
        IPage<SysUserModel> sysUserPage = sysUserDao.selectPage(page, wrapper);
        List<SysUserModel> collect = sysUserPage.getRecords()
                .stream()
                .peek(i -> {
                    List<SysRoleModel> roleModels = sysRoleDao.selectRoleByUserId(i.getId());
                    i.setSysRoleModels(roleModels);
                    i.setCreateTime(DateUtil.parseDate(DateUtil.formatDate(i.getCreateTime())).toSqlDate());
                }).collect(Collectors.toList());
        sysUserPage.setRecords(collect);
        return sysUserPage;
    }

    /**
     * 检验用户是否正确
     *
     * @param userDto userDto
     * @return SysUserModel
     */
    public SysUserModel getUserDetail(UserDto userDto) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq(IS_DELETE, 0);
        wrapper.eq("username", userDto.getUsername());
        SysUserModel user = sysUserDao.selectOne(wrapper);
        if (null == user) {
            return new SysUserModel();
        }
        if (new BCryptPasswordEncoder().matches(userDto.getPassword(), user.getPassword())) {
            return user;
        }
        return new SysUserModel();
    }

    /**
     * 根据id查询用户详情
     *
     * @param id id
     * @return SysUserModel
     */
    public SysUserModel getUserById(Integer id) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq(IS_DELETE, 0);
        wrapper.eq("id", id);
        wrapper.select("id", "username", "phone", "mail", "nickname", "avatar", "is_disable");
        SysUserModel user = sysUserDao.selectOne(wrapper);
        List<Integer> idList = sysUserRoleDao.selectRoleId(id);
        user.setRoleIds(idList);
        return user;
    }

    /**
     * 获取用户
     *
     * @param user user
     * @return List
     */
    public List<SysUserModel> getUser(UserDto user) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(user.getUsername()) || StrUtil.isNotBlank(user.getPhone())) {
            wrapper.eq("username", user.getUsername()).or().eq("phone", user.getPhone());
        }
        wrapper.eq(IS_DELETE, 0);
        return sysUserDao.selectList(wrapper);
    }

    /**
     * 通过用户名查询用户信息
     *
     * @param username username
     * @return List
     */
    public SysUserModel getUserInfo(String username) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq(IS_DELETE, 0);
        return sysUserDao.selectOne(wrapper);
    }

    public UserInfo queryUserInfo(String username) {
        UserInfo userInfo = new UserInfo();
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq(IS_DELETE, 0);
        SysUserModel sysUserModel = sysUserDao.selectOne(wrapper);
        Integer userId = sysUserModel.getId();
        List<Integer> roleIds = sysUserRoleDao.selectRoleId(userId);
        Set<String> permission = Sets.newHashSet();
        roleIds.forEach(roleId -> {
            List<String> permissionList = sysMenuDao.getMenuByRoleId(roleId)
                    .stream()
                    .filter(i -> StrUtil.isNotBlank(i.getPermission()))
                    .map(SysMenuModel::getPermission)
                    .collect(Collectors.toList());
            permission.addAll(permissionList);
        });
        userInfo.setSysUser(sysUserModel);
        userInfo.setPermissions(Lists.newArrayList(permission));
        userInfo.setRoles(roleIds);
        return userInfo;
    }

    /**
     * 通过id删除用户
     *
     * @param id
     */
    public void deleteById(Integer id) {
        try {
            sysUserDao.deleteById(id);
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("101005", "删除失败");
        }
    }

    public void isDisableByUserId(UserDto user) {
        SysUserModel userModel = new SysUserModel();
        userModel.setId(user.getId());
        userModel.setIsDisable(user.getIsDisable());
        sysUserDao.updateById(userModel);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUserById(UserDto userDto) {
        Integer userId = userDto.getId();
        SysUserModel user = new SysUserModel();
        user.setId(userId);
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAvatar(userDto.getAvatar());
        user.setMail(userDto.getMail());
        user.setNickname(userDto.getNickname());
        user.setUpdateUser(userDto.getUpdateUser());
        sysUserDao.updateById(user);
        sysUserRoleDao.deleteByUserId(userId);
        saveUserRole(userDto, user);
    }

    private void saveUserRole(UserDto userDto, SysUserModel user) {
        userDto.getRoleIds().forEach(i -> {
            SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
            sysUserRoleModel.setRoleId(i);
            sysUserRoleModel.setUserId(user.getId());
            sysUserRoleDao.insert(sysUserRoleModel);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void parseUserExcel(String filepath) {
        List<Map<String, Object>> userMaps = ExcelUtils.importExcel(filepath);
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        List<SysUserModel> users = sysUserDao.selectList(wrapper);
        List<String> userNames = users.stream().map(SysUserModel::getUsername).collect(Collectors.toList());
        List<String> phones = users.stream().map(SysUserModel::getPhone).collect(Collectors.toList());
        long isName = userMaps.stream().filter(i -> userNames.contains(String.valueOf(i.get("username")))).count();
        if (isName > 0) {
            return;
        }
        long isPhone = userMaps.stream().filter(i -> phones.contains(String.valueOf(i.get("phone")))).count();
        if (isPhone > 0) {
            return;
        }
        userMaps.forEach(i -> {
            SysUserModel userModel = new SysUserModel();
            userModel.setUsername(String.valueOf(i.get("username")));
            userModel.setPhone(String.valueOf(i.get("phone")));
            userModel.setPassword(String.valueOf(i.get("password")));
            userModel.setSalt(UUID.randomUUID().toString());
            userModel.setCreateUser("admin");
            userModel.setUpdateUser("admin");
            sysUserDao.insert(userModel);
        });
    }

}
