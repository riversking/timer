package com.rivers.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.user.api.dto.MenuDto;
import com.rivers.user.api.dto.MenuTree;
import com.rivers.user.api.entity.SysMenuModel;
import com.rivers.user.mapper.SysMenuDao;
import com.rivers.utils.tree.TreeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author riverskingking
 */
@Service
public class MenuService extends ServiceImpl<SysMenuDao, SysMenuModel> {

    @Resource
    private SysMenuDao sysMenuDao;


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
     * @param menuDto
     */
    public void addMenu(MenuDto menuDto) {
        SysMenuModel sysMenuModel = new SysMenuModel();
        BeanUtils.copyProperties(menuDto,sysMenuModel);
        sysMenuDao.insert(sysMenuModel);
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
        MenuTree node;
        for (SysMenuModel menu : menus) {
            node = new MenuTree();
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
            node.setIsContent(menu.getIsContent());
            node.setIFrame(menu.getIframe());
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }
}
