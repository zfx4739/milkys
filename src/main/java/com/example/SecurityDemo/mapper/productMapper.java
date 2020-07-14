package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.SecurityDemo.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品信息表 Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface productMapper extends BaseMapper<Product> {
    /**
     * 查询所有产品
     * @return
     */
  List<Product> findAll ();

    /**
     * 分页查询产品数据
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Product> GetProductList(@Param("page") IPage<Product> page, @Param(Constants.WRAPPER) Wrapper<Product> queryWrapper);

    /**
     * 模糊查询商品，排除分类数据
     * @param names
     * @return
     */
    List<Product> queryProduct(@Param("names")String names);
}
