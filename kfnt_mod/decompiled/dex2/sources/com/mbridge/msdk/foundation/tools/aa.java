package com.mbridge.msdk.foundation.tools;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.mbridge.msdk.MBridgeConstans;

/* compiled from: TargetAdaptionUtils.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class aa {
    public static int a = -1;
    public static String b = "";
    public static int c = -1;
    public static String d = "";
    public static int e = -1;
    public static int f = -1;
    public static String g = "";
    public static String h = "";
    public static int i = -1;
    public static String j = "";

    public static boolean a(Context context) {
        if (i == 1) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        try {
            if (context == null) {
                i = -1;
                j = "content is null";
                return false;
            }
            if (o.v(context) >= 26 && Build.VERSION.SDK_INT >= 26) {
                new NotificationCompat.Builder(context, "download");
                j = "ok";
                i = 1;
            }
            return true;
        } catch (Exception e2) {
            j = e2.getMessage();
            i = -1;
            return false;
        } catch (NoSuchMethodError e3) {
            j = e3.getMessage();
            i = 0;
            return false;
        } catch (Throwable th) {
            j = th.getMessage();
            i = -1;
            return false;
        }
    }

    public static boolean b(Context context) {
        if (e == 1) {
            return true;
        }
        if (e == 0) {
            return false;
        }
        try {
            if (context == null) {
                e = -1;
                g = "context is null";
                return false;
            }
            if (o.v(context) >= 26 && Build.VERSION.SDK_INT >= 26) {
                String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
                if (strArr == null) {
                    e = -1;
                    g = "permissions is null";
                    return false;
                }
                if (strArr.length == 0) {
                    e = 0;
                    g = "permissions is zero";
                    return false;
                }
                int length = strArr.length;
                int i2 = 0;
                boolean z = true;
                while (i2 < length) {
                    if (strArr[i2].equals("android.permission.REQUEST_INSTALL_PACKAGES")) {
                        e = 1;
                        g = "ok";
                    } else {
                        g = "can't find permission";
                        e = 0;
                        i2++;
                        z = false;
                    }
                }
                return z;
            }
            return true;
        } catch (Exception e2) {
            g = e2.getMessage();
            e = -1;
            return false;
        } catch (Throwable th) {
            g = th.getMessage();
            e = -1;
            return false;
        }
    }

    public static boolean c(Context context) {
        if (c == 1) {
            return true;
        }
        if (c == 0) {
            return false;
        }
        try {
            if (context == null) {
                c = -1;
                d = "context is null";
                return false;
            }
            ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services;
            if (serviceInfoArr == null) {
                c = -1;
                d = "serviceinfo is null";
                return false;
            }
            if (serviceInfoArr.length == 0) {
                c = 0;
                d = "serviceinfos is zero";
                return false;
            }
            int length = serviceInfoArr.length;
            int i2 = 0;
            boolean z = true;
            while (i2 < length) {
                if (serviceInfoArr[i2].name.equals("com.mbridge.msdk.shell.MBService")) {
                    c = 1;
                    d = "ok";
                    return true;
                }
                d = "can't find service";
                c = 0;
                i2++;
                z = false;
            }
            return z;
        } catch (Exception e2) {
            d = e2.getMessage();
            c = -1;
            return false;
        } catch (Throwable th) {
            d = th.getMessage();
            c = -1;
            return false;
        }
    }

    public static boolean d(Context context) {
        if (f == 1) {
            return true;
        }
        if (f == 0) {
            return false;
        }
        try {
            if (context == null) {
                f = -1;
                h = "context is null";
                return false;
            }
            ProviderInfo[] providerInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers;
            if (providerInfoArr == null) {
                f = -1;
                h = "providerInfos is null";
                return false;
            }
            if (providerInfoArr.length == 0) {
                h = "providerInfos is zero";
                f = 0;
                return false;
            }
            int length = providerInfoArr.length;
            int i2 = 0;
            boolean z = true;
            while (i2 < length) {
                if (providerInfoArr[i2].name.equals("com.mbridge.msdk.foundation.tools.MBFileProvider")) {
                    f = 1;
                    h = "ok";
                    return true;
                }
                f = 0;
                h = "providerInfos can't find";
                i2++;
                z = false;
            }
            return z;
        } catch (Exception e2) {
            f = -1;
            h = e2.getMessage();
            return false;
        } catch (Throwable th) {
            h = th.getMessage();
            f = -1;
            return false;
        }
    }

    public static boolean e(Context context) {
        try {
            if (o.v(context) >= 24) {
                return Build.VERSION.SDK_INT >= 24;
            }
            return false;
        } catch (Throwable th) {
            if (!MBridgeConstans.DEBUG) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }
}
