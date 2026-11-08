package com.kwad.sdk.core.diskcache.a;

import android.content.Context;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    Context a;
    int b;
    long c;
    File d;

    public static class a {
        private Context a;
        private int b = 1;
        private long c = 100;
        private File d;

        public a(Context context) {
            this.a = context.getApplicationContext();
        }

        public a a(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("appVersion <= 0");
            }
            this.b = i;
            return this;
        }

        public a a(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("maxSize <= 0");
            }
            this.c = j;
            return this;
        }

        public a a(File file) {
            d.a(file, "directory is not allow null");
            this.d = file;
            return this;
        }

        public b a() {
            b bVar = new b();
            bVar.a = this.a;
            bVar.b = this.b;
            bVar.c = this.c;
            bVar.d = this.d;
            return bVar;
        }
    }

    private b() {
    }
}
