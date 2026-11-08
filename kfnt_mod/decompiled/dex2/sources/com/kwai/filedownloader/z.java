package com.kwai.filedownloader;

import com.kwai.filedownloader.a;
import com.kwai.filedownloader.event.DownloadServiceConnectChangedEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class z extends e implements v {
    private final ArrayList<a.b> a = new ArrayList<>();

    @Override // com.kwai.filedownloader.e
    public void a() {
        w wVarD = r.a().d();
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "The downloader service is connected.", new Object[0]);
        }
        synchronized (this.a) {
            List<a.b> list = (List) this.a.clone();
            this.a.clear();
            ArrayList arrayList = new ArrayList(wVarD.b());
            for (a.b bVar : list) {
                int I = bVar.I();
                if (wVarD.a(I)) {
                    bVar.F().a().a();
                    if (!arrayList.contains(Integer.valueOf(I))) {
                        arrayList.add(Integer.valueOf(I));
                    }
                } else {
                    bVar.N();
                }
            }
            wVarD.a(arrayList);
        }
    }

    @Override // com.kwai.filedownloader.v
    public boolean a(a.b bVar) {
        return !this.a.isEmpty() && this.a.contains(bVar);
    }

    @Override // com.kwai.filedownloader.e
    public void b() {
        if (c() != DownloadServiceConnectChangedEvent.ConnectStatus.lost) {
            if (h.a().b() > 0) {
                com.kwai.filedownloader.e.d.d(this, "file download service has be unbound but the size of active tasks are not empty %d ", Integer.valueOf(h.a().b()));
                return;
            }
            return;
        }
        w wVarD = r.a().d();
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "lost the connection to the file download service, and current active task size is %d", Integer.valueOf(h.a().b()));
        }
        if (h.a().b() > 0) {
            synchronized (this.a) {
                h.a().a(this.a);
                Iterator<a.b> it = this.a.iterator();
                while (it.hasNext()) {
                    it.next().M();
                }
                wVarD.a();
            }
            r.a().b();
        }
    }

    @Override // com.kwai.filedownloader.v
    public void b(a.b bVar) {
        if (this.a.isEmpty()) {
            return;
        }
        synchronized (this.a) {
            this.a.remove(bVar);
        }
    }

    @Override // com.kwai.filedownloader.v
    public boolean c(a.b bVar) {
        if (!r.a().c()) {
            synchronized (this.a) {
                if (!r.a().c()) {
                    if (com.kwai.filedownloader.e.d.a) {
                        com.kwai.filedownloader.e.d.c(this, "Waiting for connecting with the downloader service... %d", Integer.valueOf(bVar.F().h()));
                    }
                    n.a().a(com.kwai.filedownloader.e.c.a());
                    if (!this.a.contains(bVar)) {
                        bVar.M();
                        this.a.add(bVar);
                    }
                    return true;
                }
            }
        }
        b(bVar);
        return false;
    }
}
