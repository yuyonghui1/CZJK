package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Menu;
import org.apache.ibatis.annotations.Param;

public interface MenuDao {
    void add(Menu menu);

    void set(@Param("roleId") Integer roleId,@Param("menuId") Integer id);

    Page<Menu> findByCondition(String queryString);

    Menu findById(Integer id);

    Integer[] findRoleIdsById(Integer id);

    long findCountById(Integer id);

    void delById(Integer id);

    void edit(Menu menu);

    void delAssociation(Integer id);
}
