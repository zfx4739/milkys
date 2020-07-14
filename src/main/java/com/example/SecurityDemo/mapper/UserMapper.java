package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.SecurityDemo.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户顾客信息表 Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface UserMapper extends BaseMapper<SysUser> {
    /**
    * @description  查询所有用户信息
    *@params
    * @return
    * @author  zfx
    * @date  2020/6/30 15:48
    *
    */
    List<SysUser> findAll();

    /**
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<SysUser> GetUserList(@Param("page") IPage<SysUser> page, @Param(Constants.WRAPPER) Wrapper<SysUser> queryWrapper);

    /**
     * 根据名字查询用户
     * @param userName
     * @return
     */
    SysUser selectByName(String userName);
}
