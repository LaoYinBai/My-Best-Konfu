package com.bytedance.gameprotect;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class q {

    public class b implements c.a.a.a.a.e {

        /* renamed from: a, reason: collision with root package name */
        private final String f3885a = Integer.toString(c.a.a.a.a.h.a());

        public b(q qVar) {
        }

        @Override // c.a.a.a.a.e
        public void a(int i, JSONObject jSONObject) {
            if (jSONObject != null) {
                p.a(i, this.f3885a, jSONObject.toString());
            }
        }
    }

    private enum c {
        INSTANCE;


        /* renamed from: a, reason: collision with root package name */
        private final q f3888a = new q();

        c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public q a() {
            return this.f3888a;
        }
    }

    private q() {
        try {
            c.a.a.a.a.h.a(new b(this));
        } catch (Exception unused) {
        }
    }

    public static q a() {
        return c.INSTANCE.a();
    }
}
