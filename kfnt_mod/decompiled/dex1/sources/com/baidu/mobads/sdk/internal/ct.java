package com.baidu.mobads.sdk.internal;

import android.os.CountDownTimer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class ct extends CountDownTimer {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ cr f3220a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ct(cr crVar, long j, long j2) {
        super(j, j2);
        this.f3220a = crVar;
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        int i = (int) (j / 1000);
        if (i > 5) {
            i = 5;
        }
        this.f3220a.d.setText(String.valueOf(i));
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        this.f3220a.f3217b.a("CountDownTimer finished");
        this.f3220a.c();
    }
}
