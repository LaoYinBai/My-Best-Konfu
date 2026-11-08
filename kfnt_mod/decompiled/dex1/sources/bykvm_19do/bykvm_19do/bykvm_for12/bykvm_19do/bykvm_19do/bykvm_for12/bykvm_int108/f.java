package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static String f1455a;

    public static String a(Context context) {
        String str = f1455a;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    f1455a = runningAppProcessInfo.processName;
                    return f1455a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        f1455a = a();
        return f1455a;
    }

    public static boolean b(Context context) {
        String strA = a(context);
        return (strA == null || !strA.contains(":")) && strA != null && strA.equals(context.getPackageName());
    }

    public static boolean c(Context context) {
        String strA = a(context);
        return strA != null && (strA.endsWith(":push") || strA.endsWith(":pushservice"));
    }

    private static String a() throws IOException {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
        } catch (Throwable unused) {
            bufferedReader = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                int i = bufferedReader.read();
                if (i <= 0) {
                    break;
                }
                sb.append((char) i);
            }
            String string = sb.toString();
            try {
                bufferedReader.close();
            } catch (Exception unused2) {
            }
            return string;
        } catch (Throwable unused3) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused4) {
                }
            }
            return null;
        }
    }
}
