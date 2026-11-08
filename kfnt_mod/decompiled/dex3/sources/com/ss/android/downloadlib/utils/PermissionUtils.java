package com.ss.android.downloadlib.utils;

import android.text.TextUtils;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PermissionUtils {
    private static Map<String, Listener> sListenerMap = Collections.synchronizedMap(new HashMap());

    public interface Listener {
        void onDenied(String str);

        void onGranted();
    }

    public static void request(String[] strArr, Listener listener) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        String strValueOf = String.valueOf(System.currentTimeMillis());
        addListener(strValueOf, listener);
        TTDelegateActivity.requestPermission(strValueOf, strArr);
    }

    public static void handleYes(String str) {
        Listener listenerRemoveListener;
        if (TextUtils.isEmpty(str) || (listenerRemoveListener = removeListener(str)) == null) {
            return;
        }
        listenerRemoveListener.onGranted();
    }

    public static void handleNo(String str, String str2) {
        Listener listenerRemoveListener;
        if (TextUtils.isEmpty(str) || (listenerRemoveListener = removeListener(str)) == null) {
            return;
        }
        listenerRemoveListener.onDenied(str2);
    }

    private static void addListener(String str, Listener listener) {
        if (TextUtils.isEmpty(str) || listener == null) {
            return;
        }
        sListenerMap.put(str, listener);
    }

    private static Listener removeListener(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return sListenerMap.remove(str);
    }

    public static boolean hasPermission(String str) {
        return GlobalInfo.getDownloadPermissionChecker().hasPermission(GlobalInfo.getContext(), str);
    }
}
