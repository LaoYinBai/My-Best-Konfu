package b.b.a.k;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j implements b.b.a.b {

    /* renamed from: a, reason: collision with root package name */
    public static volatile j f1136a;

    /* renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArraySet<b.b.a.b> f1137b = new CopyOnWriteArraySet<>();

    public static j a() {
        if (f1136a == null) {
            synchronized (j.class) {
                if (f1136a == null) {
                    f1136a = new j();
                }
            }
        }
        return f1136a;
    }

    public void b(b.b.a.b bVar) {
        if (bVar != null) {
            this.f1137b.remove(bVar);
        }
    }

    public void a(@NonNull String str, @NonNull String str2, String str3, long j, long j2, String str4) {
        Iterator<b.b.a.b> it = this.f1137b.iterator();
        while (it.hasNext()) {
            ((j) it.next()).a(str, str2, str3, j, j2, str4);
        }
    }

    public void a(@NonNull String str, @Nullable JSONObject jSONObject) {
        Iterator<b.b.a.b> it = this.f1137b.iterator();
        while (it.hasNext()) {
            ((j) it.next()).a(str, jSONObject);
        }
    }

    public void a(b.b.a.b bVar) {
        if (bVar != null) {
            this.f1137b.add(bVar);
        }
    }
}
