package com.kwad.a;

import android.os.Build;
import com.kwad.a.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class d implements b.InterfaceC0021b {
    d() {
    }

    @Override // com.kwad.a.b.InterfaceC0021b
    public void a(String str) {
        System.loadLibrary(str);
    }

    @Override // com.kwad.a.b.InterfaceC0021b
    public String[] a() {
        return (Build.VERSION.SDK_INT < 21 || Build.SUPPORTED_ABIS.length <= 0) ? !e.a(Build.CPU_ABI2) ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : new String[]{Build.CPU_ABI} : Build.SUPPORTED_ABIS;
    }

    @Override // com.kwad.a.b.InterfaceC0021b
    public void b(String str) {
        System.load(str);
    }

    @Override // com.kwad.a.b.InterfaceC0021b
    public String c(String str) {
        return (str.startsWith("lib") && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    @Override // com.kwad.a.b.InterfaceC0021b
    public String d(String str) {
        return str.substring(3, str.length() - 3);
    }
}
