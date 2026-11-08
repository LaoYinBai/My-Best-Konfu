package com.mbridge.msdk.foundation.same.b;

import android.util.Log;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.u;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: MBridgeDirManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class e {
    private static e c;
    private b a;
    private ArrayList<a> b = new ArrayList<>();

    private e(b bVar) {
        this.a = bVar;
    }

    public static synchronized void a(b bVar) {
        if (c == null) {
            c = new e(bVar);
        }
    }

    public static synchronized e a() {
        if (c == null && com.mbridge.msdk.foundation.controller.a.f().j() != null) {
            u.a(com.mbridge.msdk.foundation.controller.a.f().j());
        }
        if (c == null) {
            Log.e("MBridgeDirManager", "mDirectoryManager == null");
        }
        return c;
    }

    public static File a(c cVar) {
        try {
            if (a() == null) {
                return null;
            }
            Iterator<a> it = a().b.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next.a.equals(cVar)) {
                    return next.b;
                }
            }
            return null;
        } catch (Throwable th) {
            s.b("MBridgeDirManager", th.getMessage(), th);
            return null;
        }
    }

    public static String b(c cVar) {
        File fileA = a(cVar);
        if (fileA != null) {
            return fileA.getAbsolutePath();
        }
        return null;
    }

    public final boolean b() {
        return a(this.a.a());
    }

    private boolean a(com.mbridge.msdk.foundation.same.b.a aVar) {
        String strB;
        com.mbridge.msdk.foundation.same.b.a aVarC = aVar.c();
        if (aVarC == null) {
            strB = aVar.b();
        } else {
            strB = a(aVarC.a()).getAbsolutePath() + File.separator + aVar.b();
        }
        File file = new File(strB);
        if (!(!file.exists() ? file.mkdirs() : true)) {
            return false;
        }
        this.b.add(new a(aVar.a(), file));
        List<com.mbridge.msdk.foundation.same.b.a> listD = aVar.d();
        if (listD != null) {
            Iterator<com.mbridge.msdk.foundation.same.b.a> it = listD.iterator();
            while (it.hasNext()) {
                if (!a(it.next())) {
                    return false;
                }
            }
        }
        return true;
    }

    /* compiled from: MBridgeDirManager.java */
    private static final class a {
        public c a;
        public File b;

        public a(c cVar, File file) {
            this.a = cVar;
            this.b = file;
        }
    }
}
