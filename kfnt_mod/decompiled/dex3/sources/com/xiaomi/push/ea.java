package com.xiaomi.push;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ea {

    public static final class a extends e {

        /* renamed from: a, reason: collision with other field name */
        private boolean f253a;

        /* renamed from: b, reason: collision with other field name */
        private boolean f254b;
        private boolean d;
        private boolean e;
        private int a = 0;

        /* renamed from: c, reason: collision with other field name */
        private boolean f255c = false;
        private int b = 0;
        private boolean f = false;

        /* renamed from: a, reason: collision with other field name */
        private List<String> f252a = Collections.emptyList();
        private int c = -1;

        public static a a(byte[] bArr) {
            return (a) new a().a(bArr);
        }

        public static a b(b bVar) {
            return new a().a(bVar);
        }

        @Override // com.xiaomi.push.e
        public int a() {
            if (this.c < 0) {
                b();
            }
            return this.c;
        }

        public a a(int i) {
            this.f253a = true;
            this.a = i;
            return this;
        }

        @Override // com.xiaomi.push.e
        public a a(b bVar) throws d {
            while (true) {
                int iM109a = bVar.m109a();
                if (iM109a == 0) {
                    return this;
                }
                if (iM109a == 8) {
                    a(bVar.c());
                } else if (iM109a == 16) {
                    a(bVar.m115a());
                } else if (iM109a == 24) {
                    b(bVar.m118b());
                } else if (iM109a == 32) {
                    b(bVar.m115a());
                } else if (iM109a == 42) {
                    a(bVar.m112a());
                } else if (!a(bVar, iM109a)) {
                    return this;
                }
            }
        }

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            if (this.f252a.isEmpty()) {
                this.f252a = new ArrayList();
            }
            this.f252a.add(str);
            return this;
        }

        public a a(boolean z) {
            this.f254b = true;
            this.f255c = z;
            return this;
        }

        /* renamed from: a, reason: collision with other method in class */
        public List<String> m211a() {
            return this.f252a;
        }

        @Override // com.xiaomi.push.e
        public void a(c cVar) {
            if (m212a()) {
                cVar.m162b(1, c());
            }
            if (m214c()) {
                cVar.m154a(2, m213b());
            }
            if (m215d()) {
                cVar.m149a(3, d());
            }
            if (f()) {
                cVar.m154a(4, m216e());
            }
            Iterator<String> it = m211a().iterator();
            while (it.hasNext()) {
                cVar.m153a(5, it.next());
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m212a() {
            return this.f253a;
        }

        @Override // com.xiaomi.push.e
        public int b() {
            int iA = 0;
            int iB = m212a() ? c.b(1, c()) + 0 : 0;
            if (m214c()) {
                iB += c.a(2, m213b());
            }
            if (m215d()) {
                iB += c.a(3, d());
            }
            if (f()) {
                iB += c.a(4, m216e());
            }
            Iterator<String> it = m211a().iterator();
            while (it.hasNext()) {
                iA += c.a(it.next());
            }
            int size = iB + iA + (m211a().size() * 1);
            this.c = size;
            return size;
        }

        public a b(int i) {
            this.d = true;
            this.b = i;
            return this;
        }

        public a b(boolean z) {
            this.e = true;
            this.f = z;
            return this;
        }

        /* renamed from: b, reason: collision with other method in class */
        public boolean m213b() {
            return this.f255c;
        }

        public int c() {
            return this.a;
        }

        /* renamed from: c, reason: collision with other method in class */
        public boolean m214c() {
            return this.f254b;
        }

        public int d() {
            return this.b;
        }

        /* renamed from: d, reason: collision with other method in class */
        public boolean m215d() {
            return this.d;
        }

        public int e() {
            return this.f252a.size();
        }

        /* renamed from: e, reason: collision with other method in class */
        public boolean m216e() {
            return this.f;
        }

        public boolean f() {
            return this.e;
        }
    }
}
