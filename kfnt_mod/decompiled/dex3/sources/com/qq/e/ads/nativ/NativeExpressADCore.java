package com.qq.e.ads.nativ;

import android.content.Context;
import com.qq.e.ads.AbstractAD;
import com.qq.e.ads.LiteAbstractAD;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.express2.NativeExpressADData2;
import com.qq.e.comm.pi.NEADI;
import com.qq.e.comm.pi.NEADVI;
import com.qq.e.comm.pi.POFactory;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class NativeExpressADCore extends LiteAbstractAD<NEADVI> {
    private NativeExpressMediaListener i;
    private final NEADI k;
    private final NEADVI l;
    private final NativeExpressADData2 m;
    private ADSize n;
    final JSONObject o;
    final HashMap<String, Object> p;
    private NativeExpressADView q;
    private volatile boolean g = false;
    private volatile boolean h = false;
    private volatile boolean j = false;

    NativeExpressADCore(NativeExpressADView nativeExpressADView, NEADI neadi, NEADVI neadvi, NativeExpressADData2 nativeExpressADData2, Context context, ADSize aDSize, String str, JSONObject jSONObject, HashMap map) {
        this.q = nativeExpressADView;
        this.k = neadi;
        this.l = neadvi;
        this.m = nativeExpressADData2;
        this.n = aDSize;
        this.o = jSONObject;
        this.p = map;
        a(context, str);
    }

    protected NEADVI a(Context context, POFactory pOFactory, String str, String str2) {
        return pOFactory.getNativeExpressADView(this.k, this.l, this.m, context, this.q, this.n, str, str2, this.o, this.p);
    }

    protected /* bridge */ /* synthetic */ Object a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return a(context, pOFactory, str, str2);
    }

    void a(NativeExpressMediaListener nativeExpressMediaListener) {
        this.i = nativeExpressMediaListener;
        Object obj = ((AbstractAD) this).a;
        if (obj == null || nativeExpressMediaListener == null) {
            return;
        }
        ((NEADVI) obj).setAdListener(new NativeExpressAD.ADListenerAdapter(nativeExpressMediaListener));
    }

    protected /* bridge */ /* synthetic */ void a(Object obj) {
        d();
    }

    protected void b(int i) {
    }

    protected void d() {
        NativeExpressMediaListener nativeExpressMediaListener = this.i;
        if (nativeExpressMediaListener != null) {
            this.i = nativeExpressMediaListener;
            Object obj = ((AbstractAD) this).a;
            if (obj != null && nativeExpressMediaListener != null) {
                ((NEADVI) obj).setAdListener(new NativeExpressAD.ADListenerAdapter(nativeExpressMediaListener));
            }
        }
        if (this.g) {
            preloadVideo();
        }
        if (this.h) {
            render();
        }
        if (this.j) {
            negativeFeedback();
        }
    }

    void destroy() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NEADVI) obj).destroy();
        }
    }

    void negativeFeedback() {
        boolean z;
        if (b()) {
            Object obj = ((AbstractAD) this).a;
            if (obj == null) {
                a("negativeFeedback");
                return;
            } else {
                ((NEADVI) obj).reportAdNegative();
                z = false;
            }
        } else {
            z = true;
        }
        this.j = z;
    }

    void preloadVideo() {
        if (!b()) {
            this.g = true;
            return;
        }
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NEADVI) obj).preloadVideo();
        } else {
            a("preloadVideo");
        }
    }

    void render() {
        if (!b()) {
            this.h = true;
            return;
        }
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NEADVI) obj).render();
        } else {
            a("render");
        }
    }

    @Deprecated
    void setAdSize(ADSize aDSize) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NEADVI) obj).setAdSize(aDSize);
        }
    }
}
