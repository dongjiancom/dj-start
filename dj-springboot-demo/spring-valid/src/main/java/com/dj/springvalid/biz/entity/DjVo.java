package com.dj.springvalid.biz.entity;

import com.dj.springvalid.biz.group.*;
import com.dj.springvalid.biz.tool.CheckAtLeastOneNotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author jiandong 2024-05-15 create
 */
@Data
@CheckAtLeastOneNotNull(groups = {GroupDj2.class}, fieldNames = {"phone", "phone1","phone2"}, message = "phone012 不能同时为空")
public class DjVo {

    @NotNull(message = "name，不能为空", groups = {GroupInsert.class, GroupWeiXin.class})
    private String name;

    @NotNull(message = "age，不能为空", groups = {GroupAlipay.class})
    @Range(
            min = 1,
            max = 18,
            message = "age不能大于18"
    )
    private Integer age;

    @Email(message = "email格式不正确", groups = {GroupDj.class})
    private String email;

    private String phone;
    private String phone1;
    private String phone2;

    @Size(min = 1, max = 2, message = "address长度必须在1-2之间", groups = {GroupWeiXin.class})
    @NotNull(message = "address 无啊", groups = {GroupInsert.class})
    private String address;

    @Valid
    DjSubVo djSubVo;

}
