package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.segment.Segment;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.c;
import com.umeng.message.proguard.m;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengNotificationClickHandler implements UHandler {
    private static final String a = "com.umeng.message.UmengNotificationClickHandler";

    public void dealWithCustomAction(Context context, UMessage uMessage) {
    }

    public void dismissNotification(Context context, UMessage uMessage) {
    }

    @Override // com.umeng.message.UHandler
    public void handleMessage(Context context, UMessage uMessage) {
        try {
            if (!uMessage.clickOrDismiss) {
                dismissNotification(context, uMessage);
                return;
            }
            if (TextUtils.equals(UMessage.DISPLAY_TYPE_AUTOUPDATE, uMessage.display_type) && PushAgent.getInstance(context).isIncludesUmengUpdateSDK()) {
                autoUpdate(context, uMessage);
                return;
            }
            if (!TextUtils.isEmpty(uMessage.after_open)) {
                if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                    if (TextUtils.equals(UMessage.NOTIFICATION_GO_APPURL, uMessage.after_open)) {
                        a(context, uMessage);
                        return;
                    }
                } else {
                    if (TextUtils.equals("go_url", uMessage.after_open)) {
                        openUrl(context, uMessage);
                        return;
                    }
                    if (TextUtils.equals("go_activity", uMessage.after_open)) {
                        openActivity(context, uMessage);
                        return;
                    } else if (TextUtils.equals(UMessage.NOTIFICATION_GO_CUSTOM, uMessage.after_open)) {
                        dealWithCustomAction(context, uMessage);
                        return;
                    } else if (TextUtils.equals("go_app", uMessage.after_open)) {
                        launchApp(context, uMessage);
                        return;
                    }
                }
            }
            if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                return;
            }
            if (uMessage.url != null && !TextUtils.isEmpty(uMessage.url.trim())) {
                openUrl(context, uMessage);
                return;
            }
            if (uMessage.activity != null && !TextUtils.isEmpty(uMessage.activity.trim())) {
                openActivity(context, uMessage);
            } else if (uMessage.custom != null && !TextUtils.isEmpty(uMessage.custom.trim())) {
                dealWithCustomAction(context, uMessage);
            } else {
                launchApp(context, uMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void autoUpdate(Context context, UMessage uMessage) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Object objG = m.a(context).g();
            Class<?> cls = Class.forName("com.umeng.update.UmengUpdateAgent");
            Class<?> cls2 = Class.forName("com.umeng.update.UpdateResponse");
            Method method = cls.getMethod("showUpdateDialog", Context.class, cls2);
            if (objG != null) {
                method.invoke(cls, context, cls2.cast(objG));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(Context context, UMessage uMessage) {
        String strA;
        try {
            String str = uMessage.custom;
            if (str != null && !str.equals("")) {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("p");
                String strOptString2 = jSONObject.optString("pu");
                String strOptString3 = jSONObject.optString("ju");
                int iOptInt = jSONObject.optInt(Segment.JsonKey.END);
                Intent intent = new Intent();
                String[] strArrSplit = strOptString2.split("://");
                if (strArrSplit == null || strArrSplit.length >= 2) {
                    String[] strArrSplit2 = strArrSplit[1].split("/");
                    if (strArrSplit2 == null || strArrSplit2.length >= 1) {
                        String str2 = strArrSplit2[0];
                        if (iOptInt == 1) {
                            String[] strArrSplit3 = strOptString2.split(str2 + "/");
                            try {
                                StringBuilder sb = new StringBuilder();
                                if (strArrSplit3 != null && strArrSplit3.length >= 2) {
                                    sb.append(strArrSplit3[1]);
                                }
                                sb.append("&umessage=");
                                sb.append(uMessage.getRaw().toString());
                                sb.append("&thirdkey=");
                                sb.append(PushAgent.getInstance(context).getMessageAppkey());
                                UMLog uMLog = UMConfigure.umDebugLog;
                                UMLog.mutlInfo(a, 2, "url:" + sb.toString());
                                strA = c.a(sb.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                                strA = "";
                            }
                            strOptString2 = strArrSplit3[0] + str2 + "/" + strA;
                        }
                        intent.setData(Uri.parse(strOptString2));
                        intent.setPackage(strOptString);
                        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                        if (UmengMessageDeviceConfig.getDataData(strOptString)) {
                            try {
                                if (UmengMessageDeviceConfig.isIntentExist(context, strOptString2, strOptString)) {
                                    UTrack.getInstance(context).trackMsgPulled(uMessage, 62);
                                    context.startActivity(intent);
                                } else {
                                    UTrack.getInstance(context).trackMsgPulled(uMessage, 61);
                                    if (strOptString3 != null && !TextUtils.isEmpty(strOptString3.trim())) {
                                        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(strOptString3));
                                        intent2.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                                        context.startActivity(intent2);
                                    }
                                    return;
                                }
                                return;
                            } catch (Exception unused) {
                                return;
                            }
                        }
                        UTrack.getInstance(context).trackMsgPulled(uMessage, 60);
                        if (strOptString3 != null && !TextUtils.isEmpty(strOptString3.trim())) {
                            Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(strOptString3));
                            intent3.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                            context.startActivity(intent3);
                        }
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void openUrl(Context context, UMessage uMessage) {
        if (uMessage.url == null || TextUtils.isEmpty(uMessage.url.trim())) {
            return;
        }
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "打开链接: " + uMessage.url);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(uMessage.url));
        a(intent, uMessage);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        context.startActivity(intent);
    }

    public void openActivity(Context context, UMessage uMessage) {
        if (uMessage.activity == null || TextUtils.isEmpty(uMessage.activity.trim())) {
            return;
        }
        Intent intent = new Intent();
        a(intent, uMessage);
        intent.setClassName(context, uMessage.activity);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        context.startActivity(intent);
    }

    public void launchApp(Context context, UMessage uMessage) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, "找不到应用: " + context.getPackageName());
            return;
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        a(launchIntentForPackage, uMessage);
        context.startActivity(launchIntentForPackage);
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 0, "启动应用: " + context.getPackageName());
    }

    private Intent a(Intent intent, UMessage uMessage) {
        if (intent == null || uMessage == null || uMessage.extra == null) {
            return intent;
        }
        for (Map.Entry<String, String> entry : uMessage.extra.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null) {
                intent.putExtra(key, value);
            }
        }
        return intent;
    }
}
