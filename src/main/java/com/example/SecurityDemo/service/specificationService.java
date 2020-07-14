package com.example.SecurityDemo.service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecurityDemo.domain.Specification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 规格表 服务类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface specificationService extends IService<Specification> {
    List<Specification> findAll();

    /**
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Specification> GetSpecificationList(@Param("page") IPage<Specification> page, @Param(Constants.WRAPPER) Wrapper<Specification> queryWrapper);

    /*
     *   添加规格信息账号
     * */
    int  addSpecification(Specification specification);

    /**
     * 删除门店信息
     * @param id
     * @return
     */
    int  delspecificationService(int id);

    /**
     * 修改门店信息
     * @param specification
     * @return
     */
    int updSpecification(Specification specification);

    /**
     * 删除门店信息
     * @param id
     * @return
     */
    Specification detali(int id);


    /**
     * @description
     *@params
     * @return  根据条件查询规格信息
     * @author  zfx
     * @date  2020/6/30 16:01
     *
     */
    Specification findSpecification(String account);

}
