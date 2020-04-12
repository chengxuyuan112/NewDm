package com.xteamsoft.digitalpumper.bean;

import java.io.Serializable;

public class FluidData {
    private String Message;

    private String Result;

    private String begin;

    private String end;

    private String iDisplayLength;

    private String iDisplayRecords;

    private String iDisplayStart;

    private String iTotalRecords;

    private String id;

    private String sEcho;

    private String token;

    public String getBegin() {
        return this.begin;
    }

    public String getEnd() {
        return this.end;
    }

    public String getId() {
        return this.id;
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

    public void setBegin(String paramString) {
        this.begin = paramString;
    }

    public void setEnd(String paramString) {
        this.end = paramString;
    }

    public void setId(String paramString) {
        this.id = paramString;
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

    public static class MessageFluid implements Serializable {
        private int[] _fluiddata;

        private String _fluiddate;

        private String _fluiddepth;

        private String _fluidid;

        private String _fluidothers;

        private String _fluidpointnum;

        private String _fluidsampling;

        public int[] get_fluiddata() {
            return this._fluiddata;
        }

        public String get_fluiddate() {
            return this._fluiddate;
        }

        public String get_fluiddepth() {
            return this._fluiddepth;
        }

        public String get_fluidid() {
            return this._fluidid;
        }

        public String get_fluidothers() {
            return this._fluidothers;
        }

        public String get_fluidpointnum() {
            return this._fluidpointnum;
        }

        public String get_fluidsampling() {
            return this._fluidsampling;
        }

        public void set_fluiddata(int[] param1ArrayOfint) {
            this._fluiddata = param1ArrayOfint;
        }

        public void set_fluiddate(String param1String) {
            this._fluiddate = param1String;
        }

        public void set_fluiddepth(String param1String) {
            this._fluiddepth = param1String;
        }

        public void set_fluidid(String param1String) {
            this._fluidid = param1String;
        }

        public void set_fluidothers(String param1String) {
            this._fluidothers = param1String;
        }

        public void set_fluidpointnum(String param1String) {
            this._fluidpointnum = param1String;
        }

        public void set_fluidsampling(String param1String) {
            this._fluidsampling = param1String;
        }
    }
}
