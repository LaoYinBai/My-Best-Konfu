package com.sigmob.sdk.videoplayer;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public int a;
    public LinkedHashMap b = new LinkedHashMap();
    public HashMap<String, String> c = new HashMap<>();
    public boolean d = false;
    public String title;

    public d(String str, String str2) {
        this.title = "";
        this.b.put("URL_KEY_DEFAULT", str);
        this.title = str2;
        this.a = 0;
    }

    public Object a() {
        return a(this.a);
    }

    public Object a(int i) {
        int i2 = 0;
        for (Object obj : this.b.keySet()) {
            if (i2 == i) {
                return this.b.get(obj);
            }
            i2++;
        }
        return null;
    }
}
