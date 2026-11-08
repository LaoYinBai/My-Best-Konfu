package com.kwad.sdk.utils;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bc extends Handler {
    private WeakReference<a> a;

    public interface a {
        void a(Message message);
    }

    public bc(a aVar) {
        this.a = new WeakReference<>(aVar);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        a aVar;
        try {
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
        if (this.a == null || (aVar = this.a.get()) == null) {
            return;
        }
        aVar.a(message);
        super.handleMessage(message);
    }
}
