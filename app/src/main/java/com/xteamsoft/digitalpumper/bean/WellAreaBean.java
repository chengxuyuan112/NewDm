package com.xteamsoft.digitalpumper.bean;

import java.io.Serializable;
import org.litepal.crud.DataSupport;

public class WellAreaBean extends DataSupport implements Serializable {
    private String _wellareaaddr;

    private String _wellareaenable;

    private String _wellareaid;

    private String _wellarealat;

    private String _wellarealong;

    private String _wellareamark;

    private String _wellareaname;

    private String _wellareazone;

    public String get_wellareaaddr() {
        return this._wellareaaddr;
    }

    public String get_wellareaenable() {
        return this._wellareaenable;
    }

    public String get_wellareaid() {
        return this._wellareaid;
    }

    public String get_wellarealat() {
        return this._wellarealat;
    }

    public String get_wellarealong() {
        return this._wellarealong;
    }

    public String get_wellareamark() {
        return this._wellareamark;
    }

    public String get_wellareaname() {
        return this._wellareaname;
    }

    public String get_wellareazone() {
        return this._wellareazone;
    }

    public void set_wellareaaddr(String paramString) {
        this._wellareaaddr = paramString;
    }

    public void set_wellareaenable(String paramString) {
        this._wellareaenable = paramString;
    }

    public void set_wellareaid(String paramString) {
        this._wellareaid = paramString;
    }

    public void set_wellarealat(String paramString) {
        this._wellarealat = paramString;
    }

    public void set_wellarealong(String paramString) {
        this._wellarealong = paramString;
    }

    public void set_wellareamark(String paramString) {
        this._wellareamark = paramString;
    }

    public void set_wellareaname(String paramString) {
        this._wellareaname = paramString;
    }

    public void set_wellareazone(String paramString) {
        this._wellareazone = paramString;
    }
}
