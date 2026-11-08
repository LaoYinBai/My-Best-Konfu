package com.baidu.mobads.sdk.internal;

import android.app.Activity;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class ck implements ViewTreeObserver.OnWindowFocusChangeListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ RelativeLayout f3211a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ cg f3212b;

    ck(cg cgVar, RelativeLayout relativeLayout) {
        this.f3212b = cgVar;
        this.f3211a = relativeLayout;
    }

    @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
    public void onWindowFocusChanged(boolean z) {
        if (z) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("novel_activity", (Activity) this.f3212b.g);
            map.put("banner_container", this.f3211a);
            map.put("entry", Integer.valueOf(this.f3212b.C));
            map.put("channelId", Integer.valueOf(this.f3212b.D));
            map.put("novel_id", this.f3212b.E);
            this.f3212b.a(cg.t, map);
        }
    }
}
