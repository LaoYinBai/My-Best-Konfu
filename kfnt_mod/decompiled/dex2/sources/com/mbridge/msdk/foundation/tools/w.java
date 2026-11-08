package com.mbridge.msdk.foundation.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import com.heytap.mcssdk.mode.Message;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.kwad.v8.Platform;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.j;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SameTool.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class w extends g {
    private static Map<String, String> q;
    private static Map<String, String> r;
    private static char[] a = {'A', 'p', 'p', 'l', 'i', 'c', 'a', 't', 'i', 'o', 'n', 'I', 'n', 'f', 'o'};
    private static char[] b = {'.', 'X'};
    private static int c = 0;
    private static int d = 1;
    private static int e = 2;
    private static int f = 3;
    private static int g = 0;
    private static int h = 7;
    private static int i = 14;
    private static int j = 19;
    private static int k = 16;
    private static int l = 26;
    private static String m = "[一-龥]";
    private static Pattern n = Pattern.compile(m);
    private static int o = 1;
    private static boolean p = true;
    private static volatile Boolean s = null;

    public static <T extends String> boolean a(T t) {
        return t == null || t.length() == 0;
    }

    public static boolean b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return "wifi".equals(activeNetworkInfo.getTypeName().toLowerCase(Locale.US));
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() != null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean a(CampaignEx campaignEx) {
        if (campaignEx != null) {
            return !TextUtils.isEmpty(campaignEx.getDeepLinkURL());
        }
        return false;
    }

    public static float d(Context context) {
        if (context == null) {
            return 2.5f;
        }
        try {
            float f2 = context.getResources().getDisplayMetrics().density;
            if (f2 == 0.0f) {
                return 2.5f;
            }
            return f2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 2.5f;
        }
    }

    public static int a(Context context, float f2) {
        float f3 = 2.5f;
        if (context != null) {
            try {
                float f4 = context.getResources().getDisplayMetrics().density;
                if (f4 != 0.0f) {
                    f3 = f4;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return (int) ((f2 / f3) + 0.5f);
    }

    public static int b(Context context, float f2) {
        Resources resources;
        if (context == null || (resources = context.getResources()) == null) {
            return 0;
        }
        return (int) ((f2 * resources.getDisplayMetrics().density) + 0.5f);
    }

    public static int c(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x001f A[EXC_TOP_SPLITTER, PHI: r0 r2
  0x001f: PHI (r0v1 long) = (r0v0 long), (r0v3 long) binds: [B:19:0x002a, B:12:0x001d] A[DONT_GENERATE, DONT_INLINE]
  0x001f: PHI (r2v3 java.io.FileInputStream) = (r2v2 java.io.FileInputStream), (r2v4 java.io.FileInputStream) binds: [B:19:0x002a, B:12:0x001d] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long a(java.io.File r4) throws java.lang.Exception {
        /*
            r0 = 0
            r2 = 0
            boolean r3 = r4.exists()     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L2a
            if (r3 == 0) goto L1a
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L2a
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L2a
            int r4 = r3.available()     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L18
            long r0 = (long) r4
            r2 = r3
            goto L1d
        L15:
            r4 = move-exception
            r2 = r3
            goto L24
        L18:
            r2 = r3
            goto L2a
        L1a:
            r4.createNewFile()     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L2a
        L1d:
            if (r2 == 0) goto L2d
        L1f:
            r2.close()     // Catch: java.lang.Exception -> L2d
            goto L2d
        L23:
            r4 = move-exception
        L24:
            if (r2 == 0) goto L29
            r2.close()     // Catch: java.lang.Exception -> L29
        L29:
            throw r4
        L2a:
            if (r2 == 0) goto L2d
            goto L1f
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.tools.w.a(java.io.File):long");
    }

    public static int e(Context context) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NumberFormatException {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static float f(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int a() {
        int i2 = o;
        o = i2 + 1;
        return i2;
    }

    public static String b(int i2) {
        long[] jArrA;
        try {
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            JSONArray jSONArray = new JSONArray();
            if (aVarB != null && aVarB.O() == 1 && (jArrA = com.mbridge.msdk.foundation.db.m.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a()) != null) {
                int length = jArrA.length;
                for (int i3 = (length <= i2 || i2 == 0) ? 0 : length - i2; i3 < length; i3++) {
                    jSONArray.put(jArrA[i3]);
                }
            }
            return jSONArray.length() > 0 ? a(jSONArray) : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return "";
        }
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        int iY = aVarB.y();
        if (jSONArray.length() > iY) {
            JSONArray jSONArray2 = new JSONArray();
            for (int i2 = 0; i2 < iY; i2++) {
                try {
                    jSONArray2.put(jSONArray.get(i2));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            return jSONArray2.toString();
        }
        return jSONArray.toString();
    }

    public static boolean a(String str, Context context) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(CampaignEx campaignEx) {
        if (campaignEx != null) {
            try {
                return campaignEx.getRetarget_offer() == 1;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static double b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0.0d;
            }
            return Double.parseDouble(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0d;
        }
    }

    public static int a(Object obj) {
        if (obj == null) {
            return 0;
        }
        try {
            if (obj instanceof String) {
                return Integer.parseInt((String) obj);
            }
            return 0;
        } catch (Throwable th) {
            s.b("SameTools", th.getMessage(), th);
            return 0;
        }
    }

    public static double a(Double d2) {
        try {
            String str = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US)).format(d2);
            if (z.b(str)) {
                return Double.parseDouble(str);
            }
            return 0.0d;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0d;
        }
    }

    private static DisplayMetrics n(Context context) {
        if (context == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealMetrics(displayMetrics);
            } else {
                defaultDisplay.getMetrics(displayMetrics);
            }
            return displayMetrics;
        } catch (Throwable th) {
            th.printStackTrace();
            return context.getResources().getDisplayMetrics();
        }
    }

    public static int g(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            return n(context).heightPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int h(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            return n(context).widthPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int i(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getResources().getDisplayMetrics().widthPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int j(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getResources().getDisplayMetrics().heightPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static void a(View view) {
        if (view == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                view.setSystemUiVisibility(Message.MESSAGE_LAUNCH_ALARM);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static int k(Context context) {
        if (context != null) {
            return 0;
        }
        try {
            if (context.getResources().getIdentifier("config_showNavigationBar", "bool", Platform.ANDROID) != 0) {
                return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("navigation_bar_height", "dimen", Platform.ANDROID));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return 0;
    }

    public static void a(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        try {
            imageView.setImageResource(0);
            imageView.setImageDrawable(null);
            imageView.setImageURI(null);
            imageView.setImageBitmap(null);
        } catch (Throwable th) {
            if (MBridgeConstans.DEBUG) {
                th.printStackTrace();
            }
        }
    }

    public static List<String> b(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        try {
            if (jSONArray.length() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                String strOptString = jSONArray.optString(i2);
                if (z.b(strOptString)) {
                    arrayList.add(strOptString);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            s.b("SameTools", th.getMessage(), th);
            return null;
        }
    }

    public static String c(String str) {
        try {
            return z.b(str) ? URLEncoder.encode(str, "utf-8") : "";
        } catch (Throwable th) {
            s.b("SameTools", th.getMessage(), th);
            return "";
        }
    }

    public static boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static boolean l(Context context) {
        try {
            return ((PowerManager) context.getSystemService("power")).isScreenOn();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static int m(Context context) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo;
        if (context == null) {
            return 0;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                packageInfo = WebView.getCurrentWebViewPackage();
            } else {
                packageInfo = context.getPackageManager().getPackageInfo("com.google.android.webview", 1);
            }
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            if (packageInfo == null || TextUtils.isEmpty(packageInfo.versionName) || !packageInfo.versionName.equals("77.0.3865.92")) {
                return aVarB.ay();
            }
            return 5;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static JSONArray a(Context context, String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            if (aVarB != null && aVarB.O() == 1) {
                s.b("SameTools", "fqci cfc:" + aVarB.O());
                long[] jArrA = com.mbridge.msdk.foundation.db.m.a(com.mbridge.msdk.foundation.db.i.a(context)).a();
                if (jArrA != null) {
                    for (long j2 : jArrA) {
                        s.b("SameTools", "cfc campaignIds:" + jArrA);
                        jSONArray.put(j2);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONArray;
    }

    public static String b(Context context, String str) {
        String strA;
        strA = "";
        try {
            JSONArray jSONArrayA = a(context, str);
            strA = jSONArrayA.length() > 0 ? a(jSONArrayA) : "";
            s.b("SameTools", "get excludes:" + strA);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return strA;
    }

    public static void a(String str, CampaignEx campaignEx, int i2) {
        if (TextUtils.isEmpty(str) || campaignEx == null || com.mbridge.msdk.foundation.controller.a.f().j() == null) {
            return;
        }
        com.mbridge.msdk.foundation.db.l lVarA = com.mbridge.msdk.foundation.db.l.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        com.mbridge.msdk.foundation.entity.h hVar = new com.mbridge.msdk.foundation.entity.h();
        hVar.a(System.currentTimeMillis());
        hVar.b(str);
        hVar.a(campaignEx.getId());
        hVar.a(i2);
        lVarA.a(hVar);
    }

    public static synchronized boolean c(Context context, String str) {
        boolean z;
        z = false;
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    if (b(str, context) != null) {
                        z = true;
                    }
                } catch (Exception unused) {
                }
            }
        }
        return z;
    }

    private static Object b(String str, Context context) {
        try {
            return Class.forName(String.valueOf(c(c))).getMethod(String.valueOf(c(e)), String.class, Integer.TYPE).invoke(Class.forName(String.valueOf(c(d))).getMethod(String.valueOf(c(f)), new Class[0]).invoke(context, new Object[0]), str, 8192);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static final char[] c(int i2) {
        StringBuilder sb;
        switch (i2) {
            case 0:
                sb = new StringBuilder();
                sb.append(String.valueOf(Arrays.copyOf(v.c, h)));
                sb.append(b[g]);
                sb.append(String.valueOf(Arrays.copyOfRange(v.c, h, i)));
                sb.append(b[g]);
                sb.append(String.valueOf(Arrays.copyOfRange(v.c, i, k)));
                sb.append(b[g]);
                sb.append(String.valueOf(u.c));
                break;
            case 1:
                sb = new StringBuilder();
                sb.append(String.valueOf(Arrays.copyOf(v.c, h)));
                sb.append(b[g]);
                sb.append(String.valueOf(Arrays.copyOfRange(v.c, h, i)));
                sb.append(b[g]);
                sb.append(String.valueOf(Arrays.copyOfRange(v.c, j, l)));
                break;
            case 2:
                sb = new StringBuilder();
                sb.append(String.valueOf(Arrays.copyOfRange(v.c, k, j)));
                sb.append(String.valueOf(a));
                break;
            case 3:
                sb = new StringBuilder();
                sb.append(String.valueOf(Arrays.copyOfRange(v.c, k, j)));
                sb.append(String.valueOf(u.c));
                break;
            default:
                sb = null;
                break;
        }
        return sb.toString().toCharArray();
    }

    private static synchronized String a(String str, Context context, String str2) {
        StringBuilder sb;
        try {
            Set<String> queryParameterNames = Uri.parse(str).getQueryParameterNames();
            if (queryParameterNames != null && queryParameterNames.size() > 0) {
                sb = new StringBuilder("&rtins_type=");
            } else {
                sb = new StringBuilder("?rtins_type=");
            }
        } catch (Exception unused) {
            sb = new StringBuilder("&rtins_type=");
        }
        try {
            if (b(str2, context) != null) {
                sb.append(1);
            } else {
                sb.append(2);
            }
        } catch (Exception unused2) {
            sb.append(0);
        }
        return sb.toString();
    }

    public static synchronized String a(Context context, String str, String str2) {
        StringBuilder sb;
        sb = new StringBuilder(str2);
        try {
            sb.append(a(str2, context, str));
        } catch (Exception unused) {
        }
        return sb.toString();
    }

    public static final synchronized String d(String str) {
        String str2;
        int i2;
        int iMax;
        boolean zAm;
        boolean zAk;
        try {
            str2 = "app_id_" + str;
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b("app_id");
            if (aVarB != null) {
                zAm = aVarB.am();
                zAk = aVarB.ak();
                iMax = Math.max(0, aVarB.al());
            } else {
                iMax = 3;
                zAm = true;
                zAk = false;
            }
        } catch (Exception e2) {
            s.d("SameTools", e2.getMessage());
        }
        if (zAk && iMax != 0) {
            if (zAm && q != null && q.containsKey(str2)) {
                return q.get(str2);
            }
            StringBuilder sb = new StringBuilder("");
            StackTraceElement[] stackTrace = new Exception().getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                List<String> listA = a(stackTrace);
                Collections.reverse(listA);
                ArrayList arrayList = new ArrayList();
                for (String str3 : listA) {
                    if (!str3.startsWith(MBridgeConstans.APPLICATION_STACK_COM_ANDROID) && !str3.startsWith(MBridgeConstans.APPLICATION_STACK_ANDROID_OS) && !str3.startsWith(MBridgeConstans.APPLICATION_STACK_ANDROID_APP) && !str3.startsWith(MBridgeConstans.APPLICATION_STACK_REFLECT_METHOD) && !str3.startsWith(MBridgeConstans.APPLICATION_STACK_ANDROID_VIEW) && !arrayList.contains(str3)) {
                        arrayList.add(str3);
                    }
                }
                int iMin = Math.min(arrayList.size(), iMax);
                if (iMin > 0) {
                    for (i2 = 0; i2 < iMin; i2++) {
                        sb.append((String) arrayList.get(i2));
                        if (i2 < iMin - 1) {
                            sb.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
                        }
                    }
                }
                JSONObject jSONObject = null;
                if (!TextUtils.isEmpty(sb.toString())) {
                    jSONObject = new JSONObject();
                    jSONObject.put("1", sb.toString());
                }
                if (jSONObject == null || jSONObject.length() <= 0) {
                    return "";
                }
                String strA = a.a(jSONObject.toString());
                if (zAm && !TextUtils.isEmpty(strA)) {
                    if (q == null) {
                        q = new HashMap();
                    }
                    q.put(str2, strA);
                }
                return strA;
            }
            return "";
        }
        return "";
    }

    public static synchronized void a(String str, String str2) {
        if (r == null) {
            r = new HashMap();
        }
        r.put("app_id_" + str, str2);
    }

    public static synchronized String e(String str) {
        String str2 = "app_id_" + str;
        if (r == null || !r.containsKey(str2)) {
            return null;
        }
        return r.get(str2);
    }

    private static List<String> a(StackTraceElement[] stackTraceElementArr) {
        ArrayList arrayList = new ArrayList();
        if (stackTraceElementArr != null && stackTraceElementArr.length > 0) {
            for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                arrayList.add(stackTraceElement.getClassName());
            }
        }
        return arrayList;
    }

    public static final int c() {
        if (s == null) {
            try {
                s = j.a.a.e();
            } catch (Exception e2) {
                s.d("SameTools", e2.getMessage());
            }
        }
        if (s != null) {
            return s.booleanValue() ? 1 : 0;
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String f(java.lang.String r14) {
        /*
            java.lang.String r0 = ""
            boolean r1 = android.text.TextUtils.isEmpty(r14)
            if (r1 == 0) goto L9
            return r0
        L9:
            java.util.concurrent.ConcurrentHashMap r14 = com.mbridge.msdk.foundation.same.a.d.c(r14)
            if (r14 == 0) goto L9b
            int r1 = r14.size()
            if (r1 <= 0) goto L9b
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r14 = r14.values()
            java.util.Iterator r14 = r14.iterator()
        L22:
            boolean r2 = r14.hasNext()
            if (r2 == 0) goto L86
            java.lang.Object r2 = r14.next()
            com.mbridge.msdk.foundation.entity.c r2 = (com.mbridge.msdk.foundation.entity.c) r2
            if (r2 == 0) goto L22
            long r3 = r2.d()
            long r5 = r2.e()
            long r7 = java.lang.System.currentTimeMillis()
            r9 = 0
            int r9 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            r10 = 0
            r11 = 1
            r12 = 1000(0x3e8, double:4.94E-321)
            if (r9 <= 0) goto L4e
            long r3 = r3 * r12
            long r5 = r5 + r3
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 < 0) goto L74
            goto L73
        L4e:
            com.mbridge.msdk.b.b r3 = com.mbridge.msdk.b.b.a()
            com.mbridge.msdk.foundation.controller.a r4 = com.mbridge.msdk.foundation.controller.a.f()
            java.lang.String r4 = r4.k()
            com.mbridge.msdk.b.a r3 = r3.b(r4)
            if (r3 != 0) goto L68
            com.mbridge.msdk.b.b r3 = com.mbridge.msdk.b.b.a()
            com.mbridge.msdk.b.a r3 = r3.b()
        L68:
            long r3 = r3.B()
            long r3 = r3 * r12
            long r5 = r5 + r3
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 < 0) goto L74
        L73:
            r10 = 1
        L74:
            if (r10 == 0) goto L22
            java.util.List r2 = r2.c()
            if (r2 == 0) goto L22
            int r3 = r2.size()
            if (r3 <= 0) goto L22
            r1.addAll(r2)
            goto L22
        L86:
            int r14 = r1.size()
            if (r14 <= 0) goto L9b
            java.util.HashSet r14 = new java.util.HashSet
            r14.<init>(r1)
            r1.clear()
            r1.addAll(r14)
            java.lang.String r0 = r1.toString()
        L9b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.tools.w.f(java.lang.String):java.lang.String");
    }

    public static final String d() {
        return j.a.a.f();
    }
}
