package com.bytedance.a;

import com.bytedance.applog.ISessionObserver;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cu implements ISessionObserver {

    /* renamed from: a, reason: collision with root package name */
    public static volatile cu f3478a;

    /* renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArraySet<ISessionObserver> f3479b = new CopyOnWriteArraySet<>();

    public static cu a() {
        if (f3478a == null) {
            synchronized (cu.class) {
                f3478a = new cu();
            }
        }
        return f3478a;
    }

    public void a(ISessionObserver iSessionObserver) {
        if (iSessionObserver != null) {
            this.f3479b.add(iSessionObserver);
        }
    }

    public void b(ISessionObserver iSessionObserver) {
        if (iSessionObserver != null) {
            this.f3479b.remove(iSessionObserver);
        }
    }

    @Override // com.bytedance.applog.ISessionObserver
    public void onSessionBatchEvent(long j, String str, JSONObject jSONObject) {
        Iterator<ISessionObserver> it = this.f3479b.iterator();
        while (it.hasNext()) {
            it.next().onSessionBatchEvent(j, str, jSONObject);
        }
    }

    @Override // com.bytedance.applog.ISessionObserver
    public void onSessionStart(long j, String str) {
        Iterator<ISessionObserver> it = this.f3479b.iterator();
        while (it.hasNext()) {
            it.next().onSessionStart(j, str);
        }
    }

    @Override // com.bytedance.applog.ISessionObserver
    public void onSessionTerminate(long j, String str, JSONObject jSONObject) {
        Iterator<ISessionObserver> it = this.f3479b.iterator();
        while (it.hasNext()) {
            it.next().onSessionTerminate(j, str, jSONObject);
        }
    }
}
