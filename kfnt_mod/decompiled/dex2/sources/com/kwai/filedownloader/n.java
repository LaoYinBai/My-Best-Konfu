package com.kwai.filedownloader;

import android.content.Context;
import com.kwad.sdk.design.provider.ServiceProvider;
import com.kwai.filedownloader.services.FileDownloadServiceProxy;
import com.kwai.filedownloader.services.e;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class n implements u {
    private final u a;

    private static final class a {
        private static final n a = new n();
    }

    private n() {
        this.a = com.kwai.filedownloader.e.e.a().d ? new o() : new p(((com.kwad.sdk.design.kwai.b) ServiceProvider.a(com.kwad.sdk.design.kwai.b.class)).a(FileDownloadServiceProxy.SeparateProcessServiceProxy.class));
    }

    public static n a() {
        return a.a;
    }

    public static e.a b() {
        if (a().a instanceof o) {
            return (e.a) a().a;
        }
        return null;
    }

    @Override // com.kwai.filedownloader.u
    public void a(Context context) {
        this.a.a(context);
    }

    @Override // com.kwai.filedownloader.u
    public boolean a(int i) {
        return this.a.a(i);
    }

    @Override // com.kwai.filedownloader.u
    public boolean a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, com.kwai.filedownloader.c.b bVar, boolean z3) {
        return this.a.a(str, str2, z, i, i2, i3, z2, bVar, z3);
    }

    @Override // com.kwai.filedownloader.u
    public byte b(int i) {
        return this.a.b(i);
    }

    @Override // com.kwai.filedownloader.u
    public boolean c() {
        return this.a.c();
    }

    @Override // com.kwai.filedownloader.u
    public boolean c(int i) {
        return this.a.c(i);
    }
}
