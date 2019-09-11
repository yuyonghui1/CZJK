package com.itheima.dao;

import com.itheima.pojo.Menu;

import java.util.List;

/**
 * @version 1.0
 * @Author zwh
 * @Date 2019/9/10 10:24
 */
public interface MenuDao {

    List<Menu> findAllAndSon(Integer id);

    Integer findRoleId(String username);

    List<Menu> findFatherById(Integer roleId);
}
