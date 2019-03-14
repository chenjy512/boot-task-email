package com.cjy.task.domain;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Map;


public class EmailVo implements Serializable {

    private String from; //发送人
    private String to[]; //接收人
    private String cc[];//抄送
    private String subject; //主题
    private String text;   //内容
    private Map<String,String> filenames;//发送附件


    public EmailVo() {
    }

    public EmailVo(String from, String[] to, String[] cc, String subject, String text, Map<String, String> filenames) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.text = text;
        this.filenames = filenames;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getFilenames() {
        return filenames;
    }

    public void setFilenames(Map<String, String> filenames) {
        this.filenames = filenames;
    }
}
