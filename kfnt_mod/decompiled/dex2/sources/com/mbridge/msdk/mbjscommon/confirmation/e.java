package com.mbridge.msdk.mbjscommon.confirmation;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.widget.custom.CustomViewMessageWrap;
import com.mbridge.msdk.widget.custom.DownloadMessageDialog;
import com.mbridge.msdk.widget.custom.a;
import com.mbridge.msdk.widget.custom.baseview.MBButton;
import com.mbridge.msdk.widget.dialog.MBAlertDialog;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: SecondaryConfirmationManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class e implements Serializable {
    public static int b = 1;
    public static int c = 0;
    public static int d = 2;
    HashMap<String, Integer> a;
    private WindVaneWebView e;
    private WindVaneWebView f;
    private com.mbridge.msdk.mbjscommon.confirmation.b g;
    private com.mbridge.msdk.mbjscommon.confirmation.b h;
    private c i;
    private int j;
    private MBAlertDialog k;
    private DownloadMessageDialog l;
    private AtomicBoolean m;
    private AtomicBoolean n;
    private volatile boolean o;
    private Timer p;
    private a q;
    private final Handler r;

    private e() {
        this.a = new HashMap<>();
        this.j = 1;
        this.m = new AtomicBoolean(false);
        this.n = new AtomicBoolean(false);
        this.o = false;
        this.p = new Timer();
        this.r = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.mbjscommon.confirmation.e.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1:
                        if (e.this.g != null) {
                            e.this.g.a(1, "render is time out");
                            break;
                        } else {
                            e.this.b();
                            break;
                        }
                    case 2:
                        if (e.this.h != null) {
                            e.this.h.a(1, "render is time out");
                            break;
                        }
                        break;
                }
            }
        };
    }

    /* compiled from: SecondaryConfirmationManager.java */
    static class b {
        private static final e a = new e();
    }

    public static e a() {
        return b.a;
    }

    private String a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), str2) != null) {
            str = com.mbridge.msdk.b.d.a;
        }
        return !TextUtils.isEmpty(str) ? str : "";
    }

    public final void b() {
        this.p.cancel();
        this.o = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x018c A[Catch: Throwable -> 0x0210, all -> 0x0224, TryCatch #1 {Throwable -> 0x0210, blocks: (B:49:0x00fb, B:51:0x00ff, B:53:0x0105, B:54:0x0113, B:56:0x0119, B:57:0x0127, B:59:0x012c, B:61:0x0134, B:63:0x013e, B:65:0x014c, B:70:0x0157, B:72:0x015b, B:74:0x0163, B:76:0x016d, B:78:0x017b, B:80:0x0181, B:82:0x0188, B:84:0x018c, B:89:0x01b1, B:91:0x01cc, B:92:0x01f3, B:86:0x0194, B:88:0x01a8, B:94:0x020c), top: B:106:0x00fb, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0194 A[Catch: Throwable -> 0x0210, all -> 0x0224, TryCatch #1 {Throwable -> 0x0210, blocks: (B:49:0x00fb, B:51:0x00ff, B:53:0x0105, B:54:0x0113, B:56:0x0119, B:57:0x0127, B:59:0x012c, B:61:0x0134, B:63:0x013e, B:65:0x014c, B:70:0x0157, B:72:0x015b, B:74:0x0163, B:76:0x016d, B:78:0x017b, B:80:0x0181, B:82:0x0188, B:84:0x018c, B:89:0x01b1, B:91:0x01cc, B:92:0x01f3, B:86:0x0194, B:88:0x01a8, B:94:0x020c), top: B:106:0x00fb, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01a8 A[Catch: Throwable -> 0x0210, all -> 0x0224, TryCatch #1 {Throwable -> 0x0210, blocks: (B:49:0x00fb, B:51:0x00ff, B:53:0x0105, B:54:0x0113, B:56:0x0119, B:57:0x0127, B:59:0x012c, B:61:0x0134, B:63:0x013e, B:65:0x014c, B:70:0x0157, B:72:0x015b, B:74:0x0163, B:76:0x016d, B:78:0x017b, B:80:0x0181, B:82:0x0188, B:84:0x018c, B:89:0x01b1, B:91:0x01cc, B:92:0x01f3, B:86:0x0194, B:88:0x01a8, B:94:0x020c), top: B:106:0x00fb, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01cc A[Catch: Throwable -> 0x0210, all -> 0x0224, TryCatch #1 {Throwable -> 0x0210, blocks: (B:49:0x00fb, B:51:0x00ff, B:53:0x0105, B:54:0x0113, B:56:0x0119, B:57:0x0127, B:59:0x012c, B:61:0x0134, B:63:0x013e, B:65:0x014c, B:70:0x0157, B:72:0x015b, B:74:0x0163, B:76:0x016d, B:78:0x017b, B:80:0x0181, B:82:0x0188, B:84:0x018c, B:89:0x01b1, B:91:0x01cc, B:92:0x01f3, B:86:0x0194, B:88:0x01a8, B:94:0x020c), top: B:106:0x00fb, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01f3 A[Catch: Throwable -> 0x0210, all -> 0x0224, TryCatch #1 {Throwable -> 0x0210, blocks: (B:49:0x00fb, B:51:0x00ff, B:53:0x0105, B:54:0x0113, B:56:0x0119, B:57:0x0127, B:59:0x012c, B:61:0x0134, B:63:0x013e, B:65:0x014c, B:70:0x0157, B:72:0x015b, B:74:0x0163, B:76:0x016d, B:78:0x017b, B:80:0x0181, B:82:0x0188, B:84:0x018c, B:89:0x01b1, B:91:0x01cc, B:92:0x01f3, B:86:0x0194, B:88:0x01a8, B:94:0x020c), top: B:106:0x00fb, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void a(java.lang.String r20, final com.mbridge.msdk.foundation.entity.CampaignEx r21, android.content.Context r22, final java.lang.String r23, com.mbridge.msdk.widget.dialog.a r24) {
        /*
            Method dump skipped, instructions count: 551
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbjscommon.confirmation.e.a(java.lang.String, com.mbridge.msdk.foundation.entity.CampaignEx, android.content.Context, java.lang.String, com.mbridge.msdk.widget.dialog.a):void");
    }

    public final void a(String str, final CampaignEx campaignEx, Context context, final String str2, c cVar) {
        final String strA = a(str, str2);
        this.h = new com.mbridge.msdk.mbjscommon.confirmation.b() { // from class: com.mbridge.msdk.mbjscommon.confirmation.e.3
            @Override // com.mbridge.msdk.mbjscommon.confirmation.b
            public final void a() {
                com.mbridge.msdk.mbjscommon.confirmation.a aVar;
                e.this.r.removeMessages(2);
                if (e.this.f != null && (aVar = (com.mbridge.msdk.mbjscommon.confirmation.a) e.this.f.getObject()) != null) {
                    aVar.a(e.b);
                    e.this.f.setObject(aVar);
                }
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, strA, str2, 1, "", 2);
            }

            @Override // com.mbridge.msdk.mbjscommon.confirmation.b
            public final void a(int i, String str3) {
                com.mbridge.msdk.mbjscommon.confirmation.a aVar;
                e.this.r.removeMessages(2);
                if (e.this.f != null && (aVar = (com.mbridge.msdk.mbjscommon.confirmation.a) e.this.f.getObject()) != null) {
                    aVar.a(e.d);
                    e.this.f.setObject(aVar);
                }
                if (i != 5) {
                    com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, strA, str2, 2, str3, 2);
                }
            }
        };
        if (TextUtils.isEmpty(str)) {
            this.h.a(2, "url is null");
            return;
        }
        if (!str.contains("ldtm=1")) {
            this.h.a(5, "unwanted prerender");
            return;
        }
        if (this.f == null || this.f.isDestoryed()) {
            this.f = new WindVaneWebView(context);
            this.f.setBackgroundColor(0);
            if (this.f.getBackground() != null) {
                this.f.getBackground().setAlpha(0);
            }
        }
        this.n.compareAndSet(true, false);
        this.r.sendEmptyMessageDelayed(2, 500L);
        a(this.f, str, campaignEx, context, str2, cVar, this.h);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView r8, java.lang.String r9, com.mbridge.msdk.foundation.entity.CampaignEx r10, android.content.Context r11, java.lang.String r12, com.mbridge.msdk.mbjscommon.confirmation.c r13, final com.mbridge.msdk.mbjscommon.confirmation.b r14) {
        /*
            r7 = this;
            com.mbridge.msdk.mbjscommon.base.b r11 = new com.mbridge.msdk.mbjscommon.base.b
            r11.<init>()
            com.mbridge.msdk.mbjscommon.confirmation.e$4 r13 = new com.mbridge.msdk.mbjscommon.confirmation.e$4
            r13.<init>()
            r11.a(r13)
            com.mbridge.msdk.mbjscommon.confirmation.a r13 = new com.mbridge.msdk.mbjscommon.confirmation.a
            r13.<init>(r10, r9, r12)
            int r10 = com.mbridge.msdk.mbjscommon.confirmation.e.c
            r13.a(r10)
            r8.setObject(r13)
            r8.setWebViewClient(r11)
            r10 = 3
            boolean r11 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Exception -> Ld7
            if (r11 != 0) goto L33
            com.mbridge.msdk.foundation.download.download.H5DownLoadManager r11 = com.mbridge.msdk.foundation.download.download.H5DownLoadManager.getInstance()     // Catch: java.lang.Exception -> Ld7
            java.lang.String r11 = r11.getH5ResAddress(r9)     // Catch: java.lang.Exception -> Ld7
            boolean r12 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Exception -> Ld7
            if (r12 != 0) goto L33
            goto L34
        L33:
            r11 = r9
        L34:
            com.mbridge.msdk.foundation.download.download.HTMLResourceManager r12 = com.mbridge.msdk.foundation.download.download.HTMLResourceManager.getInstance()     // Catch: java.lang.Exception -> Ld7
            java.lang.String r12 = r12.getHtmlContentFromUrl(r9)     // Catch: java.lang.Exception -> Ld7
            java.lang.String r13 = ""
            boolean r0 = android.text.TextUtils.isEmpty(r12)     // Catch: java.lang.Exception -> Ld7
            if (r0 != 0) goto L46
            r3 = r12
            goto L47
        L46:
            r3 = r13
        L47:
            boolean r12 = r8.isDestoryed()     // Catch: java.lang.Exception -> Ld7
            if (r12 != 0) goto Lcf
            boolean r12 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> Ld7
            if (r12 != 0) goto La3
            java.lang.String r2 = r7.a(r9)     // Catch: java.lang.Exception -> Ld7
            java.lang.String r9 = "SecondaryConfirmationManager"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld7
            r12.<init>()     // Catch: java.lang.Exception -> Ld7
            java.lang.String r13 = "load====== html url:"
            r12.append(r13)     // Catch: java.lang.Exception -> Ld7
            r12.append(r11)     // Catch: java.lang.Exception -> Ld7
            java.lang.String r11 = r12.toString()     // Catch: java.lang.Exception -> Ld7
            com.mbridge.msdk.foundation.tools.s.a(r9, r11)     // Catch: java.lang.Exception -> Ld7
            java.lang.String r9 = "SecondaryConfirmationManager"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld7
            r11.<init>()     // Catch: java.lang.Exception -> Ld7
            java.lang.String r12 = "load===== html..."
            r11.append(r12)     // Catch: java.lang.Exception -> Ld7
            r11.append(r3)     // Catch: java.lang.Exception -> Ld7
            java.lang.String r11 = r11.toString()     // Catch: java.lang.Exception -> Ld7
            com.mbridge.msdk.foundation.tools.s.a(r9, r11)     // Catch: java.lang.Exception -> Ld7
            java.lang.String r9 = "SecondaryConfirmationManager"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld7
            r11.<init>()     // Catch: java.lang.Exception -> Ld7
            java.lang.String r12 = "load=====lastUrl html..."
            r11.append(r12)     // Catch: java.lang.Exception -> Ld7
            r11.append(r2)     // Catch: java.lang.Exception -> Ld7
            java.lang.String r11 = r11.toString()     // Catch: java.lang.Exception -> Ld7
            com.mbridge.msdk.foundation.tools.s.a(r9, r11)     // Catch: java.lang.Exception -> Ld7
            java.lang.String r4 = "text/html"
            java.lang.String r5 = "UTF-8"
            r6 = 0
            r1 = r8
            r1.loadDataWithBaseURL(r2, r3, r4, r5, r6)     // Catch: java.lang.Exception -> Ld7
            goto Le4
        La3:
            boolean r9 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Exception -> Ld7
            if (r9 == 0) goto Lb1
            if (r14 == 0) goto Le4
            java.lang.String r8 = "localUrl is null"
            r14.a(r10, r8)     // Catch: java.lang.Exception -> Ld7
            goto Le4
        Lb1:
            java.lang.String r9 = r7.a(r11)     // Catch: java.lang.Exception -> Ld7
            java.lang.String r11 = "SecondaryConfirmationManager"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld7
            r12.<init>()     // Catch: java.lang.Exception -> Ld7
            java.lang.String r13 = "load====== url:"
            r12.append(r13)     // Catch: java.lang.Exception -> Ld7
            r12.append(r9)     // Catch: java.lang.Exception -> Ld7
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Exception -> Ld7
            com.mbridge.msdk.foundation.tools.s.a(r11, r12)     // Catch: java.lang.Exception -> Ld7
            r8.loadUrl(r9)     // Catch: java.lang.Exception -> Ld7
            goto Le4
        Lcf:
            if (r14 == 0) goto Le4
            java.lang.String r8 = "webview has destoryed"
            r14.a(r10, r8)     // Catch: java.lang.Exception -> Ld7
            goto Le4
        Ld7:
            r8 = move-exception
            r8.printStackTrace()
            if (r14 == 0) goto Le4
            java.lang.String r8 = r8.getMessage()
            r14.a(r10, r8)
        Le4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbjscommon.confirmation.e.a(com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView, java.lang.String, com.mbridge.msdk.foundation.entity.CampaignEx, android.content.Context, java.lang.String, com.mbridge.msdk.mbjscommon.confirmation.c, com.mbridge.msdk.mbjscommon.confirmation.b):void");
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        builderBuildUpon.appendQueryParameter("dialogRenderMode", "default");
        return builderBuildUpon.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CampaignEx campaignEx, Context context, String str, String str2, c cVar) {
        if (this.k != null) {
            this.k.makeDownloadAlert(campaignEx.getAppName());
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
                    com.mbridge.msdk.foundation.same.report.d.a(context, campaignEx, str, str2, 4, 2);
                    b();
                    return;
                } else {
                    if (!activity.isFinishing()) {
                        this.k.show();
                        this.j = 1;
                        a(this.j);
                        cVar.a(this.j);
                        com.mbridge.msdk.foundation.same.report.d.a(context, campaignEx, str, str2, 1, 2);
                        return;
                    }
                    com.mbridge.msdk.foundation.same.report.d.a(context, campaignEx, str, str2, 4, 2);
                    b();
                    return;
                }
            }
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WindVaneWebView windVaneWebView, String str, CampaignEx campaignEx, Context context, String str2, c cVar) {
        if (windVaneWebView != null && !windVaneWebView.isDestoryed()) {
            a(windVaneWebView);
            if (windVaneWebView.getParent() != null) {
                ((ViewGroup) windVaneWebView.getParent()).removeView(windVaneWebView);
            }
            this.k.setContentView(windVaneWebView);
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
                    com.mbridge.msdk.foundation.same.report.d.a(context, campaignEx, str, str2, 4, 2);
                    b();
                    return;
                } else {
                    if (!activity.isFinishing()) {
                        this.k.show();
                        this.j = 2;
                        a(this.j);
                        cVar.a(this.j);
                        com.mbridge.msdk.foundation.same.report.d.a(context, campaignEx, str, str2, 2, 2);
                        return;
                    }
                    com.mbridge.msdk.foundation.same.report.d.a(context, campaignEx, str, str2, 4, 2);
                    b();
                    return;
                }
            }
            b();
            return;
        }
        b(str, campaignEx, context, str2, cVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, final CampaignEx campaignEx, final Context context, final String str2, final c cVar) {
        CloseDialogBroadcastReceiver closeDialogBroadcastReceiver;
        final d[] dVarArr = {null};
        this.l = new DownloadMessageDialog(context, true, new CustomViewMessageWrap(campaignEx, CustomViewMessageWrap.getViewFilePath("mbridge_download_dialog_view")), new com.mbridge.msdk.widget.custom.a() { // from class: com.mbridge.msdk.mbjscommon.confirmation.e.6
            @Override // com.mbridge.msdk.widget.custom.a
            public final void a(DownloadMessageDialog downloadMessageDialog, CustomViewMessageWrap customViewMessageWrap) {
            }

            @Override // com.mbridge.msdk.widget.custom.a
            public final void b(DownloadMessageDialog downloadMessageDialog, CustomViewMessageWrap customViewMessageWrap) {
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str, str2, 1, "", 2);
            }

            @Override // com.mbridge.msdk.widget.custom.a
            public final void c(DownloadMessageDialog downloadMessageDialog, CustomViewMessageWrap customViewMessageWrap) {
                if (e.this.k != null) {
                    e.this.k.clear();
                    e.this.k.setOnDismissListener(null);
                    e.this.k = null;
                }
            }

            @Override // com.mbridge.msdk.widget.custom.a
            public final void a(DownloadMessageDialog downloadMessageDialog, CustomViewMessageWrap customViewMessageWrap, Exception exc) {
                e.this.a(campaignEx, context, str, str2, cVar);
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str, str2, 2, exc.getMessage(), 2);
            }

            @Override // com.mbridge.msdk.widget.custom.a
            public final void a(DownloadMessageDialog downloadMessageDialog, CustomViewMessageWrap customViewMessageWrap, a.EnumC0147a enumC0147a) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                if (enumC0147a == a.EnumC0147a.CLICK_EVENT_CLOSE) {
                    if (downloadMessageDialog != null) {
                        downloadMessageDialog.dismiss();
                    }
                    if (dVarArr[0] != null) {
                        dVarArr[0].b();
                        return;
                    }
                    return;
                }
                if (enumC0147a == a.EnumC0147a.CLICK_EVENT_DOWNLOAD) {
                    MBButton mBButton = (MBButton) customViewMessageWrap.getView("button_download");
                    if (dVarArr[0] == null) {
                        dVarArr[0] = new d(mBButton);
                        dVarArr[0].b(campaignEx.getLinkType());
                        dVarArr[0].a(campaignEx.getAkdlui());
                        dVarArr[0].b(str);
                    }
                    dVarArr[0].a();
                    if (cVar != null) {
                        cVar.a(e.this.j);
                        cVar.c();
                        return;
                    }
                    return;
                }
                if (enumC0147a == a.EnumC0147a.CLICK_EVENT_OPEN_PRIVACY) {
                    String applicationPrivacy = customViewMessageWrap.getApplicationPrivacy();
                    if (TextUtils.isEmpty(applicationPrivacy)) {
                        return;
                    }
                    com.mbridge.msdk.click.c.b(com.mbridge.msdk.foundation.controller.a.f().j(), applicationPrivacy);
                }
            }
        });
        if (context == null || campaignEx == null || !w.a(campaignEx)) {
            closeDialogBroadcastReceiver = null;
        } else {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("mb_dp_close_broadcast_receiver");
                CloseDialogBroadcastReceiver closeDialogBroadcastReceiver2 = new CloseDialogBroadcastReceiver(this.l);
                try {
                    context.registerReceiver(closeDialogBroadcastReceiver2, intentFilter);
                } catch (Exception unused) {
                }
                closeDialogBroadcastReceiver = closeDialogBroadcastReceiver2;
            } catch (Exception e) {
                s.a("SecondaryConfirmationManager", e.getMessage());
            }
        }
        this.j = 1;
        this.l.setOnDismissListener(new a(closeDialogBroadcastReceiver, str + campaignEx.getApkDisplayInfo().toString(), cVar, this.e, this.f, this.j));
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str, str2, 4, 2);
                b();
                return;
            } else if (!activity.isFinishing()) {
                this.l.show();
                cVar.a(this.j);
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str, str2, 3, 2);
                return;
            } else {
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str, str2, 4, 2);
                b();
                return;
            }
        }
        b();
    }

    public final void a(Object obj, int i) {
        if (this.e != null && this.e.getObject() != null && this.e.getObject().equals(a(obj)) && !this.m.compareAndSet(true, true) && this.g != null) {
            if (i == b) {
                this.g.a();
            } else {
                this.g.a(0, "webview render error and undefault");
            }
        }
        if (this.f == null || this.f.getObject() == null || !this.f.getObject().equals(a(obj)) || this.n.compareAndSet(true, true) || this.h == null) {
            return;
        }
        if (i == b) {
            this.h.a();
        } else {
            this.h.a(0, "webview render error and undefault");
        }
    }

    public final void a(Object obj, String str) {
        s.a("SecondaryConfirmationManager", "closeDialog");
        if (this.k == null || !this.k.isShowing()) {
            return;
        }
        this.k.dismiss();
        this.k.clear();
    }

    private void a(WindVaneWebView windVaneWebView) {
        if (windVaneWebView != null) {
            h.a().a((WebView) windVaneWebView, "webviewshow", "");
        }
    }

    public final void b(Object obj, String str) {
        if (this.k == null || !this.k.isShowing() || this.k.getListener() == null) {
            return;
        }
        this.k.getListener().c();
    }

    /* compiled from: SecondaryConfirmationManager.java */
    private static final class a implements DialogInterface.OnDismissListener {
        private BroadcastReceiver a;
        private final String b;
        private final c c;
        private final WindVaneWebView d;
        private final WindVaneWebView e;
        private int f;

        public a(BroadcastReceiver broadcastReceiver, String str, c cVar, WindVaneWebView windVaneWebView, WindVaneWebView windVaneWebView2, int i) {
            this.a = broadcastReceiver;
            this.b = str;
            this.c = cVar;
            this.d = windVaneWebView;
            this.e = windVaneWebView2;
            this.f = i;
        }

        public a(String str, c cVar, WindVaneWebView windVaneWebView, WindVaneWebView windVaneWebView2, int i) {
            this.b = str;
            this.c = cVar;
            this.d = windVaneWebView;
            this.e = windVaneWebView2;
            this.f = i;
        }

        public final void a(int i) {
            this.f = i;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            if (this.a != null) {
                try {
                    com.mbridge.msdk.foundation.controller.a.f().j().unregisterReceiver(this.a);
                } catch (Exception e) {
                    s.a("SecondaryConfirmationManager", e.getMessage());
                }
            }
            String str = this.b;
            c cVar = this.c;
            if (this.d != null && !this.d.isDestoryed() && this.d.getObject() != null && this.d.getObject().equals(e.a().a((Object) str))) {
                e.a(e.a(), this.d);
            }
            if (this.e != null && this.e.getObject() != null && this.e.getObject().equals(e.a().a((Object) str))) {
                e.a(e.a(), this.e);
            }
            if (cVar != null) {
                cVar.a(this.f);
                cVar.b();
            }
            e.i(e.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.mbridge.msdk.mbjscommon.confirmation.a a(Object obj) {
        if (obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) {
            Object object = ((com.mbridge.msdk.mbjscommon.windvane.a) obj).a.getObject();
            if (object instanceof com.mbridge.msdk.mbjscommon.confirmation.a) {
                return (com.mbridge.msdk.mbjscommon.confirmation.a) object;
            }
        }
        return null;
    }

    private void a(int i) {
        if (this.q != null) {
            this.q.a(i);
        }
    }

    public final void c() {
        try {
            if (this.l != null) {
                this.l.dismiss();
            }
            if (this.k != null) {
                this.k.dismiss();
            }
        } catch (Throwable th) {
            s.d("SecondaryConfirmationManager", th.getMessage());
        }
    }

    static /* synthetic */ void a(e eVar, WindVaneWebView windVaneWebView) {
        if (eVar.k != null) {
            eVar.k.clear();
        }
        if (windVaneWebView != null) {
            h.a().a((WebView) windVaneWebView, CampaignEx.JSON_NATIVE_VIDEO_CLOSE, "");
        }
    }

    static /* synthetic */ void i(e eVar) {
        if (eVar.i != null) {
            eVar.i = null;
        }
        if (eVar.g != null) {
            eVar.g = null;
        }
        if (eVar.e != null) {
            eVar.e.release();
        }
        if (eVar.f != null) {
            eVar.f.release();
        }
        if (eVar.k != null && eVar.k.isShowing()) {
            eVar.k.dismiss();
        }
        if (eVar.l != null && eVar.l.isShowing()) {
            eVar.l.dismiss();
        }
        eVar.b();
    }
}
