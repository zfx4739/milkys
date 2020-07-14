package com.example.SecurityDemo.service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecurityDemo.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品信息表 服务类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface productService extends IService<Product> {
    /**
     * 查询所有产品
     * @return
     */
    List<Product> findAll ();

    /**
     * 模糊查询商品，排除分类数据
     * @param names
     * @return
     */
    List<Product> queryProduct(@Param("names")String names);

    /**
     * 分页查询产品数据
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Product> GetProductList(@Param("page") IPage<Product> page, @Param(Constants.WRAPPER) Wrapper<Product> queryWrapper);

    /**
     * 添加产品信息
     * @param product
     * @return
     */
    int addProduct(Product product);

    /**
     *删除产品信息
     * @param id
     * @return
     */
    int delProduct(int id);

    /**
     *修改产品信息
     * @param product
     * @return
     */
    int updProdect(Product product);

    /**
     * 获取产品详情
     * @param id
     * @return
     */
    Product detali(int id);
}
