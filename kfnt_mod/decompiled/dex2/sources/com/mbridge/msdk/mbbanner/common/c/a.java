package com.mbridge.msdk.mbbanner.common.c;

import android.content.Context;
import android.os.Handler;
import com.mbridge.msdk.b.d;
import com.mbridge.msdk.foundation.tools.s;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: BannerLoadManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "a";
    private static volatile a h;
    private Context b = com.mbridge.msdk.foundation.controller.a.f().j();
    private com.mbridge.msdk.mbbanner.common.util.a c = new com.mbridge.msdk.mbbanner.common.util.a();
    private Map<String, com.mbridge.msdk.mbbanner.common.a.c> d = new ConcurrentHashMap();
    private Map<String, Boolean> e = new ConcurrentHashMap();
    private Map<String, Handler> f = new ConcurrentHashMap();
    private Map<String, Integer> g = new ConcurrentHashMap();

    public static a a() {
        if (h == null) {
            synchronized (a.class) {
                if (h == null) {
                    h = new a();
                }
            }
        }
        return h;
    }

    private a() {
    }

    public final void a(String str, String str2, final com.mbridge.msdk.mbbanner.common.a.b bVar, com.mbridge.msdk.mbbanner.common.b.b bVar2) {
        com.mbridge.msdk.mbbanner.common.a.c cVar;
        if (this.b == null) {
            this.c.a(bVar2, "Banner Context == null!", str2);
            return;
        }
        if (bVar == null || bVar2 == null) {
            this.c.a(bVar2, "Banner request parameters or callback empty!", str2);
            return;
        }
        synchronized (a()) {
            if (this.e != null && this.e.containsKey(str2) && this.e.get(str2).booleanValue()) {
                this.c.a(bVar2, "Current unit is loading!", str2);
                return;
            }
            this.e.put(str2, true);
            if (this.d.containsKey(str2)) {
                cVar = this.d.get(str2);
            } else {
                d dVarF = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), str2);
                if (dVarF == null) {
                    dVarF = d.d(str2);
                }
                com.mbridge.msdk.mbbanner.common.a.c cVar2 = new com.mbridge.msdk.mbbanner.common.a.c(str2, "", 0, dVarF.x() * 1);
                this.d.put(str2, cVar2);
                cVar = cVar2;
            }
            new b(this.b, cVar, bVar2, this.c).a(str, str2, bVar, new com.mbridge.msdk.mbbanner.common.b.d() { // from class: com.mbridge.msdk.mbbanner.common.c.a.1
                @Override // com.mbridge.msdk.mbbanner.common.b.d
                public final void a(String str3) {
                    synchronized (a.a()) {
                        bVar.a("");
                        a.this.e.put(str3, false);
                    }
                }
            });
        }
    }

    public final void b(final String str, final String str2, final com.mbridge.msdk.mbbanner.common.a.b bVar, final com.mbridge.msdk.mbbanner.common.b.b bVar2) {
        Handler handler;
        if (bVar == null || bVar.b() <= 0) {
            s.d(a, "doUnitRotation: Illegal banner request parameters! && unitId=" + str2);
            return;
        }
        if (this.f.containsKey(str2)) {
            handler = this.f.get(str2);
        } else {
            handler = new Handler();
            this.f.put(str2, handler);
        }
        Runnable runnable = new Runnable() { // from class: com.mbridge.msdk.mbbanner.common.c.a.2
            @Override // java.lang.Runnable
            public final void run() {
                int iIntValue;
                if (a.this.e != null && a.this.e.containsKey(str2) && ((Boolean) a.this.e.get(str2)).booleanValue()) {
                    return;
                }
                if (a.this.g.containsKey(str2) && ((iIntValue = ((Integer) a.this.g.get(str2)).intValue()) == 2 || iIntValue == 4)) {
                    s.d(a.a, "doUnitRotation: autoRotationStatus=" + iIntValue + " && unitId=" + str2);
                    if (a.this.c != null) {
                        a.this.c.a(bVar2, "banner load failed because env is exception", str2);
                        return;
                    }
                    return;
                }
                a.this.a(str, str2, bVar, bVar2);
            }
        };
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(runnable, bVar.b());
    }

    public final void a(String str) {
        if (this.f.containsKey(str)) {
            this.f.get(str).removeCallbacksAndMessages(null);
            this.f.remove(str);
        }
    }

    public final void a(int i, String str, String str2, com.mbridge.msdk.mbbanner.common.a.b bVar, com.mbridge.msdk.mbbanner.common.b.b bVar2) {
        int iIntValue = this.g.containsKey(str2) ? this.g.get(str2).intValue() : 0;
        switch (i) {
            case 1:
                if (this.f.containsKey(str2)) {
                    this.f.get(str2).removeCallbacksAndMessages(null);
                }
                this.g.put(str2, Integer.valueOf(i));
                break;
            case 2:
                if (iIntValue == 1) {
                    if (this.f.containsKey(str2)) {
                        this.f.get(str2).removeCallbacksAndMessages(null);
                    }
                    this.g.put(str2, Integer.valueOf(i));
                    break;
                }
                break;
            case 3:
                if (iIntValue == 2 || iIntValue == 4) {
                    this.g.put(str2, 1);
                    b(str, str2, bVar, bVar2);
                    break;
                }
                break;
            case 4:
                if (iIntValue == 0) {
                    this.g.put(str2, 0);
                    break;
                } else {
                    if (this.f.containsKey(str2)) {
                        this.f.get(str2).removeCallbacksAndMessages(null);
                    }
                    this.g.put(str2, Integer.valueOf(i));
                    break;
                }
        }
    }

    public final void b() {
        if (this.d != null) {
            this.d.clear();
        }
        if (this.e != null) {
            this.e.clear();
        }
        if (this.f != null) {
            for (Map.Entry<String, Handler> entry : this.f.entrySet()) {
                if (entry.getValue() != null) {
                    entry.getValue().removeCallbacksAndMessages(null);
                }
            }
            this.f.clear();
        }
        if (this.g != null) {
            this.g.clear();
        }
    }
}
