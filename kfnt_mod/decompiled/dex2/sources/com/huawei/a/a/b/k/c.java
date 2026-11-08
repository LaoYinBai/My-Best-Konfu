package com.huawei.a.a.b.k;

import android.text.TextUtils;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    public static String a(String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str2)) {
            return a(str, str2, str3) ? str2 : str4;
        }
        com.huawei.a.a.b.e.a.c("hmsSdk", "checkStrParameter() Parameter verification failure! Parameter:" + str);
        return str4;
    }

    public static boolean a(String str) {
        return !a("eventId", str, 256);
    }

    public static boolean a(String str, String str2, int i) {
        String str3;
        StringBuilder sb;
        String str4;
        if (TextUtils.isEmpty(str2)) {
            str3 = "hmsSdk";
            sb = new StringBuilder();
            str4 = "checkString() Parameter is empty : ";
        } else {
            if (str2.length() <= i) {
                return true;
            }
            str3 = "hmsSdk";
            sb = new StringBuilder();
            str4 = "checkString() Failure of parameter length check! Parameter:";
        }
        sb.append(str4);
        sb.append(str);
        com.huawei.a.a.b.e.a.c(str3, sb.toString());
        return false;
    }

    public static boolean a(String str, String str2, String str3) {
        String str4;
        StringBuilder sb;
        String str5;
        if (TextUtils.isEmpty(str2)) {
            str4 = "hmsSdk";
            sb = new StringBuilder();
            str5 = "checkString() Parameter is null! Parameter:";
        } else {
            if (Pattern.compile(str3).matcher(str2).matches()) {
                return true;
            }
            str4 = "hmsSdk";
            sb = new StringBuilder();
            str5 = "checkString() Parameter verification failure! Parameter:";
        }
        sb.append(str5);
        sb.append(str);
        com.huawei.a.a.b.e.a.c(str4, sb.toString());
        return false;
    }

    public static boolean a(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "onEvent() mapValue has not data.so,The data will be empty");
            return false;
        }
        if (map.size() == 1 && (map.get("constants") != null || map.get("_constants") != null)) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "checkMap() the key can't be constants or _constants");
            return false;
        }
        if (map.size() <= 2048 && map.toString().length() <= 204800) {
            return true;
        }
        com.huawei.a.a.b.e.a.c("hmsSdk", "checkMap Map data is too big! size: " + map.size() + " length: " + map.toString().length());
        return false;
    }
}
