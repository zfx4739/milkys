package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.SysUser;
import com.example.SecurityDemo.mapper.UserMapper;
import com.example.SecurityDemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户顾客信息表 服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    /**
     * @return
     * @description 查询所有用户信息
     * @params
     * @author zfx
     * @date 2020/6/30 15:48
     */
    @Override
    public List<SysUser> findAll() {
        return baseMapper.findAll();
    }

    /**
     * @param page
     * @param queryWrapper
     * @return
     * @description 分页查询
     * @params
     * @author zfx
     * @date 2020/6/30 15:28
     */
    @Override
    public IPage<SysUser> GetUserList(IPage<SysUser> page, Wrapper<SysUser> queryWrapper) {
        IPage<SysUser> mapIPage = baseMapper.GetUserList(page, queryWrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<SysUser> records = mapIPage.getRecords();
        records.forEach(System.out::println);
        return mapIPage;
    }
    /**
    * @description 添加用户
    *@params
    * @return
    * @author  zfx
    * @date  2020/6/30 16:06
    *
    */
    @Override
    public int addUser(SysUser user) {
        user.setCreateTime(new Date());
        user.setCreator("admin");
        return baseMapper.insert(user);

    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @Override
    public int delUser(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public int updUser(SysUser user) {
       user.setUpdateTime(new Date());
        user.setMender("admin");
        return baseMapper.updateById(user);
    }

    /**
     * 用户信息详情
     *
     * @param id
     * @return
     */
    @Override
    public SysUser detali(int id) {
        QueryWrapper que=new QueryWrapper();
        que.eq("id",id);
        return baseMapper.selectOne(que);
    }

    /**
     * @param account
     * @return 根据条件查询用户信息
     * @description
     * @params
     * @author zfx
     * @date 2020/6/30 16:01
     */
    @Override
    public SysUser findUser(String account) {
        return null;
    }

    /**
     * @param openID
     * @return 根据条件查询用户信息
     * @description
     * @params
     * @author zfx
     * @date 2020/6/30 16:01
     */
    @Override
    public SysUser findOpenId(String openID) {
        QueryWrapper que=new QueryWrapper();
        que.eq("openid",openID);
        return baseMapper.selectOne(que);
    }

    @Override
    public SysUser selectByName(String userName) {
        return baseMapper.selectByName(userName);
    }
}
