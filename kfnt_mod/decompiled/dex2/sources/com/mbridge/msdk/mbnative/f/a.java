package com.mbridge.msdk.mbnative.f;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.mbridge.msdk.foundation.tools.q;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbnative.c.b;
import com.mbridge.msdk.mbnative.c.c;
import com.mbridge.msdk.out.AdMobClickListener;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.NativeListener;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* compiled from: NativeProvider.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private b a;
    private Handler b = new Handler(Looper.getMainLooper());
    private com.mbridge.msdk.mbnative.d.a c;
    private NativeListener.NativeTrackingListener d;

    public final void a(com.mbridge.msdk.mbnative.d.a aVar) {
        this.c = aVar;
    }

    public final void a(NativeListener.NativeTrackingListener nativeTrackingListener) {
        this.d = nativeTrackingListener;
    }

    public a() {
    }

    public a(com.mbridge.msdk.mbnative.d.a aVar, NativeListener.NativeTrackingListener nativeTrackingListener) {
        this.c = aVar;
        this.d = nativeTrackingListener;
    }

    public final void a(Context context, Resources resources, Map<String, Object> map) {
        this.a = new b(this.c, this.d, map, context);
    }

    public final void a() throws JSONException {
        a(0, "");
    }

    public final void a(String str) throws JSONException {
        a(0, str);
    }

    public final void b() throws JSONException {
        a(1, "");
    }

    public final void c() {
        try {
            b bVar = this.a;
            q.a();
        } catch (Exception unused) {
            s.d("NativeProvider", "clear cache failed");
        }
    }

    public final void d() {
        try {
            this.a.a();
        } catch (Exception unused) {
            s.d("NativeProvider", "release failed");
        }
    }

    public final void a(View view, List<View> list, Campaign campaign) {
        s.b("NativeProvider", "native provider registerView");
        if (this.a == null) {
            return;
        }
        this.a.a(campaign, view, list);
    }

    public final void a(View view, Campaign campaign) {
        s.b("NativeProvider", "native provider registerView");
        if (this.a == null) {
            return;
        }
        this.a.a(campaign, view);
    }

    public final void b(View view, Campaign campaign) {
        s.b("NativeProvider", "native provider unregisterView");
        if (this.a == null) {
            return;
        }
        this.a.b(campaign, view);
    }

    public final void b(View view, List<View> list, Campaign campaign) {
        s.b("NativeProvider", "native provider unregisterView");
        if (this.a == null) {
            return;
        }
        this.a.b(campaign, view, list);
    }

    public static void preload(Map<String, Object> map, int i, AdMobClickListener adMobClickListener) {
        s.b("NativeProvider", "native provider preload");
        new c().a(map, i, adMobClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.c.onAdLoadError("current request is loading");
        this.c.b();
    }

    private void a(int i, String str) throws JSONException {
        if (this.a != null) {
            if (this.c != null && this.c.a()) {
                if (w.b()) {
                    f();
                    return;
                } else {
                    this.b.post(new Runnable() { // from class: com.mbridge.msdk.mbnative.f.a.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            a.this.f();
                        }
                    });
                    return;
                }
            }
            if (this.c != null) {
                this.c.b();
            }
            this.a.a(i, str);
        }
    }

    public final String e() {
        return this.a != null ? this.a.b() : "";
    }
}
