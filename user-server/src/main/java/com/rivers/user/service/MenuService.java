package com.rivers.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.user.api.entity.SysMenuModel;
import com.rivers.user.mapper.SysMenuDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuService extends ServiceImpl<SysMenuDao, SysMenuModel> {

    @Resource
    private SysMenuDao sysMenuDao;
}
