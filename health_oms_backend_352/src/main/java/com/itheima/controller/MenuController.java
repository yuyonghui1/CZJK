package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConst;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.service.MenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    MenuService menuService;

    @RequestMapping("/add")
    public Result add(Integer[] roleIds , @RequestBody Menu menu){
        try {
            menuService.add(roleIds,menu);
            return new Result(true, MessageConst.ADD_Menu_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConst.ADD_Menu_FALL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = menuService.queryPage(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Menu menu = menuService.findById(id);
            return new Result(true,MessageConst.QUERY_Menu_SUCCESS, menu);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_Menu_FALL);
        }
    }

    @RequestMapping("/findRoleIdsById")
    public Result findRoleIdsById(Integer id){
        try {
            Integer[] roleIds = menuService.findRoleIdsById(id);
            return new Result(true,MessageConst.QUERY_Role_SUCCESS, roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_Role_FALL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(Integer[] roleIds, @RequestBody Menu menu){
        try {
            menuService.edit(roleIds, menu);
            return new Result(true,MessageConst.EDIT_Menu_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.EDIT_Menu_FALL);
        }
    }

    @RequestMapping("/delById")
    public Result delById(Integer id){
        try {
            menuService.delById(id);
            return new Result(true,MessageConst.DELETE_Menu_SUCCESS);
        }catch (RuntimeException e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.DELETE_Menu_FALL);
        }
    }

}
