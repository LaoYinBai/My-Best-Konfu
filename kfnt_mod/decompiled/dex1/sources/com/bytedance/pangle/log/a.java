package com.bytedance.pangle.log;

import com.bytedance.pangle.g;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f4648a;

    public static a a() {
        if (f4648a == null) {
            synchronized (a.class) {
                f4648a = new a();
            }
        }
        return f4648a;
    }

    private a() {
    }

    public static void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        if (!g.a().f4632b.isCloseDefaultReport()) {
            ZeusLogger.d("Zeus/DefaultReporterImpl", "report by defaultImpl");
            com.bytedance.pangle.helper.d.a().monitorStatusAndEvent(str, 0, jSONObject, jSONObject2, jSONObject3);
            com.bytedance.pangle.helper.d.a().flushBuffer(new b("report result : "));
            return;
        }
        ZeusLogger.d("Zeus/DefaultReporterImpl", "skip default report");
    }
}
