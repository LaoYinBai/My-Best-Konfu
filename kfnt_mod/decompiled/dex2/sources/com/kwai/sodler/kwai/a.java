package com.kwai.sodler.kwai;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
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
}
