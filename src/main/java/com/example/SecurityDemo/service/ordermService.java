package com.example.SecurityDemo.service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecurityDemo.domain.Orderm;
import com.example.SecurityDemo.dto.AddOrderDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单的基本信息 服务类
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
public interface ordermService extends IService<Orderm> {
    /**
     * @description
     *@params
     * @return  查询所有订单数据
     * @author  zfx
     * @date  2020/6/30 15:05
     *
     */
    List<Orderm> findAll();

    /**
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Orderm> GetOrdermList(@Param("page") IPage<Orderm> page, @Param(Constants.WRAPPER) Wrapper<Orderm> queryWrapper);

    /**
     * 添加订单
     * @param orderm
     * @return
     */
    int addOrderm(Orderm orderm);

    /**
     * wx添加订单
     * @param addOrderDto
     * @return
     */
    int addOrderm(AddOrderDto addOrderDto);

    /**
     *修改订单
     * @param id
     * @return
     */
    int delOrder(int id);

    /**
     * 修改订单
     * @param orderm
     * @return
     */
    int updOrder(Orderm orderm);

    /**
     * 获取订单信息详情
     * @param id
     * @return
     */
    Orderm detali(int id);


}
