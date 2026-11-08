package com.xiaomi.push;

import android.util.Log;
import android.util.Pair;
import java.util.Date;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class di implements Runnable {
    final /* synthetic */ dh a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f241a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ Throwable f242a;

    di(dh dhVar, String str, Throwable th) {
        this.a = dhVar;
        this.f241a = str;
        this.f242a = th;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        dh.f238a.add(new Pair(String.format("%1$s %2$s %3$s ", dh.f237a.format(new Date()), this.a.b, this.f241a), this.f242a));
        if (dh.f238a.size() > 20000) {
            int size = (dh.f238a.size() - 20000) + 50;
            for (int i = 0; i < size; i++) {
                try {
                    if (dh.f238a.size() > 0) {
                        dh.f238a.remove(0);
                    }
                } catch (IndexOutOfBoundsException unused) {
                }
            }
            dh.f238a.add(new Pair(String.format("%1$s %2$s %3$s ", dh.f237a.format(new Date()), this.a.b, "flush " + size + " lines logs."), null));
        }
        try {
            if (aa.d()) {
                this.a.m199a();
            } else {
                Log.w(this.a.b, "SDCard is unavailable.");
            }
        } catch (Exception e) {
            Log.e(this.a.b, "", e);
        }
    }
}
