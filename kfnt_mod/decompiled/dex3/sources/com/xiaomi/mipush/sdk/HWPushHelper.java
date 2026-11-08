package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class HWPushHelper {
    private static boolean a;

    public static void convertMessage(Intent intent) {
        i.a(intent);
    }

    public static boolean hasNetwork(Context context) {
        return i.m83a(context);
    }

    public static boolean isHmsTokenSynced(Context context) {
        String strA = i.a(e.ASSEMBLE_PUSH_HUAWEI);
        if (TextUtils.isEmpty(strA)) {
            return false;
        }
        String strA2 = i.a(context, strA);
        String strA3 = ae.a(context).a(at.UPLOAD_HUAWEI_TOKEN);
        return (TextUtils.isEmpty(strA2) || TextUtils.isEmpty(strA3) || !"synced".equals(strA3)) ? false : true;
    }

    public static boolean isUserOpenHmsPush(Context context) {
        return MiPushClient.getOpenHmsPush(context);
    }

    public static boolean needConnect() {
        return a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
    
        r0 = r2.getString("pushMsg");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void notifyHmsNotificationMessageClicked(android.content.Context r4, java.lang.String r5) throws org.json.JSONException {
        /*
            java.lang.String r0 = ""
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 != 0) goto L39
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Exception -> L31
            r1.<init>(r5)     // Catch: java.lang.Exception -> L31
            int r5 = r1.length()     // Catch: java.lang.Exception -> L31
            if (r5 <= 0) goto L39
            r5 = 0
        L14:
            int r2 = r1.length()     // Catch: java.lang.Exception -> L31
            if (r5 >= r2) goto L39
            org.json.JSONObject r2 = r1.getJSONObject(r5)     // Catch: java.lang.Exception -> L31
            java.lang.String r3 = "pushMsg"
            boolean r3 = r2.has(r3)     // Catch: java.lang.Exception -> L31
            if (r3 == 0) goto L2e
            java.lang.String r5 = "pushMsg"
            java.lang.String r5 = r2.getString(r5)     // Catch: java.lang.Exception -> L31
            r0 = r5
            goto L39
        L2e:
            int r5 = r5 + 1
            goto L14
        L31:
            r5 = move-exception
            java.lang.String r5 = r5.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r5)
        L39:
            com.xiaomi.mipush.sdk.PushMessageReceiver r5 = com.xiaomi.mipush.sdk.i.a(r4)
            if (r5 == 0) goto L53
            com.xiaomi.mipush.sdk.MiPushMessage r0 = com.xiaomi.mipush.sdk.i.a(r0)
            java.util.Map r1 = r0.getExtra()
            java.lang.String r2 = "notify_effect"
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L50
            return
        L50:
            r5.onNotificationMessageClicked(r4, r0)
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.HWPushHelper.notifyHmsNotificationMessageClicked(android.content.Context, java.lang.String):void");
    }

    public static void notifyHmsPassThoughMessageArrived(Context context, String str) throws JSONException {
        String string = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(com.umeng.commonsdk.framework.c.a)) {
                    string = jSONObject.getString(com.umeng.commonsdk.framework.c.a);
                }
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.d(e.toString());
        }
        PushMessageReceiver pushMessageReceiverA = i.a(context);
        if (pushMessageReceiverA != null) {
            pushMessageReceiverA.onReceivePassThroughMessage(context, i.a(string));
        }
    }

    public static void registerHuaWeiAssemblePush(Context context) {
        AbstractPushManager abstractPushManagerA = f.a(context).a(e.ASSEMBLE_PUSH_HUAWEI);
        if (abstractPushManagerA != null) {
            abstractPushManagerA.register();
        }
    }

    public static void reportError(String str, int i) {
        i.a(str, i);
    }

    public static synchronized void setConnectTime(Context context) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_connect_time", System.currentTimeMillis()).commit();
    }

    public static synchronized void setGetTokenTime(Context context) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_get_token_time", System.currentTimeMillis()).commit();
    }

    public static void setNeedConnect(boolean z) {
        a = z;
    }

    public static synchronized boolean shouldGetToken(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_get_token_time", -1L)) > 172800000;
    }

    public static synchronized boolean shouldTryConnect(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_connect_time", -1L)) > AppLinkMonitor.DELAY_TIME;
    }

    public static void uploadToken(Context context, String str) {
        i.a(context, e.ASSEMBLE_PUSH_HUAWEI, str);
    }
}
