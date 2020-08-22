package com.rivers.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rivers.core.bean.LoginUser;
import com.rivers.user.api.dto.DeptDTO;
import com.rivers.user.api.dto.DeptTree;
import com.rivers.user.api.entity.SysDeptModel;
import com.rivers.user.dao.SysDeptDao;
import com.rivers.utils.tree.TreeUtil;
import org.springframework.beans.BeanUtils;
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

    public List<DeptDTO> getDeptList() {
        QueryWrapper<SysDeptModel> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        return sysDeptDao.selectList(wrapper)
                .stream()
                .map(i -> {
                    DeptDTO dept = new DeptDTO();
                    BeanUtils.copyProperties(i, dept);
                    return dept;
                }).collect(Collectors.toList());
    }

    public void addDept(DeptDTO deptDTO) {
        SysDeptModel deptModel = new SysDeptModel();
        LoginUser user = deptDTO.getUser();
        deptModel.setName(deptDTO.getName());
        deptModel.setParentId(deptDTO.getParentId());
        deptModel.setOrderNum(deptDTO.getOrderNum());
        deptModel.setCreateUser(user.getUserId());
        deptModel.setUpdateUser(user.getUserId());
        deptModel.insert();
    }

    public void deleteDept(Integer id) {
        sysDeptDao.deleteById(id);
    }

    public void updateDeptById(DeptDTO deptDTO) {
        SysDeptModel deptModel = new SysDeptModel();
        LoginUser user = deptDTO.getUser();
        deptModel.setId(deptDTO.getId());
        deptModel.setName(deptDTO.getName());
        deptModel.setParentId(deptDTO.getParentId());
        deptModel.setOrderNum(deptDTO.getOrderNum());
        deptModel.setUpdateUser(user.getUserId());
        deptModel.updateById();
    }


    private List<DeptTree> buildDeptTree(List<SysDeptModel> depts) {
        List<DeptTree> treeList = depts.stream()
                .map(dept -> {
                    DeptTree node = new DeptTree();
                    node.setId(dept.getId());
                    node.setParentId(dept.getParentId());
                    node.setName(dept.getName());
                    node.setLabel(dept.getName());
                    node.setOrderNum(dept.getOrderNum());
                    return node;
                }).collect(Collectors.toList());
        List<DeptTree> roots = treeList.stream()
                .filter(i -> 0 == i.getParentId())
                .collect(Collectors.toList());
        TreeUtil.buildTree(treeList, roots);
        return roots;
    }

}
