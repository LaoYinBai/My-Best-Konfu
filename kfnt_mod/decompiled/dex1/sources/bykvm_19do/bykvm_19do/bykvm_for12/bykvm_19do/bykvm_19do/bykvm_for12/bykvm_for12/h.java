package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12;

import android.content.Context;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    private static volatile h f1437a;

    /* renamed from: b, reason: collision with root package name */
    private static HashMap<Integer, f> f1438b;

    /* renamed from: c, reason: collision with root package name */
    private static HashMap<Integer, a> f1439c;

    private h() {
        f1438b = new HashMap<>();
        f1439c = new HashMap<>();
    }

    public static synchronized h a() {
        if (f1437a == null) {
            synchronized (h.class) {
                if (f1437a == null) {
                    f1437a = new h();
                }
            }
        }
        return f1437a;
    }

    public f a(int i) {
        if (f1438b.get(Integer.valueOf(i)) == null) {
            f1438b.put(Integer.valueOf(i), new f(i));
        }
        return f1438b.get(Integer.valueOf(i));
    }

    public a a(int i, Context context) {
        if (f1439c.get(Integer.valueOf(i)) == null) {
            f1439c.put(Integer.valueOf(i), new a(context, i));
        }
        return f1439c.get(Integer.valueOf(i));
    }
}
