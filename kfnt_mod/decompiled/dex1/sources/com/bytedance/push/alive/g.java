package com.bytedance.push.alive;

import android.content.Context;
import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g implements d {
    public String toString() {
        return "push daemon";
    }

    @Override // com.bytedance.push.alive.d
    public void a(Context context) {
        com.ss.android.push.daemon.c.a(context).a();
        com.bytedance.push.utils.e.a("PushAlive", "start push demon success");
    }

    @Override // com.bytedance.push.alive.d
    public boolean b(Context context) {
        return Build.VERSION.SDK_INT >= 21 && (com.ss.android.message.a.b.e(context) || com.ss.android.message.a.b.f(context));
    }
}
