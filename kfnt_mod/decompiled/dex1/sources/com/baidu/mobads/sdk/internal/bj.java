package com.baidu.mobads.sdk.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.mobads.sdk.internal.bh;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class bj extends Handler {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ bh f3165a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    bj(bh bhVar, Looper looper) {
        super(looper);
        this.f3165a = bhVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        String string = message.getData().getString(bh.n);
        bf bfVar = (bf) message.getData().getParcelable(bh.m);
        if (!bh.k.equals(string)) {
            this.f3165a.z.a(bh.f3160a, "mOnApkDownloadCompleted: download failed, code: " + string);
            this.f3165a.a(false);
            if (this.f3165a.A) {
                this.f3165a.A = false;
                this.f3165a.a(false, "Refused to download remote for version...");
                return;
            }
            return;
        }
        ba baVar = new ba(bfVar.e(), this.f3165a.y, bfVar);
        try {
            try {
                if (this.f3165a.u != bh.t) {
                    this.f3165a.a(baVar);
                    bh bhVar = this.f3165a;
                    baVar.a(bh.f());
                    this.f3165a.a(true);
                } else {
                    baVar.a();
                    baVar.a(bh.f());
                    if (bh.p != null) {
                        bh.p.f3137b = bfVar.b();
                    }
                    this.f3165a.l();
                    if (this.f3165a.A) {
                        this.f3165a.A = false;
                        this.f3165a.a(this.f3165a.p(), "load remote file just downloaded");
                    }
                }
            } catch (bh.a e) {
                String str = "download apk file failed: " + e.toString();
                this.f3165a.a(false);
                this.f3165a.z.a(bh.f3160a, str);
            }
        } finally {
            baVar.delete();
        }
    }
}
