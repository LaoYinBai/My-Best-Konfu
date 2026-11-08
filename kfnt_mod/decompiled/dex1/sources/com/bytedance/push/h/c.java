package com.bytedance.push.h;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import anet.channel.util.HttpConstant;
import com.bytedance.b.a.c.h;
import com.bytedance.common.push.utility.f;
import com.bytedance.push.c.i;
import com.bytedance.push.settings.LocalFrequencySettings;
import com.bytedance.push.settings.PushOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.ss.android.pushmanager.MessageConstants;
import com.ss.android.pushmanager.setting.PushCommonSetting;
import com.ss.android.pushmanager.setting.PushSetting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private final i f4831a;

    /* renamed from: b, reason: collision with root package name */
    private volatile boolean f4832b;

    /* renamed from: c, reason: collision with root package name */
    private volatile AtomicBoolean f4833c = new AtomicBoolean(false);

    public c(i iVar) {
        this.f4831a = iVar;
    }

    /* JADX WARN: Finally extract failed */
    public synchronized void a(boolean z) {
        boolean z2;
        Application applicationA = com.ss.android.message.a.a();
        LocalFrequencySettings localFrequencySettings = (LocalFrequencySettings) SettingsManager.obtain(applicationA, LocalFrequencySettings.class);
        boolean z3 = Math.abs(System.currentTimeMillis() - localFrequencySettings.a()) < ((PushOnlineSettings) SettingsManager.obtain(applicationA, PushOnlineSettings.class)).b();
        String strB = localFrequencySettings.b();
        String strC = localFrequencySettings.c();
        String strD = localFrequencySettings.d();
        String strE = localFrequencySettings.e();
        Map<String, String> mapA = com.bytedance.push.i.j().a();
        String str = mapA.get(h.x);
        String str2 = mapA.get("version_code");
        String str3 = mapA.get("update_version_code");
        String str4 = mapA.get("channel");
        com.bytedance.push.utils.e.a("UpdateSenderTask", "handleAppLogUpdate requestFrequent=" + z3 + ",lastDeviceId=" + strB + ",deviceId=" + str + ",lastVersionCode=" + strC + ",versionCode=" + str2 + ",lastUpdateVersionCode=" + strD + ",updateVersionCode=" + str3 + ",lastChannel=" + strE + ",channel=" + str4);
        if (((!z3 || (!TextUtils.equals(strB, str) || !TextUtils.equals(strC, str2) || !TextUtils.equals(strD, str3) || !TextUtils.equals(strE, str4)) || this.f4831a.c(applicationA)) && !this.f4832b) || z) {
            this.f4832b = true;
            try {
                try {
                    mapA.put("notice", PushSetting.getInstance().isPushNotifyEnable() ? OnekeyLoginConstants.CU_RESULT_SUCCESS : "1");
                    mapA.put("system_notify_status", com.ss.android.message.a.b.d(com.ss.android.message.a.a()) + "");
                    String strA = com.ss.android.message.a.b.a(MessageConstants.getUpdateSenderUrl(), mapA);
                    ArrayList arrayList = new ArrayList();
                    String string = com.bytedance.push.third.e.a(applicationA).c().toString();
                    arrayList.add(new Pair("push_sdk", string));
                    String strG = com.ss.android.message.a.b.g(applicationA);
                    if (!TextUtils.isEmpty(strG)) {
                        arrayList.add(new Pair("launcher", strG));
                    }
                    com.bytedance.push.utils.e.a("Start", "start request sender. support ：" + string + ", " + strA);
                    String strA2 = f.a().a(strA, arrayList);
                    StringBuilder sb = new StringBuilder();
                    sb.append("UpdateSenderTask response = ");
                    sb.append(strA2);
                    com.bytedance.push.utils.e.a("Start", sb.toString());
                    a(strA2, applicationA, string, str, str2, str3, str4);
                    z2 = false;
                } catch (Throwable th) {
                    a(applicationA, 301, Log.getStackTraceString(th));
                    com.bytedance.push.utils.e.b("Start", "run: UPDATE_SENDER_URL e = " + th.getMessage());
                    th.printStackTrace();
                    z2 = false;
                }
                this.f4832b = z2;
            } catch (Throwable th2) {
                this.f4832b = false;
                throw th2;
            }
        } else {
            boolean z4 = false;
            if (!this.f4832b) {
                HashMap map = new HashMap();
                PushCommonSetting.getInstance().getSSIDs(map);
                String str5 = (String) map.get("clientudid");
                String str6 = (String) map.get(h.x);
                String str7 = (String) map.get("install_id");
                if (!com.bytedance.common.push.utility.i.a(str5) && !com.bytedance.common.push.utility.i.a(str6) && !com.bytedance.common.push.utility.i.a(str7)) {
                    z4 = true;
                }
                if (z4) {
                    a(applicationA);
                }
            }
        }
    }

    private void a(String str, Application application, String str2, String str3, String str4, String str5, String str6) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            a(application, HttpConstant.SC_NOT_MODIFIED, str);
            return;
        }
        JSONObject jSONObject = new JSONObject(str);
        if (!TextUtils.equals("success", jSONObject.optString("message"))) {
            a(application, 302, str);
            return;
        }
        a(application, jSONObject.optJSONArray("channels"));
        String strOptString = jSONObject.optString("allow_push_list");
        if (TextUtils.isEmpty(strOptString)) {
            a(application, 303, str);
            return;
        }
        LocalFrequencySettings localFrequencySettings = (LocalFrequencySettings) SettingsManager.obtain(application, LocalFrequencySettings.class);
        localFrequencySettings.a(System.currentTimeMillis());
        localFrequencySettings.a(str3);
        localFrequencySettings.b(str4);
        localFrequencySettings.c(str5);
        localFrequencySettings.d(str6);
        localFrequencySettings.e(str2);
        boolean z = false;
        try {
            if (new JSONArray(strOptString).length() > 0) {
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!z) {
            a(application, 303, str);
        } else {
            com.bytedance.push.i.o().c();
        }
        this.f4833c.set(true);
        a((Context) com.ss.android.message.a.a(), strOptString, true);
    }

    private void a(Application application, JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String strOptString = jSONObject.optString("id");
                String strOptString2 = jSONObject.optString("name");
                if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                    arrayList.add(new com.bytedance.push.e.a(jSONObject));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        com.bytedance.push.i.j().b().a(application, arrayList);
    }

    private void a(Context context, String str, boolean z) {
        boolean zB = com.bytedance.push.third.e.a(context).b(str);
        com.bytedance.push.utils.e.a("Start", "tryRegisterServerPush " + str + " hasSupport = " + zB);
        boolean z2 = false;
        if (z && !zB) {
            str = PushSetting.getInstance().getPushChannelsJsonArray();
            if (!com.bytedance.push.third.e.a(context).b(str)) {
                com.bytedance.push.i.o().a(false, str);
                return;
            }
        }
        com.bytedance.push.utils.e.a("Start", "tryRegisterAllSelectedPush: the senders = " + str);
        if (z && zB) {
            z2 = true;
        }
        com.bytedance.push.third.e.a(str, z2);
        com.bytedance.push.i.o().a(this.f4831a.a(context), str);
    }

    private void a(Context context, int i, String str) {
        com.bytedance.push.i.o().a(i, str);
        com.bytedance.push.utils.e.b("Start", "request UPDATE_SENDER_URL failed: " + str);
        a(context);
    }

    private void a(Context context) {
        if (this.f4833c.compareAndSet(false, true)) {
            a(context, PushSetting.getInstance().getPushChannelsJsonArray(), false);
        }
    }
}
