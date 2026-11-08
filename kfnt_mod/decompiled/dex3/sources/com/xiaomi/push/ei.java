package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ei implements Runnable {
    final /* synthetic */ Context a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ eh f324a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f325a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    ei(eh ehVar, String str, Context context, String str2, String str3) {
        this.f324a = ehVar;
        this.f325a = str;
        this.a = context;
        this.b = str2;
        this.c = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        String str;
        String str2;
        Context context2;
        String str3;
        String str4;
        eh ehVar;
        ej ejVar;
        Context context3;
        if (TextUtils.isEmpty(this.f325a)) {
            context = this.a;
            str = "null";
            str2 = "A receive a incorrect message with empty info";
        } else {
            try {
                ed.a(this.a, this.f325a, 1001, "get message");
                JSONObject jSONObject = new JSONObject(this.f325a);
                String strOptString = jSONObject.optString("action");
                String strOptString2 = jSONObject.optString("awakened_app_packagename");
                String strOptString3 = jSONObject.optString("awake_app_packagename");
                String strOptString4 = jSONObject.optString("awake_app");
                String strOptString5 = jSONObject.optString("awake_type");
                int iOptInt = jSONObject.optInt("awake_foreground", 0);
                if (this.b.equals(strOptString3) && this.c.equals(strOptString4)) {
                    if (!TextUtils.isEmpty(strOptString5) && !TextUtils.isEmpty(strOptString3) && !TextUtils.isEmpty(strOptString4) && !TextUtils.isEmpty(strOptString2)) {
                        this.f324a.b(strOptString3);
                        this.f324a.a(strOptString4);
                        eg egVar = new eg();
                        egVar.b(strOptString);
                        egVar.a(strOptString2);
                        egVar.a(iOptInt);
                        egVar.d(this.f325a);
                        if ("service".equals(strOptString5)) {
                            if (TextUtils.isEmpty(strOptString)) {
                                egVar.c("com.xiaomi.mipush.sdk.PushMessageHandler");
                                ehVar = this.f324a;
                                ejVar = ej.SERVICE_COMPONENT;
                                context3 = this.a;
                            } else {
                                ehVar = this.f324a;
                                ejVar = ej.SERVICE_ACTION;
                                context3 = this.a;
                            }
                        } else if (ej.ACTIVITY.f327a.equals(strOptString5)) {
                            ehVar = this.f324a;
                            ejVar = ej.ACTIVITY;
                            context3 = this.a;
                        } else if (ej.PROVIDER.f327a.equals(strOptString5)) {
                            ehVar = this.f324a;
                            ejVar = ej.PROVIDER;
                            context3 = this.a;
                        } else {
                            context2 = this.a;
                            str3 = this.f325a;
                            str4 = "A receive a incorrect message with unknown type " + strOptString5;
                        }
                        ehVar.a(ejVar, context3, egVar);
                        return;
                    }
                    context2 = this.a;
                    str3 = this.f325a;
                    str4 = "A receive a incorrect message with empty type";
                    ed.a(context2, str3, DownloadErrorCode.ERROR_HANDLE_COMPLETE, str4);
                    return;
                }
                ed.a(this.a, this.f325a, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A receive a incorrect message with incorrect package info" + strOptString3);
                return;
            } catch (JSONException e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
                context = this.a;
                str = this.f325a;
                str2 = "A meet a exception when receive the message";
            }
        }
        ed.a(context, str, DownloadErrorCode.ERROR_HANDLE_COMPLETE, str2);
    }
}
