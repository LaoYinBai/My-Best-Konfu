package com.appsflyer;

import android.content.ContentResolver;
import android.os.Build;
import android.provider.Settings;
import com.appsflyer.k;
import com.appsflyer.share.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class n {
    n() {
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    static k m192(ContentResolver contentResolver) {
        String string;
        if (contentResolver == null || AppsFlyerProperties.getInstance().getString("amazon_aid") != null || !"Amazon".equals(Build.MANUFACTURER)) {
            return null;
        }
        int i = Settings.Secure.getInt(contentResolver, "limit_ad_tracking", 2);
        if (i == 0) {
            return new k(k.b.AMAZON, Settings.Secure.getString(contentResolver, Constants.URL_ADVERTISING_ID), false);
        }
        if (i == 2) {
            return null;
        }
        try {
            string = Settings.Secure.getString(contentResolver, Constants.URL_ADVERTISING_ID);
        } catch (Throwable th) {
            AFLogger.afErrorLog("Couldn't fetch Amazon Advertising ID (Ad-Tracking is limited!)", th);
            string = "";
        }
        return new k(k.b.AMAZON, string, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0118  */
    /* renamed from: ˋ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void m191(android.content.Context r9, java.util.Map<java.lang.String, java.lang.Object> r10) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.n.m191(android.content.Context, java.util.Map):void");
    }

    static class d extends IllegalStateException {
        d(String str) {
            super(str);
        }
    }
}
