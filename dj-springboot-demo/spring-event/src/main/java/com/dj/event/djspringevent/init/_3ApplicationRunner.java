package com.dj.event.djspringevent.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author jiandong 2024-04-23 create
 * 使用场景：
 * 1. 命令行参数解析：由于ApplicationArguments提供了丰富的参数解析能力，因此更适合处理带有键值对形式的命令行参数，如--server-port=8080，然后根据这些参数执行不同的初始化操作。
 * 1. 启动时初始化：同CommandLineRunner，也可用于执行启动后的一次性操作，例如读取配置、初始化缓存、检查系统资源等，同时可以根据解析的命令行参数决定初始化的具体内容。
 * 相比较于CommandLineRunner，ApplicationRunner提供了更强大的命令行参数解析功能，可以轻松处理各种类型的参数。可以根据命令行参数灵活调整启动时的初始化逻辑。但是其缺点同CommandLineRunner。
 * ApplicationRunner和CommandLineRunner都可以用来在Spring Boot启动时执行特定代码，两者在应用场景上略有差异，具体选择哪种取决于项目的实际需求和命令行参数的复杂程度。
 */
@Component
public class _3ApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("初始化操作" + "3. 实现 ApplicationRunner，读到环境变量" + args);
        List<String> serverPortOptions = args.getOptionValues("server-port");
        if (serverPortOptions != null && !serverPortOptions.isEmpty()) {
            Optional<Integer> port = args.getOptionValues("server-port").stream()
                    .map(Integer::parseInt)
                    .findFirst();
            if (port.isPresent()) {
                // 根据端口号进行特定的初始化操作
            }
        }
    }
}
