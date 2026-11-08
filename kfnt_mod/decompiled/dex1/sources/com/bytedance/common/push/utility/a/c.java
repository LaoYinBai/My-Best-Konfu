package com.bytedance.common.push.utility.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends Handler {

    /* renamed from: a, reason: collision with root package name */
    WeakReference<a> f3826a;

    public interface a {
        void handleMsg(Message message);
    }

    public c(a aVar) {
        this(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper(), aVar);
    }

    public c(Looper looper, a aVar) {
        super(looper);
        this.f3826a = new WeakReference<>(aVar);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        a aVar = this.f3826a.get();
        if (aVar == null || message == null) {
            return;
        }
        aVar.handleMsg(message);
    }
}
