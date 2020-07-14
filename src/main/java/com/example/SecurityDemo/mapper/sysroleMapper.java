package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SecurityDemo.domain.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-07-07
 */
@Mapper
public interface sysroleMapper extends BaseMapper<SysRole> {
    @Results(
            {@Result(id=true,column="id",property="id"),
                    @Result(column="name", property="name")
            })
    @Select("select * from sys_role where id in (select roles_id from sys_user_roles where sys_user_id=#{userid})")
    List<SysRole> getSysRolesByUserId(@Param("userid")Integer userid);

    @Select("select * from sys_role where id in (select roles_id from sys_user_roles where sys_user_id=#{userid})")
    Set<SysRole> selectRolePermissionByUserId(@Param("userid")Integer userid);

    List<SysRole> findAll();
}
