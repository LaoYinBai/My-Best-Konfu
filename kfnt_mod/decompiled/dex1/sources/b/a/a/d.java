package b.a.a;

import c.p;
import c.x;
import c.y;
import com.baidu.mobads.sdk.internal.ad;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d implements Closeable, Flushable {

    /* renamed from: a, reason: collision with root package name */
    static final String f760a = "journal";

    /* renamed from: b, reason: collision with root package name */
    static final String f761b = "journal.tmp";

    /* renamed from: c, reason: collision with root package name */
    static final String f762c = "journal.bkp";
    static final String d = "libcore.io.DiskLruCache";
    static final String e = "1";
    static final long f = -1;
    private static final String t = "CLEAN";
    private static final String u = "DIRTY";
    private static final String v = "REMOVE";
    private static final String w = "READ";
    private final int A;
    private long B;
    private final Executor E;
    final b.a.g.a h;
    final File i;
    final int j;
    c.d k;
    int m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    private final File x;
    private final File y;
    private final File z;
    static final /* synthetic */ boolean s = !d.class.desiredAssertionStatus();
    static final Pattern g = Pattern.compile("[a-z0-9_-]{1,120}");
    private long C = 0;
    final LinkedHashMap<String, b> l = new LinkedHashMap<>(0, 0.75f, true);
    private long D = 0;
    private final Runnable F = new Runnable() { // from class: b.a.a.d.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (d.this) {
                if ((!d.this.o) || d.this.p) {
                    return;
                }
                try {
                    d.this.h();
                } catch (IOException unused) {
                    d.this.q = true;
                }
                try {
                    if (d.this.f()) {
                        d.this.b();
                        d.this.m = 0;
                    }
                } catch (IOException unused2) {
                    d.this.r = true;
                    d.this.k = p.a(p.a());
                }
            }
        }
    };

    d(b.a.g.a aVar, File file, int i, int i2, long j, Executor executor) {
        this.h = aVar;
        this.i = file;
        this.A = i;
        this.x = new File(file, f760a);
        this.y = new File(file, f761b);
        this.z = new File(file, f762c);
        this.j = i2;
        this.B = j;
        this.E = executor;
    }

    public synchronized void a() throws IOException {
        if (!s && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (this.o) {
            return;
        }
        if (this.h.e(this.z)) {
            if (this.h.e(this.x)) {
                this.h.d(this.z);
            } else {
                this.h.a(this.z, this.x);
            }
        }
        if (this.h.e(this.x)) {
            try {
                l();
                n();
                this.o = true;
                return;
            } catch (IOException e2) {
                b.a.h.f.c().a(5, "DiskLruCache " + this.i + " is corrupt: " + e2.getMessage() + ", removing", e2);
                try {
                    i();
                    this.p = false;
                } catch (Throwable th) {
                    this.p = false;
                    throw th;
                }
            }
        }
        b();
        this.o = true;
    }

    public static d a(b.a.g.a aVar, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        return new d(aVar, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), b.a.c.a("OkHttp DiskLruCache", true)));
    }

    private void l() throws IOException {
        c.e eVarA = p.a(this.h.a(this.x));
        try {
            String strV = eVarA.v();
            String strV2 = eVarA.v();
            String strV3 = eVarA.v();
            String strV4 = eVarA.v();
            String strV5 = eVarA.v();
            if (!d.equals(strV) || !"1".equals(strV2) || !Integer.toString(this.A).equals(strV3) || !Integer.toString(this.j).equals(strV4) || !"".equals(strV5)) {
                throw new IOException("unexpected journal header: [" + strV + ", " + strV2 + ", " + strV4 + ", " + strV5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    d(eVarA.v());
                    i++;
                } catch (EOFException unused) {
                    this.m = i - this.l.size();
                    if (!eVarA.g()) {
                        b();
                    } else {
                        this.k = m();
                    }
                    b.a.c.a(eVarA);
                    return;
                }
            }
        } catch (Throwable th) {
            b.a.c.a(eVarA);
            throw th;
        }
    }

    private c.d m() throws FileNotFoundException {
        return p.a(new e(this.h.c(this.x)) { // from class: b.a.a.d.2

            /* renamed from: a, reason: collision with root package name */
            static final /* synthetic */ boolean f764a = !d.class.desiredAssertionStatus();

            @Override // b.a.a.e
            protected void a(IOException iOException) {
                if (!f764a && !Thread.holdsLock(d.this)) {
                    throw new AssertionError();
                }
                d.this.n = true;
            }
        });
    }

    private void d(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i);
            if (iIndexOf == v.length() && str.startsWith(v)) {
                this.l.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        b bVar = this.l.get(strSubstring);
        if (bVar == null) {
            bVar = new b(strSubstring);
            this.l.put(strSubstring, bVar);
        }
        if (iIndexOf2 != -1 && iIndexOf == t.length() && str.startsWith(t)) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            bVar.e = true;
            bVar.f = null;
            bVar.a(strArrSplit);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == u.length() && str.startsWith(u)) {
            bVar.f = new a(bVar);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == w.length() && str.startsWith(w)) {
            return;
        }
        throw new IOException("unexpected journal line: " + str);
    }

    private void n() throws IOException {
        this.h.d(this.y);
        Iterator<b> it = this.l.values().iterator();
        while (it.hasNext()) {
            b next = it.next();
            int i = 0;
            if (next.f == null) {
                while (i < this.j) {
                    this.C += next.f774b[i];
                    i++;
                }
            } else {
                next.f = null;
                while (i < this.j) {
                    this.h.d(next.f775c[i]);
                    this.h.d(next.d[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    synchronized void b() throws IOException {
        if (this.k != null) {
            this.k.close();
        }
        c.d dVarA = p.a(this.h.b(this.y));
        try {
            dVarA.b(d).m(10);
            dVarA.b("1").m(10);
            dVarA.o(this.A).m(10);
            dVarA.o(this.j).m(10);
            dVarA.m(10);
            for (b bVar : this.l.values()) {
                if (bVar.f != null) {
                    dVarA.b(u).m(32);
                    dVarA.b(bVar.f773a);
                    dVarA.m(10);
                } else {
                    dVarA.b(t).m(32);
                    dVarA.b(bVar.f773a);
                    bVar.a(dVarA);
                    dVarA.m(10);
                }
            }
            dVarA.close();
            if (this.h.e(this.x)) {
                this.h.a(this.x, this.z);
            }
            this.h.a(this.y, this.x);
            this.h.d(this.z);
            this.k = m();
            this.n = false;
            this.r = false;
        } catch (Throwable th) {
            dVarA.close();
            throw th;
        }
    }

    public synchronized c a(String str) throws IOException {
        a();
        o();
        e(str);
        b bVar = this.l.get(str);
        if (bVar != null && bVar.e) {
            c cVarA = bVar.a();
            if (cVarA == null) {
                return null;
            }
            this.m++;
            this.k.b(w).m(32).b(str).m(10);
            if (f()) {
                this.E.execute(this.F);
            }
            return cVarA;
        }
        return null;
    }

    @Nullable
    public a b(String str) throws IOException {
        return a(str, -1L);
    }

    synchronized a a(String str, long j) throws IOException {
        a();
        o();
        e(str);
        b bVar = this.l.get(str);
        if (j != -1 && (bVar == null || bVar.g != j)) {
            return null;
        }
        if (bVar != null && bVar.f != null) {
            return null;
        }
        if (!this.q && !this.r) {
            this.k.b(u).m(32).b(str).m(10);
            this.k.flush();
            if (this.n) {
                return null;
            }
            if (bVar == null) {
                bVar = new b(str);
                this.l.put(str, bVar);
            }
            a aVar = new a(bVar);
            bVar.f = aVar;
            return aVar;
        }
        this.E.execute(this.F);
        return null;
    }

    public File c() {
        return this.i;
    }

    public synchronized long d() {
        return this.B;
    }

    public synchronized void a(long j) {
        this.B = j;
        if (this.o) {
            this.E.execute(this.F);
        }
    }

    public synchronized long e() throws IOException {
        a();
        return this.C;
    }

    synchronized void a(a aVar, boolean z) throws IOException {
        b bVar = aVar.f769a;
        if (bVar.f != aVar) {
            throw new IllegalStateException();
        }
        if (z && !bVar.e) {
            for (int i = 0; i < this.j; i++) {
                if (!aVar.f770b[i]) {
                    aVar.c();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!this.h.e(bVar.d[i])) {
                    aVar.c();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.j; i2++) {
            File file = bVar.d[i2];
            if (z) {
                if (this.h.e(file)) {
                    File file2 = bVar.f775c[i2];
                    this.h.a(file, file2);
                    long j = bVar.f774b[i2];
                    long jF = this.h.f(file2);
                    bVar.f774b[i2] = jF;
                    this.C = (this.C - j) + jF;
                }
            } else {
                this.h.d(file);
            }
        }
        this.m++;
        bVar.f = null;
        if (bVar.e | z) {
            bVar.e = true;
            this.k.b(t).m(32);
            this.k.b(bVar.f773a);
            bVar.a(this.k);
            this.k.m(10);
            if (z) {
                long j2 = this.D;
                this.D = 1 + j2;
                bVar.g = j2;
            }
        } else {
            this.l.remove(bVar.f773a);
            this.k.b(v).m(32);
            this.k.b(bVar.f773a);
            this.k.m(10);
        }
        this.k.flush();
        if (this.C > this.B || f()) {
            this.E.execute(this.F);
        }
    }

    boolean f() {
        return this.m >= 2000 && this.m >= this.l.size();
    }

    public synchronized boolean c(String str) throws IOException {
        a();
        o();
        e(str);
        b bVar = this.l.get(str);
        if (bVar == null) {
            return false;
        }
        boolean zA = a(bVar);
        if (zA && this.C <= this.B) {
            this.q = false;
        }
        return zA;
    }

    boolean a(b bVar) throws IOException {
        if (bVar.f != null) {
            bVar.f.a();
        }
        for (int i = 0; i < this.j; i++) {
            this.h.d(bVar.f775c[i]);
            this.C -= bVar.f774b[i];
            bVar.f774b[i] = 0;
        }
        this.m++;
        this.k.b(v).m(32).b(bVar.f773a).m(10);
        this.l.remove(bVar.f773a);
        if (f()) {
            this.E.execute(this.F);
        }
        return true;
    }

    public synchronized boolean g() {
        return this.p;
    }

    private synchronized void o() {
        if (g()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @Override // java.io.Flushable
    public synchronized void flush() throws IOException {
        if (this.o) {
            o();
            h();
            this.k.flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.o && !this.p) {
            for (b bVar : (b[]) this.l.values().toArray(new b[this.l.size()])) {
                if (bVar.f != null) {
                    bVar.f.c();
                }
            }
            h();
            this.k.close();
            this.k = null;
            this.p = true;
            return;
        }
        this.p = true;
    }

    void h() throws IOException {
        while (this.C > this.B) {
            a(this.l.values().iterator().next());
        }
        this.q = false;
    }

    public void i() throws IOException {
        close();
        this.h.g(this.i);
    }

    public synchronized void j() throws IOException {
        a();
        for (b bVar : (b[]) this.l.values().toArray(new b[this.l.size()])) {
            a(bVar);
        }
        this.q = false;
    }

    private void e(String str) {
        if (g.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    public synchronized Iterator<c> k() throws IOException {
        a();
        return new Iterator<c>() { // from class: b.a.a.d.3

            /* renamed from: a, reason: collision with root package name */
            final Iterator<b> f766a;

            /* renamed from: b, reason: collision with root package name */
            c f767b;

            /* renamed from: c, reason: collision with root package name */
            c f768c;

            {
                this.f766a = new ArrayList(d.this.l.values()).iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.f767b != null) {
                    return true;
                }
                synchronized (d.this) {
                    if (d.this.p) {
                        return false;
                    }
                    while (this.f766a.hasNext()) {
                        c cVarA = this.f766a.next().a();
                        if (cVarA != null) {
                            this.f767b = cVarA;
                            return true;
                        }
                    }
                    return false;
                }
            }

            @Override // java.util.Iterator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public c next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.f768c = this.f767b;
                this.f767b = null;
                return this.f768c;
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.f768c == null) {
                    throw new IllegalStateException("remove() before next()");
                }
                try {
                    d.this.c(this.f768c.f777b);
                } catch (IOException unused) {
                } catch (Throwable th) {
                    this.f768c = null;
                    throw th;
                }
                this.f768c = null;
            }
        };
    }

    public final class c implements Closeable {

        /* renamed from: b, reason: collision with root package name */
        private final String f777b;

        /* renamed from: c, reason: collision with root package name */
        private final long f778c;
        private final y[] d;
        private final long[] e;

        c(String str, long j, y[] yVarArr, long[] jArr) {
            this.f777b = str;
            this.f778c = j;
            this.d = yVarArr;
            this.e = jArr;
        }

        public String a() {
            return this.f777b;
        }

        @Nullable
        public a b() throws IOException {
            return d.this.a(this.f777b, this.f778c);
        }

        public y a(int i) {
            return this.d[i];
        }

        public long b(int i) {
            return this.e[i];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            for (y yVar : this.d) {
                b.a.c.a(yVar);
            }
        }
    }

    public final class a {

        /* renamed from: a, reason: collision with root package name */
        final b f769a;

        /* renamed from: b, reason: collision with root package name */
        final boolean[] f770b;
        private boolean d;

        a(b bVar) {
            this.f769a = bVar;
            this.f770b = bVar.e ? null : new boolean[d.this.j];
        }

        void a() {
            if (this.f769a.f == this) {
                for (int i = 0; i < d.this.j; i++) {
                    try {
                        d.this.h.d(this.f769a.d[i]);
                    } catch (IOException unused) {
                    }
                }
                this.f769a.f = null;
            }
        }

        public y a(int i) {
            synchronized (d.this) {
                if (this.d) {
                    throw new IllegalStateException();
                }
                if (!this.f769a.e || this.f769a.f != this) {
                    return null;
                }
                try {
                    return d.this.h.a(this.f769a.f775c[i]);
                } catch (FileNotFoundException unused) {
                    return null;
                }
            }
        }

        public x b(int i) {
            synchronized (d.this) {
                if (this.d) {
                    throw new IllegalStateException();
                }
                if (this.f769a.f != this) {
                    return p.a();
                }
                if (!this.f769a.e) {
                    this.f770b[i] = true;
                }
                try {
                    return new e(d.this.h.b(this.f769a.d[i])) { // from class: b.a.a.d.a.1
                        @Override // b.a.a.e
                        protected void a(IOException iOException) {
                            synchronized (d.this) {
                                a.this.a();
                            }
                        }
                    };
                } catch (FileNotFoundException unused) {
                    return p.a();
                }
            }
        }

        public void b() throws IOException {
            synchronized (d.this) {
                if (this.d) {
                    throw new IllegalStateException();
                }
                if (this.f769a.f == this) {
                    d.this.a(this, true);
                }
                this.d = true;
            }
        }

        public void c() throws IOException {
            synchronized (d.this) {
                if (this.d) {
                    throw new IllegalStateException();
                }
                if (this.f769a.f == this) {
                    d.this.a(this, false);
                }
                this.d = true;
            }
        }

        public void d() {
            synchronized (d.this) {
                if (!this.d && this.f769a.f == this) {
                    try {
                        d.this.a(this, false);
                    } catch (IOException unused) {
                    }
                }
            }
        }
    }

    private final class b {

        /* renamed from: a, reason: collision with root package name */
        final String f773a;

        /* renamed from: b, reason: collision with root package name */
        final long[] f774b;

        /* renamed from: c, reason: collision with root package name */
        final File[] f775c;
        final File[] d;
        boolean e;
        a f;
        long g;

        b(String str) {
            this.f773a = str;
            this.f774b = new long[d.this.j];
            this.f775c = new File[d.this.j];
            this.d = new File[d.this.j];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < d.this.j; i++) {
                sb.append(i);
                this.f775c[i] = new File(d.this.i, sb.toString());
                sb.append(ad.k);
                this.d[i] = new File(d.this.i, sb.toString());
                sb.setLength(length);
            }
        }

        void a(String[] strArr) throws IOException {
            if (strArr.length != d.this.j) {
                throw b(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.f774b[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw b(strArr);
                }
            }
        }

        void a(c.d dVar) throws IOException {
            for (long j : this.f774b) {
                dVar.m(32).o(j);
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        c a() throws IOException {
            if (!Thread.holdsLock(d.this)) {
                throw new AssertionError();
            }
            y[] yVarArr = new y[d.this.j];
            long[] jArr = (long[]) this.f774b.clone();
            for (int i = 0; i < d.this.j; i++) {
                try {
                    yVarArr[i] = d.this.h.a(this.f775c[i]);
                } catch (FileNotFoundException unused) {
                    for (int i2 = 0; i2 < d.this.j && yVarArr[i2] != null; i2++) {
                        b.a.c.a(yVarArr[i2]);
                    }
                    try {
                        d.this.a(this);
                        return null;
                    } catch (IOException unused2) {
                        return null;
                    }
                }
            }
            return d.this.new c(this.f773a, this.g, yVarArr, jArr);
        }
    }
}
