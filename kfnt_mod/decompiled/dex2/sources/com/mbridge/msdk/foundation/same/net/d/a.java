package com.mbridge.msdk.foundation.same.net.d;

import com.mbridge.msdk.foundation.same.net.i;
import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;
import java.io.File;

/* compiled from: FileDownloadRequest.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends i<Void> {
    private static final String c = "a";
    private File d;
    private File e;

    @Override // com.mbridge.msdk.foundation.same.net.i
    public final int g() {
        return 1;
    }

    public a(File file, String str) {
        super(0, str);
        this.d = file;
        this.e = new File(file + ".tmp");
    }

    @Override // com.mbridge.msdk.foundation.same.net.i
    protected final k<Void> a(com.mbridge.msdk.foundation.same.net.f.c cVar) {
        if (!c()) {
            if (this.e.canRead() && this.e.length() > 0) {
                if (this.e.renameTo(this.d)) {
                    return k.a(null, cVar);
                }
                s.b(c, "Can't rename the download temporary file!");
                return k.a(new com.mbridge.msdk.foundation.same.net.a.a(4, cVar));
            }
            s.b(c, "Download temporary file was invalid!");
            return k.a(new com.mbridge.msdk.foundation.same.net.a.a(4, cVar));
        }
        return k.a(new com.mbridge.msdk.foundation.same.net.a.a(-2, cVar));
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x008a, code lost:
    
        r15.b(r13);
     */
    @Override // com.mbridge.msdk.foundation.same.net.i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] a(com.mbridge.msdk.foundation.same.net.f.b r14, com.mbridge.msdk.foundation.same.net.c r15) throws java.lang.Throwable {
        /*
            r13 = this;
            java.util.List r0 = r14.b()
            java.lang.String r1 = "Content-Length"
            java.lang.String r0 = com.mbridge.msdk.foundation.same.net.g.b.a(r0, r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r1 != 0) goto L1b
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            long r0 = r0.longValue()
            goto L1c
        L1b:
            r0 = r2
        L1c:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L27
            java.lang.String r5 = com.mbridge.msdk.foundation.same.net.d.a.c
            java.lang.String r6 = "Response doesn't present Content-Length!"
            com.mbridge.msdk.foundation.tools.s.b(r5, r6)
        L27:
            r10 = 0
            if (r4 <= 0) goto L43
            java.io.File r4 = r13.d
            long r4 = r4.length()
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 != 0) goto L43
            java.io.File r14 = r13.d
            java.io.File r2 = r13.e
            r14.renameTo(r2)
            r4 = r15
            r5 = r13
            r6 = r0
            r8 = r0
            r4.a(r5, r6, r8)
            return r10
        L43:
            java.io.RandomAccessFile r11 = new java.io.RandomAccessFile
            java.io.File r4 = r13.e
            java.lang.String r4 = r4.getAbsolutePath()
            java.lang.String r5 = "rw"
            r11.<init>(r4, r5)
            r11.setLength(r2)
            java.io.InputStream r4 = r14.c()     // Catch: java.lang.Throwable -> La4
            java.util.List r14 = r14.b()     // Catch: java.lang.Throwable -> La2
            boolean r14 = com.mbridge.msdk.foundation.same.net.g.b.b(r14)     // Catch: java.lang.Throwable -> La2
            if (r14 == 0) goto L6b
            boolean r14 = r4 instanceof java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> La2
            if (r14 != 0) goto L6b
            java.util.zip.GZIPInputStream r14 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> La2
            r14.<init>(r4)     // Catch: java.lang.Throwable -> La2
            goto L6c
        L6b:
            r14 = r4
        L6c:
            r4 = 6144(0x1800, float:8.61E-42)
            byte[] r12 = new byte[r4]     // Catch: java.lang.Throwable -> L9e
        L70:
            int r4 = r14.read(r12)     // Catch: java.lang.Throwable -> L9e
            r5 = -1
            if (r4 == r5) goto L8d
            r5 = 0
            r11.write(r12, r5, r4)     // Catch: java.lang.Throwable -> L9e
            long r4 = (long) r4     // Catch: java.lang.Throwable -> L9e
            long r2 = r2 + r4
            r4 = r15
            r5 = r13
            r6 = r0
            r8 = r2
            r4.a(r5, r6, r8)     // Catch: java.lang.Throwable -> L9e
            boolean r4 = r13.c()     // Catch: java.lang.Throwable -> L9e
            if (r4 == 0) goto L70
            r15.b(r13)     // Catch: java.lang.Throwable -> L9e
        L8d:
            if (r14 == 0) goto L9a
            r14.close()     // Catch: java.lang.Exception -> L93
            goto L9a
        L93:
            java.lang.String r14 = com.mbridge.msdk.foundation.same.net.d.a.c
            java.lang.String r15 = "Error occured when calling InputStream.close"
            com.mbridge.msdk.foundation.tools.s.d(r14, r15)
        L9a:
            r11.close()
            return r10
        L9e:
            r15 = move-exception
            r4 = r14
            r14 = r15
            goto La6
        La2:
            r14 = move-exception
            goto La6
        La4:
            r14 = move-exception
            r4 = r10
        La6:
            if (r4 == 0) goto Lb3
            r4.close()     // Catch: java.lang.Exception -> Lac
            goto Lb3
        Lac:
            java.lang.String r15 = com.mbridge.msdk.foundation.same.net.d.a.c
            java.lang.String r0 = "Error occured when calling InputStream.close"
            com.mbridge.msdk.foundation.tools.s.d(r15, r0)
        Lb3:
            r11.close()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.same.net.d.a.a(com.mbridge.msdk.foundation.same.net.f.b, com.mbridge.msdk.foundation.same.net.c):byte[]");
    }
}
