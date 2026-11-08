package com.meizu.cloud.pushsdk.c.b;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.meizu.cloud.pushsdk.b.c.i;
import com.meizu.cloud.pushsdk.b.c.j;
import com.meizu.cloud.pushsdk.b.c.k;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class c {
    protected final Context a;
    protected final f b;
    protected final int c;
    protected final int d;
    protected final int e;
    protected final TimeUnit f;
    private Uri.Builder j;
    private d k;
    private com.meizu.cloud.pushsdk.c.b.a l;
    private h m;
    private final SSLSocketFactory n;
    private final HostnameVerifier o;
    private String p;
    private final long q;
    private final long r;
    private final com.meizu.cloud.pushsdk.b.c.a s;
    private final String h = c.class.getSimpleName();
    private final com.meizu.cloud.pushsdk.b.c.g i = com.meizu.cloud.pushsdk.b.c.g.a("application/json; charset=utf-8");
    protected final AtomicBoolean g = new AtomicBoolean(false);

    public static class a {
        protected final String a;
        protected final Context b;
        protected SSLSocketFactory m;
        protected HostnameVerifier n;
        protected f c = null;
        protected d d = d.POST;
        protected com.meizu.cloud.pushsdk.c.b.a e = com.meizu.cloud.pushsdk.c.b.a.Single;
        protected h f = h.HTTPS;
        protected int g = 5;
        protected int h = 250;
        protected int i = 5;
        protected long j = 40000;
        protected long k = 40000;
        protected TimeUnit l = TimeUnit.SECONDS;
        protected com.meizu.cloud.pushsdk.b.c.a o = new com.meizu.cloud.pushsdk.b.c.e();

        public a(String str, Context context, Class<? extends c> cls) {
            this.a = str;
            this.b = context;
        }

        public a a(int i) {
            this.g = i;
            return this;
        }

        public a a(com.meizu.cloud.pushsdk.b.c.a aVar) {
            if (aVar != null) {
                this.o = aVar;
                com.meizu.cloud.pushsdk.c.f.c.c(a.class.getSimpleName(), "set new call " + aVar, new Object[0]);
            }
            return this;
        }

        public a a(com.meizu.cloud.pushsdk.c.b.a aVar) {
            this.e = aVar;
            return this;
        }

        public a a(f fVar) {
            this.c = fVar;
            return this;
        }

        public a b(int i) {
            this.h = i;
            return this;
        }

        public a c(int i) {
            this.i = i;
            return this;
        }
    }

    public c(a aVar) {
        this.k = aVar.d;
        this.b = aVar.c;
        this.a = aVar.b;
        this.l = aVar.e;
        this.m = aVar.f;
        this.n = aVar.m;
        this.o = aVar.n;
        this.c = aVar.g;
        this.d = aVar.i;
        this.e = aVar.h;
        this.q = aVar.j;
        this.r = aVar.k;
        this.p = aVar.a;
        this.f = aVar.l;
        this.s = aVar.o;
        c();
        com.meizu.cloud.pushsdk.c.f.c.c(this.h, "Emitter created successfully!", new Object[0]);
    }

    private i a(com.meizu.cloud.pushsdk.c.a.a aVar) {
        a(aVar, "");
        this.j.clearQuery();
        HashMap map = (HashMap) aVar.a();
        for (String str : map.keySet()) {
            this.j.appendQueryParameter(str, (String) map.get(str));
        }
        return new i.a().a(this.j.build().toString()).a().c();
    }

    private i a(ArrayList<com.meizu.cloud.pushsdk.c.a.a> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<com.meizu.cloud.pushsdk.c.a.a> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().a());
        }
        com.meizu.cloud.pushsdk.c.a.b bVar = new com.meizu.cloud.pushsdk.c.a.b("push_group_data", arrayList2);
        com.meizu.cloud.pushsdk.c.f.c.b(this.h, "final SelfDescribingJson " + bVar, new Object[0]);
        return new i.a().a(this.j.build().toString()).a(j.a(this.i, bVar.toString())).c();
    }

    private void a(k kVar) {
        if (kVar != null) {
            try {
                if (kVar.b() != null) {
                    kVar.b().close();
                }
            } catch (Exception unused) {
                com.meizu.cloud.pushsdk.c.f.c.b(this.h, "Unable to close source data", new Object[0]);
            }
        }
    }

    private void a(com.meizu.cloud.pushsdk.c.a.a aVar, String str) {
        if ("".equals(str)) {
            str = com.meizu.cloud.pushsdk.c.f.e.a();
        }
        aVar.a("stm", str);
    }

    private void c() {
        StringBuilder sb;
        String str;
        com.meizu.cloud.pushsdk.c.f.c.a(this.h, "security " + this.m, new Object[0]);
        if (this.m == h.HTTP) {
            sb = new StringBuilder();
            str = "http://";
        } else {
            sb = new StringBuilder();
            str = "https://";
        }
        sb.append(str);
        sb.append(this.p);
        this.j = Uri.parse(sb.toString()).buildUpon();
        if (this.k == d.GET) {
            this.j.appendPath("i");
        } else {
            this.j.appendEncodedPath("push_data_report/mobile");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(i iVar) throws Throwable {
        k kVarA;
        k kVar = null;
        try {
            try {
                com.meizu.cloud.pushsdk.c.f.c.b(this.h, "Sending request: %s", iVar);
                kVarA = this.s.a(iVar);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            int iA = kVarA.a();
            a(kVarA);
            return iA;
        } catch (IOException e2) {
            kVar = kVarA;
            e = e2;
            com.meizu.cloud.pushsdk.c.f.c.a(this.h, "Request sending failed: %s", Log.getStackTraceString(e));
            a(kVar);
            return -1;
        } catch (Throwable th2) {
            th = th2;
            kVar = kVarA;
            a(kVar);
            throw th;
        }
    }

    protected LinkedList<e> a(b bVar) {
        int size = bVar.a().size();
        LinkedList<Long> linkedListB = bVar.b();
        LinkedList<e> linkedList = new LinkedList<>();
        long j = 22;
        if (this.k == d.GET) {
            for (int i = 0; i < size; i++) {
                LinkedList linkedList2 = new LinkedList();
                linkedList2.add(linkedListB.get(i));
                com.meizu.cloud.pushsdk.c.a.a aVar = bVar.a().get(i);
                linkedList.add(new e(aVar.b() + 22 > this.q, a(aVar), linkedList2));
            }
        } else {
            int iA = 0;
            while (iA < size) {
                LinkedList linkedList3 = new LinkedList();
                ArrayList<com.meizu.cloud.pushsdk.c.a.a> arrayList = new ArrayList<>();
                long j2 = 0;
                LinkedList linkedList4 = linkedList3;
                int i2 = iA;
                while (i2 < this.l.a() + iA && i2 < size) {
                    com.meizu.cloud.pushsdk.c.a.a aVar2 = bVar.a().get(i2);
                    long jB = aVar2.b() + j;
                    if (jB + 88 > this.r) {
                        ArrayList<com.meizu.cloud.pushsdk.c.a.a> arrayList2 = new ArrayList<>();
                        LinkedList linkedList5 = new LinkedList();
                        arrayList2.add(aVar2);
                        linkedList5.add(linkedListB.get(i2));
                        linkedList.add(new e(true, a(arrayList2), linkedList5));
                    } else {
                        j2 += jB;
                        if (j2 + 88 + (arrayList.size() - 1) > this.r) {
                            linkedList.add(new e(false, a(arrayList), linkedList4));
                            ArrayList<com.meizu.cloud.pushsdk.c.a.a> arrayList3 = new ArrayList<>();
                            LinkedList linkedList6 = new LinkedList();
                            arrayList3.add(aVar2);
                            linkedList6.add(linkedListB.get(i2));
                            arrayList = arrayList3;
                            linkedList4 = linkedList6;
                            j2 = jB;
                        } else {
                            arrayList.add(aVar2);
                            linkedList4.add(linkedListB.get(i2));
                        }
                    }
                    i2++;
                    j = 22;
                }
                if (!arrayList.isEmpty()) {
                    linkedList.add(new e(false, a(arrayList), linkedList4));
                }
                iA += this.l.a();
                j = 22;
            }
        }
        return linkedList;
    }

    public abstract void a();

    public abstract void a(com.meizu.cloud.pushsdk.c.a.a aVar, boolean z);

    protected boolean a(int i) {
        return i >= 200 && i < 300;
    }

    public String b() {
        return this.j.clearQuery().build().toString();
    }
}
