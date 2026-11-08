package anetwork.channel.f;

import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final CopyOnWriteArrayList<b> f702a = new CopyOnWriteArrayList<>();

    public static b a(int i) {
        return f702a.get(i);
    }

    public static int a() {
        return f702a.size();
    }
}
