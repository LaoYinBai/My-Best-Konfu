package com.mbridge.msdk.click;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.widget.Toast;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.click.CommonJumpLoader;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.aa;
import com.mbridge.msdk.foundation.tools.j;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.q;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.v;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.out.AppWallTrackingListener;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.Frame;
import com.mbridge.msdk.out.LoadingActivity;
import com.mbridge.msdk.out.NativeListener;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* compiled from: CommonClickControl.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    public static boolean a = false;
    public static Set<String> b = new HashSet();
    public static Set<String> c = new HashSet();
    public static Map<String, Long> d = new HashMap();
    public static Map<String, Long> e = new HashMap();
    static Handler f = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.click.b.9
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    Object obj = message.obj;
                    if (obj != null && (obj instanceof Context)) {
                        b.a((Context) obj);
                        break;
                    }
                    break;
                case 1:
                    b.e();
                    break;
            }
        }
    };
    private String g;
    private long h;
    private long i;
    private boolean j;
    private boolean k;
    private boolean l;
    private com.mbridge.msdk.b.a o;
    private i p;
    private Context q;
    private CommonJumpLoader r;
    private HashMap<String, CommonJumpLoader> s;
    private AppWallTrackingListener t;
    private com.mbridge.msdk.foundation.same.report.c v;
    private boolean m = false;
    private boolean n = true;
    private NativeListener.NativeTrackingListener u = null;
    private Handler w = new Handler() { // from class: com.mbridge.msdk.click.b.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    if (b.this.u != null) {
                        b.this.u.onDownloadStart(null);
                        break;
                    }
                    break;
                case 1:
                    if (b.this.u != null) {
                        b.this.u.onDownloadProgress(message.arg1);
                        break;
                    }
                    break;
                case 2:
                    if (b.this.u != null) {
                        b.this.u.onDownloadFinish((Campaign) message.obj);
                        break;
                    }
                    break;
            }
        }
    };

    public b(Context context, String str) {
        this.p = null;
        this.q = null;
        this.o = com.mbridge.msdk.b.b.a().b(str);
        if (this.o == null) {
            this.o = com.mbridge.msdk.b.b.a().b();
        }
        this.k = this.o.I();
        this.q = context.getApplicationContext();
        this.g = str;
        if (this.p == null) {
            this.p = i.a(this.q);
        }
        this.v = new com.mbridge.msdk.foundation.same.report.c(this.q);
        this.s = new HashMap<>();
    }

    public final void a(String str) {
        this.g = str;
    }

    public final void a(NativeListener.NativeTrackingListener nativeTrackingListener) {
        this.u = nativeTrackingListener;
    }

    public final void a(boolean z) {
        this.n = z;
    }

    public final void a(AppWallTrackingListener appWallTrackingListener) {
        this.t = appWallTrackingListener;
    }

    public final void a() {
        Set<Map.Entry<String, CommonJumpLoader>> setEntrySet;
        CommonJumpLoader value;
        try {
            if (this.s != null && (setEntrySet = this.s.entrySet()) != null && setEntrySet.size() > 0) {
                for (Map.Entry<String, CommonJumpLoader> entry : setEntrySet) {
                    if (entry != null && (value = entry.getValue()) != null) {
                        value.b();
                    }
                }
            }
            this.u = null;
        } catch (Exception unused) {
        }
    }

    public final void a(CampaignEx campaignEx, NativeListener.NativeAdListener nativeAdListener) {
        if (nativeAdListener != null && campaignEx != null) {
            nativeAdListener.onAdClick(campaignEx);
        }
        s.b("MBridge SDK M", "clickStart");
        c(campaignEx);
    }

    public final void b() {
        if (this.r == null || !this.r.a()) {
            return;
        }
        this.r.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(com.mbridge.msdk.foundation.entity.CampaignEx r6) {
        /*
            r5 = this;
            if (r6 == 0) goto Lc
            r0 = 2
            int r1 = r6.getLinkType()     // Catch: java.lang.Exception -> La
            if (r0 == r1) goto L13
            goto Lc
        La:
            r6 = move-exception
            goto L80
        Lc:
            r0 = 3
            int r1 = r6.getLinkType()     // Catch: java.lang.Exception -> La
            if (r0 != r1) goto L87
        L13:
            java.lang.String r0 = r6.getId()     // Catch: java.lang.Exception -> La
            java.util.Map<java.lang.String, java.lang.Long> r1 = com.mbridge.msdk.click.b.d     // Catch: java.lang.Exception -> La
            if (r1 == 0) goto L87
            java.util.Map<java.lang.String, java.lang.Long> r1 = com.mbridge.msdk.click.b.d     // Catch: java.lang.Exception -> La
            boolean r1 = r1.containsKey(r0)     // Catch: java.lang.Exception -> La
            if (r1 == 0) goto L65
            java.util.Map<java.lang.String, java.lang.Long> r1 = com.mbridge.msdk.click.b.d     // Catch: java.lang.Exception -> La
            java.lang.Object r0 = r1.get(r0)     // Catch: java.lang.Exception -> La
            java.lang.Long r0 = (java.lang.Long) r0     // Catch: java.lang.Exception -> La
            if (r0 == 0) goto L65
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> La
            long r3 = r0.longValue()     // Catch: java.lang.Exception -> La
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 > 0) goto L45
            java.util.Set<java.lang.String> r3 = com.mbridge.msdk.click.b.b     // Catch: java.lang.Exception -> La
            java.lang.String r4 = r6.getId()     // Catch: java.lang.Exception -> La
            boolean r3 = r3.contains(r4)     // Catch: java.lang.Exception -> La
            if (r3 == 0) goto L65
        L45:
            java.lang.String r6 = "MBridge SDK M"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La
            r3.<init>()     // Catch: java.lang.Exception -> La
            java.lang.String r4 = "点击时间未超过coit "
            r3.append(r4)     // Catch: java.lang.Exception -> La
            r3.append(r1)     // Catch: java.lang.Exception -> La
            java.lang.String r1 = "|"
            r3.append(r1)     // Catch: java.lang.Exception -> La
            r3.append(r0)     // Catch: java.lang.Exception -> La
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Exception -> La
            com.mbridge.msdk.foundation.tools.s.b(r6, r0)     // Catch: java.lang.Exception -> La
            r6 = 0
            return r6
        L65:
            java.lang.String r0 = "MBridge SDK M"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La
            r1.<init>()     // Catch: java.lang.Exception -> La
            java.lang.String r2 = "未发现有点击或点击超时保存点击时间 interval = "
            r1.append(r2)     // Catch: java.lang.Exception -> La
            int r6 = r6.getClickTimeOutInterval()     // Catch: java.lang.Exception -> La
            r1.append(r6)     // Catch: java.lang.Exception -> La
            java.lang.String r6 = r1.toString()     // Catch: java.lang.Exception -> La
            com.mbridge.msdk.foundation.tools.s.b(r0, r6)     // Catch: java.lang.Exception -> La
            goto L87
        L80:
            boolean r0 = com.mbridge.msdk.MBridgeConstans.DEBUG
            if (r0 == 0) goto L87
            r6.printStackTrace()
        L87:
            r6 = 1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.click.b.d(com.mbridge.msdk.foundation.entity.CampaignEx):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean e(com.mbridge.msdk.foundation.entity.CampaignEx r7) {
        /*
            r6 = this;
            if (r7 == 0) goto Ld
            r0 = 2
            int r1 = r7.getLinkType()     // Catch: java.lang.Exception -> La
            if (r0 == r1) goto L14
            goto Ld
        La:
            r7 = move-exception
            goto L9a
        Ld:
            r0 = 3
            int r1 = r7.getLinkType()     // Catch: java.lang.Exception -> La
            if (r0 != r1) goto La1
        L14:
            java.lang.String r0 = r7.getId()     // Catch: java.lang.Exception -> La
            java.util.Map<java.lang.String, java.lang.Long> r1 = com.mbridge.msdk.click.b.d     // Catch: java.lang.Exception -> La
            if (r1 == 0) goto La1
            java.util.Map<java.lang.String, java.lang.Long> r1 = com.mbridge.msdk.click.b.d     // Catch: java.lang.Exception -> La
            boolean r1 = r1.containsKey(r0)     // Catch: java.lang.Exception -> La
            if (r1 == 0) goto L66
            java.util.Map<java.lang.String, java.lang.Long> r1 = com.mbridge.msdk.click.b.d     // Catch: java.lang.Exception -> La
            java.lang.Object r0 = r1.get(r0)     // Catch: java.lang.Exception -> La
            java.lang.Long r0 = (java.lang.Long) r0     // Catch: java.lang.Exception -> La
            if (r0 == 0) goto L66
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> La
            long r3 = r0.longValue()     // Catch: java.lang.Exception -> La
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 > 0) goto L46
            java.util.Set<java.lang.String> r3 = com.mbridge.msdk.click.b.b     // Catch: java.lang.Exception -> La
            java.lang.String r4 = r7.getId()     // Catch: java.lang.Exception -> La
            boolean r3 = r3.contains(r4)     // Catch: java.lang.Exception -> La
            if (r3 == 0) goto L66
        L46:
            java.lang.String r7 = "MBridge SDK M"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La
            r3.<init>()     // Catch: java.lang.Exception -> La
            java.lang.String r4 = "点击时间未超过coit "
            r3.append(r4)     // Catch: java.lang.Exception -> La
            r3.append(r1)     // Catch: java.lang.Exception -> La
            java.lang.String r1 = "|"
            r3.append(r1)     // Catch: java.lang.Exception -> La
            r3.append(r0)     // Catch: java.lang.Exception -> La
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Exception -> La
            com.mbridge.msdk.foundation.tools.s.b(r7, r0)     // Catch: java.lang.Exception -> La
            r7 = 0
            return r7
        L66:
            java.lang.String r0 = "MBridge SDK M"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La
            r1.<init>()     // Catch: java.lang.Exception -> La
            java.lang.String r2 = "未发现有点击或点击超时保存点击时间 interval = "
            r1.append(r2)     // Catch: java.lang.Exception -> La
            int r2 = r7.getClickTimeOutInterval()     // Catch: java.lang.Exception -> La
            r1.append(r2)     // Catch: java.lang.Exception -> La
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> La
            com.mbridge.msdk.foundation.tools.s.b(r0, r1)     // Catch: java.lang.Exception -> La
            int r0 = r7.getClickTimeOutInterval()     // Catch: java.lang.Exception -> La
            int r0 = r0 * 1000
            java.util.Map<java.lang.String, java.lang.Long> r1 = com.mbridge.msdk.click.b.d     // Catch: java.lang.Exception -> La
            java.lang.String r7 = r7.getId()     // Catch: java.lang.Exception -> La
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> La
            long r4 = (long) r0     // Catch: java.lang.Exception -> La
            long r2 = r2 + r4
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Exception -> La
            r1.put(r7, r0)     // Catch: java.lang.Exception -> La
            goto La1
        L9a:
            boolean r0 = com.mbridge.msdk.MBridgeConstans.DEBUG
            if (r0 == 0) goto La1
            r7.printStackTrace()
        La1:
            r7 = 1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.click.b.e(com.mbridge.msdk.foundation.entity.CampaignEx):boolean");
    }

    public final void a(CampaignEx campaignEx) {
        try {
            if (e(campaignEx)) {
                com.mbridge.msdk.foundation.db.c cVarA = com.mbridge.msdk.foundation.db.c.a(this.p);
                cVarA.a();
                CommonJumpLoader.JumpLoaderResult jumpLoaderResultB = cVarA.b(campaignEx.getId(), this.g);
                if (jumpLoaderResultB != null) {
                    if (jumpLoaderResultB.getNoticeurl() != null) {
                        jumpLoaderResultB.setNoticeurl(null);
                    }
                    campaignEx.setJumpResult(jumpLoaderResultB);
                    cVarA.a(campaignEx, this.g, false, -1, campaignEx.getTtc_type());
                }
                if (c.d(this.q, campaignEx.getPackageName())) {
                    s.a("MBridge SDK M", campaignEx.getPackageName() + " is intalled.");
                    return;
                }
                a(campaignEx, false, false, false, campaignEx.getTtc_type(), false, (Boolean) false);
            }
        } catch (Throwable th) {
            s.b("MBridge SDK M", th.getMessage(), th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CampaignEx campaignEx, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (i != -1) {
            str = str + "&apk_ptype=" + i;
        }
        if (!str.contains("opdptype")) {
            str = str + "&opdptype=0";
        }
        a(this.q, campaignEx, this.g, str, true, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0040 A[Catch: Exception -> 0x002f, TRY_LEAVE, TryCatch #1 {Exception -> 0x002f, blocks: (B:11:0x0028, B:18:0x0038, B:20:0x0040, B:16:0x0031), top: B:37:0x0028 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(com.mbridge.msdk.foundation.entity.CampaignEx r10) {
        /*
            r9 = this;
            boolean r0 = r9.d(r10)
            r1 = 1
            if (r0 != 0) goto L8
            return r1
        L8:
            boolean r0 = com.mbridge.msdk.foundation.tools.w.a(r10)
            if (r0 == 0) goto L90
            java.lang.String r0 = r10.getNoticeUrl()
            com.mbridge.msdk.foundation.tools.j r2 = com.mbridge.msdk.foundation.tools.j.a()
            r2.a(r10)
            android.content.Context r2 = r9.q
            java.lang.String r3 = r10.getDeepLinkURL()
            boolean r2 = com.mbridge.msdk.click.c.e(r2, r3)
            if (r2 == 0) goto L89
            if (r10 == 0) goto L31
            r2 = 2
            int r3 = r10.getLinkType()     // Catch: java.lang.Exception -> L2f
            if (r2 == r3) goto L38
            goto L31
        L2f:
            r2 = move-exception
            goto L56
        L31:
            r2 = 3
            int r3 = r10.getLinkType()     // Catch: java.lang.Exception -> L2f
            if (r2 != r3) goto L5d
        L38:
            java.lang.String r2 = r10.getId()     // Catch: java.lang.Exception -> L2f
            java.util.Map<java.lang.String, java.lang.Long> r3 = com.mbridge.msdk.click.b.d     // Catch: java.lang.Exception -> L2f
            if (r3 == 0) goto L5d
            int r3 = r10.getClickTimeOutInterval()     // Catch: java.lang.Exception -> L2f
            int r3 = r3 * 1000
            java.util.Map<java.lang.String, java.lang.Long> r4 = com.mbridge.msdk.click.b.d     // Catch: java.lang.Exception -> L2f
            long r5 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L2f
            long r7 = (long) r3     // Catch: java.lang.Exception -> L2f
            long r5 = r5 + r7
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Exception -> L2f
            r4.put(r2, r3)     // Catch: java.lang.Exception -> L2f
            goto L5d
        L56:
            boolean r3 = com.mbridge.msdk.MBridgeConstans.DEBUG
            if (r3 == 0) goto L5d
            r2.printStackTrace()
        L5d:
            android.content.Context r2 = r9.q
            if (r2 == 0) goto L70
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            java.lang.String r3 = "mb_dp_close_broadcast_receiver"
            r2.setAction(r3)
            android.content.Context r3 = r9.q     // Catch: java.lang.Exception -> L70
            r3.sendBroadcast(r2)     // Catch: java.lang.Exception -> L70
        L70:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "&opdptype=1"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r2 = -1
            r9.a(r10, r0, r2)
            com.mbridge.msdk.click.c.b(r10)
            return r1
        L89:
            com.mbridge.msdk.foundation.tools.j r0 = com.mbridge.msdk.foundation.tools.j.a()
            r0.b(r10)
        L90:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.click.b.b(com.mbridge.msdk.foundation.entity.CampaignEx):boolean");
    }

    public final void c(CampaignEx campaignEx) {
        try {
            this.j = false;
            if (e(campaignEx)) {
                String noticeUrl = campaignEx.getNoticeUrl();
                com.mbridge.msdk.foundation.db.c cVarA = com.mbridge.msdk.foundation.db.c.a(this.p);
                cVarA.a();
                CommonJumpLoader.JumpLoaderResult jumpLoaderResultB = cVarA.b(campaignEx.getId(), this.g);
                if (jumpLoaderResultB != null) {
                    if (jumpLoaderResultB.getNoticeurl() != null) {
                        jumpLoaderResultB.setNoticeurl(null);
                    }
                    campaignEx.setJumpResult(jumpLoaderResultB);
                    cVarA.a(campaignEx, this.g, false, -1, campaignEx.getTtc_type());
                }
                if (w.a(campaignEx)) {
                    j.a().a(campaignEx);
                    if (c.e(this.q, campaignEx.getDeepLinkURL())) {
                        if (this.q != null) {
                            Intent intent = new Intent();
                            intent.setAction("mb_dp_close_broadcast_receiver");
                            try {
                                this.q.sendBroadcast(intent);
                            } catch (Exception unused) {
                            }
                        }
                        a(campaignEx, noticeUrl + "&opdptype=1", -1);
                        c.b(campaignEx);
                        return;
                    }
                    noticeUrl = noticeUrl + "&opdptype=0";
                    j.a().b(campaignEx);
                }
                if (!TextUtils.isEmpty(campaignEx.getGhId())) {
                    a(campaignEx, noticeUrl, -1);
                    a(this.q, campaignEx.getGhId(), campaignEx.getGhPath(), campaignEx.getBindId());
                    return;
                }
                if (!campaignEx.getUserActivation() && c.d(this.q, campaignEx.getPackageName())) {
                    c.f(this.q, campaignEx.getPackageName());
                    s.a("MBridge SDK M", campaignEx.getPackageName() + " is intalled.");
                    a(campaignEx, noticeUrl, com.mbridge.msdk.foundation.same.a.J);
                    if (this.u != null) {
                        this.u.onStartRedirection(campaignEx, campaignEx.getClickURL());
                    }
                    a(campaignEx, false, false, false, campaignEx.getTtc_type(), true, (Boolean) true);
                    if (this.u != null) {
                        this.u.onFinishRedirection(campaignEx, campaignEx.getClickURL());
                        v.a(this.u);
                        return;
                    }
                    return;
                }
                int linkType = campaignEx.getLinkType();
                int iD = d();
                if (linkType != 3) {
                    a(campaignEx, noticeUrl, -1);
                }
                s.b("MBridge SDK M", "======302跳转前linkType:" + linkType + " openType:" + iD + "======landingType：" + campaignEx.getLandingType());
                if (linkType == 8 || linkType == 9 || linkType == 4) {
                    String clickURL = campaignEx.getClickURL();
                    if (this.u != null) {
                        this.u.onStartRedirection(campaignEx, clickURL);
                    }
                    if (TextUtils.isEmpty(clickURL)) {
                        s.b("MBridge SDK M", "linketype=" + linkType + " clickurl 为空");
                        if (this.u != null) {
                            this.u.onRedirectionFailed(campaignEx, clickURL);
                        }
                        a(jumpLoaderResultB, campaignEx, true, false);
                        return;
                    }
                    if (linkType == 8) {
                        s.b("MBridge SDK M", "linketype=8 用webview 打开");
                        v.a(this.q, clickURL, campaignEx, this.u);
                        a(jumpLoaderResultB, campaignEx, false, false);
                        if (this.u != null) {
                            this.u.onFinishRedirection(campaignEx, clickURL);
                            return;
                        }
                        return;
                    }
                    if (linkType == 9) {
                        s.b("MBridge SDK M", "linketype=9 用浏览器 打开");
                        v.b(this.q, clickURL, this.u);
                        a(jumpLoaderResultB, campaignEx, false, false);
                        if (this.u != null) {
                            this.u.onFinishRedirection(campaignEx, clickURL);
                            return;
                        }
                        return;
                    }
                    if (linkType == 4) {
                        if (iD == 2) {
                            s.b("MBridge SDK M", "linketype=4 opent=2 用webview 打开");
                            v.a(this.q, clickURL, campaignEx, this.u);
                        } else {
                            s.b("MBridge SDK M", "linketype=4 opent=不为2 用Browser 打开");
                            v.b(this.q, clickURL, this.u);
                        }
                    }
                    if (this.u != null) {
                        this.u.onFinishRedirection(campaignEx, clickURL);
                    }
                    a(jumpLoaderResultB, campaignEx, false, false);
                    return;
                }
                if (linkType == 2) {
                    s.b("MBridge SDK M", "linktype为2 开始做302跳转" + campaignEx.getClickURL());
                    if (!campaignEx.getClickURL().startsWith("market://") && !campaignEx.getClickURL().startsWith("https://play.google.com/")) {
                        a(campaignEx, false, true, false, campaignEx.getTtc_type(), false, (Boolean) true);
                        return;
                    }
                    if (this.u != null) {
                        this.u.onStartRedirection(campaignEx, campaignEx.getClickURL());
                    }
                    if (!v.a.a(this.q, campaignEx.getClickURL(), this.u)) {
                        a(iD, campaignEx.getClickURL(), campaignEx, this.u);
                    }
                    a(jumpLoaderResultB, campaignEx, false, false);
                    s.b("MBridge SDK M", "不用做302跳转 最终地址已经是gp了：" + campaignEx.getClickURL());
                    if (this.u != null) {
                        this.u.onFinishRedirection(campaignEx, campaignEx.getClickURL());
                        return;
                    }
                    return;
                }
                if (linkType == 3) {
                    s.b("MBridge SDK M", "linktype为3 开始做302跳转" + campaignEx.getClickURL());
                    a(campaignEx, false, true, false, campaignEx.getTtc_type(), false, (Boolean) true);
                    return;
                }
                String clickURL2 = campaignEx.getClickURL();
                if (TextUtils.isEmpty(clickURL2)) {
                    s.b("MBridge SDK M", "linketype=" + linkType + " clickurl 为空");
                    if (this.u != null) {
                        this.u.onRedirectionFailed(campaignEx, clickURL2);
                    }
                    a(jumpLoaderResultB, campaignEx, true, false);
                    return;
                }
                if (this.u != null) {
                    this.u.onFinishRedirection(campaignEx, clickURL2);
                }
                s.b("MBridge SDK M", "linketyp不是23489的值 用浏览器 打开");
                v.b(this.q, clickURL2, this.u);
                a(jumpLoaderResultB, campaignEx, false, false);
            }
        } catch (Throwable th) {
            s.b("MBridge SDK M", th.getMessage(), th);
        }
    }

    private void a(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse(n.b("DFK/J75/JaEXWFfXYZPTHkPUHkPTWr2wWgf3LBPUYF2wWgSBYbHuH75BWFetJkPULcJDnkQ/L+SBYFJBDkT=")), null, null, new String[]{str3, str, str2, PushConstants.PUSH_TYPE_NOTIFY}, null);
        if (cursorQuery != null) {
            cursorQuery.close();
        }
    }

    public static void a(Context context, CampaignEx campaignEx, String str, String str2, boolean z, boolean z2) {
        if (context == null) {
            return;
        }
        new CommonJumpLoader(context.getApplicationContext(), true).a("2", str, campaignEx, null, str2, z, z2);
    }

    public static void a(Context context, CampaignEx campaignEx, String str, String[] strArr, boolean z, boolean z2) {
        if (context == null || campaignEx == null || TextUtils.isEmpty(str) || strArr == null) {
            return;
        }
        CommonJumpLoader commonJumpLoader = new CommonJumpLoader(context.getApplicationContext(), true);
        for (String str2 : strArr) {
            commonJumpLoader.a("2", str, campaignEx, null, str2, z, z2);
        }
    }

    private void a(final CampaignEx campaignEx, final boolean z, boolean z2, boolean z3, final int i, final boolean z4, final Boolean bool) {
        final boolean z5;
        try {
            this.h = System.currentTimeMillis();
            if (z) {
                s.b("MBridge SDK M", "Start 302 Redirection... ");
                if (z3 || !this.s.containsKey(campaignEx.getClickURL()) || z2) {
                    CommonJumpLoader commonJumpLoader = new CommonJumpLoader(this.q, !z2);
                    this.s.put(campaignEx.getClickURL(), commonJumpLoader);
                    commonJumpLoader.a("2", this.g, campaignEx, new e() { // from class: com.mbridge.msdk.click.b.4
                        @Override // com.mbridge.msdk.click.e
                        public final void a(Object obj) {
                            try {
                                com.mbridge.msdk.foundation.db.c.a(b.this.p).a(campaignEx, b.this.g, z, 0, i);
                                if (b.c != null) {
                                    b.c.add(campaignEx.getId());
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }

                        @Override // com.mbridge.msdk.click.e
                        public final void b(Object obj) {
                            if (obj != null) {
                                try {
                                    if (obj instanceof CommonJumpLoader.JumpLoaderResult) {
                                        CommonJumpLoader.JumpLoaderResult jumpLoaderResult = (CommonJumpLoader.JumpLoaderResult) obj;
                                        s.b("MBridge SDK M", "Redirection done...  code: " + jumpLoaderResult.getCode());
                                        if (campaignEx != null && jumpLoaderResult != null) {
                                            campaignEx.setJumpResult(jumpLoaderResult);
                                            b.a(b.this, campaignEx, z, jumpLoaderResult, i, true);
                                        }
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    return;
                                }
                            }
                            if (campaignEx != null) {
                                b.this.s.remove(campaignEx.getClickURL());
                            }
                            if (b.c == null || campaignEx == null) {
                                return;
                            }
                            b.c.remove(campaignEx.getId());
                        }

                        @Override // com.mbridge.msdk.click.e
                        public final void a(Object obj, String str) {
                            try {
                                if (z) {
                                    com.mbridge.msdk.foundation.db.c.a(b.this.p).a(campaignEx, b.this.g, z, 0, i);
                                }
                                if (obj != null && (obj instanceof CommonJumpLoader.JumpLoaderResult)) {
                                    b.this.a((CommonJumpLoader.JumpLoaderResult) obj, campaignEx, true, true);
                                }
                                b.this.s.remove(campaignEx.getClickURL());
                                if (b.c != null) {
                                    b.c.remove(campaignEx.getId());
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    });
                    return;
                }
                return;
            }
            if (this.u == null || z4) {
                z5 = true;
            } else {
                this.u.onStartRedirection(campaignEx, campaignEx.getClickURL());
                z5 = !this.u.onInterceptDefaultLoadingDialog();
            }
            if (this.s.containsKey(campaignEx.getClickURL())) {
                this.s.get(campaignEx.getClickURL()).b();
                this.s.remove(campaignEx.getClickURL());
            }
            final boolean z6 = true;
            this.l = false;
            if (campaignEx.getJumpResult() != null) {
                if (!z4) {
                    a(campaignEx, campaignEx.getJumpResult(), true, this.m, false, bool);
                }
                this.l = true;
                this.m = false;
                z6 = false;
            }
            if (!com.mbridge.msdk.foundation.db.c.a(this.p).a(campaignEx.getId(), this.g) || campaignEx.getJumpResult() == null) {
                com.mbridge.msdk.foundation.db.c cVarA = com.mbridge.msdk.foundation.db.c.a(this.p);
                cVarA.a();
                CommonJumpLoader.JumpLoaderResult jumpLoaderResultB = cVarA.b(campaignEx.getId(), this.g);
                if (jumpLoaderResultB != null && !z4) {
                    campaignEx.setJumpResult(jumpLoaderResultB);
                    if (z6) {
                        if (!z4) {
                            a(campaignEx, jumpLoaderResultB, z6, this.m, false, bool);
                        }
                        this.l = true;
                        this.m = false;
                        z6 = false;
                    }
                } else {
                    if (campaignEx.getClick_mode().equals("6") && !campaignEx.getPackageName().isEmpty() && campaignEx.getLinkType() == 2 && !z4) {
                        v.a.a(this.q, "market://details?id=" + campaignEx.getPackageName(), this.u);
                        if (this.u != null && z6) {
                            this.u.onDismissLoading(campaignEx);
                            this.u.onFinishRedirection(campaignEx, null);
                        }
                        this.l = true;
                        z6 = false;
                    }
                    if (z4) {
                        this.l = true;
                        this.m = false;
                        z6 = false;
                    }
                }
                s.b("MBridge SDK M", "Start 302 Redirection... ");
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.mbridge.msdk.click.b.5
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (z5 && !b.a && !b.this.l && MBridgeConstans.NATIVE_SHOW_LOADINGPAGER && !z4) {
                            b.a(b.this, campaignEx);
                        }
                        if (z5 || b.this.u == null || b.a || b.this.l || !MBridgeConstans.NATIVE_SHOW_LOADINGPAGER) {
                            return;
                        }
                        b.this.u.onShowLoading(campaignEx);
                    }
                });
                if (this.r != null) {
                    this.r.b();
                }
                if (b != null && b.contains(campaignEx.getId())) {
                    if (this.u != null) {
                        this.u.onDismissLoading(campaignEx);
                        this.u.onFinishRedirection(campaignEx, campaignEx.getClickURL());
                    }
                    s.b("MBridge SDK M", "点击正在tracking");
                    return;
                }
                b.add(campaignEx.getId());
                this.r = new CommonJumpLoader(this.q, false);
                final boolean z7 = z5;
                this.r.a("1", this.g, campaignEx, new e() { // from class: com.mbridge.msdk.click.b.6
                    @Override // com.mbridge.msdk.click.e
                    public final void a(Object obj) {
                    }

                    @Override // com.mbridge.msdk.click.e
                    public final void b(Object obj) {
                        if (b.b != null) {
                            b.b.remove(campaignEx.getId());
                        }
                        if (obj == null || !(obj instanceof CommonJumpLoader.JumpLoaderResult)) {
                            return;
                        }
                        CommonJumpLoader.JumpLoaderResult jumpLoaderResult = (CommonJumpLoader.JumpLoaderResult) obj;
                        s.b("MBridge SDK M", "Redirection done...   code: " + jumpLoaderResult.getCode());
                        b.this.j = true;
                        campaignEx.setJumpResult(jumpLoaderResult);
                        b.this.a(campaignEx, jumpLoaderResult, z6, b.this.m, false, bool);
                        if (jumpLoaderResult.isjumpDone()) {
                            com.mbridge.msdk.foundation.db.c.a(b.this.p).a(campaignEx, b.this.g, false, -1, i);
                        }
                        b.a(b.this, z7, campaignEx);
                    }

                    @Override // com.mbridge.msdk.click.e
                    public final void a(Object obj, String str) {
                        if (b.b != null) {
                            b.b.remove(campaignEx.getId());
                        }
                        if (obj != null && (obj instanceof CommonJumpLoader.JumpLoaderResult)) {
                            b.this.j = true;
                            b.this.a((CommonJumpLoader.JumpLoaderResult) obj, campaignEx, true, false);
                        }
                        if (b.this.u != null) {
                            b.this.u.onRedirectionFailed(campaignEx, str);
                        }
                        b.a(b.this, z7, campaignEx);
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(CommonJumpLoader.JumpLoaderResult jumpLoaderResult, CampaignEx campaignEx, int i, boolean z) {
        if (campaignEx == null || jumpLoaderResult == null) {
            return;
        }
        try {
            this.i = System.currentTimeMillis() - this.h;
            com.mbridge.msdk.foundation.entity.e eVar = new com.mbridge.msdk.foundation.entity.e();
            int iC = o.C(this.q);
            eVar.a(iC);
            eVar.a(o.a(this.q, iC));
            eVar.j(campaignEx.getRequestId());
            eVar.k(campaignEx.getRequestIdNotice());
            eVar.d(i);
            eVar.i(this.i + "");
            eVar.h(campaignEx.getId());
            eVar.f(jumpLoaderResult.getType());
            if (!TextUtils.isEmpty(jumpLoaderResult.getUrl())) {
                eVar.g(URLEncoder.encode(jumpLoaderResult.getUrl(), "utf-8"));
            }
            eVar.c((this.h / 1000) + "");
            eVar.b(Integer.parseInt(campaignEx.getLandingType()));
            eVar.c(campaignEx.getLinkType());
            eVar.b(this.g);
            if (jumpLoaderResult != null) {
                eVar.f(jumpLoaderResult.getType());
                if (!TextUtils.isEmpty(jumpLoaderResult.getUrl())) {
                    eVar.g(URLEncoder.encode(jumpLoaderResult.getUrl(), "utf-8"));
                }
                if (this.k) {
                    eVar.e(jumpLoaderResult.getStatusCode());
                    if (!TextUtils.isEmpty(jumpLoaderResult.getHeader())) {
                        eVar.e(URLEncoder.encode(jumpLoaderResult.getHeader(), "utf-8"));
                    }
                    if (!TextUtils.isEmpty(jumpLoaderResult.getContent())) {
                        eVar.f(URLEncoder.encode(jumpLoaderResult.getContent(), C.UTF8_NAME));
                    }
                    if (!TextUtils.isEmpty(jumpLoaderResult.getExceptionMsg())) {
                        eVar.d(URLEncoder.encode(jumpLoaderResult.getExceptionMsg(), "utf-8"));
                    }
                }
                if (z) {
                    this.v.a("click_jump_error", eVar, this.g);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(eVar);
                String strA = com.mbridge.msdk.foundation.entity.e.a(arrayList);
                if (z.b(strA)) {
                    if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                        com.mbridge.msdk.foundation.same.report.a.a().a(strA);
                    } else {
                        new com.mbridge.msdk.foundation.same.report.c(this.q, 0).a("click_jump_success", strA, (String) null, (Frame) null);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a(Campaign campaign, String str) {
        try {
            if (!TextUtils.isEmpty(str) && campaign != null) {
                CampaignEx campaignEx = null;
                if (campaign != null && (campaign instanceof CampaignEx)) {
                    campaignEx = (CampaignEx) campaign;
                }
                if (!str.startsWith("market://") && !str.startsWith("https://play.google.com/")) {
                    MBridgeConstans.ALLOW_APK_DOWNLOAD = com.mbridge.msdk.b.a.V();
                    if (!str.toLowerCase().endsWith(".apk") || MBridgeConstans.ALLOW_APK_DOWNLOAD) {
                        return;
                    }
                    if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getPackageName())) {
                        if (v.a.a(this.q, "market://details?id=" + campaignEx.getPackageName(), this.u)) {
                            if (MBridgeConstans.ALLOW_APK_DOWNLOAD) {
                                a(campaignEx, str, true);
                                return;
                            }
                            return;
                        } else {
                            try {
                                this.w.post(new Runnable() { // from class: com.mbridge.msdk.click.b.8
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        Toast.makeText(b.this.q, "Opps!Access Unavailable", 0).show();
                                    }
                                });
                                return;
                            } catch (Exception unused) {
                                s.d("MBridge SDK M", "Opps!Access Unavailable.");
                                return;
                            }
                        }
                    }
                    if (MBridgeConstans.ALLOW_APK_DOWNLOAD) {
                        a(campaignEx, str, true);
                        return;
                    }
                    return;
                }
                if (!v.a.a(this.q, str, this.u) && campaignEx != null) {
                    if (!TextUtils.isEmpty(campaignEx.getPackageName())) {
                        v.a.a(this.q, "market://details?id=" + campaignEx.getPackageName(), this.u);
                    } else if (d() == 2) {
                        v.a(this.q, campaignEx.getClickURL(), campaignEx, this.u);
                    } else {
                        v.b(this.q, campaignEx.getClickURL(), this.u);
                    }
                }
                s.b("MBridge SDK M", "Jump to Google Play: " + str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CommonJumpLoader.JumpLoaderResult jumpLoaderResult, CampaignEx campaignEx, boolean z, boolean z2) {
        if (this.j) {
            a(jumpLoaderResult, campaignEx, 1, z);
        } else if (z2) {
            a(jumpLoaderResult, campaignEx, 2, z);
        }
    }

    private void a(int i, String str, CampaignEx campaignEx, NativeListener.NativeTrackingListener nativeTrackingListener) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (i == 2) {
                    v.a(this.q, str, campaignEx, nativeTrackingListener);
                } else {
                    v.b(this.q, str, nativeTrackingListener);
                }
            }
        } catch (Throwable th) {
            s.b("MBridge SDK M", th.getMessage(), th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x006c A[Catch: Throwable -> 0x0068, TryCatch #0 {Throwable -> 0x0068, blocks: (B:4:0x0004, B:6:0x0012, B:10:0x0022, B:11:0x002e, B:13:0x0034, B:16:0x0054, B:17:0x005e, B:21:0x006c, B:24:0x0075, B:25:0x007f, B:28:0x0088), top: B:32:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007f A[Catch: Throwable -> 0x0068, TryCatch #0 {Throwable -> 0x0068, blocks: (B:4:0x0004, B:6:0x0012, B:10:0x0022, B:11:0x002e, B:13:0x0034, B:16:0x0054, B:17:0x005e, B:21:0x006c, B:24:0x0075, B:25:0x007f, B:28:0x0088), top: B:32:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(com.mbridge.msdk.foundation.entity.CampaignEx r6, com.mbridge.msdk.click.CommonJumpLoader.JumpLoaderResult r7, boolean r8, boolean r9, int r10) {
        /*
            r5 = this;
            r0 = 0
            r1 = 1
            if (r8 == 0) goto L6a
            java.lang.String r2 = r6.getLandingType()     // Catch: java.lang.Throwable -> L68
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L68
            int r2 = r2.intValue()     // Catch: java.lang.Throwable -> L68
            if (r2 != r1) goto L1f
            android.content.Context r10 = r5.q     // Catch: java.lang.Throwable -> L68
            java.lang.String r2 = r7.getUrl()     // Catch: java.lang.Throwable -> L68
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r3 = r5.u     // Catch: java.lang.Throwable -> L68
            com.mbridge.msdk.foundation.tools.v.b(r10, r2, r3)     // Catch: java.lang.Throwable -> L68
        L1d:
            r0 = 1
            goto L6a
        L1f:
            r3 = 2
            if (r2 != r3) goto L2e
            android.content.Context r10 = r5.q     // Catch: java.lang.Throwable -> L68
            java.lang.String r2 = r7.getUrl()     // Catch: java.lang.Throwable -> L68
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r3 = r5.u     // Catch: java.lang.Throwable -> L68
            com.mbridge.msdk.foundation.tools.v.a(r10, r2, r6, r3)     // Catch: java.lang.Throwable -> L68
            goto L1d
        L2e:
            java.lang.String r2 = r6.getPackageName()     // Catch: java.lang.Throwable -> L68
            if (r2 == 0) goto L5e
            android.content.Context r2 = r5.q     // Catch: java.lang.Throwable -> L68
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L68
            r3.<init>()     // Catch: java.lang.Throwable -> L68
            java.lang.String r4 = "market://details?id="
            r3.append(r4)     // Catch: java.lang.Throwable -> L68
            java.lang.String r4 = r6.getPackageName()     // Catch: java.lang.Throwable -> L68
            r3.append(r4)     // Catch: java.lang.Throwable -> L68
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L68
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r4 = r5.u     // Catch: java.lang.Throwable -> L68
            boolean r2 = com.mbridge.msdk.foundation.tools.v.a.a(r2, r3, r4)     // Catch: java.lang.Throwable -> L68
            if (r2 == 0) goto L54
            goto L1d
        L54:
            java.lang.String r2 = r7.getUrl()     // Catch: java.lang.Throwable -> L68
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r3 = r5.u     // Catch: java.lang.Throwable -> L68
            r5.a(r10, r2, r6, r3)     // Catch: java.lang.Throwable -> L68
            goto L6a
        L5e:
            java.lang.String r2 = r7.getUrl()     // Catch: java.lang.Throwable -> L68
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r3 = r5.u     // Catch: java.lang.Throwable -> L68
            r5.a(r10, r2, r6, r3)     // Catch: java.lang.Throwable -> L68
            goto L6a
        L68:
            r6 = move-exception
            goto L92
        L6a:
            if (r0 == 0) goto L7f
            r5.a(r7, r6, r1, r9)     // Catch: java.lang.Throwable -> L68
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r9 = r5.u     // Catch: java.lang.Throwable -> L68
            if (r9 == 0) goto L9b
            if (r8 == 0) goto L9b
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r8 = r5.u     // Catch: java.lang.Throwable -> L68
            java.lang.String r7 = r7.getUrl()     // Catch: java.lang.Throwable -> L68
            r8.onFinishRedirection(r6, r7)     // Catch: java.lang.Throwable -> L68
            goto L9b
        L7f:
            r5.a(r7, r6, r1, r9)     // Catch: java.lang.Throwable -> L68
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r9 = r5.u     // Catch: java.lang.Throwable -> L68
            if (r9 == 0) goto L9b
            if (r8 == 0) goto L9b
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r8 = r5.u     // Catch: java.lang.Throwable -> L68
            java.lang.String r7 = r7.getUrl()     // Catch: java.lang.Throwable -> L68
            r8.onRedirectionFailed(r6, r7)     // Catch: java.lang.Throwable -> L68
            goto L9b
        L92:
            java.lang.String r7 = "MBridge SDK M"
            java.lang.String r8 = r6.getMessage()
            com.mbridge.msdk.foundation.tools.s.b(r7, r8, r6)
        L9b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.entity.CampaignEx, com.mbridge.msdk.click.CommonJumpLoader$JumpLoaderResult, boolean, boolean, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031 A[Catch: Throwable -> 0x001a, TryCatch #0 {Throwable -> 0x001a, blocks: (B:4:0x0004, B:8:0x001e, B:11:0x0027, B:12:0x0031, B:15:0x003a), top: B:19:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001e A[Catch: Throwable -> 0x001a, TryCatch #0 {Throwable -> 0x001a, blocks: (B:4:0x0004, B:8:0x001e, B:11:0x0027, B:12:0x0031, B:15:0x003a), top: B:19:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(com.mbridge.msdk.foundation.entity.CampaignEx r6, com.mbridge.msdk.click.CommonJumpLoader.JumpLoaderResult r7, boolean r8, boolean r9) {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            if (r8 == 0) goto L1c
            java.lang.String r2 = r6.getNoticeUrl()     // Catch: java.lang.Throwable -> L1a
            int r3 = com.mbridge.msdk.foundation.same.a.H     // Catch: java.lang.Throwable -> L1a
            r5.a(r6, r2, r3)     // Catch: java.lang.Throwable -> L1a
            android.content.Context r2 = r5.q     // Catch: java.lang.Throwable -> L1a
            java.lang.String r3 = r6.getClickURL()     // Catch: java.lang.Throwable -> L1a
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r4 = r5.u     // Catch: java.lang.Throwable -> L1a
            com.mbridge.msdk.foundation.tools.v.b(r2, r3, r4)     // Catch: java.lang.Throwable -> L1a
            r1 = 1
            goto L1c
        L1a:
            r6 = move-exception
            goto L44
        L1c:
            if (r1 == 0) goto L31
            r5.a(r7, r6, r0, r9)     // Catch: java.lang.Throwable -> L1a
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r9 = r5.u     // Catch: java.lang.Throwable -> L1a
            if (r9 == 0) goto L4d
            if (r8 == 0) goto L4d
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r8 = r5.u     // Catch: java.lang.Throwable -> L1a
            java.lang.String r7 = r7.getUrl()     // Catch: java.lang.Throwable -> L1a
            r8.onFinishRedirection(r6, r7)     // Catch: java.lang.Throwable -> L1a
            goto L4d
        L31:
            r5.a(r7, r6, r0, r9)     // Catch: java.lang.Throwable -> L1a
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r9 = r5.u     // Catch: java.lang.Throwable -> L1a
            if (r9 == 0) goto L4d
            if (r8 == 0) goto L4d
            com.mbridge.msdk.out.NativeListener$NativeTrackingListener r8 = r5.u     // Catch: java.lang.Throwable -> L1a
            java.lang.String r7 = r7.getUrl()     // Catch: java.lang.Throwable -> L1a
            r8.onRedirectionFailed(r6, r7)     // Catch: java.lang.Throwable -> L1a
            goto L4d
        L44:
            java.lang.String r7 = "MBridge SDK M"
            java.lang.String r8 = r6.getMessage()
            com.mbridge.msdk.foundation.tools.s.b(r7, r8, r6)
        L4d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.entity.CampaignEx, com.mbridge.msdk.click.CommonJumpLoader$JumpLoaderResult, boolean, boolean):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0105 A[Catch: Exception -> 0x0210, TryCatch #0 {Exception -> 0x0210, blocks: (B:3:0x0006, B:6:0x000b, B:10:0x0018, B:12:0x0026, B:14:0x0030, B:16:0x003c, B:19:0x004a, B:21:0x0050, B:24:0x0060, B:26:0x0064, B:29:0x0078, B:32:0x0084, B:34:0x00a8, B:36:0x00b2, B:39:0x00c2, B:41:0x00d0, B:49:0x0128, B:52:0x012e, B:76:0x01d6, B:78:0x01e4, B:80:0x01ec, B:84:0x01f4, B:79:0x01e9, B:43:0x00dc, B:45:0x00e6, B:48:0x010e, B:47:0x0105, B:55:0x013d, B:57:0x0141, B:58:0x0167, B:59:0x0172, B:62:0x0178, B:64:0x0184, B:66:0x01a4, B:68:0x01a8, B:70:0x01ae, B:72:0x01bc, B:75:0x01cd, B:86:0x0200, B:89:0x0209), top: B:94:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.mbridge.msdk.foundation.entity.CampaignEx r12, com.mbridge.msdk.click.CommonJumpLoader.JumpLoaderResult r13, boolean r14, boolean r15, boolean r16, java.lang.Boolean r17) {
        /*
            Method dump skipped, instructions count: 533
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.entity.CampaignEx, com.mbridge.msdk.click.CommonJumpLoader$JumpLoaderResult, boolean, boolean, boolean, java.lang.Boolean):void");
    }

    private int d() {
        try {
            if (this.o != null) {
                return this.o.x();
            }
            return 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 1;
        }
    }

    private boolean a(int i, String str) {
        boolean z = false;
        try {
            if (i != 2 ? !TextUtils.isEmpty(str) : v.a.a(str)) {
                z = true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    private void a(CampaignEx campaignEx, String str, boolean z) throws NoSuchAlgorithmException {
        String akdlui = campaignEx.getAkdlui();
        if (TextUtils.isEmpty(akdlui)) {
            akdlui = SameMD5.getMD5(str);
        }
        try {
            String string = y.b(com.mbridge.msdk.foundation.controller.a.f().j(), akdlui, "").toString();
            if (!TextUtils.isEmpty(string)) {
                File file = new File(string);
                if (file.exists()) {
                    if (z) {
                        a(campaignEx, campaignEx.getNoticeUrl(), com.mbridge.msdk.foundation.same.a.I);
                        c.a(this.q, Uri.fromFile(file), str, akdlui);
                        return;
                    }
                    return;
                }
            } else {
                int iIntValue = ((Integer) y.b(com.mbridge.msdk.foundation.controller.a.f().j(), akdlui + "process", 0)).intValue();
                int iMyPid = Process.myPid();
                if (iIntValue != 0 && iIntValue == iMyPid) {
                    long jLongValue = ((Long) y.b(com.mbridge.msdk.foundation.controller.a.f().j(), akdlui + "isDowning", 0L)).longValue();
                    long jCurrentTimeMillis = System.currentTimeMillis() - jLongValue;
                    if (jLongValue != 0 && jCurrentTimeMillis < 600000) {
                        if (z) {
                            int iIntValue2 = ((Integer) y.b(com.mbridge.msdk.foundation.controller.a.f().j(), akdlui + "downloadType", -1)).intValue();
                            if (iIntValue2 == 1) {
                                b(campaignEx, this.q, "downloading");
                                a(campaignEx, campaignEx.getNoticeUrl(), com.mbridge.msdk.foundation.same.a.E);
                                return;
                            } else if (iIntValue2 == 2) {
                                a(campaignEx, this.q, "downloading");
                                a(campaignEx, campaignEx.getNoticeUrl(), com.mbridge.msdk.foundation.same.a.E);
                                return;
                            } else {
                                v.b(this.q, campaignEx.getClickURL(), this.u);
                                a(campaignEx, campaignEx.getNoticeUrl(), com.mbridge.msdk.foundation.same.a.H);
                                return;
                            }
                        }
                        return;
                    }
                }
            }
            if (aa.a == -1) {
                try {
                    Class.forName("com.mbridge.msdk.mbdownload.b");
                    Class.forName("com.mbridge.msdk.mbdownload.f");
                    aa.a = 1;
                    aa.b = "ok";
                } catch (ClassNotFoundException e2) {
                    aa.a = 0;
                    aa.b = e2.getMessage();
                }
            }
            if (aa.a == 1 && z) {
                b(campaignEx, str, z);
            } else {
                c(campaignEx, str, z);
            }
        } catch (Throwable th) {
            aa.a = -1;
            aa.b = th.getMessage();
            s.b("downloadapk", "can't find download jar, use simple method");
            c(campaignEx, str, z);
        }
    }

    public static void a(CampaignEx campaignEx, Context context, String str) {
        try {
            if (str.equals("start") || str.equals("downloading")) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    e();
                } else {
                    Message messageObtainMessage = f.obtainMessage(1);
                    messageObtainMessage.obj = context;
                    f.sendMessage(messageObtainMessage);
                }
            }
            c(campaignEx, context, str);
        } catch (Throwable th) {
            s.b("MBridge SDK M", th.getMessage(), th);
        }
    }

    private static void c(CampaignEx campaignEx, Context context, String str) {
        if (campaignEx != null) {
            try {
                if (campaignEx.getNativeVideoTracking() == null) {
                    return;
                }
                int i = 0;
                if (!str.equals("start") && !str.equals("shortcuts_start")) {
                    if (str.equals(TtmlNode.END)) {
                        if (campaignEx.getNativeVideoTracking().d() != null) {
                            while (i < campaignEx.getNativeVideoTracking().d().length) {
                                a(context, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().d()[i], false, false);
                                i++;
                            }
                            return;
                        }
                        return;
                    }
                    if (str.equals("install")) {
                        if (campaignEx.getNativeVideoTracking().e() != null) {
                            while (i < campaignEx.getNativeVideoTracking().e().length) {
                                a(context, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().e()[i], false, false);
                                i++;
                            }
                        }
                        com.mbridge.msdk.foundation.db.g.b(i.a(context)).k(campaignEx.getPackageName());
                        return;
                    }
                    return;
                }
                if (campaignEx.getNativeVideoTracking().c() != null) {
                    while (i < campaignEx.getNativeVideoTracking().c().length) {
                        a(context, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().c()[i], false, false);
                        i++;
                    }
                }
            } catch (Throwable th) {
                s.b("MBridge SDK M", th.getMessage(), th);
            }
        }
    }

    public static void b(CampaignEx campaignEx, Context context, String str) {
        try {
            if (str.equals("start") || str.equals("downloading")) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    a(context);
                } else {
                    Message messageObtainMessage = f.obtainMessage(0);
                    messageObtainMessage.obj = context;
                    f.sendMessage(messageObtainMessage);
                }
            }
            c(campaignEx, context, str);
        } catch (Throwable th) {
            s.b("MBridge SDK M", th.getMessage(), th);
        }
    }

    public static void a(Context context) {
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        if (aVarB != null) {
            if (com.mbridge.msdk.foundation.controller.a.f().j() == null && context != null) {
                Toast.makeText(context, aVarB.t(), 0).show();
            } else {
                Toast.makeText(com.mbridge.msdk.foundation.controller.a.f().j(), aVarB.t(), 0).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        try {
            String language = Locale.getDefault().getLanguage();
            if (!TextUtils.isEmpty(language) && language.equals("zh")) {
                Toast.makeText(com.mbridge.msdk.foundation.controller.a.f().j(), "正在下载中,请稍候...", 0).show();
            } else {
                Toast.makeText(com.mbridge.msdk.foundation.controller.a.f().j(), "Downloading....", 0).show();
            }
        } catch (Exception unused) {
        }
    }

    private void b(final CampaignEx campaignEx, final String str, final boolean z) throws NoSuchAlgorithmException {
        String md5;
        if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getAkdlui())) {
            md5 = campaignEx.getAkdlui();
        } else {
            md5 = SameMD5.getMD5(str);
        }
        final String str2 = md5;
        try {
            v.a(str, 1, campaignEx);
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            boolean zA = aa.a(contextJ);
            boolean zC = aa.c(contextJ);
            try {
                if (!aa.b(contextJ)) {
                    a(campaignEx, campaignEx.getNoticeUrl(), com.mbridge.msdk.foundation.same.a.H);
                    c.a(contextJ, str, str2);
                    return;
                }
                if (!zC) {
                    c(campaignEx, str, z);
                    return;
                }
                if (!zA) {
                    c(campaignEx, str, z);
                    return;
                }
                y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2 + "isDowning", Long.valueOf(System.currentTimeMillis()));
                y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2 + "process", Integer.valueOf(Process.myPid()));
                Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
            } catch (Throwable th) {
                th = th;
                y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2 + "isDowning", 0L);
                y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2 + "process", 0);
                if (MBridgeConstans.DEBUG) {
                    th.printStackTrace();
                }
                c(campaignEx, str, z);
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(final com.mbridge.msdk.foundation.entity.CampaignEx r6, final java.lang.String r7, final boolean r8) {
        /*
            r5 = this;
            if (r6 == 0) goto L14
            java.lang.String r0 = r6.getAkdlui()     // Catch: java.lang.Throwable -> L11
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L11
            if (r0 != 0) goto L14
            java.lang.String r0 = r6.getAkdlui()     // Catch: java.lang.Throwable -> L11
            goto L15
        L11:
            r6 = move-exception
            goto L94
        L14:
            r0 = r7
        L15:
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L11
            if (r1 == 0) goto L1f
            java.lang.String r0 = com.mbridge.msdk.foundation.tools.SameMD5.getMD5(r7)     // Catch: java.lang.Throwable -> L11
        L1f:
            r1 = 2
            com.mbridge.msdk.foundation.tools.v.a(r7, r1, r6)     // Catch: java.lang.Throwable -> L11
            com.mbridge.msdk.foundation.controller.a r1 = com.mbridge.msdk.foundation.controller.a.f()     // Catch: java.lang.Throwable -> L11
            android.content.Context r1 = r1.j()     // Catch: java.lang.Throwable -> L11
            boolean r2 = com.mbridge.msdk.foundation.tools.aa.b(r1)     // Catch: java.lang.Throwable -> L11
            if (r2 != 0) goto L3e
            java.lang.String r8 = r6.getNoticeUrl()     // Catch: java.lang.Throwable -> L11
            int r2 = com.mbridge.msdk.foundation.same.a.H     // Catch: java.lang.Throwable -> L11
            r5.a(r6, r8, r2)     // Catch: java.lang.Throwable -> L11
            com.mbridge.msdk.click.c.a(r1, r7, r0)     // Catch: java.lang.Throwable -> L11
            return
        L3e:
            com.mbridge.msdk.foundation.controller.a r1 = com.mbridge.msdk.foundation.controller.a.f()     // Catch: java.lang.Throwable -> L11
            android.content.Context r1 = r1.j()     // Catch: java.lang.Throwable -> L11
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L11
            r2.<init>()     // Catch: java.lang.Throwable -> L11
            r2.append(r0)     // Catch: java.lang.Throwable -> L11
            java.lang.String r3 = "isDowning"
            r2.append(r3)     // Catch: java.lang.Throwable -> L11
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L11
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L11
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: java.lang.Throwable -> L11
            com.mbridge.msdk.foundation.tools.y.a(r1, r2, r3)     // Catch: java.lang.Throwable -> L11
            com.mbridge.msdk.foundation.controller.a r1 = com.mbridge.msdk.foundation.controller.a.f()     // Catch: java.lang.Throwable -> L11
            android.content.Context r1 = r1.j()     // Catch: java.lang.Throwable -> L11
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L11
            r2.<init>()     // Catch: java.lang.Throwable -> L11
            r2.append(r0)     // Catch: java.lang.Throwable -> L11
            java.lang.String r0 = "process"
            r2.append(r0)     // Catch: java.lang.Throwable -> L11
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L11
            int r2 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L11
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L11
            com.mbridge.msdk.foundation.tools.y.a(r1, r0, r2)     // Catch: java.lang.Throwable -> L11
            java.lang.Thread r0 = new java.lang.Thread     // Catch: java.lang.Throwable -> L11
            com.mbridge.msdk.click.b$2 r1 = new com.mbridge.msdk.click.b$2     // Catch: java.lang.Throwable -> L11
            r1.<init>()     // Catch: java.lang.Throwable -> L11
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L11
            r0.start()     // Catch: java.lang.Throwable -> L11
            goto L9b
        L94:
            boolean r7 = com.mbridge.msdk.MBridgeConstans.DEBUG
            if (r7 == 0) goto L9b
            r6.printStackTrace()
        L9b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.click.b.c(com.mbridge.msdk.foundation.entity.CampaignEx, java.lang.String, boolean):void");
    }

    static /* synthetic */ void a(b bVar, CampaignEx campaignEx, boolean z, CommonJumpLoader.JumpLoaderResult jumpLoaderResult, int i, boolean z2) {
        if (campaignEx == null) {
            return;
        }
        String url = "";
        if (jumpLoaderResult != null) {
            try {
                url = jumpLoaderResult.getUrl();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        com.mbridge.msdk.foundation.db.c cVarA = com.mbridge.msdk.foundation.db.c.a(bVar.p);
        if (bVar.a(campaignEx.getLinkType(), url)) {
            bVar.a(jumpLoaderResult, campaignEx, false, z2);
            if (campaignEx.getJumpResult() != null && !TextUtils.isEmpty(campaignEx.getNoticeUrl())) {
                campaignEx.getJumpResult().setNoticeurl(campaignEx.getNoticeUrl());
            }
            cVarA.a(campaignEx, bVar.g, z, 1, i);
            return;
        }
        bVar.a(jumpLoaderResult, campaignEx, true, z2);
        cVarA.a(campaignEx, bVar.g, z, 0, i);
    }

    static /* synthetic */ void a(b bVar, CampaignEx campaignEx) {
        try {
            Intent intent = new Intent(bVar.q, (Class<?>) LoadingActivity.class);
            intent.setFlags(268435456);
            intent.putExtra(CampaignEx.JSON_KEY_ICON_URL, campaignEx.getIconUrl());
            bVar.q.startActivity(intent);
        } catch (Exception e2) {
            s.b("MBridge SDK M", "Exception", e2);
        }
    }

    static /* synthetic */ void a(b bVar, final boolean z, final Campaign campaign) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.mbridge.msdk.click.b.7
            @Override // java.lang.Runnable
            public final void run() {
                if (z && !b.a && MBridgeConstans.NATIVE_SHOW_LOADINGPAGER) {
                    b.g(b.this);
                }
                if (b.this.u == null || b.a || !MBridgeConstans.NATIVE_SHOW_LOADINGPAGER) {
                    return;
                }
                b.this.u.onDismissLoading(campaign);
            }
        });
    }

    static /* synthetic */ void g(b bVar) {
        try {
            Intent intent = new Intent();
            intent.setAction("ExitApp");
            bVar.q.sendBroadcast(intent);
        } catch (Exception e2) {
            s.b("MBridge SDK M", "Exception", e2);
        }
    }

    static /* synthetic */ void a(b bVar, final CampaignEx campaignEx, final String str, final boolean z) throws NoSuchAlgorithmException {
        String md5;
        if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getAkdlui())) {
            md5 = campaignEx.getAkdlui();
        } else {
            md5 = SameMD5.getMD5(str);
        }
        try {
            final String str2 = md5;
            com.mbridge.msdk.optimize.a.a(q.a("/apk", bVar.q, new boolean[1]), c.a(str), str, new com.mbridge.msdk.optimize.b() { // from class: com.mbridge.msdk.click.b.3
                @Override // com.mbridge.msdk.optimize.b
                public final void a() {
                    b.this.w.sendMessage(b.this.w.obtainMessage(0));
                    b.this.a(campaignEx, campaignEx.getNoticeUrl(), com.mbridge.msdk.foundation.same.a.G);
                    if (z) {
                        b.a(campaignEx, b.this.q, "start");
                    } else {
                        b.a(campaignEx, b.this.q, "shortcuts_start");
                    }
                }

                @Override // com.mbridge.msdk.optimize.b
                public final void a(int i) {
                    Message messageObtainMessage = b.this.w.obtainMessage(1);
                    messageObtainMessage.arg1 = i;
                    b.this.w.sendMessage(messageObtainMessage);
                }

                @Override // com.mbridge.msdk.optimize.b
                public final void a(File file) {
                    y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2 + "isDowning", 0L);
                    y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2 + "process", 0);
                    b.a(campaignEx, b.this.q, TtmlNode.END);
                    com.mbridge.msdk.foundation.db.g.b(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(campaignEx);
                    if (file.exists() && z) {
                        c.a(b.this.q, Uri.fromFile(file), str, str2);
                        y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2, file.getAbsolutePath());
                    } else {
                        if (z) {
                            return;
                        }
                        y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2, file.getAbsolutePath());
                    }
                }

                @Override // com.mbridge.msdk.optimize.b
                public final void b() {
                    c.a(b.this.q, str, str2);
                }
            });
        } catch (Throwable unused) {
            c.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, md5);
        }
    }
}
