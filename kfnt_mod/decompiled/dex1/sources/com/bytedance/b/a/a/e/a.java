package com.bytedance.b.a.a.e;

import android.database.Cursor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    public static void a(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.isClosed()) {
                    return;
                }
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }
}
