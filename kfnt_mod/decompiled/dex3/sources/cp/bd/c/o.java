package cp.bd.c;

import android.content.Context;
import android.util.Log;
import cp.bd.c.n;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class o {
    protected final Set<String> a;
    protected final n.b b;
    protected final n.a c;
    protected boolean d;
    protected boolean e;

    protected o() {
        this(new p(), new l());
    }

    private o(n.b bVar, n.a aVar) {
        this.a = new HashSet();
        this.b = bVar;
        this.c = aVar;
    }

    public static void a(String str, Object... objArr) {
        String.format(Locale.US, str, objArr);
    }

    private void b(Context context, String str) throws Throwable {
        String str2;
        Object[] objArr;
        w wVar;
        if (!this.a.contains(str) || this.d) {
            try {
                this.b.a(str);
                this.a.add(str);
                a("%s (%s) was loaded normally!", str, null);
                return;
            } catch (UnsatisfiedLinkError e) {
                a("Loading the library normally failed: %s", Log.getStackTraceString(e));
                a("%s (%s) was not loaded normally, re-linking...", str, null);
                File fileC = c(context, str);
                if (!fileC.exists() || this.d) {
                    if (this.d) {
                        a("Forcing a re-link of %s (%s)...", str, null);
                    }
                    File dir = context.getDir("lib", 0);
                    File fileC2 = c(context, str);
                    final String strC = this.b.c(str);
                    File[] fileArrListFiles = dir.listFiles(new FilenameFilter() { // from class: cp.bd.c.o.1
                        @Override // java.io.FilenameFilter
                        public final boolean accept(File file, String str3) {
                            return str3.startsWith(strC);
                        }
                    });
                    if (fileArrListFiles != null) {
                        for (File file : fileArrListFiles) {
                            if (this.d || !file.getAbsolutePath().equals(fileC2.getAbsolutePath())) {
                                file.delete();
                            }
                        }
                    }
                    this.c.a(context, this.b.a(), this.b.c(str), fileC);
                }
                try {
                    if (this.e) {
                        try {
                            wVar = new w(fileC);
                            try {
                                List<String> listA = wVar.a();
                                wVar.close();
                                Iterator<String> it = listA.iterator();
                                while (it.hasNext()) {
                                    a(context, this.b.d(it.next()));
                                }
                            } catch (Throwable th) {
                                th = th;
                                wVar.close();
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            wVar = null;
                        }
                    }
                } catch (IOException unused) {
                }
                this.b.b(fileC.getAbsolutePath());
                this.a.add(str);
                str2 = "%s (%s) was re-linked!";
                objArr = new Object[]{str, null};
            }
        } else {
            str2 = "%s already loaded previously!";
            objArr = new Object[]{str};
        }
        a(str2, objArr);
    }

    private File c(Context context, String str) {
        String strC = this.b.c(str);
        if (q.a(null)) {
            return new File(context.getDir("lib", 0), strC);
        }
        return new File(context.getDir("lib", 0), strC + "." + ((String) null));
    }

    public final void a(Context context, String str) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        }
        if (q.a(str)) {
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        a("Beginning load of %s...", str);
        b(context, str);
    }
}
