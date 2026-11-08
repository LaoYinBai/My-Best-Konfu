package c;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface y extends Closeable {
    long a(c cVar, long j) throws IOException;

    z a();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;
}
