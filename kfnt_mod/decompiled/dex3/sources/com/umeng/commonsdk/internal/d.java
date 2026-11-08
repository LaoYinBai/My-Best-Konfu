package com.umeng.commonsdk.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import com.tachikoma.core.event.base.TKBaseEvent;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.utils.a;
import com.umeng.commonsdk.internal.utils.d;
import com.umeng.commonsdk.internal.utils.j;
import com.umeng.commonsdk.internal.utils.k;
import com.umeng.commonsdk.internal.utils.l;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.e;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public static void a(Context context) throws Throwable {
        try {
            ULog.i("walle", "[internal] workEvent send envelope");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(o.aA, a.d);
            JSONObject jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(context, jSONObject, g(context));
            if (jSONObjectBuildEnvelopeWithExtHeader == null || jSONObjectBuildEnvelopeWithExtHeader.has(com.umeng.commonsdk.framework.c.c)) {
                return;
            }
            ULog.i("walle", "[internal] workEvent send envelope back, result is ok");
            com.umeng.commonsdk.internal.utils.a.f(context);
            j.d(context);
            com.umeng.commonsdk.proguard.b.c(context);
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
        }
    }

    public static void b(Context context) throws Throwable {
        ULog.i("walle", "[internal] begin by stateful--->>>");
        if (context != null) {
            p(context);
        }
    }

    public static void c(Context context) throws Throwable {
        ULog.i("walle", "[internal] begin by stateful--->>>");
        if (context == null || !com.umeng.commonsdk.framework.a.b()) {
            return;
        }
        p(context);
    }

    public static void d(Context context) {
        if (context != null) {
            q(context);
        }
    }

    public static void e(Context context) {
        if (context == null || !com.umeng.commonsdk.framework.a.b()) {
            return;
        }
        q(context);
    }

    private static void p(Context context) {
        try {
            if (UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                UMWorkDispatch.sendEvent(context, a.e, b.a(context).a(), null);
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
    }

    private static void q(Context context) {
        Context applicationContext;
        Throwable th;
        try {
            ULog.i("walle", "[internal] begin by not stateful--->>>");
            applicationContext = context.getApplicationContext();
            try {
                f.a(applicationContext, applicationContext.getFilesDir() + "/" + com.umeng.commonsdk.stateless.a.e + "/" + Base64.encodeToString(a.a.getBytes(), 0), 10);
                UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                JSONObject jSONObjectBuildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(applicationContext);
                if (jSONObjectBuildSLBaseHeader != null && jSONObjectBuildSLBaseHeader.has("header")) {
                    try {
                        JSONObject jSONObject = (JSONObject) jSONObjectBuildSLBaseHeader.opt("header");
                        if (jSONObject != null) {
                            jSONObject.put(o.aA, a.d);
                        }
                    } catch (Exception unused) {
                    }
                }
                ULog.i("walle", "[internal] header is " + jSONObjectBuildSLBaseHeader.toString());
                JSONObject jSONObjectF = f(applicationContext);
                ULog.i("walle", "[internal] body is " + jSONObjectF.toString());
                ULog.i("walle", uMSLEnvelopeBuild.buildSLEnvelope(applicationContext, jSONObjectBuildSLBaseHeader, jSONObjectF, a.a).toString());
            } catch (Throwable th2) {
                th = th2;
                UMCrashManager.reportCrash(applicationContext, th);
            }
        } catch (Throwable th3) {
            applicationContext = context;
            th = th3;
        }
    }

    public static JSONObject f(Context context) throws JSONException {
        JSONArray jSONArrayS;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            try {
                try {
                    if (FieldManager.allow(com.umeng.commonsdk.utils.b.ad) && (jSONArrayS = s(applicationContext)) != null && jSONArrayS.length() > 0) {
                        jSONObject2.put("run_server", jSONArrayS);
                    }
                } catch (Exception e) {
                    UMCrashManager.reportCrash(applicationContext, e);
                }
                try {
                    if (FieldManager.allow(com.umeng.commonsdk.utils.b.ae)) {
                        String strK = com.umeng.commonsdk.internal.utils.a.k(applicationContext);
                        if (!TextUtils.isEmpty(strK)) {
                            jSONObject2.put(Constants.KEY_IMSI, strK);
                        }
                    }
                } catch (Exception e2) {
                    UMCrashManager.reportCrash(applicationContext, e2);
                }
                try {
                    if (FieldManager.allow(com.umeng.commonsdk.utils.b.af)) {
                        String strL = com.umeng.commonsdk.internal.utils.a.l(applicationContext);
                        if (!TextUtils.isEmpty(strL)) {
                            jSONObject2.put("meid", strL);
                        }
                    }
                } catch (Exception e3) {
                    UMCrashManager.reportCrash(applicationContext, e3);
                }
                try {
                    jSONObject.put(UMModuleRegister.INNER, jSONObject2);
                } catch (JSONException e4) {
                    UMCrashManager.reportCrash(applicationContext, e4);
                }
            } catch (Exception unused) {
            }
        }
        return jSONObject;
    }

    public static JSONObject g(Context context) throws JSONException {
        JSONObject jSONObjectC;
        JSONObject jSONObjectD;
        JSONArray jSONArrayR;
        JSONObject jSONObjectO;
        JSONArray jSONArrayN;
        JSONArray jSONArrayM;
        JSONObject jSONObjectK;
        JSONObject jSONObjectJ;
        JSONObject jSONObjectB;
        JSONObject jSONObjectA;
        JSONArray jSONArrayU;
        JSONArray jSONArrayT;
        JSONArray jSONArrayS;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.E) && (jSONArrayS = s(applicationContext)) != null && jSONArrayS.length() > 0) {
                    jSONObject2.put("rs", jSONArrayS);
                }
            } catch (Exception e) {
                UMCrashManager.reportCrash(applicationContext, e);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.Z) && (jSONArrayT = t(applicationContext)) != null && jSONArrayT.length() > 0) {
                    jSONObject2.put("bstn", jSONArrayT);
                }
            } catch (Exception e2) {
                UMCrashManager.reportCrash(applicationContext, e2);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.F) && (jSONArrayU = u(applicationContext)) != null && jSONArrayU.length() > 0) {
                    jSONObject2.put("by", jSONArrayU);
                }
            } catch (Exception e3) {
                UMCrashManager.reportCrash(applicationContext, e3);
            }
            try {
                a(applicationContext, jSONObject2);
            } catch (Exception e4) {
                UMCrashManager.reportCrash(applicationContext, e4);
            }
            try {
                b(applicationContext, jSONObject2);
            } catch (Exception e5) {
                UMCrashManager.reportCrash(applicationContext, e5);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.O) && (jSONObjectA = a()) != null && jSONObjectA.length() > 0) {
                    jSONObject2.put("sd", jSONObjectA);
                }
            } catch (Exception e6) {
                UMCrashManager.reportCrash(applicationContext, e6);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.P) && (jSONObjectB = b()) != null && jSONObjectB.length() > 0) {
                    jSONObject2.put("build", jSONObjectB);
                }
            } catch (Exception e7) {
                UMCrashManager.reportCrash(applicationContext, e7);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.Q)) {
                    JSONObject jSONObject3 = new JSONObject();
                    JSONArray jSONArrayI = i(applicationContext);
                    if (jSONArrayI != null && jSONArrayI.length() > 0) {
                        try {
                            jSONObject3.put("a_sr", jSONArrayI);
                        } catch (JSONException unused) {
                        }
                    }
                    JSONArray jSONArrayC = j.c(applicationContext);
                    if (jSONArrayC != null && jSONArrayC.length() > 0) {
                        try {
                            jSONObject3.put("stat", jSONArrayC);
                        } catch (JSONException unused2) {
                        }
                    }
                    jSONObject2.put("sr", jSONObject3);
                }
            } catch (Exception e8) {
                UMCrashManager.reportCrash(applicationContext, e8);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.R) && (jSONObjectJ = j(applicationContext)) != null && jSONObjectJ.length() > 0) {
                    jSONObject2.put("scr", jSONObjectJ);
                }
            } catch (Exception e9) {
                UMCrashManager.reportCrash(applicationContext, e9);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.S) && (jSONObjectK = k(applicationContext)) != null && jSONObjectK.length() > 0) {
                    jSONObject2.put("sinfo", jSONObjectK);
                }
            } catch (Exception e10) {
                UMCrashManager.reportCrash(applicationContext, e10);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.T)) {
                    JSONObject jSONObject4 = new JSONObject();
                    JSONArray jSONArrayE = com.umeng.commonsdk.internal.utils.a.e(applicationContext);
                    if (jSONArrayE != null && jSONArrayE.length() > 0) {
                        try {
                            jSONObject4.put("wl", jSONArrayE);
                        } catch (JSONException unused3) {
                        }
                    }
                    JSONArray jSONArrayL = l(applicationContext);
                    if (jSONArrayL != null && jSONArrayL.length() > 0) {
                        try {
                            jSONObject4.put("a_wls", jSONArrayL);
                        } catch (JSONException unused4) {
                        }
                    }
                    jSONObject2.put("winfo", jSONObject4);
                }
            } catch (Exception e11) {
                UMCrashManager.reportCrash(applicationContext, e11);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.U) && (jSONArrayM = m(applicationContext)) != null && jSONArrayM.length() > 0) {
                    jSONObject2.put(TKBaseEvent.TK_INPUT_EVENT_NAME, jSONArrayM);
                }
            } catch (Exception e12) {
                UMCrashManager.reportCrash(applicationContext, e12);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.ab) && (jSONArrayN = n(applicationContext)) != null && jSONArrayN.length() > 0) {
                    jSONObject2.put("appls", jSONArrayN);
                }
            } catch (Exception e13) {
                UMCrashManager.reportCrash(applicationContext, e13);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.W) && (jSONObjectO = o(applicationContext)) != null && jSONObjectO.length() > 0) {
                    jSONObject2.put("mem", jSONObjectO);
                }
            } catch (Exception e14) {
                UMCrashManager.reportCrash(applicationContext, e14);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.ac) && (jSONArrayR = r(applicationContext)) != null && jSONArrayR.length() > 0) {
                    jSONObject2.put("lbs", jSONArrayR);
                }
            } catch (Exception e15) {
                UMCrashManager.reportCrash(applicationContext, e15);
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.X) && (jSONObjectD = d()) != null && jSONObjectD.length() > 0) {
                    jSONObject2.put(o.v, jSONObjectD);
                }
            } catch (Exception unused5) {
            }
            try {
                if (FieldManager.allow(com.umeng.commonsdk.utils.b.Y) && (jSONObjectC = c()) != null && jSONObjectC.length() > 0) {
                    jSONObject2.put("rom", jSONObjectC);
                }
            } catch (Exception unused6) {
            }
            try {
                jSONObject.put(o.ao, jSONObject2);
            } catch (JSONException e16) {
                UMCrashManager.reportCrash(applicationContext, e16);
            }
        }
        return jSONObject;
    }

    private static JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tot_s", com.umeng.commonsdk.internal.utils.a.h());
            jSONObject.put("ava_s", com.umeng.commonsdk.internal.utils.a.i());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private static JSONObject d() throws Throwable {
        try {
            d.a aVarA = com.umeng.commonsdk.internal.utils.d.a();
            if (aVarA == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("pro", aVarA.a);
                jSONObject.put("pla", aVarA.b);
                jSONObject.put("cpus", aVarA.c);
                jSONObject.put("fea", aVarA.d);
                jSONObject.put("imp", aVarA.e);
                jSONObject.put("arc", aVarA.f);
                jSONObject.put("var", aVarA.g);
                jSONObject.put("par", aVarA.h);
                jSONObject.put("rev", aVarA.i);
                jSONObject.put("har", aVarA.j);
                jSONObject.put("rev", aVarA.k);
                jSONObject.put("ser", aVarA.l);
                jSONObject.put("cur_cpu", com.umeng.commonsdk.internal.utils.d.d());
                jSONObject.put("max_cpu", com.umeng.commonsdk.internal.utils.d.b());
                jSONObject.put("min_cpu", com.umeng.commonsdk.internal.utils.d.c());
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception unused) {
            }
            return jSONObject;
        } catch (Exception unused2) {
            return null;
        }
    }

    private static JSONArray r(Context context) {
        if (context != null) {
            return com.umeng.commonsdk.proguard.b.b(context.getApplicationContext());
        }
        return null;
    }

    private static JSONArray s(Context context) {
        List<ActivityManager.RunningServiceInfo> runningServices;
        JSONArray jSONArray = null;
        if (context == null) {
            return null;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService("activity");
            if (activityManager == null || (runningServices = activityManager.getRunningServices(Integer.MAX_VALUE)) == null || runningServices.isEmpty()) {
                return null;
            }
            for (int i = 0; i < runningServices.size(); i++) {
                if (runningServices.get(i) != null && runningServices.get(i).service != null && runningServices.get(i).service.getClassName() != null && runningServices.get(i).service.getPackageName() != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("sn", runningServices.get(i).service.getClassName().toString());
                        jSONObject.put("pn", runningServices.get(i).service.getPackageName().toString());
                        if (jSONArray == null) {
                            jSONArray = new JSONArray();
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException unused) {
                    }
                }
            }
            if (jSONArray == null) {
                return jSONArray;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("ts", System.currentTimeMillis());
                jSONObject2.put("ls", jSONArray);
            } catch (JSONException unused2) {
            }
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("sers", jSONObject2);
            } catch (JSONException unused3) {
            }
            JSONArray jSONArray2 = new JSONArray();
            try {
                jSONArray2.put(jSONObject3);
                return jSONArray2;
            } catch (Throwable th) {
                th = th;
                jSONArray = jSONArray2;
                UMCrashManager.reportCrash(context, th);
                return jSONArray;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static JSONArray t(Context context) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObjectC = k.c(context);
        if (jSONObjectC != null) {
            try {
                String strD = k.d(context);
                if (!TextUtils.isEmpty(strD)) {
                    jSONObjectC.put("sig", strD);
                }
                jSONArray.put(jSONObjectC);
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }

    private static JSONArray u(Context context) {
        JSONArray jSONArray = new JSONArray();
        String strE = k.e(context);
        if (!TextUtils.isEmpty(strE)) {
            try {
                jSONArray.put(new JSONObject(strE));
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }

    private static void a(Context context, JSONObject jSONObject) throws JSONException {
        PackageManager packageManager;
        if (context == null || (packageManager = context.getApplicationContext().getPackageManager()) == null) {
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.G)) {
            a(jSONObject, "gp", packageManager.hasSystemFeature("android.hardware.location.gps"));
        }
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.H)) {
            a(jSONObject, "to", packageManager.hasSystemFeature("android.hardware.touchscreen"));
        }
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.I)) {
            a(jSONObject, "mo", packageManager.hasSystemFeature("android.hardware.telephony"));
        }
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.J)) {
            a(jSONObject, "ca", packageManager.hasSystemFeature("android.hardware.camera"));
        }
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.K)) {
            a(jSONObject, "fl", packageManager.hasSystemFeature("android.hardware.camera.flash"));
        }
    }

    private static void a(JSONObject jSONObject, String str, boolean z) throws JSONException {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (z) {
                jSONObject.put(str, 1);
            } else {
                jSONObject.put(str, 0);
            }
        } catch (Exception unused) {
        }
    }

    private static void b(Context context, JSONObject jSONObject) throws JSONException {
        if (context != null) {
            String strA = l.a(context);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            try {
                JSONObject jSONObject2 = new JSONObject(strA);
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                if (jSONObject2.has(l.d) && FieldManager.allow(com.umeng.commonsdk.utils.b.L)) {
                    jSONObject.put(l.d, jSONObject2.opt(l.d));
                }
                if (jSONObject2.has(l.c) && FieldManager.allow(com.umeng.commonsdk.utils.b.M)) {
                    jSONObject.put(l.c, jSONObject2.opt(l.c));
                }
                if (jSONObject2.has(l.b) && FieldManager.allow(com.umeng.commonsdk.utils.b.N)) {
                    jSONObject.put(l.b, jSONObject2.opt(l.b));
                }
            } catch (Exception unused) {
            }
        }
    }

    public static String h(Context context) {
        try {
            e eVarA = e.a(context);
            if (eVarA == null) {
                return null;
            }
            eVarA.a();
            String strEncodeToString = Base64.encodeToString(new ac().a(eVarA.b()), 0);
            if (TextUtils.isEmpty(strEncodeToString)) {
                return null;
            }
            return strEncodeToString;
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return null;
        }
    }

    public static JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("f", com.umeng.commonsdk.internal.utils.a.c());
            jSONObject.put(o.av, com.umeng.commonsdk.internal.utils.a.d());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public static JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a_pr", Build.PRODUCT);
            jSONObject.put("a_bl", Build.BOOTLOADER);
            if (Build.VERSION.SDK_INT >= 14) {
                jSONObject.put("a_rv", Build.getRadioVersion());
            }
            jSONObject.put("a_fp", Build.FINGERPRINT);
            jSONObject.put("a_hw", Build.HARDWARE);
            jSONObject.put("a_host", Build.HOST);
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < Build.SUPPORTED_32_BIT_ABIS.length; i++) {
                    jSONArray.put(Build.SUPPORTED_32_BIT_ABIS[i]);
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("a_s32", jSONArray);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < Build.SUPPORTED_64_BIT_ABIS.length; i2++) {
                    jSONArray2.put(Build.SUPPORTED_64_BIT_ABIS[i2]);
                }
                if (jSONArray2.length() > 0) {
                    jSONObject.put("a_s64", jSONArray2);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray3 = new JSONArray();
                for (int i3 = 0; i3 < Build.SUPPORTED_ABIS.length; i3++) {
                    jSONArray3.put(Build.SUPPORTED_ABIS[i3]);
                }
                if (jSONArray3.length() > 0) {
                    jSONObject.put("a_sa", jSONArray3);
                }
            }
            jSONObject.put("a_ta", Build.TAGS);
            jSONObject.put("a_uk", UtilityImpl.NET_TYPE_UNKNOWN);
            jSONObject.put("a_user", Build.USER);
            jSONObject.put("a_cpu1", Build.CPU_ABI);
            jSONObject.put("a_cpu2", Build.CPU_ABI2);
            jSONObject.put("a_ra", Build.RADIO);
            if (Build.VERSION.SDK_INT >= 23) {
                jSONObject.put("a_bos", Build.VERSION.BASE_OS);
                jSONObject.put("a_pre", Build.VERSION.PREVIEW_SDK_INT);
                jSONObject.put("a_sp", Build.VERSION.SECURITY_PATCH);
            }
            jSONObject.put("a_cn", Build.VERSION.CODENAME);
            jSONObject.put("a_intl", Build.VERSION.INCREMENTAL);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public static JSONArray i(Context context) {
        if (context != null) {
            return k.f(context.getApplicationContext());
        }
        return null;
    }

    public static JSONObject j(Context context) throws JSONException {
        DisplayMetrics displayMetrics;
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            try {
                jSONObject.put("a_st_h", com.umeng.commonsdk.internal.utils.a.h(context));
                jSONObject.put("a_nav_h", com.umeng.commonsdk.internal.utils.a.i(context));
                if (context.getResources() != null && (displayMetrics = context.getResources().getDisplayMetrics()) != null) {
                    jSONObject.put("a_den", displayMetrics.density);
                    jSONObject.put("a_dpi", displayMetrics.densityDpi);
                }
            } catch (Exception e) {
                UMCrashManager.reportCrash(context, e);
            }
        }
        return jSONObject;
    }

    public static JSONObject k(Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            String packageName = applicationContext.getPackageName();
            try {
                jSONObject.put("a_fit", com.umeng.commonsdk.internal.utils.a.a(applicationContext, packageName));
                jSONObject.put("a_alut", com.umeng.commonsdk.internal.utils.a.b(applicationContext, packageName));
                jSONObject.put("a_c", com.umeng.commonsdk.internal.utils.a.c(applicationContext, packageName));
                jSONObject.put("a_uid", com.umeng.commonsdk.internal.utils.a.d(applicationContext, packageName));
                if (com.umeng.commonsdk.internal.utils.a.a()) {
                    jSONObject.put("a_root", 1);
                } else {
                    jSONObject.put("a_root", 0);
                }
                jSONObject.put("tf", com.umeng.commonsdk.internal.utils.a.b());
                jSONObject.put("s_fs", com.umeng.commonsdk.internal.utils.a.a(applicationContext));
                jSONObject.put("a_meid", com.umeng.commonsdk.internal.utils.a.l(applicationContext));
                jSONObject.put("a_imsi", com.umeng.commonsdk.internal.utils.a.k(applicationContext));
                jSONObject.put("st", com.umeng.commonsdk.internal.utils.a.f());
                String strA = k.a(applicationContext);
                if (!TextUtils.isEmpty(strA)) {
                    try {
                        jSONObject.put("a_iccid", strA);
                    } catch (Exception unused) {
                    }
                }
                String strB = k.b(applicationContext);
                if (!TextUtils.isEmpty(strB)) {
                    try {
                        jSONObject.put("a_simei", strB);
                    } catch (Exception unused2) {
                    }
                }
                jSONObject.put("hn", com.umeng.commonsdk.internal.utils.a.g());
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception e) {
                UMCrashManager.reportCrash(applicationContext, e);
            }
        }
        return jSONObject;
    }

    public static JSONArray l(Context context) throws JSONException {
        Context applicationContext;
        List<ScanResult> listB;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listB = com.umeng.commonsdk.internal.utils.a.b((applicationContext = context.getApplicationContext()))) != null && listB.size() > 0) {
            for (ScanResult scanResult : listB) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("a_bssid", scanResult.BSSID);
                    jSONObject.put("a_ssid", scanResult.SSID);
                    jSONObject.put("a_cap", scanResult.capabilities);
                    jSONObject.put("a_fcy", scanResult.frequency);
                    jSONObject.put("ts", System.currentTimeMillis());
                    if (Build.VERSION.SDK_INT >= 23) {
                        jSONObject.put("a_c0", scanResult.centerFreq0);
                        jSONObject.put("a_c1", scanResult.centerFreq1);
                        jSONObject.put("a_cw", scanResult.channelWidth);
                        if (scanResult.is80211mcResponder()) {
                            jSONObject.put("a_is80211", 1);
                        } else {
                            jSONObject.put("a_is80211", 0);
                        }
                        if (scanResult.isPasspointNetwork()) {
                            jSONObject.put("a_isppn", 1);
                        } else {
                            jSONObject.put("a_isppn", 0);
                        }
                        jSONObject.put("a_ofn", scanResult.operatorFriendlyName);
                        jSONObject.put("a_vn", scanResult.venueName);
                    }
                    jSONObject.put("a_dc", scanResult.describeContents());
                    jSONArray.put(jSONObject);
                } catch (Exception e) {
                    UMCrashManager.reportCrash(applicationContext, e);
                }
            }
        }
        return jSONArray;
    }

    public static JSONArray m(Context context) {
        Context applicationContext;
        List<InputMethodInfo> listM;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listM = com.umeng.commonsdk.internal.utils.a.m((applicationContext = context.getApplicationContext()))) != null) {
            for (InputMethodInfo inputMethodInfo : listM) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("a_id", inputMethodInfo.getId());
                    jSONObject.put("a_pn", inputMethodInfo.getPackageName());
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONArray.put(jSONObject);
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(applicationContext, th);
                }
            }
        }
        return jSONArray;
    }

    public static JSONArray n(Context context) throws JSONException {
        Context applicationContext;
        List<a.C0140a> listN;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listN = com.umeng.commonsdk.internal.utils.a.n((applicationContext = context.getApplicationContext()))) != null && !listN.isEmpty()) {
            for (a.C0140a c0140a : listN) {
                if (c0140a != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("a_pn", c0140a.a);
                        jSONObject.put("a_la", c0140a.b);
                        jSONObject.put("ts", System.currentTimeMillis());
                        jSONArray.put(jSONObject);
                    } catch (Exception e) {
                        UMCrashManager.reportCrash(applicationContext, e);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static JSONObject o(Context context) throws JSONException {
        Context applicationContext;
        ActivityManager.MemoryInfo memoryInfoO;
        JSONObject jSONObject = new JSONObject();
        if (context != null && (memoryInfoO = com.umeng.commonsdk.internal.utils.a.o((applicationContext = context.getApplicationContext()))) != null) {
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    jSONObject.put(o.av, memoryInfoO.totalMem);
                }
                jSONObject.put("f", memoryInfoO.availMem);
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception e) {
                UMCrashManager.reportCrash(applicationContext, e);
            }
        }
        return jSONObject;
    }
}
