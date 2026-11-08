package com.kwai.filedownloader.services;

import android.text.TextUtils;
import com.kwai.filedownloader.download.DownloadLaunchRunnable;
import com.kwai.filedownloader.y;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class g implements y {
    private final com.kwai.filedownloader.a.a a;
    private final h b;

    g() {
        com.kwai.filedownloader.download.b bVarA = com.kwai.filedownloader.download.b.a();
        this.a = bVarA.c();
        this.b = new h(bVarA.d());
    }

    @Override // com.kwai.filedownloader.y
    public int a(String str, int i) {
        return this.b.a(str, i);
    }

    public void a() {
        List<Integer> listB = this.b.b();
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "pause all tasks %d", Integer.valueOf(listB.size()));
        }
        Iterator<Integer> it = listB.iterator();
        while (it.hasNext()) {
            b(it.next().intValue());
        }
    }

    public synchronized void a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, com.kwai.filedownloader.c.b bVar, boolean z3) {
        com.kwai.filedownloader.c.c cVar;
        List<com.kwai.filedownloader.c.a> listC;
        boolean z4 = true;
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "request start the task with url(%s) path(%s) isDirectory(%B)", str, str2, Boolean.valueOf(z));
        }
        int iA = com.kwai.filedownloader.e.f.a(str, str2, z);
        com.kwai.filedownloader.c.c cVarB = this.a.b(iA);
        if (z || cVarB != null) {
            cVar = cVarB;
            listC = null;
        } else {
            int iA2 = com.kwai.filedownloader.e.f.a(str, com.kwai.filedownloader.e.f.h(str2), true);
            com.kwai.filedownloader.c.c cVarB2 = this.a.b(iA2);
            if (cVarB2 == null || !str2.equals(cVarB2.d())) {
                listC = null;
            } else {
                if (com.kwai.filedownloader.e.d.a) {
                    com.kwai.filedownloader.e.d.c(this, "task[%d] find model by dirCaseId[%d]", Integer.valueOf(iA), Integer.valueOf(iA2));
                }
                listC = this.a.c(iA2);
            }
            cVar = cVarB2;
        }
        if (com.kwai.filedownloader.e.c.a(iA, cVar, (y) this, true)) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "has already started download %d", Integer.valueOf(iA));
            }
            return;
        }
        String strD = cVar != null ? cVar.d() : com.kwai.filedownloader.e.f.a(str2, z, (String) null);
        if (com.kwai.filedownloader.e.c.a(iA, strD, z2, true)) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "has already completed downloading %d", Integer.valueOf(iA));
            }
            return;
        }
        if (com.kwai.filedownloader.e.c.a(iA, cVar != null ? cVar.g() : 0L, cVar != null ? cVar.e() : com.kwai.filedownloader.e.f.d(strD), strD, this)) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "there is an another task with the same target-file-path %d %s", Integer.valueOf(iA), strD);
                if (cVar != null) {
                    this.a.e(iA);
                    this.a.d(iA);
                }
            }
            return;
        }
        if (cVar == null || !(cVar.f() == -2 || cVar.f() == -1 || cVar.f() == 1 || cVar.f() == 6 || cVar.f() == 2)) {
            if (cVar == null) {
                cVar = new com.kwai.filedownloader.c.c();
            }
            cVar.a(str);
            cVar.a(str2, z);
            cVar.a(iA);
            cVar.a(0L);
            cVar.c(0L);
            cVar.a((byte) 1);
            cVar.b(1);
        } else if (cVar.a() != iA) {
            this.a.e(cVar.a());
            this.a.d(cVar.a());
            cVar.a(iA);
            cVar.a(str2, z);
            if (listC != null) {
                for (com.kwai.filedownloader.c.a aVar : listC) {
                    aVar.a(iA);
                    this.a.a(aVar);
                }
            }
        } else if (TextUtils.equals(str, cVar.b())) {
            z4 = false;
        } else {
            cVar.a(str);
        }
        if (z4) {
            this.a.a(cVar);
        }
        this.b.a(new DownloadLaunchRunnable.a().a(cVar).a(bVar).a(this).a(Integer.valueOf(i2)).b(Integer.valueOf(i)).a(Boolean.valueOf(z2)).b(Boolean.valueOf(z3)).c(Integer.valueOf(i3)).a());
    }

    public boolean a(int i) {
        return a(this.a.b(i));
    }

    @Override // com.kwai.filedownloader.y
    public boolean a(com.kwai.filedownloader.c.c cVar) {
        if (cVar == null) {
            return false;
        }
        boolean zC = this.b.c(cVar.a());
        if (com.kwai.filedownloader.c.d.a(cVar.f())) {
            if (!zC) {
                return false;
            }
        } else if (!zC) {
            com.kwai.filedownloader.e.d.a(this, "%d status is[%s](not finish) & but not in the pool", Integer.valueOf(cVar.a()), Byte.valueOf(cVar.f()));
            return false;
        }
        return true;
    }

    public boolean a(String str, String str2) {
        return a(com.kwai.filedownloader.e.f.b(str, str2));
    }

    public boolean b() {
        return this.b.a() <= 0;
    }

    public boolean b(int i) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "request pause the task %d", Integer.valueOf(i));
        }
        com.kwai.filedownloader.c.c cVarB = this.a.b(i);
        if (cVarB == null) {
            return false;
        }
        cVarB.a((byte) -2);
        this.b.b(i);
        return true;
    }

    public long c(int i) {
        com.kwai.filedownloader.c.c cVarB = this.a.b(i);
        if (cVarB == null) {
            return 0L;
        }
        int iN = cVarB.n();
        if (iN <= 1) {
            return cVarB.g();
        }
        List<com.kwai.filedownloader.c.a> listC = this.a.c(i);
        if (listC == null || listC.size() != iN) {
            return 0L;
        }
        return com.kwai.filedownloader.c.a.a(listC);
    }

    public void c() {
        this.a.a();
    }

    public long d(int i) {
        com.kwai.filedownloader.c.c cVarB = this.a.b(i);
        if (cVarB == null) {
            return 0L;
        }
        return cVarB.h();
    }

    public byte e(int i) {
        com.kwai.filedownloader.c.c cVarB = this.a.b(i);
        if (cVarB == null) {
            return (byte) 0;
        }
        return cVarB.f();
    }

    public synchronized boolean f(int i) {
        return this.b.a(i);
    }

    public boolean g(int i) {
        String str;
        Object[] objArr;
        if (i == 0) {
            str = "The task[%d] id is invalid, can't clear it.";
            objArr = new Object[]{Integer.valueOf(i)};
        } else {
            if (!a(i)) {
                this.a.e(i);
                this.a.d(i);
                return true;
            }
            str = "The task[%d] is downloading, can't clear it.";
            objArr = new Object[]{Integer.valueOf(i)};
        }
        com.kwai.filedownloader.e.d.d(this, str, objArr);
        return false;
    }
}
