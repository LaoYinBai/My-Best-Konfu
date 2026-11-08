package b.b.a.k;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class t implements b.b.a.h {

    /* renamed from: a, reason: collision with root package name */
    public static volatile t f1146a;

    /* renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArraySet<b.b.a.h> f1147b = new CopyOnWriteArraySet<>();

    public static t a() {
        if (f1146a == null) {
            synchronized (t.class) {
                f1146a = new t();
            }
        }
        return f1146a;
    }

    public void b(b.b.a.h hVar) {
        if (hVar != null) {
            this.f1147b.remove(hVar);
        }
    }

    public void a(long j, String str) {
        Iterator<b.b.a.h> it = this.f1147b.iterator();
        while (it.hasNext()) {
            ((t) it.next()).a(j, str);
        }
    }

    public void a(long j, String str, JSONObject jSONObject) {
        Iterator<b.b.a.h> it = this.f1147b.iterator();
        while (it.hasNext()) {
            ((t) it.next()).a(j, str, jSONObject);
        }
    }

    public void a(b.b.a.h hVar) {
        if (hVar != null) {
            this.f1147b.add(hVar);
        }
    }
}
