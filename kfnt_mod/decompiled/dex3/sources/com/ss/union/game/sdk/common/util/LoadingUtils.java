package com.ss.union.game.sdk.common.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LoadingUtils {
    public static ProgressDialog showDialog() {
        try {
            return a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static ProgressDialog a() {
        ProgressDialog progressDialog;
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            progressDialog = new ProgressDialog(topActivity, 3);
        } else {
            progressDialog = new ProgressDialog(topActivity);
        }
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        int layoutIdByName = ResourceUtils.getLayoutIdByName("lg_dialog_loading");
        if (layoutIdByName == 0) {
            return progressDialog;
        }
        progressDialog.show();
        progressDialog.setContentView(layoutIdByName);
        View viewFindViewById = progressDialog.getWindow().getDecorView().findViewById(ResourceUtils.getIdByName("lg_loading_iv"));
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 359.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setStartOffset(10L);
        viewFindViewById.startAnimation(rotateAnimation);
        return progressDialog;
    }

    public static void dismissDialog(Dialog dialog) {
        if (dialog != null) {
            try {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
