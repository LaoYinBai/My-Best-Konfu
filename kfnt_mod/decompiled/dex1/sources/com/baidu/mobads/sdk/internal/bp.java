package com.baidu.mobads.sdk.internal;

import android.content.SharedPreferences;
import android.os.Handler;
import com.baidu.mobads.sdk.internal.bh;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class bp extends h {

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ bh.c f3174b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Handler f3175c;
    final /* synthetic */ bh d;

    bp(bh bhVar, bh.c cVar, Handler handler) {
        this.d = bhVar;
        this.f3174b = cVar;
        this.f3175c = handler;
    }

    @Override // com.baidu.mobads.sdk.internal.h
    protected Object i() {
        try {
            try {
                try {
                    synchronized (bh.class) {
                        this.d.b(this.f3174b, this.f3175c);
                    }
                    SharedPreferences.Editor editorEdit = this.d.m().edit();
                    editorEdit.putString(bh.d, this.d.a());
                    if (av.a(this.d.y).a() >= 9) {
                        editorEdit.apply();
                        return null;
                    }
                    editorEdit.commit();
                    return null;
                } catch (Throwable th) {
                    this.d.z.a(bh.f3160a, "Load APK Failed: " + th.toString());
                    this.d.b(false);
                    SharedPreferences.Editor editorEdit2 = this.d.m().edit();
                    editorEdit2.putString(bh.d, this.d.a());
                    if (av.a(this.d.y).a() >= 9) {
                        editorEdit2.apply();
                        return null;
                    }
                    editorEdit2.commit();
                    return null;
                }
            } catch (Throwable th2) {
                try {
                    SharedPreferences.Editor editorEdit3 = this.d.m().edit();
                    editorEdit3.putString(bh.d, this.d.a());
                    if (av.a(this.d.y).a() >= 9) {
                        editorEdit3.apply();
                    } else {
                        editorEdit3.commit();
                    }
                } catch (Throwable th3) {
                    this.d.z.a(bh.f3160a, th3);
                }
                throw th2;
            }
        } catch (Throwable th4) {
            this.d.z.a(bh.f3160a, th4);
            return null;
        }
    }
}
