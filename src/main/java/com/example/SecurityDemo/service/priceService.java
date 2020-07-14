package com.example.SecurityDemo.service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecurityDemo.domain.Price;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 价位表 服务类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface priceService extends IService<Price> {
    /**
     *获取价位信息
     * @return
     */
    List<Price> findAll();
    /**
     * 分页查询产品数据
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Price> GetPriceList(@Param("page") IPage<Price> page, @Param(Constants.WRAPPER) Wrapper<Price> queryWrapper);

    /**
     * 添加价位信息
     * @param price
     * @return
     */
    int addPrice(Price price);

    /**
     * 删除价位信息
     * @param id
     * @return
     */
    int delPrice(int id);

    /**
     * 修改价位信息
     * @param price
     * @return
     */
    int updPrice(Price price);

    /**
     * 获取价位详情
     * @param id
     * @return
     */
    Price detali(int id);

}
