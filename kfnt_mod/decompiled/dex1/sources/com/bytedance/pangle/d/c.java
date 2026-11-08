package com.bytedance.pangle.d;

import com.bytedance.pangle.util.f;
import com.bytedance.sdk.account.utils.UrlBuilder;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private final FileInputStream f4549a;

    /* renamed from: b, reason: collision with root package name */
    private a f4550b;

    /* renamed from: c, reason: collision with root package name */
    private b[] f4551c;
    private C0139c[] d;
    private final Map<String, C0139c> e = new HashMap();

    public static boolean a(File file) throws IOException {
        try {
            f.a(new c(file));
            return true;
        } catch (IOException unused) {
            f.a(null);
            return false;
        } catch (Throwable th) {
            f.a(null);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private c(File file) throws IOException {
        this.f4550b = null;
        this.f4551c = null;
        this.d = null;
        this.f4549a = new FileInputStream(file);
        FileChannel channel = this.f4549a.getChannel();
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        this.f4550b = new a(channel, 0 == true ? 1 : 0);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(128);
        byteBufferAllocate.limit(this.f4550b.j);
        byteBufferAllocate.order(this.f4550b.f4552a[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
        channel.position(this.f4550b.f);
        this.f4551c = new b[this.f4550b.k];
        for (int i = 0; i < this.f4551c.length; i++) {
            b(channel, byteBufferAllocate, "failed to read phdr.");
            this.f4551c[i] = new b(byteBufferAllocate, this.f4550b.f4552a[4], objArr2 == true ? 1 : 0);
        }
        channel.position(this.f4550b.g);
        byteBufferAllocate.limit(this.f4550b.l);
        this.d = new C0139c[this.f4550b.m];
        for (int i2 = 0; i2 < this.d.length; i2++) {
            b(channel, byteBufferAllocate, "failed to read shdr.");
            this.d[i2] = new C0139c(byteBufferAllocate, this.f4550b.f4552a[4], objArr == true ? 1 : 0);
        }
        if (this.f4550b.n > 0) {
            C0139c c0139c = this.d[this.f4550b.n];
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate((int) c0139c.f);
            this.f4549a.getChannel().position(c0139c.e);
            b(this.f4549a.getChannel(), byteBufferAllocate2, "failed to read section: " + c0139c.k);
            for (C0139c c0139c2 : this.d) {
                byteBufferAllocate2.position(c0139c2.f4558a);
                c0139c2.k = a(byteBufferAllocate2);
                this.e.put(c0139c2.k, c0139c2);
            }
        }
    }

    private static String a(ByteBuffer byteBuffer) {
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position();
        while (byteBuffer.hasRemaining() && bArrArray[byteBuffer.position()] != 0) {
            byteBuffer.position(byteBuffer.position() + 1);
        }
        byteBuffer.position(byteBuffer.position() + 1);
        return new String(bArrArray, iPosition, (byteBuffer.position() - iPosition) - 1, Charset.forName(UrlBuilder.ASCII));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(FileChannel fileChannel, ByteBuffer byteBuffer, String str) throws IOException {
        byteBuffer.rewind();
        int i = fileChannel.read(byteBuffer);
        if (i != byteBuffer.limit()) {
            throw new IOException(str + " Rest bytes insufficient, expect to read " + byteBuffer.limit() + " bytes but only " + i + " bytes were read.");
        }
        byteBuffer.flip();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f4549a.close();
        this.e.clear();
        this.f4551c = null;
        this.d = null;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        public final byte[] f4552a;

        /* renamed from: b, reason: collision with root package name */
        public final short f4553b;

        /* renamed from: c, reason: collision with root package name */
        public final short f4554c;
        public final int d;
        public final long e;
        public final long f;
        public final long g;
        public final int h;
        public final short i;
        public final short j;
        public final short k;
        public final short l;
        public final short m;
        public final short n;

        /* synthetic */ a(FileChannel fileChannel, byte b2) {
            this(fileChannel);
        }

        private a(FileChannel fileChannel) throws IOException {
            this.f4552a = new byte[16];
            fileChannel.position(0L);
            fileChannel.read(ByteBuffer.wrap(this.f4552a));
            if (this.f4552a[0] != 127 || this.f4552a[1] != 69 || this.f4552a[2] != 76 || this.f4552a[3] != 70) {
                throw new IOException(String.format("bad elf magic: %x %x %x %x.", Byte.valueOf(this.f4552a[0]), Byte.valueOf(this.f4552a[1]), Byte.valueOf(this.f4552a[2]), Byte.valueOf(this.f4552a[3])));
            }
            c.a(this.f4552a[4], 2, "bad elf class: " + ((int) this.f4552a[4]));
            c.a(this.f4552a[5], 2, "bad elf data encoding: " + ((int) this.f4552a[5]));
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.f4552a[4] == 1 ? 36 : 48);
            byteBufferAllocate.order(this.f4552a[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
            c.b(fileChannel, byteBufferAllocate, "failed to read rest part of ehdr.");
            this.f4553b = byteBufferAllocate.getShort();
            this.f4554c = byteBufferAllocate.getShort();
            this.d = byteBufferAllocate.getInt();
            c.a(this.d, 1, "bad elf version: " + this.d);
            switch (this.f4552a[4]) {
                case 1:
                    this.e = byteBufferAllocate.getInt();
                    this.f = byteBufferAllocate.getInt();
                    this.g = byteBufferAllocate.getInt();
                    break;
                case 2:
                    this.e = byteBufferAllocate.getLong();
                    this.f = byteBufferAllocate.getLong();
                    this.g = byteBufferAllocate.getLong();
                    break;
                default:
                    throw new IOException("Unexpected elf class: " + ((int) this.f4552a[4]));
            }
            this.h = byteBufferAllocate.getInt();
            this.i = byteBufferAllocate.getShort();
            this.j = byteBufferAllocate.getShort();
            this.k = byteBufferAllocate.getShort();
            this.l = byteBufferAllocate.getShort();
            this.m = byteBufferAllocate.getShort();
            this.n = byteBufferAllocate.getShort();
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        public final int f4555a;

        /* renamed from: b, reason: collision with root package name */
        public final int f4556b;

        /* renamed from: c, reason: collision with root package name */
        public final long f4557c;
        public final long d;
        public final long e;
        public final long f;
        public final long g;
        public final long h;

        /* synthetic */ b(ByteBuffer byteBuffer, int i, byte b2) {
            this(byteBuffer, i);
        }

        private b(ByteBuffer byteBuffer, int i) throws IOException {
            switch (i) {
                case 1:
                    this.f4555a = byteBuffer.getInt();
                    this.f4557c = byteBuffer.getInt();
                    this.d = byteBuffer.getInt();
                    this.e = byteBuffer.getInt();
                    this.f = byteBuffer.getInt();
                    this.g = byteBuffer.getInt();
                    this.f4556b = byteBuffer.getInt();
                    this.h = byteBuffer.getInt();
                    return;
                case 2:
                    this.f4555a = byteBuffer.getInt();
                    this.f4556b = byteBuffer.getInt();
                    this.f4557c = byteBuffer.getLong();
                    this.d = byteBuffer.getLong();
                    this.e = byteBuffer.getLong();
                    this.f = byteBuffer.getLong();
                    this.g = byteBuffer.getLong();
                    this.h = byteBuffer.getLong();
                    return;
                default:
                    throw new IOException("Unexpected elf class: ".concat(String.valueOf(i)));
            }
        }
    }

    /* renamed from: com.bytedance.pangle.d.c$c, reason: collision with other inner class name */
    static class C0139c {

        /* renamed from: a, reason: collision with root package name */
        public final int f4558a;

        /* renamed from: b, reason: collision with root package name */
        public final int f4559b;

        /* renamed from: c, reason: collision with root package name */
        public final long f4560c;
        public final long d;
        public final long e;
        public final long f;
        public final int g;
        public final int h;
        public final long i;
        public final long j;
        public String k;

        /* synthetic */ C0139c(ByteBuffer byteBuffer, int i, byte b2) {
            this(byteBuffer, i);
        }

        private C0139c(ByteBuffer byteBuffer, int i) throws IOException {
            switch (i) {
                case 1:
                    this.f4558a = byteBuffer.getInt();
                    this.f4559b = byteBuffer.getInt();
                    this.f4560c = byteBuffer.getInt();
                    this.d = byteBuffer.getInt();
                    this.e = byteBuffer.getInt();
                    this.f = byteBuffer.getInt();
                    this.g = byteBuffer.getInt();
                    this.h = byteBuffer.getInt();
                    this.i = byteBuffer.getInt();
                    this.j = byteBuffer.getInt();
                    break;
                case 2:
                    this.f4558a = byteBuffer.getInt();
                    this.f4559b = byteBuffer.getInt();
                    this.f4560c = byteBuffer.getLong();
                    this.d = byteBuffer.getLong();
                    this.e = byteBuffer.getLong();
                    this.f = byteBuffer.getLong();
                    this.g = byteBuffer.getInt();
                    this.h = byteBuffer.getInt();
                    this.i = byteBuffer.getLong();
                    this.j = byteBuffer.getLong();
                    break;
                default:
                    throw new IOException("Unexpected elf class: ".concat(String.valueOf(i)));
            }
            this.k = null;
        }
    }

    static /* synthetic */ void a(int i, int i2, String str) throws IOException {
        if (i <= 0 || i > i2) {
            throw new IOException(str);
        }
    }
}
