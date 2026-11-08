package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.IOAdEventListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class aa implements IOAdEventListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ y f3092a;

    aa(y yVar) {
        this.f3092a = yVar;
    }

    @Override // com.baidu.mobads.sdk.api.IOAdEventListener
    public void run(IOAdEvent iOAdEvent) {
        if (iOAdEvent != null) {
            this.f3092a.w();
        }
    }
}
