package com.xteamsoft.baselibrary.utilsLT;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringGsonToObject {
    public static Object JSONToObject(String paramString, Class paramClass) {
        return (new Gson()).fromJson(paramString, paramClass);
    }

    public static <T> List<T> stringToList(String paramString, Class<T> paramClass) {
        Gson gson = new Gson();
        ArrayList<T> arrayList = new ArrayList();
        Iterator<JsonElement> iterator = (new JsonParser()).parse(paramString).getAsJsonArray().iterator();
        while (iterator.hasNext())
            arrayList.add(gson.fromJson(iterator.next(), paramClass));
        return arrayList;
    }
}
