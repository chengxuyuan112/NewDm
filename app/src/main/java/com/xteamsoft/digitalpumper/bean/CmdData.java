package com.xteamsoft.digitalpumper.bean;

import java.io.Serializable;

public class CmdData {
    private String Result;

    private int dataType;

    private String json;

    private String message;

    private String messageExt;

    private int messageType;

    private int status;

    private String token;

    public int getDataType() {
        return this.dataType;
    }

    public String getJson() {
        return this.json;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMessageExt() {
        return this.messageExt;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public String getResult() {
        return this.Result;
    }

    public int getStatus() {
        return this.status;
    }

    public String getToken() {
        return this.token;
    }

    public void setDataType(int paramInt) {
        this.dataType = paramInt;
    }

    public void setJson(String paramString) {
        this.json = paramString;
    }

    public void setMessage(String paramString) {
        this.message = paramString;
    }

    public void setMessageExt(String paramString) {
        this.messageExt = paramString;
    }

    public void setMessageType(int paramInt) {
        this.messageType = paramInt;
    }

    public void setResult(String paramString) {
        this.Result = paramString;
    }

    public void setStatus(int paramInt) {
        this.status = paramInt;
    }

    public void setToken(String paramString) {
        this.token = paramString;
    }

    public static class RcmdData implements Serializable {
        private int dataType;

        private String message;

        private String messageExt;

        private int messageType;

        public RcmdData() {}

        public RcmdData(int param1Int1, String param1String1, String param1String2, int param1Int2) {
            this.dataType = param1Int1;
            this.message = param1String1;
            this.messageExt = param1String2;
            this.messageType = param1Int2;
        }

        public int getDataType() {
            return this.dataType;
        }

        public String getMessage() {
            return this.message;
        }

        public String getMessageExt() {
            return this.messageExt;
        }

        public int getMessageType() {
            return this.messageType;
        }

        public void setDataType(int param1Int) {
            this.dataType = param1Int;
        }

        public void setMessage(String param1String) {
            this.message = param1String;
        }

        public void setMessageExt(String param1String) {
            this.messageExt = param1String;
        }

        public void setMessageType(int param1Int) {
            this.messageType = param1Int;
        }
    }
}
