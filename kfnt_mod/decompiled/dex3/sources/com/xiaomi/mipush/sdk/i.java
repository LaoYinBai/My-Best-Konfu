package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.android.token.TokenConstants;
import com.xiaomi.push.bg;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class i {
    private static HashMap<String, String> a = new HashMap<>();

    public static MiPushMessage a(String str) {
        MiPushMessage miPushMessage = new MiPushMessage();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("messageId")) {
                    miPushMessage.setMessageId(jSONObject.getString("messageId"));
                }
                if (jSONObject.has(TokenConstants.ERROR_MSG)) {
                    miPushMessage.setDescription(jSONObject.getString(TokenConstants.ERROR_MSG));
                }
                if (jSONObject.has(DBDefinition.TITLE)) {
                    miPushMessage.setTitle(jSONObject.getString(DBDefinition.TITLE));
                }
                if (jSONObject.has(com.umeng.commonsdk.framework.c.a)) {
                    miPushMessage.setContent(jSONObject.getString(com.umeng.commonsdk.framework.c.a));
                }
                if (jSONObject.has("passThrough")) {
                    miPushMessage.setPassThrough(jSONObject.getInt("passThrough"));
                }
                if (jSONObject.has("notifyType")) {
                    miPushMessage.setNotifyType(jSONObject.getInt("notifyType"));
                }
                if (jSONObject.has("messageType")) {
                    miPushMessage.setMessageType(jSONObject.getInt("messageType"));
                }
                if (jSONObject.has("alias")) {
                    miPushMessage.setAlias(jSONObject.getString("alias"));
                }
                if (jSONObject.has("topic")) {
                    miPushMessage.setTopic(jSONObject.getString("topic"));
                }
                if (jSONObject.has("user_account")) {
                    miPushMessage.setUserAccount(jSONObject.getString("user_account"));
                }
                if (jSONObject.has("notifyId")) {
                    miPushMessage.setNotifyId(jSONObject.getInt("notifyId"));
                }
                if (jSONObject.has("category")) {
                    miPushMessage.setCategory(jSONObject.getString("category"));
                }
                if (jSONObject.has("isNotified")) {
                    miPushMessage.setNotified(jSONObject.getBoolean("isNotified"));
                }
                if (jSONObject.has("extra")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("extra");
                    Iterator<String> itKeys = jSONObject2.keys();
                    HashMap map = new HashMap();
                    while (itKeys != null && itKeys.hasNext()) {
                        String next = itKeys.next();
                        map.put(next, jSONObject2.getString(next));
                    }
                    if (map.size() > 0) {
                        miPushMessage.setExtra(map);
                    }
                }
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.d(e.toString());
            }
        }
        return miPushMessage;
    }

    protected static PushMessageReceiver a(Context context) {
        ResolveInfo next;
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        try {
            List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            if (listQueryBroadcastReceivers != null) {
                Iterator<ResolveInfo> it = listQueryBroadcastReceivers.iterator();
                while (it.hasNext()) {
                    next = it.next();
                    if (next.activityInfo != null && next.activityInfo.packageName.equals(context.getPackageName())) {
                        break;
                    }
                }
                next = null;
            } else {
                next = null;
            }
            if (next != null) {
                return (PushMessageReceiver) com.xiaomi.push.t.a(context, next.activityInfo.name).newInstance();
            }
            return null;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.d(e.toString());
            return null;
        }
    }

    protected static synchronized String a(Context context, String str) {
        String str2;
        str2 = a.get(str);
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        return str2;
    }

    public static String a(e eVar) {
        switch (k.a[eVar.ordinal()]) {
            case 1:
                return "hms_push_token";
            case 2:
                return "fcm_push_token";
            case 3:
                return "cos_push_token";
            case 4:
                return "ftos_push_token";
            default:
                return null;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0019. Please report as an issue. */
    public static HashMap<String, String> a(Context context, e eVar) throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo;
        StringBuilder sb;
        af afVar;
        HashMap<String, String> map = new HashMap<>();
        String strA = a(eVar);
        if (TextUtils.isEmpty(strA)) {
            return map;
        }
        String string = null;
        switch (k.a[eVar.ordinal()]) {
            case 1:
                try {
                    applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.logger.b.d(e.toString());
                    applicationInfo = null;
                }
                int i = applicationInfo != null ? applicationInfo.metaData.getInt(Constants.HUAWEI_HMS_CLIENT_APPID) : -1;
                sb = new StringBuilder();
                sb.append("brand:");
                sb.append(n.a(context).name());
                sb.append(Constants.WAVE_SEPARATOR);
                sb.append("token");
                sb.append(Constants.COLON_SEPARATOR);
                sb.append(a(context, strA));
                sb.append(Constants.WAVE_SEPARATOR);
                sb.append("package_name");
                sb.append(Constants.COLON_SEPARATOR);
                sb.append(context.getPackageName());
                sb.append(Constants.WAVE_SEPARATOR);
                sb.append("app_id");
                sb.append(Constants.COLON_SEPARATOR);
                sb.append(i);
                string = sb.toString();
                break;
            case 2:
                sb = new StringBuilder();
                sb.append("brand:");
                afVar = af.FCM;
                sb.append(afVar.name());
                sb.append(Constants.WAVE_SEPARATOR);
                sb.append("token");
                sb.append(Constants.COLON_SEPARATOR);
                sb.append(a(context, strA));
                sb.append(Constants.WAVE_SEPARATOR);
                sb.append("package_name");
                sb.append(Constants.COLON_SEPARATOR);
                sb.append(context.getPackageName());
                string = sb.toString();
                break;
            case 3:
                sb = new StringBuilder();
                sb.append("brand:");
                afVar = af.OPPO;
                sb.append(afVar.name());
                sb.append(Constants.WAVE_SEPARATOR);
                sb.append("token");
                sb.append(Constants.COLON_SEPARATOR);
                sb.append(a(context, strA));
                sb.append(Constants.WAVE_SEPARATOR);
                sb.append("package_name");
                sb.append(Constants.COLON_SEPARATOR);
                sb.append(context.getPackageName());
                string = sb.toString();
                break;
            case 4:
                sb = new StringBuilder();
                sb.append("brand:");
                afVar = af.VIVO;
                sb.append(afVar.name());
                sb.append(Constants.WAVE_SEPARATOR);
                sb.append("token");
                sb.append(Constants.COLON_SEPARATOR);
                sb.append(a(context, strA));
                sb.append(Constants.WAVE_SEPARATOR);
                sb.append("package_name");
                sb.append(Constants.COLON_SEPARATOR);
                sb.append(context.getPackageName());
                string = sb.toString();
                break;
        }
        map.put(Constants.ASSEMBLE_PUSH_REG_INFO, string);
        return map;
    }

    /* renamed from: a, reason: collision with other method in class */
    static void m81a(Context context) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        String strA = a(e.ASSEMBLE_PUSH_HUAWEI);
        String strA2 = a(e.ASSEMBLE_PUSH_FCM);
        if (!TextUtils.isEmpty(sharedPreferences.getString(strA, "")) && TextUtils.isEmpty(sharedPreferences.getString(strA2, ""))) {
            z = true;
        }
        if (z) {
            an.a(context).a(2, strA);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m82a(Context context, e eVar) {
        String strA = a(eVar);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        com.xiaomi.push.r.a(context.getSharedPreferences("mipush_extra", 0).edit().putString(strA, ""));
    }

    public static void a(Context context, e eVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        String strA = a(eVar);
        if (TextUtils.isEmpty(strA)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
            return;
        }
        String string = sharedPreferences.getString(strA, "");
        if (!TextUtils.isEmpty(string) && str.equals(string)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : do not need to send token");
            return;
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : send token upload");
        a(eVar, str);
        at atVarA = l.a(eVar);
        if (atVarA == null) {
            return;
        }
        an.a(context).a((String) null, atVarA, eVar);
    }

    public static void a(Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null || !extras.containsKey("pushMsg")) {
            return;
        }
        intent.putExtra(PushMessageHelper.KEY_MESSAGE, a(extras.getString("pushMsg")));
    }

    private static synchronized void a(e eVar, String str) {
        String strA = a(eVar);
        if (TextUtils.isEmpty(strA)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
        } else if (TextUtils.isEmpty(str)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : token is null");
        } else {
            a.put(strA, str);
        }
    }

    public static void a(String str, int i) {
        MiTinyDataClient.upload("hms_push_error", str, 1L, "error code = " + i);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m83a(Context context) {
        if (context == null) {
            return false;
        }
        return bg.b(context);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m84a(Context context, e eVar) {
        if (l.m86a(eVar) != null) {
            return com.xiaomi.push.service.aq.a(context).a(l.m86a(eVar).a(), true);
        }
        return false;
    }

    public static String b(e eVar) {
        switch (k.a[eVar.ordinal()]) {
            case 1:
                return "hms_push_error";
            case 2:
                return "fcm_push_error";
            case 3:
                return "cos_push_error";
            case 4:
                return "ftos_push_error";
            default:
                return null;
        }
    }

    public static void b(Context context) {
        f.a(context).register();
    }

    public static void b(Context context, e eVar, String str) {
        com.xiaomi.push.ai.a(context).a(new j(str, context, eVar));
    }

    public static void c(Context context) {
        f.a(context).unregister();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void d(Context context, e eVar, String str) {
        String strA = a(eVar);
        if (TextUtils.isEmpty(strA)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
            return;
        }
        com.xiaomi.push.r.a(context.getSharedPreferences("mipush_extra", 0).edit().putString(strA, str));
        com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : update sp file success!  " + str);
    }
}
