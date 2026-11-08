package com.apm.insight.runtime;

import com.apm.insight.nativecrash.NativeImpl;
import com.apm.insight.runtime.q;
import java.util.Comparator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private static final Comparator<a> f3035a = new Comparator<a>() { // from class: com.apm.insight.runtime.g.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final int compare(a aVar, a aVar2) {
            int i = aVar.f3038b + aVar.f3039c;
            int i2 = aVar2.f3038b + aVar2.f3039c;
            if (i != i2) {
                return i > i2 ? -1 : 1;
            }
            return 0;
        }
    };

    /* renamed from: b, reason: collision with root package name */
    private static byte[] f3036b = new byte[4096];

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public String f3037a = "unknown";

        /* renamed from: b, reason: collision with root package name */
        public int f3038b;

        /* renamed from: c, reason: collision with root package name */
        public int f3039c;

        a() {
        }
    }

    public static long a(int i) {
        return NativeImpl.c(i) * q.a.a();
    }
}
