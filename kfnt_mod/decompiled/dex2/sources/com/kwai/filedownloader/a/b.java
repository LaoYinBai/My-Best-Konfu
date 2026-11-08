package com.kwai.filedownloader.a;

import android.util.SparseArray;
import com.kwai.filedownloader.a.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements com.kwai.filedownloader.a.a {
    final SparseArray<com.kwai.filedownloader.c.c> a = new SparseArray<>();
    final SparseArray<List<com.kwai.filedownloader.c.a>> b = new SparseArray<>();

    class a implements a.InterfaceC0090a {
        a() {
        }

        @Override // com.kwai.filedownloader.a.a.InterfaceC0090a
        public void a() {
        }

        @Override // com.kwai.filedownloader.a.a.InterfaceC0090a
        public void a(int i, com.kwai.filedownloader.c.c cVar) {
        }

        @Override // com.kwai.filedownloader.a.a.InterfaceC0090a
        public void a(com.kwai.filedownloader.c.c cVar) {
        }

        @Override // com.kwai.filedownloader.a.a.InterfaceC0090a
        public void b(com.kwai.filedownloader.c.c cVar) {
        }

        @Override // java.lang.Iterable
        public Iterator<com.kwai.filedownloader.c.c> iterator() {
            return b.this.new C0091b();
        }
    }

    /* renamed from: com.kwai.filedownloader.a.b$b, reason: collision with other inner class name */
    class C0091b implements Iterator<com.kwai.filedownloader.c.c> {
        C0091b() {
        }

        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public com.kwai.filedownloader.c.c next() {
            return null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public void remove() {
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public void a() {
        synchronized (this.a) {
            this.a.clear();
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i) {
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, int i2) {
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, int i2, long j) {
        List<com.kwai.filedownloader.c.a> list;
        synchronized (this.b) {
            list = this.b.get(i);
        }
        if (list == null) {
            return;
        }
        for (com.kwai.filedownloader.c.a aVar : list) {
            if (aVar.b() == i2) {
                aVar.b(j);
                return;
            }
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, long j) {
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, long j, String str, String str2) {
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, String str, long j, long j2, int i2) {
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, Throwable th) {
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, Throwable th, long j) {
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(com.kwai.filedownloader.c.a aVar) {
        List<com.kwai.filedownloader.c.a> arrayList;
        try {
            int iA = aVar.a();
            synchronized (this.b) {
                arrayList = this.b.get(iA);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.b.put(iA, arrayList);
                }
            }
            arrayList.add(aVar);
        } catch (Exception unused) {
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(com.kwai.filedownloader.c.c cVar) {
        if (cVar == null) {
            com.kwai.filedownloader.e.d.d(this, "update but model == null!", new Object[0]);
            return;
        }
        if (b(cVar.a()) == null) {
            b(cVar);
            return;
        }
        synchronized (this.a) {
            this.a.remove(cVar.a());
            this.a.put(cVar.a(), cVar);
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public a.InterfaceC0090a b() {
        return new a();
    }

    @Override // com.kwai.filedownloader.a.a
    public com.kwai.filedownloader.c.c b(int i) {
        com.kwai.filedownloader.c.c cVar;
        synchronized (this.a) {
            cVar = this.a.get(i);
        }
        return cVar;
    }

    @Override // com.kwai.filedownloader.a.a
    public void b(int i, long j) {
        e(i);
    }

    public void b(com.kwai.filedownloader.c.c cVar) {
        synchronized (this.a) {
            this.a.put(cVar.a(), cVar);
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public List<com.kwai.filedownloader.c.a> c(int i) {
        List<com.kwai.filedownloader.c.a> list;
        try {
            ArrayList arrayList = new ArrayList();
            synchronized (this.b) {
                list = this.b.get(i);
            }
            if (list != null) {
                arrayList.addAll(list);
            }
            return arrayList;
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public void c(int i, long j) {
    }

    @Override // com.kwai.filedownloader.a.a
    public void d(int i) {
        try {
            synchronized (this.b) {
                this.b.remove(i);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public boolean e(int i) {
        synchronized (this.a) {
            this.a.remove(i);
        }
        return true;
    }

    @Override // com.kwai.filedownloader.a.a
    public void f(int i) {
    }
}
