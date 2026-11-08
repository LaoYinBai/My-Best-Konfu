package com.mbridge.msdk.foundation.same.net;

import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;

/* compiled from: Request.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class i<T> implements Comparable<i<T>> {
    private static final String c = "i";
    protected ConcurrentHashMap<String, String> a;
    protected e<T> b;
    private final int d;
    private final String e;
    private Integer f;
    private j g;
    private boolean h;
    private l i;

    protected abstract k<T> a(com.mbridge.msdk.foundation.same.net.f.c cVar);

    public byte[] e() {
        return null;
    }

    public int g() {
        return 2;
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(Object obj) {
        i iVar = (i) obj;
        int iG = g();
        int iG2 = iVar.g();
        return iG == iG2 ? this.f.intValue() - iVar.f.intValue() : iG2 - iG;
    }

    public i(int i, String str, e<T> eVar) {
        this.a = new ConcurrentHashMap<>();
        this.b = null;
        this.h = false;
        this.e = str;
        this.d = i;
        this.b = eVar;
        this.i = new b();
    }

    public i(int i, String str) {
        this.a = new ConcurrentHashMap<>();
        this.b = null;
        this.h = false;
        this.e = str;
        this.d = i;
        this.i = new b();
    }

    public final int a() {
        return this.d;
    }

    public final void a(e<T> eVar) {
        this.b = eVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final i<?> a(l lVar) {
        this.i = lVar;
        return this;
    }

    public final void a(String str) {
        s.b(c, "request finish with " + str);
        if (this.g != null) {
            this.g.b(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final i<?> a(j jVar) {
        this.g = jVar;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final i<?> a(int i) {
        this.f = Integer.valueOf(i);
        return this;
    }

    public final String b() {
        return this.e;
    }

    public final boolean c() {
        return this.h;
    }

    public final Map<String, String> d() {
        return this.a;
    }

    public final void f() {
        a("Connection", CampaignEx.JSON_NATIVE_VIDEO_CLOSE);
        a("Charset", C.UTF8_NAME);
    }

    public byte[] a(com.mbridge.msdk.foundation.same.net.f.b bVar, c cVar) throws com.mbridge.msdk.foundation.same.net.a.a, IOException {
        if (bVar.c() != null) {
            return a(bVar);
        }
        return new byte[0];
    }

    private byte[] a(com.mbridge.msdk.foundation.same.net.f.b bVar) throws Throwable {
        Throwable th;
        InputStream gZIPInputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            InputStream inputStreamC = bVar.c();
            try {
                gZIPInputStream = (!com.mbridge.msdk.foundation.same.net.g.b.b(bVar.b()) || (inputStreamC instanceof GZIPInputStream)) ? inputStreamC : new GZIPInputStream(inputStreamC);
                try {
                    if (gZIPInputStream == null) {
                        s.d(c, "responseToBytes-->response.getContent() == null");
                        throw new com.mbridge.msdk.foundation.same.net.a.a(7, null);
                    }
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = gZIPInputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            byteArrayOutputStream2.write(bArr, 0, i);
                        }
                        byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        if (gZIPInputStream != null) {
                            try {
                                gZIPInputStream.close();
                            } catch (IOException unused) {
                                s.d(c, "responseToBytes InputStream close error!");
                            }
                        }
                        byteArrayOutputStream2.close();
                        return byteArray;
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        if (gZIPInputStream != null) {
                            try {
                                gZIPInputStream.close();
                            } catch (IOException unused2) {
                                s.d(c, "responseToBytes InputStream close error!");
                                throw th;
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
                gZIPInputStream = inputStreamC;
            }
        } catch (Throwable th5) {
            th = th5;
            gZIPInputStream = null;
        }
    }

    public final int h() {
        return this.i.a();
    }

    public final l i() {
        return this.i;
    }

    public final void a(k<T> kVar) {
        if (this.b != null) {
            this.b.a(kVar);
        }
    }

    public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        if (this.b != null) {
            this.b.a(aVar);
        }
    }

    public final void j() {
        if (this.b != null) {
            this.b.c();
        }
    }

    public final void k() {
        if (this.b != null) {
            this.b.a();
        }
    }

    public final void l() {
        if (this.b != null) {
            this.b.b();
        }
    }

    public final void m() {
        if (this.b != null) {
            this.b.d();
        }
    }

    public final void a(long j, long j2) {
        if (this.b != null) {
            this.b.a(j, j2);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.h ? "[X] " : "[ ] ");
        sb.append(this.e);
        sb.append(" ");
        sb.append(g());
        sb.append(" ");
        sb.append(this.f);
        return sb.toString();
    }

    public final void a(String str, String str2) {
        this.a.remove(str);
        this.a.put(str, str2);
    }
}
