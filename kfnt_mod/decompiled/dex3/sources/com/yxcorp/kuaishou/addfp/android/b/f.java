package com.yxcorp.kuaishou.addfp.android.b;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import java.lang.reflect.Field;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class f {
    private static Context b;
    public h a;
    private final Class c;
    private i d;
    private String[] e;
    private String[] f;

    private f() {
        this.e = null;
        this.f = null;
        this.c = b();
        this.a = new h(this);
        this.d = new i(this);
    }

    /* synthetic */ f(byte b2) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Parcel a(String str, String str2, Parcel parcel, Parcel parcel2) {
        Object objInvoke;
        int i;
        String strA = a(str);
        if (strA != null && str2 != null) {
            try {
                objInvoke = this.c.getDeclaredMethod("getService", String.class).invoke(null, str);
                try {
                    Field declaredField = Class.forName(strA + "$Stub").getDeclaredField(str2);
                    declaredField.setAccessible(true);
                    i = declaredField.getInt(null);
                } catch (Throwable th) {
                    th = th;
                    th.printStackTrace();
                    i = -1;
                    if (objInvoke != null) {
                        try {
                            ((IBinder) objInvoke).transact(i, parcel, parcel2, 0);
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                        return parcel2;
                    }
                    return null;
                }
            } catch (Throwable th3) {
                th = th3;
                objInvoke = null;
            }
            if (objInvoke != null && i >= 0) {
                ((IBinder) objInvoke).transact(i, parcel, parcel2, 0);
                return parcel2;
            }
        }
        return null;
    }

    public static f a(Context context) {
        b = context;
        return g.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        String string;
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                ((IBinder) this.c.getDeclaredMethod("getService", String.class).invoke(null, str)).transact(1598968902, parcelObtain, parcelObtain2, 0);
                string = parcelObtain2.readString();
            } catch (Throwable th) {
                th.printStackTrace();
                parcelObtain.recycle();
                parcelObtain2.recycle();
                string = null;
            }
            return string;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    private static Class b() {
        try {
            return Class.forName("android.os.ServiceManager");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
