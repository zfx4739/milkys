package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.Store;
import com.example.SecurityDemo.domain.SysLogininfor;
import com.example.SecurityDemo.mapper.SysLogininforMapper;
import com.example.SecurityDemo.service.SysLogininforService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-07-17
 */
@Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements SysLogininforService {

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor)
    {
        baseMapper.insertLogininfor(logininfor);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param page
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public IPage<SysLogininfor> selectLogininforList(IPage<SysLogininfor> page, SysLogininfor logininfor) {

        IPage<SysLogininfor> mapIPage = baseMapper.selectLogininforList(page, logininfor);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<SysLogininfor> records = mapIPage.getRecords();
        records.forEach(System.out::println);
        return mapIPage;
    }


    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return
     */
    @Override
    public int deleteLogininforByIds(Long[] infoIds)
    {
        return baseMapper.deleteLogininforByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public int cleanLogininfor()
    {
        baseMapper.cleanLogininfor();

        return 0;
    }
}
