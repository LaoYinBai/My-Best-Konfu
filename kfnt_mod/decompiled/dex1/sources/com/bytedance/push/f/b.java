package com.bytedance.push.f;

import android.os.Handler;
import android.os.Message;
import com.bytedance.common.push.utility.a.c;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class b {

    /* renamed from: b, reason: collision with root package name */
    private static Handler f4800b;
    private static long d;

    /* renamed from: a, reason: collision with root package name */
    private static c.a f4799a = new c.a() { // from class: com.bytedance.push.f.b.1
        @Override // com.bytedance.common.push.utility.a.c.a
        public void handleMsg(Message message) {
            b.b(message);
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private static volatile AtomicBoolean f4801c = new AtomicBoolean(false);

    private static Handler c() {
        if (f4800b == null) {
            f4800b = new com.bytedance.common.push.utility.a.c(e.a(), f4799a);
        }
        return f4800b;
    }

    static void a(com.bytedance.push.d dVar) {
        if (com.ss.android.message.a.b.c(com.ss.android.message.a.a())) {
            d = System.currentTimeMillis();
            c().sendEmptyMessageDelayed(2, dVar.z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i, long j) throws JSONException {
        String str = i != 201 ? "init success" : "not invoke start() method/not invoke start() when 30s passed";
        if (i == 0) {
            com.bytedance.push.utils.e.c("Monitor", "Push init error:" + str);
        } else {
            com.bytedance.push.utils.e.b("Monitor", "Push init error:" + str);
        }
        a(i, j, str);
    }

    private static void a(int i, long j, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("delta", j);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        e.a("push_monitor_applog_timeout", jSONObject, jSONObject2, null);
    }

    static void a() {
        if (f4801c.compareAndSet(false, true)) {
            if (c().hasMessages(2)) {
                c().removeMessages(2);
            }
            b(0, System.currentTimeMillis() - d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Message message) {
        if (message == null) {
            return;
        }
        Runnable runnable = message.what == 2 ? new Runnable() { // from class: com.bytedance.push.f.b.2
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                b.b(201, System.currentTimeMillis() - b.d);
            }
        } : null;
        if (runnable != null) {
            com.bytedance.common.push.utility.b.b.a(runnable);
        }
    }
}
