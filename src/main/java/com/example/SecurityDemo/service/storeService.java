package com.example.SecurityDemo.service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecurityDemo.domain.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 门店信息表 服务类
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
public interface storeService extends IService<Store> {
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
    IPage<Store> GetStoreList(@Param("page") IPage<Store> page, @Param(Constants.WRAPPER) Wrapper<Store> queryWrapper);

    /*
     *   添加门店信息账号
     * */
    int  addStore(Store store);

    /**
     * 删除门店信息
     * @param id
     * @return
     */
    int  delStore(int id);

    /**
     * 修改门店信息
     * @param store
     * @return
     */
    int updStore(Store store);

    /**
     * 删除门店信息
     * @param id
     * @return
     */
    Store detali(int id);


    /**
     * @description
     *@params
     * @return  根据条件查询门店信息
     * @author  zfx
     * @date  2020/6/30 16:01
     *
     */
    Store findStore(String account);


}
