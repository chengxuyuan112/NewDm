package com.xteamsoft.digitalpumper.bean;

public class WellAreaData {
    private String Message;

    private String Result;

    private String areaaddr;

    private String areaname;

    private String iDisplayLength;

    private String iDisplayRecords;

    private String iDisplayStart;

    private String iTotalRecords;

    private String sEcho;

    private String token;

    public String getAreaaddr() {
        return this.areaaddr;
    }

    public String getAreaname() {
        return this.areaname;
    }

    public String getMessage() {
        return this.Message;
    }

    public String getResult() {
        return this.Result;
    }

    public String getToken() {
        return this.token;
    }

    public String getiDisplayLength() {
        return this.iDisplayLength;
    }

    public String getiDisplayRecords() {
        return this.iDisplayRecords;
    }

    public String getiDisplayStart() {
        return this.iDisplayStart;
    }

    public String getiTotalRecords() {
        return this.iTotalRecords;
    }

    public String getsEcho() {
        return this.sEcho;
    }

    public void setAreaaddr(String paramString) {
        this.areaaddr = paramString;
    }

    public void setAreaname(String paramString) {
        this.areaname = paramString;
    }

    public void setMessage(String paramString) {
        this.Message = paramString;
    }

    public void setResult(String paramString) {
        this.Result = paramString;
    }

    public void setToken(String paramString) {
        this.token = paramString;
    }

    public void setiDisplayLength(String paramString) {
        this.iDisplayLength = paramString;
    }

    public void setiDisplayRecords(String paramString) {
        this.iDisplayRecords = paramString;
    }

    public void setiDisplayStart(String paramString) {
        this.iDisplayStart = paramString;
    }

    public void setiTotalRecords(String paramString) {
        this.iTotalRecords = paramString;
    }

    public void setsEcho(String paramString) {
        this.sEcho = paramString;
    }
}
