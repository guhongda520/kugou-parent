package cn.com.kugou.provider.contoller;

import cn.com.kugou.common.email.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/12
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailTemplate emailTemplate;


    @GetMapping("/email")
    public void emailTest() throws Exception {
        MimeMessage mimeMessage = emailTemplate.MimeMessageInstance();
        String fromEmail = "747791618@qq.com";
        String toEmail = "15631973182@163.com";
        String subject = "主题：简单邮件";
        String text = "测试邮件内容";
        emailTemplate.sendSimpleMail(mimeMessage,fromEmail, toEmail, subject, text);
    }
}
