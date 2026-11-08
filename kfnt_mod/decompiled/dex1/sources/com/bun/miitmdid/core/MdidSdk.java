package com.bun.miitmdid.core;

import android.content.Context;
import androidx.annotation.Keep;
import com.bun.miitmdid.a.b;
import com.bun.supplier.IIdentifierListener;
import com.bun.supplier.IdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MdidSdk implements SupplierListener {

    @Keep
    private IIdentifierListener _InnerListener;

    @Keep
    private b _setting;

    @Keep
    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        @Keep
        static final /* synthetic */ int[] f3319a = new int[com.bun.miitmdid.c.a.values().length];

        static {
            try {
                f3319a[com.bun.miitmdid.c.a.XIAOMI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.BLACKSHARK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.VIVO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.HUA_WEI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.OPPO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.ONEPLUS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.MOTO.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.LENOVO.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.ASUS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.SAMSUNG.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.MEIZU.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.NUBIA.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.ZTE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.FREEMEOS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f3319a[com.bun.miitmdid.c.a.SSUIOS.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    @Keep
    public MdidSdk() {
        try {
            com.bun.lib.a.a(true);
        } catch (Exception e) {
            com.bun.lib.a.b("mdidsdk", "extractor exception!", e);
        }
    }

    @Keep
    public MdidSdk(boolean z) {
        try {
            com.bun.lib.a.a(z);
        } catch (Exception e) {
            com.bun.lib.a.b("mdidsdk", "extractor exception!", e);
        }
    }

    @Keep
    private native int _InnerFailed(int i, IdSupplier idSupplier);

    @Keep
    public native int InitSdk(Context context, IIdentifierListener iIdentifierListener);

    @Override // com.bun.supplier.SupplierListener
    @Keep
    public native void OnSupport(boolean z, IdSupplier idSupplier);

    @Keep
    public native void UnInitSdk();
}
