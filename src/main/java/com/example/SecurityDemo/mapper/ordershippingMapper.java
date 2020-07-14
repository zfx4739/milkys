package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.SecurityDemo.domain.Ordershipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单配送表 Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
public interface ordershippingMapper extends BaseMapper<Ordershipping> {
    /**
     * @description
     *@params
     * @return  查询所有订单详情数据
     * @author  zfx
     * @date  2020/6/30 15:05
     *
     */
    List<Ordershipping> findAll();

    /**
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Ordershipping> GetOrdershippingList(@Param("page") IPage<Ordershipping> page, @Param(Constants.WRAPPER) Wrapper<Ordershipping> queryWrapper);
}
