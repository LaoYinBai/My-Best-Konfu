package b.a.b;

import c.c;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class a {

    /* renamed from: a, reason: collision with root package name */
    private final FileChannel f781a;

    a(FileChannel fileChannel) {
        this.f781a = fileChannel;
    }

    public void a(long j, c cVar, long j2) throws IOException {
        if (j2 < 0 || j2 > cVar.b()) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long jTransferFrom = this.f781a.transferFrom(cVar, j, j2);
            j += jTransferFrom;
            j2 -= jTransferFrom;
        }
    }

    public void b(long j, c cVar, long j2) throws IOException {
        if (j2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long jTransferTo = this.f781a.transferTo(j, j2, cVar);
            j += jTransferTo;
            j2 -= jTransferTo;
        }
    }
}
