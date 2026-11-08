package com.kwai.filedownloader;

import com.kwai.filedownloader.a;
import com.kwai.filedownloader.message.MessageSnapshot;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private final ArrayList<a.b> a;

    private static final class a {
        private static final h a = new h();
    }

    private h() {
        this.a = new ArrayList<>();
    }

    public static h a() {
        return a.a;
    }

    int a(int i) {
        int i2;
        synchronized (this.a) {
            Iterator<a.b> it = this.a.iterator();
            i2 = 0;
            while (it.hasNext()) {
                if (it.next().b(i)) {
                    i2++;
                }
            }
        }
        return i2;
    }

    void a(List<a.b> list) {
        synchronized (this.a) {
            Iterator<a.b> it = this.a.iterator();
            while (it.hasNext()) {
                a.b next = it.next();
                if (!list.contains(next)) {
                    list.add(next);
                }
            }
            this.a.clear();
        }
    }

    boolean a(a.b bVar) {
        return this.a.isEmpty() || !this.a.contains(bVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean a(a.b bVar, MessageSnapshot messageSnapshot) {
        boolean zRemove;
        byte b = messageSnapshot.b();
        synchronized (this.a) {
            zRemove = this.a.remove(bVar);
        }
        if (com.kwai.filedownloader.e.d.a && this.a.size() == 0) {
            com.kwai.filedownloader.e.d.e(this, "remove %s left %d %d", bVar, Byte.valueOf(b), Integer.valueOf(this.a.size()));
        }
        if (zRemove) {
            t tVarD = bVar.G().d();
            switch (b) {
                case -4:
                    tVarD.g(messageSnapshot);
                    break;
                case -3:
                    tVarD.e(com.kwai.filedownloader.message.f.a(messageSnapshot));
                    break;
                case -2:
                    tVarD.i(messageSnapshot);
                    break;
                case -1:
                    tVarD.h(messageSnapshot);
                    break;
            }
        } else {
            com.kwai.filedownloader.e.d.a(this, "remove error, not exist: %s %d", bVar, Byte.valueOf(b));
        }
        return zRemove;
    }

    int b() {
        return this.a.size();
    }

    List<a.b> b(int i) {
        byte bV;
        ArrayList arrayList = new ArrayList();
        synchronized (this.a) {
            Iterator<a.b> it = this.a.iterator();
            while (it.hasNext()) {
                a.b next = it.next();
                if (next.b(i) && !next.H() && (bV = next.F().v()) != 0 && bV != 10) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    void b(a.b bVar) {
        if (!bVar.F().d()) {
            bVar.J();
        }
        if (bVar.G().d().a()) {
            c(bVar);
        }
    }

    List<a.b> c(int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.a) {
            Iterator<a.b> it = this.a.iterator();
            while (it.hasNext()) {
                a.b next = it.next();
                if (next.b(i) && !next.H()) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    void c(a.b bVar) {
        if (bVar.K()) {
            return;
        }
        synchronized (this.a) {
            if (this.a.contains(bVar)) {
                com.kwai.filedownloader.e.d.d(this, "already has %s", bVar);
            } else {
                bVar.L();
                this.a.add(bVar);
                if (com.kwai.filedownloader.e.d.a) {
                    com.kwai.filedownloader.e.d.e(this, "add list in all %s %d %d", bVar, Byte.valueOf(bVar.F().v()), Integer.valueOf(this.a.size()));
                }
            }
        }
    }
}
