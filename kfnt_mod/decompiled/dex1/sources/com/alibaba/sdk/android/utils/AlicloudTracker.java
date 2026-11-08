package com.alibaba.sdk.android.utils;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AlicloudTracker {

    /* renamed from: a, reason: collision with root package name */
    private c f2407a;

    /* renamed from: a, reason: collision with other field name */
    private String f46a;

    /* renamed from: b, reason: collision with root package name */
    private String f2408b;

    /* renamed from: b, reason: collision with other field name */
    private Map<String, String> f47b = new HashMap();

    AlicloudTracker(c cVar, String str, String str2) {
        this.f2407a = cVar;
        this.f46a = str;
        this.f2408b = str2;
    }

    public void sendCustomHit(String str, long j, Map<String, String> map) {
        try {
            if (this.f2407a == null) {
                Log.e("AlicloudTracker", "dataTracker is null, can not sendCustomHit");
                return;
            }
            if (map == null) {
                map = new HashMap<>();
            }
            map.putAll(this.f47b);
            map.put("sdkId", this.f46a);
            map.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, this.f2408b);
            this.f2407a.sendCustomHit(this.f46a + "_" + str, j, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCustomHit(String str, Map<String, String> map) {
        sendCustomHit(str, 0L, map);
    }

    public void setGlobalProperty(String str, String str2) {
        if (!TextUtils.isEmpty(str) && str2 != null) {
            if (this.f47b.containsKey(str)) {
                this.f47b.remove(str);
            }
            this.f47b.put(str, str2);
            return;
        }
        Log.e("AlicloudTracker", "key is null or key is empty or value is null,please check it!");
    }

    public void removeGlobalProperty(String str) {
        if (!TextUtils.isEmpty(str) && this.f47b.containsKey(str)) {
            this.f47b.remove(str);
        } else {
            Log.e("AlicloudTracker", "key is null or key is empty,please check it!");
        }
    }
}
