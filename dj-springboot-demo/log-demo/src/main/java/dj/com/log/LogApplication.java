package dj.com.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2022-05-31 15:42
 **/
@SpringBootApplication(scanBasePackages = {"dj.com.log"})
public class LogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }
}
