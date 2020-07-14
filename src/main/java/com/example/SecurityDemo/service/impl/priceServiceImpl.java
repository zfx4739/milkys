package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.Price;
import com.example.SecurityDemo.mapper.priceMapper;
import com.example.SecurityDemo.service.priceService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 价位表 服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
@Service
public class priceServiceImpl extends ServiceImpl<priceMapper, Price> implements priceService {

    /**
     * 获取价位信息
     *
     * @return
     */
    @Override
    public List<Price> findAll() {
        return baseMapper.findAll();
    }

    /**
     * 分页查询产品数据
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<Price> GetPriceList(IPage<Price> page, Wrapper<Price> queryWrapper) {
        IPage<Price> mapIPage = baseMapper.GetPriceList(page, queryWrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<Price> records = mapIPage.getRecords();
        records.forEach(System.out::println);;
        return mapIPage;
    }

    /**
     * 添加价位信息
     *
     * @param price
     * @return
     */
    @Override
    public int addPrice(Price price) {
        price.setCreateTime(new Date());
        price.setCreator("admin");
        return baseMapper.insert(price);
    }

    /**
     * 删除价位信息
     *
     * @param id
     * @return
     */
    @Override
    public int delPrice(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改价位信息
     *
     * @param price
     * @return
     */
    @Override
    public int updPrice(Price price) {
        price.setCreateTime(new Date());
        price.setMender("admin");
        return baseMapper.updateById(price);
    }

    /**
     * 获取价位详情
     *
     * @param id
     * @return
     */
    @Override
    public Price detali(int id) {
        QueryWrapper que=new QueryWrapper();
        que.eq("id",id);

        return baseMapper.selectOne(que);
    }
}
