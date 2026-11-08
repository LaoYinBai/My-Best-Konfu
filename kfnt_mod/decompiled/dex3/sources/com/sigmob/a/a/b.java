package com.sigmob.a.a;

import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends com.sigmob.a.c {
    protected final d a;
    private boolean b;
    private long c;
    private long d;
    private byte[] e;
    private a f;

    public b(InputStream inputStream) {
        this(inputStream, 10240, 512);
    }

    public b(InputStream inputStream, int i, int i2) {
        this.a = new d(inputStream, i, i2);
        this.e = null;
        this.b = false;
    }

    private byte[] d() throws IOException {
        if (this.b) {
            return null;
        }
        byte[] bArrB = this.a.b();
        if (bArrB == null || this.a.a(bArrB)) {
            this.b = true;
        }
        if (this.b) {
            return null;
        }
        return bArrB;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
    
        if (r7 == (-1)) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
    
        if (r7 != 61) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
    
        r5 = r5.toString();
        r4 = r4 - r3;
        r3 = new char[r4];
        r8 = r0.read(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0038, code lost:
    
        if (r8 != r4) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        r1.put(r5, new java.lang.String(r3, 0, r4 - 1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0063, code lost:
    
        throw new java.io.IOException("Failed to read Paxheader. Expected " + r4 + " chars, read " + r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0064, code lost:
    
        r5.append((char) r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0069, code lost:
    
        r5 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
    
        r5 = new java.lang.StringBuffer();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
    
        r7 = r0.read();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.a.a.b.e():void");
    }

    private void f() throws IOException {
        byte[] bArrD;
        if (this.f.c()) {
            do {
                bArrD = d();
                if (this.b) {
                    this.f = null;
                    return;
                }
            } while (new c(bArrD).a());
        }
    }

    @Override // java.io.InputStream
    public int available() {
        if (this.c - this.d > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) (this.c - this.d);
    }

    public a b() throws IOException {
        if (this.b) {
            return null;
        }
        if (this.f != null) {
            long j = this.c;
            long jSkip = this.d;
            do {
                j -= jSkip;
                if (j > 0) {
                    jSkip = skip(j);
                } else {
                    this.e = null;
                }
            } while (jSkip > 0);
            throw new RuntimeException("failed to skip current tar entry");
        }
        byte[] bArrD = d();
        if (this.b) {
            this.f = null;
            return null;
        }
        this.f = new a(bArrD);
        this.d = 0L;
        this.c = this.f.b();
        if (this.f.e()) {
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bArr = new byte[ApkDownloadComplianceInterface.INSTALL_BITS];
            while (true) {
                int i = read(bArr);
                if (i < 0) {
                    break;
                }
                stringBuffer.append(new String(bArr, 0, i));
            }
            c();
            if (this.f == null) {
                return null;
            }
            if (stringBuffer.length() > 0 && stringBuffer.charAt(stringBuffer.length() - 1) == 0) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            this.f.a(stringBuffer.toString());
        }
        if (this.f.f()) {
            e();
        }
        if (this.f.d()) {
            f();
        }
        return this.f;
    }

    public com.sigmob.a.a c() {
        return b();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.a.d();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (this.d >= this.c) {
            return -1;
        }
        if (i2 + this.d > this.c) {
            i2 = (int) (this.c - this.d);
        }
        if (this.e != null) {
            int length = i2 > this.e.length ? this.e.length : i2;
            System.arraycopy(this.e, 0, bArr, i, length);
            if (length >= this.e.length) {
                this.e = null;
            } else {
                int length2 = this.e.length - length;
                byte[] bArr2 = new byte[length2];
                System.arraycopy(this.e, length, bArr2, 0, length2);
                this.e = bArr2;
            }
            i3 = length + 0;
            i2 -= length;
            i += length;
        } else {
            i3 = 0;
        }
        while (i2 > 0) {
            byte[] bArrB = this.a.b();
            if (bArrB == null) {
                throw new IOException("unexpected EOF with " + i2 + " bytes unread. Occured at byte: " + a());
            }
            a(bArrB.length);
            int length3 = bArrB.length;
            if (length3 > i2) {
                System.arraycopy(bArrB, 0, bArr, i, i2);
                int i4 = length3 - i2;
                this.e = new byte[i4];
                System.arraycopy(bArrB, i2, this.e, 0, i4);
                length3 = i2;
            } else {
                System.arraycopy(bArrB, 0, bArr, i, length3);
            }
            i3 += length3;
            i2 -= length3;
            i += length3;
        }
        this.d += i3;
        return i3;
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        byte[] bArr = new byte[8192];
        long j2 = j;
        while (j2 > 0) {
            int i = read(bArr, 0, (int) (j2 > ((long) bArr.length) ? bArr.length : j2));
            if (i == -1) {
                break;
            }
            j2 -= i;
        }
        return j - j2;
    }
}
