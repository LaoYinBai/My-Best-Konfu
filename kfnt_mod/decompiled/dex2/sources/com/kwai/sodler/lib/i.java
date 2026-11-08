package com.kwai.sodler.lib;

import android.content.Context;
import android.text.TextUtils;
import com.kwai.sodler.lib.ext.PluginError;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i extends g {
    public i(String str) {
        super(str);
    }

    private void a(Set<File> set) throws Throwable {
        if (this.f == null || this.f.h.size() <= 0 || set == null) {
            return;
        }
        HashMap<String, String> map = this.f.h;
        for (File file : set) {
            String strF = com.kwai.sodler.lib.b.b.f(file);
            String str = map.get(file.getName());
            if (str != null && !TextUtils.equals(strF, str)) {
                b(set);
                throw new PluginError.LoadError(new Exception(file.getName() + "Md5 check error,find " + strF + ",except " + str), PluginError.ERROR_LOA_SO_MD5_CHECK);
            }
        }
    }

    private void b(Set<File> set) {
        Iterator<File> it = set.iterator();
        while (it.hasNext()) {
            com.kwai.sodler.lib.b.b.a(it.next());
        }
    }

    protected Set<File> a(Context context, File file, File file2) throws Throwable {
        a.b("Sodler.simple.SoLib", "Install plugin so libs, destDir = " + file2);
        HashSet hashSet = new HashSet();
        if (file2.exists() && file2.list().length > 0) {
            return hashSet;
        }
        File file3 = new File(file2.getParentFile(), this.c.d());
        com.kwai.sodler.lib.b.b.e(file3);
        Set<String> setA = com.kwai.sodler.lib.b.c.a(file, file3);
        if (setA != null) {
            for (String str : setA) {
                a.b("Sodler.simple.SoLib", "extractSoLib, soName = " + str);
                File fileA = com.kwai.sodler.lib.b.c.a(context, file3, str, file2);
                if (fileA != null) {
                    hashSet.add(fileA);
                }
            }
            com.kwai.sodler.lib.b.b.a(file3);
        }
        return hashSet;
    }

    @Override // com.kwai.sodler.lib.g, com.kwai.sodler.lib.kwai.a
    public void a(Context context, String str) throws Throwable {
        a.b("Sodler.simple.SoLib", "Install plugin so libs.");
        File file = new File(str);
        a(file);
        try {
            this.a = b(file);
            try {
                a(a(context, file, this.a));
                synchronized (Runtime.getRuntime()) {
                    try {
                        com.kwai.sodler.lib.ext.d.a(getClass().getClassLoader(), this.a);
                    } finally {
                        PluginError.LoadError loadError = new PluginError.LoadError(th, PluginError.ERROR_LOA_SO_INSTALL);
                    }
                }
                super.a(context, str);
            } catch (IOException th) {
                throw new PluginError.LoadError(th, i);
            }
        } catch (IOException e) {
            throw new PluginError.LoadError(e, PluginError.ERROR_LOA_SO_DIR);
        }
    }

    public File b(File file) throws IOException {
        File file2 = new File(file.getParentFile(), this.c.c());
        com.kwai.sodler.lib.b.b.e(file2);
        return file2;
    }
}
