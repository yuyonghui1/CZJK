package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConst;
import com.itheima.entity.Result;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    RoleService roleService;

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<Role> roleList = roleService.findAll();
            return new Result(true,MessageConst.QUERY_Role_SUCCESS,roleList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_Role_FALL);
        }
    }
}
