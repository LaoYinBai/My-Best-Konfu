package com.bytedance.push.e;

import android.app.NotificationChannel;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private String f4781a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4782b;

    /* renamed from: c, reason: collision with root package name */
    private String f4783c;
    private String d;
    private int e;
    private boolean f;
    private int g;
    private boolean h;
    private boolean i;
    private boolean j;

    public a(JSONObject jSONObject) {
        this.f4782b = true;
        this.f4783c = jSONObject.optString("id");
        this.d = jSONObject.optString("name");
        this.e = jSONObject.optInt("importance", 3);
        this.f = jSONObject.optBoolean("bypassDnd", true);
        this.g = jSONObject.optInt("lockscreenVisibility", -1);
        this.h = jSONObject.optBoolean("lights", true);
        this.i = jSONObject.optBoolean("vibration", true);
        this.j = jSONObject.optBoolean("showBadge", true);
        this.f4782b = jSONObject.optBoolean("enable", true);
        this.f4781a = jSONObject.optString("desc");
    }

    public a(NotificationChannel notificationChannel) {
        this.f4782b = true;
        this.f4783c = notificationChannel.getId();
        this.d = String.valueOf(notificationChannel.getName());
        this.e = notificationChannel.getImportance();
        this.f = notificationChannel.canBypassDnd();
        this.g = notificationChannel.getLockscreenVisibility();
        this.h = notificationChannel.shouldShowLights();
        this.i = notificationChannel.shouldVibrate();
        this.j = notificationChannel.canShowBadge();
        this.f4781a = notificationChannel.getDescription();
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", b());
        jSONObject.put("name", c());
        jSONObject.put("importance", d());
        jSONObject.put("bypassDnd", f());
        jSONObject.put("lockscreenVisibility", e());
        jSONObject.put("lights", g());
        jSONObject.put("vibration", h());
        jSONObject.put("showBadge", i());
        jSONObject.put("enable", j());
        jSONObject.put("desc", k());
        return jSONObject;
    }

    public String b() {
        return this.f4783c;
    }

    public String c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public int e() {
        return this.g;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.h;
    }

    public boolean h() {
        return this.i;
    }

    public boolean i() {
        return this.j;
    }

    public boolean j() {
        return this.f4782b;
    }

    public String k() {
        return this.f4781a;
    }
}
