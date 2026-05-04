package com.simple.microservices.notification.dto;
public class NotificationTemplateDto {
    private String id;
    private String code;
    private String channel;
    private String subject;
    private String body;
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code=code;
    }
    public String getChannel(){
        return channel;
    }
    public void setChannel(String channel){
        this.channel=channel;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public String getBody(){
        return body;
    }
    public void setBody(String body){
        this.body=body;
    }
}