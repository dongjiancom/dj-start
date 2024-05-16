package com.dj.springvalid.biz;

import com.dj.springvalid.biz.entity.DjVo;
import com.dj.springvalid.biz.group.GroupDj;
import com.dj.springvalid.biz.group.GroupDj2;
import com.dj.springvalid.biz.group.GroupInsert;
import com.dj.springvalid.biz.group.GroupWeiXin;
import com.dj.springvalid.biz.tool.ValidatorUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author jiandong 2024-05-15 create
 *
 *  如果是dubbo调用，可以使用
 *      import org.apache.dubbo.validation.MethodValidated;
 *      @MethodValidated({GroupRefund.class, GroupHasOri.class})
 *
 */
@RestController
public class DjController {

    @PostMapping("/normalValid")
    @ResponseBody
    public String normalValid(@RequestBody @Validated DjVo djVo) {
        return "success";
    }

    @PostMapping("/validGroupInsert")
    @ResponseBody
    public String validGroupInsert(@RequestBody @Validated(GroupInsert.class) DjVo djVo) {
        return "success";
    }

    @PostMapping("/validGroupWeiXinAndInsert")
    @ResponseBody
    public String validGroupWeiXinAndInsert(@RequestBody @Validated({GroupWeiXin.class, GroupInsert.class}) DjVo djVo) {
        return "success";
    }

    @PostMapping("/validByBodyDetail")
    @ResponseBody
    public String validByBodyDetail(@RequestBody DjVo djVo) {
        if("dj".equals(djVo.getName())){
            ValidatorUtils.validateEntity(djVo, GroupDj.class);
        }
        return "success";
    }

    @PostMapping("/validCustom")
    @ResponseBody
    public String validCustom(@RequestBody @Validated({GroupDj2.class}) DjVo djVo) {
        return "success";
    }

}
