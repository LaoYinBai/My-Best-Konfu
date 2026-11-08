package com.huawei.hms.common.internal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class DialogRedirect implements DialogInterface.OnClickListener {
    protected abstract void redirect();

    public static DialogRedirect getInstance(Activity activity, Intent intent, int i) {
        return new DialogRedirectImpl(intent, activity, i);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            try {
                redirect();
            } catch (Throwable unused) {
                HMSLog.e("DialogRedirect", "Failed to start resolution intent");
            }
        } finally {
            dialogInterface.dismiss();
        }
    }
}
