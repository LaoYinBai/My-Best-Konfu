package com.apm.insight.l;

import androidx.annotation.NonNull;
import com.apm.insight.nativecrash.NativeImpl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {
    @NonNull
    public static File a(String str, int i, int i2) throws IOException {
        File file = new File(o.a(com.apm.insight.h.g(), str), "logcat.txt");
        if (file.exists() && file.length() > 0) {
            return file;
        }
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException unused) {
        }
        NativeImpl.a(file.getAbsolutePath(), String.valueOf(i), String.valueOf(i2));
        return file;
    }

    public static void a() {
        try {
            a(com.apm.insight.h.f(), com.apm.insight.h.i().getLogcatDumpCount(), com.apm.insight.h.i().getLogcatLevel());
            b();
            d();
            c();
            e();
        } catch (Throwable unused) {
        }
    }

    @NonNull
    public static File b() throws IOException {
        File file = new File(o.e(com.apm.insight.h.g()), "maps.txt");
        if (file.exists()) {
            return file;
        }
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException unused) {
        }
        NativeImpl.f(file.getAbsolutePath());
        return file;
    }

    @NonNull
    public static File c() throws IOException {
        File file = new File(o.e(com.apm.insight.h.g()), "meminfo.txt");
        if (file.exists()) {
            return file;
        }
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException unused) {
        }
        NativeImpl.d(file.getAbsolutePath());
        return file;
    }

    public static File d() throws IOException {
        File file = new File(o.e(com.apm.insight.h.g()), "fds.txt");
        if (file.exists()) {
            return file;
        }
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException unused) {
        }
        NativeImpl.e(file.getAbsolutePath());
        return file;
    }

    public static File e() throws IOException {
        File file = new File(o.e(com.apm.insight.h.g()), "threads.txt");
        if (file.exists()) {
            return file;
        }
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException unused) {
        }
        NativeImpl.g(file.getAbsolutePath());
        return file;
    }

    public static File f() throws Throwable {
        BufferedReader bufferedReader;
        File file = new File(o.e(com.apm.insight.h.g()), "anr_trace.txt");
        if (file.exists() || !com.apm.insight.runtime.a.f()) {
            return file;
        }
        File file2 = new File("/data/anr/traces.txt");
        if (!file2.exists()) {
            return file;
        }
        BufferedWriter bufferedWriter = null;
        try {
            file.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new FileReader(file2));
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file));
                int length = 0;
                do {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        bufferedWriter2.write(line);
                        bufferedWriter2.write(10);
                        length += line.length();
                    } catch (IOException unused) {
                        bufferedWriter = bufferedWriter2;
                        k.a(bufferedReader);
                        k.a(bufferedWriter);
                        return file;
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        k.a(bufferedReader);
                        k.a(bufferedWriter);
                        throw th;
                    }
                } while (length < 1048576);
                k.a(bufferedReader);
                k.a(bufferedWriter2);
            } catch (IOException unused2) {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused3) {
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
        return file;
    }
}
