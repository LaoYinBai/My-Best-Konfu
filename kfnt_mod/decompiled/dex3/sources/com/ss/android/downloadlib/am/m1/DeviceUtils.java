package com.ss.android.downloadlib.am.m1;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.ss.android.downloadlib.addownload.GlobalInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DeviceUtils {
    public static String getImeiMd5(Context context) {
        try {
            return toHexReadable(DigestUtils.get(getDeviceId(), "MD5"));
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getDeviceId() {
        return getDeviceId(GlobalInfo.getContext());
    }

    public static String getDeviceId(Context context) {
        String string;
        try {
            string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
            string = null;
        }
        return TextUtils.isEmpty(string) ? "normal" : string;
    }

    public static String toHexReadable(byte[] bArr) {
        return BinStr.byte2str(bArr);
    }
}
