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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单的基本信息
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("订单的基本")
public class   Orderm extends Model<Orderm> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    @TableField("orderNumber")
    private String orderNumber;
    /**
     * 订单信息①来自前台2)菜品名称
     */
    @ApiModelProperty("订单信息①来自前台2)菜品名称")
    @TableField("orderInfo")
    private String orderInfo;
    /**
     * 门店ID
     */
    @ApiModelProperty("门店ID")
    @TableField("storeId")
    private String storeId;
    /**
     * 门店名称
     */
    @ApiModelProperty("门店名称")
    @TableField("storeName")
    private String storeName;
    /**
     * 产品ID
     */
    @ApiModelProperty("产品ID")
    @TableField("productId")
    private String productId;
    /**
     * 评论
     */
    @ApiModelProperty("评论")
    private String comment;
    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    @TableField("productName")
    private String productName;
    /**
     * 订单支付状态（0：已取消1：已支付2：未支付）
     */
    @ApiModelProperty("订单支付状态（0：已取消1：已支付2：未支付）")
    private String status;
    /**
     * 订单备注
     */
    @ApiModelProperty("订单备注")
    private String remark;
    /**
     * 总价
     */
    @ApiModelProperty("总价")
    @TableField("totalPrice")
    private BigDecimal totalPrice;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("createTime")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField("updateTime")
    private Date updateTime;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String creator;
    /**
     * 修改者
     */
    @ApiModelProperty("修改者")
    private String mender;

    /**
     * 买家昵称
     */
    @ApiModelProperty("买家昵称")
    private String buyernick;
    /**
     * 买家留言
     */
    @ApiModelProperty("买家留言")
    private String buyermessage;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer userid;
    /**
     * 订单详情
     */
    @ApiModelProperty("订单详情")
    @TableField(exist = false)
    List<Orderitem> Orderitems;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
