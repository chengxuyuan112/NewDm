package com.xteamsoft.baselibrary.utilsLT;

public class MaxPageReturn {
    public static int returnMaxPage(int paramInt, String paramString) {
        int i = Integer.parseInt(paramString) / paramInt;
        return (Integer.parseInt(paramString) % paramInt > 0) ? (i + 1) : i;
    }
}
