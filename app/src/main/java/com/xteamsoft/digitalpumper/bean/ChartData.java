package com.xteamsoft.digitalpumper.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ChartData {
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

    public static class MessageChart implements Serializable, Parcelable {
        public static final Parcelable.Creator<MessageChart> CREATOR = new Parcelable.Creator<MessageChart>() {
            public ChartData.MessageChart createFromParcel(Parcel param2Parcel) {
                return new ChartData.MessageChart(param2Parcel);
            }

            public ChartData.MessageChart[] newArray(int param2Int) {
                return new ChartData.MessageChart[param2Int];
            }
        };

        private int[] _loaddata;

        private String _loaddatetime;

        private String _loaddispid;

        private int[] _loadothers;

        private String _loadpointnum;

        private String _loadsampling;

        private String _loadspeed;

        private String _wellid;

        protected MessageChart(Parcel param1Parcel) {
            this._loaddispid = param1Parcel.readString();
            this._loaddatetime = param1Parcel.readString();
            this._loadpointnum = param1Parcel.readString();
            this._loadsampling = param1Parcel.readString();
            this._loadspeed = param1Parcel.readString();
            this._loadothers = param1Parcel.createIntArray();
            this._loaddata = param1Parcel.createIntArray();
            this._wellid = param1Parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public int[] get_loaddata() {
            return this._loaddata;
        }

        public String get_loaddatetime() {
            return this._loaddatetime;
        }

        public String get_loaddispid() {
            return this._loaddispid;
        }

        public int[] get_loadothers() {
            return this._loadothers;
        }

        public String get_loadpointnum() {
            return this._loadpointnum;
        }

        public String get_loadsampling() {
            return this._loadsampling;
        }

        public String get_loadspeed() {
            return this._loadspeed;
        }

        public String get_wellid() {
            return this._wellid;
        }

        public void set_loaddata(int[] param1ArrayOfint) {
            this._loaddata = param1ArrayOfint;
        }

        public void set_loaddatetime(String param1String) {
            this._loaddatetime = param1String;
        }

        public void set_loaddispid(String param1String) {
            this._loaddispid = param1String;
        }

        public void set_loadothers(int[] param1ArrayOfint) {
            this._loadothers = param1ArrayOfint;
        }

        public void set_loadpointnum(String param1String) {
            this._loadpointnum = param1String;
        }

        public void set_loadsampling(String param1String) {
            this._loadsampling = param1String;
        }

        public void set_loadspeed(String param1String) {
            this._loadspeed = param1String;
        }

        public void set_wellid(String param1String) {
            this._wellid = param1String;
        }

        public void writeToParcel(Parcel param1Parcel, int param1Int) {
            param1Parcel.writeString(this._loaddispid);
            param1Parcel.writeString(this._loaddatetime);
            param1Parcel.writeString(this._loadpointnum);
            param1Parcel.writeString(this._loadsampling);
            param1Parcel.writeString(this._loadspeed);
            param1Parcel.writeIntArray(this._loadothers);
            param1Parcel.writeIntArray(this._loaddata);
            param1Parcel.writeString(this._wellid);
        }
    }

//    static final class null implements Parcelable.Creator<MessageChart> {
//        public ChartData.MessageChart createFromParcel(Parcel param1Parcel) {
//            return new ChartData.MessageChart(param1Parcel);
//        }
//
//        public ChartData.MessageChart[] newArray(int param1Int) {
//            return new ChartData.MessageChart[param1Int];
//        }
//    }
}
