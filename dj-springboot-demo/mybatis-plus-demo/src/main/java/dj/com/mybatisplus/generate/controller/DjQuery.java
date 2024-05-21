package dj.com.mybatisplus.generate.controller;

import lombok.Data;

import java.util.Date;

/**
 * @author jiandong 2024-05-21 create
 */
@Data
public class DjQuery extends PageReqVO {

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
}
