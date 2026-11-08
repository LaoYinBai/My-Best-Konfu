package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.idtracking.e;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static String a = null;
    public static String b = "";
    private static final String c = "EnvelopeManager";
    private static String d;
    private static boolean f;
    private int e = 0;

    public static long a(Context context) {
        long j = DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX - DataHelper.ENVELOPE_EXTRA_LENGTH;
        JSONObject jSONObjectB = b(context);
        if (jSONObjectB != null && jSONObjectB.toString() != null && jSONObjectB.toString().getBytes() != null) {
            long length = jSONObjectB.toString().getBytes().length;
            if (ULog.DEBUG) {
                Log.i(c, "headerLen size is " + length);
            }
            j -= length;
        }
        if (ULog.DEBUG) {
            Log.i(c, "free size is " + j);
        }
        return j;
    }

    private JSONObject a(int i, JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            try {
                jSONObject.put(com.umeng.commonsdk.framework.c.c, i);
            } catch (Exception unused) {
            }
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(com.umeng.commonsdk.framework.c.c, i);
        } catch (Exception unused2) {
        }
        return jSONObject2;
    }

    public JSONObject a(Context context, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        JSONObject jSONObject3;
        String str;
        String string;
        Envelope envelopeA;
        String str2;
        String str3;
        if (ULog.DEBUG && jSONObject != null && jSONObject2 != null) {
            Log.i(c, "headerJSONObject size is " + jSONObject.toString().getBytes().length);
            Log.i(c, "bodyJSONObject size is " + jSONObject2.toString().getBytes().length);
        }
        JSONObject jSONObject4 = null;
        if (context == null || jSONObject2 == null) {
            return a(110, (JSONObject) null);
        }
        try {
            JSONObject jSONObjectB = b(context);
            if (jSONObjectB != null && jSONObject != null) {
                jSONObjectB = a(jSONObjectB, jSONObject);
            }
            if (jSONObjectB != null && jSONObject2 != null) {
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (next != null && (next instanceof String) && (str3 = next) != null && jSONObject2.opt(str3) != null) {
                        try {
                            jSONObjectB.put(str3, jSONObject2.opt(str3));
                        } catch (Exception unused) {
                        }
                    }
                }
            }
            if (jSONObjectB != null) {
                StringBuilder sb = new StringBuilder();
                if (jSONObjectB.length() > 0) {
                    if (jSONObjectB.has("push")) {
                        String strOptString = jSONObjectB.optJSONObject("header").optString(o.ay);
                        if (!TextUtils.isEmpty("p") && !TextUtils.isEmpty(strOptString)) {
                            sb.append("p");
                            sb.append("==");
                            sb.append(strOptString);
                            sb.append("&=");
                        }
                    }
                    if (jSONObjectB.has("share")) {
                        String strOptString2 = jSONObjectB.optJSONObject("header").optString(o.az);
                        if (!TextUtils.isEmpty(o.at) && !TextUtils.isEmpty(strOptString2)) {
                            sb.append(o.at);
                            sb.append("==");
                            sb.append(strOptString2);
                            sb.append("&=");
                        }
                    }
                    if (jSONObjectB.has("analytics")) {
                        if (jSONObjectB.has(o.al)) {
                            str2 = "ad";
                        } else {
                            str2 = o.ap;
                            if (jSONObjectB.optJSONObject("header").has("st")) {
                                str2 = o.av;
                            }
                        }
                        String strOptString3 = jSONObjectB.optJSONObject("header").optString("sdk_version");
                        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(strOptString3)) {
                            sb.append(str2);
                            sb.append("==");
                            sb.append(strOptString3);
                            sb.append("&=");
                        }
                    }
                    if (jSONObjectB.has(o.al)) {
                        String strOptString4 = jSONObjectB.optJSONObject("header").optString("sdk_version");
                        if (jSONObjectB.has("analytics")) {
                            if (!sb.toString().contains("ad") && !TextUtils.isEmpty("ad") && !TextUtils.isEmpty(strOptString4)) {
                                sb.append("ad");
                                sb.append("==");
                                sb.append(strOptString4);
                                sb.append("&=");
                            }
                        } else if (!TextUtils.isEmpty(o.aq) && !TextUtils.isEmpty(strOptString4)) {
                            sb.append(o.aq);
                            sb.append("==");
                            sb.append(strOptString4);
                            sb.append("&=");
                        }
                    }
                    if (jSONObjectB.has(o.ao)) {
                        String strOptString5 = jSONObjectB.optJSONObject("header").optString(o.aA);
                        if (!TextUtils.isEmpty(o.au) && !TextUtils.isEmpty(strOptString5)) {
                            sb.append(o.au);
                            sb.append("==");
                            sb.append(strOptString5);
                            sb.append("&=");
                        }
                    }
                }
                string = sb.toString();
                if (TextUtils.isEmpty(string)) {
                    return a(101, jSONObjectB);
                }
                if (string.endsWith("&=")) {
                    string = string.substring(0, string.length() - 2);
                }
            } else {
                string = null;
            }
            if (jSONObjectB != null) {
                try {
                    e eVarA = e.a(context);
                    if (eVarA != null) {
                        eVarA.a();
                        String strEncodeToString = Base64.encodeToString(new ac().a(eVarA.b()), 0);
                        if (!TextUtils.isEmpty(strEncodeToString)) {
                            JSONObject jSONObject5 = jSONObjectB.getJSONObject("header");
                            jSONObject5.put(o.V, strEncodeToString);
                            jSONObjectB.put("header", jSONObject5);
                        }
                    }
                } catch (Exception unused2) {
                }
            }
            if (jSONObjectB != null && DataHelper.largeThanMaxSize(jSONObjectB.toString().getBytes().length, DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX)) {
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putInt("serial", sharedPreferences.getInt("serial", 1) + 1).commit();
                }
                return a(113, jSONObjectB);
            }
            if (jSONObjectB != null) {
                envelopeA = a(context, jSONObjectB.toString().getBytes());
                if (envelopeA == null) {
                    return a(111, jSONObjectB);
                }
            } else {
                envelopeA = null;
            }
            if (envelopeA != null && DataHelper.largeThanMaxSize(envelopeA.toBinary().length, DataHelper.ENVELOPE_LENGTH_MAX)) {
                return a(114, jSONObjectB);
            }
            int iA = a(context, envelopeA, string, jSONObjectB != null ? jSONObjectB.optJSONObject("header").optString("app_version") : null);
            if (iA != 0) {
                return a(iA, jSONObjectB);
            }
            if (ULog.DEBUG) {
                Log.i(c, "constructHeader size is " + jSONObjectB.toString().getBytes().length);
            }
            return jSONObjectB;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
            if (jSONObject != null) {
                try {
                    jSONObject3 = new JSONObject();
                } catch (Exception e) {
                    e = e;
                }
                try {
                    jSONObject3.put("header", jSONObject);
                } catch (JSONException unused3) {
                } catch (Exception e2) {
                    e = e2;
                    jSONObject4 = jSONObject3;
                    UMCrashManager.reportCrash(context, e);
                    return a(110, jSONObject4);
                }
                jSONObject4 = jSONObject3;
            }
            if (jSONObject2 != null) {
                if (jSONObject4 == null) {
                    jSONObject4 = new JSONObject();
                }
                if (jSONObject2 != null) {
                    Iterator<String> itKeys2 = jSONObject2.keys();
                    while (itKeys2.hasNext()) {
                        String next2 = itKeys2.next();
                        if (next2 != null && (next2 instanceof String) && (str = next2) != null && jSONObject2.opt(str) != null) {
                            try {
                                jSONObject4.put(str, jSONObject2.opt(str));
                            } catch (Exception unused4) {
                            }
                        }
                    }
                }
            }
            return a(110, jSONObject4);
        }
    }

    private static JSONObject b(Context context) {
        SharedPreferences sharedPreferences;
        JSONObject jSONObject;
        Class<?> cls;
        try {
            sharedPreferences = PreferenceWrapper.getDefault(context);
            if (!TextUtils.isEmpty(d)) {
                try {
                    jSONObject = new JSONObject(d);
                } catch (Exception unused) {
                    jSONObject = null;
                }
            } else {
                jSONObject = new JSONObject();
                jSONObject.put(o.o, DeviceConfig.getAppMD5Signature(context));
                jSONObject.put(o.p, DeviceConfig.getAppSHA1Key(context));
                jSONObject.put(o.q, DeviceConfig.getAppHashKey(context));
                jSONObject.put("app_version", DeviceConfig.getAppVersionName(context));
                jSONObject.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(context)));
                jSONObject.put("idmd5", DeviceConfig.getDeviceIdUmengMD5(context));
                jSONObject.put(o.v, DeviceConfig.getCPU());
                String mccmnc = DeviceConfig.getMCCMNC(context);
                if (!TextUtils.isEmpty(mccmnc)) {
                    jSONObject.put(o.A, mccmnc);
                    b = mccmnc;
                } else {
                    jSONObject.put(o.A, "");
                }
                String subOSName = DeviceConfig.getSubOSName(context);
                if (!TextUtils.isEmpty(subOSName)) {
                    jSONObject.put(o.J, subOSName);
                }
                String subOSVersion = DeviceConfig.getSubOSVersion(context);
                if (!TextUtils.isEmpty(subOSVersion)) {
                    jSONObject.put(o.K, subOSVersion);
                }
                String deviceType = DeviceConfig.getDeviceType(context);
                if (!TextUtils.isEmpty(deviceType)) {
                    jSONObject.put(o.ae, deviceType);
                }
                jSONObject.put("package_name", DeviceConfig.getPackageName(context));
                jSONObject.put(o.t, "Android");
                jSONObject.put("device_id", DeviceConfig.getDeviceId(context));
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.k)) {
                    jSONObject.put(o.C, Build.MODEL);
                    jSONObject.put(o.D, Build.BOARD);
                    jSONObject.put(o.E, Build.BRAND);
                    jSONObject.put(o.F, Build.TIME);
                    jSONObject.put(o.G, Build.MANUFACTURER);
                    jSONObject.put(o.H, Build.ID);
                    jSONObject.put(o.I, Build.DEVICE);
                    jSONObject.put(o.x, Build.VERSION.RELEASE);
                }
                jSONObject.put("os", "Android");
                int[] resolutionArray = DeviceConfig.getResolutionArray(context);
                if (resolutionArray != null) {
                    jSONObject.put(o.y, resolutionArray[1] + "*" + resolutionArray[0]);
                }
                jSONObject.put(o.z, DeviceConfig.getMac(context));
                jSONObject.put(o.L, DeviceConfig.getTimeZone(context));
                String[] localeInfo = DeviceConfig.getLocaleInfo(context);
                jSONObject.put(o.N, localeInfo[0]);
                jSONObject.put(o.M, localeInfo[1]);
                jSONObject.put(o.O, DeviceConfig.getNetworkOperatorName(context));
                jSONObject.put(o.r, DeviceConfig.getAppName(context));
                String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(context);
                if ("Wi-Fi".equals(networkAccessMode[0])) {
                    jSONObject.put(o.P, UtilityImpl.NET_TYPE_WIFI);
                } else if ("2G/3G".equals(networkAccessMode[0])) {
                    jSONObject.put(o.P, "2G/3G");
                } else {
                    jSONObject.put(o.P, "unknow");
                }
                if (!"".equals(networkAccessMode[1])) {
                    jSONObject.put(o.Q, networkAccessMode[1]);
                }
                jSONObject.put(o.b, SdkVersion.SDK_VERSION);
                jSONObject.put(o.c, SdkVersion.SDK_TYPE);
                if (!TextUtils.isEmpty(a)) {
                    jSONObject.put(o.d, a);
                }
                jSONObject.put(o.af, Build.VERSION.SDK_INT);
                jSONObject.put(o.ag, UMUtils.getOaidRequiredTime(context));
                d = jSONObject.toString();
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
        if (jSONObject == null) {
            return null;
        }
        try {
            jSONObject.put(o.R, sharedPreferences.getInt("successful_request", 0));
            jSONObject.put(o.S, sharedPreferences.getInt(o.S, 0));
            jSONObject.put(o.T, sharedPreferences.getInt("last_request_spent_ms", 0));
            if (UMConfigure.sShouldCollectZData) {
                String zData = UMUtils.getZData(context);
                if (!TextUtils.isEmpty(zData)) {
                    jSONObject.put(o.ah, zData);
                    jSONObject.put(o.ai, UMUtils.getZDataRequestTime(context));
                    jSONObject.put(o.aj, com.umeng.commonsdk.internal.c.a());
                }
            }
        } catch (Exception unused2) {
        }
        jSONObject.put("channel", UMUtils.getChannel(context));
        jSONObject.put("appkey", UMUtils.getAppkey(context));
        try {
            String deviceToken = UMUtils.getDeviceToken(context);
            if (!TextUtils.isEmpty(deviceToken)) {
                jSONObject.put(o.a, deviceToken);
            }
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
        }
        try {
            if (SdkVersion.SDK_TYPE != 1) {
                try {
                    cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                } catch (Throwable unused3) {
                }
                String str = cls != null ? (String) cls.getMethod("getUmtt", Context.class).invoke(cls, context) : null;
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put(o.e, str);
                }
            }
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
        }
        try {
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(context, "umid", null);
            if (!TextUtils.isEmpty(strImprintProperty)) {
                jSONObject.put("umid", strImprintProperty);
            }
        } catch (Exception e3) {
            UMCrashManager.reportCrash(context, e3);
        }
        try {
            jSONObject.put(o.h, a.a);
            jSONObject.put(o.i, a.b);
        } catch (Exception unused4) {
        }
        byte[] bArrA = ImprintHandler.getImprintService(context).a();
        if (bArrA != null && bArrA.length > 0) {
            try {
                jSONObject.put(o.U, Base64.encodeToString(bArrA, 0));
            } catch (JSONException e4) {
                UMCrashManager.reportCrash(context, e4);
            }
        }
        if (jSONObject != null && jSONObject.length() > 0) {
            return new JSONObject().put("header", jSONObject);
        }
        return null;
    }

    private JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        String str;
        if (jSONObject != null && jSONObject2 != null && jSONObject.opt("header") != null && (jSONObject.opt("header") instanceof JSONObject)) {
            JSONObject jSONObject3 = (JSONObject) jSONObject.opt("header");
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next != null && (next instanceof String) && (str = next) != null && jSONObject2.opt(str) != null) {
                    try {
                        jSONObject3.put(str, jSONObject2.opt(str));
                        if (str.equals("vertical_type") && (jSONObject2.opt(str) instanceof Integer)) {
                            this.e = ((Integer) jSONObject2.opt(str)).intValue();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return jSONObject;
    }

    private Envelope a(Context context, byte[] bArr) {
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(context, "codex", null);
        int iIntValue = -1;
        try {
            if (!TextUtils.isEmpty(strImprintProperty)) {
                iIntValue = Integer.valueOf(strImprintProperty).intValue();
            }
        } catch (NumberFormatException e) {
            UMCrashManager.reportCrash(context, e);
        }
        if (iIntValue == 0) {
            return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (iIntValue == 1) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (f) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
    }

    private int a(Context context, Envelope envelope, String str, String str2) {
        if (context == null || envelope == null || TextUtils.isEmpty(str)) {
            return 101;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = DeviceConfig.getAppVersionName(context);
        }
        return UMFrUtils.saveEnvelopeFile(context, str + "&&" + str2 + "_" + System.currentTimeMillis() + "_envelope.log", envelope.toBinary());
    }

    public static void a(boolean z) {
        f = z;
    }
}
