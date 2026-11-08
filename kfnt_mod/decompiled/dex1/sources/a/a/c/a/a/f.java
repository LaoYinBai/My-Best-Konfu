package a.a.c.a.a;

import a.a.c.a.a.c;
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

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f implements c, Closeable {

    /* renamed from: a, reason: collision with root package name */
    public final FileChannel f349a;

    public f(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.f349a = new FileInputStream(file).getChannel();
    }

    public List<String> a() throws IOException {
        c.b eVar;
        long j;
        long j2;
        c.a aVarA;
        this.f349a.position(0L);
        ArrayList arrayList = new ArrayList();
        this.f349a.position(0L);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        a(byteBufferAllocate, 0L, 4);
        if ((byteBufferAllocate.getInt() & 4294967295L) != 1179403647) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        a(byteBufferAllocate, 4L, 1);
        short s = (short) (byteBufferAllocate.get() & 255);
        a(byteBufferAllocate, 5L, 1);
        boolean z = ((short) (byteBufferAllocate.get() & 255)) == 2;
        if (s == 1) {
            eVar = new d(z, this);
        } else {
            if (s != 2) {
                throw new IllegalStateException("Invalid class type!");
            }
            eVar = new e(z, this);
        }
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(8);
        byteBufferAllocate2.order(eVar.f342a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = eVar.e;
        if (j3 == 65535) {
            j3 = eVar.a(0).f348a;
        }
        long j4 = 0;
        while (true) {
            if (j4 >= j3) {
                j = j3;
                j2 = 0;
                break;
            }
            c.AbstractC0001c abstractC0001cA = eVar.a(j4);
            j = j3;
            if (abstractC0001cA.f345a == 2) {
                j2 = abstractC0001cA.f346b;
                break;
            }
            j4++;
            j3 = j;
        }
        if (j2 == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        long j5 = 0;
        int i = 0;
        do {
            aVarA = eVar.a(j2, i);
            long j6 = aVarA.f340a;
            if (j6 == 1) {
                arrayList2.add(Long.valueOf(aVarA.f341b));
            } else if (j6 == 5) {
                j5 = aVarA.f341b;
            }
            i++;
        } while (aVarA.f340a != 0);
        if (j5 == 0) {
            throw new IllegalStateException("String table offset not found!");
        }
        for (long j7 = 0; j7 < j; j7++) {
            c.AbstractC0001c abstractC0001cA2 = eVar.a(j7);
            if (abstractC0001cA2.f345a == 1) {
                long j8 = abstractC0001cA2.f347c;
                if (j8 <= j5 && j5 <= abstractC0001cA2.d + j8) {
                    long j9 = (j5 - j8) + abstractC0001cA2.f346b;
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        long jLongValue = ((Long) it.next()).longValue() + j9;
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            long j10 = jLongValue + 1;
                            a(byteBufferAllocate2, jLongValue, 1);
                            short s2 = (short) (byteBufferAllocate2.get() & 255);
                            if (s2 != 0) {
                                sb.append((char) s2);
                                jLongValue = j10;
                            }
                        }
                        arrayList.add(sb.toString());
                    }
                    return arrayList;
                }
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    public void a(ByteBuffer byteBuffer, long j, int i) {
        byteBuffer.position(0);
        byteBuffer.limit(i);
        long j2 = 0;
        while (j2 < i) {
            int i2 = this.f349a.read(byteBuffer, j + j2);
            if (i2 == -1) {
                throw new EOFException();
            }
            j2 += i2;
        }
        byteBuffer.position(0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f349a.close();
    }
}
