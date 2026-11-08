package com.yxcorp.kuaishou.addfp.android.b;

import android.os.Build;
import android.os.Parcel;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class h {
    private final String a = "iphonesubinfo";
    private /* synthetic */ f b;

    public h(f fVar) {
        this.b = fVar;
    }

    private String a(String str, String str2) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        String string = "";
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        String strA = this.b.a(str2);
        if (TextUtils.isEmpty(strA)) {
            return "";
        }
        parcelObtain.writeInterfaceToken(strA);
        if (Build.VERSION.SDK_INT >= 23) {
            parcelObtain.writeString(f.b.getPackageName());
        }
        if (this.b.a(str2, str, parcelObtain, parcelObtain2) != null) {
            try {
                parcelObtain2.readException();
                string = parcelObtain2.readString();
            } catch (SecurityException unused) {
                return "";
            }
        }
        parcelObtain2.recycle();
        parcelObtain.recycle();
        return string;
    }

    public final String a() {
        return a("TRANSACTION_getDeviceId", "iphonesubinfo");
    }

    public final String b() {
        return a("TRANSACTION_getIccSerialNumber", "iphonesubinfo");
    }
}
