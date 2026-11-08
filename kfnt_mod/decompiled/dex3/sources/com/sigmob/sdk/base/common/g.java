package com.sigmob.sdk.base.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import java.lang.ref.WeakReference;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class g {
    protected String a;
    protected Context b;
    protected Context c;
    protected WeakReference<Activity> d;
    protected RelativeLayout e;
    protected h f;

    protected g(Activity activity, String str, h hVar) {
        this.c = activity;
        this.b = activity.getApplicationContext();
        this.d = new WeakReference<>(activity);
        this.a = str;
        this.f = hVar;
        this.e = new RelativeLayout(this.b);
    }

    public void a(int i, int i2, Intent intent) {
    }

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    protected void a(String str) {
        if (this.a != null) {
            BaseBroadcastReceiver.a(this.b, this.a, str);
        } else {
            SigmobLog.w("Tried to broadcast a video event without a broadcast identifier to send to.");
        }
    }

    protected void a(String str, int i) {
        if (this.a != null) {
            BaseBroadcastReceiver.a(this.b, this.a, str, i);
        } else {
            SigmobLog.w("Tried to broadcast a video event without a broadcast identifier to send to.");
        }
    }

    protected void a(String str, Map<String, Object> map) {
        if (this.a != null) {
            BaseBroadcastReceiver.a(this.b, this.a, map, str, 0);
        } else {
            SigmobLog.w("Tried to broadcast a video event without a broadcast identifier to send to.");
        }
    }

    public abstract void b();

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public boolean g() {
        return true;
    }

    protected h h() {
        return this.f;
    }

    protected Context i() {
        return this.b;
    }

    protected ViewGroup j() {
        return this.e;
    }

    protected Activity k() {
        if (this.d != null) {
            return this.d.get();
        }
        return null;
    }
}
