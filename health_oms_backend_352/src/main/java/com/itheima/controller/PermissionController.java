package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConst;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Reference
    PermissionService permissionService;

    @RequestMapping("/add")
    public Result add(Integer[] roleIds , @RequestBody Permission permission){
        try {
            permissionService.add(roleIds,permission);
            return new Result(true, MessageConst.ADD_Permission_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConst.ADD_Permission_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = permissionService.queryPage(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Permission permission = permissionService.findById(id);
            return new Result(true,MessageConst.QUERY_Permission_SUCCESS, permission);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_Permission_FAIL);
        }
    }

    @RequestMapping("/findRoleIdsById")
    public Result findRoleIdsById(Integer id){
        try {
            Integer[] roleIds = permissionService.findRoleIdsById(id);
            return new Result(true,MessageConst.QUERY_Role_SUCCESS, roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_Role_FALL);
        }
    }

    @RequestMapping("/delById")
    public Result delById(Integer id){
        try {
            permissionService.delById(id);
            return new Result(true,MessageConst.DELETE_Permission_SUCCESS);
        }catch (RuntimeException e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.DELETE_Permission_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(Integer[] roleIds, @RequestBody Permission permission){
        try {
            permissionService.edit(roleIds, permission);
            return new Result(true,MessageConst.EDIT_Permission_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.EDIT_Permission_FAIL);
        }
    }
}
