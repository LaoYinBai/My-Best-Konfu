package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.proguard.h;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NetworkReceiver extends BroadcastReceiver {
    private static final String a = "com.umeng.message.UmengLocationService";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws SecurityException {
        boolean zIsOnline = UmengMessageDeviceConfig.isOnline(context);
        boolean zC = h.c(context, a);
        if (!zIsOnline && zC) {
            Intent intent2 = new Intent();
            intent2.setAction(MsgConstant.MESSAGE_LBS_ACTION);
            intent2.setPackage(context.getPackageName());
            intent2.putExtra("stopTimer", true);
        }
        if (zIsOnline && zC) {
            Intent intent3 = new Intent();
            intent3.setAction(MsgConstant.MESSAGE_LBS_ACTION);
            intent3.setPackage(context.getPackageName());
        }
    }
}
