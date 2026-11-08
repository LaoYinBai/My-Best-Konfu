package com.huawei.a.a.b.e;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private boolean a = false;
    private int b = 4;

    public void a(int i, String str, String str2) {
        b(i, "HiAnalyticsSDK", str + "=> " + str2);
    }

    public boolean a(int i) {
        return this.a && i >= this.b;
    }

    public void b(int i, String str, String str2) {
        switch (i) {
            case 3:
                Log.d(str, str2);
                break;
            case 4:
            default:
                Log.i(str, str2);
                break;
            case 5:
                Log.w(str, str2);
                break;
            case 6:
                Log.e(str, str2);
                break;
        }
    }
}
