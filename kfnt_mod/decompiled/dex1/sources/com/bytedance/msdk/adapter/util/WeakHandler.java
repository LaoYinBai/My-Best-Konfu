package com.bytedance.msdk.adapter.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class WeakHandler extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference<IHandler> f4254a;

    public interface IHandler {
        void handleMsg(Message message);
    }

    public WeakHandler(IHandler iHandler) {
        this.f4254a = new WeakReference<>(iHandler);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        IHandler iHandler = this.f4254a.get();
        if (iHandler == null || message == null) {
            return;
        }
        iHandler.handleMsg(message);
    }

    public WeakHandler(Looper looper, IHandler iHandler) {
        super(looper);
        this.f4254a = new WeakReference<>(iHandler);
    }
}
