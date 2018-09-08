package cn.com.kugou.server.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/4
 */

@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServerApplication.class).web(true).run(args);
    }

}