package com.bytedance.msdk.adapter.baidu;

import android.content.Context;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class BaiduAdapterUtil {

    /* renamed from: a, reason: collision with root package name */
    static Map<String, Integer> f3925a = new HashMap();

    static {
        f3925a.put(OnekeyLoginConstants.CU_RESULT_SUCCESS, 0);
        f3925a.put("0103010", 1);
        f3925a.put("0103011", 1);
        f3925a.put("0103012", 1);
        f3925a.put("0103060", 2);
        f3925a.put("0107001", 3);
        f3925a.put("0107002", 3);
        f3925a.put("0107003", 4);
        f3925a.put("1020001", 5);
        f3925a.put("1040001", 6);
        f3925a.put("1040003", 7);
        f3925a.put("3030002", 8);
    }

    BaiduAdapterUtil() {
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean isNativeSmartOpt(int i) {
        return i == 28 || i == 29 || i == 30 || i == 33 || i == 34 || i == 35 || i == 36 || i == 37;
    }
}
