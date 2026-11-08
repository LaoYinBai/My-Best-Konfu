package com.kwai.filedownloader.download;

import com.kwai.filedownloader.download.ConnectTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements Runnable {
    final int a;
    private final ConnectTask b;
    private final f c;
    private final String d;
    private final boolean e;
    private e f;
    private volatile boolean g;
    private final int h;

    public static class a {
        private final ConnectTask.a a = new ConnectTask.a();
        private f b;
        private String c;
        private Boolean d;
        private Integer e;

        public a a(int i) {
            this.a.a(i);
            return this;
        }

        public a a(com.kwai.filedownloader.c.b bVar) {
            this.a.a(bVar);
            return this;
        }

        public a a(com.kwai.filedownloader.download.a aVar) {
            this.a.a(aVar);
            return this;
        }

        public a a(f fVar) {
            this.b = fVar;
            return this;
        }

        public a a(Integer num) {
            this.e = num;
            return this;
        }

        public a a(String str) {
            this.a.a(str);
            return this;
        }

        public a a(boolean z) {
            this.d = Boolean.valueOf(z);
            return this;
        }

        public c a() {
            if (this.b == null || this.c == null || this.d == null || this.e == null) {
                throw new IllegalArgumentException(com.kwai.filedownloader.e.f.a("%s %s %B", this.b, this.c, this.d));
            }
            ConnectTask connectTaskA = this.a.a();
            return new c(connectTaskA.a, this.e.intValue(), connectTaskA, this.b, this.d.booleanValue(), this.c);
        }

        public a b(String str) {
            this.a.b(str);
            return this;
        }

        public a c(String str) {
            this.c = str;
            return this;
        }
    }

    private c(int i, int i2, ConnectTask connectTask, f fVar, boolean z, String str) {
        this.h = i;
        this.a = i2;
        this.g = false;
        this.c = fVar;
        this.d = str;
        this.b = connectTask;
        this.e = z;
    }

    public void a() {
        this.g = true;
        if (this.f != null) {
            this.f.a();
        }
    }

    public void b() {
        a();
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0136 A[SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.filedownloader.download.c.run():void");
    }
}
