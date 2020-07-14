package com.example.SecurityDemo.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecurityDemo.domain.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zfx
 * @since 2020-07-07
 */
public interface sysroleService extends IService<SysRole> {
    /**
     * 查询所有角色
     * @return
     */
    List<SysRole> findAll();

    /**
     * 通过userId查询角色
     * @param userid
     * @return
     */
    List<SysRole> getSysRolesByUserId(@Param("userid")Integer userid);

    Set<SysRole> selectRolePermissionByUserId(@Param("userid")Integer userid);
}
