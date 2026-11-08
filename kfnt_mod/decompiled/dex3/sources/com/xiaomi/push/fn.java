package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.push.eb;
import com.xiaomi.push.fp;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.av;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class fn extends fw {
    private fi a;

    /* renamed from: a, reason: collision with other field name */
    private fj f388a;

    /* renamed from: a, reason: collision with other field name */
    private Thread f389a;

    /* renamed from: a, reason: collision with other field name */
    private byte[] f390a;

    public fn(XMPushService xMPushService, fq fqVar) {
        super(xMPushService, fqVar);
    }

    private fg a(boolean z) {
        fm fmVar = new fm();
        if (z) {
            fmVar.a("1");
        }
        byte[] bArrM380a = hd.m380a();
        if (bArrM380a != null) {
            eb.j jVar = new eb.j();
            jVar.a(a.a(bArrM380a));
            fmVar.a(jVar.a(), (String) null);
        }
        return fmVar;
    }

    private void h() throws ga {
        try {
            this.a = new fi(this.f411a.getInputStream(), this);
            this.f388a = new fj(this.f411a.getOutputStream(), this);
            this.f389a = new fo(this, "Blob Reader (" + this.b + com.umeng.message.proguard.l.t);
            this.f389a.start();
        } catch (Exception e) {
            throw new ga("Error to init reader and writer", e);
        }
    }

    @Override // com.xiaomi.push.fw
    /* renamed from: a */
    protected synchronized void mo341a() {
        h();
        this.f388a.a();
    }

    @Override // com.xiaomi.push.fw
    protected synchronized void a(int i, Exception exc) {
        if (this.a != null) {
            this.a.b();
            this.a = null;
        }
        if (this.f388a != null) {
            try {
                this.f388a.b();
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
            }
            this.f388a = null;
            this.f390a = null;
            super.a(i, exc);
        } else {
            this.f390a = null;
            super.a(i, exc);
        }
    }

    void a(fg fgVar) {
        if (fgVar == null) {
            return;
        }
        if (fgVar.m315a()) {
            com.xiaomi.channel.commonutils.logger.b.m22a("[Slim] RCV blob chid=" + fgVar.a() + "; id=" + fgVar.e() + "; errCode=" + fgVar.b() + "; err=" + fgVar.m319c());
        }
        if (fgVar.a() == 0) {
            if ("PING".equals(fgVar.m312a())) {
                com.xiaomi.channel.commonutils.logger.b.m22a("[Slim] RCV ping id=" + fgVar.e());
                g();
            } else if ("CLOSE".equals(fgVar.m312a())) {
                c(13, null);
            }
        }
        Iterator<fp.a> it = this.f400a.values().iterator();
        while (it.hasNext()) {
            it.next().a(fgVar);
        }
    }

    @Override // com.xiaomi.push.fp
    @Deprecated
    public void a(gg ggVar) throws ga {
        b(fg.a(ggVar, (String) null));
    }

    @Override // com.xiaomi.push.fp
    public synchronized void a(av.b bVar) {
        ff.a(bVar, c(), this);
    }

    @Override // com.xiaomi.push.fp
    public synchronized void a(String str, String str2) {
        ff.a(str, str2, this);
    }

    @Override // com.xiaomi.push.fw
    /* renamed from: a, reason: collision with other method in class */
    protected void mo325a(boolean z) throws ga {
        if (this.f388a == null) {
            throw new ga("The BlobWriter is null.");
        }
        fg fgVarA = a(z);
        com.xiaomi.channel.commonutils.logger.b.m22a("[Slim] SND ping id=" + fgVarA.e());
        b(fgVarA);
        f();
    }

    @Override // com.xiaomi.push.fw, com.xiaomi.push.fp
    public void a(fg[] fgVarArr) throws ga {
        for (fg fgVar : fgVarArr) {
            b(fgVar);
        }
    }

    @Override // com.xiaomi.push.fp
    /* renamed from: a, reason: collision with other method in class */
    public boolean mo326a() {
        return true;
    }

    /* renamed from: a, reason: collision with other method in class */
    synchronized byte[] m327a() {
        if (this.f390a == null && !TextUtils.isEmpty(this.f397a)) {
            String strM619a = com.xiaomi.push.service.bk.m619a();
            this.f390a = com.xiaomi.push.service.be.a(this.f397a.getBytes(), (this.f397a.substring(this.f397a.length() / 2) + strM619a.substring(strM619a.length() / 2)).getBytes());
        }
        return this.f390a;
    }

    @Override // com.xiaomi.push.fp
    public void b(fg fgVar) throws ga {
        if (this.f388a == null) {
            throw new ga("the writer is null.");
        }
        try {
            int iA = this.f388a.a(fgVar);
            this.d = System.currentTimeMillis();
            String strF = fgVar.f();
            if (!TextUtils.isEmpty(strF)) {
                gu.a(this.f396a, strF, iA, false, true, System.currentTimeMillis());
            }
            Iterator<fp.a> it = this.f403b.values().iterator();
            while (it.hasNext()) {
                it.next().a(fgVar);
            }
        } catch (Exception e) {
            throw new ga(e);
        }
    }

    void b(gg ggVar) {
        if (ggVar == null) {
            return;
        }
        Iterator<fp.a> it = this.f400a.values().iterator();
        while (it.hasNext()) {
            it.next().a(ggVar);
        }
    }
}
