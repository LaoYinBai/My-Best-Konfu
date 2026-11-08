package com.ss.android.common.applog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class HotsoonReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
            if (TextUtils.isEmpty(encodedSchemeSpecificPart)) {
                return;
            }
            UserInfo.getPackage(encodedSchemeSpecificPart);
        } catch (Throwable unused) {
        }
    }
}
