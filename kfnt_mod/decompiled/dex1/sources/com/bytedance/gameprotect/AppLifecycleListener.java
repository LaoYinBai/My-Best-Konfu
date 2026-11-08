package com.bytedance.gameprotect;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AppLifecycleListener implements LifecycleObserver {

    static class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ProcessLifecycleOwner.get().getLifecycle().addObserver(new AppLifecycleListener());
        }
    }

    public static void a() {
        k.a(new a());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onMoveToBackground() {
        IlIlllIiI.a(0);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMoveToForeground() {
        IlIlllIiI.a(1);
    }
}
