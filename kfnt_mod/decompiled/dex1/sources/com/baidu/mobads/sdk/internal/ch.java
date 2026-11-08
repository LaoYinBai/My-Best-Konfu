package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.IOAdEventListener;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class ch implements IOAdEventListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ cg f3208a;

    ch(cg cgVar) {
        this.f3208a = cgVar;
    }

    @Override // com.baidu.mobads.sdk.api.IOAdEventListener
    public void run(IOAdEvent iOAdEvent) {
        if (iOAdEvent != null) {
            this.f3208a.c((Map<String, Object>) iOAdEvent.getData());
        }
    }
}
