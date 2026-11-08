package com.xiaomi.push;

import com.ss.android.socialbase.downloader.constants.MonitorConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class s {
    public static String a(String str, String str2) {
        try {
            return (String) t.a(null, "android.os.SystemProperties").getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("SystemProperties.get: " + e);
            return str2;
        }
    }
}
