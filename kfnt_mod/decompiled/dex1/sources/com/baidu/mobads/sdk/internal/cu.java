package com.baidu.mobads.sdk.internal;

import android.app.Activity;
import android.content.Context;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cu {

    /* renamed from: a, reason: collision with root package name */
    private Boolean f3221a;

    public String c() {
        return "http://211.151.146.65:8080/wlantest/shanghai_sun/mock_ad_server_intersitial_video.json";
    }

    public cu(Context context, Activity activity, Boolean bool) {
        this.f3221a = bool;
        a().booleanValue();
    }

    public Boolean a() {
        return this.f3221a;
    }

    protected HashMap<String, String> b() {
        HashMap<String, String> map = new HashMap<>();
        map.put("xyz", "hihihi");
        return map;
    }
}
