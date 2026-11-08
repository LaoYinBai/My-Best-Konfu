package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.push.eb;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class fi {

    /* renamed from: a, reason: collision with other field name */
    private fn f372a;

    /* renamed from: a, reason: collision with other field name */
    private InputStream f373a;

    /* renamed from: a, reason: collision with other field name */
    private volatile boolean f376a;

    /* renamed from: a, reason: collision with other field name */
    private byte[] f377a;

    /* renamed from: a, reason: collision with other field name */
    private ByteBuffer f374a = ByteBuffer.allocate(2048);
    private ByteBuffer b = ByteBuffer.allocate(4);

    /* renamed from: a, reason: collision with other field name */
    private Adler32 f375a = new Adler32();
    private fl a = new fl();

    fi(InputStream inputStream, fn fnVar) {
        this.f373a = new BufferedInputStream(inputStream);
        this.f372a = fnVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.nio.ByteBuffer a() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.fi.a():java.nio.ByteBuffer");
    }

    private void a(ByteBuffer byteBuffer, int i) throws IOException {
        int iPosition = byteBuffer.position();
        do {
            int i2 = this.f373a.read(byteBuffer.array(), iPosition, i);
            if (i2 == -1) {
                throw new EOFException();
            }
            i -= i2;
            iPosition += i2;
        } while (i > 0);
        byteBuffer.position(iPosition);
    }

    private void c() throws IOException {
        StringBuilder sb;
        String string;
        boolean z = false;
        this.f376a = false;
        fg fgVarM320a = m320a();
        if ("CONN".equals(fgVarM320a.m312a())) {
            eb.f fVarA = eb.f.a(fgVarM320a.m316a());
            if (fVarA.m263a()) {
                this.f372a.a(fVarA.m262a());
                z = true;
            }
            if (fVarA.c()) {
                eb.b bVarM261a = fVarA.m261a();
                fg fgVar = new fg();
                fgVar.a("SYNC", "CONF");
                fgVar.a(bVarM261a.a(), (String) null);
                this.f372a.a(fgVar);
            }
            com.xiaomi.channel.commonutils.logger.b.m22a("[Slim] CONN: host = " + fVarA.m264b());
        }
        if (!z) {
            com.xiaomi.channel.commonutils.logger.b.m22a("[Slim] Invalid CONN");
            throw new IOException("Invalid Connection");
        }
        this.f377a = this.f372a.m327a();
        while (!this.f376a) {
            fg fgVarM320a2 = m320a();
            this.f372a.c();
            switch (fgVarM320a2.m314a()) {
                case 1:
                    this.f372a.a(fgVarM320a2);
                    break;
                case 2:
                    if ("SECMSG".equals(fgVarM320a2.m312a()) && ((fgVarM320a2.a() == 2 || fgVarM320a2.a() == 3) && TextUtils.isEmpty(fgVarM320a2.m318b()))) {
                        try {
                            this.f372a.b(this.a.a(fgVarM320a2.m317a(com.xiaomi.push.service.av.a().a(Integer.valueOf(fgVarM320a2.a()).toString(), fgVarM320a2.g()).h), this.f372a));
                        } catch (Exception e) {
                            e = e;
                            sb = new StringBuilder();
                            sb.append("[Slim] Parse packet from Blob chid=");
                            sb.append(fgVarM320a2.a());
                            sb.append("; Id=");
                            sb.append(fgVarM320a2.e());
                            sb.append(" failure:");
                            sb.append(e.getMessage());
                            string = sb.toString();
                            com.xiaomi.channel.commonutils.logger.b.m22a(string);
                        }
                    } else {
                        this.f372a.a(fgVarM320a2);
                    }
                    break;
                case 3:
                    try {
                        this.f372a.b(this.a.a(fgVarM320a2.m316a(), this.f372a));
                    } catch (Exception e2) {
                        e = e2;
                        sb = new StringBuilder();
                        sb.append("[Slim] Parse packet from Blob chid=");
                        sb.append(fgVarM320a2.a());
                        sb.append("; Id=");
                        sb.append(fgVarM320a2.e());
                        sb.append(" failure:");
                        sb.append(e.getMessage());
                        string = sb.toString();
                        com.xiaomi.channel.commonutils.logger.b.m22a(string);
                    }
                default:
                    string = "[Slim] unknow blob type " + ((int) fgVarM320a2.m314a());
                    com.xiaomi.channel.commonutils.logger.b.m22a(string);
                    break;
            }
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    fg m320a() throws IOException {
        int iPosition;
        ByteBuffer byteBufferA;
        try {
            byteBufferA = a();
            iPosition = byteBufferA.position();
        } catch (IOException e) {
            e = e;
            iPosition = 0;
        }
        try {
            byteBufferA.flip();
            byteBufferA.position(8);
            fg fmVar = iPosition == 8 ? new fm() : fg.a(byteBufferA.slice());
            com.xiaomi.channel.commonutils.logger.b.c("[Slim] Read {cmd=" + fmVar.m312a() + ";chid=" + fmVar.a() + ";len=" + iPosition + "}");
            return fmVar;
        } catch (IOException e2) {
            e = e2;
            if (iPosition == 0) {
                iPosition = this.f374a.position();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[Slim] read Blob [");
            byte[] bArrArray = this.f374a.array();
            if (iPosition > 128) {
                iPosition = 128;
            }
            sb.append(af.a(bArrArray, 0, iPosition));
            sb.append("] Err:");
            sb.append(e.getMessage());
            com.xiaomi.channel.commonutils.logger.b.m22a(sb.toString());
            throw e;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    void m321a() throws IOException {
        try {
            c();
        } catch (IOException e) {
            if (!this.f376a) {
                throw e;
            }
        }
    }

    void b() {
        this.f376a = true;
    }
}
