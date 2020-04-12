package com.xteamsoft.digitalpumper.bean;

public class SheetData {
    private boolean ischecked;

    private String number;

    private String title;

    private String unit;

    public SheetData() {}

    public SheetData(String paramString1, String paramString2) {
        this.title = paramString1;
        this.unit = paramString2;
    }

    public SheetData(String paramString1, String paramString2, String paramString3) {
        this.title = paramString1;
        this.unit = paramString2;
        this.number = paramString3;
    }

    public SheetData(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
        this.title = paramString1;
        this.unit = paramString2;
        this.number = paramString3;
        this.ischecked = paramBoolean;
    }

    public SheetData(String paramString1, String paramString2, boolean paramBoolean) {
        this.title = paramString1;
        this.unit = paramString2;
        this.ischecked = paramBoolean;
    }

    public String getNumber() {
        return this.number;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUnit() {
        return this.unit;
    }

    public boolean isIschecked() {
        return this.ischecked;
    }

    public void setIschecked(boolean paramBoolean) {
        this.ischecked = paramBoolean;
    }

    public void setNumber(String paramString) {
        this.number = paramString;
    }

    public void setTitle(String paramString) {
        this.title = paramString;
    }

    public void setUnit(String paramString) {
        this.unit = paramString;
    }
}
