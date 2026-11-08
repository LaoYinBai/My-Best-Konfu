package bykvm_19do.bykvm_19do.bykvm_19do;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class s {

    /* renamed from: b, reason: collision with root package name */
    public static JSONObject f1374b;

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f1373a = {"GET", "POST"};

    /* renamed from: c, reason: collision with root package name */
    private static final String[] f1375c = {"aid", "app_version", "tt_data"};
    static final String[] d = {"aid", "version_code", "ab_client", "ab_version", "ab_feature", "ab_group", "iid", "device_platform"};
    public static final String[] e = {"tt_data", "device_platform"};

    private static String a(String str) {
        if (TextUtils.isEmpty(str) || !a.h()) {
            return str;
        }
        Uri uri = Uri.parse(str);
        String query = uri.getQuery();
        ArrayList<Pair> arrayList = new ArrayList();
        for (String str2 : f1375c) {
            String queryParameter = uri.getQueryParameter(str2);
            if (!TextUtils.isEmpty(queryParameter)) {
                arrayList.add(new Pair(str2, queryParameter));
            }
        }
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.clearQuery();
        for (Pair pair : arrayList) {
            builderBuildUpon.appendQueryParameter((String) pair.first, (String) pair.second);
        }
        builderBuildUpon.appendQueryParameter("tt_info", new String(Base64.encode(a.a(query), 8)));
        return builderBuildUpon.build().toString();
    }

    public static boolean b(String str, JSONObject jSONObject) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        float rawOffset = (TimeZone.getDefault().getRawOffset() * 1.0f) / 3600000.0f;
        if (rawOffset < -12.0f) {
            rawOffset = -12.0f;
        }
        if (rawOffset > 12.0f) {
            rawOffset = 12.0f;
        }
        a(sb, "timezone", rawOffset + "");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("sim_serial_number");
        if (jSONArrayOptJSONArray != null) {
            try {
                String strOptString = ((JSONObject) jSONArrayOptJSONArray.get(0)).optString("sim_serial_number");
                for (int i = 1; i < jSONArrayOptJSONArray.length(); i++) {
                    strOptString = strOptString + IWeiboService.Scope.EMPTY_SCOPE + ((JSONObject) jSONArrayOptJSONArray.get(i)).optString("sim_serial_number");
                }
                a(sb, "sim_serial_number", strOptString);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        String strA = a(sb.toString());
        JSONObject jSONObject2 = null;
        String strA2 = a(0, strA, null, null);
        if (strA2 != null) {
            try {
                jSONObject2 = new JSONObject(strA2);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        return jSONObject2 != null && "success".equals(jSONObject2.optString("message", ""));
    }

    public static JSONObject c(String str, JSONObject jSONObject) {
        JSONObject jSONObject2;
        String strA = a(1, str, null, a.a(jSONObject.toString()));
        if (strA != null) {
            try {
                jSONObject2 = new JSONObject(strA);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            jSONObject2 = null;
        }
        if (jSONObject2 != null && "ss_app_log".equals(jSONObject2.optString("magic_tag", ""))) {
            return jSONObject2.optJSONObject("config");
        }
        return null;
    }

    public static JSONObject d(String str, JSONObject jSONObject) {
        JSONObject jSONObject2;
        String strA = a(1, str, null, a.a(jSONObject.toString()));
        if (strA != null) {
            try {
                jSONObject2 = new JSONObject(strA);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            jSONObject2 = null;
        }
        if (jSONObject2 != null && "success".equals(jSONObject2.optString("message", ""))) {
            return jSONObject2.optJSONObject("data");
        }
        return null;
    }

    public static String a(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Uri uri = Uri.parse(str);
        HashMap map = new HashMap(strArr.length);
        for (String str2 : strArr) {
            String queryParameter = uri.getQueryParameter(str2);
            if (!TextUtils.isEmpty(queryParameter)) {
                map.put(str2, queryParameter);
            }
        }
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.clearQuery();
        for (String str3 : map.keySet()) {
            builderBuildUpon.appendQueryParameter(str3, (String) map.get(str3));
        }
        return builderBuildUpon.build().toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01b0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01cd A[EXC_TOP_SPLITTER, PHI: r7 r9
  0x01cd: PHI (r7v3 java.io.BufferedReader) = (r7v2 java.io.BufferedReader), (r7v13 java.io.BufferedReader) binds: [B:81:0x01cb, B:69:0x01b3] A[DONT_GENERATE, DONT_INLINE]
  0x01cd: PHI (r9v3 java.lang.String) = (r9v2 java.lang.String), (r9v11 java.lang.String) binds: [B:81:0x01cb, B:69:0x01b3] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(int r6, java.lang.String r7, java.util.HashMap<java.lang.String, java.lang.String> r8, byte[] r9) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 501
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_19do.s.a(int, java.lang.String, java.util.HashMap, byte[]):java.lang.String");
    }

    public static JSONObject a(String str, JSONObject jSONObject) {
        HashMap map = new HashMap(2);
        if (a.h()) {
            map.put("Content-Type", "application/octet-stream;tt-data=a");
        } else {
            map.put("Content-Type", com.bytedance.b.a.a.c.e.f3610c);
        }
        String strA = a(1, a(str), map, a.a(jSONObject.toString()));
        if (strA == null) {
            return null;
        }
        try {
            return new JSONObject(strA);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    protected static void a(StringBuilder sb, String str, String str2) {
        if (sb == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (sb.toString().indexOf(63) < 0) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        sb.append(str);
        sb.append("=");
        sb.append(Uri.encode(str2));
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(java.lang.String[] r8, byte[] r9, bykvm_19do.bykvm_19do.bykvm_19do.i2 r10) throws org.json.JSONException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_19do.s.a(java.lang.String[], byte[], bykvm_19do.bykvm_19do.bykvm_19do.i2):int");
    }
}
