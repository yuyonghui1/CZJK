package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.MenuDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    @Transactional
    public void add(Integer[] roleIds, Menu menu) {
        //1. 添加检查组的基本信息（主键回显（selectKey））
        menuDao.add(menu);
        // 2. 维护检查组与检查项中间表的关系
        if(menu.getId() != null && roleIds != null && roleIds.length > 0 ){
            for (Integer roleId : roleIds) {
                menuDao.set(roleId,menu.getId());
            }
        }
    }

    @Override
    public PageResult queryPage(QueryPageBean queryPageBean) {
        //1. 开始分页
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //2. 条件查询
        Page<Menu> page = menuDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page);
    }

    @Override
    public Menu findById(Integer id) {
        return menuDao.findById(id);
    }

    @Override
    public Integer[] findRoleIdsById(Integer id) {
        return menuDao.findRoleIdsById(id);
    }

    @Override
    public void edit(Integer[] roleIds, Menu menu) {
        //1. 修改检查组的基本数据
        menuDao.edit(menu);
        // 2. 维护检查组和检查项的关系
        //2.1 先删除该检查组与检查项的关系
        menuDao.delAssociation(menu.getId());
        //2.2 维护检查组和检查项新的关系
        if(roleIds != null && roleIds.length > 0){
            for (Integer roleId : roleIds) {
                menuDao.set(roleId ,menu.getId());
            }
        }
    }

    @Override
    public void delById(Integer id) {
        long count = menuDao.findCountById(id);
        if(count > 0) {
            //说明被关联
            throw new RuntimeException("该菜单栏被角色关联，不能删除！！");
        }else {
            menuDao.delById(id);
        }
    }
}
