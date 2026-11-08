package com.xiaomi.push;

import android.os.SystemClock;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.xiaomi.push.service.XMPushService;
import java.io.IOException;
import java.net.Socket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class fw extends fp {
    protected Exception a;

    /* renamed from: a, reason: collision with other field name */
    protected Socket f411a;
    protected XMPushService b;
    private int c;

    /* renamed from: c, reason: collision with other field name */
    String f412c;
    private String d;
    protected volatile long e;
    protected volatile long f;
    protected volatile long g;

    public fw(XMPushService xMPushService, fq fqVar) {
        super(xMPushService, fqVar);
        this.a = null;
        this.f412c = null;
        this.e = 0L;
        this.f = 0L;
        this.g = 0L;
        this.b = xMPushService;
    }

    private void a(fq fqVar) throws Throwable {
        a(fqVar.c(), fqVar.m336a());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02fc  */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(java.lang.String r27, int r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 774
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.fw.a(java.lang.String, int):void");
    }

    cq a(String str) {
        cq cqVarA = cu.a().a(str, false);
        if (!cqVarA.b()) {
            gs.a(new fz(this, str));
        }
        return cqVarA;
    }

    @Override // com.xiaomi.push.fp
    /* renamed from: a */
    public String mo331a() {
        return this.d;
    }

    public Socket a() {
        return new Socket();
    }

    /* renamed from: a, reason: collision with other method in class */
    protected synchronized void mo341a() {
    }

    protected synchronized void a(int i, Exception exc) {
        if (b() == 2) {
            return;
        }
        a(2, i, exc);
        this.f397a = "";
        try {
            this.f411a.close();
        } catch (Throwable unused) {
        }
        this.e = 0L;
        this.f = 0L;
    }

    protected void a(Exception exc) {
        if (SystemClock.elapsedRealtime() - this.g < 300000) {
            if (!bg.b(this.b)) {
                return;
            }
            this.c++;
            if (this.c < 2) {
                return;
            }
            String strMo331a = mo331a();
            com.xiaomi.channel.commonutils.logger.b.m22a("max short conn time reached, sink down current host:" + strMo331a);
            a(strMo331a, 0L, exc);
        }
        this.c = 0;
    }

    protected void a(String str, long j, Exception exc) {
        cq cqVarA = cu.a().a(fq.a(), false);
        if (cqVarA != null) {
            cqVarA.b(str, j, 0L, exc);
            cu.a().m190c();
        }
    }

    /* renamed from: a */
    protected abstract void mo325a(boolean z);

    @Override // com.xiaomi.push.fp
    public void a(fg[] fgVarArr) throws ga {
        throw new ga("Don't support send Blob");
    }

    @Override // com.xiaomi.push.fp
    public void b(int i, Exception exc) {
        a(i, exc);
        if ((exc != null || i == 18) && this.g != 0) {
            a(exc);
        }
    }

    @Override // com.xiaomi.push.fp
    public void b(boolean z) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        mo325a(z);
        if (z) {
            return;
        }
        this.b.a(new fx(this, 13, jCurrentTimeMillis), DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL);
    }

    public String c() {
        return this.f397a;
    }

    public void c(int i, Exception exc) {
        this.b.a(new fy(this, 2, i, exc));
    }

    public synchronized void e() {
        try {
            if (!c() && !b()) {
                a(0, 0, (Exception) null);
                a(this.f394a);
                return;
            }
            com.xiaomi.channel.commonutils.logger.b.m22a("WARNING: current xmpp has connected");
        } catch (IOException e) {
            throw new ga(e);
        }
    }

    public void f() {
        this.e = SystemClock.elapsedRealtime();
    }

    public void g() {
        this.f = SystemClock.elapsedRealtime();
    }
}
