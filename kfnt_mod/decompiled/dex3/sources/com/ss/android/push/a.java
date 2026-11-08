package com.ss.android.push;

import android.content.Intent;
import android.os.Bundle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static int a(Intent intent, String str, int i) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return i;
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException unused) {
                return i;
            }
        }
        try {
            return ((Integer) obj).intValue();
        } catch (ClassCastException unused2) {
            return i;
        }
    }

    public static boolean a(Intent intent, String str, boolean z) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return z;
        }
        if (obj instanceof String) {
            try {
                return Boolean.parseBoolean((String) obj);
            } catch (NumberFormatException unused) {
                return z;
            }
        }
        try {
            return ((Boolean) obj).booleanValue();
        } catch (ClassCastException unused2) {
            return z;
        }
    }
}
