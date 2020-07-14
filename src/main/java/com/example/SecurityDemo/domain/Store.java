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
 * 门店信息表
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("门店信息实体类")
public class Store extends Model<Store> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 微信公众号
     */
    @ApiModelProperty("微信公众号")
    private String wechatoa;
    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;
    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String phone;
    /**
     * 法人代表
     */
    @ApiModelProperty("法人代表")
    private String lawperson;
    /**
     * 统一信用号
     */
    @ApiModelProperty("统一信用号")
    @TableField("Unifcrenum")
    private String Unifcrenum;
    /**
     * 法人身份证号
     */
    @ApiModelProperty("法人身份证号")
    private String juripersoncard;
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
