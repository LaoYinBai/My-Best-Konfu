package com.bytedance.a;

import com.bytedance.applog.IDataObserver;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class n implements IDataObserver {

    /* renamed from: a, reason: collision with root package name */
    public static volatile n f3532a;

    /* renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArraySet<IDataObserver> f3533b = new CopyOnWriteArraySet<>();

    public static n a() {
        if (f3532a == null) {
            synchronized (n.class) {
                if (f3532a == null) {
                    f3532a = new n();
                }
            }
        }
        return f3532a;
    }

    public void a(IDataObserver iDataObserver) {
        if (iDataObserver != null) {
            this.f3533b.add(iDataObserver);
        }
    }

    public void b(IDataObserver iDataObserver) {
        if (iDataObserver != null) {
            this.f3533b.remove(iDataObserver);
        }
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onAbVidsChange(String str, String str2) {
        Iterator<IDataObserver> it = this.f3533b.iterator();
        while (it.hasNext()) {
            it.next().onAbVidsChange(str, str2);
        }
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onIdLoaded(String str, String str2, String str3) {
        Iterator<IDataObserver> it = this.f3533b.iterator();
        while (it.hasNext()) {
            it.next().onIdLoaded(str, str2, str3);
        }
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteAbConfigGet(boolean z, JSONObject jSONObject) {
        Iterator<IDataObserver> it = this.f3533b.iterator();
        while (it.hasNext()) {
            it.next().onRemoteAbConfigGet(z, jSONObject);
        }
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteConfigGet(boolean z, JSONObject jSONObject) {
        Iterator<IDataObserver> it = this.f3533b.iterator();
        while (it.hasNext()) {
            it.next().onRemoteConfigGet(z, jSONObject);
        }
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteIdGet(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        Iterator<IDataObserver> it = this.f3533b.iterator();
        while (it.hasNext()) {
            it.next().onRemoteIdGet(z, str, str2, str3, str4, str5, str6);
        }
    }
}
