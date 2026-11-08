package com.kwai.filedownloader;

import com.kwai.filedownloader.a;
import com.kwai.filedownloader.message.MessageSnapshot;
import com.kwai.filedownloader.x;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class k implements t {
    private a.b a;
    private a.d b;
    private Queue<MessageSnapshot> c;
    private boolean d = false;

    k(a.b bVar, a.d dVar) {
        b(bVar, dVar);
    }

    private void a(int i) {
        if (com.kwai.filedownloader.c.d.a(i)) {
            if (!this.c.isEmpty()) {
                MessageSnapshot messageSnapshotPeek = this.c.peek();
                com.kwai.filedownloader.e.d.d(this, "the messenger[%s](with id[%d]) has already accomplished all his job, but there still are some messages in parcel queue[%d] queue-top-status[%d]", this, Integer.valueOf(messageSnapshotPeek.m()), Integer.valueOf(this.c.size()), Byte.valueOf(messageSnapshotPeek.b()));
            }
            this.a = null;
        }
    }

    private void b(a.b bVar, a.d dVar) {
        this.a = bVar;
        this.b = dVar;
        this.c = new LinkedBlockingQueue();
    }

    private void k(MessageSnapshot messageSnapshot) {
        if (this.a == null) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "occur this case, it would be the host task of this messenger has been over(paused/warn/completed/error) on the other thread before receiving the snapshot(id[%d], status[%d])", Integer.valueOf(messageSnapshot.m()), Byte.valueOf(messageSnapshot.b()));
            }
        } else {
            if (!this.d && this.a.F().p() != null) {
                this.c.offer(messageSnapshot);
                j.a().a(this);
                return;
            }
            if ((l.b() || this.a.O()) && messageSnapshot.b() == 4) {
                this.b.c();
            }
            a(messageSnapshot.b());
        }
    }

    @Override // com.kwai.filedownloader.t
    public void a(a.b bVar, a.d dVar) {
        if (this.a != null) {
            throw new IllegalStateException(com.kwai.filedownloader.e.f.a("the messenger is working, can't re-appointment for %s", bVar));
        }
        b(bVar, dVar);
    }

    @Override // com.kwai.filedownloader.t
    public void a(MessageSnapshot messageSnapshot) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify pending %s", this.a);
        }
        this.b.e_();
        k(messageSnapshot);
    }

    @Override // com.kwai.filedownloader.t
    public boolean a() {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify begin %s", this.a);
        }
        if (this.a == null) {
            com.kwai.filedownloader.e.d.d(this, "can't begin the task, the holder fo the messenger is nil, %d", Integer.valueOf(this.c.size()));
            return false;
        }
        this.b.a();
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.kwai.filedownloader.t
    public void b() {
        if (this.d) {
        }
        MessageSnapshot messageSnapshotPoll = this.c.poll();
        byte b = messageSnapshotPoll.b();
        a.b bVar = this.a;
        if (bVar == null) {
            return;
        }
        a aVarF = bVar.F();
        i iVarP = aVarF.p();
        x.a aVarG = bVar.G();
        a(b);
        if (iVarP == null || iVarP.a()) {
            return;
        }
        if (b == 4) {
            try {
                iVarP.b(aVarF);
                j(((com.kwai.filedownloader.message.a) messageSnapshotPoll).f_());
                return;
            } catch (Throwable th) {
                h(aVarG.a(th));
                return;
            }
        }
        g gVar = iVarP instanceof g ? (g) iVarP : null;
        switch (b) {
            case -4:
                iVarP.d(aVarF);
                break;
            case -3:
                iVarP.c(aVarF);
                break;
            case -2:
                if (gVar == null) {
                    iVarP.c(aVarF, messageSnapshotPoll.a(), messageSnapshotPoll.c());
                    break;
                } else {
                    gVar.c(aVarF, messageSnapshotPoll.i(), messageSnapshotPoll.d());
                    break;
                }
            case -1:
                iVarP.a(aVarF, messageSnapshotPoll.j());
                break;
            case 1:
                if (gVar == null) {
                    iVarP.a(aVarF, messageSnapshotPoll.a(), messageSnapshotPoll.c());
                    break;
                } else {
                    gVar.a(aVarF, messageSnapshotPoll.i(), messageSnapshotPoll.d());
                    break;
                }
            case 2:
                if (gVar == null) {
                    iVarP.a(aVarF, messageSnapshotPoll.h(), messageSnapshotPoll.g(), aVarF.q(), messageSnapshotPoll.c());
                    break;
                } else {
                    gVar.a(aVarF, messageSnapshotPoll.h(), messageSnapshotPoll.g(), aVarF.r(), messageSnapshotPoll.d());
                    break;
                }
            case 3:
                if (gVar == null) {
                    iVarP.b(aVarF, messageSnapshotPoll.a(), aVarF.s());
                    break;
                } else {
                    gVar.b(aVarF, messageSnapshotPoll.i(), aVarF.t());
                    break;
                }
            case 5:
                if (gVar == null) {
                    iVarP.a(aVarF, messageSnapshotPoll.j(), messageSnapshotPoll.k(), messageSnapshotPoll.a());
                    break;
                } else {
                    gVar.a(aVarF, messageSnapshotPoll.j(), messageSnapshotPoll.k(), messageSnapshotPoll.i());
                    break;
                }
            case 6:
                iVarP.a(aVarF);
                break;
        }
    }

    @Override // com.kwai.filedownloader.t
    public void b(MessageSnapshot messageSnapshot) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify started %s", this.a);
        }
        this.b.e_();
        k(messageSnapshot);
    }

    @Override // com.kwai.filedownloader.t
    public void c(MessageSnapshot messageSnapshot) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify connected %s", this.a);
        }
        this.b.e_();
        k(messageSnapshot);
    }

    @Override // com.kwai.filedownloader.t
    public boolean c() {
        return this.a.F().C();
    }

    @Override // com.kwai.filedownloader.t
    public void d(MessageSnapshot messageSnapshot) {
        a aVarF = this.a.F();
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify progress %s %d %d", aVarF, Long.valueOf(aVarF.r()), Long.valueOf(aVarF.t()));
        }
        if (aVarF.j() > 0) {
            this.b.e_();
            k(messageSnapshot);
        } else if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify progress but client not request notify %s", this.a);
        }
    }

    @Override // com.kwai.filedownloader.t
    public boolean d() {
        return this.c.peek().b() == 4;
    }

    @Override // com.kwai.filedownloader.t
    public void e() {
        this.d = true;
    }

    @Override // com.kwai.filedownloader.t
    public void e(MessageSnapshot messageSnapshot) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify block completed %s %s", this.a, Thread.currentThread().getName());
        }
        this.b.e_();
        k(messageSnapshot);
    }

    @Override // com.kwai.filedownloader.t
    public void f(MessageSnapshot messageSnapshot) {
        if (com.kwai.filedownloader.e.d.a) {
            a aVarF = this.a.F();
            com.kwai.filedownloader.e.d.c(this, "notify retry %s %d %d %s", this.a, Integer.valueOf(aVarF.A()), Integer.valueOf(aVarF.B()), aVarF.y());
        }
        this.b.e_();
        k(messageSnapshot);
    }

    @Override // com.kwai.filedownloader.t
    public void g(MessageSnapshot messageSnapshot) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify warn %s", this.a);
        }
        this.b.c();
        k(messageSnapshot);
    }

    @Override // com.kwai.filedownloader.t
    public void h(MessageSnapshot messageSnapshot) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify error %s %s", this.a, this.a.F().y());
        }
        this.b.c();
        k(messageSnapshot);
    }

    @Override // com.kwai.filedownloader.t
    public void i(MessageSnapshot messageSnapshot) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify paused %s", this.a);
        }
        this.b.c();
        k(messageSnapshot);
    }

    public void j(MessageSnapshot messageSnapshot) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "notify completed %s", this.a);
        }
        this.b.c();
        k(messageSnapshot);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(this.a == null ? -1 : this.a.F().h());
        objArr[1] = super.toString();
        return com.kwai.filedownloader.e.f.a("%d:%s", objArr);
    }
}
