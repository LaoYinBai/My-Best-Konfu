package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.IOAdEventListener;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class cc implements IOAdEventListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ cb f3202a;

    cc(cb cbVar) {
        this.f3202a = cbVar;
    }

    @Override // com.baidu.mobads.sdk.api.IOAdEventListener
    public void run(IOAdEvent iOAdEvent) {
        if (iOAdEvent != null) {
            this.f3202a.c((Map<String, Object>) iOAdEvent.getData());
        }
    }
}
