package com.kwad.a;

import android.content.Context;
import android.util.Log;
import com.kwad.a.b;
import com.kwad.a.kwai.f;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    protected final Set<String> a;
    protected final b.InterfaceC0021b b;
    protected final b.a c;
    protected boolean d;
    protected boolean e;
    protected b.d f;

    protected c() {
        this(new d(), new a());
    }

    protected c(b.InterfaceC0021b interfaceC0021b, b.a aVar) {
        this.a = new HashSet();
        if (interfaceC0021b == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        }
        if (aVar == null) {
            throw new IllegalArgumentException("Cannot pass null library installer");
        }
        this.b = interfaceC0021b;
        this.c = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, String str, String str2) throws Throwable {
        if (this.a.contains(str) && !this.d) {
            a("%s already loaded previously!", str);
            return;
        }
        try {
            this.b.a(str);
            this.a.add(str);
            a("%s (%s) was loaded normally!", str, str2);
        } catch (UnsatisfiedLinkError e) {
            a("Loading the library normally failed: %s", Log.getStackTraceString(e));
            a("%s (%s) was not loaded normally, re-linking...", str, str2);
            File fileA = a(context, str, str2);
            if (!fileA.exists() || this.d) {
                if (this.d) {
                    a("Forcing a re-link of %s (%s)...", str, str2);
                }
                b(context, str, str2);
                this.c.a(context, this.b.a(), this.b.c(str), fileA, this);
            }
            try {
                if (this.e) {
                    f fVar = null;
                    try {
                        f fVar2 = new f(fileA);
                        try {
                            List<String> listB = fVar2.b();
                            fVar2.close();
                            Iterator<String> it = listB.iterator();
                            while (it.hasNext()) {
                                a(context, this.b.d(it.next()));
                            }
                        } catch (Throwable th) {
                            th = th;
                            fVar = fVar2;
                            fVar.close();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (IOException unused) {
            }
            this.b.b(fileA.getAbsolutePath());
            this.a.add(str);
            a("%s (%s) was re-linked!", str, str2);
        }
    }

    protected File a(Context context) {
        return context.getDir("lib", 0);
    }

    protected File a(Context context, String str, String str2) {
        String strC = this.b.c(str);
        if (e.a(str2)) {
            return new File(a(context), strC);
        }
        return new File(a(context), strC + "" + str2);
    }

    public void a(Context context, String str) {
        a(context, str, (String) null, (b.c) null);
    }

    public void a(final Context context, final String str, final String str2, final b.c cVar) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        }
        if (e.a(str)) {
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        a("Beginning load of %s...", str);
        if (cVar == null) {
            c(context, str, str2);
        } else {
            new Thread(new Runnable() { // from class: com.kwad.a.c.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        c.this.c(context, str, str2);
                        cVar.a(str);
                    } catch (Exception | UnsatisfiedLinkError e) {
                        cVar.a(str, e);
                    }
                }
            }).start();
        }
    }

    public void a(String str) {
        if (this.f != null) {
            this.f.a(str);
        }
    }

    public void a(String str, Object... objArr) {
        a(String.format(Locale.US, str, objArr));
    }

    protected void b(Context context, String str, String str2) {
        File fileA = a(context);
        File fileA2 = a(context, str, str2);
        final String strC = this.b.c(str);
        File[] fileArrListFiles = fileA.listFiles(new FilenameFilter() { // from class: com.kwad.a.c.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str3) {
                return str3.startsWith(strC);
            }
        });
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (this.d || !file.getAbsolutePath().equals(fileA2.getAbsolutePath())) {
                file.delete();
            }
        }
    }
}
