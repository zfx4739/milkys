package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.SecurityDemo.domain.Orderm;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 * 订单的基本信息 Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
public interface ordermMapper extends BaseMapper<Orderm> {
    /**
     * @description
     *@params
     * @return  查询所有订单数据
     * @author  zfx
     * @date  2020/6/30 15:05
     *
     */
    List<Orderm> findAll();

    /**
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Orderm> GetOrdermList(@Param("page") IPage<Orderm> page, @Param(Constants.WRAPPER) Wrapper<Orderm> queryWrapper);
}
