package com.bytedance.sdk.open.aweme.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SignatureUtils {
    public static boolean validateSign(Context context, String str, String str2) {
        List<String> md5Signs;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && context != null && (md5Signs = getMd5Signs(context, str)) != null && md5Signs.size() > 0) {
            Iterator<String> it = md5Signs.iterator();
            while (it.hasNext()) {
                if (str2.equalsIgnoreCase(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<String> getMd5Signs(Context context, String str) throws PackageManager.NameNotFoundException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            ArrayList arrayList = new ArrayList();
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null) {
                return null;
            }
            for (Signature signature : signatureArr) {
                arrayList.add(Md5Utils.hexDigest(signature.toByteArray()));
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static String packageSignature(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                sb.append(IWeiboService.Scope.EMPTY_SCOPE);
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
