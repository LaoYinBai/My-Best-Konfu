package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1;

import androidx.appcompat.widget.ActivityChooserView;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.d;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class j implements Closeable {
    private static final Logger g = Logger.getLogger(e.class.getName());

    /* renamed from: a, reason: collision with root package name */
    private final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d f1642a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f1643b;
    private boolean e;

    /* renamed from: c, reason: collision with root package name */
    private final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c f1644c = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c();
    final d.b f = new d.b(this.f1644c);
    private int d = 16384;

    j(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d dVar, boolean z) {
        this.f1642a = dVar;
        this.f1643b = z;
    }

    public synchronized void a(n nVar) throws IOException {
        if (this.e) {
            throw new IOException("closed");
        }
        this.d = nVar.c(this.d);
        if (nVar.b() != -1) {
            this.f.a(nVar.b());
        }
        a(0, 0, (byte) 4, (byte) 1);
        this.f1642a.flush();
    }

    public synchronized void b(n nVar) throws IOException {
        if (this.e) {
            throw new IOException("closed");
        }
        int i = 0;
        a(0, nVar.d() * 6, (byte) 4, (byte) 0);
        while (i < 10) {
            if (nVar.d(i)) {
                this.f1642a.writeShort(i == 4 ? 3 : i == 7 ? 4 : i);
                this.f1642a.writeInt(nVar.a(i));
            }
            i++;
        }
        this.f1642a.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.e = true;
        this.f1642a.close();
    }

    public synchronized void flush() throws IOException {
        if (this.e) {
            throw new IOException("closed");
        }
        this.f1642a.flush();
    }

    public synchronized void i() throws IOException {
        if (this.e) {
            throw new IOException("closed");
        }
        if (this.f1643b) {
            if (g.isLoggable(Level.FINE)) {
                g.fine(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(">> CONNECTION %s", e.f1591a.b()));
            }
            this.f1642a.write(e.f1591a.g());
            this.f1642a.flush();
        }
    }

    public int j() {
        return this.d;
    }

    public synchronized void a(int i, int i2, List<c> list) throws IOException {
        if (!this.e) {
            this.f.a(list);
            long jO = this.f1644c.o();
            int iMin = (int) Math.min(this.d - 4, jO);
            long j = iMin;
            a(i, iMin + 4, (byte) 5, jO == j ? (byte) 4 : (byte) 0);
            this.f1642a.writeInt(i2 & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            this.f1642a.b(this.f1644c, j);
            if (jO > j) {
                b(i, jO - j);
            }
        } else {
            throw new IOException("closed");
        }
    }

    private void b(int i, long j) throws IOException {
        while (j > 0) {
            int iMin = (int) Math.min(this.d, j);
            long j2 = iMin;
            j -= j2;
            a(i, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.f1642a.b(this.f1644c, j2);
        }
    }

    public synchronized void a(boolean z, int i, int i2, List<c> list) throws IOException {
        if (!this.e) {
            a(z, i, list);
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(int i, b bVar) throws IOException {
        if (!this.e) {
            if (bVar.f1579a != -1) {
                a(i, 4, (byte) 3, (byte) 0);
                this.f1642a.writeInt(bVar.f1579a);
                this.f1642a.flush();
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(boolean z, int i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, int i2) throws IOException {
        if (!this.e) {
            a(i, z ? (byte) 1 : (byte) 0, cVar, i2);
        } else {
            throw new IOException("closed");
        }
    }

    void a(int i, byte b2, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, int i2) throws IOException {
        a(i, i2, (byte) 0, b2);
        if (i2 > 0) {
            this.f1642a.b(cVar, i2);
        }
    }

    public synchronized void a(boolean z, int i, int i2) throws IOException {
        if (!this.e) {
            a(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
            this.f1642a.writeInt(i);
            this.f1642a.writeInt(i2);
            this.f1642a.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(int i, b bVar, byte[] bArr) throws IOException {
        if (!this.e) {
            if (bVar.f1579a != -1) {
                a(0, bArr.length + 8, (byte) 7, (byte) 0);
                this.f1642a.writeInt(i);
                this.f1642a.writeInt(bVar.f1579a);
                if (bArr.length > 0) {
                    this.f1642a.write(bArr);
                }
                this.f1642a.flush();
            } else {
                e.a("errorCode.httpCode == -1", new Object[0]);
                throw null;
            }
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(int i, long j) throws IOException {
        if (this.e) {
            throw new IOException("closed");
        }
        if (j != 0 && j <= 2147483647L) {
            a(i, 4, (byte) 8, (byte) 0);
            this.f1642a.writeInt((int) j);
            this.f1642a.flush();
        } else {
            e.a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            throw null;
        }
    }

    public void a(int i, int i2, byte b2, byte b3) throws IOException {
        if (g.isLoggable(Level.FINE)) {
            g.fine(e.a(false, i, i2, b2, b3));
        }
        int i3 = this.d;
        if (i2 > i3) {
            e.a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2));
            throw null;
        }
        if ((Integer.MIN_VALUE & i) == 0) {
            a(this.f1642a, i2);
            this.f1642a.writeByte(b2 & 255);
            this.f1642a.writeByte(b3 & 255);
            this.f1642a.writeInt(i & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            return;
        }
        e.a("reserved bit set: %s", Integer.valueOf(i));
        throw null;
    }

    private static void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d dVar, int i) throws IOException {
        dVar.writeByte((i >>> 16) & 255);
        dVar.writeByte((i >>> 8) & 255);
        dVar.writeByte(i & 255);
    }

    void a(boolean z, int i, List<c> list) throws IOException {
        if (!this.e) {
            this.f.a(list);
            long jO = this.f1644c.o();
            int iMin = (int) Math.min(this.d, jO);
            long j = iMin;
            byte b2 = jO == j ? (byte) 4 : (byte) 0;
            if (z) {
                b2 = (byte) (b2 | 1);
            }
            a(i, iMin, (byte) 1, b2);
            this.f1642a.b(this.f1644c, j);
            if (jO > j) {
                b(i, jO - j);
                return;
            }
            return;
        }
        throw new IOException("closed");
    }
}
