package com.mbridge.msdk.foundation.tools;

import android.net.Uri;
import android.text.TextUtils;
import java.net.URL;

/* compiled from: UriUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class ab {
    public static String a(String str) {
        try {
            return !TextUtils.isEmpty(str) ? new URL(str).getPath() : str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(String str, String str2) {
        try {
            return !TextUtils.isEmpty(str) ? Uri.parse(str).getQueryParameter(str2) : str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
