package com.ss.union.game.sdk.core.glide.disklrucache;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class DiskLruCache implements Closeable {
    static final String a = "journal";
    static final String b = "journal.tmp";
    static final String c = "journal.bkp";
    static final String d = "libcore.io.DiskLruCache";
    static final String e = "1";
    static final long f = -1;
    private static final String h = "CLEAN";
    private static final String i = "DIRTY";
    private static final String j = "REMOVE";
    private static final String k = "READ";
    private final File l;
    private final File m;
    private final File n;
    private final File o;
    private final int p;
    private long q;
    private final int r;
    private Writer t;
    private int v;
    private long s = 0;
    private final LinkedHashMap<String, b> u = new LinkedHashMap<>(0, 0.75f, true);
    private long w = 0;
    final ThreadPoolExecutor g = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new a());
    private final Callable<Void> x = new Callable<Void>() { // from class: com.ss.union.game.sdk.core.glide.disklrucache.DiskLruCache.1
        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            synchronized (DiskLruCache.this) {
                if (DiskLruCache.this.t == null) {
                    return null;
                }
                DiskLruCache.this.f();
                if (DiskLruCache.this.d()) {
                    DiskLruCache.this.c();
                    DiskLruCache.this.v = 0;
                }
                return null;
            }
        }
    };

    private DiskLruCache(File file, int i2, int i3, long j2) {
        this.l = file;
        this.p = i2;
        this.m = new File(file, a);
        this.n = new File(file, b);
        this.o = new File(file, c);
        this.r = i3;
        this.q = j2;
    }

    public static DiskLruCache open(File file, int i2, int i3, long j2) throws IOException {
        if (j2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, c);
        if (file2.exists()) {
            File file3 = new File(file, a);
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        DiskLruCache diskLruCache = new DiskLruCache(file, i2, i3, j2);
        if (diskLruCache.m.exists()) {
            try {
                diskLruCache.a();
                diskLruCache.b();
                return diskLruCache;
            } catch (IOException e2) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e2.getMessage() + ", removing");
                diskLruCache.delete();
            }
        }
        file.mkdirs();
        DiskLruCache diskLruCache2 = new DiskLruCache(file, i2, i3, j2);
        diskLruCache2.c();
        return diskLruCache2;
    }

    private void a() throws IOException {
        com.ss.union.game.sdk.core.glide.disklrucache.a aVar = new com.ss.union.game.sdk.core.glide.disklrucache.a(new FileInputStream(this.m), com.ss.union.game.sdk.core.glide.disklrucache.b.a);
        try {
            String strA = aVar.a();
            String strA2 = aVar.a();
            String strA3 = aVar.a();
            String strA4 = aVar.a();
            String strA5 = aVar.a();
            if (!d.equals(strA) || !"1".equals(strA2) || !Integer.toString(this.p).equals(strA3) || !Integer.toString(this.r).equals(strA4) || !"".equals(strA5)) {
                throw new IOException("unexpected journal header: [" + strA + ", " + strA2 + ", " + strA4 + ", " + strA5 + "]");
            }
            int i2 = 0;
            while (true) {
                try {
                    a(aVar.a());
                    i2++;
                } catch (EOFException unused) {
                    this.v = i2 - this.u.size();
                    if (aVar.b()) {
                        c();
                    } else {
                        this.t = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.m, true), com.ss.union.game.sdk.core.glide.disklrucache.b.a));
                    }
                    com.ss.union.game.sdk.core.glide.disklrucache.b.a(aVar);
                    return;
                }
            }
        } catch (Throwable th) {
            com.ss.union.game.sdk.core.glide.disklrucache.b.a(aVar);
            throw th;
        }
    }

    private void a(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i2 = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i2);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i2);
            if (iIndexOf == j.length() && str.startsWith(j)) {
                this.u.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i2, iIndexOf2);
        }
        b bVar = this.u.get(strSubstring);
        if (bVar == null) {
            bVar = new b(strSubstring);
            this.u.put(strSubstring, bVar);
        }
        if (iIndexOf2 != -1 && iIndexOf == h.length() && str.startsWith(h)) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            bVar.f = true;
            bVar.g = null;
            bVar.a(strArrSplit);
            return;
        }
        if (iIndexOf2 != -1 || iIndexOf != i.length() || !str.startsWith(i)) {
            if (iIndexOf2 == -1 && iIndexOf == k.length() && str.startsWith(k)) {
                return;
            }
            throw new IOException("unexpected journal line: " + str);
        }
        bVar.g = new Editor(bVar);
    }

    private void b() throws IOException {
        a(this.n);
        Iterator<b> it = this.u.values().iterator();
        while (it.hasNext()) {
            b next = it.next();
            int i2 = 0;
            if (next.g == null) {
                while (i2 < this.r) {
                    this.s += next.e[i2];
                    i2++;
                }
            } else {
                next.g = null;
                while (i2 < this.r) {
                    a(next.a(i2));
                    a(next.b(i2));
                    i2++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void c() throws IOException {
        if (this.t != null) {
            this.t.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.n), com.ss.union.game.sdk.core.glide.disklrucache.b.a));
        try {
            bufferedWriter.write(d);
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.p));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.r));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.u.values()) {
                if (bVar.g != null) {
                    bufferedWriter.write("DIRTY " + bVar.d + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + bVar.d + bVar.a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.m.exists()) {
                a(this.m, this.o, true);
            }
            a(this.n, this.m, false);
            this.o.delete();
            this.t = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.m, true), com.ss.union.game.sdk.core.glide.disklrucache.b.a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
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

    public synchronized Value get(String str) throws IOException {
        e();
        b bVar = this.u.get(str);
        if (bVar == null) {
            return null;
        }
        if (!bVar.f) {
            return null;
        }
        for (File file : bVar.a) {
            if (!file.exists()) {
                return null;
            }
        }
        this.v++;
        this.t.append((CharSequence) k);
        this.t.append(' ');
        this.t.append((CharSequence) str);
        this.t.append('\n');
        if (d()) {
            this.g.submit(this.x);
        }
        return new Value(str, bVar.h, bVar.a, bVar.e);
    }

    public Editor edit(String str) throws IOException {
        return a(str, -1L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Editor a(String str, long j2) throws IOException {
        e();
        b bVar = this.u.get(str);
        if (j2 != -1 && (bVar == null || bVar.h != j2)) {
            return null;
        }
        if (bVar == null) {
            bVar = new b(str);
            this.u.put(str, bVar);
        } else if (bVar.g != null) {
            return null;
        }
        Editor editor = new Editor(bVar);
        bVar.g = editor;
        this.t.append((CharSequence) i);
        this.t.append(' ');
        this.t.append((CharSequence) str);
        this.t.append('\n');
        this.t.flush();
        return editor;
    }

    public File getDirectory() {
        return this.l;
    }

    public synchronized long getMaxSize() {
        return this.q;
    }

    public synchronized void setMaxSize(long j2) {
        this.q = j2;
        this.g.submit(this.x);
    }

    public synchronized long size() {
        return this.s;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(Editor editor, boolean z) throws IOException {
        b bVar = editor.b;
        if (bVar.g != editor) {
            throw new IllegalStateException();
        }
        if (z && !bVar.f) {
            for (int i2 = 0; i2 < this.r; i2++) {
                if (!editor.c[i2]) {
                    editor.abort();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                }
                if (!bVar.b(i2).exists()) {
                    editor.abort();
                    return;
                }
            }
        }
        for (int i3 = 0; i3 < this.r; i3++) {
            File fileB = bVar.b(i3);
            if (z) {
                if (fileB.exists()) {
                    File fileA = bVar.a(i3);
                    fileB.renameTo(fileA);
                    long j2 = bVar.e[i3];
                    long length = fileA.length();
                    bVar.e[i3] = length;
                    this.s = (this.s - j2) + length;
                }
            } else {
                a(fileB);
            }
        }
        this.v++;
        bVar.g = null;
        if (bVar.f | z) {
            bVar.f = true;
            this.t.append((CharSequence) h);
            this.t.append(' ');
            this.t.append((CharSequence) bVar.d);
            this.t.append((CharSequence) bVar.a());
            this.t.append('\n');
            if (z) {
                long j3 = this.w;
                this.w = 1 + j3;
                bVar.h = j3;
            }
        } else {
            this.u.remove(bVar.d);
            this.t.append((CharSequence) j);
            this.t.append(' ');
            this.t.append((CharSequence) bVar.d);
            this.t.append('\n');
        }
        this.t.flush();
        if (this.s > this.q || d()) {
            this.g.submit(this.x);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        return this.v >= 2000 && this.v >= this.u.size();
    }

    public synchronized boolean remove(String str) throws IOException {
        e();
        b bVar = this.u.get(str);
        if (bVar != null && bVar.g == null) {
            for (int i2 = 0; i2 < this.r; i2++) {
                File fileA = bVar.a(i2);
                if (fileA.exists() && !fileA.delete()) {
                    throw new IOException("failed to delete " + fileA);
                }
                this.s -= bVar.e[i2];
                bVar.e[i2] = 0;
            }
            this.v++;
            this.t.append((CharSequence) j);
            this.t.append(' ');
            this.t.append((CharSequence) str);
            this.t.append('\n');
            this.u.remove(str);
            if (d()) {
                this.g.submit(this.x);
            }
            return true;
        }
        return false;
    }

    public synchronized boolean isClosed() {
        return this.t == null;
    }

    private void e() {
        if (this.t == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        e();
        f();
        this.t.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.t == null) {
            return;
        }
        Iterator it = new ArrayList(this.u.values()).iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.g != null) {
                bVar.g.abort();
            }
        }
        f();
        this.t.close();
        this.t = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() throws IOException {
        while (this.s > this.q) {
            remove(this.u.entrySet().iterator().next().getKey());
        }
    }

    public void delete() throws IOException {
        close();
        com.ss.union.game.sdk.core.glide.disklrucache.b.a(this.l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(InputStream inputStream) throws IOException {
        return com.ss.union.game.sdk.core.glide.disklrucache.b.a((Reader) new InputStreamReader(inputStream, com.ss.union.game.sdk.core.glide.disklrucache.b.b));
    }

    public final class Value {
        private final String b;
        private final long c;
        private final long[] d;
        private final File[] e;

        private Value(String str, long j, File[] fileArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.e = fileArr;
            this.d = jArr;
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.a(this.b, this.c);
        }

        public File getFile(int i) {
            return this.e[i];
        }

        public String getString(int i) throws IOException {
            return DiskLruCache.b(new FileInputStream(this.e[i]));
        }

        public long getLength(int i) {
            return this.d[i];
        }
    }

    public final class Editor {
        private final b b;
        private final boolean[] c;
        private boolean d;

        private Editor(b bVar) {
            this.b = bVar;
            this.c = bVar.f ? null : new boolean[DiskLruCache.this.r];
        }

        private InputStream a(int i) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.b.g != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.f) {
                    return null;
                }
                try {
                    return new FileInputStream(this.b.a(i));
                } catch (FileNotFoundException unused) {
                    return null;
                }
            }
        }

        public String getString(int i) throws IOException {
            InputStream inputStreamA = a(i);
            if (inputStreamA != null) {
                return DiskLruCache.b(inputStreamA);
            }
            return null;
        }

        public File getFile(int i) throws IOException {
            File fileB;
            synchronized (DiskLruCache.this) {
                if (this.b.g != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.f) {
                    this.c[i] = true;
                }
                fileB = this.b.b(i);
                if (!DiskLruCache.this.l.exists()) {
                    DiskLruCache.this.l.mkdirs();
                }
            }
            return fileB;
        }

        public void set(int i, String str) throws Throwable {
            OutputStreamWriter outputStreamWriter = null;
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(getFile(i)), com.ss.union.game.sdk.core.glide.disklrucache.b.b);
                try {
                    outputStreamWriter2.write(str);
                    com.ss.union.game.sdk.core.glide.disklrucache.b.a(outputStreamWriter2);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    com.ss.union.game.sdk.core.glide.disklrucache.b.a(outputStreamWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        public void commit() throws IOException {
            DiskLruCache.this.a(this, true);
            this.d = true;
        }

        public void abort() throws IOException {
            DiskLruCache.this.a(this, false);
        }

        public void abortUnlessCommitted() {
            if (this.d) {
                return;
            }
            try {
                abort();
            } catch (IOException unused) {
            }
        }
    }

    private final class b {
        File[] a;
        File[] b;
        private final String d;
        private final long[] e;
        private boolean f;
        private Editor g;
        private long h;

        private b(String str) {
            this.d = str;
            this.e = new long[DiskLruCache.this.r];
            this.a = new File[DiskLruCache.this.r];
            this.b = new File[DiskLruCache.this.r];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < DiskLruCache.this.r; i++) {
                sb.append(i);
                this.a[i] = new File(DiskLruCache.this.l, sb.toString());
                sb.append(".tmp");
                this.b[i] = new File(DiskLruCache.this.l, sb.toString());
                sb.setLength(length);
            }
        }

        public String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.e) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String[] strArr) throws IOException {
            if (strArr.length != DiskLruCache.this.r) {
                throw b(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.e[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return this.a[i];
        }

        public File b(int i) {
            return this.b[i];
        }
    }

    private static final class a implements ThreadFactory {
        private a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }
    }
}
