package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.Ordershipping;
import com.example.SecurityDemo.mapper.ordershippingMapper;
import com.example.SecurityDemo.service.ordershippingService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单配送表 服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
@Service
public class ordershippingServiceImpl extends ServiceImpl<ordershippingMapper, Ordershipping> implements ordershippingService {

    /**
     * @return 查询所有订单详情数据
     * @description
     * @params
     * @author zfx
     * @date 2020/6/30 15:05
     */
    @Override
    public List<Ordershipping> findAll() {
        return baseMapper.findAll();
    }

    /**
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<Ordershipping> GetOrdershippingList(IPage<Ordershipping> page, Wrapper<Ordershipping> queryWrapper) {
        IPage<Ordershipping> mapIPage = baseMapper.GetOrdershippingList(page, queryWrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<Ordershipping> records = mapIPage.getRecords();
        records.forEach(System.out::println);
        return mapIPage;
    }

    /**
     * 添加订单
     *
     * @param ordershipping
     * @return
     */
    @Override
    public int addOrdershipping(Ordershipping ordershipping) {
        ordershipping.setCreateTime(new Date());
        ordershipping.setCreator("admin");
        return baseMapper.insert(ordershipping);
    }

    /**
     * 修改订单
     *
     * @param id
     * @return
     */
    @Override
    public int delOrdershipping(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改订单
     *
     * @param ordershipping
     * @return
     */
    @Override
    public int updOrdershipping(Ordershipping ordershipping) {
        ordershipping.setCreateTime(new Date());
        ordershipping.setCreator("admin");
        return baseMapper.updateById(ordershipping);
    }

    /**
     * 获取订单信息详情
     *
     * @param id
     * @return
     */
    @Override
    public Ordershipping detali(int id) {
        QueryWrapper que=new QueryWrapper();
        que.eq("id",id);
        return baseMapper.selectOne(que);
    }
}
