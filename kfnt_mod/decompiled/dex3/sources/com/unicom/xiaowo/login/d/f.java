package com.unicom.xiaowo.login.d;

import android.os.Environment;
import android.util.Log;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class f {
    private static boolean a;

    public static void a() {
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                if (new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/mmd.xv1").exists()) {
                    a = true;
                }
            }
        } catch (Exception unused) {
        }
    }

    public static void a(Exception exc) {
        if (a) {
            exc.printStackTrace();
        }
    }

    public static void a(String str) {
        if (a) {
            Log.i("UniAccount", "3.1.0AL02B0307 " + str);
        }
    }

    public static void b(String str) {
        if (a) {
            Log.e("UniAccount", "3.1.0AL02B0307" + str);
        }
    }

    public static void c(String str) {
        Log.e("UniAccount", "3.1.0AL02B0307" + str);
    }
}
