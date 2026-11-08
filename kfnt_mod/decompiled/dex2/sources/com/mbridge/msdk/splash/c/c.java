package com.mbridge.msdk.splash.c;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.confirmation.e;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.splash.view.MBSplashView;
import com.mbridge.msdk.splash.view.MBSplashWebview;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: SplashShowManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private CampaignEx c;
    private MBSplashView d;
    private com.mbridge.msdk.splash.d.d e;
    private com.mbridge.msdk.click.b f;
    private boolean g;
    private TextView h;
    private View i;
    private String j;
    private String k;
    private MBridgeIds l;
    private String n;
    private String o;
    private String p;
    private String q;
    private boolean r;
    private Context s;
    private String b = "SplashShowManager";
    private int m = 5;
    private View.OnClickListener t = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.c.c.1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (c.this.g) {
                c.this.b(1);
                c.b(c.this, -1);
            }
        }
    };
    public Handler a = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.splash.c.c.2
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    if (c.this.m <= 0) {
                        c.this.b(2);
                        break;
                    } else {
                        c.c(c.this);
                        c.b(c.this, c.this.m);
                        c.this.a.removeMessages(1);
                        sendEmptyMessageDelayed(1, 1000L);
                        break;
                    }
                case 2:
                    if (c.this.c != null && c.this.c.isActiveOm() && c.this.d != null) {
                        c.this.d.getSplashWebview();
                        break;
                    }
                    break;
            }
        }
    };
    private com.mbridge.msdk.splash.d.a u = new com.mbridge.msdk.splash.d.a() { // from class: com.mbridge.msdk.splash.c.c.3
        @Override // com.mbridge.msdk.splash.d.a
        public final void a(int i) {
            if (c.this.d != null) {
                c.this.d.changeCloseBtnState(i);
            }
        }

        @Override // com.mbridge.msdk.splash.d.a
        public final void a(Object obj, String str) {
            c.this.b(1);
        }

        @Override // com.mbridge.msdk.splash.d.a
        public final void a(CampaignEx campaignEx) {
            c.this.a(campaignEx, false, "");
        }

        @Override // com.mbridge.msdk.splash.d.a
        public final void b(int i) {
            s.d(c.this.b, "resetCountdown" + i);
            c.this.m = i;
            c.this.a.removeMessages(1);
            c.this.a.sendEmptyMessageDelayed(1, 1000L);
        }

        @Override // com.mbridge.msdk.splash.d.a
        public final void a(int i, int i2) {
            if (i == 1) {
                c.this.a.removeMessages(1);
            }
            if (i == 2) {
                c.this.m = i2;
                c.this.a.removeMessages(1);
                c.this.a.sendEmptyMessageDelayed(1, 1000L);
            }
        }

        @Override // com.mbridge.msdk.splash.d.a
        public final void a() {
            c.this.b(1);
        }

        @Override // com.mbridge.msdk.splash.d.a
        public final void a(boolean z) {
            if (z) {
                c.this.a.removeMessages(1);
            }
        }

        @Override // com.mbridge.msdk.splash.d.a
        public final void a(boolean z, String str) {
            try {
                if (c.this.e != null) {
                    if (TextUtils.isEmpty(str)) {
                        c.this.e.b(c.this.l);
                    } else {
                        CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(CampaignEx.campaignToJsonObject(c.this.c));
                        campaignWithBackData.setClickURL(str);
                        c.this.a(campaignWithBackData, true, str);
                    }
                }
            } catch (Exception e) {
                s.d(c.this.b, e.getMessage());
            }
        }
    };

    static /* synthetic */ int c(c cVar) {
        int i = cVar.m;
        cVar.m = i - 1;
        return i;
    }

    public c(Context context, String str, String str2) throws Resources.NotFoundException {
        this.n = "点击跳过|";
        this.o = "点击跳过|";
        this.p = "秒";
        this.q = "秒后自动关闭";
        this.j = str2;
        this.k = str;
        this.l = new MBridgeIds(this.k, str2);
        this.s = context;
        if (this.h == null) {
            this.h = new TextView(context);
            this.h.setGravity(1);
            this.h.setTextIsSelectable(false);
            this.h.setPadding(w.b(context, 5.0f), w.b(context, 5.0f), w.b(context, 5.0f), w.b(context, 5.0f));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.h.getLayoutParams();
            this.h.setLayoutParams(layoutParams == null ? new RelativeLayout.LayoutParams(w.b(context, 100.0f), w.b(context, 50.0f)) : layoutParams);
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            String strD = com.mbridge.msdk.foundation.controller.a.f().d();
            int identifier = contextJ.getResources().getIdentifier("mbridge_splash_count_time_can_skip", "string", strD);
            int identifier2 = contextJ.getResources().getIdentifier("mbridge_splash_count_time_can_skip_not", "string", strD);
            int identifier3 = contextJ.getResources().getIdentifier("mbridge_splash_count_time_can_skip_s", "string", strD);
            this.o = contextJ.getResources().getString(identifier);
            String string = contextJ.getResources().getString(identifier2);
            this.q = string;
            this.n = string;
            this.p = contextJ.getResources().getString(identifier3);
            this.h.setBackgroundResource(contextJ.getResources().getIdentifier("mbridge_splash_close_bg", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
            this.h.setTextColor(contextJ.getResources().getColor(contextJ.getResources().getIdentifier("mbridge_splash_count_time_skip_text_color", "color", strD)));
        }
    }

    public final void a(com.mbridge.msdk.splash.d.d dVar) {
        this.e = dVar;
    }

    public final void a(int i) {
        this.m = i;
    }

    public final void a(ViewGroup viewGroup) {
        if (viewGroup != null) {
            viewGroup.setOnClickListener(this.t);
        }
        this.i = viewGroup;
    }

    public final com.mbridge.msdk.splash.d.a a() {
        return this.u;
    }

    public final void a(CampaignEx campaignEx, MBSplashView mBSplashView) {
        a(this.g);
        this.c = campaignEx;
        this.d = mBSplashView;
        com.mbridge.msdk.splash.js.b splashJSBridgeImpl = mBSplashView.getSplashJSBridgeImpl();
        if (splashJSBridgeImpl == null) {
            splashJSBridgeImpl = new com.mbridge.msdk.splash.js.b(mBSplashView.getContext(), this.k, this.j);
            ArrayList arrayList = new ArrayList();
            arrayList.add(campaignEx);
            splashJSBridgeImpl.a(arrayList);
        }
        splashJSBridgeImpl.b(this.m);
        splashJSBridgeImpl.a(this.g ? 1 : 0);
        splashJSBridgeImpl.a(this.u);
        mBSplashView.setSplashJSBridgeImpl(splashJSBridgeImpl);
        boolean zIsHasMBTplMark = campaignEx.isHasMBTplMark();
        if (this.i == null) {
            if (zIsHasMBTplMark) {
                this.h.setVisibility(8);
            }
            g();
            a(this.h);
            mBSplashView.setCloseView(this.h);
        } else {
            if (zIsHasMBTplMark) {
                this.i.setVisibility(8);
            }
            a(this.i);
            mBSplashView.setCloseView(this.i);
        }
        mBSplashView.show();
        if (this.c != null && this.c.isActiveOm() && mBSplashView != null) {
            mBSplashView.getSplashWebview();
        }
        if (!this.c.isReport()) {
            final CampaignEx campaignEx2 = this.c;
            boolean z = false;
            if (!campaignEx2.isHasMBTplMark()) {
                final Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
                String str = this.j;
                com.mbridge.msdk.foundation.controller.a.f().a(contextJ);
                if (!TextUtils.isEmpty(campaignEx2.getImpressionURL())) {
                    new Thread(new Runnable() { // from class: com.mbridge.msdk.splash.c.c.6
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                m.a(i.a(contextJ)).b(campaignEx2.getId());
                            } catch (Exception unused) {
                                s.d(c.this.b, "campain can't insert db");
                            }
                        }
                    }).start();
                    com.mbridge.msdk.click.b.a(contextJ, campaignEx2, str, campaignEx2.getImpressionURL(), false, true);
                }
                if (!TextUtils.isEmpty(str) && campaignEx2.getNativeVideoTracking() != null && campaignEx2.getNativeVideoTracking().n() != null) {
                    com.mbridge.msdk.click.b.a(contextJ, campaignEx2, str, campaignEx2.getNativeVideoTracking().n(), false, false);
                }
                campaignEx2.setReport(true);
                com.mbridge.msdk.foundation.same.a.d.b(this.j, campaignEx2, "splash");
                z = true;
            }
            if (z) {
                Context contextJ2 = com.mbridge.msdk.foundation.controller.a.f().j();
                String str2 = this.j;
                if (campaignEx2 != null) {
                    try {
                        if (!TextUtils.isEmpty(campaignEx2.getOnlyImpressionURL())) {
                            com.mbridge.msdk.click.b.a(contextJ2, campaignEx2, str2, campaignEx2.getOnlyImpressionURL(), false, true);
                        }
                    } catch (Throwable th) {
                        s.d(this.b, th.getMessage());
                    }
                }
                a(campaignEx2, com.mbridge.msdk.foundation.controller.a.f().j(), this.j);
            }
            Context contextJ3 = com.mbridge.msdk.foundation.controller.a.f().j();
            CampaignEx campaignEx3 = this.c;
            String str3 = this.j;
            if (campaignEx3 != null) {
                try {
                    if (campaignEx3.isMraid()) {
                        q qVar = new q();
                        qVar.k(campaignEx3.getRequestId());
                        qVar.l(campaignEx3.getRequestIdNotice());
                        qVar.n(campaignEx3.getId());
                        qVar.a(campaignEx3.isMraid() ? q.a : q.b);
                        com.mbridge.msdk.foundation.same.report.b.a(qVar, contextJ3.getApplicationContext(), str3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (this.e != null) {
            this.e.a(this.l);
        }
        a.a(this.j);
        this.a.removeMessages(1);
        this.a.sendEmptyMessageDelayed(1, 1000L);
        this.a.sendEmptyMessageDelayed(2, 1000L);
    }

    public final String b() {
        return (this.c == null || this.c.getRequestId() == null) ? "" : this.c.getRequestId();
    }

    public final void a(boolean z) {
        this.g = z;
        if (z) {
            this.n = this.o;
        } else {
            this.n = this.q;
        }
    }

    public final void a(final CampaignEx campaignEx, final boolean z, final String str) {
        MBSplashWebview splashWebview;
        if (campaignEx != null) {
            try {
                if (campaignEx.needShowIDialog()) {
                    final com.mbridge.msdk.widget.dialog.a aVar = new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.splash.c.c.4
                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void a() {
                            c.this.b(campaignEx, z, str);
                        }

                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void b() {
                            c.this.f();
                        }

                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void c() {
                            a();
                        }
                    };
                    if (this.d != null && com.mbridge.msdk.click.c.a(campaignEx) && campaignEx.needShowIDialog()) {
                        if (this.f == null) {
                            this.f = new com.mbridge.msdk.click.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.j);
                        }
                        final Context context = this.d.getContext();
                        this.d.post(new Runnable() { // from class: com.mbridge.msdk.splash.c.c.5
                            @Override // java.lang.Runnable
                            public final void run() {
                                if (c.this.f.b(campaignEx)) {
                                    if (c.this.e != null) {
                                        c.this.e.b(c.this.l);
                                        c.this.b(3);
                                        return;
                                    }
                                    return;
                                }
                                if (context != context.getApplicationContext()) {
                                    e.a().a("", campaignEx, context, c.this.j, aVar);
                                } else {
                                    e.a().a("", campaignEx, c.this.s, c.this.j, aVar);
                                }
                            }
                        });
                        this.r = true;
                        if (this.m > 0 && this.a != null) {
                            this.a.removeMessages(1);
                        }
                        if (this.d == null || (splashWebview = this.d.getSplashWebview()) == null || splashWebview.isDestoryed()) {
                            return;
                        }
                        com.mbridge.msdk.splash.js.c.a(splashWebview, "onInstallAlertShow", "");
                        return;
                    }
                }
            } catch (Throwable th) {
                s.a(this.b, th.getMessage());
            }
        }
        b(campaignEx, z, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CampaignEx campaignEx, boolean z, String str) {
        if (this.f == null) {
            this.f = new com.mbridge.msdk.click.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.j);
        }
        campaignEx.setCampaignUnitId(this.j);
        this.f.c(campaignEx);
        if (!this.c.isReportClick()) {
            this.c.setReportClick(true);
            com.mbridge.msdk.splash.e.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx);
        }
        if (this.e != null) {
            this.e.b(this.l);
            b(3);
        }
        if (!z || TextUtils.isEmpty(str)) {
            return;
        }
        com.mbridge.msdk.splash.e.a.a(campaignEx, this.j, str);
    }

    private void g() {
        String str;
        if (this.g) {
            str = this.o + this.m + this.p;
        } else {
            str = this.m + this.q;
        }
        this.h.setText(str);
    }

    private void a(View view) {
        if (view != null) {
            view.setOnClickListener(this.t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        com.mbridge.msdk.splash.a.b bVarA;
        if (this.e != null) {
            this.e.a(this.l, i);
            this.e = null;
            com.mbridge.msdk.splash.e.a.a(this.j, this.c);
        }
        if (this.c != null) {
            bVarA = com.mbridge.msdk.splash.a.b.a().b(this.j).d(this.c.getRequestId()).g(this.c.getRequestIdNotice()).c(this.c.getId()).e(this.c.getCreativeId() + "").a(this.c.isBidCampaign());
        } else {
            bVarA = null;
        }
        com.mbridge.msdk.splash.e.a.a(bVarA, this.j, i);
        if (this.a != null) {
            this.a.removeCallbacksAndMessages(null);
        }
    }

    private void a(CampaignEx campaignEx, Context context, String str) {
        if (campaignEx != null) {
            try {
                List<String> pv_urls = campaignEx.getPv_urls();
                if (pv_urls == null || pv_urls.size() <= 0) {
                    return;
                }
                Iterator<String> it = pv_urls.iterator();
                while (it.hasNext()) {
                    com.mbridge.msdk.click.b.a(context, campaignEx, str, it.next(), false, true);
                }
            } catch (Throwable th) {
                s.d(this.b, th.getMessage());
            }
        }
    }

    public final void c() {
        if (this.e != null) {
            this.e = null;
        }
        if (this.u != null) {
            this.u = null;
        }
        if (this.t != null) {
            this.t = null;
        }
        if (this.d != null) {
            this.d.destroy();
        }
    }

    public final void d() {
        MBSplashWebview splashWebview;
        if (this.r) {
            return;
        }
        if (this.m > 0 && this.a != null) {
            this.a.removeMessages(1);
            this.a.sendEmptyMessageDelayed(1, 1000L);
        }
        if (this.d == null || (splashWebview = this.d.getSplashWebview()) == null || splashWebview.isDestoryed()) {
            return;
        }
        com.mbridge.msdk.splash.js.c.a(splashWebview, "onSystemResume", "");
    }

    public final void e() {
        MBSplashWebview splashWebview;
        if (this.m > 0 && this.a != null) {
            this.a.removeMessages(1);
        }
        if (this.d == null || (splashWebview = this.d.getSplashWebview()) == null || splashWebview.isDestoryed()) {
            return;
        }
        com.mbridge.msdk.splash.js.c.a(splashWebview, "onSystemPause", "");
    }

    public final void f() {
        MBSplashWebview splashWebview;
        this.r = false;
        if (this.m > 0 && this.a != null) {
            this.a.removeMessages(1);
            this.a.sendEmptyMessageDelayed(1, 1000L);
        }
        if (this.d == null || (splashWebview = this.d.getSplashWebview()) == null || splashWebview.isDestoryed()) {
            return;
        }
        com.mbridge.msdk.splash.js.c.a(splashWebview, "onInstallAlertHide", "");
    }

    static /* synthetic */ void b(c cVar, int i) {
        if (cVar.d != null) {
            cVar.d.updateCountdown(i);
            if (cVar.d.getSplashJSBridgeImpl() != null) {
                cVar.d.getSplashJSBridgeImpl().c(i);
            }
        }
        if (i < 0) {
            cVar.m = i;
            return;
        }
        if (cVar.e != null) {
            cVar.e.a(cVar.l, i * 1000);
        }
        if (cVar.i == null) {
            cVar.g();
        }
    }
}
