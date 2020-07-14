package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.SecurityDemo.domain.Price;
import com.example.SecurityDemo.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 价位表 Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface priceMapper extends BaseMapper<Price> {
    /**
     *获取价位信息
     * @return
     */
    List<Price>  findAll();
    /**
     * 分页查询产品数据
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Price> GetPriceList(@Param("page") IPage<Price> page, @Param(Constants.WRAPPER) Wrapper<Price> queryWrapper);
}
