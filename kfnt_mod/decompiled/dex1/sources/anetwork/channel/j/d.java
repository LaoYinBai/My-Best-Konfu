package anetwork.channel.j;

import android.text.TextUtils;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d implements a {

    /* renamed from: c, reason: collision with root package name */
    private l f716c;
    private Request f;

    /* renamed from: b, reason: collision with root package name */
    private volatile boolean f715b = false;

    /* renamed from: a, reason: collision with root package name */
    volatile Cancelable f714a = null;
    private int d = 0;
    private int e = 0;

    static /* synthetic */ int b(d dVar) {
        int i = dVar.e;
        dVar.e = i + 1;
        return i;
    }

    public d(l lVar) {
        this.f716c = lVar;
        this.f = lVar.f736a.a();
    }

    @Override // anet.channel.request.Cancelable
    public void cancel() {
        this.f715b = true;
        if (this.f714a != null) {
            this.f714a.cancel();
        }
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        if (this.f715b) {
            return;
        }
        if (this.f716c.f736a.i()) {
            String strA = anetwork.channel.c.a.a(this.f716c.f736a.g());
            if (!TextUtils.isEmpty(strA)) {
                Request.Builder builderNewBuilder = this.f.newBuilder();
                String str = this.f.getHeaders().get(HttpConstant.COOKIE);
                if (!TextUtils.isEmpty(str)) {
                    strA = StringUtils.concatString(str, "; ", strA);
                }
                builderNewBuilder.addHeader(HttpConstant.COOKIE, strA);
                this.f = builderNewBuilder.build();
            }
        }
        this.f.f475a.degraded = 2;
        this.f.f475a.sendBeforeTime = System.currentTimeMillis() - this.f.f475a.reqStart;
        anet.channel.session.b.a(this.f, new e(this));
    }
}
