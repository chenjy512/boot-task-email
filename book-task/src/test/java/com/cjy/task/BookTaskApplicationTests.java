package com.cjy.task;

import com.cjy.task.domain.EmailVo;
import com.cjy.task.service.SendEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTaskApplicationTests {


    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Autowired
    SendEmailService sendEmailService;

    @Test
    public void contextLoads() {

        SimpleMailMessage ms = new SimpleMailMessage();
        ms.setSubject("发点什么呢_加点样式");
        ms.setText("今天的月亮圆不圆11");

        ms.setTo("cjy_java@126.com");
        ms.setFrom("770305120@qq.com");
       // ms.set
       // ms.setTo();
        javaMailSender.send(ms);
        System.out.println("测试邮件发送完成");
    }

    @Test
    public void test1() throws MessagingException {
        MimeMessage ms = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(ms, true);//设置html样式
        helper.setSubject("附件发送邮件");
        helper.setText("今天的月亮圆-<b>样式加粗</b>",true); //设置样式
        helper.setTo("cjy_java@126.com");
        helper.setFrom("770305120@qq.com");

        //发送附件
        helper.addAttachment("fstp.jpg",new File("/Users/chenjunying/Downloads/1.jpg"));

        javaMailSender.send(ms);

    }

    //-----------------------------------测试email模型

    @Test
    public void test2(){
        EmailVo vo = new EmailVo();
        vo.setFrom("770305120@qq.com");
        vo.setTo(new String[]{"cjy_java@126.com"});
       // vo.setCc(new String[]{"cjy_it@126.com"});
        vo.setSubject("测试模型vo发送普通邮件");
        vo.setText("发送的邮件内容。。。。。。。");

        try {
            sendEmailService.sendText(vo);
            System.out.println("普通邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){

        EmailVo vo = new EmailVo();
        vo.setFrom("770305120@qq.com");
        vo.setTo(new String[]{"cjy_java@126.com"});
        // vo.setCc(new String[]{"cjy_it@126.com"});
        vo.setSubject("测试模型vo发送样式邮件邮件");
        vo.setText("发送的邮件内容。。。。。。。<b style='color:red'>加粗字体显示</b>");

        try {
            sendEmailService.sendHtmlMail(vo);
            System.out.println("普通邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){

        EmailVo vo = new EmailVo();
        vo.setFrom("770305120@qq.com");
        vo.setTo(new String[]{"cjy_java@126.com"});
        // vo.setCc(new String[]{"cjy_it@126.com"});
        vo.setSubject("测试模型vo发送附件邮件");
        vo.setText("发送的邮件内容。。。。。。。<b style='color:red'>加粗字体显示</b>");
        Map map = new HashMap();
        vo.setFilenames(map);
        map.put("1.jpg","/Users/chenjunying/Downloads/1.jpg");
        try {
            sendEmailService.sendAttachmentsMail(vo);
            System.out.println("普通邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //发送邮件异常状态总结： https://blog.csdn.net/wangyanming123/article/details/52734334

}
