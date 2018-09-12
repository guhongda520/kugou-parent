package cn.com.kugou.common.email.impl;

import cn.com.kugou.common.email.EmailTemplate;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.util.CollectionUtils;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/12
 */
@Service
public class EmailTemplateImpl implements EmailTemplate {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    MimeMessage mimeMessage = null;
    @Override
    public MimeMessage MimeMessageInstance(){
        if(mimeMessage == null){
            mimeMessage = mailSender.createMimeMessage();
        }
        return mimeMessage;
    }

    public MimeMessageHelper simpleMail(MimeMessage mimeMessage, String fromEmail, String toEmail, String subject, String text) throws Exception {
        if(StringUtils.isBlank(fromEmail) || StringUtils.isBlank(toEmail) || StringUtils.isBlank(subject) || StringUtils.isBlank(text)){
            return null;
        }
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text);
        return helper;
    }

    @Override
    public void sendSimpleMail(MimeMessage mimeMessage, String fromEmail, String toEmail, String subject, String text) throws Exception {
        simpleMail(mimeMessage,fromEmail, toEmail, subject, text);
        sendmessage(mimeMessage);
    }

    @Override
    public void sendAttachmentsMail(MimeMessage mimeMessage, String fromEmail, String toEmail, String subject, String text, List<FileSystemResource> files) throws Exception {

        MimeMessageHelper helper =  simpleMail(mimeMessage,fromEmail, toEmail, subject, text);
        if(CollectionUtils.isEmpty(files) || helper == null){
            return;
        }
        for (int i = 0; i < files.size(); i++) {
            helper.addAttachment("附件-" + i, files.get(i) );
        }
        sendmessage(mimeMessage);
    }
    @Override
    public void sendInlineMail(MimeMessage mimeMessage, String fromEmail, String toEmail, String subject, String text, List<File> files) throws Exception {
        MimeMessageHelper helper =  simpleMail(mimeMessage,fromEmail, toEmail, subject, text);
        if(helper == null || CollectionUtils.isEmpty(files)){
            return;
        }
        for (int i = 0; i < files.size(); i++) {
            helper.addAttachment("文件-" + i, files.get(i) );
        }
        sendmessage(mimeMessage);
    }

    @Override
    public void sendTemplateMail(MimeMessage mimeMessage, String fromEmail, String toEmail, String subject) throws Exception {
        MimeMessageHelper helper =  simpleMail(mimeMessage,fromEmail, toEmail, subject, null);
        if(helper == null){
            return;
        }
        Map<String, Object> model = new HashedMap();
        model.put("username", "didi");
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "template.vm", "UTF-8", model);
        helper.setText(text, true);
        sendmessage(mimeMessage);
    }

    private void sendmessage(MimeMessage mimeMessage){
        mailSender.send(mimeMessage);
    }

}
