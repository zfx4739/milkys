package com.example.SecurityDemo.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.SecurityDemo.domain.Store;
import com.example.SecurityDemo.domain.SysLogininfor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author zfx
 * @since 2020-07-17
 */
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {
    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    IPage<SysLogininfor> selectLogininforList(@Param("page") IPage<SysLogininfor> page, @Param("logininfor")SysLogininfor logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    public int deleteLogininforByIds(Long[] infoIds);

    /**
     * 清空系统登录日志
     *
     * @return 结果
     */
    public int cleanLogininfor();
}
