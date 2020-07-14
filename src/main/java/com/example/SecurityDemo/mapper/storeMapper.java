package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.SecurityDemo.domain.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 门店信息表 Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface storeMapper extends BaseMapper<Store> {
    /**
     * @description  查询所有门店信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 15:48
     *
     */
    List<Store> findAll();

    /**
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Store> GetSpecificationList(@Param("page") IPage<Store> page, @Param(Constants.WRAPPER) Wrapper<Store> queryWrapper);
}
