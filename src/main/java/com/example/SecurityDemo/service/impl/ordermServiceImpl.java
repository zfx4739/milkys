package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.Orderitem;
import com.example.SecurityDemo.domain.Orderm;
import com.example.SecurityDemo.dto.AddOrderDto;
import com.example.SecurityDemo.mapper.ordermMapper;
import com.example.SecurityDemo.service.orderitemService;
import com.example.SecurityDemo.service.ordermService;
import com.example.SecurityDemo.util.TimeNumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单的基本信息 服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
@Service
public class ordermServiceImpl extends ServiceImpl<ordermMapper, Orderm> implements ordermService {


    //订单详情
    @Autowired
    private orderitemService orderitemservice;
    /**
     * @return 查询所有订单数据
     * @description
     * @params
     * @author zfx
     * @date 2020/6/30 15:05
     */
    @Override
    public List<Orderm> findAll() {
        return baseMapper.findAll();
    }

    /**
     * 分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<Orderm> GetOrdermList(IPage<Orderm> page, Wrapper<Orderm> queryWrapper) {
        IPage<Orderm> mapIPage = baseMapper.GetOrdermList(page, queryWrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<Orderm> records = mapIPage.getRecords();
        records.forEach(System.out::println);
        return mapIPage;
    }

    /**
     * 添加订单
     *
     * @param orderm
     * @return
     */
    @Override
    public int addOrderm(Orderm orderm) {
        //订单创建时间
        orderm.setCreateTime(new Date());
        //创建人
        orderm.setCreator("周峰喜");

        String ordernum=TimeNumberUtils.getLocalTrmSeqNum();
        ordernum=TimeNumberUtils.addLeftZero(ordernum,20);
        //订单号
        orderm.setOrderNumber(ordernum);
        Orderitem orderitem=new Orderitem();

        orderitem.setProductid(Integer.parseInt(orderm.getProductId()));
        orderitem.setOrderid(ordernum);
        orderitem.setNum(1);
        orderitem.setTitle("商品标题");
        orderitem.setPrice(12);
        orderitem.setTotalfee(28);
        orderitem.setPicpath("dsad");
        orderitemservice.addrderitem(orderitem);
        return baseMapper.insert(orderm);
    }

    /**
     * wx添加订单
     *
     * @param addOrderDto
     * @return
     */
    @Override
    public int addOrderm(AddOrderDto addOrderDto) {
        Orderm orderm=new Orderm();
        //订单创建时间
        orderm.setCreateTime(new Date());
        //创建人
        orderm.setCreator("周峰喜");

        String ordernum=TimeNumberUtils.getLocalTrmSeqNum();
        ordernum=TimeNumberUtils.addLeftZero(ordernum,20);
        //订单号
        orderm.setOrderNumber(ordernum);
        Orderitem orderitem=new Orderitem();

        orderitem.setProductid(Integer.parseInt(orderm.getProductId()));
        orderitem.setOrderid(ordernum);
        orderitem.setNum(1);
        orderitem.setTitle("商品标题");
        orderitem.setPrice(12);
        orderitem.setTotalfee(28);
        orderitem.setPicpath("dsad");
        orderitemservice.addrderitem(orderitem);
     //   return baseMapper.insert(orderm);
        return 1;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int delOrder(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改订单数据
     * @param orderm
     * @return
     */
    @Override
    public int updOrder(Orderm orderm) {
        orderm.setUpdateTime(new Date());
        orderm.setMender("白雪");
        return baseMapper.updateById(orderm);
    }

    /**
     * 获取订单信息详情
     *
     * @param id
     * @return
     */
    @Override
    public Orderm detali(int id) {
        QueryWrapper que=new QueryWrapper();
        que.eq("id",id);
        return baseMapper.selectOne(que);
    }
}
