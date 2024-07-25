package dj.com.log.controller;

import dj.com.pojo.DjBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiandong 2024-05-16 create
 */
@RestController
@Slf4j
public class DemoController {
    @PostMapping("/JsonUtils")
    @ResponseBody
    public String jsonUtils(@RequestBody DjBean djBean) {
//        String jsonString = JsonUtils.toJsonString(djBean);
//        log.info("djBean-json-str:{}", jsonString);
//        log.info("djBean-object:{}", JsonUtils.parseObject(jsonString, DjBean.class));
        return "success";
    }
}
