package dj.com.mybatisplus.generate.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 主键ID
     *
     * @mbg.generated Fri Jan 22 22:51:56 CST 2021
     */
    private Long id;

    /**
     * 姓名
     *
     * @mbg.generated Fri Jan 22 22:51:56 CST 2021
     */
    private String name;

    /**
     * 年龄
     *
     * @mbg.generated Fri Jan 22 22:51:56 CST 2021
     */
    private Integer age;

    /**
     * 邮箱
     *
     * @mbg.generated Fri Jan 22 22:51:56 CST 2021
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user
     *
     * @mbg.generated Fri Jan 22 22:51:56 CST 2021
     */
    private static final long serialVersionUID = 1L;
}