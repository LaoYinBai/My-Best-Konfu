package com.bytedance.gameprotect;

import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MethodCallsLogger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AppLifecycleListener_LifecycleAdapter implements GeneratedAdapter {

    /* renamed from: a, reason: collision with root package name */
    final AppLifecycleListener f3860a;

    AppLifecycleListener_LifecycleAdapter(AppLifecycleListener appLifecycleListener) {
        this.f3860a = appLifecycleListener;
    }

    @Override // androidx.lifecycle.GeneratedAdapter
    public void callMethods(LifecycleOwner lifecycleOwner, Lifecycle.Event event, boolean z, MethodCallsLogger methodCallsLogger) {
        boolean z2 = methodCallsLogger != null;
        if (z) {
            return;
        }
        if (event == Lifecycle.Event.ON_START) {
            if (!z2 || methodCallsLogger.approveCall("onMoveToForeground", 1)) {
                this.f3860a.onMoveToForeground();
                return;
            }
            return;
        }
        if (event == Lifecycle.Event.ON_STOP) {
            if (!z2 || methodCallsLogger.approveCall("onMoveToBackground", 1)) {
                this.f3860a.onMoveToBackground();
            }
        }
    }
}
