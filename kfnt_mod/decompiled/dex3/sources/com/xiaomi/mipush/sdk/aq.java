package com.xiaomi.mipush.sdk;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.push.bg;
import com.xiaomi.push.service.bc;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class aq extends ContentObserver {
    final /* synthetic */ an a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    aq(an anVar, Handler handler) {
        super(handler);
        this.a = anVar;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        this.a.f71a = Integer.valueOf(bc.a(this.a.f67a).a());
        if (this.a.f71a.intValue() != 0) {
            this.a.f67a.getContentResolver().unregisterContentObserver(this);
            if (bg.b(this.a.f67a)) {
                this.a.m60c();
            }
        }
    }
}
