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
 * 订单详情表：订单的详情主要就是购买商品的信息，通过订单的id来实现关联
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("订单详情表：订单的详情主要就是购买商品的信息，通过订单的id来实现关联")
public class Orderitem extends Model<Orderitem> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Integer productid;
    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private String orderid;
    /**
     * 商品购买数量
     */
    @ApiModelProperty("主键")
    private Integer num;
    /**
     * 商品标题
     */
    @ApiModelProperty("商品标题")
    private String title;
    /**
     * 商品单价
     */
    @ApiModelProperty("商品单价")
    private Integer price;
    /**
     * 商品总金额
     */
    @ApiModelProperty("商品总金额")
    private Integer totalfee;
    /**
     * 商品图片地址
     */
    @ApiModelProperty("商品图片地址")
    private String picpath;
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
