package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.umeng.message.MsgConstant;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.push.dg;
import com.xiaomi.push.dh;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Logger {
    private static boolean sDisablePushLog;
    private static LoggerInterface sUserLogger;

    public static void disablePushFileLog(Context context) {
        sDisablePushLog = true;
        setPushLog(context);
    }

    public static void enablePushFileLog(Context context) {
        sDisablePushLog = false;
        setPushLog(context);
    }

    @Deprecated
    public static File getLogFile(String str) {
        return null;
    }

    protected static LoggerInterface getUserLogger() {
        return sUserLogger;
    }

    private static boolean hasWritePermission(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null) {
                for (String str : strArr) {
                    if (MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE.equals(str)) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static void setLogger(Context context, LoggerInterface loggerInterface) {
        sUserLogger = loggerInterface;
        setPushLog(context);
    }

    public static void setPushLog(Context context) {
        boolean z = false;
        boolean z2 = sUserLogger != null;
        if (sDisablePushLog) {
            z2 = false;
        } else if (hasWritePermission(context)) {
            z = true;
        }
        com.xiaomi.channel.commonutils.logger.b.a(new dg(z2 ? sUserLogger : null, z ? new dh(context) : null));
    }

    @Deprecated
    public static void uploadLogFile(Context context, boolean z) {
    }
}
