package com.huawei.a.a.b.k;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.UserManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    private static g a = new g();
    private boolean b = false;
    private Context c = com.huawei.a.a.a.b.j();

    private g() {
    }

    public static g a() {
        return a;
    }

    @TargetApi(24)
    public boolean b() {
        boolean zIsUserUnlocked;
        if (!this.b) {
            if (this.c == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                UserManager userManager = (UserManager) this.c.getSystemService("user");
                if (userManager != null) {
                    zIsUserUnlocked = userManager.isUserUnlocked();
                } else {
                    this.b = false;
                }
            } else {
                zIsUserUnlocked = true;
            }
            this.b = zIsUserUnlocked;
        }
        return this.b;
    }
}
