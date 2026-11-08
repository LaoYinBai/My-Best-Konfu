package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.IOAdEventListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class cd implements IOAdEventListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ cb f3203a;

    cd(cb cbVar) {
        this.f3203a = cbVar;
    }

    @Override // com.baidu.mobads.sdk.api.IOAdEventListener
    public void run(IOAdEvent iOAdEvent) {
        if (iOAdEvent != null) {
            this.f3203a.h();
        }
    }
}
