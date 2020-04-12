package com.xteamsoft.digitalpumper.bean;

public class UserData {
    private String Message;

    private String Result;

    private String iTotalDisplayRecords;

    private String iTotalRecords;

    private String newpwd;

    private String pwd;

    private String sEcho;

    private String token;

    private String username;

    public String getITotalDisplayRecords() {
        return this.iTotalDisplayRecords;
    }

    public String getITotalRecords() {
        return this.iTotalRecords;
    }

    public String getMessage() {
        return this.Message;
    }

    public String getNewpwd() {
        return this.newpwd;
    }

    public String getPwd() {
        return this.pwd;
    }

    public String getResult() {
        return this.Result;
    }

    public String getSEcho() {
        return this.sEcho;
    }

    public String getToken() {
        return this.token;
    }

    public String getUsername() {
        return this.username;
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

    public void setITotalDisplayRecords(String paramString) {
        this.iTotalDisplayRecords = paramString;
    }

    public void setITotalRecords(String paramString) {
        this.iTotalRecords = paramString;
    }

    public void setMessage(String paramString) {
        this.Message = paramString;
    }

    public void setNewpwd(String paramString) {
        this.newpwd = paramString;
    }

    public void setPwd(String paramString) {
        this.pwd = paramString;
    }

    public void setResult(String paramString) {
        this.Result = paramString;
    }

    public void setSEcho(String paramString) {
        this.sEcho = paramString;
    }

    public void setToken(String paramString) {
        this.token = paramString;
    }

    public void setUsername(String paramString) {
        this.username = paramString;
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

    public static class MessageBean {
        private String userEmail;

        private String userID;

        private String userRight;

        private String userToken;

        private String wellAreas;

        public String getUserEmail() {
            return this.userEmail;
        }

        public String getUserID() {
            return this.userID;
        }

        public String getUserRight() {
            return this.userRight;
        }

        public String getUserToken() {
            return this.userToken;
        }

        public String getWellAreas() {
            return this.wellAreas;
        }

        public void setUserEmail(String param1String) {
            this.userEmail = param1String;
        }

        public void setUserID(String param1String) {
            this.userID = param1String;
        }

        public void setUserRight(String param1String) {
            this.userRight = param1String;
        }

        public void setUserToken(String param1String) {
            this.userToken = param1String;
        }

        public void setWellAreas(String param1String) {
            this.wellAreas = param1String;
        }
    }
}
