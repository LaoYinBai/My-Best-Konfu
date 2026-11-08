package cp.bd.c;

import cp.bd.c.t;
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
import kotlin.UByte;
import kotlin.UShort;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class w implements t, Closeable {
    private final int a = 1179403647;
    private final FileChannel b;

    public w(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.b = new FileInputStream(file).getChannel();
    }

    private static long a(t.b bVar, long j, long j2) {
        for (long j3 = 0; j3 < j; j3++) {
            t.c cVarA = bVar.a(j3);
            if (cVarA.a == 1 && cVarA.c <= j2 && j2 <= cVarA.c + cVarA.d) {
                return (j2 - cVarA.c) + cVarA.b;
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    private void a(ByteBuffer byteBuffer, long j, int i) throws IOException {
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

    private String d(ByteBuffer byteBuffer, long j) throws IOException {
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

    private short e(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 1);
        return (short) (byteBuffer.get() & UByte.MAX_VALUE);
    }

    protected final long a(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 8);
        return byteBuffer.getLong();
    }

    public final List<String> a() throws IOException {
        t.b vVar;
        long j;
        long j2;
        t.a aVarA;
        long j3;
        this.b.position(0L);
        ArrayList arrayList = new ArrayList();
        this.b.position(0L);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        if (b(byteBufferAllocate, 0L) != 1179403647) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        short sE = e(byteBufferAllocate, 4L);
        boolean z = e(byteBufferAllocate, 5L) == 2;
        if (sE == 1) {
            vVar = new u(z, this);
        } else {
            if (sE != 2) {
                throw new IllegalStateException("Invalid class type!");
            }
            vVar = new v(z, this);
        }
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(8);
        byteBufferAllocate2.order(vVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j4 = vVar.f;
        if (j4 == 65535) {
            j4 = vVar.a().a;
        }
        long j5 = 0;
        while (true) {
            if (j5 >= j4) {
                j = j4;
                j2 = 0;
                break;
            }
            t.c cVarA = vVar.a(j5);
            j = j4;
            if (cVarA.a == 2) {
                j2 = cVarA.b;
                break;
            }
            j5++;
            j4 = j;
        }
        if (j2 == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        long j6 = 0;
        int i = 0;
        do {
            aVarA = vVar.a(j2, i);
            if (aVarA.a == 1) {
                arrayList2.add(Long.valueOf(aVarA.b));
                j3 = 5;
            } else {
                j3 = 5;
                if (aVarA.a == 5) {
                    j6 = aVarA.b;
                }
            }
            i++;
        } while (aVarA.a != 0);
        if (j6 == 0) {
            throw new IllegalStateException("String table offset not found!");
        }
        long jA = a(vVar, j, j6);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(d(byteBufferAllocate2, ((Long) it.next()).longValue() + jA));
        }
        return arrayList;
    }

    protected final long b(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 4);
        return byteBuffer.getInt() & 4294967295L;
    }

    protected final int c(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 2);
        return byteBuffer.getShort() & UShort.MAX_VALUE;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.b.close();
    }
}
