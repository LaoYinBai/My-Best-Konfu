package com.kwai.filedownloader;

import com.kwai.filedownloader.a;
import com.kwai.filedownloader.message.MessageSnapshot;
import com.kwai.filedownloader.s;
import com.kwai.filedownloader.x;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements a.d, x, x.a, x.b {
    private t a;
    private final Object b;
    private final a c;
    private volatile long e;
    private final s.b g;
    private final s.a h;
    private long i;
    private long j;
    private int k;
    private boolean l;
    private boolean m;
    private String n;
    private volatile byte d = 0;
    private Throwable f = null;
    private boolean o = false;

    interface a {
        com.kwai.filedownloader.c.b Q();

        a.b R();

        ArrayList<a.InterfaceC0089a> S();

        void c(String str);
    }

    d(a aVar, Object obj) {
        this.b = obj;
        this.c = aVar;
        b bVar = new b();
        this.g = bVar;
        this.h = bVar;
        this.a = new k(aVar.R(), this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void e(MessageSnapshot messageSnapshot) {
        MessageSnapshot messageSnapshotL;
        t tVar;
        com.kwai.filedownloader.a aVarF = this.c.R().F();
        byte b = messageSnapshot.b();
        a(b);
        this.l = messageSnapshot.n();
        switch (b) {
            case -4:
                this.g.a();
                int iA = h.a().a(aVarF.h());
                if (iA + ((iA > 1 || !aVarF.m()) ? 0 : h.a().a(com.kwai.filedownloader.e.f.b(aVarF.i(), aVarF.o()))) <= 1) {
                    byte b2 = n.a().b(aVarF.h());
                    com.kwai.filedownloader.e.d.d(this, "warn, but no mListener to receive, switch to pending %d %d", Integer.valueOf(aVarF.h()), Integer.valueOf(b2));
                    if (com.kwai.filedownloader.c.d.b(b2)) {
                        a((byte) 1);
                        this.j = messageSnapshot.d();
                        this.i = messageSnapshot.i();
                        this.g.a(this.i);
                        tVar = this.a;
                        messageSnapshotL = ((MessageSnapshot.a) messageSnapshot).l();
                        tVar.a(messageSnapshotL);
                        break;
                    }
                }
                h.a().a(this.c.R(), messageSnapshot);
                break;
            case -3:
                this.o = messageSnapshot.e();
                this.i = messageSnapshot.d();
                this.j = messageSnapshot.d();
                h.a().a(this.c.R(), messageSnapshot);
                break;
            case -1:
                this.f = messageSnapshot.j();
                this.i = messageSnapshot.i();
                h.a().a(this.c.R(), messageSnapshot);
                break;
            case 1:
                this.i = messageSnapshot.i();
                this.j = messageSnapshot.d();
                tVar = this.a;
                messageSnapshotL = messageSnapshot;
                tVar.a(messageSnapshotL);
                break;
            case 2:
                this.j = messageSnapshot.d();
                this.m = messageSnapshot.g();
                this.n = messageSnapshot.h();
                String strF = messageSnapshot.f();
                if (strF != null) {
                    if (aVarF.n() != null) {
                        com.kwai.filedownloader.e.d.d(this, "already has mFilename[%s], but assign mFilename[%s] again", aVarF.n(), strF);
                    }
                    this.c.c(strF);
                }
                this.g.a(this.i);
                this.a.c(messageSnapshot);
                break;
            case 3:
                this.i = messageSnapshot.i();
                this.g.c(messageSnapshot.i());
                this.a.d(messageSnapshot);
                break;
            case 5:
                this.i = messageSnapshot.i();
                this.f = messageSnapshot.j();
                this.k = messageSnapshot.k();
                this.g.a();
                this.a.f(messageSnapshot);
                break;
            case 6:
                this.a.b(messageSnapshot);
                break;
        }
    }

    private void q() throws IOException {
        File file;
        com.kwai.filedownloader.a aVarF = this.c.R().F();
        if (aVarF.l() == null) {
            aVarF.a(com.kwai.filedownloader.e.f.b(aVarF.i()));
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "save Path is null to %s", aVarF.l());
            }
        }
        if (aVarF.m()) {
            file = new File(aVarF.l());
        } else {
            String strH = com.kwai.filedownloader.e.f.h(aVarF.l());
            if (strH == null) {
                throw new InvalidParameterException(com.kwai.filedownloader.e.f.a("the provided mPath[%s] is invalid, can't find its directory", aVarF.l()));
            }
            file = new File(strH);
        }
        if (!file.exists() && !file.mkdirs() && !file.exists()) {
            throw new IOException(com.kwai.filedownloader.e.f.a("Create parent directory failed, please make sure you have permission to create file or directory on the path: %s", file.getAbsolutePath()));
        }
    }

    private int r() {
        return this.c.R().F().h();
    }

    @Override // com.kwai.filedownloader.x.a
    public MessageSnapshot a(Throwable th) {
        a((byte) -1);
        this.f = th;
        return com.kwai.filedownloader.message.f.a(r(), j(), th);
    }

    @Override // com.kwai.filedownloader.a.d
    public void a() {
        if (l.b()) {
            l.a().b(this.c.R().F());
        }
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.e(this, "filedownloader:lifecycle:start %s by %d ", toString(), Byte.valueOf(g()));
        }
    }

    protected void a(byte b) {
        this.d = b;
        this.e = System.currentTimeMillis();
    }

    @Override // com.kwai.filedownloader.x.a
    public boolean a(MessageSnapshot messageSnapshot) {
        if (com.kwai.filedownloader.c.d.a(g(), messageSnapshot.b())) {
            e(messageSnapshot);
            return true;
        }
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "can't update mStatus change by keep ahead, %d, but the current mStatus is %d, %d", Byte.valueOf(this.d), Byte.valueOf(g()), Integer.valueOf(r()));
        }
        return false;
    }

    @Override // com.kwai.filedownloader.s.a
    public int b() {
        return this.h.b();
    }

    @Override // com.kwai.filedownloader.x.a
    public boolean b(MessageSnapshot messageSnapshot) {
        byte bG = g();
        byte b = messageSnapshot.b();
        if (-2 == bG && com.kwai.filedownloader.c.d.b(b)) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "High concurrent cause, callback pending, but has already be paused %d", Integer.valueOf(r()));
            }
            return true;
        }
        if (com.kwai.filedownloader.c.d.b(bG, b)) {
            e(messageSnapshot);
            return true;
        }
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "can't update mStatus change by keep flow, %d, but the current mStatus is %d, %d", Byte.valueOf(this.d), Byte.valueOf(g()), Integer.valueOf(r()));
        }
        return false;
    }

    @Override // com.kwai.filedownloader.a.d
    public void c() {
        com.kwai.filedownloader.a aVarF = this.c.R().F();
        if (l.b()) {
            l.a().d(aVarF);
        }
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.e(this, "filedownloader:lifecycle:over %s by %d ", toString(), Byte.valueOf(g()));
        }
        this.g.b(this.i);
        if (this.c.S() != null) {
            ArrayList arrayList = (ArrayList) this.c.S().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((a.InterfaceC0089a) arrayList.get(i)).a(aVarF);
            }
        }
        r.a().e().b(this.c.R());
    }

    @Override // com.kwai.filedownloader.x.a
    public boolean c(MessageSnapshot messageSnapshot) {
        if (!com.kwai.filedownloader.c.d.a(this.c.R().F())) {
            return false;
        }
        e(messageSnapshot);
        return true;
    }

    @Override // com.kwai.filedownloader.x.a
    public t d() {
        return this.a;
    }

    @Override // com.kwai.filedownloader.x.a
    public boolean d(MessageSnapshot messageSnapshot) {
        if (!this.c.R().F().m() || messageSnapshot.b() != -4 || g() != 2) {
            return false;
        }
        e(messageSnapshot);
        return true;
    }

    @Override // com.kwai.filedownloader.x
    public void e() {
        boolean z;
        synchronized (this.b) {
            if (this.d != 0) {
                com.kwai.filedownloader.e.d.d(this, "High concurrent cause, this task %d will not input to launch pool, because of the status isn't idle : %d", Integer.valueOf(r()), Byte.valueOf(this.d));
                return;
            }
            a((byte) 10);
            a.b bVarR = this.c.R();
            com.kwai.filedownloader.a aVarF = bVarR.F();
            if (l.b()) {
                l.a().a(aVarF);
            }
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.e(this, "call start Url[%s], Path[%s] Listener[%s], Tag[%s]", aVarF.i(), aVarF.l(), aVarF.p(), aVarF.z());
            }
            try {
                q();
                z = true;
            } catch (Throwable th) {
                h.a().b(bVarR);
                h.a().a(bVarR, a(th));
                z = false;
            }
            if (z) {
                q.a().a(this);
            }
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.e(this, "the task[%d] has been into the launch pool.", Integer.valueOf(r()));
            }
        }
    }

    @Override // com.kwai.filedownloader.a.d
    public void e_() {
        if (l.b() && g() == 6) {
            l.a().c(this.c.R().F());
        }
    }

    @Override // com.kwai.filedownloader.x
    public boolean f() {
        if (com.kwai.filedownloader.c.d.a(g())) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "High concurrent cause, Already is over, can't pause again, %d %d", Byte.valueOf(g()), Integer.valueOf(this.c.R().F().h()));
            }
            return false;
        }
        a((byte) -2);
        a.b bVarR = this.c.R();
        com.kwai.filedownloader.a aVarF = bVarR.F();
        q.a().b(this);
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.e(this, "the task[%d] has been expired from the launch pool.", Integer.valueOf(r()));
        }
        if (r.a().c()) {
            n.a().a(aVarF.h());
        } else if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "request pause the task[%d] to the download service, but the download service isn't connected yet.", Integer.valueOf(aVarF.h()));
        }
        h.a().b(bVarR);
        h.a().a(bVarR, com.kwai.filedownloader.message.f.a(aVarF));
        r.a().e().b(bVarR);
        return true;
    }

    @Override // com.kwai.filedownloader.x
    public byte g() {
        return this.d;
    }

    @Override // com.kwai.filedownloader.x
    public long h() {
        return this.e;
    }

    @Override // com.kwai.filedownloader.x
    public void i() {
        this.f = null;
        this.n = null;
        this.m = false;
        this.k = 0;
        this.o = false;
        this.l = false;
        this.i = 0L;
        this.j = 0L;
        this.g.a();
        if (com.kwai.filedownloader.c.d.a(this.d)) {
            this.a.e();
            this.a = new k(this.c.R(), this);
        } else {
            this.a.a(this.c.R(), this);
        }
        a((byte) 0);
    }

    @Override // com.kwai.filedownloader.x
    public long j() {
        return this.i;
    }

    @Override // com.kwai.filedownloader.x
    public long k() {
        return this.j;
    }

    @Override // com.kwai.filedownloader.x
    public Throwable l() {
        return this.f;
    }

    @Override // com.kwai.filedownloader.x
    public int m() {
        return this.k;
    }

    @Override // com.kwai.filedownloader.x
    public boolean n() {
        return this.l;
    }

    @Override // com.kwai.filedownloader.x
    public void o() {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "free the task %d, when the status is %d", Integer.valueOf(r()), Byte.valueOf(this.d));
        }
        a((byte) 0);
    }

    @Override // com.kwai.filedownloader.x.b
    public void p() {
        if (this.d != 10) {
            com.kwai.filedownloader.e.d.d(this, "High concurrent cause, this task %d will not start, because the of status isn't toLaunchPool: %d", Integer.valueOf(r()), Byte.valueOf(this.d));
            return;
        }
        a.b bVarR = this.c.R();
        com.kwai.filedownloader.a aVarF = bVarR.F();
        v vVarE = r.a().e();
        try {
            if (vVarE.c(bVarR)) {
                return;
            }
            synchronized (this.b) {
                if (this.d != 10) {
                    com.kwai.filedownloader.e.d.d(this, "High concurrent cause, this task %d will not start, the status can't assign to toFileDownloadService, because the status isn't toLaunchPool: %d", Integer.valueOf(r()), Byte.valueOf(this.d));
                    return;
                }
                a(FileDownloadStatus.toFileDownloadService);
                h.a().b(bVarR);
                if (com.kwai.filedownloader.e.c.a(aVarF.h(), aVarF.o(), aVarF.x(), true)) {
                    return;
                }
                boolean zA = n.a().a(aVarF.i(), aVarF.l(), aVarF.m(), aVarF.j(), aVarF.k(), aVarF.A(), aVarF.x(), this.c.Q(), aVarF.E());
                if (this.d == -2) {
                    com.kwai.filedownloader.e.d.d(this, "High concurrent cause, this task %d will be paused,because of the status is paused, so the pause action must be applied", Integer.valueOf(r()));
                    if (zA) {
                        n.a().a(r());
                        return;
                    }
                    return;
                }
                if (zA) {
                    vVarE.b(bVarR);
                    return;
                }
                if (vVarE.c(bVarR)) {
                    return;
                }
                MessageSnapshot messageSnapshotA = a(new RuntimeException("Occur Unknown Error, when request to start maybe some problem in binder, maybe the process was killed in unexpected."));
                if (h.a().a(bVarR)) {
                    vVarE.b(bVarR);
                    h.a().b(bVarR);
                }
                h.a().a(bVarR, messageSnapshotA);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            h.a().a(bVarR, a(th));
        }
    }
}
