package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.Specification;
import com.example.SecurityDemo.mapper.specificationMapper;
import com.example.SecurityDemo.service.specificationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 规格表 服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
@Service
public class specificationServiceImpl extends ServiceImpl<specificationMapper, Specification> implements specificationService {

    @Override
    public List<Specification> findAll() {
        return baseMapper.findAll();
    }

    /**
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<Specification> GetSpecificationList(IPage<Specification> page, Wrapper<Specification> queryWrapper) {
        IPage<Specification> mapIPage = baseMapper.GetSpecificationList(page, queryWrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<Specification> records = mapIPage.getRecords();
        records.forEach(System.out::println);
        return mapIPage;
    }

    @Override
    public int addSpecification(Specification specification) {
        specification.setCreateTime(new Date());
        specification.setCreator("admin");
        return baseMapper.insert(specification);
    }

    /**
     * 删除门店信息
     *
     * @param id
     * @return
     */
    @Override
    public int delspecificationService(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改门店信息
     *
     * @param specification
     * @return
     */
    @Override
    public int updSpecification(Specification specification) {
        specification.setUpdateTime(new Date());
        specification.setMender("admin");
        return baseMapper.updateById(specification);
    }

    /**
     * 删除门店信息
     *
     * @param id
     * @return
     */
    @Override
    public Specification detali(int id) {
        QueryWrapper<Specification> que=new  QueryWrapper<Specification>();
        que.eq("id",id);
        return baseMapper.selectOne(que);
    }

    /**
     * @param account
     * @return 根据条件查询规格信息
     * @description
     * @params
     * @author zfx
     * @date 2020/6/30 16:01
     */
    @Override
    public Specification findSpecification(String account) {
        return null;
    }
}
