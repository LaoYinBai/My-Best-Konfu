package com.mbridge.msdk.mbdownload;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: MSGMapper.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class g {
    public static String a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static String e = "";
    public static String f = "";
    public static String g = "";
    public static String h = "";
    public static String i = "";
    public static String j = "";
    public static String k = "";
    public static String l = "";
    public static String m = "";
    public static String n = "";
    public static String o = "";
    public static String p = "";
    public static String q = "";
    public static String r = "";

    public static String a(Context context) {
        if (TextUtils.isEmpty(o)) {
            o = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_ins", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return o;
    }

    public static String b(Context context) {
        if (TextUtils.isEmpty(p)) {
            p = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_open", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return p;
    }

    public static String c(Context context) {
        if (TextUtils.isEmpty(q)) {
            q = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_ins_progress_des", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return q;
    }

    public static String d(Context context) {
        if (TextUtils.isEmpty(r)) {
            r = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_open_progress_des", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return r;
    }

    public static String e(Context context) {
        if (TextUtils.isEmpty(d)) {
            d = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_network_error", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return d;
    }

    public static String f(Context context) {
        if (TextUtils.isEmpty(e)) {
            e = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_start_download", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return e;
    }

    public static String g(Context context) {
        if (TextUtils.isEmpty(h)) {
            h = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_update_finish_to_install", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return h;
    }

    public static String h(Context context) {
        if (TextUtils.isEmpty(i)) {
            i = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_downloading", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return i;
    }

    public static String i(Context context) {
        if (TextUtils.isEmpty(j)) {
            j = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_download_finish_to_install", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return j;
    }

    public static String j(Context context) {
        if (TextUtils.isEmpty(k)) {
            k = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_download_finish_to_install", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return k;
    }

    public static String k(Context context) {
        if (TextUtils.isEmpty(l)) {
            l = context.getResources().getString(context.getResources().getIdentifier("mbridge_download_notify_update_package", "string", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
        return l;
    }
}
