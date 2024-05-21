package dj.com.mybatisplus.generate.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * demo-0-表
 * @TableName dj
 */
@TableName(value ="dj")
@Data
public class Dj implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除;0为未删除，1为已删除
     */
    private String delFlag;

    /**
     * 租户号
     */
    private String tenantId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 收入
     */
    private Long money;

    /**
     * 姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private Date dateOfBirth;

    /**
     * 附加参数JSON
     */
    private Object append;

    /**
     * 附件参数Object
     */
    private String attach;

    /**
     * 银行卡号（密文）
     */
    private String cardNoEnc;

    /**
     * 银行卡号，掩码
     */
    private String cardNoMask;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}