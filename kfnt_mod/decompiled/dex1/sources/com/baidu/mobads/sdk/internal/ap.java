package com.baidu.mobads.sdk.internal;

import android.util.Log;
import java.lang.Thread;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class ap implements Thread.UncaughtExceptionHandler {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ ao f3123a;

    ap(ao aoVar) {
        this.f3123a = aoVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Log.i("ThreadPoolFactory", "线程名字=" + thread.getName() + "线程crash信息", th);
    }
}
