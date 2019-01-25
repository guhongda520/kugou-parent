package cn.com.kugou.common.email;

import org.springframework.core.io.FileSystemResource;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/12
 */
public interface EmailTemplate {

    MimeMessage MimeMessageInstance();

    void sendSimpleMail(MimeMessage mimeMessage, String fromEmail, String toEmail, String subject, String text)throws Exception;

    void sendAttachmentsMail(MimeMessage mimeMessage, String fromEmail, String toEmail, String subject, String text, List<FileSystemResource> files) throws Exception;

    void sendInlineMail(MimeMessage mimeMessage, String fromEmail, String toEmail, String subject, String text, List<File> files) throws Exception;

    void sendTemplateMail(MimeMessage mimeMessage, String fromEmail, String toEmail, String subject) throws Exception;
}
