package com.dj.springvalid.biz.entity;

import com.dj.springvalid.biz.group.GroupAlipay;
import com.dj.springvalid.biz.group.GroupDj;
import com.dj.springvalid.biz.group.GroupInsert;
import com.dj.springvalid.biz.group.GroupWeiXin;
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

    @Size(min = 1, max = 2, message = "address长度必须在1-2之间", groups = {GroupWeiXin.class})
    @NotNull(message = "address 无啊", groups = {GroupInsert.class})
    private String address;

    @Valid
    DjSubVo djSubVo;

}
