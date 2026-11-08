package b.a.f;

import androidx.appcompat.widget.ActivityChooserView;
import b.a.f.d;
import c.y;
import c.z;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class h implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    static final Logger f897a = Logger.getLogger(e.class.getName());

    /* renamed from: b, reason: collision with root package name */
    final d.a f898b;

    /* renamed from: c, reason: collision with root package name */
    private final c.e f899c;
    private final a d;
    private final boolean e;

    interface b {
        void a();

        void a(int i, int i2, int i3, boolean z);

        void a(int i, int i2, List<c> list) throws IOException;

        void a(int i, long j);

        void a(int i, b.a.f.b bVar);

        void a(int i, b.a.f.b bVar, c.f fVar);

        void a(int i, String str, c.f fVar, String str2, int i2, long j);

        void a(boolean z, int i, int i2);

        void a(boolean z, int i, int i2, List<c> list);

        void a(boolean z, int i, c.e eVar, int i2) throws IOException;

        void a(boolean z, m mVar);
    }

    h(c.e eVar, boolean z) {
        this.f899c = eVar;
        this.e = z;
        this.d = new a(this.f899c);
        this.f898b = new d.a(4096, this.d);
    }

    public void a(b bVar) throws IOException {
        if (this.e) {
            if (!a(true, bVar)) {
                throw e.b("Required SETTINGS preface not received", new Object[0]);
            }
            return;
        }
        c.f fVarD = this.f899c.d(e.f862a.k());
        if (f897a.isLoggable(Level.FINE)) {
            f897a.fine(b.a.c.a("<< CONNECTION %s", fVarD.h()));
        }
        if (!e.f862a.equals(fVarD)) {
            throw e.b("Expected a connection header but was %s", fVarD.a());
        }
    }

    public boolean a(boolean z, b bVar) throws IOException {
        try {
            this.f899c.a(9L);
            int iA = a(this.f899c);
            if (iA < 0 || iA > 16384) {
                throw e.b("FRAME_SIZE_ERROR: %s", Integer.valueOf(iA));
            }
            byte bJ = (byte) (this.f899c.j() & 255);
            if (z && bJ != 4) {
                throw e.b("Expected a SETTINGS frame but was %s", Byte.valueOf(bJ));
            }
            byte bJ2 = (byte) (this.f899c.j() & 255);
            int iL = this.f899c.l() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            if (f897a.isLoggable(Level.FINE)) {
                f897a.fine(e.a(true, iL, iA, bJ, bJ2));
            }
            switch (bJ) {
                case 0:
                    b(bVar, iA, bJ2, iL);
                    return true;
                case 1:
                    a(bVar, iA, bJ2, iL);
                    return true;
                case 2:
                    c(bVar, iA, bJ2, iL);
                    return true;
                case 3:
                    d(bVar, iA, bJ2, iL);
                    return true;
                case 4:
                    e(bVar, iA, bJ2, iL);
                    return true;
                case 5:
                    f(bVar, iA, bJ2, iL);
                    return true;
                case 6:
                    g(bVar, iA, bJ2, iL);
                    return true;
                case 7:
                    h(bVar, iA, bJ2, iL);
                    return true;
                case 8:
                    i(bVar, iA, bJ2, iL);
                    return true;
                default:
                    this.f899c.i(iA);
                    return true;
            }
        } catch (IOException unused) {
            return false;
        }
    }

    private void a(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            throw e.b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }
        boolean z = (b2 & 1) != 0;
        short sJ = (b2 & 8) != 0 ? (short) (this.f899c.j() & 255) : (short) 0;
        if ((b2 & 32) != 0) {
            a(bVar, i2);
            i -= 5;
        }
        bVar.a(z, i2, -1, a(a(i, b2, sJ), sJ, b2, i2));
    }

    private List<c> a(int i, short s, byte b2, int i2) throws IOException {
        a aVar = this.d;
        this.d.d = i;
        aVar.f900a = i;
        this.d.e = s;
        this.d.f901b = b2;
        this.d.f902c = i2;
        this.f898b.b();
        return this.f898b.c();
    }

    private void b(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            throw e.b("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
        }
        boolean z = (b2 & 1) != 0;
        if ((b2 & 32) != 0) {
            throw e.b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        short sJ = (b2 & 8) != 0 ? (short) (this.f899c.j() & 255) : (short) 0;
        bVar.a(z, i2, this.f899c, a(i, b2, sJ));
        this.f899c.i(sJ);
    }

    private void c(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 5) {
            throw e.b("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
        }
        if (i2 == 0) {
            throw e.b("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
        a(bVar, i2);
    }

    private void a(b bVar, int i) throws IOException {
        int iL = this.f899c.l();
        bVar.a(i, iL & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, (this.f899c.j() & 255) + 1, (Integer.MIN_VALUE & iL) != 0);
    }

    private void d(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            throw e.b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
        }
        if (i2 == 0) {
            throw e.b("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
        int iL = this.f899c.l();
        b.a.f.b bVarA = b.a.f.b.a(iL);
        if (bVarA == null) {
            throw e.b("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(iL));
        }
        bVar.a(i2, bVarA);
    }

    private void e(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 != 0) {
            throw e.b("TYPE_SETTINGS streamId != 0", new Object[0]);
        }
        if ((b2 & 1) != 0) {
            if (i != 0) {
                throw e.b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }
            bVar.a();
            return;
        }
        if (i % 6 != 0) {
            throw e.b("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
        }
        m mVar = new m();
        for (int i3 = 0; i3 < i; i3 += 6) {
            int iK = this.f899c.k() & 65535;
            int iL = this.f899c.l();
            switch (iK) {
                case 2:
                    if (iL != 0 && iL != 1) {
                        throw e.b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                    }
                    break;
                case 3:
                    iK = 4;
                    break;
                case 4:
                    iK = 7;
                    if (iL < 0) {
                        throw e.b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                    }
                    break;
                case 5:
                    if (iL < 16384 || iL > 16777215) {
                        throw e.b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(iL));
                    }
                    break;
                    break;
            }
            mVar.a(iK, iL);
        }
        bVar.a(false, mVar);
    }

    private void f(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            throw e.b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        short sJ = (b2 & 8) != 0 ? (short) (this.f899c.j() & 255) : (short) 0;
        bVar.a(i2, this.f899c.l() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, a(a(i - 4, b2, sJ), sJ, b2, i2));
    }

    private void g(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 8) {
            throw e.b("TYPE_PING length != 8: %s", Integer.valueOf(i));
        }
        if (i2 != 0) {
            throw e.b("TYPE_PING streamId != 0", new Object[0]);
        }
        bVar.a((b2 & 1) != 0, this.f899c.l(), this.f899c.l());
    }

    private void h(b bVar, int i, byte b2, int i2) throws IOException {
        if (i < 8) {
            throw e.b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
        }
        if (i2 != 0) {
            throw e.b("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
        int iL = this.f899c.l();
        int iL2 = this.f899c.l();
        int i3 = i - 8;
        b.a.f.b bVarA = b.a.f.b.a(iL2);
        if (bVarA == null) {
            throw e.b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(iL2));
        }
        c.f fVarD = c.f.f2139b;
        if (i3 > 0) {
            fVarD = this.f899c.d(i3);
        }
        bVar.a(iL, bVarA, fVarD);
    }

    private void i(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            throw e.b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
        }
        long jL = this.f899c.l() & 2147483647L;
        if (jL == 0) {
            throw e.b("windowSizeIncrement was 0", Long.valueOf(jL));
        }
        bVar.a(i2, jL);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f899c.close();
    }

    static final class a implements y {

        /* renamed from: a, reason: collision with root package name */
        int f900a;

        /* renamed from: b, reason: collision with root package name */
        byte f901b;

        /* renamed from: c, reason: collision with root package name */
        int f902c;
        int d;
        short e;
        private final c.e f;

        @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        a(c.e eVar) {
            this.f = eVar;
        }

        @Override // c.y
        public long a(c.c cVar, long j) throws IOException {
            while (this.d == 0) {
                this.f.i(this.e);
                this.e = (short) 0;
                if ((this.f901b & 4) != 0) {
                    return -1L;
                }
                b();
            }
            long jA = this.f.a(cVar, Math.min(j, this.d));
            if (jA == -1) {
                return -1L;
            }
            this.d = (int) (this.d - jA);
            return jA;
        }

        @Override // c.y
        public z a() {
            return this.f.a();
        }

        private void b() throws IOException {
            int i = this.f902c;
            int iA = h.a(this.f);
            this.d = iA;
            this.f900a = iA;
            byte bJ = (byte) (this.f.j() & 255);
            this.f901b = (byte) (this.f.j() & 255);
            if (h.f897a.isLoggable(Level.FINE)) {
                h.f897a.fine(e.a(true, this.f902c, this.f900a, bJ, this.f901b));
            }
            this.f902c = this.f.l() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            if (bJ != 9) {
                throw e.b("%s != TYPE_CONTINUATION", Byte.valueOf(bJ));
            }
            if (this.f902c != i) {
                throw e.b("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    static int a(c.e eVar) throws IOException {
        return (eVar.j() & 255) | ((eVar.j() & 255) << 16) | ((eVar.j() & 255) << 8);
    }

    static int a(int i, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        throw e.b("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
    }
}
