package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1;

import androidx.appcompat.widget.ActivityChooserView;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.t;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.d;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class h implements Closeable {
    static final Logger e = Logger.getLogger(e.class.getName());

    /* renamed from: a, reason: collision with root package name */
    private final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e f1627a;

    /* renamed from: b, reason: collision with root package name */
    private final a f1628b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f1629c;
    final d.a d;

    static final class a implements s {

        /* renamed from: a, reason: collision with root package name */
        private final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e f1630a;

        /* renamed from: b, reason: collision with root package name */
        int f1631b;

        /* renamed from: c, reason: collision with root package name */
        byte f1632c;
        int d;
        int e;
        short f;

        a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e eVar) {
            this.f1630a = eVar;
        }

        private void h() throws IOException {
            int i = this.d;
            int iA = h.a(this.f1630a);
            this.e = iA;
            this.f1631b = iA;
            byte b2 = (byte) (this.f1630a.readByte() & 255);
            this.f1632c = (byte) (this.f1630a.readByte() & 255);
            if (h.e.isLoggable(Level.FINE)) {
                h.e.fine(e.a(true, this.d, this.f1631b, b2, this.f1632c));
            }
            this.d = this.f1630a.readInt() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            if (b2 != 9) {
                e.b("%s != TYPE_CONTINUATION", Byte.valueOf(b2));
                throw null;
            }
            if (this.d == i) {
                return;
            }
            e.b("TYPE_CONTINUATION streamId changed", new Object[0]);
            throw null;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public long a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            while (true) {
                int i = this.e;
                if (i != 0) {
                    long jA = this.f1630a.a(cVar, Math.min(j, i));
                    if (jA == -1) {
                        return -1L;
                    }
                    this.e = (int) (this.e - jA);
                    return jA;
                }
                this.f1630a.skip(this.f);
                this.f = (short) 0;
                if ((this.f1632c & 4) != 0) {
                    return -1L;
                }
                h();
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public t b() {
            return this.f1630a.b();
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }
    }

    interface b {
        void a();

        void a(int i, int i2, int i3, boolean z);

        void a(int i, int i2, List<c> list) throws IOException;

        void a(int i, long j);

        void a(int i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar);

        void a(int i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f fVar);

        void a(boolean z, int i, int i2);

        void a(boolean z, int i, int i2, List<c> list);

        void a(boolean z, int i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e eVar, int i2) throws IOException;

        void a(boolean z, n nVar);
    }

    h(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e eVar, boolean z) {
        this.f1627a = eVar;
        this.f1629c = z;
        this.f1628b = new a(this.f1627a);
        this.d = new d.a(4096, this.f1628b);
    }

    private void b(b bVar, int i, byte b2, int i2) throws IOException {
        if (i < 8) {
            e.b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            e.b("TYPE_GOAWAY streamId != 0", new Object[0]);
            throw null;
        }
        int i3 = this.f1627a.readInt();
        int i4 = this.f1627a.readInt();
        int i5 = i - 8;
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVarA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.a(i4);
        if (bVarA == null) {
            e.b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(i4));
            throw null;
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f fVarB = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f.e;
        if (i5 > 0) {
            fVarB = this.f1627a.b(i5);
        }
        bVar.a(i3, bVarA, fVarB);
    }

    private void c(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            e.b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            throw null;
        }
        boolean z = (b2 & 1) != 0;
        short s = (b2 & 8) != 0 ? (short) (this.f1627a.readByte() & 255) : (short) 0;
        if ((b2 & 32) != 0) {
            a(bVar, i2);
            i -= 5;
        }
        bVar.a(z, i2, -1, a(a(i, b2, s), s, b2, i2));
    }

    private void d(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 8) {
            e.b("TYPE_PING length != 8: %s", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            e.b("TYPE_PING streamId != 0", new Object[0]);
            throw null;
        }
        bVar.a((b2 & 1) != 0, this.f1627a.readInt(), this.f1627a.readInt());
    }

    private void e(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 5) {
            e.b("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            a(bVar, i2);
        } else {
            e.b("TYPE_PRIORITY streamId == 0", new Object[0]);
            throw null;
        }
    }

    private void f(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            e.b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            throw null;
        }
        short s = (b2 & 8) != 0 ? (short) (this.f1627a.readByte() & 255) : (short) 0;
        bVar.a(i2, this.f1627a.readInt() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, a(a(i - 4, b2, s), s, b2, i2));
    }

    private void g(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            e.b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
            throw null;
        }
        if (i2 == 0) {
            e.b("TYPE_RST_STREAM streamId == 0", new Object[0]);
            throw null;
        }
        int i3 = this.f1627a.readInt();
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVarA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.a(i3);
        if (bVarA != null) {
            bVar.a(i2, bVarA);
        } else {
            e.b("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(i3));
            throw null;
        }
    }

    private void h(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 != 0) {
            e.b("TYPE_SETTINGS streamId != 0", new Object[0]);
            throw null;
        }
        if ((b2 & 1) != 0) {
            if (i == 0) {
                bVar.a();
                return;
            } else {
                e.b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                throw null;
            }
        }
        if (i % 6 != 0) {
            e.b("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
            throw null;
        }
        n nVar = new n();
        for (int i3 = 0; i3 < i; i3 += 6) {
            short s = this.f1627a.readShort();
            int i4 = this.f1627a.readInt();
            switch (s) {
                case 2:
                    if (i4 != 0 && i4 != 1) {
                        e.b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                        throw null;
                    }
                    break;
                case 3:
                    s = 4;
                    break;
                case 4:
                    s = 7;
                    if (i4 < 0) {
                        e.b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                        throw null;
                    }
                    break;
                case 5:
                    if (i4 < 16384 || i4 > 16777215) {
                        e.b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(i4));
                        throw null;
                    }
                    break;
                    break;
            }
            nVar.a(s, i4);
        }
        bVar.a(false, nVar);
    }

    private void i(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            e.b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
            throw null;
        }
        long j = this.f1627a.readInt() & 2147483647L;
        if (j != 0) {
            bVar.a(i2, j);
        } else {
            e.b("windowSizeIncrement was 0", Long.valueOf(j));
            throw null;
        }
    }

    public void a(b bVar) throws IOException {
        if (this.f1629c) {
            if (a(true, bVar)) {
                return;
            }
            e.b("Required SETTINGS preface not received", new Object[0]);
            throw null;
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f fVarB = this.f1627a.b(e.f1591a.e());
        if (e.isLoggable(Level.FINE)) {
            e.fine(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a("<< CONNECTION %s", fVarB.b()));
        }
        if (e.f1591a.equals(fVarB)) {
            return;
        }
        e.b("Expected a connection header but was %s", fVarB.h());
        throw null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f1627a.close();
    }

    public boolean a(boolean z, b bVar) throws IOException {
        try {
            this.f1627a.a(9L);
            int iA = a(this.f1627a);
            if (iA >= 0 && iA <= 16384) {
                byte b2 = (byte) (this.f1627a.readByte() & 255);
                if (z && b2 != 4) {
                    e.b("Expected a SETTINGS frame but was %s", Byte.valueOf(b2));
                    throw null;
                }
                byte b3 = (byte) (this.f1627a.readByte() & 255);
                int i = this.f1627a.readInt() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                if (e.isLoggable(Level.FINE)) {
                    e.fine(e.a(true, i, iA, b2, b3));
                }
                switch (b2) {
                    case 0:
                        a(bVar, iA, b3, i);
                        return true;
                    case 1:
                        c(bVar, iA, b3, i);
                        return true;
                    case 2:
                        e(bVar, iA, b3, i);
                        return true;
                    case 3:
                        g(bVar, iA, b3, i);
                        return true;
                    case 4:
                        h(bVar, iA, b3, i);
                        return true;
                    case 5:
                        f(bVar, iA, b3, i);
                        return true;
                    case 6:
                        d(bVar, iA, b3, i);
                        return true;
                    case 7:
                        b(bVar, iA, b3, i);
                        return true;
                    case 8:
                        i(bVar, iA, b3, i);
                        return true;
                    default:
                        this.f1627a.skip(iA);
                        return true;
                }
            }
            e.b("FRAME_SIZE_ERROR: %s", Integer.valueOf(iA));
            throw null;
        } catch (IOException unused) {
            return false;
        }
    }

    private List<c> a(int i, short s, byte b2, int i2) throws IOException {
        a aVar = this.f1628b;
        aVar.e = i;
        aVar.f1631b = i;
        aVar.f = s;
        aVar.f1632c = b2;
        aVar.d = i2;
        this.d.c();
        return this.d.a();
    }

    private void a(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            e.b("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
            throw null;
        }
        boolean z = (b2 & 1) != 0;
        if (!((b2 & 32) != 0)) {
            short s = (b2 & 8) != 0 ? (short) (this.f1627a.readByte() & 255) : (short) 0;
            bVar.a(z, i2, this.f1627a, a(i, b2, s));
            this.f1627a.skip(s);
            return;
        }
        e.b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        throw null;
    }

    private void a(b bVar, int i) throws IOException {
        int i2 = this.f1627a.readInt();
        bVar.a(i, i2 & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, (this.f1627a.readByte() & 255) + 1, (Integer.MIN_VALUE & i2) != 0);
    }

    static int a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e eVar) throws IOException {
        return (eVar.readByte() & 255) | ((eVar.readByte() & 255) << 16) | ((eVar.readByte() & 255) << 8);
    }

    static int a(int i, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        e.b("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw null;
    }
}
