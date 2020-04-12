package com.xteamsoft.digitalpumper.beanMy;

public class ChangePasswordBean {
    private String code;

    private String message;

    private String newPassword;

    private String oldPassword;

    private String token;

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public String getToken() {
        return this.token;
    }

    public void setCode(String paramString) {
        this.code = paramString;
    }

    public void setMessage(String paramString) {
        this.message = paramString;
    }

    public void setNewPassword(String paramString) {
        this.newPassword = paramString;
    }

    public void setOldPassword(String paramString) {
        this.oldPassword = paramString;
    }

    public void setToken(String paramString) {
        this.token = paramString;
    }
}
