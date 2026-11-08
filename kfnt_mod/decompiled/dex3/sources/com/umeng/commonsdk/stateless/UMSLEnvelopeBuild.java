package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UMSLEnvelopeBuild {
    private static final String TAG = "UMSLEnvelopeBuild";
    private static String cacheSystemheader;
    private static boolean isEncryptEnabled;
    public static Context mContext;
    public static String module;

    public synchronized JSONObject buildSLBaseHeader(Context context) {
        JSONObject jSONObject;
        Class<?> cls;
        ULog.i("walle", "[stateless] begin build hader, thread is " + Thread.currentThread());
        if (context == null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        try {
            if (!TextUtils.isEmpty(cacheSystemheader)) {
                try {
                    jSONObject = new JSONObject(cacheSystemheader);
                } catch (Exception unused) {
                    jSONObject = null;
                }
            } else {
                jSONObject = new JSONObject();
                jSONObject.put(o.o, DeviceConfig.getAppMD5Signature(applicationContext));
                jSONObject.put(o.p, DeviceConfig.getAppSHA1Key(applicationContext));
                jSONObject.put(o.q, DeviceConfig.getAppHashKey(applicationContext));
                jSONObject.put("app_version", DeviceConfig.getAppVersionName(applicationContext));
                jSONObject.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(applicationContext)));
                jSONObject.put("idmd5", DeviceConfig.getDeviceIdUmengMD5(applicationContext));
                jSONObject.put(o.v, DeviceConfig.getCPU());
                String mccmnc = DeviceConfig.getMCCMNC(applicationContext);
                if (!TextUtils.isEmpty(mccmnc)) {
                    jSONObject.put(o.A, mccmnc);
                } else {
                    jSONObject.put(o.A, "");
                }
                String subOSName = DeviceConfig.getSubOSName(applicationContext);
                if (!TextUtils.isEmpty(subOSName)) {
                    jSONObject.put(o.J, subOSName);
                }
                String subOSVersion = DeviceConfig.getSubOSVersion(applicationContext);
                if (!TextUtils.isEmpty(subOSVersion)) {
                    jSONObject.put(o.K, subOSVersion);
                }
                String deviceType = DeviceConfig.getDeviceType(applicationContext);
                if (!TextUtils.isEmpty(deviceType)) {
                    jSONObject.put(o.ae, deviceType);
                }
                jSONObject.put("package_name", DeviceConfig.getPackageName(applicationContext));
                jSONObject.put(o.t, "Android");
                jSONObject.put("device_id", DeviceConfig.getDeviceId(applicationContext));
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
                int[] resolutionArray = DeviceConfig.getResolutionArray(applicationContext);
                if (resolutionArray != null) {
                    jSONObject.put(o.y, resolutionArray[1] + "*" + resolutionArray[0]);
                }
                jSONObject.put(o.z, DeviceConfig.getMac(applicationContext));
                jSONObject.put(o.L, DeviceConfig.getTimeZone(applicationContext));
                String[] localeInfo = DeviceConfig.getLocaleInfo(applicationContext);
                jSONObject.put(o.N, localeInfo[0]);
                jSONObject.put(o.M, localeInfo[1]);
                jSONObject.put(o.O, DeviceConfig.getNetworkOperatorName(applicationContext));
                jSONObject.put(o.r, DeviceConfig.getAppName(applicationContext));
                String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(applicationContext);
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
                if (!TextUtils.isEmpty(module)) {
                    jSONObject.put(o.d, module);
                }
                jSONObject.put(o.af, Build.VERSION.SDK_INT);
                jSONObject.put(o.ag, UMUtils.getOaidRequiredTime(applicationContext));
                cacheSystemheader = jSONObject.toString();
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(applicationContext, th);
        }
        if (jSONObject == null) {
            return null;
        }
        jSONObject.put("channel", UMUtils.getChannel(applicationContext));
        jSONObject.put("appkey", UMUtils.getAppkey(applicationContext));
        try {
            if (SdkVersion.SDK_TYPE != 1) {
                try {
                    cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                } catch (Throwable unused2) {
                }
                String str = cls != null ? (String) cls.getMethod("getUmtt", Context.class).invoke(cls, applicationContext) : null;
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put(o.e, str);
                }
            }
        } catch (Exception unused3) {
        }
        try {
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(applicationContext, "umid", null);
            if (!TextUtils.isEmpty(strImprintProperty)) {
                jSONObject.put("umid", strImprintProperty);
            }
        } catch (Exception unused4) {
        }
        try {
            jSONObject.put(o.h, a.a);
            jSONObject.put(o.i, a.b);
        } catch (Exception unused5) {
        }
        if (jSONObject != null && jSONObject.length() > 0) {
            JSONObject jSONObject2 = new JSONObject();
            ULog.i("walle", "[stateless] build header end , header is " + jSONObject.toString() + ", thread is " + Thread.currentThread());
            return jSONObject2.put("header", jSONObject);
        }
        ULog.i("walle", "[stateless] build header end , header is null !!! thread is " + Thread.currentThread());
        return null;
    }

    public static boolean isReadyBuildNew(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        return com.umeng.commonsdk.framework.a.b();
    }

    private synchronized JSONObject makeErrorResult(int i, JSONObject jSONObject) {
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

    public synchronized JSONObject buildSLEnvelope(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str) {
        Context applicationContext;
        Throwable th;
        c cVarConstructEnvelope;
        String str2;
        ULog.i("walle", "[stateless] build envelope, heade is " + jSONObject.toString());
        ULog.i("walle", "[stateless] build envelope, body is " + jSONObject2.toString());
        ULog.i("walle", "[stateless] build envelope, thread is " + Thread.currentThread());
        if (context == null || jSONObject == null || jSONObject2 == null || str == null) {
            ULog.i("walle", "[stateless] build envelope, context is null or header is null or body is null");
            return makeErrorResult(110, null);
        }
        try {
            applicationContext = context.getApplicationContext();
            if (jSONObject != null && jSONObject2 != null) {
                try {
                    Iterator<String> itKeys = jSONObject2.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        if (next != null && (next instanceof String) && (str2 = next) != null && jSONObject2.opt(str2) != null) {
                            try {
                                jSONObject.put(str2, jSONObject2.opt(str2));
                            } catch (Exception unused) {
                            }
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    UMCrashManager.reportCrash(applicationContext, th);
                    ULog.i("walle", "build envelope end, thread is " + Thread.currentThread());
                    return makeErrorResult(110, null);
                }
            }
            if (jSONObject != null) {
                try {
                    com.umeng.commonsdk.statistics.idtracking.e eVarA = com.umeng.commonsdk.statistics.idtracking.e.a(applicationContext);
                    if (eVarA != null) {
                        eVarA.a();
                        String strEncodeToString = Base64.encodeToString(new ac().a(eVarA.b()), 0);
                        if (!TextUtils.isEmpty(strEncodeToString)) {
                            JSONObject jSONObject3 = jSONObject.getJSONObject("header");
                            jSONObject3.put(o.V, strEncodeToString);
                            jSONObject.put("header", jSONObject3);
                        }
                    }
                } catch (Exception unused2) {
                }
            }
            if (jSONObject != null && f.a(jSONObject.toString().getBytes().length, a.c)) {
                ULog.i("walle", "[stateless] build envelope, json overstep!!!! size is " + jSONObject.toString().getBytes().length);
                return makeErrorResult(113, jSONObject);
            }
            ULog.i("walle", "[stateless] build envelope, json size is " + jSONObject.toString().getBytes().length);
            if (jSONObject != null) {
                cVarConstructEnvelope = constructEnvelope(applicationContext, jSONObject.toString().getBytes());
                if (cVarConstructEnvelope == null) {
                    ULog.i("walle", "[stateless] build envelope, envelope is null !!!!");
                    return makeErrorResult(111, jSONObject);
                }
            } else {
                cVarConstructEnvelope = null;
            }
            if (cVarConstructEnvelope != null && f.a(cVarConstructEnvelope.b().length, a.d)) {
                ULog.i("walle", "[stateless] build envelope, envelope overstep!!!! size is " + cVarConstructEnvelope.b().length);
                return makeErrorResult(114, jSONObject);
            }
            if (!f.a(applicationContext, Base64.encodeToString(str.getBytes(), 0), Base64.encodeToString((str + "_" + System.currentTimeMillis()).getBytes(), 0), cVarConstructEnvelope.b())) {
                ULog.i("walle", "[stateless] build envelope, save fail ----->>>>>");
                return makeErrorResult(101, jSONObject);
            }
            ULog.i("walle", "[stateless] build envelope, save ok ----->>>>>");
            ULog.i("walle", "[stateless] envelope file size is " + jSONObject.toString().getBytes().length);
            new d(applicationContext);
            d.b(d.a);
            ULog.i("walle", "[stateless] build envelope end, thread is " + Thread.currentThread());
            return jSONObject;
        } catch (Throwable th3) {
            applicationContext = context;
            th = th3;
        }
    }

    private synchronized c constructEnvelope(Context context, byte[] bArr) {
        c cVarA;
        int iIntValue = -1;
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(context, "slcodex", null);
        ULog.i("walle", "[stateless] build envelope, codexStr is " + strImprintProperty);
        try {
            if (!TextUtils.isEmpty(strImprintProperty)) {
                iIntValue = Integer.valueOf(strImprintProperty).intValue();
            }
        } catch (NumberFormatException e) {
            UMCrashManager.reportCrash(context, e);
        }
        if (iIntValue == 0) {
            ULog.i("walle", "[stateless] build envelope, codexValue is 0");
            cVarA = c.a(context, UMUtils.getAppkey(context), bArr);
        } else if (iIntValue == 1) {
            ULog.i("walle", "[stateless] build envelope, codexValue is 1");
            cVarA = c.b(context, UMUtils.getAppkey(context), bArr);
        } else if (isEncryptEnabled) {
            ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is true");
            cVarA = c.b(context, UMUtils.getAppkey(context), bArr);
        } else {
            ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is false");
            cVarA = c.a(context, UMUtils.getAppkey(context), bArr);
        }
        return cVarA;
    }

    public static void setEncryptEnabled(boolean z) {
        isEncryptEnabled = z;
    }
}
