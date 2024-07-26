package dj.com.java21virtual.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jiandong 2024-07-26 create
 */
public class DemoController {

    @RequestMapping("/get")
    public Object get() throws Exception {
        Thread.sleep(50);
        return "ok";
    }

}
