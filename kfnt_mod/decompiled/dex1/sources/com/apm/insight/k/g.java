package com.apm.insight.k;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.Npth;
import com.apm.insight.l.q;
import com.apm.insight.runtime.o;
import com.apm.insight.runtime.p;
import com.apm.insight.runtime.u;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    private static final ConcurrentHashMap<Object, ConcurrentLinkedQueue<com.apm.insight.entity.c>> f2927a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private static final HashMap<Object, HashMap<String, ConcurrentLinkedQueue<com.apm.insight.entity.c>>> f2928b = new HashMap<>();

    /* renamed from: c, reason: collision with root package name */
    private static volatile g f2929c;
    private volatile boolean e = false;
    private Runnable f = new Runnable() { // from class: com.apm.insight.k.g.1
        @Override // java.lang.Runnable
        public void run() {
            if (Npth.isStopUpload()) {
                return;
            }
            if (!g.f2928b.isEmpty() && o.e()) {
                g.f();
            }
            g.this.c();
            g.this.d.a(g.this.f, com.bytedance.b.a.a.c.g.e);
        }
    };
    private final u d = p.b();

    private g() {
    }

    public static g a() {
        if (f2929c == null) {
            synchronized (g.class) {
                if (f2929c == null) {
                    f2929c = new g();
                }
            }
        }
        return f2929c;
    }

    public static void a(@NonNull com.apm.insight.entity.c cVar) throws JSONException {
        a(com.apm.insight.g.a(), cVar);
    }

    public static void a(@Nullable final Object obj, @NonNull final com.apm.insight.entity.c cVar) throws JSONException {
        Handler handlerA = p.b().a();
        if (handlerA == null || handlerA.getLooper() != Looper.myLooper()) {
            p.b().a(new Runnable() { // from class: com.apm.insight.k.g.2
                @Override // java.lang.Runnable
                public void run() throws JSONException {
                    g.a(obj, cVar);
                }
            });
            return;
        }
        if (obj == null) {
            obj = com.apm.insight.g.a();
        }
        if (!o.e()) {
            q.a("EventUploadQueue", "enqueue before init.");
            c(obj, cVar);
            return;
        }
        if (!com.apm.insight.runtime.a.a(obj)) {
            a.b();
        }
        f();
        String string = null;
        try {
            string = cVar.h().getString(com.bytedance.b.a.a.d.o);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(string) || !com.apm.insight.runtime.a.a(obj, string)) {
            q.a("EventUploadQueue", "logType " + string + " not sampled");
            return;
        }
        q.a("EventUploadQueue", "logType " + string + " enqueued");
        b(obj, cVar);
    }

    private static void b(Object obj, com.apm.insight.entity.c cVar) {
        ConcurrentLinkedQueue<com.apm.insight.entity.c> concurrentLinkedQueue;
        synchronized (obj) {
            concurrentLinkedQueue = f2927a.get(obj);
            if (concurrentLinkedQueue == null) {
                concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
                f2927a.put(obj, concurrentLinkedQueue);
            }
        }
        concurrentLinkedQueue.add(cVar);
        int size = f2927a.size();
        boolean z = size >= 30;
        q.b("[enqueue] size=" + size);
        if (z) {
            g();
        }
    }

    private static void c(Object obj, com.apm.insight.entity.c cVar) throws JSONException {
        ConcurrentLinkedQueue<com.apm.insight.entity.c> concurrentLinkedQueue;
        try {
            String string = cVar.h().getString(com.bytedance.b.a.a.d.o);
            synchronized (f2928b) {
                HashMap<String, ConcurrentLinkedQueue<com.apm.insight.entity.c>> map = f2928b.get(string);
                if (map == null) {
                    map = new HashMap<>();
                    f2928b.put(obj, map);
                }
                concurrentLinkedQueue = map.get(string);
                if (concurrentLinkedQueue == null) {
                    concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
                    map.put(string, concurrentLinkedQueue);
                }
            }
            concurrentLinkedQueue.add(cVar);
            if (concurrentLinkedQueue.size() > 100) {
                concurrentLinkedQueue.poll();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f() {
        HashMap map;
        synchronized (f2928b) {
            map = new HashMap(f2928b);
            f2928b.clear();
        }
        if (!com.apm.insight.runtime.a.b()) {
            q.a("EventUploadQueue", "ApmConfig not inited, clear cache.");
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            for (Map.Entry entry2 : ((HashMap) entry.getValue()).entrySet()) {
                String str = (String) entry2.getKey();
                ConcurrentLinkedQueue concurrentLinkedQueue = (ConcurrentLinkedQueue) entry2.getValue();
                if (concurrentLinkedQueue == null || (com.apm.insight.runtime.a.b() && !com.apm.insight.runtime.a.a(entry.getKey(), str))) {
                    q.a("EventUploadQueue", "logType " + str + " not sampled");
                } else {
                    while (!concurrentLinkedQueue.isEmpty()) {
                        try {
                            com.apm.insight.entity.c cVar = (com.apm.insight.entity.c) concurrentLinkedQueue.poll();
                            if (cVar != null) {
                                b(entry.getKey(), cVar);
                            }
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
        }
    }

    private static void g() {
        if (!o.e() || Npth.isStopUpload()) {
            return;
        }
        try {
            p.b().a(new Runnable() { // from class: com.apm.insight.k.g.3
                @Override // java.lang.Runnable
                public void run() {
                    g.a().c();
                }
            });
        } catch (Throwable unused) {
        }
    }

    public void b() {
        if (f2927a.isEmpty()) {
            this.d.a(this.f, com.bytedance.b.a.a.c.g.e);
        } else {
            this.d.a(this.f);
        }
    }

    public void c() {
        synchronized (this.d) {
            if (this.e) {
                return;
            }
            this.e = true;
            LinkedList linkedList = new LinkedList();
            for (Map.Entry<Object, ConcurrentLinkedQueue<com.apm.insight.entity.c>> entry : f2927a.entrySet()) {
                ConcurrentLinkedQueue<com.apm.insight.entity.c> value = entry.getValue();
                Object key = entry.getKey();
                while (!value.isEmpty()) {
                    for (int i = 0; i < 30; i++) {
                        try {
                            if (value.isEmpty()) {
                                break;
                            }
                            linkedList.add(value.poll());
                        } catch (Throwable th) {
                            q.b(th);
                        }
                    }
                    if (linkedList.isEmpty()) {
                        break;
                    }
                    com.apm.insight.entity.a aVarA = com.apm.insight.runtime.a.f.a().a(linkedList, com.apm.insight.entity.b.a(key));
                    if (aVarA != null) {
                        q.a((Object) "upload events");
                        d.a().a(aVarA.h());
                    }
                    linkedList.clear();
                }
            }
            this.e = false;
        }
    }
}
