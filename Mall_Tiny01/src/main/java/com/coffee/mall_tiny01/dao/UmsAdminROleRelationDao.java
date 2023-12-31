package com.coffee.mall_tiny01.dao;

import com.coffee.mall_tiny01.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 * */
public interface UmsAdminRoleRelationDao {

    /*
    * 获取用户所有权限(包含+-权限)
    * */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

}
