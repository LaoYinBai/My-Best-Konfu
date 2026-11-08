package com.kwad.v8;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class V8Plugins {
    private static volatile ErrorHandler sJSBindingErrorHandler;

    public interface ErrorHandler {
        void onExceptionCaptured(Throwable th);
    }

    static ErrorHandler getJSBindingErrorHandler() {
        return sJSBindingErrorHandler;
    }

    public static void setJSBindingErrorHandler(ErrorHandler errorHandler) {
        sJSBindingErrorHandler = errorHandler;
    }
}
