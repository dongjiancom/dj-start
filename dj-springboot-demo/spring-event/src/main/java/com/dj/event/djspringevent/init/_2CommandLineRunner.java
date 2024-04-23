package com.dj.event.djspringevent.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author jiandong 2024-04-23 create
 *
 * 使用场景：
 * 1. 命令行参数处理：CommandLineRunner接口常用于处理从命令行传入的参数，例如运行不同模式下的任务（如dev模式、prod模式）、读取配置项等。
 * 2. 应用启动后的一次性操作：在应用启动后，可能需要进行一些一次性执行的任务，如数据库表结构检查、初始化缓存、发送通知邮件等。
 * 使用CommandLineRunner接口这种方式是，我们只需要实现接口，无需关注容器的生命周期事件或手动注册监听器。但是如果是多个CommandLineRunner之间的执行顺序无法保证，可能会带来不确定性（如果是不关心顺序，那就不是缺点了）。另外，我们不应该在`` run方法中实现过多或较为复杂的任务。
 */
@Component
public class _2CommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("初始化操作" + "2. 实现 CommandLineRunner，读到环境变量" + args);
    }
}
