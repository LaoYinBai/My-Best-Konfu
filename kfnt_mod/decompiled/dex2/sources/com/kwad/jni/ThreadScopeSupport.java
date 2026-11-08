package com.kwad.jni;

import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ThreadScopeSupport {
    static {
        System.loadLibrary("kwad-fb");
    }

    @Keep
    private static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }

    private static native void runStdFunctionImpl(long j);
}
