package com.huawei.a.a.b.d;

import android.text.TextUtils;
import com.kwad.sdk.collector.AppStatusRules;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private List<com.huawei.a.a.b.c.b.d> a;
    private String b;
    private String c;
    private String d;

    public b(List<com.huawei.a.a.b.c.b.d> list, String str, String str2, String str3) {
        this.a = list;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    private void a(List<com.huawei.a.a.b.c.b.d> list, String str, String str2) throws IOException {
        if (list.isEmpty()) {
            return;
        }
        int size = (list.size() / 500) + 1;
        for (int i = 0; i < size; i++) {
            int i2 = i * 500;
            List<com.huawei.a.a.b.c.b.d> listSubList = list.subList(i2, Math.min(list.size(), i2 + 500));
            String strReplace = UUID.randomUUID().toString().replace("-", "");
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jG = com.huawei.a.a.a.c.g(str2, str) * AppStatusRules.DEFAULT_START_TIME;
            ArrayList arrayList = new ArrayList();
            for (com.huawei.a.a.b.c.b.d dVar : listSubList) {
                if (!com.huawei.a.a.b.k.b.a(dVar.b(), jCurrentTimeMillis, jG)) {
                    arrayList.add(dVar);
                }
            }
            if (arrayList.size() > 0) {
                new c(str2, str, this.d, arrayList, strReplace).a();
            } else {
                com.huawei.a.a.b.e.a.c("DataOrganizeHandler", "No data to report handler");
            }
        }
    }

    public void a() {
        if (!"_default_config_tag".equals(this.c)) {
            a(this.a, this.c, this.b);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (com.huawei.a.a.b.c.b.d dVar : this.a) {
            String strA = dVar.a();
            if (TextUtils.isEmpty(strA) || "oper".equals(strA)) {
                arrayList4.add(dVar);
            } else if ("maint".equals(strA)) {
                arrayList.add(dVar);
            } else if ("preins".equals(strA)) {
                arrayList2.add(dVar);
            } else if ("diffprivacy".equals(strA)) {
                arrayList3.add(dVar);
            }
        }
        a(arrayList4, "oper", "_default_config_tag");
        a(arrayList, "maint", "_default_config_tag");
        a(arrayList2, "preins", "_default_config_tag");
        a(arrayList3, "diffprivacy", "_default_config_tag");
    }
}
