package com.rivers.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rivers.user.api.dto.DeptTree;
import com.rivers.user.api.entity.SysDeptModel;
import com.rivers.user.mapper.SysDeptDao;
import com.rivers.utils.tree.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author riverskingkng
 */
@Service
public class DeptService {

    @Resource
    private SysDeptDao sysDeptDao;

    /**
     * 获取树形menu
     *
     * @return List
     */
    public List<DeptTree> getDeptTree() {
        QueryWrapper<SysDeptModel> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        List<SysDeptModel> list = sysDeptDao.selectList(wrapper);
        return buildDeptTree(list);
    }


    private List<DeptTree> buildDeptTree(List<SysDeptModel> depts) {
        List<DeptTree> treeList = depts.stream()
                .filter(dept -> !dept.getId().equals(dept.getParentId()))
                .map(dept -> {
                    DeptTree node = new DeptTree();
                    node.setId(dept.getId());
                    node.setParentId(dept.getParentId());
                    node.setName(dept.getName());
                    node.setTitle(dept.getName());
                    return node;
                }).collect(Collectors.toList());
        return TreeUtil.bulid(treeList, 0);
    }

}
