package com.bytedance.push;

import android.text.TextUtils;
import com.ss.android.pushmanager.setting.PushSetting;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f {

    /* renamed from: b, reason: collision with root package name */
    private static Deque<Runnable> f4796b = new LinkedList();

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f4797a = false;

    /* renamed from: c, reason: collision with root package name */
    private volatile boolean f4798c = true;

    f() {
    }

    void a(Runnable runnable) {
        a();
        synchronized (this) {
            if (!this.f4798c) {
                runnable.run();
            } else {
                f4796b.add(runnable);
            }
        }
    }

    private void a() {
        if (this.f4797a) {
            return;
        }
        synchronized (this) {
            if (!this.f4797a) {
                HashMap map = new HashMap();
                PushSetting.getInstance().getSSIDs(map);
                if (!TextUtils.isEmpty((String) map.get(com.bytedance.b.a.c.h.x))) {
                    b();
                }
                this.f4797a = true;
            }
        }
    }

    private void b() {
        while (f4796b.size() > 0) {
            f4796b.pop().run();
        }
        this.f4798c = false;
    }

    void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this) {
            b();
        }
    }
}
