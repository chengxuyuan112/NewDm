package com.xteamsoft.digitalpumper.bean;

public class FeedData {
    private String Message;

    private String Result;

    private String iTotalDisplayRecords;

    private String iTotalRecords;

    private String messages;

    private String sEcho;

    private String token;

    public String getMessage() {
        return this.Message;
    }

    public String getMessages() {
        return this.messages;
    }

    public String getResult() {
        return this.Result;
    }

    public String getToken() {
        return this.token;
    }

    public String getiTotalDisplayRecords() {
        return this.iTotalDisplayRecords;
    }

    public String getiTotalRecords() {
        return this.iTotalRecords;
    }

    public String getsEcho() {
        return this.sEcho;
    }

    public void setMessage(String paramString) {
        this.Message = paramString;
    }

    public void setMessages(String paramString) {
        this.messages = paramString;
    }

    public void setResult(String paramString) {
        this.Result = paramString;
    }

    public void setToken(String paramString) {
        this.token = paramString;
    }

    public void setiTotalDisplayRecords(String paramString) {
        this.iTotalDisplayRecords = paramString;
    }

    public void setiTotalRecords(String paramString) {
        this.iTotalRecords = paramString;
    }

    public void setsEcho(String paramString) {
        this.sEcho = paramString;
    }
}
