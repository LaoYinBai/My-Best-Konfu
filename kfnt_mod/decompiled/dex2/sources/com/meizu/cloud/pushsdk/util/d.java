package com.meizu.cloud.pushsdk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.c.b.f;
import com.meizu.cloud.pushsdk.c.c.b;
import com.meizu.cloud.pushsdk.c.f.e;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.notification.MPushMessage;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private static int a(Context context) {
        if (MzSystemUtils.isMeizu(context)) {
            return 1;
        }
        if (MzSystemUtils.isXiaoMi()) {
            return 2;
        }
        return MzSystemUtils.isHuaWei() ? 3 : 0;
    }

    private static Intent a(Context context, Map<String, String> map) {
        String str;
        String str2;
        String str3;
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(new Intent(PushConstants.MZ_PUSH_TRACKER_SERVICE_ACTION), 0);
        if (listQueryIntentServices != null) {
            Iterator<ResolveInfo> it = listQueryIntentServices.iterator();
            while (true) {
                if (!it.hasNext()) {
                    str2 = null;
                    str3 = null;
                    break;
                }
                ResolveInfo next = it.next();
                if (PushConstants.PUSH_PACKAGE_NAME.equals(next.serviceInfo.packageName)) {
                    str2 = next.serviceInfo.packageName;
                    str3 = next.serviceInfo.name;
                    break;
                }
            }
            if (!TextUtils.isEmpty(str3) || listQueryIntentServices.size() <= 0) {
                str = str3;
            } else {
                str2 = listQueryIntentServices.get(0).serviceInfo.packageName;
                str = listQueryIntentServices.get(0).serviceInfo.name;
            }
        } else {
            str = null;
            str2 = null;
        }
        DebugLogger.i("UxIPUtils", "current process packageName " + str2);
        if (!TextUtils.isEmpty(str)) {
            try {
                String string = e.a((Map) map).toString();
                Intent intent = new Intent();
                intent.setPackage(str2);
                intent.setAction(PushConstants.MZ_PUSH_TRACKER_SERVICE_ACTION);
                intent.putExtra(PushConstants.EXTRA_PUSH_TRACKER_JSON_DATA, string);
                return intent;
            } catch (Exception e) {
                e.printStackTrace();
                DebugLogger.e("UxIPUtils", "getRemotePushTrackerIntent error " + e.getMessage());
            }
        }
        return null;
    }

    public static com.meizu.cloud.pushsdk.handler.a.b.d a(String str) {
        String str2;
        String str3;
        com.meizu.cloud.pushsdk.handler.a.b.d dVar = new com.meizu.cloud.pushsdk.handler.a.b.d();
        if (TextUtils.isEmpty(str)) {
            str2 = "UxIPUtils";
            str3 = "the platformExtra is empty";
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                return com.meizu.cloud.pushsdk.handler.a.b.d.a().a(jSONObject.has(PushConstants.TASK_ID) ? jSONObject.getString(PushConstants.TASK_ID) : null).d(jSONObject.has("device_id") ? jSONObject.getString("device_id") : null).c(jSONObject.has("push_timestamp") ? jSONObject.getString("push_timestamp") : null).b(jSONObject.has("seq_id") ? jSONObject.getString("seq_id") : null).a();
            } catch (Exception unused) {
                str2 = "UxIPUtils";
                str3 = "the platformExtra parse error";
            }
        }
        DebugLogger.e(str2, str3);
        return dVar;
    }

    public static String a(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_TASK_ID);
        if (!TextUtils.isEmpty(stringExtra)) {
            return stringExtra;
        }
        try {
            MPushMessage mPushMessage = (MPushMessage) intent.getSerializableExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE);
            return mPushMessage != null ? mPushMessage.getTaskId() : stringExtra;
        } catch (Exception e) {
            DebugLogger.e("UxIPUtils", "parse MessageV2 error " + e.getMessage());
            return "no push platform task";
        }
    }

    public static void a(Context context, Intent intent, String str, int i) {
        a(context, intent, PushManager.TAG, str, i);
    }

    public static void a(Context context, Intent intent, String str, String str2, int i) {
        if (TextUtils.isEmpty(a(intent))) {
            return;
        }
        a(context, context.getPackageName(), intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_STATISTICS_IMEI_KEY), a(intent), str, str2, i);
    }

    public static void a(Context context, String str, int i, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        a(context, context.getPackageName(), str3, str2, PushManager.TAG, str, i);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        a(context, true, str, str2, str3, str4, "dpm", str5);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, int i) {
        HashMap map = new HashMap(8);
        map.put("taskId", str3);
        map.put("deviceId", str2);
        map.put(CampaignEx.JSON_KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
        map.put(CampaignEx.JSON_KEY_PACKAGE_NAME, str);
        map.put("pushsdk_version", str4);
        map.put("push_info", str5);
        map.put("push_info_type", String.valueOf(i));
        a(context, false, "notification_service_message", (Map<String, String>) map);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, long j) {
        a(context, true, str, str2, str3, str4, "spm", str5, j);
    }

    public static void a(Context context, boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        a(context, z, str, str2, str3, str4, str5, str6, 0L);
    }

    public static void a(Context context, boolean z, String str, String str2, String str3, String str4, String str5, String str6, long j) {
        HashMap map = new HashMap(8);
        map.put("en", str5);
        map.put("ti", str3);
        map.put(AppIconSetting.DEFAULT_LARGE_ICON, str2);
        if (TextUtils.isEmpty(str6)) {
            str6 = String.valueOf(System.currentTimeMillis() / 1000);
        }
        map.put("ts", str6);
        map.put("pn", str);
        map.put("pv", PushManager.TAG);
        map.put("nm", String.valueOf(System.currentTimeMillis() / 1000));
        if (!TextUtils.isEmpty(str4)) {
            map.put("si", str4);
        }
        if (a(context, map, z, j)) {
            return;
        }
        a(context, z, str5, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Context context, boolean z, String str, Map<String, String> map) {
        DebugLogger.e("UxIPUtils", "onLogEvent eventName [" + str + "] properties = " + map);
        if ("notification_service_message".equals(str)) {
            return;
        }
        com.meizu.cloud.pushsdk.c.a.a(context, (f) null).a(((b.a) com.meizu.cloud.pushsdk.c.c.b.d().a(str).a(a(context)).a(Long.valueOf(map.get("ts")).longValue())).h(map.get("nm") != null ? map.get("nm") : String.valueOf(System.currentTimeMillis() / 1000)).c(map.get(AppIconSetting.DEFAULT_LARGE_ICON)).e(map.get("pn")).d(map.get("pv")).b(map.get("ti")).f(TextUtils.isEmpty(map.get("si")) ? "" : map.get("si")).g(String.valueOf(b.j(context, map.get("pn")))).b(), z);
    }

    private static boolean a(final Context context, final Intent intent, final boolean z, final Map<String, String> map, final long j) {
        if (intent == null) {
            DebugLogger.e("UxIPUtils", "startRemotePushTracker error intent is null");
            return false;
        }
        if (j != 0) {
            new Handler(context.getMainLooper()).postDelayed(new Runnable() { // from class: com.meizu.cloud.pushsdk.util.d.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        context.startService(intent);
                        DebugLogger.i("UxIPUtils", "delayed " + j + " ms start tracker data in mz_tracker process " + intent.getStringExtra(PushConstants.EXTRA_PUSH_TRACKER_JSON_DATA));
                    } catch (Exception e) {
                        e.printStackTrace();
                        DebugLogger.e("UxIPUtils", "delayed startRemotePushTracker error " + e.getMessage());
                        d.a(context, z, (String) map.get("en"), (Map<String, String>) map);
                    }
                }
            }, j);
            return true;
        }
        try {
            context.startService(intent);
            DebugLogger.i("UxIPUtils", "immediately start tracker data in mz_tracker process " + intent.getStringExtra(PushConstants.EXTRA_PUSH_TRACKER_JSON_DATA));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            DebugLogger.e("UxIPUtils", "startRemotePushTracker error " + e.getMessage());
            return false;
        }
    }

    private static boolean a(Context context, Map<String, String> map, boolean z, long j) {
        return a(context, a(context, map), z, map, j);
    }

    public static void b(Context context, String str, String str2, String str3, String str4, String str5) {
        a(context, false, str, str2, str3, str4, "rpe", str5);
    }

    public static void b(Context context, String str, String str2, String str3, String str4, String str5, long j) {
        a(context, false, str, str2, str3, str4, "rpe", str5, j);
    }

    public static void c(Context context, String str, String str2, String str3, String str4, String str5) {
        a(context, true, str, str2, str3, str4, "rpe", str5);
    }

    public static void c(Context context, String str, String str2, String str3, String str4, String str5, long j) {
        a(context, false, str, str2, str3, str4, "sipm", str5, j);
    }

    public static void d(Context context, String str, String str2, String str3, String str4, String str5) {
        a(context, true, str, str2, str3, str4, "cpm", str5);
    }

    public static void d(Context context, String str, String str2, String str3, String str4, String str5, long j) {
        a(context, false, str, str2, str3, str4, "npm", str5, j);
    }
}
