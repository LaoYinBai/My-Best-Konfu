package com.mbridge.msdk.pluginFramework;

import android.content.Intent;
import android.os.IBinder;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbdownload.c;

/* compiled from: PluginServiceAgent.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public C0134a a;

    public a(C0134a c0134a) {
        this.a = c0134a;
    }

    /* compiled from: PluginServiceAgent.java */
    /* renamed from: com.mbridge.msdk.pluginFramework.a$a, reason: collision with other inner class name */
    public static class C0134a {
        c a;

        public C0134a(c cVar) {
            this.a = cVar;
        }

        public final IBinder a(Intent intent) {
            try {
                return this.a.a(intent);
            } catch (Exception e) {
                s.b("PluginServiceContext", "invoke onBind error", e);
                return null;
            }
        }

        public final int a(Intent intent, int i, int i2) {
            try {
                return this.a.a(intent, i, i2);
            } catch (Exception e) {
                s.b("PluginServiceContext", "invoke onStartCommand error", e);
                return 0;
            }
        }

        public final void a() {
            try {
                this.a.a();
            } catch (Exception e) {
                s.b("PluginServiceContext", "invoke onCreate error", e);
            }
        }

        public final void b() {
            try {
                this.a.b();
            } catch (Exception e) {
                s.b("PluginServiceContext", "invoke onDestroy error", e);
            }
        }
    }
}
