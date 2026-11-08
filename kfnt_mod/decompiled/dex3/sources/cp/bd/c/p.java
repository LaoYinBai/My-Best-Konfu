package cp.bd.c;

import android.os.Build;
import cp.bd.c.n;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class p implements n.b {
    p() {
    }

    @Override // cp.bd.c.n.b
    public final void a(String str) {
        System.loadLibrary(str);
    }

    @Override // cp.bd.c.n.b
    public final String[] a() {
        return (Build.VERSION.SDK_INT < 21 || Build.SUPPORTED_ABIS.length <= 0) ? !q.a(Build.CPU_ABI2) ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : new String[]{Build.CPU_ABI} : Build.SUPPORTED_ABIS;
    }

    @Override // cp.bd.c.n.b
    public final void b(String str) {
        System.load(str);
    }

    @Override // cp.bd.c.n.b
    public final String c(String str) {
        return (str.startsWith("lib") && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    @Override // cp.bd.c.n.b
    public final String d(String str) {
        return str.substring(3, str.length() - 3);
    }
}
