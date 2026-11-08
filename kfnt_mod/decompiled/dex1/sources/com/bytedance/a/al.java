package com.bytedance.a;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.profile.UserProfileCallback;
import com.bytedance.sdk.account.platform.api.IAppAuthService;
import com.bytedance.sdk.account.platform.api.IGoogleService;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class al {

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f3355a = {"aid", "region", "os", "package", "app_version", PluginConstants.KEY_SDK_VERSION, "os_version", "device_model", "resolution", "language", "timezone", "access", IGoogleService.ResponseKey.DISPLAY_NAME, "channel", DispatchConstants.CARRIER, "app_language", "app_region", "tz_name", "tz_offset", "install_id", "openudid", "mcc_mnc", "rom", "manifest_version_code", "device_manufacturer", "clientudid", "sig_hash", "display_density", "os_api", "update_version_code", "density_dpi", "version_code", "sim_serial_number", "release_build", "udid", "cpu_abi", "google_aid"};

    /* renamed from: b, reason: collision with root package name */
    public static final String[] f3356b = {"setOnce", "synchronize"};

    /* renamed from: c, reason: collision with root package name */
    public static final int[] f3357c = {-1, -1};
    public static final long[] d = {-1, -1};

    public static class a implements UserProfileCallback {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3358a;

        /* renamed from: b, reason: collision with root package name */
        public final /* synthetic */ JSONObject f3359b;

        /* renamed from: c, reason: collision with root package name */
        public final /* synthetic */ UserProfileCallback f3360c;

        public a(int i, JSONObject jSONObject, UserProfileCallback userProfileCallback) {
            this.f3358a = i;
            this.f3359b = jSONObject;
            this.f3360c = userProfileCallback;
        }

        @Override // com.bytedance.applog.profile.UserProfileCallback
        public void onFail(int i) {
            this.f3360c.onFail(i);
        }

        @Override // com.bytedance.applog.profile.UserProfileCallback
        public void onSuccess() {
            al.f3357c[this.f3358a] = this.f3359b.toString().hashCode();
            al.d[this.f3358a] = System.currentTimeMillis();
            this.f3360c.onSuccess();
        }
    }

    public static void a(dg dgVar, int i, JSONObject jSONObject, UserProfileCallback userProfileCallback, Handler handler, boolean z) {
        boolean z2;
        JSONObject jSONObject2;
        if (z) {
            z2 = true;
        } else {
            boolean z3 = System.currentTimeMillis() - d[i] > 60000;
            z2 = (jSONObject == null || f3357c[i] == jSONObject.toString().hashCode()) ? false : true;
            de.a("exec " + i + ", " + z3 + ", " + z2, null);
            if (!z3) {
                if (userProfileCallback != null) {
                    userProfileCallback.onFail(4);
                    return;
                }
                return;
            }
        }
        if (!z2) {
            if (userProfileCallback != null) {
                userProfileCallback.onSuccess();
                return;
            }
            return;
        }
        Context context = AppLog.getContext();
        String did = AppLog.getDid();
        String aid = AppLog.getAid();
        String profileUri = dgVar.c().getProfileUri();
        if (TextUtils.isEmpty(did) || TextUtils.isEmpty(aid) || TextUtils.isEmpty(profileUri)) {
            if (userProfileCallback != null) {
                userProfileCallback.onFail(3);
                return;
            }
            return;
        }
        String str = profileUri + String.format("/service/api/v3/userprofile/%s/%s", aid, f3356b[i]);
        JSONObject jSONObject3 = new JSONObject();
        try {
            JSONObject header = AppLog.getHeader();
            try {
                jSONObject2 = new JSONObject(header, f3355a);
                jSONObject2.put(PluginConstants.KEY_SDK_VERSION, header.opt(PluginConstants.KEY_SDK_VERSION).toString());
                jSONObject2.put("tz_offset", header.opt("tz_offset").toString());
            } catch (JSONException e) {
                de.a("", e);
                jSONObject2 = new JSONObject();
            }
            jSONObject3.put("header", jSONObject2);
            jSONObject3.put(IAppAuthService.Scope.PROFILE, jSONObject);
            JSONObject header2 = AppLog.getHeader();
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put(com.bytedance.b.a.c.h.x, header2.opt(com.bytedance.b.a.c.h.x));
            jSONObject4.put("user_id", header2.opt("user_id"));
            jSONObject4.put("ssid", header2.opt("ssid"));
            jSONObject3.put("user", jSONObject4);
        } catch (JSONException e2) {
            de.a("", e2);
        }
        ba baVar = new ba(str, aid, jSONObject3.toString(), new a(i, jSONObject, userProfileCallback), context);
        if (handler != null) {
            handler.post(baVar);
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            de.a("U SHALL NOT PASS!", null);
        }
        baVar.run();
    }
}
