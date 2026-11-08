package com.cmic.sso.sdk.b.c;

import android.net.Network;
import com.baidu.mobads.sdk.internal.ae;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.cmic.sso.sdk.auth.AuthnHelper;
import com.cmic.sso.sdk.b.b.g;
import com.cmic.sso.sdk.d.e;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    String f5430a;

    /* renamed from: b, reason: collision with root package name */
    private String f5431b;

    /* renamed from: c, reason: collision with root package name */
    private Map<String, String> f5432c;
    private String d;
    private boolean e;
    private String f;
    private Network g;
    private long h;
    private String i;
    private int j;
    private g k;

    private c(String str, Map<String, String> map, g gVar, String str2, String str3) {
        this.e = false;
        this.f5431b = str;
        this.k = gVar;
        this.f5432c = map == null ? new HashMap<>() : map;
        this.f5430a = gVar == null ? "" : gVar.b().toString();
        this.d = str2;
        this.f = str3;
        this.i = gVar == null ? "" : gVar.a();
        l();
    }

    public c(String str, g gVar, String str2, String str3) {
        this(str, null, gVar, str2, str3);
    }

    private void l() {
        this.f5432c.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, AuthnHelper.SDK_VERSION);
        this.f5432c.put("Content-Type", ae.d);
        this.f5432c.put("traceId", this.f);
        this.f5432c.put("appid", this.i);
        this.f5432c.put("Connection", "close");
    }

    public void a(String str, String str2) {
        this.f5432c.put(str, str2);
    }

    public String a() {
        return this.f5431b;
    }

    public boolean b() {
        return this.e;
    }

    void a(boolean z) {
        this.e = z;
    }

    public Map<String, String> c() {
        return this.f5432c;
    }

    public String d() {
        return this.f5430a;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.f;
    }

    public boolean g() {
        return !e.a(this.f) || this.f5431b.contains("logReport") || this.f5431b.contains("uniConfig");
    }

    public Network h() {
        return this.g;
    }

    public void a(Network network) {
        this.g = network;
    }

    void a(long j) {
        this.h = j;
    }

    long i() {
        return this.h;
    }

    public boolean j() {
        int i = this.j;
        this.j = i + 1;
        return i < 2;
    }

    public g k() {
        return this.k;
    }
}
