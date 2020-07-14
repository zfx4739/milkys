package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.Member;
import com.example.SecurityDemo.mapper.memberMapper;
import com.example.SecurityDemo.service.memberService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员信息表 服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
@Service
public class memberServiceImpl extends ServiceImpl<memberMapper, Member> implements memberService {

    /**
     * @return 查询所有会员数据
     * @description
     * @params
     * @author zfx
     * @date 2020/6/30 15:05
     */
    @Override
    public List<Member> findAll() {
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
    public IPage<Member> GetMemberList(IPage<Member> page, Wrapper<Member> queryWrapper) {
        IPage<Member> mapIPage = baseMapper.GetMemberList(page, queryWrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<Member> records = mapIPage.getRecords();
        records.forEach(System.out::println);
        return mapIPage;    }

    /**
     * @description
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/16 16:58
     *
     */

    @Override
    public int addMember(Member member) {
        //会员创建是时间

        //创建数据时间
             member.setCreateTime(new Date());

        return baseMapper.insert(member);
    }

    /**
     * 删除会员信息
     *
     * @param id
     * @return
     */
    @Override
    public int delMember(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改订单信息
     *
     * @param member
     * @return
     */
    @Override
    public int updMember(Member member) {
        member.setUpdateTime(new Date());
        return baseMapper.updateById(member);
    }

    /**
     * 删除订单信息
     *
     * @param id
     * @return
     */
    @Override
    public Member detali(int id) {
        QueryWrapper<Member> que=new QueryWrapper<Member>();
        que.eq("id",id);
        return baseMapper.selectOne(que);
    }

    @Override
    public Member findMember(String account) {
        QueryWrapper<Member> que=new QueryWrapper<Member>();
        que.eq("account",account);
        return baseMapper.selectOne(que);
    }
}
