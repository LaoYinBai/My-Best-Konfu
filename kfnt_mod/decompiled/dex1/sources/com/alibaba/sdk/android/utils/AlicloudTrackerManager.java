package com.alibaba.sdk.android.utils;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.sdk.android.utils.crashdefend.SDKMessageCallback;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AlicloudTrackerManager {

    /* renamed from: a, reason: collision with root package name */
    private static AlicloudTrackerManager f2409a;

    /* renamed from: a, reason: collision with other field name */
    private c f48a = new c();

    /* renamed from: a, reason: collision with other field name */
    private com.alibaba.sdk.android.utils.crashdefend.b f49a;

    /* renamed from: c, reason: collision with root package name */
    private Map<String, AlicloudTracker> f2410c;

    private AlicloudTrackerManager(Application application) {
        this.f49a = null;
        HashMap map = new HashMap(4);
        map.put("kVersion", "1.1.4");
        map.put("packageName", application.getPackageName());
        this.f48a.a(application, map);
        this.f2410c = new HashMap();
        this.f49a = com.alibaba.sdk.android.utils.crashdefend.b.a(application, this.f48a);
    }

    public static synchronized AlicloudTrackerManager getInstance(Application application) {
        if (application == null) {
            return null;
        }
        if (f2409a == null) {
            f2409a = new AlicloudTrackerManager(application);
        }
        return f2409a;
    }

    public AlicloudTracker getTracker(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.e("AlicloudTrackerManager", "sdkId or sdkVersion is null");
            return null;
        }
        String str3 = str + str2;
        if (this.f2410c.containsKey(str3)) {
            return this.f2410c.get(str3);
        }
        AlicloudTracker alicloudTracker = new AlicloudTracker(this.f48a, str, str2);
        this.f2410c.put(str3, alicloudTracker);
        return alicloudTracker;
    }

    public boolean registerCrashDefend(String str, String str2, int i, int i2, SDKMessageCallback sDKMessageCallback) {
        if (this.f49a == null) {
            return false;
        }
        com.alibaba.sdk.android.utils.crashdefend.c cVar = new com.alibaba.sdk.android.utils.crashdefend.c();
        cVar.f58a = str;
        cVar.f60b = str2;
        cVar.f2418a = i;
        cVar.f2419b = i2;
        return this.f49a.m33a(cVar, sDKMessageCallback);
    }

    public void unregisterCrashDefend(String str, String str2) {
        this.f49a.d(str, str2);
    }
}
