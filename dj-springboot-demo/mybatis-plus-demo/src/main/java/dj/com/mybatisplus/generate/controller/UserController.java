package dj.com.mybatisplus.generate.controller;

import dj.com.mybatisplus.generate.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jiandong 2024-06-05 create
 * 流查询示例
 * https://baomidou.com/guides/stream-query/
 */
@RestController
public class UserController {

    @Resource
    IUserService iUserService;

    @GetMapping("/user/streamQuery")
    public String streamQuery() {
        iUserService.streamQuery();
        return "over";
    }
}
