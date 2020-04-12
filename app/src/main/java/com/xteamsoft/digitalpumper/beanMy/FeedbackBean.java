package com.xteamsoft.digitalpumper.beanMy;

public class FeedbackBean {
    private String code;

    private String content;

    private String is_anonymous;

    private String message;

    private String token;

    public String getCode() {
        return this.code;
    }

    public String getContent() {
        return this.content;
    }

    public String getIs_anonymous() {
        return this.is_anonymous;
    }

    public String getMessage() {
        return this.message;
    }

    public String getToken() {
        return this.token;
    }

    public void setCode(String paramString) {
        this.code = paramString;
    }

    public void setContent(String paramString) {
        this.content = paramString;
    }

    public void setIs_anonymous(String paramString) {
        this.is_anonymous = paramString;
    }

    public void setMessage(String paramString) {
        this.message = paramString;
    }

    public void setToken(String paramString) {
        this.token = paramString;
    }
}
