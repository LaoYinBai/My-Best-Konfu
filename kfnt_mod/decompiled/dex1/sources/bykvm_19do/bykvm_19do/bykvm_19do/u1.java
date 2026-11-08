package bykvm_19do.bykvm_19do.bykvm_19do;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.bytedance.mapplog.collector.Collector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class u1 implements Handler.Callback, Comparator<u> {
    private static u1 l;

    /* renamed from: a, reason: collision with root package name */
    private k1 f1391a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1392b;

    /* renamed from: c, reason: collision with root package name */
    public Application f1393c;
    private i2 d;
    private final ArrayList<u> e = new ArrayList<>(32);
    private v f;
    private j2 g;
    private Handler h;
    private a2 i;
    private com.bytedance.mapplog.util.a j;
    private Handler k;

    private u1() {
    }

    public static void h() {
        u1 u1Var = l;
        if (u1Var != null) {
            u1Var.b(null);
        }
    }

    public static u1 i() {
        if (l == null) {
            synchronized (u1.class) {
                if (l == null) {
                    l = new u1();
                }
            }
        }
        return l;
    }

    public static String j() {
        a2 a2Var;
        u1 u1Var = l;
        if (u1Var == null || (a2Var = u1Var.i) == null) {
            return null;
        }
        return a2Var.e;
    }

    private void k() {
        if (this.d.K()) {
            if (this.f1391a == null) {
                this.f1391a = new k1(this);
                this.h.obtainMessage(6, this.f1391a).sendToTarget();
                return;
            }
            return;
        }
        k1 k1Var = this.f1391a;
        if (k1Var != null) {
            k1Var.f();
            this.f1391a = null;
        }
    }

    private void l() {
        if (l0.f1356a) {
            l0.a("packAndSend once, " + this.i.e + ", hadUI:" + this.i.b(), null);
        }
        Handler handler = this.h;
        if (handler != null) {
            handler.sendMessage(this.k.obtainMessage(6, new v1(this)));
            this.h.sendMessage(this.k.obtainMessage(6, new w1(this)));
        }
    }

    public Context a() {
        return this.f1393c;
    }

    public v b() {
        return this.f;
    }

    public i2 c() {
        return this.d;
    }

    public j2 d() {
        return this.g;
    }

    public a2 e() {
        return this.i;
    }

    public com.bytedance.mapplog.util.a f() {
        if (this.j == null) {
            this.j = this.d.o().t();
            if (this.j == null) {
                this.j = com.bytedance.mapplog.util.b.a(0);
            }
        }
        return this.j;
    }

    public boolean g() {
        this.f1392b = true;
        r1 r1Var = new r1(this);
        Handler handler = this.h;
        if (handler == null) {
            return false;
        }
        handler.obtainMessage(6, r1Var).sendToTarget();
        return true;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.d.L();
                if (!this.g.e()) {
                    this.k.removeMessages(1);
                    this.k.sendEmptyMessageDelayed(1, 1000L);
                } else if (this.d.G()) {
                    HandlerThread handlerThread = new HandlerThread("bd_tracker_n");
                    handlerThread.start();
                    this.h = new Handler(handlerThread.getLooper(), this);
                    this.h.sendEmptyMessage(2);
                    if (this.e.size() > 0) {
                        this.k.removeMessages(4);
                        this.k.sendEmptyMessageDelayed(4, 1000L);
                    }
                    l0.d("net|worker start", null);
                }
                c2.a();
                return true;
            case 2:
                ArrayList arrayList = new ArrayList(4);
                arrayList.add(new y1(this));
                arrayList.add(new t1(this));
                arrayList.add(new x1(this));
                arrayList.add(new z1(this));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    s1 s1Var = (s1) it.next();
                    long jH = s1Var.h();
                    if (jH < 864000000) {
                        this.h.sendMessageDelayed(this.k.obtainMessage(6, s1Var), jH);
                    }
                }
                k();
                return true;
            case 3:
            default:
                l0.a(null);
                return true;
            case 4:
                b(null);
                return true;
            case 5:
                b((String[]) message.obj);
                return true;
            case 6:
                s1 s1Var2 = (s1) message.obj;
                if (!s1Var2.g()) {
                    long jH2 = s1Var2.h();
                    if (jH2 < 864000000) {
                        this.h.sendMessageDelayed(this.k.obtainMessage(6, s1Var2), jH2);
                    }
                    k();
                }
                return true;
            case 7:
                synchronized (this.e) {
                    this.e.add(a2.d());
                }
                b(null);
                return true;
        }
    }

    private void b(String[] strArr) {
        ArrayList<u> arrayList;
        synchronized (this.e) {
            arrayList = (ArrayList) this.e.clone();
            this.e.clear();
        }
        int i = 0;
        if (strArr != null) {
            arrayList.ensureCapacity(arrayList.size() + strArr.length);
            for (String str : strArr) {
                arrayList.add(u.a(str));
            }
        }
        boolean zA = this.d.a(arrayList);
        if (arrayList.size() > 0) {
            if (!this.d.G()) {
                Intent intent = new Intent(this.f1393c, (Class<?>) Collector.class);
                int size = arrayList.size();
                String[] strArr2 = new String[size];
                int length = 0;
                while (i < size) {
                    strArr2[i] = arrayList.get(i).e().toString();
                    length += strArr2[i].length();
                    i++;
                }
                if (length >= 307200) {
                    l0.a(null);
                }
                intent.putExtra("EMBED_K_DATA", strArr2);
                try {
                    this.f1393c.sendBroadcast(intent);
                    return;
                } catch (Exception e) {
                    l0.a(e);
                    return;
                }
            }
            if (!zA && arrayList.size() <= 100) {
                synchronized (this.e) {
                    this.e.addAll(arrayList);
                }
                return;
            }
            Collections.sort(arrayList, this);
            ArrayList<u> arrayList2 = new ArrayList<>(arrayList.size());
            Iterator<u> it = arrayList.iterator();
            boolean zB = false;
            while (it.hasNext()) {
                u next = it.next();
                if (this.i.a(next, arrayList2)) {
                    l();
                }
                if (next instanceof b0) {
                    zB = a2.b(next);
                    i = 1;
                }
            }
            if (i != 0) {
                if (zB) {
                    this.k.removeMessages(7);
                } else {
                    this.k.sendEmptyMessageDelayed(7, this.d.M());
                }
            }
            this.f.a(arrayList2);
            if (this.f1392b || !this.i.b() || this.h == null || !a.e()) {
                return;
            }
            g();
        }
    }

    public void a(Application application, i2 i2Var, j2 j2Var, j0 j0Var) {
        this.f1393c = application;
        this.f = new v(application, j2Var, i2Var);
        this.d = i2Var;
        this.g = j2Var;
        this.i = new a2(this.g, this.d);
        this.f1393c.registerActivityLifecycleCallbacks(j0Var);
        HandlerThread handlerThread = new HandlerThread("bd_tracker_w");
        handlerThread.start();
        this.k = new Handler(handlerThread.getLooper(), this);
        this.k.sendEmptyMessage(1);
        k0.a(i2Var.x() != 0);
    }

    public static void a(u uVar) {
        int size;
        Handler handler;
        u1 u1Var = l;
        if (u1Var == null) {
            l0.b("Init comes First!", null);
            c2.a(uVar);
            return;
        }
        if (uVar.f1385a == 0) {
            l0.a(null);
        }
        if (uVar instanceof c0) {
            ((c0) uVar).k = u1Var.d.x();
        }
        synchronized (u1Var.e) {
            size = u1Var.e.size();
            u1Var.e.add(uVar);
        }
        if (size % 10 != 0 || (handler = u1Var.k) == null) {
            return;
        }
        handler.removeMessages(4);
        u1Var.k.sendEmptyMessageDelayed(4, size == 0 ? 500L : 250L);
    }

    public static void a(String[] strArr) {
        u1 u1Var = l;
        if (u1Var == null) {
            l0.a(new RuntimeException("Init comes First!"));
            return;
        }
        Handler handler = u1Var.k;
        if (handler != null) {
            handler.removeMessages(4);
            u1Var.k.obtainMessage(5, strArr).sendToTarget();
        }
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(u uVar, u uVar2) {
        long j = uVar.f1385a - uVar2.f1385a;
        if (j < 0) {
            return -1;
        }
        return j > 0 ? 1 : 0;
    }
}
