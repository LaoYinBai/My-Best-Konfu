package com.sigmob.sdk.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.sigmob.devicehelper.DeviceHelper;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.e.m;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntityGDPR;
import com.sigmob.sdk.common.mta.PointType;
import com.sigmob.sdk.common.utils.DeviceUtils;
import com.sigmob.sdk.common.utils.b;
import com.sigmob.sdk.common.utils.f;
import com.sigmob.sdk.common.utils.n;
import com.sigmob.sdk.common.utils.p;
import com.sigmob.windad.consent.WindAdConsentInformation;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.tan.mark.SigNativeId;
import com.umeng.message.MsgConstant;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements f.a {
    private static String i;
    private static volatile a j;
    private boolean A;
    private Location l;
    private f m;
    private String o;
    private String p;
    private int q;
    private String s;
    private String t;
    private String u;
    private String v;
    private Context w;
    private boolean x;
    private boolean y;
    private boolean z;
    private static final AtomicInteger b = new AtomicInteger(1);
    public static String a = Constants.SDK_VERSION;
    private static String c = "-1";
    private static String d = Constants.FAIL;
    private static int e = 0;
    private static int f = 0;
    private static boolean g = true;
    private static int h = 0;
    private final int k = 9;
    private String n = null;
    private boolean r = true;

    /* renamed from: com.sigmob.sdk.common.a$a, reason: collision with other inner class name */
    public enum EnumC0015a {
        FORCE_PORTRAIT("portrait"),
        FORCE_LANDSCAPE("landscape"),
        DEVICE_ORIENTATION("device"),
        UNDEFINED("");

        private final String e;

        EnumC0015a(String str) {
            this.e = str;
        }
    }

    public static PackageInfo a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(str, 0);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String a() {
        return TextUtils.isEmpty(c) ? "-1" : c;
    }

    public static Map<String, String> a(Uri uri) {
        HashMap map = new HashMap();
        for (String str : uri.getQueryParameterNames()) {
            map.put(str, TextUtils.join(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP, uri.getQueryParameters(str)));
        }
        return map;
    }

    public static void a(String str) {
        c = str;
    }

    public static boolean b() {
        return l().equalsIgnoreCase("1") || !q();
    }

    public static boolean b(Context context, String str) throws ClassNotFoundException {
        Class<?> cls = null;
        try {
            cls = Class.forName("androidx.core.content.ContextCompat");
        } catch (Throwable unused) {
        }
        try {
            cls = Class.forName("androidx.core.content.ContextCompat");
        } catch (Throwable unused2) {
        }
        if (cls == null) {
            return false;
        }
        try {
            n.a aVar = new n.a((Class) cls, "checkSelfPermission");
            aVar.a(Context.class, context);
            aVar.a(String.class, str);
            Integer num = (Integer) aVar.a();
            if (num != null) {
                return num.intValue() == 0;
            }
            return false;
        } catch (Throwable unused3) {
            return false;
        }
    }

    public static String c() {
        return DeviceUtils.j();
    }

    public static String d() {
        return DeviceUtils.h();
    }

    public static String e() {
        return DeviceUtils.i();
    }

    public static Long f() {
        return Long.valueOf(DeviceUtils.a());
    }

    public static Integer g() {
        return Integer.valueOf(DeviceUtils.g());
    }

    public static String h() {
        try {
            return DeviceHelper.getMacAddress();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String i() {
        try {
            return DeviceUtils.b();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String j() {
        return Build.BOARD;
    }

    public static String k() {
        try {
            return DeviceHelper.getVAID();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String l() {
        return d;
    }

    public static int m() {
        return f;
    }

    public static a n() {
        if (j == null) {
            synchronized (a.class) {
                if (j == null) {
                    j = new a();
                }
            }
        }
        return j;
    }

    public static boolean o() {
        try {
            return DeviceUtils.c();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean p() {
        try {
            return DeviceUtils.k();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean q() {
        return g;
    }

    public static int r() {
        int i2;
        int i3;
        do {
            i2 = b.get();
            i3 = i2 + 1;
            if (i3 > 16777215) {
                i3 = 1;
            }
        } while (!b.compareAndSet(i2, i3));
        return i2;
    }

    public String A() {
        try {
        } catch (Throwable th) {
            SigmobLog.i("getUpdateId:" + th.getMessage());
        }
        if (SDKConfig.sharedInstance().isDisableBootMark()) {
            return "";
        }
        if (TextUtils.isEmpty(this.v)) {
            this.v = SigNativeId.getUpdateFromJNI();
            SigmobLog.i("updateId:" + this.v);
        }
        return this.v;
    }

    public String B() {
        try {
            return DeviceUtils.f();
        } catch (Throwable unused) {
            return null;
        }
    }

    public String C() {
        try {
            return DeviceHelper.getIMSI(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String D() {
        return DeviceUtils.e();
    }

    public boolean E() {
        try {
            return DeviceUtils.e(this.w);
        } catch (Throwable unused) {
            return false;
        }
    }

    public Float F() {
        try {
            return Float.valueOf(DeviceUtils.h(this.w));
        } catch (Throwable unused) {
            return Float.valueOf(0.0f);
        }
    }

    public Integer G() {
        try {
            return Integer.valueOf(DeviceUtils.j(this.w));
        } catch (Throwable unused) {
            return 0;
        }
    }

    public Boolean H() {
        try {
            return Boolean.valueOf(DeviceUtils.i(this.w));
        } catch (Throwable unused) {
            return false;
        }
    }

    public int I() {
        try {
            return (int) DeviceUtils.l(this.w);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public Integer J() {
        try {
            return Integer.valueOf(DeviceUtils.A(this.w));
        } catch (Throwable unused) {
            return 0;
        }
    }

    public Integer K() {
        try {
            return Integer.valueOf(DeviceUtils.y(this.w));
        } catch (Throwable unused) {
            return 0;
        }
    }

    public String L() {
        try {
            return DeviceUtils.b(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public Integer M() {
        try {
            return Integer.valueOf(DeviceUtils.B(this.w));
        } catch (Throwable unused) {
            return null;
        }
    }

    public Integer N() {
        try {
            return Integer.valueOf(DeviceUtils.z(this.w));
        } catch (Throwable unused) {
            return null;
        }
    }

    public String O() {
        try {
            return String.valueOf(this.w.getApplicationInfo().targetSdkVersion);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String P() {
        try {
            return b.c(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public Integer Q() {
        try {
            return Integer.valueOf(DeviceUtils.g(this.w));
        } catch (Throwable unused) {
            return 0;
        }
    }

    public String R() {
        try {
            return b.d(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public int S() {
        try {
            return DeviceUtils.d().a();
        } catch (Throwable unused) {
            return DeviceUtils.a.UNKNOWN.a();
        }
    }

    public String T() {
        try {
            return DeviceUtils.u(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String U() {
        try {
            return DeviceHelper.getWifimac(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String V() {
        try {
            return DeviceHelper.getWifiName(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String W() {
        try {
            return DeviceUtils.n(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public Locale X() {
        try {
            return DeviceUtils.m(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String Y() {
        String strZ = n().Z();
        return !TextUtils.isEmpty(strZ) ? strZ : n().s();
    }

    public String Z() {
        try {
            switch (SDKConfig.sharedInstance().getDisable_up_OAid()) {
                case 0:
                    if (Build.VERSION.SDK_INT > 28) {
                        return DeviceHelper.getOAID(this.w);
                    }
                    return null;
                case 1:
                    if (Build.VERSION.SDK_INT >= 23) {
                        return DeviceHelper.getOAID(this.w);
                    }
                    return null;
                default:
                    return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    public int a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return 0;
        }
        return windowManager.getDefaultDisplay().getRotation();
    }

    public String a(String str, String str2) {
        Resources resources;
        int identifier;
        return (this.w == null || (resources = this.w.getResources()) == null || (identifier = resources.getIdentifier(str, PushMultiProcessSharedProvider.STRING_TYPE, this.w.getPackageName())) == 0) ? str2 : resources.getString(identifier);
    }

    public String a(String str, String str2, Object... objArr) {
        Resources resources;
        if (this.w == null || (resources = this.w.getResources()) == null) {
            return str2;
        }
        int identifier = resources.getIdentifier(str, PushMultiProcessSharedProvider.STRING_TYPE, this.w.getPackageName());
        if (identifier == 0) {
            return String.format(str2, objArr);
        }
        SigmobLog.d("getStringResources resid" + identifier);
        return resources.getString(identifier, objArr);
    }

    public void a(int i2) {
        f = i2;
        SharedPreferences.Editor editorEdit = p.a(this.w).edit();
        editorEdit.putInt(Constants.WIND_AGERESTRICTED_STATUS, f);
        editorEdit.commit();
        PointEntityGDPR pointEntityGDPR = new PointEntityGDPR();
        pointEntityGDPR.setAge_restricted(String.valueOf(i2));
        pointEntityGDPR.setAc_type(PointType.GDPR_CONSENT);
        pointEntityGDPR.setSub_category(PointCategory.COPPA);
        pointEntityGDPR.setCategory(PointCategory.PRIVACY);
        pointEntityGDPR.commit();
    }

    public void a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(this.o)) {
            this.w = context.getApplicationContext();
            this.o = str;
            this.p = p.a(this.w).getString("uid", null);
            this.m = new f(this.w, this);
            DeviceUtils.w(this.w);
        }
    }

    public void a(Location location) {
        this.l = location;
    }

    public void a(WindowInsets windowInsets) {
        if (Build.VERSION.SDK_INT < 20 || windowInsets == null || !windowInsets.isRound()) {
            return;
        }
        this.q = windowInsets.getSystemWindowInsetBottom();
    }

    @Override // com.sigmob.sdk.common.utils.f.a
    public void a(com.sigmob.sdk.common.utils.a aVar, com.sigmob.sdk.common.utils.a aVar2) {
    }

    public void a(boolean z) {
        SharedPreferences.Editor editorEdit = p.a(this.w).edit();
        editorEdit.putBoolean(Constants.USER_GDPR_REGION, z);
        editorEdit.commit();
        h = z ? 1 : 2;
    }

    public DisplayMetrics aa() {
        try {
            return DeviceUtils.p(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String ab() {
        return this.o;
    }

    public String ac() {
        return this.p;
    }

    public int ad() {
        return this.q;
    }

    public int ae() {
        return e;
    }

    public Context af() {
        return this.w;
    }

    public String ag() {
        try {
            return DeviceUtils.o(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public Long ah() {
        try {
            return Long.valueOf(DeviceUtils.k(this.w));
        } catch (Throwable unused) {
            return null;
        }
    }

    public DisplayMetrics ai() {
        try {
            return DeviceUtils.C(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean aj() {
        try {
            return m.b(new URL(SDKConfig.getConfigUrl()).getHost());
        } catch (Throwable unused) {
            return false;
        }
    }

    public String ak() {
        try {
            return DeviceUtils.d(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public List<PackageInfo> al() {
        try {
            return b.b(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public LocationManager am() {
        try {
            if (DeviceHelper.isCanUseLocation(this.w)) {
                return (LocationManager) this.w.getSystemService(MsgConstant.KEY_LOCATION_PARAMS);
            }
            return null;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return null;
        }
    }

    @SuppressLint({"MissingPermission"})
    public Location an() {
        if (this.l != null) {
            return this.l;
        }
        LocationManager locationManagerAm = am();
        if (this.x && locationManagerAm != null && DeviceHelper.isCanRetryLocation()) {
            SigmobLog.d("private :use_location ");
            Location lastKnownLocation = am().getLastKnownLocation("passive");
            if (lastKnownLocation != null) {
                this.l = lastKnownLocation;
            }
        }
        return this.l;
    }

    public String ao() {
        try {
            return (String) this.w.getPackageManager().getApplicationLabel(this.w.getApplicationInfo());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean ap() {
        return this.z;
    }

    public boolean aq() {
        return this.y;
    }

    public boolean ar() {
        return this.A;
    }

    public String b(int i2) {
        try {
            if (TextUtils.isEmpty(this.s)) {
                if (DeviceHelper.isCanUsePhoneState(this.w) && DeviceHelper.isCanRetryIMEI()) {
                    this.s = DeviceHelper.getIMEI(this.w);
                    if (i2 != -1) {
                        this.t = DeviceHelper.getIMEI(this.w, i2);
                        this.u = DeviceHelper.getIMEI(this.w, i2);
                    }
                }
                return null;
            }
            return i2 == -1 ? this.s : i2 == 0 ? this.t : this.u;
        } catch (Throwable unused) {
            return null;
        }
    }

    public String b(Context context) {
        String string;
        StringBuilder sb;
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            String[] strArr = packageManager.getPackageInfo(packageName, 4096).requestedPermissions;
            string = "";
            for (int i2 = 0; i2 < strArr.length; i2++) {
                try {
                    if (packageManager.checkPermission(strArr[i2], packageName) == 0) {
                        if (i2 == strArr.length - 1) {
                            sb = new StringBuilder();
                            sb.append(string);
                            sb.append(strArr[i2]);
                        } else {
                            sb = new StringBuilder();
                            sb.append(string);
                            sb.append(strArr[i2]);
                            sb.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                        }
                        string = sb.toString();
                    }
                } catch (Throwable th) {
                    th = th;
                    th.printStackTrace();
                    return string;
                }
            }
            SigmobLog.d("permissionReq:" + string);
            if (!TextUtils.isEmpty(string)) {
                return Base64.encodeToString(string.getBytes(), 2);
            }
        } catch (Throwable th2) {
            th = th2;
            string = "";
        }
        return string;
    }

    public void b(String str) {
        d = str;
        SharedPreferences.Editor editorEdit = p.a(this.w).edit();
        editorEdit.putString(Constants.WIND_CONSENT_STATUS, str);
        editorEdit.commit();
    }

    public void b(boolean z) {
        this.x = z;
    }

    public void c(int i2) {
        e = i2;
        SharedPreferences.Editor editorEdit = p.a(this.w).edit();
        editorEdit.putInt(Constants.WIND_USER_AGE, e);
        editorEdit.commit();
        PointEntityGDPR pointEntityGDPR = new PointEntityGDPR();
        pointEntityGDPR.setAge(String.valueOf(i2));
        pointEntityGDPR.setAc_type(PointType.GDPR_CONSENT);
        pointEntityGDPR.setSub_category(PointCategory.COPPA);
        pointEntityGDPR.setCategory(PointCategory.PRIVACY);
        pointEntityGDPR.commit();
    }

    public void c(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (TextUtils.isEmpty(this.p) || !str.equalsIgnoreCase(this.p)) {
                this.p = str;
                SharedPreferences.Editor editorEdit = p.a(this.w).edit();
                editorEdit.putString("uid", str);
                editorEdit.apply();
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    public void c(boolean z) {
        SharedPreferences.Editor editorEdit = p.a(this.w).edit();
        editorEdit.putBoolean(Constants.EXT_GDPR_REGION, z);
        editorEdit.commit();
        g = z && SDKConfig.isGDPRRegion();
    }

    public void d(String str) {
        PointEntityGDPR pointEntityGDPR = new PointEntityGDPR();
        pointEntityGDPR.setUser_consent(str);
        pointEntityGDPR.setGdpr_region(q() ? "1" : Constants.FAIL);
        try {
            pointEntityGDPR.setGdpr_dialog_region(WindAdConsentInformation.getInstance(this.w).isRequestLocationInEeaOrUnknown() ? "1" : Constants.FAIL);
        } catch (Throwable unused) {
        }
        pointEntityGDPR.setSub_category(PointCategory.CONSENT);
        pointEntityGDPR.setCategory(PointCategory.GDPR);
        pointEntityGDPR.setAc_type(PointType.GDPR_CONSENT);
        pointEntityGDPR.commit();
    }

    public void d(boolean z) {
        this.z = z;
    }

    public int e(String str) {
        Resources resources;
        if (this.w == null || (resources = this.w.getResources()) == null) {
            return 0;
        }
        return resources.getIdentifier(str, "style", this.w.getPackageName());
    }

    public void e(boolean z) {
        this.y = z;
    }

    public void f(boolean z) {
        this.A = z;
    }

    public String s() {
        try {
            return (Build.VERSION.SDK_INT <= 28 || SDKConfig.sharedInstance().getOaidApiDisable()) ? "" : DeviceHelper.getOAID_API(this.w);
        } catch (Throwable unused) {
            return "";
        }
    }

    public String t() {
        try {
            return DeviceUtils.x(this.w);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String u() {
        try {
            return DeviceUtils.a(this.w, "SHA1");
        } catch (Throwable unused) {
            return null;
        }
    }

    public String v() {
        try {
            return DeviceUtils.a(this.w, "MD5");
        } catch (Throwable unused) {
            return null;
        }
    }

    public String w() {
        try {
            return this.m.a().b;
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean x() {
        try {
            return this.m.a().d;
        } catch (Throwable unused) {
            return false;
        }
    }

    public String y() {
        try {
            return b(-1);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String z() {
        try {
        } catch (Throwable th) {
            SigmobLog.i("getBootId:" + th.getMessage());
        }
        if (SDKConfig.sharedInstance().isDisableBootMark()) {
            return "";
        }
        if (TextUtils.isEmpty(i)) {
            byte[] bootFromJNI = SigNativeId.getBootFromJNI();
            if (bootFromJNI != null && bootFromJNI.length > 0) {
                i = new String(bootFromJNI);
            }
            SigmobLog.i("origin bootId:" + i);
            if (!TextUtils.isEmpty(i)) {
                i = i.replaceAll("\\s*|\t|\r|\n", "");
                if (i.length() > 36) {
                    i = i.substring(0, 36);
                }
            }
            SigmobLog.i("bootId:" + i);
        }
        return i;
    }
}
