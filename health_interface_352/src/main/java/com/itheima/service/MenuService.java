package com.itheima.service;

import com.itheima.pojo.Menu;

import java.util.List;

/**
 * @version 1.0
 * @Author zwh
 * @Date 2019/9/10 10:23
 */
public interface MenuService {
    List<Menu> findAll(String username);

    List<Menu> findAllAndSon(Integer id);
}
