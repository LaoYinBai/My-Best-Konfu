package a.a.c.a;

import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends UnsatisfiedLinkError {

    /* renamed from: a, reason: collision with root package name */
    public static final ArrayList<d> f357a = new ArrayList<>();

    public d(String str) {
        super(str);
        synchronized (f357a) {
            f357a.add(this);
        }
    }

    public d(String str, Throwable th) {
        super(str);
        initCause(th);
        synchronized (f357a) {
            f357a.add(this);
        }
    }
}
