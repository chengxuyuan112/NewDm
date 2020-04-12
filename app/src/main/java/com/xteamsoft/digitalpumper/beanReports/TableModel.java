package com.xteamsoft.digitalpumper.beanReports;

import android.graphics.Color;
import android.widget.TextView;

public class TableModel {
    private String leftTitle;

    private String orgCode;

    private String str_green_color = "#009944";

    private String str_red_color = "#ff0000";

    private String text0;

    private String text1;

    private String text10;

    private String text11;

    private String text12;

    private String text13;

    private String text14;

    private String text2;

    private String text3;

    private String text4;

    private String text5;

    private String text6;

    private String text7;

    private String text8;

    private String text9;

    public String getLeftTitle() {
        return this.leftTitle;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public String getText0() {
        return this.text0;
    }

    public String getText1() {
        return this.text1;
    }

    public String getText10() {
        return this.text10;
    }

    public String getText11() {
        return this.text11;
    }

    public String getText12() {
        return this.text12;
    }

    public String getText13() {
        return this.text13;
    }

    public String getText14() {
        return this.text14;
    }

    public String getText2() {
        return this.text2;
    }

    public String getText3() {
        return this.text3;
    }

    public String getText4() {
        return this.text4;
    }

    public String getText5() {
        return this.text5;
    }

    public String getText6() {
        return this.text6;
    }

    public String getText7() {
        return this.text7;
    }

    public String getText8() {
        return this.text8;
    }

    public String getText9() {
        return this.text9;
    }

    public boolean positiveNumber1() {
        return !this.text1.contains("-");
    }

    public void setLeftTitle(String paramString) {
        this.leftTitle = paramString;
    }

    public void setOrgCode(String paramString) {
        this.orgCode = paramString;
    }

    public void setText0(String paramString) {
        this.text0 = paramString;
    }

    public void setText1(String paramString) {
        this.text1 = paramString;
    }

    public void setText10(String paramString) {
        this.text10 = paramString;
    }

    public void setText11(String paramString) {
        this.text11 = paramString;
    }

    public void setText12(String paramString) {
        this.text12 = paramString;
    }

    public void setText13(String paramString) {
        this.text13 = paramString;
    }

    public void setText14(String paramString) {
        this.text14 = paramString;
    }

    public void setText2(String paramString) {
        this.text2 = paramString;
    }

    public void setText3(String paramString) {
        this.text3 = paramString;
    }

    public void setText4(String paramString) {
        this.text4 = paramString;
    }

    public void setText5(String paramString) {
        this.text5 = paramString;
    }

    public void setText6(String paramString) {
        this.text6 = paramString;
    }

    public void setText7(String paramString) {
        this.text7 = paramString;
    }

    public void setText8(String paramString) {
        this.text8 = paramString;
    }

    public void setText9(String paramString) {
        this.text9 = paramString;
    }

    public void setTextColor(TextView paramTextView, String paramString) {
        if (!paramString.contains("-")) {
            paramTextView.setTextColor(Color.parseColor(this.str_red_color));
            return;
        }
        if (paramString.trim().length() > 1) {
            paramTextView.setTextColor(Color.parseColor(this.str_green_color));
            return;
        }
        paramTextView.setTextColor(-16777216);
    }
}
