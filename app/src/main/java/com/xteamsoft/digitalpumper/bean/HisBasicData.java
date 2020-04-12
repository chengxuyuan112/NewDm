package com.xteamsoft.digitalpumper.bean;

import java.io.Serializable;

public class HisBasicData {
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

    public static class MessageHisBasic implements Serializable {
        private String _accumflow;

        private String _breakintoalarm;

        private String _casingpres;

        private String _collectdate;

        private String _currentflow;

        private String _f1;

        private String _f2;

        private String _f3;

        private String _fluidlevelpos;

        private String _gaslinepres;

        private String _gastemperature;

        private String _hisdataid;

        private String _inverteralarm;

        private String _invertercurr;

        private String _inverterfreq;

        private String _invertersetedfreq;

        private String _invertertorque;

        private String _invertervolt;

        private String _otank1fluidpos;

        private String _otank1liqtemper;

        private String _otank2fluidpos;

        private String _otank2liqtemper;

        private String _pumpingbusvolt;

        private String _pumpingfault;

        private String _pumpingspeed;

        private String _systempoweroff;

        private String _systempres;

        private String _tubingpres;

        private String _wellid;

        private String _wtank1fluidpos;

        private String _wtank1liqtemper;

        private String _wtank2fluidpos;

        private String _wtank2liqtemper;

        public String get_accumflow() {
            return this._accumflow;
        }

        public String get_breakintoalarm() {
            return this._breakintoalarm;
        }

        public String get_casingpres() {
            return this._casingpres;
        }

        public String get_collectdate() {
            return this._collectdate;
        }

        public String get_currentflow() {
            return this._currentflow;
        }

        public String get_f1() {
            return this._f1;
        }

        public String get_f2() {
            return this._f2;
        }

        public String get_f3() {
            return this._f3;
        }

        public String get_fluidlevelpos() {
            return this._fluidlevelpos;
        }

        public String get_gaslinepres() {
            return this._gaslinepres;
        }

        public String get_gastemperature() {
            return this._gastemperature;
        }

        public String get_hisdataid() {
            return this._hisdataid;
        }

        public String get_inverteralarm() {
            return this._inverteralarm;
        }

        public String get_invertercurr() {
            return this._invertercurr;
        }

        public String get_inverterfreq() {
            return this._inverterfreq;
        }

        public String get_invertersetedfreq() {
            return this._invertersetedfreq;
        }

        public String get_invertertorque() {
            return this._invertertorque;
        }

        public String get_invertervolt() {
            return this._invertervolt;
        }

        public String get_otank1fluidpos() {
            return this._otank1fluidpos;
        }

        public String get_otank1liqtemper() {
            return this._otank1liqtemper;
        }

        public String get_otank2fluidpos() {
            return this._otank2fluidpos;
        }

        public String get_otank2liqtemper() {
            return this._otank2liqtemper;
        }

        public String get_pumpingbusvolt() {
            return this._pumpingbusvolt;
        }

        public String get_pumpingfault() {
            return this._pumpingfault;
        }

        public String get_pumpingspeed() {
            return this._pumpingspeed;
        }

        public String get_systempoweroff() {
            return this._systempoweroff;
        }

        public String get_systempres() {
            return this._systempres;
        }

        public String get_tubingpres() {
            return this._tubingpres;
        }

        public String get_wellid() {
            return this._wellid;
        }

        public String get_wtank1fluidpos() {
            return this._wtank1fluidpos;
        }

        public String get_wtank1liqtemper() {
            return this._wtank1liqtemper;
        }

        public String get_wtank2fluidpos() {
            return this._wtank2fluidpos;
        }

        public String get_wtank2liqtemper() {
            return this._wtank2liqtemper;
        }

        public void set_accumflow(String param1String) {
            this._accumflow = param1String;
        }

        public void set_breakintoalarm(String param1String) {
            this._breakintoalarm = param1String;
        }

        public void set_casingpres(String param1String) {
            this._casingpres = param1String;
        }

        public void set_collectdate(String param1String) {
            this._collectdate = param1String;
        }

        public void set_currentflow(String param1String) {
            this._currentflow = param1String;
        }

        public void set_f1(String param1String) {
            this._f1 = param1String;
        }

        public void set_f2(String param1String) {
            this._f2 = param1String;
        }

        public void set_f3(String param1String) {
            this._f3 = param1String;
        }

        public void set_fluidlevelpos(String param1String) {
            this._fluidlevelpos = param1String;
        }

        public void set_gaslinepres(String param1String) {
            this._gaslinepres = param1String;
        }

        public void set_gastemperature(String param1String) {
            this._gastemperature = param1String;
        }

        public void set_hisdataid(String param1String) {
            this._hisdataid = param1String;
        }

        public void set_inverteralarm(String param1String) {
            this._inverteralarm = param1String;
        }

        public void set_invertercurr(String param1String) {
            this._invertercurr = param1String;
        }

        public void set_inverterfreq(String param1String) {
            this._inverterfreq = param1String;
        }

        public void set_invertersetedfreq(String param1String) {
            this._invertersetedfreq = param1String;
        }

        public void set_invertertorque(String param1String) {
            this._invertertorque = param1String;
        }

        public void set_invertervolt(String param1String) {
            this._invertervolt = param1String;
        }

        public void set_otank1fluidpos(String param1String) {
            this._otank1fluidpos = param1String;
        }

        public void set_otank1liqtemper(String param1String) {
            this._otank1liqtemper = param1String;
        }

        public void set_otank2fluidpos(String param1String) {
            this._otank2fluidpos = param1String;
        }

        public void set_otank2liqtemper(String param1String) {
            this._otank2liqtemper = param1String;
        }

        public void set_pumpingbusvolt(String param1String) {
            this._pumpingbusvolt = param1String;
        }

        public void set_pumpingfault(String param1String) {
            this._pumpingfault = param1String;
        }

        public void set_pumpingspeed(String param1String) {
            this._pumpingspeed = param1String;
        }

        public void set_systempoweroff(String param1String) {
            this._systempoweroff = param1String;
        }

        public void set_systempres(String param1String) {
            this._systempres = param1String;
        }

        public void set_tubingpres(String param1String) {
            this._tubingpres = param1String;
        }

        public void set_wellid(String param1String) {
            this._wellid = param1String;
        }

        public void set_wtank1fluidpos(String param1String) {
            this._wtank1fluidpos = param1String;
        }

        public void set_wtank1liqtemper(String param1String) {
            this._wtank1liqtemper = param1String;
        }

        public void set_wtank2fluidpos(String param1String) {
            this._wtank2fluidpos = param1String;
        }

        public void set_wtank2liqtemper(String param1String) {
            this._wtank2liqtemper = param1String;
        }
    }
}
