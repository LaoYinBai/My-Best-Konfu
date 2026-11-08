package com.mbridge.msdk.advanced.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mbridge.msdk.advanced.c.d;
import com.mbridge.msdk.advanced.view.MBNativeAdvancedView;
import com.mbridge.msdk.advanced.view.MBNativeAdvancedWebview;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.ac;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.confirmation.e;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.out.NativeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: NativeAdvancedShowManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private CampaignEx c;
    private MBNativeAdvancedView d;
    private d e;
    private com.mbridge.msdk.click.b f;
    private com.mbridge.msdk.advanced.c.c g;
    private boolean h;
    private ImageView i;
    private String j;
    private String k;
    private MBridgeIds l;
    private boolean m;
    private String b = "NativeAdvancedShowManager";
    private View.OnClickListener n = new View.OnClickListener() { // from class: com.mbridge.msdk.advanced.b.b.1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (b.this.h) {
                b.a(b.this, 1);
            }
        }
    };
    public Handler a = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.advanced.b.b.2
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 2 && b.this.c != null && b.this.c.isActiveOm() && b.this.d != null) {
                b.this.d.getAdvancedNativeWebview();
            }
        }
    };
    private com.mbridge.msdk.advanced.c.a o = new com.mbridge.msdk.advanced.c.a() { // from class: com.mbridge.msdk.advanced.b.b.3
        @Override // com.mbridge.msdk.advanced.c.a
        public final void a(int i) {
            if (b.this.d != null) {
                b.this.d.changeCloseBtnState(i);
            }
        }

        @Override // com.mbridge.msdk.advanced.c.a
        public final void a(Object obj, String str) {
            if (b.this.d != null) {
                b.this.d.setVisibility(8);
            }
            b.a(b.this, 1);
        }

        @Override // com.mbridge.msdk.advanced.c.a
        public final void a(CampaignEx campaignEx) {
            b.this.a(campaignEx, false, "");
        }

        @Override // com.mbridge.msdk.advanced.c.a
        public final void b(int i) {
            s.d(b.this.b, "resetCountdown" + i);
        }

        @Override // com.mbridge.msdk.advanced.c.a
        public final void a() {
            b.a(b.this, 1);
        }

        @Override // com.mbridge.msdk.advanced.c.a
        public final void a(boolean z) {
            if (b.this.e != null) {
                b.this.m = z;
                if (z) {
                    b.this.e.e(b.this.l);
                } else {
                    b.this.e.f(b.this.l);
                }
            }
        }

        @Override // com.mbridge.msdk.advanced.c.a
        public final void a(boolean z, String str) {
            try {
                if (b.this.e != null) {
                    if (TextUtils.isEmpty(str)) {
                        b.this.e.b(b.this.l);
                        b.this.e.d(b.this.l);
                    } else {
                        CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(CampaignEx.campaignToJsonObject(b.this.c));
                        campaignWithBackData.setClickURL(str);
                        b.this.a(campaignWithBackData, z, str);
                    }
                }
            } catch (Exception e) {
                s.d(b.this.b, e.getMessage());
            }
        }
    };

    public b(Context context, String str, String str2) {
        this.j = str2;
        this.k = str;
        this.l = new MBridgeIds(str, str2);
        if (this.i == null) {
            this.i = new ImageView(context);
            this.i.setPadding(w.b(context, 2.0f), w.b(context, 2.0f), w.b(context, 2.0f), w.b(context, 2.0f));
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            this.i.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.i.getLayoutParams();
            this.i.setLayoutParams(layoutParams == null ? new RelativeLayout.LayoutParams(w.b(contextJ, 29.0f), w.b(contextJ, 16.0f)) : layoutParams);
            this.i.setImageResource(contextJ.getResources().getIdentifier("mbridge_native_advanced_close_icon", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
    }

    public final void a(com.mbridge.msdk.advanced.c.c cVar) {
        this.g = cVar;
    }

    public final void a(d dVar) {
        this.e = dVar;
    }

    public final String a() {
        return (this.c == null || this.c.getRequestId() == null) ? "" : this.c.getRequestId();
    }

    public final com.mbridge.msdk.advanced.c.a b() {
        return this.o;
    }

    public final void a(final CampaignEx campaignEx, final MBNativeAdvancedView mBNativeAdvancedView, boolean z) {
        if (mBNativeAdvancedView == null) {
            return;
        }
        this.h = this.h;
        this.c = campaignEx;
        this.d = mBNativeAdvancedView;
        com.mbridge.msdk.advanced.js.a advancedNativeJSBridgeImpl = mBNativeAdvancedView.getAdvancedNativeJSBridgeImpl();
        if (advancedNativeJSBridgeImpl == null) {
            advancedNativeJSBridgeImpl = new com.mbridge.msdk.advanced.js.a(mBNativeAdvancedView.getContext(), this.k, this.j);
            ArrayList arrayList = new ArrayList();
            arrayList.add(campaignEx);
            advancedNativeJSBridgeImpl.a(arrayList);
        }
        advancedNativeJSBridgeImpl.a(this.h ? 1 : 0);
        advancedNativeJSBridgeImpl.a(this.o);
        mBNativeAdvancedView.setAdvancedNativeJSBridgeImpl(advancedNativeJSBridgeImpl);
        if (campaignEx.isHasMBTplMark() || !this.h) {
            this.i.setVisibility(8);
        }
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setOnClickListener(this.n);
        }
        mBNativeAdvancedView.setCloseView(this.i);
        if (mBNativeAdvancedView.getVisibility() != 0) {
            mBNativeAdvancedView.setVisibility(0);
        }
        boolean zA = ac.a(mBNativeAdvancedView.getAdvancedNativeWebview());
        if (this.g == null || zA || this.g.b() == null || this.g.b().getAlpha() < 0.5f || this.g.b().getVisibility() != 0 || this.m) {
            if (z) {
                mBNativeAdvancedView.postDelayed(new Runnable() { // from class: com.mbridge.msdk.advanced.b.b.4
                    @Override // java.lang.Runnable
                    public final void run() {
                        b.this.a(campaignEx, mBNativeAdvancedView, false);
                    }
                }, 200L);
                return;
            }
            return;
        }
        mBNativeAdvancedView.show();
        if (this.c != null && this.c.isActiveOm() && mBNativeAdvancedView != null) {
            mBNativeAdvancedView.getAdvancedNativeWebview();
            try {
                com.mbridge.msdk.advanced.common.b.b(campaignEx.getRequestId());
            } catch (Throwable th) {
                s.a("OMSDK", th.getMessage());
                if (this.c != null) {
                    String requestId = this.c.getRequestId();
                    String requestIdNotice = this.c.getRequestIdNotice();
                    String id = this.c.getId();
                    new com.mbridge.msdk.foundation.same.report.c(com.mbridge.msdk.foundation.controller.a.f().j()).a(requestId, requestIdNotice, id, this.j, "fetch OM failed, exception" + th.getMessage());
                }
            }
        }
        if (!this.c.isReport()) {
            final CampaignEx campaignEx2 = this.c;
            boolean z2 = true;
            if (campaignEx2.isHasMBTplMark()) {
                z2 = false;
            } else {
                final Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
                String str = this.j;
                com.mbridge.msdk.foundation.controller.a.f().a(contextJ);
                if (!TextUtils.isEmpty(campaignEx2.getImpressionURL())) {
                    new Thread(new Runnable() { // from class: com.mbridge.msdk.advanced.b.b.7
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                m.a(i.a(contextJ)).b(campaignEx2.getId());
                            } catch (Exception unused) {
                                s.d(b.this.b, "campain can't insert db");
                            }
                        }
                    }).start();
                    com.mbridge.msdk.click.b.a(contextJ, campaignEx2, str, campaignEx2.getImpressionURL(), false, true);
                }
                if (!TextUtils.isEmpty(str) && campaignEx2.getNativeVideoTracking() != null && campaignEx2.getNativeVideoTracking().n() != null) {
                    com.mbridge.msdk.click.b.a(contextJ, campaignEx2, str, campaignEx2.getNativeVideoTracking().n(), false, false);
                }
                campaignEx2.setReport(true);
                com.mbridge.msdk.foundation.same.a.d.b(this.j, campaignEx2, "h5_native");
            }
            if (z2) {
                Context contextJ2 = com.mbridge.msdk.foundation.controller.a.f().j();
                String str2 = this.j;
                if (campaignEx2 != null) {
                    try {
                        if (!TextUtils.isEmpty(campaignEx2.getOnlyImpressionURL())) {
                            com.mbridge.msdk.click.b.a(contextJ2, campaignEx2, str2, campaignEx2.getOnlyImpressionURL(), false, true);
                        }
                    } catch (Throwable th2) {
                        s.d(this.b, th2.getMessage());
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
            if (this.e != null) {
                this.e.a(this.l);
            }
        }
        c.a(this.j);
        this.a.sendEmptyMessageDelayed(2, 1000L);
    }

    public final void a(boolean z) {
        this.h = z;
    }

    public final void a(final CampaignEx campaignEx, final boolean z, final String str) {
        if (this.f == null) {
            this.f = new com.mbridge.msdk.click.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.j);
        }
        this.f.a(new NativeListener.TrackingExListener() { // from class: com.mbridge.msdk.advanced.b.b.5
            @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
            public final void onDismissLoading(Campaign campaign) {
            }

            @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
            public final void onDownloadFinish(Campaign campaign) {
            }

            @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
            public final void onDownloadProgress(int i) {
            }

            @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
            public final void onDownloadStart(Campaign campaign) {
            }

            @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
            public final void onFinishRedirection(Campaign campaign, String str2) {
            }

            @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
            public final boolean onInterceptDefaultLoadingDialog() {
                return false;
            }

            @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
            public final void onRedirectionFailed(Campaign campaign, String str2) {
            }

            @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
            public final void onShowLoading(Campaign campaign) {
            }

            @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
            public final void onStartRedirection(Campaign campaign, String str2) {
            }

            @Override // com.mbridge.msdk.out.NativeListener.TrackingExListener
            public final void onLeaveApp() {
                if (b.this.e != null) {
                    b.this.e.d(b.this.l);
                }
            }
        });
        if (campaignEx != null) {
            try {
                if (campaignEx.needShowIDialog()) {
                    com.mbridge.msdk.widget.dialog.a aVar = new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.advanced.b.b.6
                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void b() {
                        }

                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void a() {
                            if (z && !TextUtils.isEmpty(str)) {
                                com.mbridge.msdk.advanced.d.a.a(campaignEx, b.this.j, str);
                            }
                            campaignEx.setCampaignUnitId(b.this.j);
                            b.this.f.c(campaignEx);
                            if (!b.this.c.isReportClick()) {
                                b.this.c.setReportClick(true);
                                com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx);
                            }
                            if (b.this.e != null) {
                                b.this.e.b(b.this.l);
                            }
                        }

                        @Override // com.mbridge.msdk.widget.dialog.a
                        public final void c() {
                            a();
                        }
                    };
                    if (com.mbridge.msdk.click.c.a(campaignEx) && campaignEx.needShowIDialog()) {
                        if (!this.f.b(campaignEx)) {
                            e.a().a("", campaignEx, this.d.getContext(), this.j, aVar);
                            return;
                        } else {
                            if (this.e != null) {
                                this.e.b(this.l);
                                return;
                            }
                            return;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        if (z && !TextUtils.isEmpty(str)) {
            com.mbridge.msdk.advanced.d.a.a(campaignEx, this.j, str);
        }
        campaignEx.setCampaignUnitId(this.j);
        this.f.c(campaignEx);
        if (!this.c.isReportClick()) {
            this.c.setReportClick(true);
            com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx);
        }
        if (this.e != null) {
            this.e.b(this.l);
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
        if (this.o != null) {
            this.o = null;
        }
        if (this.n != null) {
            this.n = null;
        }
        if (this.d != null) {
            this.d.destroy();
        }
        if (this.g != null) {
            this.g = null;
        }
    }

    public final void d() {
        MBNativeAdvancedWebview advancedNativeWebview;
        if (this.d == null || (advancedNativeWebview = this.d.getAdvancedNativeWebview()) == null || advancedNativeWebview.isDestoryed()) {
            return;
        }
        com.mbridge.msdk.advanced.js.b.a(advancedNativeWebview, "onViewAppeared", "");
    }

    public final void e() {
        MBNativeAdvancedWebview advancedNativeWebview;
        if (this.d == null || (advancedNativeWebview = this.d.getAdvancedNativeWebview()) == null || advancedNativeWebview.isDestoryed()) {
            return;
        }
        h.a().a((WebView) advancedNativeWebview, "onViewDisappeared", "");
    }

    static /* synthetic */ void a(b bVar, int i) {
        com.mbridge.msdk.advanced.common.c cVarA;
        if (bVar.e != null) {
            bVar.e.c(bVar.l);
            bVar.e = null;
            String str = bVar.j;
            CampaignEx campaignEx = bVar.c;
            if (campaignEx != null && campaignEx.isMraid()) {
                q qVar = new q("2000061", campaignEx.getId(), campaignEx.getRequestId(), campaignEx.getRequestIdNotice(), str, o.C(com.mbridge.msdk.foundation.controller.a.f().j()));
                qVar.a(q.a);
                com.mbridge.msdk.foundation.same.report.b.b(qVar, com.mbridge.msdk.foundation.controller.a.f().j(), str);
            }
        }
        if (bVar.c != null) {
            cVarA = com.mbridge.msdk.advanced.common.c.a().b(bVar.j).d(bVar.c.getRequestId()).g(bVar.c.getRequestIdNotice()).c(bVar.c.getId()).e(bVar.c.getCreativeId() + "").a(bVar.c.isBidCampaign());
        } else {
            cVarA = null;
        }
        String str2 = bVar.j;
        if (cVarA != null) {
            cVarA.a("2000069");
            cVarA.a(i);
            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                com.mbridge.msdk.foundation.same.report.a.a().a(cVarA.b());
            } else {
                com.mbridge.msdk.foundation.same.report.b.a(cVarA.b(), com.mbridge.msdk.foundation.controller.a.f().j(), str2);
            }
        }
        if (bVar.d != null) {
            bVar.d.setVisibility(8);
            bVar.e();
        }
        if (bVar.a != null) {
            bVar.a.removeCallbacksAndMessages(null);
        }
    }
}
