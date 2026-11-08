package b.b.a.f;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import b.b.a.k.v;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    public static volatile ConcurrentHashMap<g, j> f1076a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    public static Account f1077b;

    /* renamed from: c, reason: collision with root package name */
    public volatile b.b.a.k.n f1078c;
    public b.b.a.j.a d;

    public static b.b.a.k.n a(Context context, g gVar) {
        j jVar = f1076a.get(gVar);
        if (jVar == null) {
            synchronized (j.class) {
                jVar = f1076a.get(gVar);
                if (jVar == null) {
                    if (context == null) {
                        throw new IllegalArgumentException("context == null");
                    }
                    jVar = new j();
                    jVar.d = new b.b.a.j.a(context);
                    a(context);
                    if (jVar.f1078c == null) {
                        jVar.f1078c = new b.b.a.k.g(context, gVar, jVar.d);
                        if (f1077b != null) {
                            ((b.b.a.k.g) jVar.f1078c).a(f1077b);
                        }
                    }
                }
            }
        }
        return jVar.f1078c;
    }

    @SuppressLint({"MissingPermission"})
    public static void a(Context context, boolean z) {
    }

    public static boolean a() {
        return false;
    }

    public static boolean a(Context context) {
        v.a("DeviceRegisterParameterFactory isNewUserMode false. context=" + context + " isDebugChannel()=false");
        return false;
    }
}
