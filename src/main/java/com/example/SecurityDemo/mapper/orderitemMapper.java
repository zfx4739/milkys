package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.SecurityDemo.domain.Orderitem;
import com.example.SecurityDemo.domain.Orderm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单详情表：订单的详情主要就是购买商品的信息，通过订单的id来实现关联 Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
public interface orderitemMapper extends BaseMapper<Orderitem> {
    /**
     * @description
     *@params
     * @return  查询所有订单详情数据
     * @author  zfx
     * @date  2020/6/30 15:05
     *
     */
    List<Orderitem> findAll();

    /**
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Orderitem> GetOrderitemList(@Param("page") IPage<Orderitem> page, @Param(Constants.WRAPPER) Wrapper<Orderitem> queryWrapper);

}
