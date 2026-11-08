package com.kwai.sodler.lib;

import android.content.Context;
import com.kwai.sodler.lib.ext.PluginError;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends com.kwai.sodler.lib.kwai.a {
    public g(String str) {
        super(str);
    }

    @Override // com.kwai.sodler.lib.kwai.a
    public void a(Context context, String str) throws PluginError.LoadError {
        a.b("Sodler.simple.package", "Create plugin package entity.");
        a(new File(str));
        d();
    }

    protected void a(File file) throws PluginError.LoadError {
        if (file == null || !file.exists()) {
            a.e("Sodler.simple.package", "Apk file not exist.");
            throw new PluginError.LoadError("Apk file not exist.", PluginError.ERROR_LOA_NOT_FOUND);
        }
        if (file.getAbsolutePath().trim().startsWith("/data/")) {
            return;
        }
        a.e("Sodler.simple.package", "Apk file seems to locate in external path (not executable), path = " + file.getAbsolutePath());
    }
}
