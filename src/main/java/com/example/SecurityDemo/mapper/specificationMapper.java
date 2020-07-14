package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.SecurityDemo.domain.Specification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 规格表 Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface specificationMapper extends BaseMapper<Specification> {
    /**
     * @description  查询所有用户信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 15:48
     *
     */
    List<Specification> findAll();

    /**
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Specification> GetSpecificationList(@Param("page") IPage<Specification> page, @Param(Constants.WRAPPER) Wrapper<Specification> queryWrapper);
}
