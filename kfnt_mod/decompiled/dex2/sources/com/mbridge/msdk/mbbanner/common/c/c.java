package com.mbridge.msdk.mbbanner.common.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mbridge.msdk.b.d;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.download.download.HTMLResourceManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.q;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.interstitial.view.MBInterstitialActivity;
import com.mbridge.msdk.mbbanner.common.b.e;
import com.mbridge.msdk.mbbanner.common.util.BannerUtils;
import com.mbridge.msdk.mbbanner.view.MBBannerWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.MBBannerView;
import com.mbridge.msdk.out.NativeListener;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

/* compiled from: BannerShowManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private static String b = "BannerShowManager";
    com.mbridge.msdk.click.b a;
    private com.mbridge.msdk.mbbanner.common.b.c c;
    private boolean d;
    private CampaignEx e;
    private MBBannerView f;
    private ImageView g;
    private MBBannerWebView h;
    private ImageView i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private String q;
    private String r;
    private List<CampaignEx> s;
    private int t;
    private com.mbridge.msdk.mbbanner.common.bridge.a v;
    private float y;
    private float z;
    private long u = MBInterstitialActivity.WEB_LOAD_TIME;
    private Handler w = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.mbbanner.common.c.c.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
        }
    };
    private com.mbridge.msdk.foundation.same.e.a x = new com.mbridge.msdk.foundation.same.e.a() { // from class: com.mbridge.msdk.mbbanner.common.c.c.5
        @Override // com.mbridge.msdk.foundation.same.e.a
        public final void cancelTask() {
        }

        @Override // com.mbridge.msdk.foundation.same.e.a
        public final void pauseTask(boolean z) {
        }

        @Override // com.mbridge.msdk.foundation.same.e.a
        public final void runTask() {
            c.this.a("banner render failed because render is timeout");
        }
    };
    private View.OnClickListener A = new View.OnClickListener() { // from class: com.mbridge.msdk.mbbanner.common.c.c.6
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (c.this.p) {
                c.b(c.this);
            }
        }
    };
    private com.mbridge.msdk.mbbanner.common.b.a B = new com.mbridge.msdk.mbbanner.common.b.a() { // from class: com.mbridge.msdk.mbbanner.common.c.c.7
        @Override // com.mbridge.msdk.mbbanner.common.b.a
        public final void a(int i) {
            if (i != 2) {
                c.this.f();
            } else {
                c.c(c.this);
            }
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.a
        public final void a(String str) {
            c.b(c.this);
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.a
        public final void a() {
            c.b(c.this);
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.a
        public final void b(int i) {
            if (i == 1) {
                c.this.d();
                c.this.a("", 1);
            } else {
                c.this.c();
            }
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.a
        public final void a(CampaignEx campaignEx) {
            c.this.a(campaignEx, false, "");
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.a
        public final void a(boolean z) {
            if (c.this.c != null) {
                c.this.o = z;
                if (z) {
                    c.this.c.c();
                } else {
                    c.this.c.d();
                }
            }
        }

        @Override // com.mbridge.msdk.mbbanner.common.b.a
        public final void a(boolean z, String str) {
            try {
                if (c.this.c != null) {
                    if (TextUtils.isEmpty(str)) {
                        c.this.c.a();
                        c.this.c.b();
                    } else {
                        CampaignEx campaignWithBackData = CampaignEx.parseCampaignWithBackData(CampaignEx.campaignToJsonObject(c.this.e));
                        campaignWithBackData.setClickURL(str);
                        c.this.a(campaignWithBackData, z, str);
                    }
                }
            } catch (Exception e) {
                s.d(c.b, e.getMessage());
            }
        }
    };
    private com.mbridge.msdk.mbjscommon.c.b C = new com.mbridge.msdk.mbjscommon.c.b() { // from class: com.mbridge.msdk.mbbanner.common.c.c.4
        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, String str) {
            c.this.l = true;
            s.d("WindVaneWebView", "BANNER onPageFinished");
            s.d("BannerCallJS", "fireOnJSBridgeConnected");
            h.a().a(webView);
            c.this.n = true;
            if (c.this.e == null || c.this.e.isHasMBTplMark()) {
                return;
            }
            c.this.d();
            c.this.a("", 1);
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i, String str, String str2) {
            c.this.a(str);
            c.this.a(str, 2);
        }
    };

    public c(MBBannerView mBBannerView, com.mbridge.msdk.mbbanner.common.b.c cVar, String str, String str2, boolean z, d dVar) {
        this.d = z;
        this.f = mBBannerView;
        this.q = str2;
        this.r = str;
        this.c = new e(cVar, dVar);
    }

    public final void a(boolean z, int i) {
        this.t = i;
        if (i == 0) {
            d dVarF = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), this.q);
            if (dVarF == null) {
                return;
            } else {
                z = dVarF.c() == 1;
            }
        }
        this.d = z;
    }

    public final void a(boolean z) {
        this.d = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.f != null) {
            if (this.h != null && this.h.getParent() != null) {
                this.f.removeView(this.h);
            }
            if (this.g == null) {
                this.g = new ImageView(com.mbridge.msdk.foundation.controller.a.f().j());
                this.g.setOnTouchListener(new View.OnTouchListener() { // from class: com.mbridge.msdk.mbbanner.common.c.c.8
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        c.this.y = motionEvent.getRawX();
                        c.this.z = motionEvent.getRawY();
                        s.d(c.b, c.this.y + "  " + c.this.z);
                        return false;
                    }
                });
                this.g.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.mbbanner.common.c.c.9
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) throws JSONException {
                        c.this.a(BannerUtils.managerCampaignEX(com.mbridge.msdk.mbjscommon.bridge.b.a(c.this.y, c.this.z), c.this.e), false, "");
                    }
                });
            }
            String imageUrl = this.e.getImageUrl();
            if (!TextUtils.isEmpty(imageUrl)) {
                com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(imageUrl, new com.mbridge.msdk.foundation.same.c.c() { // from class: com.mbridge.msdk.mbbanner.common.c.c.10
                    @Override // com.mbridge.msdk.foundation.same.c.c
                    public final void onSuccessLoad(Bitmap bitmap, String str) {
                        if (c.this.g != null) {
                            c.this.g.setImageBitmap(bitmap);
                        }
                        c.this.l = true;
                        c.l(c.this);
                        c.this.f();
                        c.this.d();
                    }

                    @Override // com.mbridge.msdk.foundation.same.c.c
                    public final void onFailedLoad(String str, String str2) {
                        c.this.a("banner show failed because banner default view is exception");
                    }
                });
                return;
            } else {
                a("banner show failed because campain is exception");
                return;
            }
        }
        a("banner show failed because banner view is exception");
    }

    private String a(CampaignEx campaignEx) throws Throwable {
        if (campaignEx == null) {
            return "";
        }
        String htmlContentFromUrl = HTMLResourceManager.getInstance().getHtmlContentFromUrl(campaignEx.getBannerUrl());
        if (!TextUtils.isEmpty(htmlContentFromUrl)) {
            return htmlContentFromUrl;
        }
        String bannerHtml = campaignEx.getBannerHtml();
        if (campaignEx.isActiveOm()) {
            String strA = "";
            try {
                File file = new File(bannerHtml);
                if (file.exists()) {
                    strA = q.a(file);
                }
            } catch (Exception unused) {
                File file2 = new File(bannerHtml);
                if (!file2.exists() || !file2.isFile() || !file2.canRead()) {
                    return bannerHtml;
                }
                strA = "file:////" + bannerHtml;
            }
            return strA;
        }
        File file3 = new File(bannerHtml);
        if (!file3.exists() || !file3.isFile() || !file3.canRead()) {
            return bannerHtml;
        }
        return "file:////" + bannerHtml;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.e == null || e()) {
            return;
        }
        this.w.removeCallbacks(this.x);
        if (this.c != null) {
            this.c.a(str);
        }
        com.mbridge.msdk.foundation.same.report.d.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.e, this.q, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0319  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d() {
        /*
            Method dump skipped, instructions count: 800
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbbanner.common.c.c.d():void");
    }

    private synchronized boolean e() {
        boolean zIsReport;
        zIsReport = this.e.isReport();
        if (!zIsReport) {
            this.e.setReport(true);
        }
        return zIsReport;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (!this.d || this.i == null) {
            return;
        }
        if (this.i.getVisibility() != 0) {
            this.i.setVisibility(0);
            this.i.setOnClickListener(this.A);
        }
        if (this.i.getParent() != null || this.f == null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w.b(com.mbridge.msdk.foundation.controller.a.f().j(), 12.0f), w.b(com.mbridge.msdk.foundation.controller.a.f().j(), 12.0f));
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        this.f.addView(this.i, layoutParams);
    }

    public final void a() {
        if (this.c != null) {
            this.c = null;
        }
        if (this.h != null) {
            this.h.setWebViewListener(null);
        }
        if (this.C != null) {
            this.C = null;
        }
        if (this.i != null) {
            this.i.setOnClickListener(null);
        }
        if (this.g != null) {
            this.g.setOnClickListener(null);
        }
        if (this.f != null) {
            this.f.removeAllViews();
        }
        if (this.h != null) {
            this.h.release();
        }
        if (this.v != null) {
            this.v.a();
        }
        if (this.B != null) {
            this.B = null;
        }
    }

    public final void b(boolean z) {
        this.j = z;
        d();
        if (z) {
            return;
        }
        CampaignEx campaignEx = this.e;
        String str = this.q;
        if (campaignEx != null) {
            String strA = com.mbridge.msdk.mbjscommon.mraid.c.a(campaignEx.getId());
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            new com.mbridge.msdk.foundation.same.report.c(com.mbridge.msdk.foundation.controller.a.f().j()).a(campaignEx.getRequestId(), campaignEx.getRequestIdNotice(), campaignEx.getId(), str, strA, campaignEx.isBidCampaign());
            com.mbridge.msdk.mbjscommon.mraid.c.b(campaignEx.getId());
        }
    }

    public final void c(boolean z) {
        this.k = z;
        d();
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
                s.d(b, th.getMessage());
            }
        }
    }

    private void b(CampaignEx campaignEx, Context context, String str) {
        if (campaignEx != null) {
            try {
                if (TextUtils.isEmpty(campaignEx.getOnlyImpressionURL())) {
                    return;
                }
                com.mbridge.msdk.click.b.a(context, campaignEx, str, campaignEx.getOnlyImpressionURL(), false, true);
            } catch (Throwable th) {
                s.d(b, th.getMessage());
            }
        }
    }

    private void c(final CampaignEx campaignEx, final Context context, String str) {
        if (!TextUtils.isEmpty(campaignEx.getImpressionURL())) {
            new Thread(new Runnable() { // from class: com.mbridge.msdk.mbbanner.common.c.c.12
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        m.a(i.a(context)).b(campaignEx.getId());
                    } catch (Exception unused) {
                        s.d(c.b, "campain can't insert db");
                    }
                }
            }).start();
            com.mbridge.msdk.click.b.a(context, campaignEx, str, campaignEx.getImpressionURL(), false, true);
        }
        if (TextUtils.isEmpty(str) || campaignEx.getNativeVideoTracking() == null || campaignEx.getNativeVideoTracking().n() == null) {
            return;
        }
        com.mbridge.msdk.click.b.a(context, campaignEx, str, campaignEx.getNativeVideoTracking().n(), false, false);
    }

    public final void a(final CampaignEx campaignEx, final boolean z, final String str) {
        if (this.p) {
            if (this.a == null) {
                this.a = new com.mbridge.msdk.click.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.q);
            }
            this.a.a(new NativeListener.TrackingExListener() { // from class: com.mbridge.msdk.mbbanner.common.c.c.2
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
                    if (c.this.c != null) {
                        c.this.c.b();
                    }
                }
            });
            if (campaignEx != null) {
                try {
                    if (campaignEx.needShowIDialog()) {
                        com.mbridge.msdk.widget.dialog.a aVar = new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.mbbanner.common.c.c.3
                            @Override // com.mbridge.msdk.widget.dialog.a
                            public final void a() {
                                campaignEx.setCampaignUnitId(c.this.q);
                                c.this.a.c(campaignEx);
                                if (!c.this.e.isReportClick()) {
                                    c.this.e.setReportClick(true);
                                    com.mbridge.msdk.mbbanner.common.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx);
                                }
                                if (c.this.c != null) {
                                    c.this.c.a();
                                }
                                if (!z || TextUtils.isEmpty(str)) {
                                    return;
                                }
                                com.mbridge.msdk.mbbanner.common.d.a.a(campaignEx, c.this.q, str);
                            }

                            @Override // com.mbridge.msdk.widget.dialog.a
                            public final void b() {
                                if (c.this.c != null) {
                                    c.this.c.a(false);
                                }
                            }

                            @Override // com.mbridge.msdk.widget.dialog.a
                            public final void c() {
                                a();
                            }
                        };
                        if (com.mbridge.msdk.click.c.a(campaignEx) && campaignEx.needShowIDialog()) {
                            if (!this.a.b(campaignEx)) {
                                com.mbridge.msdk.mbjscommon.confirmation.e.a().a("", campaignEx, this.f.getContext(), this.q, aVar);
                                if (this.c != null) {
                                    this.c.a(true);
                                    return;
                                }
                                return;
                            }
                            if (this.c != null) {
                                this.c.a();
                                return;
                            }
                            return;
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            campaignEx.setCampaignUnitId(this.q);
            this.a.c(campaignEx);
            if (!this.e.isReportClick()) {
                this.e.setReportClick(true);
                com.mbridge.msdk.mbbanner.common.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx);
            }
            if (this.c != null) {
                this.c.a();
            }
            if (!z || TextUtils.isEmpty(str)) {
                return;
            }
            com.mbridge.msdk.mbbanner.common.d.a.a(campaignEx, this.q, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) {
        if (this.e != null) {
            com.mbridge.msdk.mbbanner.common.d.a.a(com.mbridge.msdk.mbbanner.common.a.a.a().c(this.e.getId()).b(this.q).d(this.e.getRequestId()).e(this.e.getRequestIdNotice()).g(str).a(i).a(this.e.isBidCampaign()), this.q);
        }
    }

    public final void a(int i, int i2, int i3, int i4) {
        if (i == i3 && i2 == i4) {
            return;
        }
        MBBannerWebView mBBannerWebView = this.h;
        s.d("BannerCallJS", "fireOnBannerViewSizeChange");
        try {
            com.mbridge.msdk.mbjscommon.mraid.a.a().a(mBBannerWebView, i, i2);
        } catch (Throwable th) {
            s.b("BannerCallJS", "fireOnBannerViewSizeChange", th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.mbridge.msdk.foundation.entity.CampaignUnit r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 475
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbbanner.common.c.c.a(com.mbridge.msdk.foundation.entity.CampaignUnit):void");
    }

    static /* synthetic */ void b(c cVar) {
        if (cVar.h != null && cVar.h.getParent() != null) {
            cVar.f.removeView(cVar.h);
        }
        if (cVar.g != null && cVar.g.getParent() != null) {
            cVar.g.setVisibility(8);
            cVar.f.removeView(cVar.g);
        }
        if (cVar.i != null && cVar.i.getParent() != null) {
            cVar.f.removeView(cVar.i);
            cVar.i.setVisibility(8);
        }
        com.mbridge.msdk.mbbanner.common.a.a aVarA = com.mbridge.msdk.mbbanner.common.a.a.a().b(cVar.q).d(cVar.e.getRequestId()).e(cVar.e.getRequestIdNotice()).c(cVar.e.getId()).f(cVar.e.getCreativeId() + "").a(cVar.e.isBidCampaign());
        String str = cVar.q;
        if (aVarA != null) {
            aVarA.a("2000069");
            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                com.mbridge.msdk.foundation.same.report.a.a().a(aVarA.b());
            } else {
                com.mbridge.msdk.foundation.same.report.b.a(aVarA.b(), com.mbridge.msdk.foundation.controller.a.f().j(), str);
            }
        }
        BannerUtils.inserCloseId(cVar.q, cVar.s);
        if (cVar.c != null) {
            cVar.c.e();
        }
    }

    static /* synthetic */ void c(c cVar) {
        if (cVar.d && cVar.i != null && cVar.i.getVisibility() == 0) {
            cVar.i.setVisibility(8);
            cVar.i.setOnClickListener(null);
            if (cVar.f == null || cVar.i.getParent() == null) {
                return;
            }
            cVar.f.removeView(cVar.i);
        }
    }

    static /* synthetic */ void l(c cVar) {
        if (cVar.g != null) {
            if (cVar.h != null) {
                cVar.h.setVisibility(8);
            }
            if (cVar.g.getVisibility() != 0) {
                cVar.g.setVisibility(0);
            }
            if (cVar.f != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(10);
                cVar.g.setScaleType(ImageView.ScaleType.FIT_XY);
                if (cVar.g.getParent() == null) {
                    cVar.f.addView(cVar.g, layoutParams);
                }
            }
        }
    }
}
