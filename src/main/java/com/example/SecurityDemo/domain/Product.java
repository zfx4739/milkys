package com.example.SecurityDemo.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.SecurityDemo.dto.SpecificationDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 产品信息表
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//Swagger注解
@ApiModel("产品信息实体类")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Integer id;
    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String type;
    /**
     * 规格Id
     */
    @ApiModelProperty("规格Id")
   private String specificationid;

    /**
     * 销量
     */
    @ApiModelProperty("销量")
    private String sales;
    /**
     * 商品详情
     */
    @ApiModelProperty("商品详情")
    private String productdetails;
    /**
     * 评论
     */
    @ApiModelProperty("评论")
    private String comment;
    /**
     * 图片地址
     */
    @ApiModelProperty("图片地址")
    private String pictureaddress;
    /**
     * 库存
     */
    @ApiModelProperty("库存")
    private Integer stock;
    /**
     * 商品价格
     */
    @ApiModelProperty("商品价格")
    private Integer price;
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

    /**
     *父级Id
     */
    @ApiModelProperty("父级Id")
    private String parents;

    @ApiModelProperty("产品集合")
    @TableField(exist = false)
    private List<Product> progs;
//规格集合
    @ApiModelProperty("规格集合")
    @TableField(exist = false)
    private List<SpecificationDto> Specifications;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
