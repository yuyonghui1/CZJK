package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;

public interface MenuService {
    void add(Integer[] roleIds, Menu menu);

    PageResult queryPage(QueryPageBean queryPageBean);

    Menu findById(Integer id);

    Integer[] findRoleIdsById(Integer id);

    void edit(Integer[] roleIds, Menu menu);

    void delById(Integer id);
}
