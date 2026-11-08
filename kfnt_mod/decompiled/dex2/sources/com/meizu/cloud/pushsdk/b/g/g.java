package com.meizu.cloud.pushsdk.b.g;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class g {
    private static final Logger a = Logger.getLogger(g.class.getName());

    private g() {
    }

    public static c a(l lVar) {
        if (lVar != null) {
            return new h(lVar);
        }
        throw new IllegalArgumentException("sink == null");
    }

    public static d a(m mVar) {
        if (mVar != null) {
            return new i(mVar);
        }
        throw new IllegalArgumentException("source == null");
    }

    public static l a(OutputStream outputStream) {
        return a(outputStream, new n());
    }

    private static l a(final OutputStream outputStream, final n nVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (nVar != null) {
            return new l() { // from class: com.meizu.cloud.pushsdk.b.g.g.1
                @Override // com.meizu.cloud.pushsdk.b.g.l
                public void a(b bVar, long j) throws IOException {
                    o.a(bVar.b, 0L, j);
                    while (j > 0) {
                        nVar.a();
                        j jVar = bVar.a;
                        int iMin = (int) Math.min(j, jVar.c - jVar.b);
                        outputStream.write(jVar.a, jVar.b, iMin);
                        jVar.b += iMin;
                        long j2 = iMin;
                        j -= j2;
                        bVar.b -= j2;
                        if (jVar.b == jVar.c) {
                            bVar.a = jVar.a();
                            k.a(jVar);
                        }
                    }
                }

                @Override // com.meizu.cloud.pushsdk.b.g.l, java.io.Closeable, java.lang.AutoCloseable, com.meizu.cloud.pushsdk.b.g.m
                public void close() throws IOException {
                    outputStream.close();
                }

                @Override // com.meizu.cloud.pushsdk.b.g.l, java.io.Flushable
                public void flush() throws IOException {
                    outputStream.flush();
                }

                public String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static m a(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static m a(InputStream inputStream) {
        return a(inputStream, new n());
    }

    private static m a(final InputStream inputStream, final n nVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (nVar != null) {
            return new m() { // from class: com.meizu.cloud.pushsdk.b.g.g.2
                @Override // com.meizu.cloud.pushsdk.b.g.m
                public long b(b bVar, long j) throws IOException {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    }
                    if (j == 0) {
                        return 0L;
                    }
                    nVar.a();
                    j jVarC = bVar.c(1);
                    int i = inputStream.read(jVarC.a, jVarC.c, (int) Math.min(j, 2048 - jVarC.c));
                    if (i == -1) {
                        return -1L;
                    }
                    jVarC.c += i;
                    long j2 = i;
                    bVar.b += j2;
                    return j2;
                }

                @Override // com.meizu.cloud.pushsdk.b.g.m, java.lang.AutoCloseable
                public void close() throws IOException {
                    inputStream.close();
                }

                public String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }
}
