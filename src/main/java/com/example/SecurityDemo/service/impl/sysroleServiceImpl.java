package com.example.SecurityDemo.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecurityDemo.domain.SysRole;
import com.example.SecurityDemo.mapper.sysroleMapper;
import com.example.SecurityDemo.service.sysroleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zfx
 * @since 2020-07-07
 */
@Service
public class sysroleServiceImpl extends ServiceImpl<sysroleMapper, SysRole> implements sysroleService {

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<SysRole> findAll() {
        return baseMapper.findAll();
    }

    /**
     * 通过userId查询角色
     *
     * @param userid
     * @return
     */
    @Override
    public List<SysRole> getSysRolesByUserId(Integer userid) {
        return baseMapper.getSysRolesByUserId(userid);
    }

    @Override
    public Set<SysRole> selectRolePermissionByUserId(Integer userid) {
        return baseMapper.selectRolePermissionByUserId(userid);
    }
}
