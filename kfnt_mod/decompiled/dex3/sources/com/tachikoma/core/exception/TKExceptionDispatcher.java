package com.tachikoma.core.exception;

import com.tachikoma.core.api.ITKExceptionHandlerInner;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.utility.TKEnv;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKExceptionDispatcher {
    private static ITKExceptionHandlerInner sExceptionHandler;

    public static void dispatchException(Throwable th, int i) {
        if (sExceptionHandler == null || th == null) {
            return;
        }
        sExceptionHandler.handleException(th, i);
    }

    public static void dispatchJSException(Throwable th, int i) {
        if (sExceptionHandler == null || th == null) {
            return;
        }
        sExceptionHandler.handleJSException(th, i);
    }

    public static void dispatchJSException(Throwable th, String str, int i) {
        if (sExceptionHandler == null || th == null) {
            return;
        }
        sExceptionHandler.handleJSException(th, str, i);
    }

    public static void reportException(TKJSContext tKJSContext, Throwable th) {
        if (th == null) {
            return;
        }
        if (TKEnv.isDebug(tKJSContext)) {
            throw new RuntimeException("reportException", th);
        }
        dispatchJSException(th, tKJSContext != null ? tKJSContext.hashCode() : -1);
    }

    public static void setExceptionHandler(ITKExceptionHandlerInner iTKExceptionHandlerInner) {
        sExceptionHandler = iTKExceptionHandlerInner;
    }
}
