package com.kwai.filedownloader;

import android.content.Context;
import android.content.Intent;
import com.kwad.sdk.design.provider.ServiceProvider;
import com.kwai.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.kwai.filedownloader.services.FileDownloadServiceProxy;
import com.kwai.filedownloader.services.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class o implements e.a, u {
    private static final Class<?> a = ((com.kwad.sdk.design.kwai.b) ServiceProvider.a(com.kwad.sdk.design.kwai.b.class)).a(FileDownloadServiceProxy.SharedMainProcessServiceProxy.class);
    private final ArrayList<Runnable> b = new ArrayList<>();
    private com.kwai.filedownloader.services.e c;

    o() {
    }

    @Override // com.kwai.filedownloader.services.e.a
    public void a() {
        this.c = null;
        f.a().b(new DownloadServiceConnectChangedEvent(DownloadServiceConnectChangedEvent.ConnectStatus.disconnected, a));
    }

    @Override // com.kwai.filedownloader.u
    public void a(Context context) {
        a(context, null);
    }

    public void a(Context context, Runnable runnable) {
        if (runnable != null && !this.b.contains(runnable)) {
            this.b.add(runnable);
        }
        context.startService(new Intent(context, a));
    }

    @Override // com.kwai.filedownloader.services.e.a
    public void a(com.kwai.filedownloader.services.e eVar) {
        this.c = eVar;
        List list = (List) this.b.clone();
        this.b.clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        f.a().b(new DownloadServiceConnectChangedEvent(DownloadServiceConnectChangedEvent.ConnectStatus.connected, a));
    }

    @Override // com.kwai.filedownloader.u
    public boolean a(int i) {
        return !c() ? com.kwai.filedownloader.e.a.a(i) : this.c.a(i);
    }

    @Override // com.kwai.filedownloader.u
    public boolean a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, com.kwai.filedownloader.c.b bVar, boolean z3) {
        if (!c()) {
            return com.kwai.filedownloader.e.a.a(str, str2, z);
        }
        this.c.a(str, str2, z, i, i2, i3, z2, bVar, z3);
        return true;
    }

    @Override // com.kwai.filedownloader.u
    public byte b(int i) {
        return !c() ? com.kwai.filedownloader.e.a.b(i) : this.c.e(i);
    }

    @Override // com.kwai.filedownloader.u
    public boolean c() {
        return this.c != null;
    }

    @Override // com.kwai.filedownloader.u
    public boolean c(int i) {
        return !c() ? com.kwai.filedownloader.e.a.c(i) : this.c.f(i);
    }
}
