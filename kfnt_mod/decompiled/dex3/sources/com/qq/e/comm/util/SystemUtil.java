package com.qq.e.comm.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.qq.e.comm.managers.GDTADManager;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class SystemUtil {
    public static String buildNewPathByProcessName(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        String processName = GDTADManager.getInstance().getProcessName();
        if (StringUtil.isEmpty(processName)) {
            return str;
        }
        boolean zEndsWith = processName.endsWith("_");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(zEndsWith ? "" : "_");
        sb.append(Md5Util.encode(processName));
        return sb.toString();
    }

    public static String getProcessName(Context context) {
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (runningAppProcessInfo.pid == iMyPid) {
                return runningAppProcessInfo.processName;
            }
            continue;
        }
        return null;
    }
}
