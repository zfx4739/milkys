package com.example.SecurityDemo.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 会员信息表
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("会员信息实体类")
public class Member extends Model<Member> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 会员积分
     */
    @ApiModelProperty("会员积分")
    private String memberpoints;
    /**
     * 会员等级
     */
    @ApiModelProperty("会员等级")
    private String memberclass;
    /**
     * 会员优惠
     */
    @ApiModelProperty("会员优惠")
    private String memberdiscounts;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("createTime")
    private Date createTime;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String creator;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField("updateTime")
    private Date updateTime;
    /**
     * 修改者
     */
    @ApiModelProperty("修改者")
    private String mender;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
