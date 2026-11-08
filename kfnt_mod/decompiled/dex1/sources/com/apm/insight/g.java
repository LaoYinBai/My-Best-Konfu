package com.apm.insight;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.k.k;
import com.apm.insight.l.l;
import com.apm.insight.l.v;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: b, reason: collision with root package name */
    protected static MonitorCrash f2853b;

    /* renamed from: a, reason: collision with root package name */
    protected MonitorCrash f2854a;

    private g(MonitorCrash monitorCrash) {
        this.f2854a = monitorCrash;
        com.apm.insight.entity.b.a(this);
        com.apm.insight.j.b.d();
        k.e();
    }

    public static Object a() {
        return f2853b;
    }

    static void a(Context context, final MonitorCrash monitorCrash) {
        f2853b = monitorCrash;
        g gVar = new g(monitorCrash);
        final com.apm.insight.runtime.d dVarA = h.a();
        Npth.init(context, new ICommonParams() { // from class: com.apm.insight.g.1
            @Override // com.apm.insight.ICommonParams
            public Map<String, Object> getCommonParams() {
                return l.b(g.this.e());
            }

            @Override // com.apm.insight.ICommonParams
            public String getDeviceId() {
                return TextUtils.isEmpty(monitorCrash.mConfig.mDeviceId) ? dVarA.d() : monitorCrash.mConfig.mDeviceId;
            }

            @Override // com.apm.insight.ICommonParams
            public List<String> getPatchInfo() {
                return null;
            }

            @Override // com.apm.insight.ICommonParams
            public Map<String, Integer> getPluginInfo() {
                return null;
            }

            @Override // com.apm.insight.ICommonParams
            public String getSessionId() {
                return null;
            }

            @Override // com.apm.insight.ICommonParams
            public long getUserId() {
                return 0L;
            }
        });
    }

    static void a(MonitorCrash monitorCrash) {
        new g(monitorCrash);
    }

    @Nullable
    private JSONObject b(CrashType crashType) {
        Map<? extends String, ? extends String> userData;
        if (this.f2854a.mCustomData == null || (userData = this.f2854a.mCustomData.getUserData(crashType)) == null) {
            return null;
        }
        return new JSONObject(userData);
    }

    @NonNull
    private JSONObject c(CrashType crashType) {
        return new JSONObject(this.f2854a.mTagMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public JSONObject e() throws JSONException {
        com.apm.applog.a aVarA;
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.f2854a.mConfig.mPackageName == null) {
                Context contextG = h.g();
                PackageInfo packageInfo = contextG.getPackageManager().getPackageInfo(contextG.getPackageName(), 128);
                if (packageInfo != null) {
                    if (this.f2854a.mConfig.mVersionInt == -1) {
                        this.f2854a.mConfig.mVersionInt = packageInfo.versionCode;
                    }
                    if (this.f2854a.mConfig.mVersionStr == null) {
                        this.f2854a.mConfig.mVersionStr = packageInfo.versionName;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        if ((TextUtils.isEmpty(this.f2854a.mConfig.mDeviceId) || OnekeyLoginConstants.CU_RESULT_SUCCESS.equals(this.f2854a.mConfig.mDeviceId)) && (aVarA = com.apm.applog.a.a(this.f2854a.mConfig.mAid)) != null) {
            this.f2854a.mConfig.mDeviceId = aVarA.u();
        }
        try {
            jSONObject.put("aid", String.valueOf(this.f2854a.mConfig.mAid));
            jSONObject.put("update_version_code", this.f2854a.mConfig.mVersionInt);
            jSONObject.put("version_code", this.f2854a.mConfig.mVersionInt);
            jSONObject.put("app_version", this.f2854a.mConfig.mVersionStr);
            jSONObject.put("channel", this.f2854a.mConfig.mChannel);
            jSONObject.put("package", l.a(this.f2854a.mConfig.mPackageName));
            jSONObject.put(com.bytedance.b.a.c.h.x, this.f2854a.mConfig.mDeviceId);
            jSONObject.put("user_id", this.f2854a.mConfig.mUID);
            jSONObject.put("ssid", this.f2854a.mConfig.mSSID);
            jSONObject.put("os", "Android");
            jSONObject.put("so_list", l.a(this.f2854a.mConfig.mSoList));
            jSONObject.put("single_upload", d() ? 1 : 0);
        } catch (JSONException unused2) {
        }
        return jSONObject;
    }

    @Nullable
    public JSONArray a(StackTraceElement[] stackTraceElementArr, Throwable th) {
        if (this.f2854a.mConfig.mPackageName == null) {
            return new JSONArray().put(new v.a(0, stackTraceElementArr.length).a());
        }
        if (th == null || stackTraceElementArr == null) {
            return null;
        }
        return v.a(stackTraceElementArr, this.f2854a.mConfig.mPackageName);
    }

    public JSONArray a(String[] strArr) {
        return this.f2854a.config().mPackageName == null ? new JSONArray().put(new v.a(0, strArr.length).a()) : v.a(strArr, this.f2854a.mConfig.mPackageName);
    }

    public JSONObject a(CrashType crashType) {
        return a(crashType, (JSONArray) null);
    }

    public JSONObject a(CrashType crashType, JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("header", e());
            if (crashType != null) {
                jSONObject.put("custom", b(crashType));
                jSONObject.put("filters", c(crashType));
            }
            jSONObject.put("line_num", jSONArray);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public boolean a(Object obj) {
        return this.f2854a == obj;
    }

    public String b() {
        return this.f2854a.mConfig.mAid;
    }

    public JSONObject c() {
        return e();
    }

    public boolean d() {
        return false;
    }
}
