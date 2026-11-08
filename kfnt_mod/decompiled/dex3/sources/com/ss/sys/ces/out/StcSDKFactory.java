package com.ss.sys.ces.out;

import android.content.Context;
import com.ss.sys.ces.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StcSDKFactory {
    private static ISdk a;

    public static ISdk getInstance() {
        return a;
    }

    public static ISdk getSDK(Context context, long j) {
        synchronized (StcSDKFactory.class) {
            if (a == null) {
                a = b.a(context, j, 255);
            }
        }
        return a;
    }

    public static ISdk getSDK(Context context, long j, int i) {
        synchronized (StcSDKFactory.class) {
            if (a == null) {
                a = b.a(context, j, i);
            }
        }
        return a;
    }
}
