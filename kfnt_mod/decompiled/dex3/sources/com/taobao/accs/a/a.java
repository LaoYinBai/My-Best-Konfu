package com.taobao.accs.a;

import android.content.Context;
import android.content.Intent;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.w;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final String TAG = "a";

    public static void a(Context context, Intent intent) {
        if (context == null || intent == null) {
            ALog.e(TAG, "dispatchIntent context or intent is null", new Object[0]);
            return;
        }
        Context applicationContext = context.getApplicationContext();
        try {
            if (w.a(applicationContext)) {
                ALog.i(TAG, "dispatchIntent bind service start", Constants.INTENT, intent.toString());
                applicationContext.bindService(intent, new b(intent, applicationContext), 1);
            } else {
                ALog.i(TAG, "dispatchIntent start service ", new Object[0]);
                applicationContext.startService(intent);
            }
        } catch (Exception e) {
            ALog.e(TAG, "dispatchIntent method call with exception ", e.toString());
            e.printStackTrace();
        }
    }
}
