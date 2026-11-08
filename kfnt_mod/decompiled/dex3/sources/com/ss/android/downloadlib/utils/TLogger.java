package com.ss.android.downloadlib.utils;

import com.ss.android.download.api.config.DownloadTLogger;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TLogger {
    public static void v(String str, String str2, JSONObject jSONObject) {
        DownloadTLogger downloadTLogger = GlobalInfo.getDownloadTLogger();
        if (downloadTLogger != null) {
            downloadTLogger.log(2, str, str2, jSONObject);
        }
    }

    public static void v(String str, String str2) {
        v(str, str2, null);
    }

    public static void v(String str) {
        v(null, str, null);
    }

    public static void d(String str, String str2, JSONObject jSONObject) {
        DownloadTLogger downloadTLogger = GlobalInfo.getDownloadTLogger();
        if (downloadTLogger != null) {
            downloadTLogger.log(3, str, str2, jSONObject);
        }
    }

    public static void d(String str, String str2) {
        d(str, str2, null);
    }

    public static void d(String str) {
        d(null, str, null);
    }

    public static void e(String str, String str2, JSONObject jSONObject) {
        DownloadTLogger downloadTLogger = GlobalInfo.getDownloadTLogger();
        if (downloadTLogger != null) {
            downloadTLogger.log(6, str, str2, jSONObject);
        }
    }

    public static void e(String str, String str2) {
        e(str, str2, null);
    }

    public static void e(String str) {
        e(null, str, null);
    }
}
