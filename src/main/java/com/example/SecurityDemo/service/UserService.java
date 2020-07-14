package com.example.SecurityDemo.service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecurityDemo.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户顾客信息表 服务类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface UserService extends IService<SysUser> {
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
     * @description 分页查询
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 15:28
     *
     */
    IPage<SysUser> GetUserList(@Param("page") IPage<SysUser> page, @Param(Constants.WRAPPER) Wrapper<SysUser> queryWrapper);
    /*
     *   添加用户信息账号
     * */
    int  addUser(SysUser user);

        /**
         * 删除用户信息
         * @param id
         * @return
         */
        int  delUser(int id);

        /**
         * 修改用户信息
         * @param user
         * @return
         */
        int updUser(SysUser user);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    SysUser detali(int id);

    
    /**
    * @description 
    *@params  
    * @return  根据条件查询用户信息
    * @author  zfx
    * @date  2020/6/30 16:01
    *
    */
    SysUser findUser(String account);

    /**
     * @description
     *@params
     * @return  根据条件查询用户信息
     * @author  zfx
     * @date  2020/6/30 16:01
     *
     */
    SysUser findOpenId(String openID);


    SysUser selectByName(String userName);


}
