package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
public interface PermissionDao {
    Set<Permission> findPermissionsByRoleId(Integer id);

    void add(Permission permission);

    void set( @Param("roleId") Integer roleId,@Param("permissionId")Integer id);

    Page<Permission> findByCondition(String queryString);

    Permission findById(Integer id);

    Integer[] findRoleIdsById(Integer id);

    long findCountById(Integer id);

    void delById(Integer id);

    void edit(Permission permission);

    void delAssociation(Integer id);
}
