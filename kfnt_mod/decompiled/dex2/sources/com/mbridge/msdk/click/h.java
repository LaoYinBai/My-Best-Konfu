package com.mbridge.msdk.click;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import com.mbridge.msdk.click.CommonJumpLoader;
import com.mbridge.msdk.click.f;
import com.mbridge.msdk.click.g;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.e.a;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.v;
import java.util.concurrent.Semaphore;

/* compiled from: WebViewSpiderLoader.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class h extends d implements a.b {
    private e c;
    private CommonJumpLoader.JumpLoaderResult d;
    private boolean f;
    private Context g;
    private com.mbridge.msdk.foundation.same.e.b h;
    private f.a i;
    private boolean k;
    private boolean a = false;
    private long b = 0;
    private boolean e = true;
    private Handler j = new Handler(Looper.getMainLooper());

    public h(Context context, boolean z) {
        this.g = context;
        this.k = z;
        if (z) {
            this.h = new com.mbridge.msdk.foundation.same.e.b(context, 2);
        } else {
            this.h = new com.mbridge.msdk.foundation.same.e.b(context);
        }
    }

    public final void a(String str, e eVar, boolean z, String str2, String str3, String str4, com.mbridge.msdk.rover.d dVar, CampaignEx campaignEx, boolean z2, boolean z3) {
        this.c = eVar;
        this.f = z;
        this.h.a(new a(this.g, str, str2, str3, str4, dVar, campaignEx, z2, z3), this);
    }

    @Override // com.mbridge.msdk.click.d
    public final void b() {
        this.e = false;
    }

    /* compiled from: WebViewSpiderLoader.java */
    private class a extends com.mbridge.msdk.foundation.same.e.a {
        private final Context c;
        private String d;
        private String e;
        private String f;
        private String g;
        private com.mbridge.msdk.rover.d h;
        private CampaignEx i;
        private boolean j;
        private boolean k;
        private final Semaphore b = new Semaphore(0);
        private g.a l = new g.a() { // from class: com.mbridge.msdk.click.h.a.1
            @Override // com.mbridge.msdk.click.g.a
            public final boolean a(String str) {
                boolean zA = a.this.a(str);
                a.a(a.this, false, true, str, "");
                if (zA) {
                    a();
                }
                return zA;
            }

            @Override // com.mbridge.msdk.click.g.a
            public final boolean b(String str) {
                boolean zA = a.this.a(str);
                a.a(a.this, false, true, str, "");
                if (zA) {
                    a.a(a.this, true, true, str, "");
                    a();
                }
                return zA;
            }

            @Override // com.mbridge.msdk.click.g.a
            public final boolean c(String str) {
                a.a(a.this, false, false, str, "");
                return false;
            }

            @Override // com.mbridge.msdk.click.g.a
            public final void a(String str, boolean z, String str2) {
                a.this.a(str);
                h.this.d.setContent(str2);
                a.a(a.this, true, false, str, "timeout");
                a();
            }

            private void a() {
                synchronized (h.this) {
                    h.this.d.setSuccess(true);
                    a.a(a.this);
                }
            }

            @Override // com.mbridge.msdk.click.g.a
            public final void a(int i, String str, String str2, String str3) {
                if (!TextUtils.isEmpty(str2)) {
                    h.this.d.setExceptionMsg(str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    h.this.d.setContent(str3);
                }
                a.this.a(str);
                a.a(a.this, true, false, str, str2);
                a();
            }
        };

        @Override // com.mbridge.msdk.foundation.same.e.a
        public final void cancelTask() {
        }

        @Override // com.mbridge.msdk.foundation.same.e.a
        public final void pauseTask(boolean z) {
        }

        public a(Context context, String str, String str2, String str3, String str4, com.mbridge.msdk.rover.d dVar, CampaignEx campaignEx, boolean z, boolean z2) {
            this.c = context;
            this.d = str;
            this.e = str2;
            this.f = str3;
            this.g = str4;
            this.h = dVar;
            this.i = campaignEx;
            this.j = z;
            this.k = z2;
        }

        /* JADX WARN: Code restructure failed: missing block: B:59:0x01a6, code lost:
        
            r2.setjumpDone(r15);
            r2.setUrl(r4);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private com.mbridge.msdk.click.CommonJumpLoader.JumpLoaderResult a(java.lang.String r21, boolean r22, boolean r23, com.mbridge.msdk.foundation.entity.CampaignEx r24) {
            /*
                Method dump skipped, instructions count: 566
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.click.h.a.a(java.lang.String, boolean, boolean, com.mbridge.msdk.foundation.entity.CampaignEx):com.mbridge.msdk.click.CommonJumpLoader$JumpLoaderResult");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a(String str) {
            if (this.i != null) {
                this.i.getLinkType();
            }
            if (v.a.a(str)) {
                h.this.d.setCode(1);
                h.this.d.setUrl(str);
                h.this.d.setjumpDone(true);
                return true;
            }
            if (c(str)) {
                h.this.d.setCode(3);
                h.this.d.setUrl(str);
                h.this.d.setjumpDone(true);
                return true;
            }
            h.this.d.setCode(2);
            h.this.d.setUrl(str);
            return false;
        }

        private boolean b(String str) {
            return !URLUtil.isNetworkUrl(str);
        }

        private boolean c(String str) {
            return !TextUtils.isEmpty(str) && str.toLowerCase().contains("apk");
        }

        @Override // com.mbridge.msdk.foundation.same.e.a
        public final void runTask() {
            if (h.this.c != null) {
                h.this.c.a(null);
            }
            h.this.d = new CommonJumpLoader.JumpLoaderResult();
            h.this.d.setUrl(this.d);
            h.this.d = a(this.d, this.j, this.k, this.i);
            if (!TextUtils.isEmpty(h.this.d.getExceptionMsg())) {
                h.this.d.setSuccess(true);
            }
            if (h.this.e && h.this.d.isSuccess()) {
                if (h.this.i != null) {
                    h.this.d.setStatusCode(h.this.i.f);
                }
                if (!c(h.this.d.getUrl()) && !v.a.a(h.this.d.getUrl()) && 200 == h.this.i.f && !TextUtils.isEmpty(h.this.d.getContent()) && !h.this.d.getContent().contains("EXCEPTION_CAMPAIGN_NOT_ACTIVE")) {
                    h.this.d.setType(2);
                    if (!TextUtils.isEmpty(h.this.d.getContent())) {
                        Log.e("302", "startWebViewHtmlParser");
                        new g(h.this.k).a(this.e, this.f, this.g, this.c, h.this.d.getUrl(), h.this.d.getContent(), this.l);
                        s.d("302", "startWebViewHtmlParser");
                    } else {
                        s.a("302", "startWebViewSpider");
                        try {
                            new g(h.this.k).a(this.e, this.f, this.g, this.c, h.this.d.getUrl(), this.l);
                        } catch (Exception unused) {
                            s.d("TAG", "webview spider start error");
                        }
                    }
                    this.b.acquireUninterruptibly();
                    return;
                }
                if (this.h != null) {
                    this.h.a(h.this.d.getUrl(), com.mbridge.msdk.rover.a.a, 0, 0, "", "");
                }
                if (h.this.i != null) {
                    h.this.d.setType(1);
                    h.this.d.setExceptionMsg(h.this.i.h);
                    h.this.d.setStatusCode(h.this.i.f);
                    h.this.d.setHeader(h.this.i.a());
                    h.this.d.setContent(h.this.i.g);
                }
                a(h.this.d.getUrl());
            }
        }

        static /* synthetic */ void a(a aVar, boolean z, boolean z2, String str, String str2) {
            int i;
            long j = h.this.b;
            if (j == 0) {
                h.this.b = System.currentTimeMillis();
                i = 0;
            } else {
                long jCurrentTimeMillis = System.currentTimeMillis();
                h.this.b = jCurrentTimeMillis;
                i = (int) (jCurrentTimeMillis - j);
            }
            if (!z) {
                if (aVar.h != null) {
                    aVar.h.b(str, com.mbridge.msdk.rover.a.b, i, 0, "", str2);
                }
            } else {
                if (z2) {
                    if (aVar.h == null || h.this.a) {
                        return;
                    }
                    h.this.a = true;
                    aVar.h.a(str, com.mbridge.msdk.rover.a.b, i, 0, "", str2);
                    return;
                }
                if (aVar.h == null || h.this.a) {
                    return;
                }
                h.this.a = true;
                aVar.h.c(str, com.mbridge.msdk.rover.a.b, i, 0, "", str2);
            }
        }

        static /* synthetic */ void a(a aVar) {
            aVar.b.release();
        }
    }

    @Override // com.mbridge.msdk.foundation.same.e.a.b
    public final void a(a.EnumC0111a enumC0111a) {
        if (enumC0111a == a.EnumC0111a.FINISH && this.e) {
            this.j.post(new Runnable() { // from class: com.mbridge.msdk.click.h.1
                @Override // java.lang.Runnable
                public final void run() {
                    if (h.this.c != null) {
                        if (h.this.d.isSuccess()) {
                            h.this.c.b(h.this.d);
                        } else {
                            h.this.c.a(h.this.d, h.this.d.getMsg());
                        }
                    }
                }
            });
        }
    }
}
