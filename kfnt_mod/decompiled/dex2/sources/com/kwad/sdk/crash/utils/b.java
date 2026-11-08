package com.kwad.sdk.crash.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    public static void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(InputStream inputStream) throws IOException {
        a((Closeable) inputStream);
    }

    public static void a(OutputStream outputStream) throws IOException {
        a((Closeable) outputStream);
    }

    public static void a(Reader reader) throws IOException {
        a((Closeable) reader);
    }

    public static void a(Writer writer) throws IOException {
        a((Closeable) writer);
    }
}
