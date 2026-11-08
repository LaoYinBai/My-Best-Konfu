package b.b.a.i;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import anet.channel.strategy.dispatch.DispatchConstants;
import b.b.a.c;
import b.b.a.i;
import b.b.a.k.p;
import b.b.a.k.v;
import b.b.a.k.x;
import com.apm.applog.e;
import com.bytedance.b.a.c.h;
import com.bytedance.common.utility.NetworkUtils;
import com.bytedance.common.utility.UIUtils;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static volatile c f1098a;

    /* renamed from: b, reason: collision with root package name */
    public static final a[] f1099b = {new a("aid", "aid", String.class), new a("google_aid", "google_aid", String.class), new a(DispatchConstants.CARRIER, DispatchConstants.CARRIER, String.class), new a("mcc_mnc", "mcc_mnc", String.class), new a("sim_region", "sim_region", String.class), new a(h.x, h.x, String.class), new a("bd_did", "bd_did", String.class), new a("install_id", "iid", String.class), new a("clientudid", "clientudid", String.class), new a(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, String.class), new a("app_version", "version_name", String.class), new a("version_code", "version_code", Integer.class), new a("manifest_version_code", "manifest_version_code", Integer.class), new a("update_version_code", "update_version_code", Integer.class), new a("sdk_version_code", "sdk_version_code", Integer.class)};

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public final String f1100a;

        /* renamed from: b, reason: collision with root package name */
        public final String f1101b;

        /* renamed from: c, reason: collision with root package name */
        public final Class f1102c;

        public a(String str, String str2, Class cls) {
            this.f1100a = str;
            this.f1101b = str2;
            this.f1102c = cls;
        }
    }

    public static String a(Context context, JSONObject jSONObject, String str, boolean z, i iVar) {
        if (context == null || TextUtils.isEmpty(str)) {
            return str;
        }
        Uri uri = Uri.parse(str);
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        Uri.Builder builderBuildUpon = uri.buildUpon();
        HashMap map = new HashMap();
        a(context, jSONObject, z, map, iVar);
        for (Map.Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            if (!queryParameterNames.contains(str2) && !TextUtils.isEmpty(str3)) {
                builderBuildUpon.appendQueryParameter(str2, (String) entry.getValue());
            }
        }
        return builderBuildUpon.build().toString();
    }

    public static void a(Context context, JSONObject jSONObject, boolean z, Map<String, String> map, i iVar) {
        if (context == null || map == null || iVar == null) {
            return;
        }
        map.put("_rticket", String.valueOf(System.currentTimeMillis()));
        map.put("device_platform", DispatchConstants.ANDROID);
        if (z) {
            map.put("ssmix", "a");
        }
        String screenResolution = UIUtils.getScreenResolution(context);
        if (!TextUtils.isEmpty(screenResolution)) {
            map.put("resolution", screenResolution);
        }
        int dpi = UIUtils.getDpi(context);
        if (dpi > 0) {
            map.put("dpi", String.valueOf(dpi));
        }
        map.put("device_type", Build.MODEL);
        map.put("device_brand", Build.BRAND);
        map.put("language", context.getResources().getConfiguration().locale.getLanguage());
        map.put("os_api", String.valueOf(Build.VERSION.SDK_INT));
        String strSubstring = Build.VERSION.RELEASE;
        int i = 0;
        if (strSubstring != null && strSubstring.length() > 10) {
            strSubstring = strSubstring.substring(0, 10);
        }
        map.put("os_version", strSubstring);
        String networkAccessType = NetworkUtils.getNetworkAccessType(context);
        if (!TextUtils.isEmpty(networkAccessType)) {
            map.put("ac", networkAccessType);
        }
        while (true) {
            a[] aVarArr = f1099b;
            if (i >= aVarArr.length) {
                break;
            }
            a aVar = aVarArr[i];
            Object objA = a(jSONObject, aVar.f1100a, null, aVar.f1102c);
            if (objA != null) {
                map.put(aVar.f1101b, objA.toString());
            }
            i++;
        }
        String str = (String) a(jSONObject, "tweaked_channel", "", String.class);
        if (TextUtils.isEmpty(str)) {
            str = (String) a(jSONObject, "channel", "", String.class);
        }
        if (!TextUtils.isEmpty(str)) {
            map.put("channel", str);
        }
        String str2 = (String) a(jSONObject, "cdid", null, String.class);
        if (!TextUtils.isEmpty(str2)) {
            map.put("cdid", str2);
        }
        boolean zA = p.a(context);
        if (iVar == i.L0) {
            if (zA) {
                String str3 = (String) a(jSONObject, "mc", null, String.class);
                String str4 = (String) a(jSONObject, "udid", null, String.class);
                if (!TextUtils.isEmpty(str3)) {
                    map.put("mac_address", str3);
                }
                if (x.b(str4)) {
                    map.put("uuid", str4);
                }
            }
            String str5 = (String) a(jSONObject, "aliyun_uuid", null, String.class);
            if (!TextUtils.isEmpty(str5)) {
                map.put("aliyun_uuid", str5);
            }
        }
        String str6 = (String) a(jSONObject, "build_serial", null, String.class);
        if (!TextUtils.isEmpty(str6)) {
            map.put("build_serial", str6);
        }
        if (iVar == i.L0) {
            String str7 = (String) a(jSONObject, "openudid", null, String.class);
            if (!TextUtils.isEmpty(str7)) {
                map.put("openudid", str7);
            }
        }
        b.b.a.a aVarD = com.apm.applog.a.d();
        if (aVarD != null) {
            map.put("aid", String.valueOf(aVarD.h()));
            String strD = aVarD.d();
            if (TextUtils.isEmpty(strD)) {
                strD = aVarD.c();
            }
            if (!TextUtils.isEmpty(strD)) {
                map.put("channel", strD);
            }
            String strA = aVarD.a();
            if (!TextUtils.isEmpty(strA)) {
                map.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, strA);
            }
            map.put("version_code", String.valueOf(aVarD.e()));
            String strB = aVarD.b();
            if (!TextUtils.isEmpty(strB)) {
                map.put("version_name", strB);
            }
            map.put("manifest_version_code", String.valueOf(aVarD.g()));
            map.put("update_version_code", String.valueOf(aVarD.f()));
            String strK = aVarD.k();
            if (!TextUtils.isEmpty(strK)) {
                map.put("ab_version", strK);
            }
            String strI = aVarD.i();
            if (!TextUtils.isEmpty(strI)) {
                map.put("ab_client", strI);
            }
            String strL = aVarD.l();
            if (!TextUtils.isEmpty(strL)) {
                map.put("ab_group", strL);
            }
            String strM = aVarD.m();
            if (!TextUtils.isEmpty(strM)) {
                map.put("ab_feature", strM);
            }
            long j = aVarD.j();
            if (j > 0) {
                map.put("abflag", String.valueOf(j));
            }
        }
        if (com.apm.applog.a.a(context)) {
            map.putAll(b.b.a.a.a.a(context).d);
        }
    }

    public static String[] a(b.b.a.d.b bVar, JSONObject jSONObject, boolean z) {
        e eVarC = bVar.c();
        String[] strArrD = z ? eVarC.d() : eVarC.c();
        int length = strArrD.length;
        String[] strArr = new String[length];
        boolean zF = com.apm.applog.a.f();
        for (int i = 0; i < length; i++) {
            strArr[i] = strArrD[i];
            if (zF) {
                strArr[i] = b.a.a.a.a.a(new StringBuilder(), strArr[i], "?tt_data=a");
            }
            strArr[i] = a((Context) bVar.f1054c, jSONObject, strArr[i], true, i.L1);
            String string = strArr[i];
            String[] strArr2 = b.b.a.i.a.d;
            if (!TextUtils.isEmpty(string)) {
                Uri uri = Uri.parse(string);
                HashMap map = new HashMap(strArr2.length);
                for (String str : strArr2) {
                    String queryParameter = uri.getQueryParameter(str);
                    if (!TextUtils.isEmpty(queryParameter)) {
                        map.put(str, queryParameter);
                    }
                }
                Uri.Builder builderBuildUpon = uri.buildUpon();
                builderBuildUpon.clearQuery();
                for (String str2 : map.keySet()) {
                    builderBuildUpon.appendQueryParameter(str2, (String) map.get(str2));
                }
                string = builderBuildUpon.build().toString();
            }
            strArr[i] = string;
        }
        return strArr;
    }

    @Nullable
    public static <T> T a(JSONObject jSONObject, String str, T t, Class<T> cls) {
        T tCast;
        Object objOpt = jSONObject.opt(str);
        if (objOpt == null || cls == null) {
            tCast = null;
        } else {
            try {
                tCast = cls.cast(objOpt);
            } catch (Throwable th) {
                v.a(th);
            }
        }
        return tCast == null ? t : tCast;
    }
}
