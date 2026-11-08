package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.common.d;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.m;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengMessageBootReceiver extends BroadcastReceiver {
    private static final String b = "com.umeng.message.UmengMessageBootReceiver";
    private static final String c = "android.intent.action.BOOT_COMPLETED";
    Runnable a = new Runnable() { // from class: com.umeng.message.UmengMessageBootReceiver.1
        @Override // java.lang.Runnable
        public void run() {
            try {
                Iterator<m.c> it = m.a(UmengMessageBootReceiver.this.d).b().iterator();
                while (it.hasNext()) {
                    m.c next = it.next();
                    if (m.a(UmengMessageBootReceiver.this.d).a(next.a) == null && next.b.equals(UMessage.DISPLAY_TYPE_NOTIFICATION)) {
                        m.a(UmengMessageBootReceiver.this.d).a(next.a, 2, System.currentTimeMillis(), "");
                    }
                }
                Iterator<m.d> it2 = m.a(UmengMessageBootReceiver.this.d).d().iterator();
                while (it2.hasNext()) {
                    m.d next2 = it2.next();
                    if (m.a(UmengMessageBootReceiver.this.d).c(next2.a) == null && next2.c.equals(UMessage.DISPLAY_TYPE_NOTIFICATION)) {
                        m.a(UmengMessageBootReceiver.this.d).a(next2.a, next2.b, "9", System.currentTimeMillis());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(UmengMessageBootReceiver.b, 2, e.toString());
            }
        }
    };
    private Context d;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "Boot this system , UmengMessageBootReceiver onReceive()");
            String action = intent.getAction();
            if (action != null && !action.equals("")) {
                UMLog uMLog2 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(b, 2, "action=" + intent.getAction());
                if (TextUtils.equals(intent.getAction(), c)) {
                    this.d = context;
                    d.a(this.a);
                }
            }
        } catch (Exception e) {
            UMLog uMLog3 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 0, e.toString());
        }
    }
}
