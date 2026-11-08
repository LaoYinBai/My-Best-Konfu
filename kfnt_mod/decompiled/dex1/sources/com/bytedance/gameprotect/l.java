package com.bytedance.gameprotect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import c.a.a.a.a.y;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l {

    static class a implements c.a.a.a.a.p {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f3882a;

        a(long j) {
            this.f3882a = j;
        }

        @Override // c.a.a.a.a.p
        public void a(c.a.a.a.a.v vVar) {
            p.a(this.f3882a, String.valueOf(vVar.a()), vVar.b());
        }

        @Override // c.a.a.a.a.p
        public void a(Exception exc) {
            p.a(this.f3882a, exc.toString());
        }
    }

    static class b implements c.a.a.a.a.p {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f3883a;

        b(long j) {
            this.f3883a = j;
        }

        @Override // c.a.a.a.a.p
        public void a(c.a.a.a.a.v vVar) {
            p.a(this.f3883a, String.valueOf(vVar.a()), vVar.b());
        }

        @Override // c.a.a.a.a.p
        public void a(Exception exc) {
            p.a(this.f3883a, exc.toString());
        }
    }

    public static Object a(long j, @NonNull String str, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2) {
        if (e.a().b()) {
            return m.a(j, str, map, map2);
        }
        y.a("GET", str, map, null, null, map2, new a(j));
        return 0;
    }

    public static Object a(long j, @NonNull String str, @NonNull byte[] bArr, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2) {
        if (e.a().b()) {
            return m.a(j, str, bArr, map, map2);
        }
        y.a("POST", str, map, bArr, null, map2, new b(j));
        return 0;
    }

    public static Object a(@NonNull String str, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2) {
        if (e.a().b()) {
            return m.a(-1L, str, map, map2);
        }
        try {
            return y.a("GET", str, map, null, null, map2).b();
        } catch (Exception unused) {
            return "Exception occurred";
        }
    }

    public static Object a(@NonNull String str, @NonNull byte[] bArr, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2) {
        if (e.a().b()) {
            return m.a(-1L, str, bArr, map, map2);
        }
        try {
            return y.a("POST", str, map, bArr, null, map2).b();
        } catch (Exception unused) {
            return "Exception occurred";
        }
    }
}
