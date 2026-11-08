package com.apm.insight.runtime;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private static j f3043a;

    public static JSONArray a(String str) throws Throwable {
        BufferedReader bufferedReader;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONArray jSONArray = new JSONArray();
                bufferedReader = new BufferedReader(new FileReader(str));
                try {
                    File file = new File(str);
                    if (file.length() > 512000) {
                        bufferedReader.skip(file.length() - 512000);
                    }
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            com.apm.insight.l.k.a(bufferedReader);
                            return jSONArray;
                        }
                        jSONArray.put(line);
                    }
                } catch (IOException e) {
                    e = e;
                    e.printStackTrace();
                    com.apm.insight.l.k.a(bufferedReader);
                    return null;
                }
            } catch (IOException e2) {
                e = e2;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                com.apm.insight.l.k.a((Closeable) null);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void a(j jVar) {
        f3043a = jVar;
    }

    public static JSONArray b(String str) {
        if (f3043a != null && com.apm.insight.h.f().equals(str)) {
            try {
                return a(f3043a.a());
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
        }
        try {
            return a(com.apm.insight.l.f.a(str, com.apm.insight.h.i().getLogcatDumpCount(), com.apm.insight.h.i().getLogcatLevel()).getAbsolutePath());
        } catch (Throwable th2) {
            com.apm.insight.b.a().a("NPTH_CATCH", th2);
            return null;
        }
    }
}
