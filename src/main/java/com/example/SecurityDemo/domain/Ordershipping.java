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
 * 订单配送表
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("订单配送实体类")
public class Ordershipping extends Model<Ordershipping> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Integer orderid;
    /**
     * 收货人姓名
     */
    @ApiModelProperty("收货人姓名")
    private String receivername;
    /**
     * 收货人电话
     */
    @ApiModelProperty("收货人电话")
    private String receiverphone;
    /**
     * 收货人地址
     */
    @ApiModelProperty("收货人地址")
    private String address;
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
