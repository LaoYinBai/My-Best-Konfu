package com.bytedance.a;

import anet.channel.util.HttpConstant;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.UriConfig;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class bi implements z {

    /* renamed from: a, reason: collision with root package name */
    public final UriConfig f3408a;

    public bi(@org.b.a.d UriConfig uriConfig) {
        Intrinsics.checkParameterIsNotNull(uriConfig, "uriConfig");
        this.f3408a = uriConfig;
    }

    @org.b.a.e
    public ao<ae> a(@org.b.a.d aj ajVar) {
        String strA;
        Intrinsics.checkParameterIsNotNull(ajVar, "queryParam");
        HashMap map = new HashMap();
        map.put("Accept", com.baidu.mobads.sdk.internal.ae.d);
        map.put("Content-Type", "application/octet-stream;tt-data=a");
        if (AppLog.getEncryptAndCompress()) {
            map.put("Content-Encoding", HttpConstant.GZIP);
        }
        try {
            strA = bf.a(0, this.f3408a.getAlinkQueryUri() + '?' + ajVar, map, null, 1000);
        } catch (Exception e) {
            de.a("", e);
            strA = null;
        }
        if (strA == null || strA.length() == 0) {
            return ao.f3362a.a();
        }
        try {
            return ao.f3362a.a(strA, ae.class);
        } catch (JSONException e2) {
            de.a("", e2);
            return null;
        }
    }

    @org.b.a.e
    public ao<at> a(@org.b.a.d ay ayVar, @org.b.a.d aj ajVar) throws JSONException {
        String str;
        String str2;
        String strA;
        Intrinsics.checkParameterIsNotNull(ayVar, "params");
        Intrinsics.checkParameterIsNotNull(ajVar, "queryParam");
        HashMap map = new HashMap();
        map.put("Accept", com.baidu.mobads.sdk.internal.ae.d);
        if (AppLog.getEncryptAndCompress()) {
            map.put("Content-Type", "application/octet-stream;tt-data=a");
            str = "Content-Encoding";
            str2 = HttpConstant.GZIP;
        } else {
            str = "Content-Type";
            str2 = "application/json; encoding=utf-8";
        }
        map.put(str, str2);
        boolean z = true;
        try {
            strA = bf.a(1, this.f3408a.getAlinkAttributionUri() + '?' + ajVar, map, m.a(ayVar.a().toString()), 2000);
        } catch (Exception e) {
            de.a("", e);
            strA = null;
        }
        if (strA != null && strA.length() != 0) {
            z = false;
        }
        if (z) {
            return ao.f3362a.a();
        }
        try {
            return ao.f3362a.a(strA, at.class);
        } catch (JSONException e2) {
            de.a("", e2);
            return null;
        }
    }
}
