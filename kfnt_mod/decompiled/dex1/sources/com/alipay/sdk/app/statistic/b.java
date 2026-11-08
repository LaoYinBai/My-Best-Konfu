package com.alipay.sdk.app.statistic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.alipay.sdk.app.statistic.a;
import com.alipay.sdk.util.c;
import com.alipay.sdk.util.k;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {
    public static final String A = "TriDesDecryptError";
    public static final String B = "ClientBindException";
    public static final String C = "SaveTradeTokenError";
    public static final String D = "ClientBindServiceFailed";
    public static final String E = "TryStartServiceEx";
    public static final String F = "BindWaitTimeoutEx";
    public static final String G = "CheckClientExistEx";
    public static final String H = "CheckClientSignEx";
    public static final String I = "GetInstalledAppEx";
    public static final String J = "ParserTidClientKeyEx";
    public static final String K = "PgApiInvoke";
    public static final String L = "PgBindStarting";
    public static final String M = "PgBinded";
    public static final String N = "PgBindEnd";
    public static final String O = "PgBindPay";
    public static final String P = "PgReturn";
    public static final String Q = "PgReturnV";
    public static final String R = "PgWltVer";
    public static final String S = "PgOpenStarting";
    public static final String T = "ErrIntentEx";
    public static final String U = "ErrActNull";
    public static final String V = "ErrActNull";
    public static final String W = "GetInstalledAppEx";
    public static final String X = "StartLaunchAppTransEx";
    public static final String Y = "CheckLaunchAppExistEx";
    public static final String Z = "LogCurrentAppLaunchSwitch";

    /* renamed from: a, reason: collision with root package name */
    public static final String f2571a = "net";
    public static final String aa = "LogCurrentQueryTime";
    public static final String ab = "LogCalledPackage";
    public static final String ac = "LogBindCalledH5";
    public static final String ad = "LogCalledH5";
    public static final String ae = "LogHkLoginByIntent";
    public static final String af = "SchemePayWrongHashEx";
    public static final String ag = "LogAppLaunchSwitchEnabled";
    public static final String ah = "H5CbUrlEmpty";
    public static final String ai = "H5CbEx";
    public static final String aj = "BuildSchemePayUriError";
    public static final String ak = "StartActivityEx";
    public static final String al = "JSONEx";
    public static final String am = "ParseBundleSerializableError";
    public static final String an = "ParseSchemeQueryError";
    public static final String ao = "tid_context_null";
    public static final String ap = "partner";
    public static final String aq = "out_trade_no";
    public static final String ar = "trade_no";
    public static final String as = "biz_content";
    public static final String at = "app_id";

    /* renamed from: b, reason: collision with root package name */
    public static final String f2572b = "biz";

    /* renamed from: c, reason: collision with root package name */
    public static final String f2573c = "cp";
    public static final String d = "auth";
    public static final String e = "third";
    public static final String f = "tid";
    public static final String g = "wlt";
    public static final String h = "FormatResultEx";
    public static final String i = "GetApdidEx";
    public static final String j = "GetApdidNull";
    public static final String k = "GetApdidTimeout";
    public static final String l = "GetUtdidEx";
    public static final String m = "GetPackageInfoEx";
    public static final String n = "NotIncludeSignatures";
    public static final String o = "GetInstalledPackagesEx";
    public static final String p = "GetPublicKeyFromSignEx";
    public static final String q = "H5PayNetworkError";
    public static final String r = "H5AuthNetworkError";
    public static final String s = "SSLError";
    public static final String t = "SSLProceed";
    public static final String u = "SSLDenied";
    public static final String v = "H5PayDataAnalysisError";
    public static final String w = "H5AuthDataAnalysisError";
    public static final String x = "PublicKeyUnmatch";
    public static final String y = "ClientBindFailed";
    public static final String z = "TriDesEncryptError";
    private String aA;
    private String aB = "";
    private String aC = "";
    private String aD;
    private String au;
    private String av;
    private String aw;
    private String ax;
    private String ay;
    private String az;

    public b(Context context, boolean z2) {
        context = context != null ? context.getApplicationContext() : context;
        this.au = c();
        this.aw = a(context);
        this.ax = a(z2 ? 0L : a.c.a(context));
        this.ay = e();
        this.az = b(context);
        this.aA = "-";
        this.aD = "-";
    }

    private boolean a() {
        return TextUtils.isEmpty(this.aC);
    }

    void a(String str, String str2, Throwable th) {
        c(str, str2, a(th));
    }

    void a(String str, String str2, Throwable th, String str3) {
        c(str, str2, str3 + ": " + a(th));
    }

    private synchronized void c(String str, String str2, String str3) {
        c.d(com.alipay.sdk.cons.a.x, String.format("err %s %s %s", str, str2, str3));
        String str4 = "";
        if (!TextUtils.isEmpty(this.aC)) {
            str4 = "^";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        Object[] objArr = new Object[4];
        objArr[0] = str;
        objArr[1] = str2;
        objArr[2] = TextUtils.isEmpty(str3) ? "-" : b(str3);
        objArr[3] = b(b());
        sb.append(String.format("%s,%s,%s,%s", objArr));
        this.aC += sb.toString();
    }

    void a(String str, String str2, String str3) {
        c(str, str2, str3);
    }

    void b(String str, String str2, String str3) {
        d("", str, str2 + "|" + str3);
    }

    void a(String str, String str2) {
        d("", str, str2);
    }

    private synchronized void d(String str, String str2, String str3) {
        c.b(com.alipay.sdk.cons.a.x, String.format("event %s %s %s", str, str2, str3));
        String str4 = "";
        if (!TextUtils.isEmpty(this.aB)) {
            str4 = "^";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        Object[] objArr = new Object[4];
        objArr[0] = TextUtils.isEmpty(str) ? "-" : b(str);
        objArr[1] = b(str2);
        objArr[2] = b(str3);
        objArr[3] = b(b());
        sb.append(String.format("%s,%s,%s,-,-,-,-,-,-,-,-,-,-,%s", objArr));
        this.aB += sb.toString();
    }

    private static String b() {
        return new SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
    }

    private static String b(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(IWeiboService.Scope.EMPTY_SCOPE, "，").replace("^", "~").replace("#", "＃");
    }

    private static String c(String str) {
        return TextUtils.isEmpty(str) ? "-" : str;
    }

    private static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName());
            stringBuffer.append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                int i2 = 0;
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString());
                    stringBuffer.append(" 》 ");
                    i2++;
                    if (i2 > 5) {
                        break;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    public String a(String str) {
        this.av = d(str);
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", this.au, this.av, this.aw, this.ax, this.ay, this.az, this.aA, c(this.aB), c(this.aC), this.aD);
    }

    @SuppressLint({"SimpleDateFormat"})
    private static String c() {
        return String.format("%s,%s", d(), new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
    }

    private static String d() {
        try {
            return UUID.randomUUID().toString();
        } catch (Throwable unused) {
            return "12345678uuid";
        }
    }

    private static String d(String str) throws JSONException {
        String strReplace;
        String string;
        if (str == null) {
            str = "";
        }
        String[] strArrSplit = str.split("&");
        String str2 = null;
        if (strArrSplit != null) {
            strReplace = null;
            string = null;
            String strReplace2 = null;
            for (String str3 : strArrSplit) {
                String[] strArrSplit2 = str3.split("=");
                if (strArrSplit2 != null && strArrSplit2.length == 2) {
                    if (strArrSplit2[0].equalsIgnoreCase(ap)) {
                        strReplace = strArrSplit2[1].replace("\"", "");
                    } else if (strArrSplit2[0].equalsIgnoreCase(aq)) {
                        string = strArrSplit2[1].replace("\"", "");
                    } else if (strArrSplit2[0].equalsIgnoreCase(ar)) {
                        strReplace2 = strArrSplit2[1].replace("\"", "");
                    } else if (strArrSplit2[0].equalsIgnoreCase(as)) {
                        try {
                            JSONObject jSONObject = new JSONObject(k.b(com.alipay.sdk.sys.a.a(), strArrSplit2[1]));
                            if (TextUtils.isEmpty(string)) {
                                string = jSONObject.getString(aq);
                            }
                        } catch (Throwable unused) {
                        }
                    } else if (strArrSplit2[0].equalsIgnoreCase("app_id") && TextUtils.isEmpty(strReplace)) {
                        strReplace = strArrSplit2[1];
                    }
                }
            }
            str2 = strReplace2;
        } else {
            strReplace = null;
            string = null;
        }
        return String.format("%s,%s,-,%s,-,-,-", b(str2), b(string), b(strReplace));
    }

    private static String a(Context context) {
        String str = "-";
        String str2 = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                String packageName = applicationContext.getPackageName();
                try {
                    PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(packageName, 64);
                    str2 = packageInfo.versionName + "|" + a(packageInfo);
                } catch (Throwable unused) {
                }
                str = packageName;
            } catch (Throwable unused2) {
            }
        }
        return String.format("%s,%s,-,-,-", b(str), b(str2));
    }

    private static String a(PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length == 0) {
            return OnekeyLoginConstants.CU_RESULT_SUCCESS;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(packageInfo.signatures.length);
            for (Signature signature : packageInfo.signatures) {
                String strSubstring = "?";
                try {
                    String strA = k.a((com.alipay.sdk.sys.a) null, signature.toByteArray());
                    strSubstring = TextUtils.isEmpty(strA) ? "?" : k.f(strA).substring(0, 8);
                } catch (Throwable unused) {
                }
                sb.append("-");
                sb.append(strSubstring);
            }
            return sb.toString();
        } catch (Throwable unused2) {
            return "?";
        }
    }

    private static String a(long j2) {
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,%s,-", b("15.7.9"), b("h.a.3.7.9"), "~" + j2);
    }

    private static String e() {
        return String.format("%s,%s,-,-,-", b(com.alipay.sdk.tid.a.a(com.alipay.sdk.sys.b.a().b()).a()), b(com.alipay.sdk.sys.b.a().e()));
    }

    private static String b(Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", b(com.alipay.sdk.util.a.d(context)), DispatchConstants.ANDROID, b(Build.VERSION.RELEASE), b(Build.MODEL), "-", b(com.alipay.sdk.util.a.a(context).a()), b(com.alipay.sdk.util.a.b(context).b()), "gw", b(com.alipay.sdk.util.a.a(context).b()));
    }
}
