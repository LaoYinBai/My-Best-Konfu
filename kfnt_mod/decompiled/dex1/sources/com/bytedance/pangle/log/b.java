package com.bytedance.pangle.log;

import com.bytedance.framwork.core.sdkmonitor.SDKMonitor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b implements SDKMonitor.ICallback {

    /* renamed from: a, reason: collision with root package name */
    String f4649a;

    public b(String str) {
        this.f4649a = str;
    }

    public final void callback(boolean z) {
        ZeusLogger.d("FlushBufferCallback", this.f4649a + z);
    }
}
