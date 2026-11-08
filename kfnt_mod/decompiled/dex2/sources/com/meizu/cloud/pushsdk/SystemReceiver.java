package com.meizu.cloud.pushsdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.base.IntentReceiver;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.meizu.cloud.pushsdk.util.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SystemReceiver extends IntentReceiver {
    public void a(Context context) {
        String str;
        String appVersionName = MzSystemUtils.getAppVersionName(context, PushConstants.PUSH_PACKAGE_NAME);
        DebugLogger.i("SystemReceiver", context.getPackageName() + " start register cloudVersion_name " + appVersionName);
        Intent intent = new Intent();
        if (PushConstants.PUSH_PACKAGE_NAME.equals(MzSystemUtils.getMzPushServicePackageName(context))) {
            DebugLogger.e("SystemReceiver", "cloud pushService start");
            intent.setAction(PushConstants.PUSH_SERVICE_ACTION);
            intent.setClassName(PushConstants.PUSH_PACKAGE_NAME, PushConstants.MZ_PUSH_SERVICE_NAME);
        } else {
            if (!TextUtils.isEmpty(appVersionName) && MzSystemUtils.compareVersion(appVersionName, PushConstants.PUSH_FLYME_4_CHANGE_VERSION)) {
                DebugLogger.e("SystemReceiver", "flyme 4.x start register cloud versionName " + appVersionName);
                intent.setPackage(PushConstants.PUSH_PACKAGE_NAME);
                str = PushConstants.MZ_PUSH_ON_START_PUSH_REGISTER;
            } else if (TextUtils.isEmpty(appVersionName) || !appVersionName.startsWith(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START)) {
                DebugLogger.e("SystemReceiver", context.getPackageName() + " start register ");
                intent.setClassName(context.getPackageName(), PushConstants.MZ_PUSH_SERVICE_NAME);
                str = PushConstants.PUSH_SERVICE_ACTION;
            } else {
                DebugLogger.e("SystemReceiver", "flyme 3.x start register cloud versionName " + appVersionName);
                intent.setAction(PushConstants.REQUEST_REGISTER_INTENT);
                intent.setPackage(PushConstants.PUSH_PACKAGE_NAME);
            }
            intent.setAction(str);
        }
        a(context, intent);
    }

    public void a(Context context, Intent intent) {
        try {
            context.startService(intent);
        } catch (Exception e) {
            DebugLogger.e("SystemReceiver", "start service error " + e.getMessage());
        }
    }

    @Override // com.meizu.cloud.pushsdk.base.IntentReceiver
    public void onHandleIntent(Context context, Intent intent) {
        if (intent != null) {
            try {
                if (PushConstants.MZ_SYSTEM_RECEIVER_ACTION.equals(intent.getAction())) {
                    a(context);
                    com.meizu.cloud.pushsdk.c.a.a(context, false).a();
                }
            } catch (Exception e) {
                DebugLogger.e("SystemReceiver", "onHandleIntent Exception " + e.getMessage());
            }
        }
    }

    @Override // com.meizu.cloud.pushsdk.base.IntentReceiver, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            super.onReceive(context, intent);
        } catch (Exception e) {
            DebugLogger.e("SystemReceiver", "Event core error " + e.getMessage());
            d.a(context, context.getPackageName(), (String) null, (String) null, PushManager.TAG, "SystemReceiver " + e.getMessage(), PushConstants.WORK_RECEIVER_EVENT_CORE_ERROR);
        }
    }
}
