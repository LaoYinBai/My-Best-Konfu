package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a0 {
    private static String a(Context context) {
        int iMyPid = Process.myPid();
        String str = "";
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == iMyPid) {
                str = runningAppProcessInfo.processName;
            }
        }
        return str;
    }

    public static boolean b(Context context) {
        String packageName;
        try {
            packageName = context.getPackageName();
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        return packageName.equalsIgnoreCase(a(context));
    }
}
