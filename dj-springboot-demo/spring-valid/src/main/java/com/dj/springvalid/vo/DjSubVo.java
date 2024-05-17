package com.dj.springvalid.vo;

import com.dj.springvalid.group.GroupAlipay;
import com.dj.springvalid.group.GroupInsert;
import com.dj.springvalid.group.GroupWeiXin;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author jiandong 2024-05-15 create
 */
@Data
public class DjSubVo {
        @NotNull(message = "subName，不能为空", groups = {GroupInsert.class, GroupWeiXin.class})
        private String name;

        @NotNull(message = "age，不能为空", groups = {GroupAlipay.class})
        @Range(
                min = 1,
                max = 18,
                message = "age"
        )
        private String subName;

        private Integer subAge;

        private String subEmail;

        private String subPhone;

        private String subAddress;
}
