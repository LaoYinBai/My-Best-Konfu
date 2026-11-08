package com.alipay.security.mobile.module.http.model;

import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends a {

    /* renamed from: c, reason: collision with root package name */
    public static final int f2732c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final String f = "APPKEY_ERROR";
    public static final String g = "SUCCESS";
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p = "";

    public int a() {
        return this.f2730a ? com.alipay.security.mobile.module.a.a.a(this.h) ? 2 : 1 : f.equals(this.f2731b) ? 3 : 2;
    }

    public boolean b() {
        return "1".equals(this.j);
    }

    public String c() {
        return this.k == null ? OnekeyLoginConstants.CU_RESULT_SUCCESS : this.k;
    }
}
