package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import java.text.SimpleDateFormat;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class cn {

    /* renamed from: a, reason: collision with other field name */
    private static SimpleDateFormat f194a = new SimpleDateFormat("yyyy/MM/dd");
    private static String a = f194a.format(Long.valueOf(System.currentTimeMillis()));

    public static hn a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        hn hnVar = new hn();
        hnVar.d("category_push_stat");
        hnVar.a("push_sdk_stat_channel");
        hnVar.a(1L);
        hnVar.b(str);
        hnVar.a(true);
        hnVar.b(System.currentTimeMillis());
        hnVar.g(bw.a(context).m146a());
        hnVar.e("com.xiaomi.xmsf");
        hnVar.f("");
        hnVar.c("push_stat");
        return hnVar;
    }
}
