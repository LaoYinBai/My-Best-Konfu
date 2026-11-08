package com.huawei.agconnect.a.a;

import android.util.Log;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class h {
    public static void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                Log.e("Utils", "Exception when closing the 'Closeable'.");
            }
        }
    }

    public static void a(Reader reader, Writer writer, char[] cArr) throws IOException {
        while (true) {
            int i = reader.read(cArr);
            if (-1 == i) {
                return;
            } else {
                writer.write(cArr, 0, i);
            }
        }
    }

    public static void a(Reader reader, Writer writer) throws IOException {
        a(reader, writer, new char[4096]);
    }

    public static String a(InputStream inputStream, String str) throws IOException {
        StringWriter stringWriter = new StringWriter();
        a(new InputStreamReader(inputStream, str), stringWriter);
        return stringWriter.toString();
    }
}
