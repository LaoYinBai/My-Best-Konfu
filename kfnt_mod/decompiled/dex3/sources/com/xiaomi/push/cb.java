package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.ci;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class cb implements Runnable {
    private String a;

    /* renamed from: a, reason: collision with other field name */
    private WeakReference<Context> f177a;

    public cb(String str, WeakReference<Context> weakReference) {
        this.a = str;
        this.f177a = weakReference;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        if (this.f177a == null || (context = this.f177a.get()) == null) {
            return;
        }
        if (co.a(this.a) <= ca.f175a) {
            com.xiaomi.channel.commonutils.logger.b.b("=====> do not need clean db");
            return;
        }
        ce ceVarA = ce.a(this.a);
        cd cdVarA = cd.a(this.a);
        ceVarA.a(cdVarA);
        cdVarA.a(cc.a(context, this.a, 1000));
        ci.a(context).a((ci.a) ceVarA);
    }
}
