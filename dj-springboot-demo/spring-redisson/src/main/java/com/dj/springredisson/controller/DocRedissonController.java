package com.dj.springredisson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiandong 2024-06-05 create
 * 流查询示例
 * https://baomidou.com/guides/stream-query/
 */
@RestController
public class DocRedissonController {




    @GetMapping("/user/streamQuery")
    public String streamQuery() {
        return "over";
    }
}
