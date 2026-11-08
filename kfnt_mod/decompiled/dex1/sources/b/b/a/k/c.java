package b.b.a.k;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements com.apm.applog.b {

    /* renamed from: a, reason: collision with root package name */
    public static volatile ConcurrentHashMap<String, c> f1125a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArraySet<com.apm.applog.b> f1126b = new CopyOnWriteArraySet<>();

    public static c a(String str) {
        c cVar = f1125a.get(str);
        if (cVar == null) {
            synchronized (c.class) {
                cVar = new c();
                f1125a.put(str, cVar);
            }
        }
        return cVar;
    }

    @Override // com.apm.applog.b
    public void a(String str, String str2) {
        Iterator<com.apm.applog.b> it = this.f1126b.iterator();
        while (it.hasNext()) {
            it.next().a(str, str2);
        }
    }

    @Override // com.apm.applog.b
    public void a(String str, String str2, String str3) {
        Iterator<com.apm.applog.b> it = this.f1126b.iterator();
        while (it.hasNext()) {
            it.next().a(str, str2, str3);
        }
    }

    @Override // com.apm.applog.b
    public void a(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        Iterator<com.apm.applog.b> it = this.f1126b.iterator();
        while (it.hasNext()) {
            it.next().a(z, str, str2, str3, str4, str5, str6);
        }
    }

    @Override // com.apm.applog.b
    public void a(boolean z, JSONObject jSONObject) {
        Iterator<com.apm.applog.b> it = this.f1126b.iterator();
        while (it.hasNext()) {
            it.next().a(z, jSONObject);
        }
    }

    public void b(com.apm.applog.b bVar) {
        if (bVar != null) {
            this.f1126b.remove(bVar);
        }
    }

    @Override // com.apm.applog.b
    public void b(boolean z, JSONObject jSONObject) {
        Iterator<com.apm.applog.b> it = this.f1126b.iterator();
        while (it.hasNext()) {
            it.next().b(z, jSONObject);
        }
    }

    public void a(com.apm.applog.b bVar) {
        if (bVar != null) {
            this.f1126b.add(bVar);
        }
    }
}
