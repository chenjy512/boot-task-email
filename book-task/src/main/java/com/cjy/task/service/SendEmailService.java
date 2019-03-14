package com.cjy.task.service;


import com.cjy.task.domain.EmailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class SendEmailService {

    @Autowired
    JavaMailSenderImpl mailSender;


    /**
     * 发送普通邮件
     *
     * @param email
     */
    public void sendText(EmailVo email) throws Exception {

        if (email == null)
            throw new IllegalArgumentException("邮件信息有误");

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getFrom());
            message.setTo(email.getTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            if (email.getCc() != null) {
                message.setCc(email.getCc());
            }
            mailSender.send(message);
        } catch (Exception e) {
            throw new Exception("发送邮件失败");

        }
    }

    /**
     * @Description: 发送html邮件
     * @author lc
     */
    public void sendHtmlMail(EmailVo email) throws Exception {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email.getFrom());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            // 发送htmltext值需要给个true，不然不生效
            helper.setText(email.getText(), true);
            if (email.getCc() != null) {
                helper.setCc(email.getCc());
            }
            mailSender.send(message);
        } catch (Exception e) {
            throw new Exception("发送邮件失败");
        }
    }

    /**
     * @Description: 发送带附件的邮件
     * @author lc
     */
    public void sendAttachmentsMail(EmailVo email) throws Exception {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email.getFrom());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText());
            if(email.getCc() != null){
                helper.setCc(email.getCc());
            }
            Map<String, String> filenames = email.getFilenames();
            Set<Map.Entry<String, String>> entries = filenames.entrySet();
            Iterator<Map.Entry<String, String>> iterator =  entries.iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                helper.addAttachment(next.getKey(),new File(next.getValue()));
            }
            mailSender.send(message);
        } catch (Exception e){
            throw new Exception("发送邮件失败");
        }
    }

}
