package com.xiaomi.push;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.GZIPInputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class fk {
    public static final byte[] a = {80, 85, 83, 72};

    /* renamed from: a, reason: collision with other field name */
    private byte f384a;

    /* renamed from: a, reason: collision with other field name */
    private int f385a;

    /* renamed from: a, reason: collision with other field name */
    private short f386a;
    private byte[] b;

    public static class a {
        public static final c a = new c();

        /* renamed from: a, reason: collision with other field name */
        public static final d f387a = new d();

        public static byte[] a(byte[] bArr) {
            return a(bArr, f387a);
        }

        public static byte[] a(byte[] bArr, b bVar) {
            if (!fk.m323a(bArr)) {
                return bArr;
            }
            fk fkVarA = fk.a(bArr);
            return (fkVarA.f384a == 0 || fkVarA.f384a != bVar.a()) ? fkVarA.b : bVar.a(fkVarA.b, fkVarA.f385a);
        }
    }

    public interface b {
        byte a();

        byte[] a(byte[] bArr, int i);
    }

    public static final class c {
    }

    public static final class d implements b {
        @Override // com.xiaomi.push.fk.b
        public byte a() {
            return (byte) 2;
        }

        @Override // com.xiaomi.push.fk.b
        public byte[] a(byte[] bArr, int i) throws Throwable {
            GZIPInputStream gZIPInputStream = null;
            try {
                GZIPInputStream gZIPInputStream2 = new GZIPInputStream(new ByteArrayInputStream(bArr), i);
                try {
                    byte[] bArr2 = new byte[i];
                    gZIPInputStream2.read(bArr2);
                    try {
                        gZIPInputStream2.close();
                    } catch (IOException unused) {
                    }
                    return bArr2;
                } catch (IOException unused2) {
                    gZIPInputStream = gZIPInputStream2;
                    if (gZIPInputStream != null) {
                        try {
                            gZIPInputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                    return bArr;
                } catch (Throwable th) {
                    th = th;
                    gZIPInputStream = gZIPInputStream2;
                    if (gZIPInputStream != null) {
                        try {
                            gZIPInputStream.close();
                        } catch (IOException unused4) {
                        }
                    }
                    throw th;
                }
            } catch (IOException unused5) {
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    protected fk(byte b2, int i, byte[] bArr) {
        this((short) 1, b2, i, bArr);
    }

    protected fk(short s, byte b2, int i, byte[] bArr) {
        this.f386a = (short) 1;
        this.f386a = s;
        this.f384a = b2;
        this.f385a = i;
        this.b = bArr;
    }

    public static fk a(byte b2, int i, byte[] bArr) {
        return new fk(b2, i, bArr);
    }

    public static fk a(short s, byte b2, int i, byte[] bArr) {
        return new fk(s, b2, i, bArr);
    }

    public static fk a(byte[] bArr) {
        if (!m323a(bArr)) {
            return a((byte) 0, bArr.length, bArr);
        }
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN);
        byteBufferOrder.getInt();
        short s = byteBufferOrder.getShort();
        byte b2 = byteBufferOrder.get();
        int i = byteBufferOrder.getInt();
        byte[] bArr2 = new byte[byteBufferOrder.getInt()];
        byteBufferOrder.get(bArr2);
        return a(s, b2, i, bArr2);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m323a(byte[] bArr) {
        return a(a, bArr, a.length);
    }

    public static boolean a(byte[] bArr, byte[] bArr2, int i) {
        if (bArr.length < i || bArr2.length < i) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }
}
