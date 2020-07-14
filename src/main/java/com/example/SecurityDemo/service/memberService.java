package com.example.SecurityDemo.service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecurityDemo.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 会员信息表 服务类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface memberService extends IService<Member> {
    /**
     * @description
     *@params
     * @return  查询所有会员数据
     * @author  zfx
     * @date  2020/6/30 15:05
     *
     */
    List<Member> findAll();
    /**
    * @description 分页查询
    *@params
    * @return
    * @author  zfx
    * @date  2020/6/30 15:28
    *
    */
    IPage<Member> GetMemberList(@Param("page") IPage<Member> page, @Param(Constants.WRAPPER) Wrapper<Member> queryWrapper);
    /*
     *   添加会员账号
     * */
    int  addMember(Member member);

    /**
     * 删除会员信息
     * @param id
     * @return
     */
    int  delMember(int id);

    /**
     * 修改订单信息
     * @param member
     * @return
     */
    int updMember(Member member);

    /**
     * 删除订单信息
     * @param id
     * @return
     */
    Member detali(int id);

    Member findMember(String account);



}
