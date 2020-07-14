package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.Product;
import com.example.SecurityDemo.mapper.productMapper;
import com.example.SecurityDemo.service.productService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 产品信息表 服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
@Service
public class productServiceImpl extends ServiceImpl<productMapper, Product> implements productService {

    /**
     * 查询所有产品
     *
     * @return
     */
    @Override
    public List<Product> findAll() {
        return baseMapper.findAll();
    }

    /**
     * 模糊查询商品，排除分类数据
     *
     * @param names
     * @return
     */
    @Override
    public List<Product> queryProduct(String names) {
        return baseMapper.queryProduct(names);
    }

    /**
     * 分页查询产品数据
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<Product> GetProductList(IPage<Product> page, Wrapper<Product> queryWrapper) {
        IPage<Product> mapIPage = baseMapper.GetProductList(page, queryWrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<Product> records = mapIPage.getRecords();
        records.forEach(System.out::println);;
        return mapIPage;
    }

    /**
     * 添加产品信息
     *
     * @param product
     * @return
     */
    @Override
    public int addProduct(Product product) {
        product.setCreateTime(new Date());
        product.setCreator("admin");
        return baseMapper.insert(product);
    }

    /**
     * 删除产品信息
     *
     * @param id
     * @return
     */
    @Override
    public int delProduct(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改产品信息
     *
     * @param product
     * @return
     */
    @Override
    public int updProdect(Product product) {
        product.setUpdateTime(new Date());
        product.setMender("admin");
        return baseMapper.updateById(product);
    }

    /**
     * 获取产品详情
     *
     * @param id
     * @return
     */
    @Override
    public Product detali(int id) {
        QueryWrapper que=new QueryWrapper();
        que.eq("id",id);
        return baseMapper.selectOne(que);
    }
}
