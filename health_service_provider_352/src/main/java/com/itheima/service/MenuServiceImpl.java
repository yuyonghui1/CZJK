package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MenuDao;
import com.itheima.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author zwh
 * @Date 2019/9/10 10:23
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findAll(String username) {
        Integer RoleId=menuDao.findRoleId(username);
        List<Menu> menuList = menuDao.findFatherById(RoleId);
        return menuList;
    }

    @Override
    public List<Menu> findAllAndSon(Integer id) {
        return menuDao.findAllAndSon(id);
    }
}
