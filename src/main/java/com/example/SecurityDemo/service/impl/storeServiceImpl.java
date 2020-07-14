package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.Store;
import com.example.SecurityDemo.mapper.storeMapper;
import com.example.SecurityDemo.service.storeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 门店信息表 服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
@Service
public class storeServiceImpl extends ServiceImpl<storeMapper, Store> implements storeService {

    /**
     * @return
     * @description 查询所有门店信息
     * @params
     * @author zfx
     * @date 2020/6/30 15:48
     */
    @Override
    public List<Store> findAll() {
        return baseMapper.findAll();
    }

    /**
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<Store> GetStoreList(IPage<Store> page, Wrapper<Store> queryWrapper) {
        IPage<Store> mapIPage = baseMapper.GetSpecificationList(page, queryWrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<Store> records = mapIPage.getRecords();
        records.forEach(System.out::println);
        return mapIPage;
    }

    /**
     * 添加
     * @param store
     * @return
     */
    @Override
    public int addStore(Store store) {
        store.setCreateTime(new Date());
        store.setCreator("admin");
        return baseMapper.insert(store);
    }

    /**
     * 删除门店信息
     *
     * @param id
     * @return
     */
    @Override
    public int delStore(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改门店信息
     *
     * @param store
     * @return
     */
    @Override
    public int updStore(Store store) {
        store.setUpdateTime(new Date());
        store.setMender("admin");

        return baseMapper.updateById(store);
    }

    /**
     * 获取门店信息详情
     *
     * @param id
     * @return
     */
    @Override
    public Store detali(int id) {
        QueryWrapper<Store> que=new QueryWrapper<Store>();
        que.eq("id",id);
        return baseMapper.selectOne(que);
    }

    /**
     * @param account
     * @return 根据条件查询门店信息
     * @description
     * @params
     * @author zfx
     * @date 2020/6/30 16:01
     */
    @Override
    public Store findStore(String account) {
        return null;
    }
}
