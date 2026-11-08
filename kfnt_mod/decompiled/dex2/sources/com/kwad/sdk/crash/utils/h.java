package com.kwad.sdk.crash.utils;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    public static final char a = File.separatorChar;
    public static final String b;

    static {
        StringBuilderWriter stringBuilderWriter = new StringBuilderWriter(4);
        PrintWriter printWriter = new PrintWriter(stringBuilderWriter);
        printWriter.println();
        b = stringBuilderWriter.toString();
        printWriter.close();
    }

    public static int a(Reader reader, Writer writer) {
        long jB = b(reader, writer);
        if (jB > 2147483647L) {
            return -1;
        }
        return (int) jB;
    }

    public static long a(Reader reader, Writer writer, char[] cArr) throws IOException {
        long j = 0;
        while (true) {
            int i = reader.read(cArr);
            if (-1 == i) {
                return j;
            }
            writer.write(cArr, 0, i);
            j += i;
        }
    }

    public static String a(File file) {
        return a(new InputStreamReader(new BufferedInputStream(new FileInputStream(file)), StandardCharsets.UTF_8));
    }

    public static String a(InputStream inputStream) {
        return a(inputStream, Charset.defaultCharset());
    }

    public static String a(InputStream inputStream, Charset charset) {
        StringBuilderWriter stringBuilderWriter = new StringBuilderWriter();
        a(inputStream, stringBuilderWriter, charset);
        return stringBuilderWriter.toString();
    }

    public static String a(Reader reader) throws Throwable {
        StringWriter stringWriter;
        Throwable th;
        StringWriter stringWriter2 = new StringWriter();
        try {
            stringWriter = new StringWriter();
            try {
                char[] cArr = new char[1024];
                while (true) {
                    int i = reader.read(cArr);
                    if (i == -1) {
                        String string = stringWriter.toString();
                        b.a(reader);
                        b.a((Writer) stringWriter);
                        return string;
                    }
                    stringWriter.write(cArr, 0, i);
                }
            } catch (Throwable th2) {
                th = th2;
                b.a(reader);
                b.a((Writer) stringWriter);
                throw th;
            }
        } catch (Throwable th3) {
            stringWriter = stringWriter2;
            th = th3;
        }
    }

    public static void a(InputStream inputStream, Writer writer, Charset charset) {
        a(new InputStreamReader(inputStream, a.a(charset)), writer);
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    public static void a(String str, OutputStream outputStream, Charset charset) throws IOException {
        if (str != null) {
            outputStream.write(str.getBytes(a.a(charset)));
        }
    }

    public static void a(String str, String str2, boolean z) throws Throwable {
        FileWriter fileWriter;
        FileWriter fileWriter2 = null;
        try {
            try {
                try {
                    File file = new File(str);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    fileWriter = new FileWriter(str, z);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                fileWriter.write(str2);
                fileWriter.close();
            } catch (Throwable th3) {
                th = th3;
                fileWriter2 = fileWriter;
                com.kwad.sdk.core.d.a.b(th);
                if (fileWriter2 != null) {
                    fileWriter2.close();
                }
            }
        } catch (Exception unused) {
        }
    }

    public static long b(Reader reader, Writer writer) {
        return a(reader, writer, new char[4096]);
    }

    public static String b(Reader reader) {
        StringBuilderWriter stringBuilderWriter = new StringBuilderWriter();
        a(reader, stringBuilderWriter);
        return stringBuilderWriter.toString();
    }

    public static void b(String str) {
        try {
            a(str);
        } catch (Throwable unused) {
        }
    }
}
