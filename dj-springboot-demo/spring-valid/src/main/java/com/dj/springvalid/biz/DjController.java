package com.dj.springvalid.biz;

import com.dj.springvalid.biz.entity.DjVo;
import com.dj.springvalid.biz.group.GroupInsert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiandong 2024-05-15 create
 */
@RestController
@Validated
public class DjController {

    @PostMapping("/noValid")
    @ResponseBody
    public String noValid(@RequestBody @Validated(GroupInsert.class) DjVo djVo) {
        return "success";
    }

    @PostMapping("/validAuto")
    @ResponseBody
    @Validated(GroupInsert.class)
    public String validAuto(@RequestBody DjVo djVo) {
        return "success";
    }

    @PostMapping("/validByBody")
    @ResponseBody
    public String validByBody(@RequestBody DjVo djVo) {
        return "success";
    }

}
