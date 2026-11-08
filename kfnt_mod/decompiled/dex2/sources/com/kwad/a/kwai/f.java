package com.kwad.a.kwai;

import com.kwad.a.kwai.c;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f implements c, Closeable {
    private final int a = 1179403647;
    private final FileChannel b;

    public f(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.b = new FileInputStream(file).getChannel();
    }

    private long a(c.b bVar, long j, long j2) {
        for (long j3 = 0; j3 < j; j3++) {
            c.AbstractC0022c abstractC0022cA = bVar.a(j3);
            if (abstractC0022cA.a == 1 && abstractC0022cA.c <= j2 && j2 <= abstractC0022cA.c + abstractC0022cA.d) {
                return (j2 - abstractC0022cA.c) + abstractC0022cA.b;
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    public c.b a() throws IOException {
        this.b.position(0L);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        if (c(byteBufferAllocate, 0L) != 1179403647) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        short sE = e(byteBufferAllocate, 4L);
        boolean z = e(byteBufferAllocate, 5L) == 2;
        if (sE == 1) {
            return new d(z, this);
        }
        if (sE == 2) {
            return new e(z, this);
        }
        throw new IllegalStateException("Invalid class type!");
    }

    protected String a(ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short sE = e(byteBuffer, j);
            if (sE == 0) {
                return sb.toString();
            }
            sb.append((char) sE);
            j = j2;
        }
    }

    protected void a(ByteBuffer byteBuffer, long j, int i) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i);
        long j2 = 0;
        while (j2 < i) {
            int i2 = this.b.read(byteBuffer, j + j2);
            if (i2 == -1) {
                throw new EOFException();
            }
            j2 += i2;
        }
        byteBuffer.position(0);
    }

    protected long b(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 8);
        return byteBuffer.getLong();
    }

    public List<String> b() throws IOException {
        long j;
        c.a aVarA;
        this.b.position(0L);
        ArrayList arrayList = new ArrayList();
        c.b bVarA = a();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(bVarA.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVarA.f;
        int i = 0;
        if (j2 == 65535) {
            j2 = bVarA.a(0).a;
        }
        long j3 = 0;
        while (true) {
            if (j3 >= j2) {
                j = 0;
                break;
            }
            c.AbstractC0022c abstractC0022cA = bVarA.a(j3);
            if (abstractC0022cA.a == 2) {
                j = abstractC0022cA.b;
                break;
            }
            j3++;
        }
        if (j == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        long j4 = 0;
        do {
            aVarA = bVarA.a(j, i);
            if (aVarA.a == 1) {
                arrayList2.add(Long.valueOf(aVarA.b));
            } else if (aVarA.a == 5) {
                j4 = aVarA.b;
            }
            i++;
        } while (aVarA.a != 0);
        if (j4 == 0) {
            throw new IllegalStateException("String table offset not found!");
        }
        long jA = a(bVarA, j2, j4);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(a(byteBufferAllocate, ((Long) it.next()).longValue() + jA));
        }
        return arrayList;
    }

    protected long c(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 4);
        return byteBuffer.getInt() & 4294967295L;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.b.close();
    }

    protected int d(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 2);
        return byteBuffer.getShort() & 65535;
    }

    protected short e(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 1);
        return (short) (byteBuffer.get() & FileDownloadStatus.error);
    }
}
