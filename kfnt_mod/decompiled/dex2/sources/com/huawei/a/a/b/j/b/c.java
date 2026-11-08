package com.huawei.a.a.b.j.b;

import android.content.Context;
import android.util.Pair;
import com.huawei.a.a.b.k.e;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements com.huawei.a.a.b.j.c.a {
    private Context a = com.huawei.a.a.a.b.j();
    private String b;
    private String c;
    private String d;

    public c(String str, String str2, String str3) {
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    private void a(String str, List<com.huawei.a.a.b.c.b.d> list) {
        Pair<String, String> pairA = e.a(str);
        new com.huawei.a.a.b.d.b(list, (String) pairA.first, (String) pairA.second, this.d).a();
    }

    @Override // java.lang.Runnable
    public void run() {
        com.huawei.a.a.b.e.a.b("EventReportTask", "eventReportTask is running");
        boolean zA = com.huawei.a.a.b.k.b.a(this.a);
        if (zA) {
            com.huawei.a.a.b.e.a.b("EventReportTask", "workKey is refresh,begin report all data");
            this.c = "alltype";
        }
        Map<String, List<com.huawei.a.a.b.c.b.d>> mapA = com.huawei.a.a.b.d.e.a(this.a, this.b, this.c);
        if (mapA.size() == 0) {
            com.huawei.a.a.b.e.a.a("EventReportTask", "no have events to report: tag:%s : type:%s", this.b, this.c);
            return;
        }
        for (Map.Entry<String, List<com.huawei.a.a.b.c.b.d>> entry : mapA.entrySet()) {
            a(entry.getKey(), entry.getValue());
        }
        if ("alltype".equals(this.c)) {
            com.huawei.a.a.b.i.a.a(this.a, "stat_v2_1", new String[0]);
            com.huawei.a.a.b.i.a.a(this.a, "cached_v2_1", new String[0]);
        } else {
            String strA = e.a(this.b, this.c);
            com.huawei.a.a.b.i.a.a(this.a, "stat_v2_1", strA);
            com.huawei.a.a.b.i.a.a(this.a, "cached_v2_1", strA);
        }
        if (zA) {
            com.huawei.a.a.b.e.a.b("EventReportTask", "refresh local key");
            com.huawei.a.a.b.g.b.a().b();
            com.huawei.a.a.b.d.a.a().a(com.huawei.a.a.b.d.a.a().b());
        }
    }
}
