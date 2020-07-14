package com.example.SecurityDemo.service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecurityDemo.domain.Orderitem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单详情表：订单的详情主要就是购买商品的信息，通过订单的id来实现关联 服务类
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
public interface orderitemService extends IService<Orderitem> {
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
    /**
     * 添加订单
     * @param orderitem
     * @return
     */
    int addrderitem(Orderitem orderitem);

    /**
     *修改订单
     * @param id
     * @return
     */
    int delrderitem(int id);

    /**
     * 修改订单
     * @param orderitem
     * @return
     */
    int updOrderitem(Orderitem orderitem);

    /**
     * 获取订单信息详情
     * @param id
     * @return
     */
    Orderitem detali(int id);

}
