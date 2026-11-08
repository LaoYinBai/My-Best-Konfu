package com.unicom.xiaowo.login.d;

import android.os.CountDownTimer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class d extends CountDownTimer {
    private e a;
    private /* synthetic */ a b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(a aVar, e eVar) {
        super(3000L, 1000L);
        this.b = aVar;
        this.a = eVar;
    }

    @Override // android.os.CountDownTimer
    public final void onFinish() {
        if (this.a != null && this.b.d != null) {
            this.a.a(false, null);
        }
        a.a(this.b, (d) null);
    }

    @Override // android.os.CountDownTimer
    public final void onTick(long j) {
    }
}
