package com.mbridge.msdk.advanced.b;

import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.mbridge.msdk.advanced.view.MBNativeAdvancedView;
import com.mbridge.msdk.advanced.view.MBNativeAdvancedWebview;
import com.mbridge.msdk.foundation.db.f;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ResManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    public static f a = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
    private static String b = "ResManager";
    private static int c = 1;

    public static CampaignEx a(MBNativeAdvancedView mBNativeAdvancedView, String str, String str2, String str3, int i, boolean z, boolean z2) {
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        long jL = aVarB.L() * 1000;
        long jB = aVarB.B() * 1000;
        if (a == null) {
            a = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        }
        a.b(jB, str2);
        if (a == null) {
            a = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        }
        List<CampaignEx> listB = a.b(str2, 0, 0, c);
        if (listB == null || listB.size() <= 0) {
            return null;
        }
        CampaignEx campaignEx = listB.get(0);
        if (TextUtils.isEmpty(campaignEx.getAdZip()) && TextUtils.isEmpty(campaignEx.getAdHtml())) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - jL;
        if (!z) {
            if ((campaignEx.getPlct() > 0 && (campaignEx.getPlct() * 1000) + campaignEx.getTimestamp() >= jCurrentTimeMillis) || (campaignEx.getPlct() <= 0 && campaignEx.getTimestamp() >= j)) {
                if (!a(mBNativeAdvancedView, campaignEx, str, str2, i, z2)) {
                    return null;
                }
                s.d(b, "cache campain is picked:" + campaignEx.getAppName());
                return a(campaignEx);
            }
            s.a(b, "========已经超了缓存时间");
            if (campaignEx.isSpareOffer(jL, jB) || mBNativeAdvancedView == null) {
                return null;
            }
            mBNativeAdvancedView.setVisibility(8);
            return null;
        }
        if ((campaignEx.getPlct() > 0 && (campaignEx.getPlct() * 1000) + campaignEx.getTimestamp() >= jCurrentTimeMillis) || (campaignEx.getPlct() <= 0 && campaignEx.getTimestamp() >= j)) {
            if (!a(mBNativeAdvancedView, campaignEx, str, str2, i, z2)) {
                return null;
            }
            s.d(b, "cache campain is picked:" + campaignEx.getAppName());
            return a(campaignEx);
        }
        if (a(mBNativeAdvancedView, campaignEx, str, str2, i, z2) && campaignEx.isSpareOffer(jL, jB)) {
            return a(campaignEx);
        }
        if (campaignEx.isSpareOffer(jL, jB) || mBNativeAdvancedView == null) {
            return null;
        }
        mBNativeAdvancedView.setVisibility(8);
        return null;
    }

    private static CampaignEx a(CampaignEx campaignEx) {
        if (!TextUtils.isEmpty(campaignEx.getAdZip()) || (!TextUtils.isEmpty(campaignEx.getAdHtml()) && campaignEx.getAdHtml().contains("<MBTPLMARK>"))) {
            campaignEx.setHasMBTplMark(true);
            campaignEx.setIsMraid(false);
        } else {
            campaignEx.setHasMBTplMark(false);
            campaignEx.setIsMraid(true);
        }
        return campaignEx;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(com.mbridge.msdk.advanced.view.MBNativeAdvancedView r11, com.mbridge.msdk.foundation.entity.CampaignEx r12, java.lang.String r13, java.lang.String r14, int r15, boolean r16) {
        /*
            r6 = r11
            r7 = 0
            if (r12 == 0) goto Ld8
            r11.clearResState()
            java.lang.String r0 = r12.getVideoUrlEncode()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r8 = 1
            if (r0 != 0) goto L26
            com.mbridge.msdk.videocommon.download.b r0 = com.mbridge.msdk.videocommon.download.b.getInstance()
            r1 = 298(0x12a, float:4.18E-43)
            boolean r2 = r12.isBidCampaign()
            r9 = r14
            boolean r0 = r0.a(r1, r14, r2)
            if (r0 == 0) goto L24
            goto L28
        L24:
            r10 = r0
            goto L2c
        L26:
            r9 = r14
            r0 = 1
        L28:
            r11.setVideoReady(r8)
            goto L24
        L2c:
            java.lang.String r0 = r12.getendcard_url()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L51
            boolean r0 = r11.isEndCardReady()
            if (r0 != 0) goto L51
            com.mbridge.msdk.foundation.download.download.H5DownLoadManager r0 = com.mbridge.msdk.foundation.download.download.H5DownLoadManager.getInstance()
            java.lang.String r1 = r12.getendcard_url()
            java.lang.String r0 = r0.getH5ResAddress(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L51
            r11.setEndCardReady(r8)
        L51:
            java.lang.String r0 = r12.getAdZip()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L8c
            boolean r0 = r11.isH5Ready()
            if (r0 != 0) goto L8c
            com.mbridge.msdk.foundation.download.download.H5DownLoadManager r0 = com.mbridge.msdk.foundation.download.download.H5DownLoadManager.getInstance()
            java.lang.String r1 = r12.getAdZip()
            java.lang.String r1 = r0.getH5ResAddress(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L8b
            java.lang.String r0 = r12.getRequestId()
            boolean r0 = com.mbridge.msdk.advanced.common.b.a(r0)
            if (r0 == 0) goto L81
            r11.setH5Ready(r8)
            goto L8c
        L81:
            r0 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            a(r0, r1, r2, r3, r4, r5)
            if (r16 != 0) goto L8c
        L8b:
            r10 = 0
        L8c:
            java.lang.String r0 = r12.getAdZip()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto Ld7
            java.lang.String r0 = r12.getAdHtml()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto Ld7
            boolean r0 = r11.isH5Ready()
            if (r0 != 0) goto Ld7
            java.lang.String r0 = r12.getAdHtml()
            java.lang.String r1 = b(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto Ld8
            java.lang.String r0 = r12.getRequestId()
            boolean r0 = com.mbridge.msdk.advanced.common.b.a(r0)
            if (r0 == 0) goto Lcc
            r11.setH5Ready(r8)
            boolean r0 = r12.isMraid()
            if (r0 == 0) goto Lca
            r11.setVideoReady(r8)
        Lca:
            r7 = 1
            goto Ld8
        Lcc:
            r0 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            a(r0, r1, r2, r3, r4, r5)
            if (r16 != 0) goto Ld7
            goto Ld8
        Ld7:
            r7 = r10
        Ld8:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.advanced.b.c.a(com.mbridge.msdk.advanced.view.MBNativeAdvancedView, com.mbridge.msdk.foundation.entity.CampaignEx, java.lang.String, java.lang.String, int, boolean):boolean");
    }

    private static void a(final MBNativeAdvancedView mBNativeAdvancedView, final String str, final CampaignEx campaignEx, String str2, final String str3, int i) {
        if (mBNativeAdvancedView == null || mBNativeAdvancedView.getAdvancedNativeWebview() == null) {
            return;
        }
        com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.advanced.common.c.a().c(campaignEx.getId()).d(campaignEx.getRequestId()).g(campaignEx.getRequestIdNotice()).b(str3).a(campaignEx.isBidCampaign()), str3);
        com.mbridge.msdk.advanced.js.a aVar = new com.mbridge.msdk.advanced.js.a(mBNativeAdvancedView.getContext(), str2, str3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(campaignEx);
        aVar.a(arrayList);
        aVar.a(i);
        mBNativeAdvancedView.setAdvancedNativeJSBridgeImpl(aVar);
        final MBNativeAdvancedWebview advancedNativeWebview = mBNativeAdvancedView.getAdvancedNativeWebview();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        advancedNativeWebview.setWebViewListener(new com.mbridge.msdk.mbjscommon.c.b() { // from class: com.mbridge.msdk.advanced.b.c.1
            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, int i2) {
                super.a(webView, i2);
                if (i2 == 1) {
                    com.mbridge.msdk.advanced.common.b.a(campaignEx.getRequestId(), true);
                    mBNativeAdvancedView.setH5Ready(true);
                    s.a("WindVaneWebView", "======渲染成功：ready");
                    com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str3, "", jCurrentTimeMillis, 1);
                    return;
                }
                mBNativeAdvancedView.setH5Ready(false);
                s.a("WindVaneWebView", "======渲染失败");
                com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str3, "readyState 2", jCurrentTimeMillis, 3);
            }

            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, int i2, String str4, String str5) {
                super.a(webView, i2, str4, str5);
                mBNativeAdvancedView.setH5Ready(false);
                s.a("WindVaneWebView", "======渲染失败");
                com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str3, "error code:" + i2 + str4, jCurrentTimeMillis, 3);
            }

            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                super.a(webView, sslErrorHandler, sslError);
                mBNativeAdvancedView.setH5Ready(false);
                s.a("WindVaneWebView", "======渲染失败");
                com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str3, "error url:" + sslError.getUrl(), jCurrentTimeMillis, 3);
            }

            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, String str4) {
                super.a(webView, str4);
                if (!campaignEx.isHasMBTplMark()) {
                    com.mbridge.msdk.advanced.common.b.a(campaignEx.getRequestId(), true);
                    mBNativeAdvancedView.setH5Ready(true);
                    s.a("WindVaneWebView", "======渲染成功：finish");
                    com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str3, "", jCurrentTimeMillis, 1);
                }
                com.mbridge.msdk.advanced.js.b.a(webView);
            }
        });
        if (!advancedNativeWebview.isDestoryed()) {
            s.a(b, "======开始渲染：" + str);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.mbridge.msdk.advanced.b.c.2
                @Override // java.lang.Runnable
                public final void run() {
                    advancedNativeWebview.loadUrl(str);
                }
            });
            return;
        }
        mBNativeAdvancedView.setH5Ready(false);
        com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str3, "webview had destory", jCurrentTimeMillis, 3);
    }

    private static String b(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                return "";
            }
            return "file:///" + file.getAbsolutePath();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Exception e) {
                e.getMessage();
                return "";
            }
        }
    }

    public static boolean a(MBNativeAdvancedView mBNativeAdvancedView, CampaignEx campaignEx, String str, String str2) {
        boolean z = false;
        if (mBNativeAdvancedView == null) {
            s.d(b, "mbAdvancedNativeView  is null");
            return false;
        }
        boolean zA = true;
        if (!TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
            zA = mBNativeAdvancedView.isVideoReady();
            s.d(b, "======isReady isVideoReady:" + zA);
        }
        if (zA && !TextUtils.isEmpty(campaignEx.getAdZip())) {
            zA = com.mbridge.msdk.advanced.common.b.a(campaignEx.getRequestId());
            s.d(b, "======isReady getAdZip:" + zA);
        }
        if (zA && TextUtils.isEmpty(campaignEx.getAdZip()) && !TextUtils.isEmpty(campaignEx.getAdHtml())) {
            zA = com.mbridge.msdk.advanced.common.b.a(campaignEx.getRequestId());
            s.d(b, "======isReady getAdHtml:" + zA);
        }
        if (TextUtils.isEmpty(campaignEx.getAdZip()) && TextUtils.isEmpty(campaignEx.getAdHtml())) {
            s.d(b, "======isReady getAdHtml  getAdZip all are empty");
        } else {
            z = zA;
        }
        if (!z || TextUtils.isEmpty(campaignEx.getendcard_url())) {
            return z;
        }
        boolean zIsEndCardReady = mBNativeAdvancedView.isEndCardReady();
        s.d(b, "======isReady isEndCardReady:" + zIsEndCardReady);
        return zIsEndCardReady;
    }

    public static void a(CampaignEx campaignEx, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(campaignEx);
        if (a == null) {
            a = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        }
        a.b(arrayList, str);
    }

    public static void a(String str) {
        if (a == null) {
            a = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        }
        a.a(str, 0, c);
    }
}
