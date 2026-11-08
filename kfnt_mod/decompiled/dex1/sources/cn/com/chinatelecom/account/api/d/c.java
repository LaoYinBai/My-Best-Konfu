package cn.com.chinatelecom.account.api.d;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.e.j;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.net.InetAddress;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String f2261a = "c";

    /* renamed from: b, reason: collision with root package name */
    private static String f2262b = null;

    /* renamed from: c, reason: collision with root package name */
    private static long f2263c = 0;
    private static long d = 1800000;

    public static synchronized String a() {
        if (System.currentTimeMillis() >= f2263c || !cn.com.chinatelecom.account.api.e.d.a(f2262b)) {
            return null;
        }
        return f2262b;
    }

    public static void a(final Context context) {
        if (b(context) && f2262b == null && cn.com.chinatelecom.account.api.e.g.a() == null) {
            new cn.com.chinatelecom.account.api.c.d().a(new cn.com.chinatelecom.account.api.c.e() { // from class: cn.com.chinatelecom.account.api.d.c.1
                @Override // cn.com.chinatelecom.account.api.c.e
                public void a() {
                    cn.com.chinatelecom.account.api.e.e eVarA;
                    String strA;
                    try {
                        String strA2 = cn.com.chinatelecom.account.api.e.d.a();
                        cn.com.chinatelecom.account.api.e.f.a(strA2).a(cn.com.chinatelecom.account.api.e.d.a(context)).c("dns").b(cn.com.chinatelecom.account.api.e.g.f(context));
                        String strA3 = cn.com.chinatelecom.account.api.a.d.a(cn.com.chinatelecom.account.api.e.b.f);
                        String strB = c.b(strA3, strA2, 0);
                        if (TextUtils.isEmpty(strB)) {
                            strB = c.b(strA3, strA2, 1);
                        }
                        synchronized (c.class) {
                            if (TextUtils.isEmpty(strB)) {
                                eVarA = cn.com.chinatelecom.account.api.e.f.a(strA2).a(80011);
                                strA = cn.com.chinatelecom.account.api.a.d.a(j.q);
                            } else {
                                String unused = c.f2262b = strB;
                                long unused2 = c.f2263c = System.currentTimeMillis() + c.d;
                                eVarA = cn.com.chinatelecom.account.api.e.f.a(strA2).a(0);
                                strA = "success";
                            }
                            eVarA.e(strA);
                        }
                        cn.com.chinatelecom.account.api.e.f.b(strA2);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2, int i) {
        StringBuilder sb;
        String str3;
        try {
            cn.com.chinatelecom.account.api.e.f.a(str2).b(i);
            return InetAddress.getByName(str).getHostAddress();
        } catch (Exception e) {
            if (i == 0) {
                sb = new StringBuilder();
                str3 = "first exception: ";
            } else {
                sb = new StringBuilder();
                str3 = "retry exception: ";
            }
            sb.append(str3);
            sb.append(e.getMessage());
            cn.com.chinatelecom.account.api.e.f.a(str2).g(sb.toString());
            return null;
        }
    }

    public static boolean b(Context context) {
        try {
            return context.getPackageName().equals(c(context));
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private static String c(Context context) {
        String str = "";
        int iMyPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == iMyPid) {
                str = runningAppProcessInfo.processName;
            }
        }
        return str;
    }
}
