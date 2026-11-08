package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hg {
    private static HashMap<String, ArrayList<hn>> a(Context context, List<hn> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HashMap<String, ArrayList<hn>> map = new HashMap<>();
        for (hn hnVar : list) {
            a(context, hnVar);
            ArrayList<hn> arrayList = map.get(hnVar.c());
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                map.put(hnVar.c(), arrayList);
            }
            arrayList.add(hnVar);
        }
        return map;
    }

    private static void a(Context context, hi hiVar, HashMap<String, ArrayList<hn>> map) {
        for (Map.Entry<String, ArrayList<hn>> entry : map.entrySet()) {
            try {
                ArrayList<hn> value = entry.getValue();
                if (value != null && value.size() != 0) {
                    hiVar.a(value, value.get(0).e(), entry.getKey());
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void a(Context context, hi hiVar, List<hn> list) {
        HashMap<String, ArrayList<hn>> mapA = a(context, list);
        if (mapA != null && mapA.size() != 0) {
            a(context, hiVar, mapA);
            return;
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("TinyData TinyDataCacheUploader.uploadTinyData itemsUploading == null || itemsUploading.size() == 0  ts:" + System.currentTimeMillis());
    }

    private static void a(Context context, hn hnVar) {
        if (hnVar.f484a) {
            hnVar.a("push_sdk_channel");
        }
        if (TextUtils.isEmpty(hnVar.d())) {
            hnVar.f(com.xiaomi.push.service.bo.a());
        }
        hnVar.b(System.currentTimeMillis());
        if (TextUtils.isEmpty(hnVar.e())) {
            hnVar.e(context.getPackageName());
        }
        if (TextUtils.isEmpty(hnVar.c())) {
            hnVar.e(hnVar.e());
        }
    }
}
