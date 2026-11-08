package com.kwad.sdk.core.videocache;

import android.text.TextUtils;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class e extends k {
    private final h a;
    private final com.kwad.sdk.core.videocache.kwai.b b;
    private b c;

    public e(h hVar, com.kwad.sdk.core.videocache.kwai.b bVar) {
        super(hVar, bVar);
        this.b = bVar;
        this.a = hVar;
    }

    private String a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    private void a(OutputStream outputStream, long j) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int iA = a(bArr, j, bArr.length);
            if (iA == -1) {
                outputStream.flush();
                return;
            } else {
                outputStream.write(bArr, 0, iA);
                j += iA;
            }
        }
    }

    private boolean a(d dVar) {
        long jA = this.a.a();
        return (((jA > 0L ? 1 : (jA == 0L ? 0 : -1)) > 0) && dVar.c && ((float) dVar.b) > ((float) this.b.a()) + (((float) jA) * 0.2f)) ? false : true;
    }

    private String b(d dVar) {
        String strC = this.a.c();
        boolean z = !TextUtils.isEmpty(strC);
        long jA = this.b.d() ? this.b.a() : this.a.a();
        boolean z2 = jA >= 0;
        long j = dVar.c ? jA - dVar.b : jA;
        boolean z3 = z2 && dVar.c;
        StringBuilder sb = new StringBuilder();
        sb.append(dVar.c ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n");
        sb.append("Accept-Ranges: bytes\n");
        sb.append(z2 ? a("Content-Length: %d\n", Long.valueOf(j)) : "");
        sb.append(z3 ? a("Content-Range: bytes %d-%d/%d\n", Long.valueOf(dVar.b), Long.valueOf(jA - 1), Long.valueOf(jA)) : "");
        sb.append(z ? a("Content-Type: %s\n", strC) : "");
        sb.append("\n");
        return sb.toString();
    }

    private void b(OutputStream outputStream, long j) {
        h hVar = new h(this.a);
        try {
            hVar.a((int) j);
            byte[] bArr = new byte[8192];
            while (true) {
                int iA = hVar.a(bArr);
                if (iA == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(bArr, 0, iA);
            }
        } finally {
            hVar.b();
        }
    }

    @Override // com.kwad.sdk.core.videocache.k
    protected void a(int i) {
        if (this.c != null) {
            this.c.a(this.b.a, this.a.d(), i);
        }
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public void a(d dVar, Socket socket) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(b(dVar).getBytes(C.UTF8_NAME));
        long j = dVar.b;
        if (a(dVar)) {
            a(bufferedOutputStream, j);
        } else {
            b(bufferedOutputStream, j);
        }
    }
}
