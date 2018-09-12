package cn.com.kugou.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/4
 */

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("cn.com.kugou.domain.mapper")
@ComponentScan(basePackages = {"cn.com.kugou.provider**","cn.com.kugou.domainimpl**","cn.com.kugou.common**"})
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ProviderApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}