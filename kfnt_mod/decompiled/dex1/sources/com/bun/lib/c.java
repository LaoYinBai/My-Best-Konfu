package com.bun.lib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface c extends IInterface {

    @Keep
    public static abstract class a extends Binder implements c {

        @Keep
        /* renamed from: com.bun.lib.c$a$a, reason: collision with other inner class name */
        private static class C0089a implements c {

            /* renamed from: a, reason: collision with root package name */
            @Keep
            private IBinder f3261a;

            @Keep
            C0089a(IBinder iBinder) {
                this.f3261a = iBinder;
            }

            @Override // android.os.IInterface
            @Keep
            public native IBinder asBinder();

            @Override // com.bun.lib.c
            @Keep
            public native boolean c();

            @Override // com.bun.lib.c
            @Keep
            public native String getAAID();

            @Override // com.bun.lib.c
            @Keep
            public native String getOAID();

            @Override // com.bun.lib.c
            @Keep
            public native String getVAID();

            @Override // com.bun.lib.c
            @Keep
            public native boolean isSupported();

            @Override // com.bun.lib.c
            @Keep
            public native void shutDown();
        }

        @Keep
        public static native c a(IBinder iBinder);
    }

    @Keep
    boolean c();

    @Keep
    String getAAID();

    @Keep
    String getOAID();

    @Keep
    String getVAID();

    @Keep
    boolean isSupported();

    @Keep
    void shutDown();
}
