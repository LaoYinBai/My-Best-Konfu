package com.huawei.a.a.b.i;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.a.a.a.c;
import java.util.Map;

@SuppressLint({"ApplySharedPref"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static String a(Context context, String str) {
        String packageName = context.getPackageName();
        String strM = c.m("_hms_config_tag", "oper");
        if (TextUtils.isEmpty(strM)) {
            return "hms_" + str + "_" + packageName;
        }
        return "hms_" + str + "_" + packageName + "_" + strM;
    }

    public static void a(Context context, String str, String str2, long j) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            com.huawei.a.a.b.e.a.c("SharedPreUtils", "context is null or spName empty or spkey is empty");
            return;
        }
        SharedPreferences sharedPreferencesD = d(context, str);
        if (sharedPreferencesD != null) {
            SharedPreferences.Editor editorEdit = sharedPreferencesD.edit();
            editorEdit.putLong(str2, j);
            editorEdit.commit();
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            com.huawei.a.a.b.e.a.c("SharedPreUtils", "context is null or spName empty or spkey is empty");
            return;
        }
        SharedPreferences sharedPreferencesD = d(context, str);
        if (sharedPreferencesD != null) {
            SharedPreferences.Editor editorEdit = sharedPreferencesD.edit();
            editorEdit.putString(str2, str3);
            editorEdit.commit();
        }
    }

    public static void a(Context context, String str, String... strArr) {
        String str2;
        String str3;
        if (context == null || TextUtils.isEmpty(str)) {
            str2 = "SharedPreUtils";
            str3 = "clearData(): parameter error.context,spname";
        } else {
            if (strArr != null) {
                SharedPreferences sharedPreferencesD = d(context, str);
                if (sharedPreferencesD != null) {
                    SharedPreferences.Editor editorEdit = sharedPreferencesD.edit();
                    if (strArr.length == 0) {
                        editorEdit.clear();
                        editorEdit.commit();
                        return;
                    }
                    for (String str4 : strArr) {
                        if (sharedPreferencesD.contains(str4)) {
                            editorEdit.remove(str4);
                            editorEdit.commit();
                        }
                    }
                    return;
                }
                return;
            }
            str2 = "SharedPreUtils";
            str3 = "clearData(): No data need to be deleted,keys is null";
        }
        com.huawei.a.a.b.e.a.c(str2, str3);
    }

    public static long b(Context context, String str, String str2, long j) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            com.huawei.a.a.b.e.a.c("SharedPreUtils", "context is null or spName empty or spkey is empty");
            return j;
        }
        SharedPreferences sharedPreferencesD = d(context, str);
        return sharedPreferencesD != null ? sharedPreferencesD.getLong(str2, j) : j;
    }

    public static String b(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            com.huawei.a.a.b.e.a.c("SharedPreUtils", "context is null or spName empty or spkey is empty");
            return str3;
        }
        SharedPreferences sharedPreferencesD = d(context, str);
        return sharedPreferencesD != null ? sharedPreferencesD.getString(str2, str3) : str3;
    }

    public static Map<String, ?> b(Context context, String str) {
        return d(context, str).getAll();
    }

    public static void c(Context context, String str) {
        String str2;
        String[] strArr;
        if (TextUtils.isEmpty(str)) {
            com.huawei.a.a.b.e.a.c("SharedPreUtils", "clearTypeDataByTag() eventTag is null or empty!");
            return;
        }
        if ("_default_config_tag".equals(str)) {
            a(context, "stat_v2_1", str);
            str2 = "cached_v2_1";
            strArr = new String[]{str};
        } else {
            String str3 = str + "-oper";
            String str4 = str + "-maint";
            String str5 = str + "-diffprivacy";
            a(context, "stat_v2_1", str3);
            a(context, "cached_v2_1", str3);
            a(context, "stat_v2_1", str4);
            a(context, "cached_v2_1", str4);
            a(context, "stat_v2_1", str5);
            str2 = "cached_v2_1";
            strArr = new String[]{str5};
        }
        a(context, str2, strArr);
    }

    private static SharedPreferences d(Context context, String str) {
        return context.getSharedPreferences(a(context, str), 0);
    }
}
