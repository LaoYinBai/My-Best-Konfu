package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.appsflyer.share.Constants;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class t {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f1381a = {"ab_version", "device_brand", "language", "os_api", "resolution", "google_aid", "build_serial", DispatchConstants.CARRIER, "install_id", "package", "app_version", "device_model", "udid", "density_dpi", "aliyun_uuid", "mcc_mnc", "sim_region", "ab_client", "ab_group", "ab_feature", com.bytedance.b.a.c.h.x, "openudid", "clientudid", "aid"};

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f1382b = {"ab_version", "device_brand", "language", "os_api", "resolution", "google_aid", "build_serial", DispatchConstants.CARRIER, "iid", TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, "version_name", "device_type", "uuid", "dpi", "aliyun_uuid", "mcc_mnc", "sim_region", "ab_client", "ab_group", "ab_feature", com.bytedance.b.a.c.h.x, "openudid", "clientudid", "aid"};

    public static String a(Context context, JSONObject jSONObject, String str, boolean z, b bVar) {
        HashMap<String, String> mapA;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        HashMap map = new HashMap(f1381a.length + 10);
        int i = 0;
        while (true) {
            String[] strArr = f1381a;
            if (i >= strArr.length) {
                break;
            }
            String strOptString = jSONObject.optString(strArr[i], null);
            if (!TextUtils.isEmpty(strOptString)) {
                map.put(f1382b[i], strOptString);
            }
            i++;
        }
        if (bVar != null) {
            try {
                mapA = bVar.a(context);
            } catch (Exception e) {
                l0.a(e);
            }
        } else {
            mapA = null;
        }
        if (m0.b(context) && mapA != null) {
            map.putAll(mapA);
        }
        try {
            HashMap<String, String> mapA2 = a.i() == null ? null : a.i().a();
            if (mapA2 != null) {
                map.putAll(mapA2);
            }
        } catch (Exception e2) {
            l0.a(e2);
        }
        if (a.f.size() > 0) {
            map.putAll(a.f);
        }
        if (z) {
            map.put("ssmix", "a");
        }
        String strB = h0.b(context);
        if (!TextUtils.isEmpty(strB)) {
            map.put("ac", strB);
        }
        String str2 = (String) a.a("tweaked_channel", "");
        if (TextUtils.isEmpty(str2)) {
            str2 = (String) a.a("channel", "");
        }
        if (!TextUtils.isEmpty(str2)) {
            map.put("channel", str2);
        }
        String strOptString2 = jSONObject.optString("os_version", null);
        if (strOptString2 != null && strOptString2.length() > 10) {
            strOptString2 = strOptString2.substring(0, 10);
        }
        map.put("os_version", strOptString2);
        map.put("_rticket", String.valueOf(System.currentTimeMillis()));
        map.put("device_platform", DispatchConstants.ANDROID);
        int iIntValue = ((Integer) a.a("version_code", -1)).intValue();
        if (iIntValue != -1) {
            map.put("version_code", String.valueOf(iIntValue));
        }
        int iIntValue2 = ((Integer) a.a("manifest_version_code", -1)).intValue();
        if (iIntValue2 != -1) {
            map.put("manifest_version_code", String.valueOf(iIntValue2));
        }
        int iIntValue3 = ((Integer) a.a("update_version_code", -1)).intValue();
        if (iIntValue3 != -1) {
            map.put("update_version_code", String.valueOf(iIntValue3));
        }
        String strA = s0.a(jSONObject.optJSONObject("oaid"));
        if (!TextUtils.isEmpty(strA)) {
            map.put("oaid", strA);
        }
        String strOptString3 = jSONObject.optString("cdid");
        if (!TextUtils.isEmpty(strOptString3)) {
            map.put("cdid", strOptString3);
        }
        if (!str.endsWith(Constants.URL_PATH_DELIMITER)) {
            str = str + Constants.URL_PATH_DELIMITER;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.indexOf(63) >= 0 ? '&' : '?');
        return m0.a(sb.toString(), map, "UTF-8");
    }

    public static String[] a(u1 u1Var, Context context, JSONObject jSONObject) {
        String[] strArrC = u1Var.f().c();
        String[] strArr = new String[strArrC.length];
        String str = a.h() ? "?tt_data=a" : "?";
        for (int i = 0; i < strArrC.length; i++) {
            strArr[i] = a(context, jSONObject, strArrC[i] + str, true, a.j());
            strArr[i] = s.a(strArr[i], s.d);
        }
        return strArr;
    }
}
