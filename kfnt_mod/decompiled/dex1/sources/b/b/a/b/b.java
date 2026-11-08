package b.b.a.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import b.b.a.k.v;
import com.bytedance.b.a.c.h;
import com.bytedance.sdk.account.platform.api.IAppAuthService;
import com.bytedance.sdk.account.platform.api.IGoogleService;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f1034a = {"aid", "region", "os", "package", "app_version", PluginConstants.KEY_SDK_VERSION, "os_version", "device_model", "resolution", "language", "timezone", "access", IGoogleService.ResponseKey.DISPLAY_NAME, "channel", DispatchConstants.CARRIER, "app_language", "app_region", "tz_name", "tz_offset", "install_id", "openudid", "mcc_mnc", "rom", "manifest_version_code", "device_manufacturer", "clientudid", "sig_hash", "display_density", "os_api", "update_version_code", "density_dpi", "version_code", "sim_serial_number", "release_build", "udid", "cpu_abi", "google_aid"};

    /* renamed from: b, reason: collision with root package name */
    public static final String[] f1035b = {"setOnce", "synchronize"};

    /* renamed from: c, reason: collision with root package name */
    public static final int[] f1036c = {-1, -1};
    public static final long[] d = {-1, -1};

    public static void a(b.b.a.d.b bVar, int i, JSONObject jSONObject, b.b.a.b.a aVar, Handler handler, boolean z) {
        boolean z2;
        JSONObject jSONObject2;
        if (z) {
            z2 = true;
        } else {
            boolean z3 = System.currentTimeMillis() - d[i] > 60000;
            z2 = (jSONObject == null || f1036c[i] == jSONObject.toString().hashCode()) ? false : true;
            v.a("exec " + i + ", " + z3 + ", " + z2, (Throwable) null);
            if (!z3) {
                if (aVar != null) {
                    ((a) aVar).a(4);
                    return;
                }
                return;
            }
        }
        if (!z2) {
            if (aVar != null) {
                ((a) aVar).a();
                return;
            }
            return;
        }
        Context contextA = com.apm.applog.a.a();
        String strOptString = bVar.g.d.optString(h.x, "");
        String strF = bVar.g.f();
        String strG = bVar.c().g();
        if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strF) || TextUtils.isEmpty(strG)) {
            if (aVar != null) {
                ((a) aVar).a(3);
                return;
            }
            return;
        }
        String str = strG + String.format("/service/api/v3/userprofile/%s/%s", strF, f1035b[i]);
        JSONObject jSONObject3 = new JSONObject();
        try {
            JSONObject jSONObjectA = bVar.g.a();
            try {
                jSONObject2 = new JSONObject(jSONObjectA, f1034a);
                jSONObject2.put(PluginConstants.KEY_SDK_VERSION, jSONObjectA.opt(PluginConstants.KEY_SDK_VERSION).toString());
                jSONObject2.put("tz_offset", jSONObjectA.opt("tz_offset").toString());
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject2 = new JSONObject();
            }
            jSONObject3.put("header", jSONObject2);
            jSONObject3.put(IAppAuthService.Scope.PROFILE, jSONObject);
            JSONObject jSONObjectA2 = bVar.g.a();
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put(h.x, jSONObjectA2.opt(h.x));
            jSONObject4.put("user_id", jSONObjectA2.opt("user_id"));
            jSONObject4.put("ssid", jSONObjectA2.opt("ssid"));
            jSONObject3.put("user", jSONObject4);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        e eVar = new e(str, strF, jSONObject3.toString(), new a(i, jSONObject, aVar), contextA);
        if (handler != null) {
            handler.post(eVar);
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            v.a((Throwable) null);
        }
        eVar.run();
    }

    public static class a implements b.b.a.b.a {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1037a;

        /* renamed from: b, reason: collision with root package name */
        public final /* synthetic */ JSONObject f1038b;

        /* renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.b.a.b.a f1039c;

        public a(int i, JSONObject jSONObject, b.b.a.b.a aVar) {
            this.f1037a = i;
            this.f1038b = jSONObject;
            this.f1039c = aVar;
        }

        public void a() {
            b.f1036c[this.f1037a] = this.f1038b.toString().hashCode();
            b.d[this.f1037a] = System.currentTimeMillis();
            ((a) this.f1039c).a();
        }

        public void a(int i) {
            ((a) this.f1039c).a(i);
        }
    }
}
