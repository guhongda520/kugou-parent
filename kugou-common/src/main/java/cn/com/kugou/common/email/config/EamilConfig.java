package cn.com.kugou.common.email.config;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/12
 */
@Configuration
public class EamilConfig {

    @Bean(name = "velocityEngine")
    public VelocityEngine initVelocityEngine(){
        return new VelocityEngine();
    }

    @Bean(name = "javaMailSender")
    public JavaMailSender initJavaMailSender(){
        return new JavaMailSenderImpl();
    }
}
