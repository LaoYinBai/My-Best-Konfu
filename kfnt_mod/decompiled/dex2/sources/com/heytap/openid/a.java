package com.heytap.openid;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface a extends IInterface {

    @Keep
    /* renamed from: com.heytap.openid.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0000a extends Binder implements a {

        @Keep
        /* renamed from: com.heytap.openid.a$a$a, reason: collision with other inner class name */
        private static class C0001a implements a {

            @Keep
            public IBinder a;

            @Keep
            public C0001a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Keep
            public native String a(String str, String str2, String str3);

            @Override // android.os.IInterface
            @Keep
            public native IBinder asBinder();
        }

        @Keep
        public static native a a(IBinder iBinder);
    }
}
