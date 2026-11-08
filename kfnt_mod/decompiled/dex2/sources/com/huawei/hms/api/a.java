package com.huawei.hms.api;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class a {
    static final a a = new a();
    List<Activity> b = new ArrayList(1);

    a() {
    }

    void a(Activity activity) {
        for (Activity activity2 : this.b) {
            if (activity2 != null && activity2 != activity && !activity2.isFinishing()) {
                activity2.finish();
            }
        }
        this.b.add(activity);
    }

    void b(Activity activity) {
        this.b.remove(activity);
    }
}
