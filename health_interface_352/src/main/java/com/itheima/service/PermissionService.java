package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Permission;

public interface PermissionService {
    void add(Integer[] roleIds, Permission permission);

    PageResult queryPage(QueryPageBean queryPageBean);

    Permission findById(Integer id);

    Integer[] findRoleIdsById(Integer id);

    void delById(Integer id);

    void edit(Integer[] roleIds, Permission permission);
}
