package com.bytedance.push.notification;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import anet.channel.util.HttpConstant;
import com.bytedance.push.c.j;
import com.bytedance.push.c.n;
import com.bytedance.push.settings.LocalFrequencySettings;
import com.bytedance.push.settings.SettingsManager;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.ss.android.pushmanager.MessageConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class i implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private final Context f4909a;

    /* renamed from: b, reason: collision with root package name */
    private final n f4910b;

    /* renamed from: c, reason: collision with root package name */
    private final j f4911c;
    private final com.bytedance.push.e.b d;
    private final boolean e;

    i(Context context, j jVar, boolean z, com.bytedance.push.e.b bVar, n nVar) {
        this.f4909a = context;
        this.f4911c = jVar;
        this.e = z;
        this.d = bVar;
        this.f4910b = nVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        LocalFrequencySettings localFrequencySettings = (LocalFrequencySettings) SettingsManager.obtain(this.f4909a, LocalFrequencySettings.class);
        int iD = com.ss.android.message.a.b.d(this.f4909a);
        Map<String, String> mapA = this.f4911c.a();
        mapA.put("notice", this.e ? OnekeyLoginConstants.CU_RESULT_SUCCESS : "1");
        mapA.put("system_notify_status", iD + "");
        String strA = com.ss.android.message.a.b.a(MessageConstants.getSendNotifyEnableUrl(), mapA);
        try {
            JSONArray jSONArrayA = e.a().a(this.f4909a);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Pair("out_app_channel_notify", jSONArrayA.toString()));
            if (this.d != null) {
                String strA2 = this.d.a();
                if (!TextUtils.isEmpty(strA2)) {
                    arrayList.add(new Pair("mute_setting", strA2));
                }
                String strB = this.d.b();
                if (!TextUtils.isEmpty(strB)) {
                    arrayList.add(new Pair("scene_status_list", strB));
                }
            }
            String strA3 = com.bytedance.common.push.utility.f.a().a(strA, arrayList);
            com.bytedance.push.utils.e.a("NoticeSync", "sendPushEnableToServer response = " + strA3);
            if (!TextUtils.isEmpty(strA3)) {
                String strOptString = new JSONObject(strA3).optString("message");
                if ("success".equals(strOptString)) {
                    localFrequencySettings.a(true);
                    localFrequencySettings.a(iD);
                    localFrequencySettings.f(jSONArrayA.toString());
                    localFrequencySettings.b(System.currentTimeMillis());
                    com.bytedance.push.i.o().d();
                    a();
                    return;
                }
                this.f4911c.i().b(302, strA3);
                a(1001, strOptString);
            } else {
                this.f4911c.i().b(HttpConstant.SC_NOT_MODIFIED, strA3);
                a(1001, "server return empty");
            }
            localFrequencySettings.a(false);
        } catch (Exception e) {
            localFrequencySettings.a(false);
            com.bytedance.push.i.o().b(301, Log.getStackTraceString(e));
            e.printStackTrace();
            if (e instanceof IOException) {
                a(1002, "network error : " + e.getMessage());
                return;
            }
            a(1003, "unknown error: " + e.getMessage());
        }
    }

    private void a() {
        if (this.f4910b != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.push.notification.i.1
                @Override // java.lang.Runnable
                public void run() {
                    i.this.f4910b.a();
                }
            });
        }
    }

    private void a(final int i, final String str) {
        if (this.f4910b != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.push.notification.i.2
                @Override // java.lang.Runnable
                public void run() {
                    i.this.f4910b.a(i, str);
                }
            });
        }
    }
}
