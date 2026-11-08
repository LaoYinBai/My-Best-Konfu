package com.ss.android.message.log;

import android.content.Context;
import com.bytedance.common.push.utility.i;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    /* JADX WARN: Type inference failed for: r0v2, types: [com.ss.android.message.log.c$1] */
    public static void a(final Context context, final String str, final String str2, final String str3, final long j, final long j2, final JSONObject jSONObject) {
        if (i.a(str) || i.a(str2)) {
            return;
        }
        new com.bytedance.common.push.utility.b.b() { // from class: com.ss.android.message.log.c.1
            public void run() {
                try {
                    b bVar = new b();
                    bVar.a = str;
                    bVar.b = str2;
                    bVar.c = str3;
                    bVar.d = j;
                    bVar.e = j2;
                    if (jSONObject != null) {
                        bVar.f = jSONObject.toString();
                    }
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("PushLog", "category = " + bVar.a + " tag = " + bVar.b + " label = " + bVar.c + " value = " + bVar.d + " ext_value = " + j2 + " ext_json = " + bVar.f);
                    }
                    a aVarA = a.a(context);
                    if (aVarA != null) {
                        aVarA.a(bVar);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }.a();
    }

    public static void a(Context context, String str, String str2, String str3, long j, long j2) {
        a(context, str, str2, str3, j, j2, null);
    }

    public static void a(Context context, String str, String str2, JSONObject... jSONObjectArr) {
        if (jSONObjectArr != null && jSONObjectArr.length > 0) {
            a(context, "event_v1", str, str2, 0L, 0L, jSONObjectArr[0]);
        } else {
            a(context, "event_v1", str, str2, 0L, 0L, null);
        }
    }
}
