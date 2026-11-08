package com.bytedance.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class aa {

    /* renamed from: a, reason: collision with root package name */
    public static final LinkedList<bu> f3330a = new LinkedList<>();

    /* renamed from: b, reason: collision with root package name */
    public static final LinkedList<String> f3331b = new LinkedList<>();

    public static int a(ArrayList<bu> arrayList) {
        int size;
        synchronized (f3330a) {
            size = f3330a.size();
            arrayList.addAll(f3330a);
            f3330a.clear();
        }
        return size;
    }

    public static void a(bu buVar) {
        synchronized (f3330a) {
            if (f3330a.size() > 300) {
                f3330a.poll();
            }
            f3330a.add(buVar);
        }
    }

    public static void a(String[] strArr) {
        synchronized (f3331b) {
            if (f3331b.size() > 300) {
                f3331b.poll();
            }
            f3331b.addAll(Arrays.asList(strArr));
        }
    }
}
