package com.tachikoma.core.api;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ITKExceptionHandlerInner {
    void handleException(Throwable th, int i);

    void handleJSException(Throwable th, int i);

    void handleJSException(Throwable th, String str, int i);
}
