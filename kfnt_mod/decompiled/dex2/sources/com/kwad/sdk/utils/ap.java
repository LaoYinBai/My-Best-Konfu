package com.kwad.sdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.core.config.b;
import com.kwad.sdk.core.config.item.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ap {
    private static volatile ap a;
    private boolean b = false;
    private p.a c;

    private ap() {
    }

    public static ap a() {
        if (a == null) {
            synchronized (ap.class) {
                if (a == null) {
                    a = new ap();
                }
            }
        }
        return a;
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Class.forName(str) != null;
    }

    private boolean a(List<String> list) {
        if (list != null && list.size() >= 1) {
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                String className = stackTraceElement.getClassName();
                if (className != null) {
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()) {
                        if (className.contains(it.next())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void c() {
        if (this.c == null) {
            return;
        }
        if (!this.b && this.c.c.size() > 0) {
            Iterator<String> it = this.c.c.iterator();
            while (it.hasNext()) {
                this.b = a(it.next());
                if (this.b) {
                    break;
                }
            }
        }
        if (this.b) {
            ArrayList arrayList = new ArrayList();
            if (this.c.a.size() > 0) {
                for (Map.Entry<Integer, String> entry : this.c.a.entrySet()) {
                    if (a(entry.getValue())) {
                        arrayList.add(entry.getKey());
                    }
                }
            }
            com.kwad.sdk.core.report.d.a(a(this.c.b), arrayList);
        }
    }

    public void a(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("ksadsdk_config", 0);
            if (sharedPreferences != null) {
                b.a.aX.a(sharedPreferences);
                this.c = b.a.aX.a();
            }
            if (this.c != null) {
                c();
            }
        }
    }

    public boolean a(KsScene ksScene, String str) {
        boolean zA = this.b ? a(this.c.b) : false;
        com.kwad.sdk.core.report.d.a(ksScene, zA, str);
        return zA;
    }

    public int b() {
        if (this.c != null) {
            return this.c.d;
        }
        return 0;
    }
}
