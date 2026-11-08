package com.cmic.sso.sdk.c;

import anet.channel.strategy.dispatch.DispatchConstants;
import com.baidu.mobads.sdk.internal.av;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.cmic.sso.sdk.b.b.g;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends g {

    /* renamed from: a, reason: collision with root package name */
    public static CopyOnWriteArrayList<Throwable> f5440a = new CopyOnWriteArrayList<>();
    private String A;
    private String B;
    private String C;
    private JSONArray p;

    /* renamed from: b, reason: collision with root package name */
    private String f5441b = null;

    /* renamed from: c, reason: collision with root package name */
    private String f5442c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;
    private String h = null;
    private String i = null;
    private String j = null;
    private String k = null;
    private String l = "";
    private String m = null;
    private String n = null;
    private String o = null;
    private String q = null;
    private String r = null;
    private String s = null;
    private String t = null;
    private String u = null;
    private String v = null;
    private String w = null;
    private String x = null;
    private String y = null;
    private String z = null;

    @Override // com.cmic.sso.sdk.b.b.g
    public String a() {
        return null;
    }

    public void a(String str) {
        this.f5441b = str;
    }

    public void b(String str) {
        this.A = str;
    }

    public void c(String str) {
        this.x = str;
    }

    public void d(String str) {
        this.y = str;
    }

    public void e(String str) {
        this.z = str;
    }

    public void f(String str) {
        this.t = str;
    }

    public void g(String str) {
        this.n = str;
    }

    public void h(String str) {
        this.m = str;
    }

    public void i(String str) {
        this.l = str;
    }

    public void j(String str) {
        this.d = str;
    }

    public void k(String str) {
        this.e = str;
    }

    public void l(String str) {
        this.g = str;
    }

    public void m(String str) {
        this.j = str;
    }

    void a(JSONArray jSONArray) {
        this.p = jSONArray;
    }

    void o(String str) {
        this.w = str;
    }

    void p(String str) {
        this.q = str;
    }

    void q(String str) {
        this.u = str;
    }

    void r(String str) {
        this.v = str;
    }

    public void s(String str) {
        this.o = str;
    }

    void t(String str) {
        this.f5442c = str;
    }

    public void u(String str) {
        this.h = str;
    }

    void v(String str) {
        this.s = str;
    }

    public void w(String str) {
        this.i = str;
    }

    public void x(String str) {
        this.k = str;
    }

    void y(String str) {
        this.f = str;
    }

    void z(String str) {
        this.r = str;
    }

    public void A(String str) {
        this.B = str;
    }

    public void B(String str) {
        this.C = str;
    }

    @Override // com.cmic.sso.sdk.b.b.g
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appid", this.f5441b);
            jSONObject.put("traceId", this.f5442c);
            jSONObject.put(DispatchConstants.APP_NAME, this.d);
            jSONObject.put(DispatchConstants.APP_VERSION, this.e);
            jSONObject.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, this.f);
            jSONObject.put("clientType", DispatchConstants.ANDROID);
            jSONObject.put("timeOut", this.g);
            jSONObject.put("requestTime", this.h);
            jSONObject.put("responseTime", this.i);
            jSONObject.put("elapsedTime", this.j);
            jSONObject.put("requestType", this.k);
            jSONObject.put("interfaceType", this.l);
            jSONObject.put("interfaceCode", this.m);
            jSONObject.put("interfaceElasped", this.n);
            jSONObject.put("loginType", this.o);
            jSONObject.put("exceptionStackTrace", this.p);
            jSONObject.put("operatorType", this.q);
            jSONObject.put("networkType", this.r);
            jSONObject.put("networkClass", this.s);
            jSONObject.put(av.k, this.t);
            jSONObject.put("reqDevice", this.u);
            jSONObject.put("reqSystem", this.v);
            jSONObject.put("simCardNum", this.w);
            jSONObject.put("imsiState", this.x);
            jSONObject.put("resultCode", this.y);
            jSONObject.put("is_root", this.z);
            jSONObject.put("is_phoneStatePermission", this.A);
            jSONObject.put("AID", this.B);
            jSONObject.put("sysOperType", this.C);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
