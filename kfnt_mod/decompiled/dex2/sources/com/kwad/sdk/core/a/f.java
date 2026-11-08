package com.kwad.sdk.core.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.kwad.sdk.KsAdSDKImpl;
import com.yxcorp.kuaishou.addfp.KWEGIDDFP;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class f {
    public static String a(String str) {
        String strDoSign;
        Context context = KsAdSDKImpl.get().getContext();
        return (context == null || (strDoSign = KWEGIDDFP.doSign(context, str)) == null) ? "" : strDoSign;
    }

    private static List<String> a(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            if (!b(key)) {
                arrayList.add(key + ContainerUtils.KEY_VALUE_DELIMITER + c(entry.getValue()));
            }
        }
        try {
            Collections.sort(arrayList);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
        return arrayList;
    }

    public static void a(String str, Map<String, String> map, String str2) {
        map.put("Ks-Sig1", a(b(str, new HashMap(), str2)));
    }

    private static String b(String str, Map<String, String> map, String str2) {
        return str + ContainerUtils.FIELD_DELIMITER + TextUtils.join(ContainerUtils.FIELD_DELIMITER, a(map)) + ContainerUtils.FIELD_DELIMITER + str2;
    }

    private static boolean b(String str) {
        return TextUtils.isEmpty(str) || str.startsWith("__");
    }

    private static String c(String str) {
        return str == null ? "" : str;
    }
}
