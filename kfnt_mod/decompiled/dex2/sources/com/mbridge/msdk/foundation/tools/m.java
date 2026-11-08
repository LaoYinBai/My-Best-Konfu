package com.mbridge.msdk.foundation.tools;

import android.content.Context;

/* compiled from: ResourceUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class m {
    public static int a(Context context, String str, String str2) {
        String packageName = "";
        try {
            try {
                packageName = com.mbridge.msdk.foundation.controller.a.f().d();
            } catch (Exception unused) {
                s.d("ResourceUtil", "MBSDKContext.getInstance() is null resName:" + str);
            }
            if (z.a(packageName) && context != null) {
                packageName = context.getPackageName();
            }
            if (z.a(packageName) || context == null) {
                return -1;
            }
            return context.getResources().getIdentifier(str, str2, packageName);
        } catch (Exception unused2) {
            s.d("ResourceUtil", "Resource not found resName:" + str);
            return -1;
        }
    }
}
