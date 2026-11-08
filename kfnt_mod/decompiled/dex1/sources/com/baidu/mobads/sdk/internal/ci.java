package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.IOAdEventListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class ci implements IOAdEventListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ cg f3209a;

    ci(cg cgVar) {
        this.f3209a = cgVar;
    }

    @Override // com.baidu.mobads.sdk.api.IOAdEventListener
    public void run(IOAdEvent iOAdEvent) {
        if (iOAdEvent != null) {
            this.f3209a.w();
        }
    }
}
