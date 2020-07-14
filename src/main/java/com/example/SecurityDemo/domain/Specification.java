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
 * 规格表
 * </p>
 *
 * @author zfx
 * @since 2020-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("规格实体类")
public class Specification extends Model<Specification> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 大小
     */
    @ApiModelProperty("大小")
    private String sizes;
    /**
     * 温度
     */
    @ApiModelProperty("温度")
    private String temperature;
    /**
     * 容量
     */
    @ApiModelProperty("容量")
    private String capacity;
    /**
     * 甜度
     */
    @ApiModelProperty("甜度")
    private String sweetness;

    /**
     * 商品id(自定义商品规格)
     */
    @ApiModelProperty("商品id(自定义商品规格)")
    @TableField("productId")
    private Integer productId;
    /**
     * 规格类型（1：加珍珠2：加糖3：温度4：容量5大小）
     */
    @ApiModelProperty("规格类型（1：加珍珠2：加糖3：温度4：容量5大小）")
    private String type;
    /**
     * 序号
     */
    @ApiModelProperty("序号")
    private Integer sequence;
    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private Integer pice;

    /**
     *  定制
     */
    @ApiModelProperty("定制")
    private String custom;
    /**
     *  是否多选
     */
    @ApiModelProperty("是否多选")
    private String ismultiple;

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
