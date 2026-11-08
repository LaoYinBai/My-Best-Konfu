package com.bytedance.a;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface y {

    public static class a implements y {
        public void a(String str, String str2, Throwable th) {
            Log.v(str, str2, th);
        }

        public void b(String str, String str2, Throwable th) {
            Log.d(str, str2, th);
        }

        public void c(String str, String str2, Throwable th) {
            Log.e(str, str2, th);
        }
    }
}
