package com.sigmob.volley.toolbox;

import com.sigmob.sdk.common.Constants;
import com.sigmob.volley.l;
import com.sigmob.volley.p;
import com.sigmob.volley.s;
import com.sigmob.volley.t;
import com.umeng.message.util.HttpRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f extends com.sigmob.volley.l<d> {
    private File b;
    private File c;
    private d d;
    private long e;
    private final Object f;
    private a g;

    public interface a {
        void a(d dVar);

        void a(d dVar, long j, long j2);

        void b(d dVar);

        void c(d dVar);
    }

    public f(d dVar, a aVar) {
        super(0, dVar.a, null);
        this.e = 0L;
        this.f = new Object();
        this.g = null;
        this.b = new File(dVar.c);
        if (this.b.getParentFile() != null && !this.b.getParentFile().exists()) {
            this.b.getParentFile().mkdirs();
        }
        if (this.b.exists()) {
            this.b.delete();
        }
        this.d = dVar;
        this.c = new File(dVar.c + ".tmp");
        if (!dVar.h) {
            this.c.delete();
        }
        this.g = aVar;
        a((p) new com.sigmob.volley.c(10000, 2, 0.0f));
        a(false);
        t.b("FileDownloadRequest()  [ %s ], url = [%s]", dVar.c, dVar.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x011d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x010d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00fd  */
    @Override // com.sigmob.volley.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.sigmob.volley.n<com.sigmob.volley.toolbox.d> a(com.sigmob.volley.i r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.volley.toolbox.f.a(com.sigmob.volley.i):com.sigmob.volley.n");
    }

    @Override // com.sigmob.volley.l
    public void a(s sVar) {
        this.d.g = 0;
        if (this.b.exists()) {
            this.b.delete();
        }
        if (this.c.exists()) {
            this.c.delete();
        }
        this.d.i = sVar;
        this.g.c(this.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sigmob.volley.l
    public void a(d dVar) {
        this.d.g = 1;
        this.g.a(dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x011f, code lost:
    
        com.sigmob.volley.t.a(r18.d.a + " download  is cancel", new java.lang.Object[0]);
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01ee A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x00fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x019b A[EDGE_INSN: B:118:0x019b->B:63:0x019b BREAK  A[LOOP:0: B:42:0x0113->B:62:0x0172], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0119 A[Catch: all -> 0x01d9, Throwable -> 0x01dc, TRY_LEAVE, TryCatch #2 {Throwable -> 0x01dc, blocks: (B:41:0x0111, B:42:0x0113, B:44:0x0119, B:50:0x013d), top: B:99:0x0111 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0108 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] a(com.sigmob.volley.toolbox.i r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 511
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.volley.toolbox.f.a(com.sigmob.volley.toolbox.i):byte[]");
    }

    @Override // com.sigmob.volley.l
    public Map<String, String> c() {
        HashMap map = new HashMap();
        this.e = this.c.length();
        map.put("Connection", "Keep-Alive");
        map.put(HttpRequest.HEADER_ACCEPT_ENCODING, HttpRequest.ENCODING_GZIP);
        map.put(Constants.RANGE, Constants.RANGE_PARAMS + this.e + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER);
        a("Range,bytes=" + this.e + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER);
        return map;
    }

    @Override // com.sigmob.volley.l
    public void j() {
        super.j();
        synchronized (this.f) {
            this.g = null;
        }
    }

    @Override // com.sigmob.volley.l
    public l.b o() {
        return l.b.LOW;
    }
}
