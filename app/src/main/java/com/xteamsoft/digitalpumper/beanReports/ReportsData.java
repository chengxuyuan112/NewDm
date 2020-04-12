package com.xteamsoft.digitalpumper.beanReports;

import java.io.Serializable;

public class ReportsData {
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

    public static class MessageReports implements Serializable {
        private String _drepaccflow;

        private String _drepbrintalarm;

        private String _drepcaspres;

        private String _drepcurflow;

        private String _drepdate;

        private String _drepf1;

        private String _drepf2;

        private String _drepf3;

        private String _drepfluidlpos;

        private String _drepgaspres;

        private String _drepgastemp;

        private String _drepid;

        private String _drepinveralarm;

        private String _drepinvercur;

        private String _drepinverfreq;

        private String _drepinversfreq;

        private String _drepinvertorque;

        private String _drepinvervolt;

        private String _drepotank1fpos;

        private String _drepotank1ftem;

        private String _drepotank2fpos;

        private String _drepotank2ftem;

        private String _dreppumpbvolt;

        private String _dreppumpfault;

        private String _dreppumpspd;

        private String _drepspowoff;

        private String _drepsyspres;

        private String _dreptubepres;

        private String _drepwtank1fpos;

        private String _drepwtank1ftem;

        private String _drepwtank2fpos;

        private String _drepwtank2ftem;

        private String _wellid;

        private String leftTitle;

        private String orgCode;

        public String getLeftTitle() {
            return this.leftTitle;
        }

        public String getOrgCode() {
            return this.orgCode;
        }

        public String get_drepaccflow() {
            return this._drepaccflow;
        }

        public String get_drepbrintalarm() {
            return this._drepbrintalarm;
        }

        public String get_drepcaspres() {
            return this._drepcaspres;
        }

        public String get_drepcurflow() {
            return this._drepcurflow;
        }

        public String get_drepdate() {
            return this._drepdate;
        }

        public String get_drepf1() {
            return this._drepf1;
        }

        public String get_drepf2() {
            return this._drepf2;
        }

        public String get_drepf3() {
            return this._drepf3;
        }

        public String get_drepfluidlpos() {
            return this._drepfluidlpos;
        }

        public String get_drepgaspres() {
            return this._drepgaspres;
        }

        public String get_drepgastemp() {
            return this._drepgastemp;
        }

        public String get_drepid() {
            return this._drepid;
        }

        public String get_drepinveralarm() {
            return this._drepinveralarm;
        }

        public String get_drepinvercur() {
            return this._drepinvercur;
        }

        public String get_drepinverfreq() {
            return this._drepinverfreq;
        }

        public String get_drepinversfreq() {
            return this._drepinversfreq;
        }

        public String get_drepinvertorque() {
            return this._drepinvertorque;
        }

        public String get_drepinvervolt() {
            return this._drepinvervolt;
        }

        public String get_drepotank1fpos() {
            return this._drepotank1fpos;
        }

        public String get_drepotank1ftem() {
            return this._drepotank1ftem;
        }

        public String get_drepotank2fpos() {
            return this._drepotank2fpos;
        }

        public String get_drepotank2ftem() {
            return this._drepotank2ftem;
        }

        public String get_dreppumpbvolt() {
            return this._dreppumpbvolt;
        }

        public String get_dreppumpfault() {
            return this._dreppumpfault;
        }

        public String get_dreppumpspd() {
            return this._dreppumpspd;
        }

        public String get_drepspowoff() {
            return this._drepspowoff;
        }

        public String get_drepsyspres() {
            return this._drepsyspres;
        }

        public String get_dreptubepres() {
            return this._dreptubepres;
        }

        public String get_drepwtank1fpos() {
            return this._drepwtank1fpos;
        }

        public String get_drepwtank1ftem() {
            return this._drepwtank1ftem;
        }

        public String get_drepwtank2fpos() {
            return this._drepwtank2fpos;
        }

        public String get_drepwtank2ftem() {
            return this._drepwtank2ftem;
        }

        public String get_wellid() {
            return this._wellid;
        }

        public void setLeftTitle(String param1String) {
            this.leftTitle = param1String;
        }

        public void setOrgCode(String param1String) {
            this.orgCode = param1String;
        }

        public void set_drepaccflow(String param1String) {
            this._drepaccflow = param1String;
        }

        public void set_drepbrintalarm(String param1String) {
            this._drepbrintalarm = param1String;
        }

        public void set_drepcaspres(String param1String) {
            this._drepcaspres = param1String;
        }

        public void set_drepcurflow(String param1String) {
            this._drepcurflow = param1String;
        }

        public void set_drepdate(String param1String) {
            this._drepdate = param1String;
        }

        public void set_drepf1(String param1String) {
            this._drepf1 = param1String;
        }

        public void set_drepf2(String param1String) {
            this._drepf2 = param1String;
        }

        public void set_drepf3(String param1String) {
            this._drepf3 = param1String;
        }

        public void set_drepfluidlpos(String param1String) {
            this._drepfluidlpos = param1String;
        }

        public void set_drepgaspres(String param1String) {
            this._drepgaspres = param1String;
        }

        public void set_drepgastemp(String param1String) {
            this._drepgastemp = param1String;
        }

        public void set_drepid(String param1String) {
            this._drepid = param1String;
        }

        public void set_drepinveralarm(String param1String) {
            this._drepinveralarm = param1String;
        }

        public void set_drepinvercur(String param1String) {
            this._drepinvercur = param1String;
        }

        public void set_drepinverfreq(String param1String) {
            this._drepinverfreq = param1String;
        }

        public void set_drepinversfreq(String param1String) {
            this._drepinversfreq = param1String;
        }

        public void set_drepinvertorque(String param1String) {
            this._drepinvertorque = param1String;
        }

        public void set_drepinvervolt(String param1String) {
            this._drepinvervolt = param1String;
        }

        public void set_drepotank1fpos(String param1String) {
            this._drepotank1fpos = param1String;
        }

        public void set_drepotank1ftem(String param1String) {
            this._drepotank1ftem = param1String;
        }

        public void set_drepotank2fpos(String param1String) {
            this._drepotank2fpos = param1String;
        }

        public void set_drepotank2ftem(String param1String) {
            this._drepotank2ftem = param1String;
        }

        public void set_dreppumpbvolt(String param1String) {
            this._dreppumpbvolt = param1String;
        }

        public void set_dreppumpfault(String param1String) {
            this._dreppumpfault = param1String;
        }

        public void set_dreppumpspd(String param1String) {
            this._dreppumpspd = param1String;
        }

        public void set_drepspowoff(String param1String) {
            this._drepspowoff = param1String;
        }

        public void set_drepsyspres(String param1String) {
            this._drepsyspres = param1String;
        }

        public void set_dreptubepres(String param1String) {
            this._dreptubepres = param1String;
        }

        public void set_drepwtank1fpos(String param1String) {
            this._drepwtank1fpos = param1String;
        }

        public void set_drepwtank1ftem(String param1String) {
            this._drepwtank1ftem = param1String;
        }

        public void set_drepwtank2fpos(String param1String) {
            this._drepwtank2fpos = param1String;
        }

        public void set_drepwtank2ftem(String param1String) {
            this._drepwtank2ftem = param1String;
        }

        public void set_wellid(String param1String) {
            this._wellid = param1String;
        }
    }
}
