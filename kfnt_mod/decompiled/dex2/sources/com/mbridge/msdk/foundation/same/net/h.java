package com.mbridge.msdk.foundation.same.net;

import android.content.Context;
import android.os.Process;
import com.mbridge.msdk.foundation.tools.s;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: NetworkDispatcher.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private static final String a = "h";
    private com.mbridge.msdk.foundation.same.net.e.a b = new com.mbridge.msdk.foundation.same.net.e.c();
    private g c;
    private c d;

    public h(Context context, SSLSocketFactory sSLSocketFactory, c cVar) {
        this.c = new com.mbridge.msdk.foundation.same.net.f.a(this.b, cVar);
        this.d = cVar;
    }

    public final void a(i iVar) {
        Process.setThreadPriority(10);
        try {
            s.b(a, "network-queue-take request=" + iVar.b());
            this.d.c(iVar);
            if (iVar.c()) {
                iVar.a("network-discard-cancelled");
                this.d.b(iVar);
                this.d.a(iVar);
            } else {
                this.d.d(iVar);
                com.mbridge.msdk.foundation.same.net.f.c cVarA = this.c.a(iVar);
                s.b(a, "network-http-complete networkResponse=" + cVarA.a);
                k<?> kVarA = iVar.a(cVarA);
                s.b(a, "network-parse-complete response=" + kVarA.a);
                this.d.a((i<?>) iVar, kVarA);
            }
        } catch (com.mbridge.msdk.foundation.same.net.a.a e) {
            this.d.a((i<?>) iVar, e);
        } catch (Exception e2) {
            s.d(a, "Unhandled exception " + e2.getMessage());
            this.d.a((i<?>) iVar, new com.mbridge.msdk.foundation.same.net.a.a(4, null));
        }
    }
}
