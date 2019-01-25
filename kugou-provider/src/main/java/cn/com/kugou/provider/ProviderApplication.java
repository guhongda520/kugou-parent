package cn.com.kugou.provider;

import cn.com.kugou.common.stream.sender.SinkSender;
import com.google.common.collect.Maps;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/4
 */

@EnableDiscoveryClient
@EnableBinding(value = {SinkSender.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("cn.com.kugou.domain.mapper")
@ComponentScan(basePackages = {"cn.com.kugou.provider**","cn.com.kugou.domainimpl**","cn.com.kugou.common**",
        "cn.com.kugou.comsumer**", "cn.com.kugou.job**"})
@ImportResource(locations={"classpath:spring-gts.xml"})
public class ProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ProviderApplication.class).web(true).run(args);
    }
}