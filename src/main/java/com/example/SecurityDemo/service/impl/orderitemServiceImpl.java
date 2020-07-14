package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.Orderitem;
import com.example.SecurityDemo.mapper.orderitemMapper;
import com.example.SecurityDemo.service.orderitemService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单详情表：订单的详情主要就是购买商品的信息，通过订单的id来实现关联 服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
@Service
public class orderitemServiceImpl extends ServiceImpl<orderitemMapper, Orderitem> implements orderitemService {

    /**
     * @return 查询所有订单详情数据
     * @description
     * @params
     * @author zfx
     * @date 2020/6/30 15:05
     */
    @Override
    public List<Orderitem> findAll() {
        return baseMapper.findAll();
    }

    /**
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<Orderitem> GetOrderitemList(IPage<Orderitem> page, Wrapper<Orderitem> queryWrapper) {
        IPage<Orderitem> mapIPage = baseMapper.GetOrderitemList(page, queryWrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<Orderitem> records = mapIPage.getRecords();
        records.forEach(System.out::println);
        return mapIPage;    }

    /**
     * 添加订单
     *
     * @param orderitem
     * @return
     */
    @Override
    public int addrderitem(Orderitem orderitem) {
        orderitem.setCreateTime(new Date());
        orderitem.setCreator("admin");

        return baseMapper.insert(orderitem);
    }

    /**
     * 修改订单
     *
     * @param id
     * @return
     */
    @Override
    public int delrderitem(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改订单
     *
     * @param orderitem
     * @return
     */
    @Override
    public int updOrderitem(Orderitem orderitem) {
        orderitem.setCreateTime(new Date());
        orderitem.setMender("admin");
        return baseMapper.updateById(orderitem);
    }

    /**
     * 获取订单信息详情
     *
     * @param id
     * @return
     */
    @Override
    public Orderitem detali(int id) {
        QueryWrapper que=new QueryWrapper();
        que.eq("id",id);
        return baseMapper.selectOne(que);
    }
}
