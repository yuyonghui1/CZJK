package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConst;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.service.MenuService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @Author zwh
 * @Date 2019/9/10 10:16
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            //获取安全框架的上下文对象
            SecurityContext securityContext = SecurityContextHolder.getContext();
//        获取认证对象
            Authentication authentication = securityContext.getAuthentication();
            //获取User对象
            Object principal = authentication.getPrincipal();
            User user = (User) principal;
            //获取用户名
            String username = user.getUsername();
            List<Menu> menuList = menuService.findAll(username);
            System.out.println(menuList);
            for (Menu menu : menuList) {
                List<Menu> menuList1 = menuService.findAllAndSon(menu.getId());
                menu.setChildren(menuList1);
            }
            return new Result(true, MessageConst.GET_MENU_SUCCESS, menuList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConst.GET_MENU_FAIL);
        }
    }
}
