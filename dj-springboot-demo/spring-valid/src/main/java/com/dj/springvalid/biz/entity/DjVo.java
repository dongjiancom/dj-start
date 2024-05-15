package com.dj.springvalid.biz.entity;

import com.dj.springvalid.biz.group.GroupAlipay;
import com.dj.springvalid.biz.group.GroupInsert;
import com.dj.springvalid.biz.group.GroupWeiXin;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
            message = "age"
    )
    private Integer age;

    private String email;

    private String phone;

    private String address;

    @Valid
    DjSubVo djSubVo;

}
