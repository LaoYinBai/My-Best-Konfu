package com.apm.insight.runtime;

import android.content.Context;
import com.bytedance.common.utility.date.DateDef;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    private Context f3040a;

    /* renamed from: b, reason: collision with root package name */
    private HashMap<String, Long> f3041b;

    /* renamed from: c, reason: collision with root package name */
    private int f3042c = 50;
    private int d = 100;

    public h(Context context) {
        this.f3041b = null;
        this.f3040a = context;
        this.f3041b = c();
        b();
    }

    private void a(File file) {
        File fileG = com.apm.insight.l.o.g(this.f3040a);
        file.renameTo(new File(fileG, String.valueOf(System.currentTimeMillis())));
        String[] list = fileG.list();
        if (list != null && list.length > 5) {
            Arrays.sort(list);
            new File(fileG, list[0]).delete();
        }
    }

    private void b() {
        this.f3042c = a.a(this.f3042c, "custom_event_settings", "npth_simple_setting", "crash_limit_issue");
        this.d = a.a(this.d, "custom_event_settings", "npth_simple_setting", "crash_limit_all");
    }

    private HashMap<String, Long> c() {
        JSONArray jSONArrayB;
        File fileH = com.apm.insight.l.o.h(this.f3040a);
        HashMap<String, Long> map = new HashMap<>();
        map.put(com.bytedance.b.a.a.a.h, Long.valueOf(System.currentTimeMillis()));
        try {
            jSONArrayB = com.apm.insight.l.i.b(fileH.getAbsolutePath());
        } catch (IOException unused) {
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
        }
        if (com.apm.insight.l.l.a(jSONArrayB)) {
            return map;
        }
        Long lDecode = Long.decode(jSONArrayB.optString(0, null));
        if (System.currentTimeMillis() - lDecode.longValue() > DateDef.DAY) {
            a(fileH);
            return map;
        }
        map.put(com.bytedance.b.a.a.a.h, lDecode);
        for (int i = 1; i < jSONArrayB.length(); i++) {
            String[] strArrSplit = jSONArrayB.optString(i, "").split(" ");
            if (strArrSplit.length == 2) {
                map.put(strArrSplit[0], Long.decode(strArrSplit[1]));
            }
        }
        return map;
    }

    public void a() throws Throwable {
        HashMap<String, Long> map = this.f3041b;
        Long lRemove = map.remove(com.bytedance.b.a.a.a.h);
        if (lRemove == null) {
            com.apm.insight.b.a().a("NPTH_CATCH", new RuntimeException("err times, no time"));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(lRemove);
        sb.append('\n');
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(' ');
            sb.append(entry.getValue());
            sb.append('\n');
        }
        try {
            com.apm.insight.l.i.a(com.apm.insight.l.o.h(this.f3040a), sb.toString(), false);
        } catch (IOException unused) {
        }
    }

    public boolean a(String str) {
        if (str == null) {
            str = com.bytedance.b.a.a.d.m;
        }
        return com.apm.insight.l.r.a(this.f3041b, str, 1L).longValue() < ((long) this.f3042c) && com.apm.insight.l.r.a(this.f3041b, "all", 1L).longValue() < ((long) this.d);
    }
}
