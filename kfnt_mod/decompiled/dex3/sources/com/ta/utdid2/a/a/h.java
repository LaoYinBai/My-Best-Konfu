package com.ta.utdid2.a.a;

import com.ss.android.socialbase.downloader.constants.MonitorConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h {
    public static String get(String str, String str2) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(cls, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }
}
