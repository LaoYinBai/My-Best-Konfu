package b.a.g;

import c.p;
import c.x;
import c.y;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface a {

    /* renamed from: a, reason: collision with root package name */
    public static final a f927a = new a() { // from class: b.a.g.a.1
        @Override // b.a.g.a
        public y a(File file) throws FileNotFoundException {
            return p.a(file);
        }

        @Override // b.a.g.a
        public x b(File file) throws FileNotFoundException {
            try {
                return p.b(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return p.b(file);
            }
        }

        @Override // b.a.g.a
        public x c(File file) throws FileNotFoundException {
            try {
                return p.c(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return p.c(file);
            }
        }

        @Override // b.a.g.a
        public void d(File file) throws IOException {
            if (file.delete() || !file.exists()) {
                return;
            }
            throw new IOException("failed to delete " + file);
        }

        @Override // b.a.g.a
        public boolean e(File file) {
            return file.exists();
        }

        @Override // b.a.g.a
        public long f(File file) {
            return file.length();
        }

        @Override // b.a.g.a
        public void a(File file, File file2) throws IOException {
            d(file2);
            if (file.renameTo(file2)) {
                return;
            }
            throw new IOException("failed to rename " + file + " to " + file2);
        }

        @Override // b.a.g.a
        public void g(File file) throws IOException {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                throw new IOException("not a readable directory: " + file);
            }
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    g(file2);
                }
                if (!file2.delete()) {
                    throw new IOException("failed to delete " + file2);
                }
            }
        }
    };

    y a(File file) throws FileNotFoundException;

    void a(File file, File file2) throws IOException;

    x b(File file) throws FileNotFoundException;

    x c(File file) throws FileNotFoundException;

    void d(File file) throws IOException;

    boolean e(File file);

    long f(File file);

    void g(File file) throws IOException;
}
