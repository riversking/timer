package com.rivers.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.user.api.dto.MenuTree;
import com.rivers.user.api.entity.SysMenuModel;
import com.rivers.user.mapper.SysMenuDao;
import com.rivers.utils.tree.TreeUtil;
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

    public List<MenuTree> getMenuTree() {
        QueryWrapper<SysMenuModel> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", 0);
        List<SysMenuModel> list = sysMenuDao.selectList(wrapper);
        return bulidTree(list, -1);
    }


    public SysMenuModel selectById(Integer id) {
        QueryWrapper<SysMenuModel> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.eq("del_flag", 0);
        return sysMenuDao.selectOne(wrapper);
    }

    /**
     * 通过sysMenu创建树形节点
     *
     * @param menus
     * @param root
     * @return
     */
    private List<MenuTree> bulidTree(List<SysMenuModel> menus, int root) {
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
            node.setIframe(menu.getIframe());
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }
}
