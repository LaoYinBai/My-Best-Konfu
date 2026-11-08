package com.kwad.sdk.core.diskcache.kwai;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    private static final OutputStream p = new OutputStream() { // from class: com.kwad.sdk.core.diskcache.kwai.a.2
        @Override // java.io.OutputStream
        public void write(int i) {
        }
    };
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private final int i;
    private Writer k;
    private int m;
    private long j = 0;
    private final LinkedHashMap<String, b> l = new LinkedHashMap<>(0, 0.75f, true);
    private long n = 0;
    final ExecutorService b = com.kwad.sdk.core.i.b.a();
    private final Callable<Void> o = new Callable<Void>() { // from class: com.kwad.sdk.core.diskcache.kwai.a.1
        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() {
            synchronized (a.this) {
                if (a.this.k == null) {
                    return null;
                }
                a.this.j();
                if (a.this.h()) {
                    a.this.g();
                    a.this.m = 0;
                }
                return null;
            }
        }
    };

    /* renamed from: com.kwad.sdk.core.diskcache.kwai.a$a, reason: collision with other inner class name */
    public final class C0033a {
        private final b b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        /* renamed from: com.kwad.sdk.core.diskcache.kwai.a$a$a, reason: collision with other inner class name */
        private class C0034a extends FilterOutputStream {
            private C0034a(OutputStream outputStream) {
                super(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    C0033a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    C0033a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) throws IOException {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    C0033a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    C0033a.this.d = true;
                }
            }
        }

        private C0033a(b bVar) {
            this.b = bVar;
            this.c = bVar.d ? null : new boolean[a.this.i];
        }

        public OutputStream a(int i) {
            FileOutputStream fileOutputStream;
            C0034a c0034a;
            if (i < 0 || i >= a.this.i) {
                throw new IllegalArgumentException("Expected index " + i + " to be greater than 0 and less than the maximum value count of " + a.this.i);
            }
            synchronized (a.this) {
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.d) {
                    this.c[i] = true;
                }
                File fileB = this.b.b(i);
                try {
                    fileOutputStream = new FileOutputStream(fileB);
                } catch (FileNotFoundException unused) {
                    a.this.c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(fileB);
                    } catch (FileNotFoundException unused2) {
                        return a.p;
                    }
                }
                c0034a = new C0034a(fileOutputStream);
            }
            return c0034a;
        }

        public void a() {
            if (this.d) {
                a.this.a(this, false);
                a.this.c(this.b.b);
            } else {
                a.this.a(this, true);
            }
            this.e = true;
        }

        public void b() {
            a.this.a(this, false);
        }
    }

    private final class b {
        private final String b;
        private final long[] c;
        private boolean d;
        private C0033a e;
        private long f;

        private b(String str) {
            this.b = str;
            this.c = new long[a.this.i];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String[] strArr) throws IOException {
            if (strArr.length != a.this.i) {
                throw b(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return new File(a.this.c, this.b);
        }

        public String a() {
            StringBuilder sb = new StringBuilder();
            for (long j : this.c) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        public File b(int i) {
            return new File(a.this.c, this.b + ".tmp");
        }
    }

    public final class c implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private c(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public InputStream a(int i) {
            return this.d[i];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            for (InputStream inputStream : this.d) {
                com.kwad.sdk.core.diskcache.kwai.c.a(inputStream);
            }
        }
    }

    private a(File file, int i, int i2, long j) {
        this.c = file;
        this.g = i;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.i = i2;
        this.h = j;
    }

    private synchronized C0033a a(String str, long j) {
        i();
        e(str);
        b bVar = this.l.get(str);
        if (j != -1 && (bVar == null || bVar.f != j)) {
            return null;
        }
        if (bVar == null) {
            bVar = new b(str);
            this.l.put(str, bVar);
        } else if (bVar.e != null) {
            return null;
        }
        C0033a c0033a = new C0033a(bVar);
        bVar.e = c0033a;
        this.k.write("DIRTY " + str + '\n');
        this.k.flush();
        return c0033a;
    }

    public static a a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        a aVar = new a(file, i, i2, j);
        if (aVar.d.exists()) {
            try {
                aVar.e();
                aVar.f();
                return aVar;
            } catch (IOException e) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                aVar.c();
            }
        }
        file.mkdirs();
        a aVar2 = new a(file, i, i2, j);
        aVar2.g();
        return aVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(C0033a c0033a, boolean z) {
        b bVar = c0033a.b;
        if (bVar.e != c0033a) {
            throw new IllegalStateException();
        }
        if (z && !bVar.d) {
            for (int i = 0; i < this.i; i++) {
                if (!c0033a.c[i]) {
                    c0033a.b();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!bVar.b(i).exists()) {
                    c0033a.b();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.i; i2++) {
            File fileB = bVar.b(i2);
            if (!z) {
                a(fileB);
            } else if (fileB.exists()) {
                File fileA = bVar.a(i2);
                fileB.renameTo(fileA);
                long j = bVar.c[i2];
                long length = fileA.length();
                bVar.c[i2] = length;
                this.j = (this.j - j) + length;
            }
        }
        this.m++;
        bVar.e = null;
        if (bVar.d || z) {
            bVar.d = true;
            this.k.write("CLEAN " + bVar.b + bVar.a() + '\n');
            if (z) {
                long j2 = this.n;
                this.n = 1 + j2;
                bVar.f = j2;
            }
        } else {
            this.l.remove(bVar.b);
            this.k.write("REMOVE " + bVar.b + '\n');
        }
        this.k.flush();
        if (this.j > this.h || h()) {
            this.b.submit(this.o);
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
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
            if (iIndexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
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
        if (iIndexOf2 != -1 && iIndexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            bVar.d = true;
            bVar.e = null;
            bVar.a(strArrSplit);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            bVar.e = new C0033a(bVar);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == "READ".length() && str.startsWith("READ")) {
            return;
        }
        throw new IOException("unexpected journal line: " + str);
    }

    private void e() throws IOException {
        com.kwad.sdk.core.diskcache.kwai.b bVar = new com.kwad.sdk.core.diskcache.kwai.b(new FileInputStream(this.d), com.kwad.sdk.core.diskcache.kwai.c.a);
        try {
            String strA = bVar.a();
            String strA2 = bVar.a();
            String strA3 = bVar.a();
            String strA4 = bVar.a();
            String strA5 = bVar.a();
            if (!"libcore.io.DiskLruCache".equals(strA) || !"1".equals(strA2) || !Integer.toString(this.g).equals(strA3) || !Integer.toString(this.i).equals(strA4) || !"".equals(strA5)) {
                throw new IOException("unexpected journal header: [" + strA + ", " + strA2 + ", " + strA4 + ", " + strA5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    d(bVar.a());
                    i++;
                } catch (EOFException unused) {
                    this.m = i - this.l.size();
                    if (bVar.b()) {
                        g();
                    } else {
                        this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), com.kwad.sdk.core.diskcache.kwai.c.a));
                    }
                    com.kwad.sdk.core.diskcache.kwai.c.a(bVar);
                    return;
                }
            }
        } catch (Throwable th) {
            com.kwad.sdk.core.diskcache.kwai.c.a(bVar);
            throw th;
        }
    }

    private void e(String str) {
        if (a.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    private void f() throws IOException {
        a(this.e);
        Iterator<b> it = this.l.values().iterator();
        while (it.hasNext()) {
            b next = it.next();
            int i = 0;
            if (next.e == null) {
                while (i < this.i) {
                    this.j += next.c[i];
                    i++;
                }
            } else {
                next.e = null;
                while (i < this.i) {
                    a(next.a(i));
                    a(next.b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void g() {
        if (this.k != null) {
            this.k.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), com.kwad.sdk.core.diskcache.kwai.c.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.l.values()) {
                bufferedWriter.write(bVar.e != null ? "DIRTY " + bVar.b + '\n' : "CLEAN " + bVar.b + bVar.a() + '\n');
            }
            bufferedWriter.close();
            if (this.d.exists()) {
                a(this.d, this.f, true);
            }
            a(this.e, this.d, false);
            this.f.delete();
            this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), com.kwad.sdk.core.diskcache.kwai.c.a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        return this.m >= 2000 && this.m >= this.l.size();
    }

    private void i() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        while (this.j > this.h) {
            c(this.l.entrySet().iterator().next().getKey());
        }
    }

    public synchronized c a(String str) {
        i();
        e(str);
        b bVar = this.l.get(str);
        if (bVar == null) {
            return null;
        }
        if (!bVar.d) {
            return null;
        }
        InputStream[] inputStreamArr = new InputStream[this.i];
        for (int i = 0; i < this.i; i++) {
            try {
                inputStreamArr[i] = new FileInputStream(bVar.a(i));
            } catch (FileNotFoundException unused) {
                for (int i2 = 0; i2 < this.i && inputStreamArr[i2] != null; i2++) {
                    com.kwad.sdk.core.diskcache.kwai.c.a(inputStreamArr[i2]);
                }
                return null;
            }
        }
        this.m++;
        this.k.append((CharSequence) ("READ " + str + '\n'));
        if (h()) {
            this.b.submit(this.o);
        }
        return new c(str, bVar.f, inputStreamArr, bVar.c);
    }

    public File a() {
        return this.c;
    }

    public C0033a b(String str) {
        return a(str, -1L);
    }

    public synchronized void b() {
        i();
        j();
        this.k.flush();
    }

    public void c() throws IOException {
        close();
        com.kwad.sdk.core.diskcache.kwai.c.a(this.c);
    }

    public synchronized boolean c(String str) {
        i();
        e(str);
        b bVar = this.l.get(str);
        if (bVar != null && bVar.e == null) {
            for (int i = 0; i < this.i; i++) {
                File fileA = bVar.a(i);
                if (fileA.exists() && !fileA.delete()) {
                    throw new IOException("failed to delete " + fileA);
                }
                this.j -= bVar.c[i];
                bVar.c[i] = 0;
            }
            this.m++;
            this.k.append((CharSequence) ("REMOVE " + str + '\n'));
            this.l.remove(str);
            if (h()) {
                this.b.submit(this.o);
            }
            return true;
        }
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.k == null) {
            return;
        }
        Iterator it = new ArrayList(this.l.values()).iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.e != null) {
                bVar.e.b();
            }
        }
        j();
        this.k.close();
        this.k = null;
    }
}
