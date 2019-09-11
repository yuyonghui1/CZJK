package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public void add(Integer[] roleIds, Permission permission) {
        //1. 添加检查组的基本信息（主键回显（selectKey））
        permissionDao.add(permission);
        // 2. 维护检查组与检查项中间表的关系
        if(permission.getId() != null && roleIds != null && roleIds.length > 0 ){
            for (Integer roleId : roleIds) {
                permissionDao.set(roleId,permission.getId());
            }
        }
    }

    @Override
    public PageResult queryPage(QueryPageBean queryPageBean) {
        //1. 开始分页
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //2. 条件查询
        Page<Permission> page = permissionDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page);
    }

    @Override
    public Permission findById(Integer id) {
        return permissionDao.findById(id);
    }

    @Override
    public Integer[] findRoleIdsById(Integer id) {
        return permissionDao.findRoleIdsById(id);
    }

    @Override
    public void delById(Integer id) {
        long count = permissionDao.findCountById(id);
        if(count > 0) {
            //说明被关联
            throw new RuntimeException("该权限项被角色关联，不能删除！！");
        }else {
            permissionDao.delById(id);
        }
    }

    @Override
    public void edit(Integer[] roleIds, Permission permission) {
        //1. 修改检查组的基本数据
        permissionDao.edit(permission);
        // 2. 维护检查组和检查项的关系
        //2.1 先删除该检查组与检查项的关系
        permissionDao.delAssociation(permission.getId());
        //2.2 维护检查组和检查项新的关系
        if(roleIds != null && roleIds.length > 0){
            for (Integer roleId : roleIds) {
                permissionDao.set(roleId ,permission.getId());
            }
        }
    }
}
