package com.example.SecurityDemo.domain;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zfx
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户角色关联信息实体类")
public class SysUserRoles extends Model<SysUserRoles> {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("用户ID")
    private Integer sys_user_id;
    @ApiModelProperty("角色ID")
    private Integer roles_id;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
