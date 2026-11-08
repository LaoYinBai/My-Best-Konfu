package com.kwai.filedownloader.e;

import android.annotation.SuppressLint;
import android.content.Context;
import com.kwai.filedownloader.exception.PathConflictException;
import com.kwai.filedownloader.y;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {

    @SuppressLint({"StaticFieldLeak"})
    private static Context a;

    public interface a {
        int a(int i, String str, String str2, long j);
    }

    public interface b {
        com.kwai.filedownloader.kwai.b a(String str);
    }

    /* renamed from: com.kwai.filedownloader.e.c$c, reason: collision with other inner class name */
    public interface InterfaceC0095c {
        com.kwai.filedownloader.a.a a();
    }

    public interface d {
        int a(int i, String str, String str2, boolean z);

        int a(String str, String str2, boolean z);
    }

    public interface e {
        com.kwai.filedownloader.d.a a(File file);

        boolean a();
    }

    public static Context a() {
        return a;
    }

    public static void a(Context context) {
        a = context;
    }

    public static boolean a(int i, long j, String str, String str2, y yVar) {
        int iA;
        if (str2 == null || str == null || (iA = yVar.a(str, i)) == 0) {
            return false;
        }
        com.kwai.filedownloader.message.e.a().a(com.kwai.filedownloader.message.f.a(i, j, new PathConflictException(iA, str, str2)));
        return true;
    }

    public static boolean a(int i, com.kwai.filedownloader.c.c cVar, y yVar, boolean z) {
        if (!yVar.a(cVar)) {
            return false;
        }
        com.kwai.filedownloader.message.e.a().a(com.kwai.filedownloader.message.f.a(i, cVar.g(), cVar.h(), z));
        return true;
    }

    public static boolean a(int i, String str, boolean z, boolean z2) {
        if (!z && str != null) {
            File file = new File(str);
            if (file.exists()) {
                com.kwai.filedownloader.message.e.a().a(com.kwai.filedownloader.message.f.a(i, file, z2));
                return true;
            }
        }
        return false;
    }
}
