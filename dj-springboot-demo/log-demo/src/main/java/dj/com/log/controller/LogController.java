package dj.com.log.controller;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2022-05-31 15:35
 **/
@RestController
//@Slf4j
public class LogController {

    /**
     * sl4j 另起一个文件文件
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private Logger specialLogger = LoggerFactory.getLogger("specialLogger");

    @GetMapping("/log")
    public String hello() {
        String s = "log print test";
        log.debug(s);
        log.info(s);
        log.error(s);
        log.warn(s);
        specialLogger.info("sl4j 另起一个文件文件");

        /**
         *  打印了 logback 自身的内部状态。logback 的内部状态对查找 logback 相关的问题非常的有用。
         */
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        if(log.isDebugEnabled()) {
            log.warn(debugM());
        }

        return s;
    }
    private String debugM(){
        throw new RuntimeException();
    }
}
