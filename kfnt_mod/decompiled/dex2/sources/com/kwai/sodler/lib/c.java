package com.kwai.sodler.lib;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class c implements com.kwai.sodler.lib.kwai.c {
    private final File a;
    private final File b;
    private final Context c;
    private final com.kwai.sodler.lib.ext.c d;

    c(Context context, com.kwai.sodler.lib.ext.c cVar) {
        this.c = context.getApplicationContext();
        this.d = cVar;
        this.a = this.c.getDir(this.d.b(), 0);
        this.b = this.c.getCacheDir();
    }

    @Nullable
    public String a(com.kwai.sodler.lib.kwai.a aVar) {
        return b(aVar.c(), aVar.a());
    }

    @Override // com.kwai.sodler.lib.kwai.c
    public void a() throws IOException {
        if (this.a.getFreeSpace() < 10000000) {
            throw new IOException("No enough capacity.");
        }
    }

    public void a(String str, String str2) {
        com.kwai.sodler.lib.b.b.a(b(str, str2));
    }

    public boolean a(String str) {
        a.b("Sodler.installer", "Check plugin's validation.");
        if (com.kwai.sodler.lib.b.b.b(str)) {
            a.a("Sodler.installer", "Check plugin's signatures success, path = " + str);
            return true;
        }
        a.e("Sodler.installer", "Plugin not found, path = " + str);
        return false;
    }

    public boolean a(String str, String str2, boolean z) {
        if (a(b(str, str2))) {
            return true;
        }
        if (!z) {
            return false;
        }
        a(str, str2);
        return false;
    }

    public boolean a(String str, boolean z) {
        if (a(str)) {
            return true;
        }
        if (!z) {
            return false;
        }
        b(str);
        return false;
    }

    public String b() {
        return this.a.getAbsolutePath();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00da  */
    @Override // com.kwai.sodler.lib.kwai.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String b(com.kwai.sodler.lib.kwai.a r8) throws com.kwai.sodler.lib.ext.PluginError.InstallError {
        /*
            Method dump skipped, instructions count: 275
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.sodler.lib.c.b(com.kwai.sodler.lib.kwai.a):java.lang.String");
    }

    @Override // com.kwai.sodler.lib.kwai.c
    public String b(String str, String str2) {
        return b() + File.separator + str + File.separator + str2 + File.separator + this.d.f();
    }

    public void b(String str) {
        com.kwai.sodler.lib.b.b.a(str);
    }

    @Override // com.kwai.sodler.lib.kwai.c
    public void c(String str) {
        File file = new File(e(str));
        if (file.exists()) {
            com.kwai.sodler.lib.b.b.a(file);
            return;
        }
        a.e("Sodler.installer", "Delete fail, dir not found, path = " + file.getAbsolutePath());
    }

    @Override // com.kwai.sodler.lib.kwai.c
    public boolean c(String str, String str2) {
        if (this.d.g()) {
            return false;
        }
        return a(str, str2, true);
    }

    @Override // com.kwai.sodler.lib.kwai.c
    public File d(String str) {
        return File.createTempFile(str + System.currentTimeMillis(), this.d.e(), this.b);
    }

    @Override // com.kwai.sodler.lib.kwai.c
    public String e(@NonNull String str) {
        return b() + File.separator + str;
    }
}
