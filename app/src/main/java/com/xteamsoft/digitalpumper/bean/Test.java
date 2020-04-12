package com.xteamsoft.digitalpumper.bean;

import org.litepal.crud.DataSupport;

public class Test extends DataSupport {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }
}
