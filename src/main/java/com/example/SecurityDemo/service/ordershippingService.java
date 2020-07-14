package com.example.SecurityDemo.service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecurityDemo.domain.Ordershipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单配送表 服务类
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
public interface ordershippingService extends IService<Ordershipping> {
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
    /**
     * 添加订单
     * @param ordershipping
     * @return
     */
    int addOrdershipping(Ordershipping ordershipping);

    /**
     *修改订单
     * @param id
     * @return
     */
    int delOrdershipping(int id);

    /**
     * 修改订单
     * @param ordershipping
     * @return
     */
    int updOrdershipping(Ordershipping ordershipping);

    /**
     * 获取订单信息详情
     * @param id
     * @return
     */
    Ordershipping detali(int id);
}
