package com.rivers.user.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.rivers.core.util.ExceptionUtil;
import com.rivers.user.api.dto.UserDTO;
import com.rivers.user.api.dto.UserInfo;
import com.rivers.user.api.entity.SysMenuModel;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.api.entity.SysUserRoleModel;
import com.rivers.user.dao.SysMenuDao;
import com.rivers.user.dao.SysRoleDao;
import com.rivers.user.dao.SysUserDao;
import com.rivers.user.dao.SysUserRoleDao;
import com.rivers.user.util.ExcelUtils;
import com.rivers.userservice.proto.GetUserListReq;
import com.rivers.userservice.proto.GetUserListRes;
import com.rivers.userservice.proto.User;
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

    public static final String IS_DELETE1 = "is_delete";
    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Resource
    private SysRoleDao sysRoleDao;


    @Resource
    private SysMenuDao sysMenuDao;

    private static String IS_DELETE = IS_DELETE1;


    /**
     * 添加用户
     *
     * @param userDto userDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDTO userDto) {
        SysUserModel sysUserModel = new SysUserModel();
        sysUserModel.setUsername(userDto.getUsername());
        sysUserModel.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        sysUserModel.setUserId(userDto.getUserId());
        sysUserModel.setPhone(userDto.getPhone());
        sysUserModel.setAvatar(userDto.getAvatar());
        sysUserModel.setSalt(UUID.randomUUID().toString());
        sysUserModel.setCreateUser(userDto.getCreateUser());
        sysUserModel.setUpdateUser(userDto.getUpdateUser());
        sysUserDao.insert(sysUserModel);
        saveUserRole(userDto, sysUserModel);
    }

    /**
     * 分页查询用户
     *
     * @param userDto userDto
     * @return IPage
     */
    public IPage<SysUserModel> getUserPage(UserDTO userDto) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(userDto.getUsername())) {
            wrapper.eq("username", userDto.getUsername());
        }
        if (StrUtil.isNotBlank(userDto.getPhone())) {
            wrapper.eq("phone", userDto.getPhone());
        }
        if (StrUtil.isNotBlank(userDto.getStartDate()) && StrUtil.isNotBlank(userDto.getEndDate())) {
            wrapper.apply("date_format(create_time,'%Y-%m-%d') >= {0}", userDto.getStartDate())
                    .apply("date_format(create_time,'%Y-%m-%d') <= {0}", userDto.getEndDate());
        }
        wrapper.eq(IS_DELETE, 0);
        wrapper.select(SysUserModel.class, info -> !"password".equals(info.getColumn()));
        Page<SysUserModel> page = new Page<>(userDto.getPage(), userDto.getPageSize());
        IPage<SysUserModel> sysUserPage = sysUserDao.selectPage(page, wrapper);
        List<SysUserModel> collect = sysUserPage.getRecords()
                .stream()
                .peek(i -> {
                    List<SysRoleModel> roleModels = sysRoleDao.selectRoleByUserId(i.getUserId());
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
    public SysUserModel getUserDetail(UserDTO userDto) {
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
        wrapper.select("id", "username", "user_id", "phone", "mail", "nickname", "avatar", "is_disable");
        SysUserModel user = sysUserDao.selectOne(wrapper);
        List<Integer> idList = sysUserRoleDao.selectRoleId(user.getUserId());
        user.setRoleIds(idList);
        return user;
    }

    /**
     * 获取用户
     *
     * @param user user
     * @return List
     */
    public List<SysUserModel> getUser(UserDTO user) {
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
        String userId = sysUserModel.getUserId();
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

    public void isDisableByUserId(UserDTO user) {
        SysUserModel userModel = new SysUserModel();
        userModel.setId(user.getId());
        userModel.setIsDisable(user.getIsDisable());
        sysUserDao.updateById(userModel);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUserById(UserDTO userDto) {
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

    private void saveUserRole(UserDTO userDto, SysUserModel user) {
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
        wrapper.eq(IS_DELETE1, 0);
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
            userModel.setUserId(String.valueOf(i.get("userId")));
            userModel.setSalt(UUID.randomUUID().toString());
            userModel.setCreateUser("admin");
            userModel.setUpdateUser("admin");
            sysUserDao.insert(userModel);
        });
    }

    public void exportUserExcel() {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq(IS_DELETE1, 0);
        List<SysUserModel> list = sysUserDao.selectList(wrapper);
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.merge(4, "一班成绩单");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        writer.flush();
        // 关闭writer，释放内存
        writer.close();
    }


    public GetUserListRes getUserList(GetUserListReq getUserListReq) {
        IPage<SysUserModel> userList = getUserByPage(getUserListReq);
        List<User> users = userList.getRecords().stream()
                .map(i -> User.newBuilder()
                        .setUserId(i.getUserId())
                        .setMail(i.getMail())
                        .setNickname(i.getNickname())
                        .setPhone(i.getPhone())
                        .setCreateDate(DateUtil.formatDate(i.getCreateTime()))
                        .setUsername(i.getUsername())
                        .build())
                .collect(Collectors.toList());
        return GetUserListRes.newBuilder().addAllUsers(users).setTotal(Math.toIntExact(userList.getTotal())).build();
    }

    private IPage<SysUserModel> getUserByPage(GetUserListReq getUserListReq) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        String username = getUserListReq.getUsername();
        String mobile = getUserListReq.getMobile();
        String startDate = getUserListReq.getStartDate();
        String endDate = getUserListReq.getEndDate();
        if (StrUtil.isNotBlank(username)) {
            wrapper.eq("username", username);
        }
        if (StrUtil.isNotBlank(mobile)) {
            wrapper.eq("phone", mobile);
        }
        if (StrUtil.isNotBlank(startDate)
                && StrUtil.isNotBlank(endDate)) {
            wrapper.apply("date_format(create_time,'%Y-%m-%d') >= {0}", startDate)
                    .apply("date_format(create_time,'%Y-%m-%d') <= {0}", endDate);
        }
        wrapper.eq(IS_DELETE, 0);
        wrapper.select(SysUserModel.class, info -> !"password".equals(info.getColumn()));
        Page<SysUserModel> page = new Page<>(getUserListReq.getPage().getPageNum(),
                getUserListReq.getPage().getPageSize());
        return sysUserDao.selectPage(page, wrapper);
    }
}
