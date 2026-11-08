package com.bytedance.sdk.open.aweme.utils;

import android.text.TextUtils;
import android.view.View;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OpenUtils {
    private static boolean visibilityValid(int i) {
        return i == 0 || i == 8 || i == 4;
    }

    public static String[] arrayUnique(String[] strArr) {
        if (strArr != null) {
            try {
                if (strArr.length > 0) {
                    return (String[]) new HashSet(new ArrayList(Arrays.asList(strArr))).toArray(new String[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return strArr;
    }

    public static void handleRequestScope(Authorization.Request request) {
        String[] strArrArrayUnique;
        String[] strArrSplit;
        if (request == null) {
            return;
        }
        if (request.scope != null) {
            request.scope = request.scope.replace(" ", "");
        }
        if (request.optionalScope1 != null) {
            request.optionalScope1 = request.optionalScope1.replace(" ", "");
        }
        if (request.optionalScope0 != null) {
            request.optionalScope0 = request.optionalScope0.replace(" ", "");
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(request.scope) && (strArrSplit = request.scope.split(IWeiboService.Scope.EMPTY_SCOPE)) != null) {
            for (int i = 0; i < strArrSplit.length; i++) {
                if (!TextUtils.isEmpty(strArrSplit[i])) {
                    if (sb.length() > 0) {
                        sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                    }
                    sb.append(strArrSplit[i]);
                }
            }
        }
        if (!TextUtils.isEmpty(request.optionalScope1)) {
            String[] strArrSplit2 = request.optionalScope1.split(IWeiboService.Scope.EMPTY_SCOPE);
            for (int i2 = 0; i2 < strArrSplit2.length; i2++) {
                if (!TextUtils.isEmpty(strArrSplit2[i2]) && sb.length() > 0) {
                    sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                    sb.append(strArrSplit2[i2]);
                }
            }
        }
        if (!TextUtils.isEmpty(request.optionalScope0)) {
            String[] strArrSplit3 = request.optionalScope0.split(IWeiboService.Scope.EMPTY_SCOPE);
            for (int i3 = 0; i3 < strArrSplit3.length; i3++) {
                if (!TextUtils.isEmpty(strArrSplit3[i3])) {
                    if (sb.length() > 0) {
                        sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                    }
                    sb.append(strArrSplit3[i3]);
                }
            }
        }
        String string = sb.toString();
        if (TextUtils.isEmpty(string) || (strArrArrayUnique = arrayUnique(string.split(IWeiboService.Scope.EMPTY_SCOPE))) == null || strArrArrayUnique.length <= 0) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        for (String str : strArrArrayUnique) {
            if (!TextUtils.isEmpty(str)) {
                if (sb2.length() > 0) {
                    sb2.append(IWeiboService.Scope.EMPTY_SCOPE);
                }
                sb2.append(str);
            }
        }
        request.scope = sb2.toString();
    }

    public static void setViewVisibility(View view, int i) {
        if (view == null || view.getVisibility() == i || !visibilityValid(i)) {
            return;
        }
        view.setVisibility(i);
    }
}
