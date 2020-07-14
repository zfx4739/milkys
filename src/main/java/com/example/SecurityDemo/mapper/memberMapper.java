package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.SecurityDemo.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 会员信息表 Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface memberMapper extends BaseMapper<Member> {
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
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Member> GetMemberList(@Param("page") IPage<Member> page, @Param(Constants.WRAPPER) Wrapper<Member> queryWrapper);


}
