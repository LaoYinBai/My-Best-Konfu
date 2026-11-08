package cn.com.chinatelecom.account.api.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import anet.channel.util.ErrorConstant;
import cn.com.chinatelecom.account.api.CtAuth;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    public static String f2297a = null;

    /* renamed from: b, reason: collision with root package name */
    public static String f2298b = null;

    /* renamed from: c, reason: collision with root package name */
    public static String f2299c = null;
    public static String d = "0";
    private static final String[] e = {"46000", "46002", "46004", "46007", "46008"};
    private static final String[] f = {"46003", "46005", "46011"};
    private static final String[] g = {"46001", "46006", "46009"};

    private static int a(int i) {
        int i2 = ErrorConstant.ERROR_EXCEPTION;
        if (i != -101) {
            i2 = -1;
            if (i != -1) {
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return 1;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 17:
                        return 2;
                    case 13:
                    case 18:
                    case 19:
                        return 3;
                    case 20:
                        return 4;
                    default:
                        return i;
                }
            }
        }
        return i2;
    }

    public static NetworkInfo a(Context context) {
        if (context == null) {
            return null;
        }
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static String a() {
        return f2297a;
    }

    public static String a(Context context, boolean z) {
        String strH = h(context);
        if (strH != null) {
            for (String str : f) {
                if (strH.equals(str)) {
                    return z ? "1" : "CT";
                }
            }
            for (String str2 : e) {
                if (strH.equals(str2)) {
                    return z ? OnekeyLoginConstants.UNICOM_TYPE : "CM";
                }
            }
            for (String str3 : g) {
                if (strH.equals(str3)) {
                    return z ? OnekeyLoginConstants.TELECOM_TYPE : "CU";
                }
            }
        }
        return z ? OnekeyLoginConstants.CU_RESULT_SUCCESS : "UN";
    }

    public static String b() {
        return f2298b != null ? "https://open.e.189.cn/openapi/special/getTimeStamp.do".replace(cn.com.chinatelecom.account.api.a.d.a(b.g), f2298b) : "https://open.e.189.cn/openapi/special/getTimeStamp.do";
    }

    public static boolean b(Context context) {
        NetworkInfo networkInfoA = a(context);
        return networkInfoA != null && networkInfoA.isAvailable();
    }

    public static String c() {
        return f2299c != null ? "https://e189.21cn.com/gw/client/accountMsg.do".replace("e189.21cn.com", f2299c) : "https://e189.21cn.com/gw/client/accountMsg.do";
    }

    public static boolean c(Context context) {
        NetworkInfo networkInfoA = a(context);
        return networkInfoA != null && networkInfoA.getType() == 0;
    }

    public static boolean d(Context context) {
        if (context == null) {
            return true;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Throwable th) {
            CtAuth.warn("NetUtil", "isMobileEnable error ", th);
            return true;
        }
    }

    public static String e(Context context) {
        int iJ = j(context);
        if (iJ == -101) {
            return "WIFI";
        }
        switch (iJ) {
            case -1:
            case 0:
                return "null";
            case 1:
                return "2G";
            case 2:
                return "3G";
            case 3:
                return "4G";
            case 4:
                return "5G";
            default:
                return Integer.toString(iJ);
        }
    }

    public static String f(Context context) {
        String strE = e(context);
        return (strE != null && strE.equals("WIFI") && d(context)) ? "BOTH" : strE;
    }

    public static String g(Context context) {
        String strF = f(context);
        return (TextUtils.isEmpty(strF) || strF.equals("null")) ? "15" : strF.equals("2G") ? "10" : strF.equals("3G") ? "11" : strF.equals("4G") ? "12" : strF.equals("5G") ? "16" : strF.equals("WIFI") ? "13" : strF.equals("BOTH") ? "14" : "15";
    }

    public static String h(Context context) {
        try {
            String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
            return !TextUtils.isEmpty(simOperator) ? simOperator : "00000";
        } catch (Throwable th) {
            th.printStackTrace();
            return "00000";
        }
    }

    public static String i(Context context) {
        return a(context, true);
    }

    private static int j(Context context) {
        int subtype = 0;
        try {
            try {
                NetworkInfo networkInfoA = a(context);
                if (networkInfoA != null && networkInfoA.isAvailable() && networkInfoA.isConnected()) {
                    int type = networkInfoA.getType();
                    if (type == 1) {
                        subtype = ErrorConstant.ERROR_EXCEPTION;
                    } else if (type == 0) {
                        try {
                            subtype = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (subtype == 0) {
                            subtype = networkInfoA.getSubtype();
                        }
                    }
                } else {
                    subtype = -1;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } catch (NullPointerException e4) {
            e4.printStackTrace();
        }
        return a(subtype);
    }
}
