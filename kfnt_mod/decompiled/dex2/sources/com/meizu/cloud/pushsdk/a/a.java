package com.meizu.cloud.pushsdk.a;

import android.content.Context;
import com.meizu.cloud.pushsdk.a.b;
import com.meizu.cloud.pushsdk.base.f;
import com.meizu.cloud.pushsdk.c.a.c;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "a";

    public static c a(Context context) {
        c cVar = new c();
        b bVarC = c(context);
        Map<String, String> mapA = bVarC.a();
        Map<String, String> mapB = bVarC.b();
        Map<String, Object> mapC = bVarC.c();
        Map<String, Object> mapD = bVarC.d();
        if (mapA.size() > 0) {
            cVar.a("ui", mapA);
        }
        if (mapB.size() > 0) {
            cVar.a(AppIconSetting.DEFAULT_LARGE_ICON, mapB);
        }
        if (mapC.size() > 0) {
            cVar.a("ai", mapC);
        }
        if (mapD.size() > 0) {
            cVar.a(AppIconSetting.LARGE_ICON_URL, mapD);
        }
        return cVar;
    }

    public static void b(final Context context) {
        f.a().execute(new Runnable() { // from class: com.meizu.cloud.pushsdk.a.a.1
            @Override // java.lang.Runnable
            public void run() {
                com.meizu.cloud.pushsdk.a.a.b.a(context).a("POST", null, a.a(context).toString());
            }
        });
    }

    private static b c(Context context) {
        return new b.a().a(context).a();
    }
}
