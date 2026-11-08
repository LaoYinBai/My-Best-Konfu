package com.bytedance.msdk.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class TToast {

    /* renamed from: a, reason: collision with root package name */
    private static Toast f3923a;

    @SuppressLint({"ShowToast"})
    private static Toast a(Context context) {
        if (context == null) {
            return f3923a;
        }
        f3923a = Toast.makeText(context.getApplicationContext(), "", 0);
        return f3923a;
    }

    public static void reset() {
        f3923a = null;
    }

    public static void show(Context context, String str) {
        show(context, str, 1);
    }

    public static void show(Context context, String str, int i) {
        Toast toastA = a(context);
        if (toastA != null) {
            toastA.setDuration(i);
            toastA.setText(String.valueOf(str));
            toastA.show();
        } else {
            Log.i("TToast", "toast msg: " + String.valueOf(str));
        }
    }
}
