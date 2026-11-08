package com.kwai.filedownloader.download;

import com.kwai.filedownloader.download.c;
import com.kwai.filedownloader.download.e;
import com.kwai.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.kwai.filedownloader.exception.FileDownloadHttpException;
import com.kwai.filedownloader.exception.FileDownloadNetworkPolicyException;
import com.kwai.filedownloader.exception.FileDownloadOutOfSpaceException;
import com.kwai.filedownloader.y;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownloadLaunchRunnable implements f, Runnable {
    private static final ThreadPoolExecutor o = com.kwai.filedownloader.e.b.a("ConnectionBlock");
    private long A;
    int a;
    private final d b;
    private final int c;
    private final com.kwai.filedownloader.c.c d;
    private final com.kwai.filedownloader.c.b e;
    private final boolean f;
    private final boolean g;
    private final com.kwai.filedownloader.a.a h;
    private final y i;
    private boolean j;
    private final boolean k;
    private final ArrayList<c> l;
    private e m;
    private boolean n;
    private boolean p;
    private boolean q;
    private boolean r;
    private final AtomicBoolean s;
    private volatile boolean t;
    private volatile boolean u;
    private volatile Exception v;
    private String w;
    private long x;
    private long y;
    private long z;

    class DiscardSafely extends Throwable {
        private static final long serialVersionUID = 4243896780616180062L;

        DiscardSafely() {
        }
    }

    class RetryDirectly extends Throwable {
        private static final long serialVersionUID = -4127585119566978768L;

        RetryDirectly() {
        }
    }

    public static class a {
        private com.kwai.filedownloader.c.c a;
        private com.kwai.filedownloader.c.b b;
        private y c;
        private Integer d;
        private Integer e;
        private Boolean f;
        private Boolean g;
        private Integer h;

        public a a(com.kwai.filedownloader.c.b bVar) {
            this.b = bVar;
            return this;
        }

        public a a(com.kwai.filedownloader.c.c cVar) {
            this.a = cVar;
            return this;
        }

        public a a(y yVar) {
            this.c = yVar;
            return this;
        }

        public a a(Boolean bool) {
            this.f = bool;
            return this;
        }

        public a a(Integer num) {
            this.d = num;
            return this;
        }

        public DownloadLaunchRunnable a() {
            if (this.a == null || this.c == null || this.d == null || this.e == null || this.f == null || this.g == null || this.h == null) {
                throw new IllegalArgumentException();
            }
            return new DownloadLaunchRunnable(this.a, this.b, this.c, this.d.intValue(), this.e.intValue(), this.f.booleanValue(), this.g.booleanValue(), this.h.intValue());
        }

        public a b(Boolean bool) {
            this.g = bool;
            return this;
        }

        public a b(Integer num) {
            this.e = num;
            return this;
        }

        public a c(Integer num) {
            this.h = num;
            return this;
        }
    }

    private DownloadLaunchRunnable(com.kwai.filedownloader.c.c cVar, com.kwai.filedownloader.c.b bVar, y yVar, int i, int i2, boolean z, boolean z2, int i3) {
        this.c = 5;
        this.l = new ArrayList<>(5);
        this.x = 0L;
        this.y = 0L;
        this.z = 0L;
        this.A = 0L;
        this.s = new AtomicBoolean(true);
        this.t = false;
        this.j = false;
        this.d = cVar;
        this.e = bVar;
        this.f = z;
        this.g = z2;
        this.h = b.a().c();
        this.k = b.a().e();
        this.i = yVar;
        this.a = i3;
        this.b = new d(cVar, i3, i, i2);
    }

    private com.kwai.filedownloader.download.a a(List<com.kwai.filedownloader.c.a> list) {
        long jG;
        long j;
        int iN = this.d.n();
        String strE = this.d.e();
        String strD = this.d.d();
        boolean z = iN > 1;
        if ((!z || this.k) && com.kwai.filedownloader.e.f.a(this.d.a(), this.d)) {
            if (!this.k) {
                jG = new File(strE).length();
            } else if (z) {
                if (iN == list.size()) {
                    jG = com.kwai.filedownloader.c.a.a(list);
                }
                j = 0;
            } else {
                jG = this.d.g();
            }
            j = jG;
        } else {
            j = 0;
        }
        this.d.a(j);
        this.p = j > 0;
        if (!this.p) {
            this.h.d(this.d.a());
            com.kwai.filedownloader.e.f.c(strD, strE);
        }
        return new com.kwai.filedownloader.download.a(0L, j, 0L, this.d.h() - j);
    }

    private void a(int i, List<com.kwai.filedownloader.c.a> list) {
        if (i <= 1 || list.size() != i) {
            throw new IllegalArgumentException();
        }
        a(list, this.d.h());
    }

    private void a(long j, int i) {
        long j2 = j / i;
        int iA = this.d.a();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        long j3 = 0;
        while (i2 < i) {
            long j4 = i2 == i + (-1) ? 0L : (j3 + j2) - 1;
            com.kwai.filedownloader.c.a aVar = new com.kwai.filedownloader.c.a();
            aVar.a(iA);
            aVar.b(i2);
            aVar.a(j3);
            aVar.b(j3);
            aVar.c(j4);
            arrayList.add(aVar);
            this.h.a(aVar);
            j3 += j2;
            i2++;
        }
        this.d.b(i);
        this.h.a(iA, i);
        a(arrayList, j);
    }

    private void a(long j, String str) throws Throwable {
        com.kwai.filedownloader.d.a aVarL;
        if (j != -1) {
            try {
                aVarL = com.kwai.filedownloader.e.f.l(this.d.e());
                try {
                    long length = new File(str).length();
                    long j2 = j - length;
                    long jF = com.kwai.filedownloader.e.f.f(str);
                    if (jF < j2) {
                        throw new FileDownloadOutOfSpaceException(jF, j2, length);
                    }
                    if (!com.kwai.filedownloader.e.e.a().f) {
                        aVarL.b(j);
                    }
                } catch (Throwable th) {
                    th = th;
                    if (aVarL != null) {
                        aVarL.b();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                aVarL = null;
            }
        } else {
            aVarL = null;
        }
        if (aVarL != null) {
            aVarL.b();
        }
    }

    private void a(com.kwai.filedownloader.download.a aVar, com.kwai.filedownloader.kwai.b bVar) throws Throwable {
        if (!this.q) {
            this.d.a(0L);
            aVar = new com.kwai.filedownloader.download.a(0L, 0L, aVar.c, aVar.d);
        }
        e.a aVar2 = new e.a();
        aVar2.a(this).b(this.d.a()).a(-1).a(this.g).a(bVar).a(aVar).a(this.d.e());
        this.d.b(1);
        this.h.a(this.d.a(), 1);
        this.m = aVar2.a();
        if (!this.t) {
            this.m.b();
        } else {
            this.d.a((byte) -2);
            this.m.a();
        }
    }

    private void a(List<com.kwai.filedownloader.c.a> list, long j) {
        int iA = this.d.a();
        String strJ = this.d.j();
        String strB = this.w != null ? this.w : this.d.b();
        String strE = this.d.e();
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "fetch data with multiple connection(count: [%d]) for task[%d] totalLength[%d]", Integer.valueOf(list.size()), Integer.valueOf(iA), Long.valueOf(j));
        }
        boolean z = this.p;
        long j2 = 0;
        long jD = 0;
        for (com.kwai.filedownloader.c.a aVar : list) {
            long jD2 = aVar.e() == j2 ? j - aVar.d() : (aVar.e() - aVar.d()) + 1;
            jD += aVar.d() - aVar.c();
            if (jD2 != j2) {
                c cVarA = new c.a().a(iA).a(Integer.valueOf(aVar.b())).a(this).a(strB).b(z ? strJ : null).a(this.e).a(this.g).a(new com.kwai.filedownloader.download.a(aVar.c(), aVar.d(), aVar.e(), jD2)).c(strE).a();
                if (com.kwai.filedownloader.e.d.a) {
                    com.kwai.filedownloader.e.d.c(this, "enable multiple connection: %s", aVar);
                }
                if (cVarA == null) {
                    throw new IllegalArgumentException("the download runnable must not be null!");
                }
                this.l.add(cVarA);
            } else if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "pass connection[%d-%d], because it has been completed", Integer.valueOf(aVar.a()), Integer.valueOf(aVar.b()));
            }
            j2 = 0;
        }
        if (jD != this.d.g()) {
            com.kwai.filedownloader.e.d.d(this, "correct the sofar[%d] from connection table[%d]", Long.valueOf(this.d.g()), Long.valueOf(jD));
            this.d.a(jD);
        }
        ArrayList arrayList = new ArrayList(this.l.size());
        Iterator<c> it = this.l.iterator();
        while (it.hasNext()) {
            c next = it.next();
            if (this.t) {
                next.a();
            } else {
                arrayList.add(Executors.callable(next));
            }
        }
        if (this.t) {
            this.d.a((byte) -2);
            return;
        }
        List<Future> listInvokeAll = o.invokeAll(arrayList);
        if (com.kwai.filedownloader.e.d.a) {
            for (Future future : listInvokeAll) {
                com.kwai.filedownloader.e.d.c(this, "finish sub-task for [%d] %B %B", Integer.valueOf(iA), Boolean.valueOf(future.isDone()), Boolean.valueOf(future.isCancelled()));
            }
        }
    }

    private void a(Map<String, List<String>> map, ConnectTask connectTask, com.kwai.filedownloader.kwai.b bVar) throws FileDownloadHttpException, RetryDirectly {
        int iA = this.d.a();
        int iE = bVar.e();
        this.q = iE == 206 || iE == 1;
        boolean z = iE == 200 || iE == 201 || iE == 0;
        String strJ = this.d.j();
        String strA = com.kwai.filedownloader.e.f.a(iA, bVar);
        if (!(iE == 412 || !(strJ == null || strJ.equals(strA) || (!z && !this.q)) || ((iE == 201 && connectTask.b()) || (iE == 416 && this.d.g() > 0)))) {
            this.w = connectTask.c();
            if (!this.q && !z) {
                throw new FileDownloadHttpException(iE, map, bVar.c());
            }
            long jB = com.kwai.filedownloader.e.f.b(iA, bVar);
            String strA2 = this.d.l() ? com.kwai.filedownloader.e.f.a(bVar, this.d.b()) : null;
            this.r = jB == -1;
            this.b.a(this.p && this.q, !this.r ? this.d.g() + jB : jB, strA, strA2);
            return;
        }
        if (this.p) {
            com.kwai.filedownloader.e.d.d(this, "there is precondition failed on this request[%d] with old etag[%s]、new etag[%s]、response requestHttpCode is %d", Integer.valueOf(iA), strJ, strA, Integer.valueOf(iE));
        }
        this.h.d(this.d.a());
        com.kwai.filedownloader.e.f.c(this.d.d(), this.d.e());
        this.p = false;
        if (strJ != null && strJ.equals(strA)) {
            com.kwai.filedownloader.e.d.d(this, "the old etag[%s] is the same to the new etag[%s], but the response status requestHttpCode is %d not Partial(206), so wo have to start this task from very beginning for task[%d]!", strJ, strA, Integer.valueOf(iE), Integer.valueOf(iA));
            strA = null;
        }
        this.d.a(0L);
        this.d.c(0L);
        this.d.b(strA);
        this.d.o();
        this.h.a(iA, this.d.j(), this.d.g(), this.d.h(), this.d.n());
        throw new RetryDirectly();
    }

    private boolean g() {
        return (!this.p || this.d.n() > 1) && this.q && this.k && !this.r;
    }

    private void h() {
        if (this.g && !com.kwai.filedownloader.e.f.j("android.permission.ACCESS_NETWORK_STATE")) {
            throw new FileDownloadGiveUpRetryException(com.kwai.filedownloader.e.f.a("Task[%d] can't start the download runnable, because this task require wifi, but user application nor current process has %s, so we can't check whether the network type connection.", Integer.valueOf(this.d.a()), "android.permission.ACCESS_NETWORK_STATE"));
        }
        if (this.g && com.kwai.filedownloader.e.f.d()) {
            throw new FileDownloadNetworkPolicyException();
        }
    }

    private void i() throws DiscardSafely, RetryDirectly {
        int iA = this.d.a();
        if (this.d.l()) {
            String strD = this.d.d();
            int iB = com.kwai.filedownloader.e.f.b(this.d.b(), strD);
            if (com.kwai.filedownloader.e.c.a(iA, strD, this.f, false)) {
                this.h.e(iA);
                this.h.d(iA);
                throw new DiscardSafely();
            }
            com.kwai.filedownloader.c.c cVarB = this.h.b(iB);
            if (cVarB != null) {
                if (com.kwai.filedownloader.e.c.a(iA, cVarB, this.i, false)) {
                    this.h.e(iA);
                    this.h.d(iA);
                    throw new DiscardSafely();
                }
                List<com.kwai.filedownloader.c.a> listC = this.h.c(iB);
                this.h.e(iB);
                this.h.d(iB);
                com.kwai.filedownloader.e.f.n(this.d.d());
                if (com.kwai.filedownloader.e.f.a(iB, cVarB)) {
                    this.d.a(cVarB.g());
                    this.d.c(cVarB.h());
                    this.d.b(cVarB.j());
                    this.d.b(cVarB.n());
                    this.h.a(this.d);
                    if (listC != null) {
                        for (com.kwai.filedownloader.c.a aVar : listC) {
                            aVar.a(iA);
                            this.h.a(aVar);
                        }
                    }
                    throw new RetryDirectly();
                }
            }
            if (com.kwai.filedownloader.e.c.a(iA, this.d.g(), this.d.e(), strD, this.i)) {
                this.h.e(iA);
                this.h.d(iA);
                throw new DiscardSafely();
            }
        }
    }

    public void a() {
        this.t = true;
        if (this.m != null) {
            this.m.a();
        }
        Iterator it = ((ArrayList) this.l.clone()).iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar != null) {
                cVar.a();
            }
        }
    }

    @Override // com.kwai.filedownloader.download.f
    public void a(long j) {
        if (this.t) {
            return;
        }
        this.b.a(j);
    }

    @Override // com.kwai.filedownloader.download.f
    public void a(c cVar, long j, long j2) {
        if (this.t) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "the task[%d] has already been paused, so pass the completed callback", Integer.valueOf(this.d.a()));
                return;
            }
            return;
        }
        int i = cVar == null ? -1 : cVar.a;
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "the connection has been completed(%d): [%d, %d)  %d", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), Long.valueOf(this.d.h()));
        }
        if (!this.n) {
            synchronized (this.l) {
                this.l.remove(cVar);
            }
        } else {
            if (j == 0 || j2 == this.d.h()) {
                return;
            }
            com.kwai.filedownloader.e.d.a(this, "the single task not completed corrected(%d, %d != %d) for task(%d)", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(this.d.h()), Integer.valueOf(this.d.a()));
        }
    }

    @Override // com.kwai.filedownloader.download.f
    public void a(Exception exc, long j) {
        if (this.t) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "the task[%d] has already been paused, so pass the retry callback", Integer.valueOf(this.d.a()));
            }
        } else {
            int i = this.a;
            this.a = i - 1;
            if (i < 0) {
                com.kwai.filedownloader.e.d.a(this, "valid retry times is less than 0(%d) for download task(%d)", Integer.valueOf(this.a), Integer.valueOf(this.d.a()));
            }
            this.b.a(exc, this.a, j);
        }
    }

    @Override // com.kwai.filedownloader.download.f
    public boolean a(Exception exc) {
        if (exc instanceof FileDownloadHttpException) {
            int code = ((FileDownloadHttpException) exc).getCode();
            if (this.n && code == 416 && !this.j) {
                com.kwai.filedownloader.e.f.c(this.d.d(), this.d.e());
                this.j = true;
                return true;
            }
        }
        return this.a > 0 && !(exc instanceof FileDownloadGiveUpRetryException);
    }

    public void b() {
        if (this.d.n() > 1) {
            List<com.kwai.filedownloader.c.a> listC = this.h.c(this.d.a());
            if (this.d.n() == listC.size()) {
                this.d.a(com.kwai.filedownloader.c.a.a(listC));
            } else {
                this.d.a(0L);
                this.h.d(this.d.a());
            }
        }
        this.b.c();
    }

    @Override // com.kwai.filedownloader.download.f
    public void b(Exception exc) {
        this.u = true;
        this.v = exc;
        if (this.t) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "the task[%d] has already been paused, so pass the error callback", Integer.valueOf(this.d.a()));
            }
        } else {
            Iterator it = ((ArrayList) this.l.clone()).iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar != null) {
                    cVar.b();
                }
            }
        }
    }

    @Override // com.kwai.filedownloader.download.f
    public void c() {
        this.h.a(this.d.a(), this.d.g());
    }

    public int d() {
        return this.d.a();
    }

    public boolean e() {
        return this.s.get() || this.b.a();
    }

    public String f() {
        return this.d.e();
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x01f8, code lost:
    
        throw new java.lang.IllegalAccessException(com.kwai.filedownloader.e.f.a("invalid connection count %d, the connection count must be larger than 0", r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009e, code lost:
    
        if (com.kwai.filedownloader.e.d.a == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a0, code lost:
    
        com.kwai.filedownloader.e.d.c(r17, "High concurrent cause, start runnable but already paused %d", java.lang.Integer.valueOf(r17.d.a()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b3, code lost:
    
        r17.b.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ba, code lost:
    
        if (r17.t == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00bf, code lost:
    
        if (r17.u == false) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c2, code lost:
    
        r17.b.g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c8, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0122, code lost:
    
        if (r17.t == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0124, code lost:
    
        r17.d.a((byte) -2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0129, code lost:
    
        if (r7 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x012b, code lost:
    
        r7.f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x012e, code lost:
    
        r17.b.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0135, code lost:
    
        if (r17.t == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x013b, code lost:
    
        if (r17.u == false) goto L178;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x013f, code lost:
    
        r17.b.g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0146, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0149, code lost:
    
        i();
        r13 = r17.d.h();
        a(r13, r17.d.e());
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x015f, code lost:
    
        if (g() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0163, code lost:
    
        if (r17.p == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0165, code lost:
    
        r8 = r17.d.n();
        r15 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x016d, code lost:
    
        r15 = r13;
        r8 = com.kwai.filedownloader.download.b.a().a(r17.d.a(), r17.d.b(), r17.d.c(), r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0189, code lost:
    
        r15 = r13;
        r8 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x018b, code lost:
    
        if (r8 <= 0) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x018f, code lost:
    
        if (r17.t == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0191, code lost:
    
        r17.d.a((byte) -2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0196, code lost:
    
        if (r7 == null) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0198, code lost:
    
        r7.f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x019b, code lost:
    
        r17.b.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01a2, code lost:
    
        if (r17.t == false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01a8, code lost:
    
        if (r17.u == false) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01ac, code lost:
    
        r17.b.g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01b3, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01b6, code lost:
    
        if (r8 != 1) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01b8, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01ba, code lost:
    
        r9 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01bb, code lost:
    
        r17.n = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01bf, code lost:
    
        if (r17.n == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01c1, code lost:
    
        a(r6.e(), r7);
        r5 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01ca, code lost:
    
        if (r7 == null) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01cc, code lost:
    
        r7.f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01d0, code lost:
    
        r5 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01d1, code lost:
    
        r17.b.e();
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01d8, code lost:
    
        if (r17.p == false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01da, code lost:
    
        a(r8, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01de, code lost:
    
        a(r15, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01e2, code lost:
    
        if (r5 == null) goto L135;
     */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0277 A[Catch: all -> 0x027b, TRY_ENTER, TryCatch #15 {all -> 0x027b, blocks: (B:3:0x0005, B:5:0x0012, B:7:0x001a, B:9:0x001e, B:10:0x0032, B:23:0x008f, B:25:0x0093, B:26:0x0098, B:28:0x009c, B:30:0x00a0, B:41:0x00ca, B:50:0x012b, B:73:0x0198, B:99:0x01e4, B:148:0x0277, B:149:0x027a, B:112:0x020d, B:115:0x0215), top: B:182:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0075  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 682
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.filedownloader.download.DownloadLaunchRunnable.run():void");
    }
}
