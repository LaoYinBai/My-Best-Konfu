package com.meizu.cloud.pushsdk.handler.a.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSettingEx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private Context a;
    private final List<Intent> b = new ArrayList();
    private BroadcastReceiver c;

    public a(Context context) {
        this.a = context.getApplicationContext();
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a() {
        if (this.b != null && this.b.size() != 0) {
            int size = this.b.size();
            int i = 0;
            Iterator<Intent> it = this.b.iterator();
            while (it.hasNext()) {
                Intent next = it.next();
                if (i != size - 1) {
                    try {
                        a(next);
                    } catch (Exception e) {
                        DebugLogger.e("BrightNotification", "send bright notification error " + e.getMessage());
                    }
                }
                DebugLogger.d("BrightNotification", "start bright notification service " + next);
                this.a.startService(next);
                it.remove();
                i++;
            }
        }
    }

    private void a(Intent intent) {
        MessageV3 messageV3 = (MessageV3) intent.getParcelableExtra(PushConstants.EXTRA_APP_PUSH_BRIGHT_NOTIFICATION_MESSAGE);
        if (messageV3 == null) {
            return;
        }
        AdvanceSetting advanceSetting = messageV3.getAdvanceSetting();
        AdvanceSettingEx advanceSettingEx = messageV3.getAdvanceSettingEx();
        if (advanceSetting == null || advanceSettingEx == null) {
            return;
        }
        advanceSettingEx.setSoundTitle(null);
        advanceSetting.getNotifyType().setSound(false);
        advanceSetting.getNotifyType().setLights(false);
        advanceSetting.getNotifyType().setVibrate(false);
    }

    private void b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        Context context = this.a;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.meizu.cloud.pushsdk.handler.a.e.a.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.intent.action.USER_PRESENT".equalsIgnoreCase(intent.getAction())) {
                    a.this.a();
                }
            }
        };
        this.c = broadcastReceiver;
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public synchronized void a(Intent intent, String str) {
        if (intent != null) {
            if (this.b != null && !TextUtils.isEmpty(str)) {
                for (Intent intent2 : this.b) {
                    MessageV3 messageV3 = (MessageV3) intent2.getParcelableExtra(PushConstants.EXTRA_APP_PUSH_BRIGHT_NOTIFICATION_MESSAGE);
                    if (messageV3 != null && messageV3.getUploadDataPackageName() != null && str.equalsIgnoreCase(messageV3.getUploadDataPackageName())) {
                        this.b.remove(intent2);
                    }
                }
                this.b.add(intent);
                DebugLogger.d("BrightNotification", "add bright notification intent, intent list: " + this.b);
            }
        }
    }
}
