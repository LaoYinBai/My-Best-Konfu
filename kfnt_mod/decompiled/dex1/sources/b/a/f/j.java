package b.a.f;

import androidx.appcompat.widget.ActivityChooserView;
import b.a.f.d;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class j implements Closeable {

    /* renamed from: b, reason: collision with root package name */
    private static final Logger f913b = Logger.getLogger(e.class.getName());

    /* renamed from: c, reason: collision with root package name */
    private final c.d f915c;
    private final boolean d;
    private boolean g;
    private final c.c e = new c.c();

    /* renamed from: a, reason: collision with root package name */
    final d.b f914a = new d.b(this.e);
    private int f = 16384;

    j(c.d dVar, boolean z) {
        this.f915c = dVar;
        this.d = z;
    }

    public synchronized void a() throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        if (this.d) {
            if (f913b.isLoggable(Level.FINE)) {
                f913b.fine(b.a.c.a(">> CONNECTION %s", e.f862a.h()));
            }
            this.f915c.d(e.f862a.l());
            this.f915c.flush();
        }
    }

    public synchronized void a(m mVar) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.f = mVar.d(this.f);
        if (mVar.c() != -1) {
            this.f914a.a(mVar.c());
        }
        a(0, 0, (byte) 4, (byte) 1);
        this.f915c.flush();
    }

    public synchronized void a(int i, int i2, List<c> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.f914a.a(list);
        long jB = this.e.b();
        int iMin = (int) Math.min(this.f - 4, jB);
        long j = iMin;
        a(i, iMin + 4, (byte) 5, jB == j ? (byte) 4 : (byte) 0);
        this.f915c.j(i2 & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        this.f915c.a_(this.e, j);
        if (jB > j) {
            b(i, jB - j);
        }
    }

    public synchronized void b() throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.f915c.flush();
    }

    public synchronized void a(boolean z, int i, int i2, List<c> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        b(z, i, list);
    }

    public synchronized void a(boolean z, int i, List<c> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        b(z, i, list);
    }

    public synchronized void a(int i, List<c> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        b(false, i, list);
    }

    public synchronized void a(int i, b bVar) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        if (bVar.l == -1) {
            throw new IllegalArgumentException();
        }
        a(i, 4, (byte) 3, (byte) 0);
        this.f915c.j(bVar.l);
        this.f915c.flush();
    }

    public int c() {
        return this.f;
    }

    public synchronized void a(boolean z, int i, c.c cVar, int i2) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        a(i, z ? (byte) 1 : (byte) 0, cVar, i2);
    }

    void a(int i, byte b2, c.c cVar, int i2) throws IOException {
        a(i, i2, (byte) 0, b2);
        if (i2 > 0) {
            this.f915c.a_(cVar, i2);
        }
    }

    public synchronized void b(m mVar) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        int i = 0;
        a(0, mVar.b() * 6, (byte) 4, (byte) 0);
        while (i < 10) {
            if (mVar.a(i)) {
                this.f915c.l(i == 4 ? 3 : i == 7 ? 4 : i);
                this.f915c.j(mVar.b(i));
            }
            i++;
        }
        this.f915c.flush();
    }

    public synchronized void a(boolean z, int i, int i2) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        a(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
        this.f915c.j(i);
        this.f915c.j(i2);
        this.f915c.flush();
    }

    public synchronized void a(int i, b bVar, byte[] bArr) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        if (bVar.l == -1) {
            throw e.a("errorCode.httpCode == -1", new Object[0]);
        }
        a(0, bArr.length + 8, (byte) 7, (byte) 0);
        this.f915c.j(i);
        this.f915c.j(bVar.l);
        if (bArr.length > 0) {
            this.f915c.d(bArr);
        }
        this.f915c.flush();
    }

    public synchronized void a(int i, long j) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        if (j == 0 || j > 2147483647L) {
            throw e.a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
        }
        a(i, 4, (byte) 8, (byte) 0);
        this.f915c.j((int) j);
        this.f915c.flush();
    }

    public void a(int i, int i2, byte b2, byte b3) throws IOException {
        if (f913b.isLoggable(Level.FINE)) {
            f913b.fine(e.a(false, i, i2, b2, b3));
        }
        if (i2 > this.f) {
            throw e.a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(this.f), Integer.valueOf(i2));
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            throw e.a("reserved bit set: %s", Integer.valueOf(i));
        }
        a(this.f915c, i2);
        this.f915c.m(b2 & 255);
        this.f915c.m(b3 & 255);
        this.f915c.j(i & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.g = true;
        this.f915c.close();
    }

    private static void a(c.d dVar, int i) throws IOException {
        dVar.m((i >>> 16) & 255);
        dVar.m((i >>> 8) & 255);
        dVar.m(i & 255);
    }

    private void b(int i, long j) throws IOException {
        while (j > 0) {
            int iMin = (int) Math.min(this.f, j);
            long j2 = iMin;
            j -= j2;
            a(i, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.f915c.a_(this.e, j2);
        }
    }

    void b(boolean z, int i, List<c> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.f914a.a(list);
        long jB = this.e.b();
        int iMin = (int) Math.min(this.f, jB);
        long j = iMin;
        byte b2 = jB == j ? (byte) 4 : (byte) 0;
        if (z) {
            b2 = (byte) (b2 | 1);
        }
        a(i, iMin, (byte) 1, b2);
        this.f915c.a_(this.e, j);
        if (jB > j) {
            b(i, jB - j);
        }
    }
}
