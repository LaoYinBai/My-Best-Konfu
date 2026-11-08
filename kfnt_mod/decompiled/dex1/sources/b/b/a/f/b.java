package b.b.a.f;

import java.util.ArrayList;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static final LinkedList<b.b.a.j.b> f1063a = new LinkedList<>();

    /* renamed from: b, reason: collision with root package name */
    public static final LinkedList<String> f1064b = new LinkedList<>();

    public static int a(ArrayList<b.b.a.j.b> arrayList) {
        int size;
        synchronized (f1063a) {
            size = f1063a.size();
            arrayList.addAll(f1063a);
            f1063a.clear();
        }
        return size;
    }
}
