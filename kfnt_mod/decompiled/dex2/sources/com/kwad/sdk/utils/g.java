package com.kwad.sdk.utils;

import android.content.Context;
import android.content.pm.Signature;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.foundation.tools.SameMD5;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    private static HashMap<String, ArrayList<String>> a = new HashMap<>();
    private static String b;

    @Nullable
    public static String a(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        ArrayList<String> arrayListA = a(context, "SHA1");
        if (arrayListA != null && arrayListA.size() != 0) {
            b = arrayListA.get(0);
        }
        return b;
    }

    private static String a(Signature signature, String str) throws NoSuchAlgorithmException {
        byte[] byteArray = signature.toByteArray();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            if (messageDigest == null) {
                return "error!";
            }
            byte[] bArrDigest = messageDigest.digest(byteArray);
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bArrDigest) {
                sb.append(Integer.toHexString((b2 & FileDownloadStatus.error) | 256).substring(1, 3).toUpperCase());
                sb.append(":");
            }
            return sb.substring(0, sb.length() - 1);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.e("AppSigningUtil", e.getMessage());
            return "error!";
        }
    }

    @Nullable
    public static ArrayList<String> a(Context context, String str) {
        String str2;
        ArrayList<String> arrayList = null;
        if (context != null && str != null) {
            String packageName = context.getPackageName();
            if (packageName == null) {
                return null;
            }
            if (a.get(str) != null) {
                return a.get(str);
            }
            arrayList = new ArrayList<>();
            try {
                for (Signature signature : b(context, packageName)) {
                    String strA = "error!";
                    if (SameMD5.TAG.equals(str)) {
                        str2 = SameMD5.TAG;
                    } else if ("SHA1".equals(str)) {
                        str2 = "SHA1";
                    } else {
                        if ("SHA256".equals(str)) {
                            str2 = "SHA256";
                        }
                        arrayList.add(strA);
                    }
                    strA = a(signature, str2);
                    arrayList.add(strA);
                }
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.e("AppSigningUtil", "签名信息列表获取失败 " + e.getMessage());
            }
            a.put(str, arrayList);
        }
        return arrayList;
    }

    private static Signature[] b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.e("AppSigningUtil", e.getMessage());
            return null;
        }
    }
}
