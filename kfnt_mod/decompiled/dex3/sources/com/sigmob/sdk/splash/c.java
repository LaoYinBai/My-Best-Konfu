package com.sigmob.sdk.splash;

import android.app.Activity;
import android.view.ViewGroup;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.models.LoadAdRequest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    g a;

    public c(Activity activity, LoadAdRequest loadAdRequest, b bVar) {
        this.a = new g(activity, loadAdRequest, bVar);
    }

    public void a(Activity activity) {
        this.a.a(activity);
    }

    public boolean a() throws Throwable {
        if (this.a != null) {
            this.a.a(false);
            return true;
        }
        SigmobLog.e("loadAd adManager is null");
        return false;
    }

    public boolean a(ViewGroup viewGroup) {
        if (this.a != null) {
            this.a.a(viewGroup);
            return true;
        }
        SigmobLog.e("showAd adManager is null");
        return false;
    }

    public void b(Activity activity) {
        this.a.b(activity);
    }

    public boolean b() {
        if (this.a != null) {
            return this.a.g();
        }
        return false;
    }

    public void c(Activity activity) {
        this.a.c(activity);
    }
}
