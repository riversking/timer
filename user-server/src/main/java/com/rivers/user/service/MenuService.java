package com.rivers.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rivers.user.api.dto.MenuDto;
import com.rivers.user.api.dto.MenuRoleDto;
import com.rivers.user.api.dto.MenuTree;
import com.rivers.user.api.entity.SysMenuModel;
import com.rivers.user.api.entity.SysRoleMenuModel;
import com.rivers.user.api.vo.MenuVo;
import com.rivers.user.mapper.SysMenuDao;
import com.rivers.user.mapper.SysRoleMenuDao;
import com.rivers.utils.tree.TreeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author riverskingking
 */
@Service
public class MenuService extends ServiceImpl<SysMenuDao, SysMenuModel> {

    @Resource
    private SysMenuDao sysMenuDao;

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;


    /**
     * 获取树形menu
     *
     * @return List
     */
    public List<MenuTree> getMenuTree() {
        QueryWrapper<SysMenuModel> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        List<SysMenuModel> list = sysMenuDao.selectList(wrapper);
        return buildTree(list, -1);
    }


    /**
     * 通过id获取menu详情
     *
     * @param id id
     * @return SysMenuModel
     */
    public SysMenuModel selectMenuById(Integer id) {
        QueryWrapper<SysMenuModel> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.eq("is_delete", 0);
        return sysMenuDao.selectOne(wrapper);
    }


    /**
     * 新增菜单
     *
     * @param menuDto menuDto
     */
    public void addMenu(MenuDto menuDto) {
        SysMenuModel sysMenuModel = new SysMenuModel();
        BeanUtils.copyProperties(menuDto, sysMenuModel);
        sysMenuDao.insert(sysMenuModel);
    }

    /**
     * 删除 menu & button
     *
     * @param id id
     */
    public void deleteMenuById(Integer id) {
        sysMenuDao.deleteById(id);
    }

    /**
     * 更新 menu & button
     *
     * @param menuDto menuDto
     */
    public void updateMenuById(MenuDto menuDto) {
        SysMenuModel sysMenuModel = new SysMenuModel();
        BeanUtils.copyProperties(menuDto, sysMenuModel);
        sysMenuDao.updateById(sysMenuModel);
    }


    /**
     * 通过角色id 查询 menu树
     *
     * @param id id
     * @return List
     */
    public List<MenuTree> getMenuByRoleId(Integer id) {
        List<Integer> list = sysRoleMenuDao.getMenuIdByRoleId(id);
        QueryWrapper<SysMenuModel> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        List<SysMenuModel> menuList = sysMenuDao.selectList(wrapper).stream().peek(i -> {
            if (list.contains(i.getId())) {
                i.setChecked(true);
            }
        }).collect(Collectors.toList());
        return buildTree(menuList, -1);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateMenuByRoleId(MenuRoleDto menuRoleDto) {
        SysRoleMenuModel sysRoleMenuModel = new SysRoleMenuModel();
        sysRoleMenuModel.setIsDelete(1);
        sysRoleMenuModel.setRoleId(menuRoleDto.getRoleId());
        sysRoleMenuDao.updateByRoleId(sysRoleMenuModel);
        menuRoleDto.getMenuIds().forEach(i -> {
            sysRoleMenuModel.setIsDelete(0);
            sysRoleMenuModel.setMenuId(i);
            sysRoleMenuDao.insert(sysRoleMenuModel);
        });
    }

    public MenuVo getMenuByUserId(Integer userId) {
        List<Integer> collect = sysMenuDao.getMenuByUserId(userId).stream().map(SysMenuModel::getId).collect(Collectors.toList());
        MenuVo menuVo = new MenuVo();
        menuVo.setAccess(collect);
        return menuVo;
    }

    public List<MenuTree> getMenu(Integer id) {
        List<Integer> list = sysRoleMenuDao.getMenuIdByRoleId(id);
        QueryWrapper<SysMenuModel> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        List<SysMenuModel> menuList = sysMenuDao.selectList(wrapper).stream().filter(i -> i.getType() == 0).map(j->{
            SysMenuModel sysMenuModel = new SysMenuModel();
            BeanUtils.copyProperties(j,sysMenuModel);
            return sysMenuModel;
        }).collect(Collectors.toList());
        return buildTree(menuList, -1);
    }


    /**
     * 通过sysMenu创建树形节点
     *
     * @param menus menus
     * @param root  root
     * @return List
     */
    private List<MenuTree> buildTree(List<SysMenuModel> menus, int root) {
        List<MenuTree> trees = new ArrayList<>();
        for (SysMenuModel menu : menus) {
            MenuTree node = new MenuTree();
            node.setId(menu.getId());
            node.setParentId(menu.getParentId());
            node.setName(menu.getName());
            node.setPath(menu.getPath());
            node.setCode(menu.getPermission());
            node.setLabel(menu.getName());
            node.setComponent(menu.getComponent());
            node.setIcon(menu.getIcon());
            node.setTitle(menu.getName());
            node.setExpand(true);
            node.setChecked(menu.isChecked());
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }
}
