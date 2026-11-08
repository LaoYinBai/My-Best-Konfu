package com.ss.android.socialbase.downloader.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WeakDownloadHandler extends Handler {
    private final WeakReference<IHandler> mRef;

    public interface IHandler {
        void handleMsg(Message message);
    }

    public WeakDownloadHandler(IHandler iHandler) {
        this.mRef = new WeakReference<>(iHandler);
    }

    public WeakDownloadHandler(Looper looper, IHandler iHandler) {
        super(looper);
        this.mRef = new WeakReference<>(iHandler);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        IHandler iHandler = this.mRef.get();
        if (iHandler == null || message == null) {
            return;
        }
        iHandler.handleMsg(message);
    }
}
