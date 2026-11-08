package com.bytedance.sdk.account.utils;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.StringRes;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ToastUtils {
    private static final int LENGTH_WITH_ICON = 1500;

    private ToastUtils() {
    }

    public static void showToast(Context context, @StringRes int i) {
        showToast(context, i, 0);
    }

    public static void showToast(Context context, String str) {
        showToast(context, str, 0);
    }

    public static void showToast(Context context, @StringRes int i, int i2) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, i, i2).show();
    }

    public static void showToast(Context context, String str, int i) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, str, i).show();
    }
}
