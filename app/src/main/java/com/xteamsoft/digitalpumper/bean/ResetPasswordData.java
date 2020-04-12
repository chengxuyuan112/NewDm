package com.xteamsoft.digitalpumper.bean;

public class ResetPasswordData {
    private String code;

    private String datatime;

    private String message;

    private String newPassword;

    private String password;

    private String telePhone;

    private String token;

    public String getCode() {
        return this.code;
    }

    public String getDatatime() {
        return this.datatime;
    }

    public String getMessage() {
        return this.message;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getPassword() {
        return this.password;
    }

    public String getTelePhone() {
        return this.telePhone;
    }

    public String getToken() {
        return this.token;
    }

    public void setCode(String paramString) {
        this.code = paramString;
    }

    public void setDatatime(String paramString) {
        this.datatime = paramString;
    }

    public void setMessage(String paramString) {
        this.message = paramString;
    }

    public void setNewPassword(String paramString) {
        this.newPassword = paramString;
    }

    public void setPassword(String paramString) {
        this.password = paramString;
    }

    public void setTelePhone(String paramString) {
        this.telePhone = paramString;
    }

    public void setToken(String paramString) {
        this.token = paramString;
    }
}
