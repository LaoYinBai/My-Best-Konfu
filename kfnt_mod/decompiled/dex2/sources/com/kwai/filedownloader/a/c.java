package com.kwai.filedownloader.a;

import android.database.SQLException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.kwai.filedownloader.a.a;
import com.kwai.filedownloader.e.f;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements a {
    private Handler c;
    private volatile Thread g;
    private volatile List<Integer> e = new CopyOnWriteArrayList();
    private AtomicInteger f = new AtomicInteger();
    private final b a = new b();
    private final d b = new d();
    private final long d = com.kwai.filedownloader.e.e.a().b;

    public c() {
        HandlerThread handlerThread = new HandlerThread(f.i("RemitHandoverToDB"), 10);
        handlerThread.start();
        this.c = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.kwai.filedownloader.a.c.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    if (c.this.g != null) {
                        LockSupport.unpark(c.this.g);
                        c.this.g = null;
                    }
                    return false;
                }
                try {
                    c.this.f.set(i);
                    c.this.g(i);
                    c.this.e.add(Integer.valueOf(i));
                    return false;
                } finally {
                    c.this.f.set(0);
                    if (c.this.g != null) {
                        LockSupport.unpark(c.this.g);
                        c.this.g = null;
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i) throws SQLException {
        this.b.a(this.a.b(i));
        List<com.kwai.filedownloader.c.a> listC = this.a.c(i);
        this.b.d(i);
        Iterator<com.kwai.filedownloader.c.a> it = listC.iterator();
        while (it.hasNext()) {
            this.b.a(it.next());
        }
    }

    private boolean h(int i) {
        return !this.e.contains(Integer.valueOf(i));
    }

    private void i(int i) throws SQLException {
        this.c.removeMessages(i);
        if (this.f.get() != i) {
            g(i);
            return;
        }
        this.g = Thread.currentThread();
        this.c.sendEmptyMessage(0);
        LockSupport.park();
    }

    @Override // com.kwai.filedownloader.a.a
    public void a() throws SQLException {
        this.a.a();
        this.b.a();
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i) {
        this.c.sendEmptyMessageDelayed(i, this.d);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, int i2) throws SQLException {
        this.a.a(i, i2);
        if (h(i)) {
            return;
        }
        this.b.a(i, i2);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, int i2, long j) throws SQLException {
        this.a.a(i, i2, j);
        if (h(i)) {
            return;
        }
        this.b.a(i, i2, j);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, long j) throws SQLException {
        this.a.a(i, j);
        if (h(i)) {
            return;
        }
        this.b.a(i, j);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, long j, String str, String str2) throws SQLException {
        this.a.a(i, j, str, str2);
        if (h(i)) {
            return;
        }
        this.b.a(i, j, str, str2);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, String str, long j, long j2, int i2) throws SQLException {
        this.a.a(i, str, j, j2, i2);
        if (h(i)) {
            return;
        }
        this.b.a(i, str, j, j2, i2);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, Throwable th) throws SQLException {
        this.a.a(i, th);
        if (h(i)) {
            return;
        }
        this.b.a(i, th);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, Throwable th, long j) throws SQLException {
        this.a.a(i, th, j);
        if (h(i)) {
            i(i);
        }
        this.b.a(i, th, j);
        this.e.remove(Integer.valueOf(i));
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(com.kwai.filedownloader.c.a aVar) throws SQLException {
        this.a.a(aVar);
        if (h(aVar.a())) {
            return;
        }
        this.b.a(aVar);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(com.kwai.filedownloader.c.c cVar) throws SQLException {
        this.a.a(cVar);
        if (h(cVar.a())) {
            return;
        }
        this.b.a(cVar);
    }

    @Override // com.kwai.filedownloader.a.a
    public a.InterfaceC0090a b() {
        return this.b.a(this.a.a, this.a.b);
    }

    @Override // com.kwai.filedownloader.a.a
    public com.kwai.filedownloader.c.c b(int i) {
        return this.a.b(i);
    }

    @Override // com.kwai.filedownloader.a.a
    public void b(int i, long j) {
        this.a.b(i, j);
        if (h(i)) {
            this.c.removeMessages(i);
            if (this.f.get() == i) {
                this.g = Thread.currentThread();
                this.c.sendEmptyMessage(0);
                LockSupport.park();
                this.b.b(i, j);
            }
        } else {
            this.b.b(i, j);
        }
        this.e.remove(Integer.valueOf(i));
    }

    @Override // com.kwai.filedownloader.a.a
    public List<com.kwai.filedownloader.c.a> c(int i) {
        return this.a.c(i);
    }

    @Override // com.kwai.filedownloader.a.a
    public void c(int i, long j) throws SQLException {
        this.a.c(i, j);
        if (h(i)) {
            i(i);
        }
        this.b.c(i, j);
        this.e.remove(Integer.valueOf(i));
    }

    @Override // com.kwai.filedownloader.a.a
    public void d(int i) throws SQLException {
        this.a.d(i);
        if (h(i)) {
            return;
        }
        this.b.d(i);
    }

    @Override // com.kwai.filedownloader.a.a
    public boolean e(int i) {
        this.b.e(i);
        return this.a.e(i);
    }

    @Override // com.kwai.filedownloader.a.a
    public void f(int i) {
        this.a.f(i);
        if (h(i)) {
            return;
        }
        this.b.f(i);
    }
}
