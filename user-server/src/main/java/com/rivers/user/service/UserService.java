package com.rivers.user.service;

import cn.hutool.core.collection.CollectionUtil;
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
import com.rivers.core.bean.LoginUser;
import com.rivers.core.util.ExceptionUtil;
import com.rivers.user.dto.UserDTO;
import com.rivers.user.api.entity.SysMenuModel;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.api.entity.SysUserRoleModel;
import com.rivers.user.dao.SysMenuDao;
import com.rivers.user.dao.SysRoleDao;
import com.rivers.user.dao.SysUserDao;
import com.rivers.user.dao.SysUserRoleDao;
import com.rivers.user.util.AESUtil;
import com.rivers.user.util.ExcelUtils;
import com.rivers.userservice.proto.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.math.NumberUtils;
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

    public static final String IS_DELETE = "is_delete";

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Resource
    private SysRoleDao sysRoleDao;


    @Resource
    private SysMenuDao sysMenuDao;


    /**
     * 添加用户
     *
     * @param userDto userDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDTO userDto) {
        String userId = sysUserDao.selectUserId();
        String code = null;
        if (null != userId) {
            code = userId.substring(userId.indexOf("T") + 1);
        }
        int num = NumberUtils.toInt(code, 0);
        SysUserModel sysUserModel = new SysUserModel();
        LoginUser user = userDto.getUser();
        sysUserModel.setUsername(userDto.getUsername());
        sysUserModel.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        sysUserModel.setUserId(String.format("T%05d", num + 1));
        sysUserModel.setPhone(userDto.getPhone());
        sysUserModel.setAvatar(userDto.getAvatar());
        sysUserModel.setSalt(UUID.randomUUID().toString());
        sysUserModel.setCreateUser(user.getUserId());
        sysUserModel.setUpdateUser(user.getUserId());
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
                    i.setPhone(mobileEncrypt(i.getPhone()));
                    i.setCreateTime(DateUtil.parseDate(DateUtil.formatDate(i.getCreateTime())).toSqlDate());
                }).collect(Collectors.toList());
        sysUserPage.setRecords(collect);
        return sysUserPage;
    }

    public String mobileEncrypt(String mobile) {
        if (StrUtil.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
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
     * @param username username
     * @return List
     */
    public Integer getUserByUserName(String username) {
        return sysUserDao.selectUserByUserName(username);
    }

    public Integer getUserByPhone(String phone) {
        return sysUserDao.selectUserByPhone(phone);
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
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq(IS_DELETE, 0);
        SysUserModel user = sysUserDao.selectOne(wrapper);
        String userId = user.getUserId();
        List<Integer> roleIds = sysUserRoleDao.selectRoleId(userId);
        String ids = roleIds.stream()
                .map(i -> "'" + i + "'")
                .collect(Collectors.joining(","));
        Set<String> permissions = sysMenuDao.getMenuByRoleId("(" + ids + ")")
                .stream()
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toSet());
        return UserInfo.newBuilder()
                .setId(user.getId())
                .setPassword(user.getPassword())
                .setUsername(user.getUsername())
                .setUserId(userId)
                .setIsDisable(user.getIsDisable())
                .addAllPermissions(permissions)
                .build();
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
        LoginUser loginUser = userDto.getUser();
        user.setId(userId);
        user.setUsername(userDto.getUsername());
        user.setPhone(userDto.getPhone());
        user.setAvatar(userDto.getAvatar());
        user.setMail(userDto.getMail());
        user.setNickname(userDto.getNickname());
        user.setUpdateUser(loginUser.getUserId());
        sysUserDao.updateById(user);
        sysUserRoleDao.deleteByUserId(userDto.getUserId());
        saveUserRole(userDto, user);
    }

    private void saveUserRole(UserDTO userDto, SysUserModel user) {
        List<Integer> roleIds = userDto.getRoleIds();
        if (CollectionUtil.isEmpty(roleIds)) {
            return;
        }
        roleIds.forEach(i -> {
            SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
            sysUserRoleModel.setRoleId(i);
            sysUserRoleModel.setUserId(user.getUserId());
            sysUserRoleDao.insert(sysUserRoleModel);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void parseUserExcel(String filepath) {
        List<Map<String, Object>> userMaps = ExcelUtils.importExcel(filepath);
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq(IS_DELETE, 0);
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
            userModel.setPassword(new BCryptPasswordEncoder().encode(String.valueOf(i.get("password"))));
            userModel.setUserId(String.valueOf(i.get("userId")));
            userModel.setSalt(UUID.randomUUID().toString());
            userModel.setCreateUser("admin");
            userModel.setUpdateUser("admin");
            sysUserDao.insert(userModel);
        });
    }

    public void exportUserExcel() {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq(IS_DELETE, 0);
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

    public ChangePasswordRes changePwd(ChangePasswordReq changePasswordReq) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.eq(IS_DELETE, 0);
        wrapper.eq("id", changePasswordReq.getId());
        wrapper.select("id", "password");
        SysUserModel user = sysUserDao.selectOne(wrapper);
        if (null == user) {
            return ChangePasswordRes.failed(-608106, "用户不存在");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String oldPassword = AESUtil.desEncrypt(changePasswordReq.getOldPassword()).trim();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return ChangePasswordRes.failed(-608107, "旧的密码不正确");
        }
        String firstPassword = AESUtil.desEncrypt(changePasswordReq.getFirstPassword());
        user.setPassword(passwordEncoder.encode(firstPassword));
        user.updateById();
        return ChangePasswordRes.ok();
    }
}
