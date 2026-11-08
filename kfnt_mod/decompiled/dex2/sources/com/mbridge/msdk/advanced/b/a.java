package com.mbridge.msdk.advanced.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.advanced.view.MBNativeAdvancedView;
import com.mbridge.msdk.advanced.view.MBNativeAdvancedWebview;
import com.mbridge.msdk.b.d;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.mbbid.out.BidResponsed;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* compiled from: NativeAdvancedLoadManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static String a = "NativeAdvancedLoadManager";
    private String b;
    private String c;
    private long d;
    private com.mbridge.msdk.advanced.a.a e;
    private MBNativeAdvancedView g;
    private d h;
    private CampaignEx i;
    private int j;
    private int k;
    private int l;
    private int m;
    private String n;
    private int o;
    private boolean p;
    private volatile boolean q;
    private com.mbridge.msdk.videocommon.listener.a r;
    private H5DownLoadManager.ZipDownloadListener s;
    private H5DownLoadManager.ZipDownloadListener t;
    private String u;
    private int v;
    private String w = "";
    private Handler x = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.advanced.b.a.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) throws Throwable {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    Object obj = message.obj;
                    int i = message.arg1;
                    if (obj != null && (obj instanceof CampaignEx)) {
                        CampaignEx campaignEx = (CampaignEx) obj;
                        a.a(a.this, H5DownLoadManager.getInstance().getH5ResAddress(campaignEx.getAdZip()), campaignEx, i);
                        break;
                    }
                    break;
                case 2:
                    Object obj2 = message.obj;
                    if (obj2 != null && (obj2 instanceof String)) {
                        a.this.a(obj2.toString(), a.this.n, a.this.o);
                        break;
                    }
                    break;
                case 3:
                    Object obj3 = message.obj;
                    if (obj3 != null && (obj3 instanceof CampaignEx)) {
                        a.this.b((CampaignEx) obj3, a.this.o);
                        break;
                    }
                    break;
                case 4:
                    Object obj4 = message.obj;
                    if (obj4 != null && (obj4 instanceof CampaignEx)) {
                        if (a.this.g != null) {
                            a.this.g.setEndCardReady(true);
                        }
                        a.this.b((CampaignEx) obj4, a.this.o);
                        break;
                    }
                    break;
                case 5:
                    Object obj5 = message.obj;
                    if (obj5 != null && (obj5 instanceof CampaignEx)) {
                        if (a.this.g != null) {
                            a.this.g.setVideoReady(true);
                        }
                        a.this.b((CampaignEx) obj5, a.this.o);
                        break;
                    }
                    break;
            }
        }
    };
    private Runnable y = new Runnable() { // from class: com.mbridge.msdk.advanced.b.a.5
        @Override // java.lang.Runnable
        public final void run() throws Throwable {
            a.this.a("load timeout", a.this.n, a.this.o);
        }
    };
    private Context f = com.mbridge.msdk.foundation.controller.a.f().j();

    public a(String str, String str2, long j) {
        this.c = str;
        this.b = str2;
    }

    public final void a(d dVar) {
        this.h = dVar;
    }

    public final void a(MBNativeAdvancedView mBNativeAdvancedView) {
        this.g = mBNativeAdvancedView;
    }

    public final void a(int i) {
        this.j = i;
    }

    public final void a(int i, int i2) {
        this.m = i;
        this.l = i2;
    }

    public final void a(String str, int i) throws Throwable {
        CampaignEx campaignExA;
        List<Integer> listU;
        this.q = false;
        this.n = str;
        this.o = i;
        this.i = null;
        if (this.g == null) {
            a("MBAdvancedNativeView is null", str, i);
            return;
        }
        if (TextUtils.isEmpty(this.n)) {
            campaignExA = c.a(this.g, this.c, this.b, str, this.j, false, false);
        } else {
            campaignExA = c.a(this.g, this.c, this.b, str, this.j, false, true);
        }
        long timestamp = campaignExA != null ? campaignExA.getTimestamp() : 0L;
        if (this.h != null && this.h.p() == 1 && this.g != null && campaignExA != null) {
            a(campaignExA, i);
            return;
        }
        this.p = false;
        if (this.h != null && (listU = this.h.u()) != null && listU.size() > 0) {
            this.d = listU.get(0).intValue() * 1000;
        } else {
            this.d = 30000L;
        }
        s.a(a, "开始从V3请求新的 offer，超时 ：" + this.d);
        if (this.h == null || timestamp <= 0 || !TextUtils.isEmpty(str)) {
            a(this.d);
            a(this.f, str, i);
        } else if (System.currentTimeMillis() - timestamp > this.h.z() * 1000) {
            a(this.d);
            a(this.f, str, i);
        } else {
            a(campaignExA, i);
        }
    }

    private void a(long j) {
        this.x.postDelayed(this.y, j);
    }

    public final String a() {
        return this.w;
    }

    private void a(Context context, final String str, final int i) throws Throwable {
        com.mbridge.msdk.advanced.common.b.a();
        try {
            if (context == null) {
                a("Context is null", str, i);
                return;
            }
            if (z.a(this.b)) {
                a("UnitId is null", str, i);
                return;
            }
            s.b(a, "load 开始准备请求参数");
            com.mbridge.msdk.foundation.same.net.h.d dVarA = a(i, this.u);
            if (dVarA == null) {
                s.b(a, "load 请求参数为空 load失败");
                a("Load param is null", str, i);
                return;
            }
            if (dVarA != null && !TextUtils.isEmpty(str)) {
                dVarA.a(BidResponsed.KEY_TOKEN, str);
            }
            String strE = w.e(this.b);
            if (!TextUtils.isEmpty(strE)) {
                dVarA.a("j", strE);
            }
            com.mbridge.msdk.advanced.e.a aVar = new com.mbridge.msdk.advanced.e.a(context);
            com.mbridge.msdk.advanced.e.c cVar = new com.mbridge.msdk.advanced.e.c(i) { // from class: com.mbridge.msdk.advanced.b.a.6
                @Override // com.mbridge.msdk.advanced.e.c
                public final void a(CampaignUnit campaignUnit, final int i2) throws Throwable {
                    try {
                        s.b(a.a, "onLoadCompaginSuccess 数据刚请求回来");
                        a.a(a.this, campaignUnit, i2, a.this.b, str);
                        a.this.w = campaignUnit.getRequestId();
                    } catch (Exception e) {
                        e.printStackTrace();
                        s.b(a.a, "onLoadCompaginSuccess 数据刚请求失败");
                        if (a.this.x != null) {
                            a.this.x.post(new Runnable() { // from class: com.mbridge.msdk.advanced.b.a.6.1
                                @Override // java.lang.Runnable
                                public final void run() throws Throwable {
                                    a.this.a("Exception after load success", str, i2);
                                }
                            });
                        }
                        a.this.v = 0;
                    }
                }

                @Override // com.mbridge.msdk.advanced.e.c
                public final void a(int i2, final String str2) {
                    s.d(a.a, str2);
                    s.b(a.a, "onLoadCompaginFailed load failed errorCode:" + i2 + " msg:" + str2);
                    if (a.this.x != null) {
                        a.this.x.post(new Runnable() { // from class: com.mbridge.msdk.advanced.b.a.6.2
                            @Override // java.lang.Runnable
                            public final void run() throws Throwable {
                                a.this.a(str2, str, i);
                            }
                        });
                    }
                    a.this.v = 0;
                }
            };
            cVar.a(str);
            cVar.d = this.b;
            cVar.e = this.c;
            cVar.f = 298;
            if (com.mbridge.msdk.foundation.same.net.g.d.c().b() && TextUtils.isEmpty(str)) {
                aVar.b(1, com.mbridge.msdk.foundation.same.net.g.d.c().a(str), dVarA, cVar);
            } else {
                aVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().a(str), dVarA, cVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
            a("Load exception", str, i);
            this.v = 0;
        }
    }

    private com.mbridge.msdk.foundation.same.net.h.d a(int i, String str) throws JSONException, NoSuchAlgorithmException {
        String strK = com.mbridge.msdk.foundation.controller.a.f().k();
        String md5 = SameMD5.getMD5(com.mbridge.msdk.foundation.controller.a.f().k() + com.mbridge.msdk.foundation.controller.a.f().l());
        com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
        String strA = com.mbridge.msdk.foundation.same.a.d.a(this.b, "h5_native");
        String strA2 = com.mbridge.msdk.advanced.common.a.a.a(this.f, this.b);
        String strB = w.b(this.f, this.b);
        String strA3 = com.mbridge.msdk.advanced.common.a.a.a();
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "app_id", strK);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, MBridgeConstans.PROPERTIES_UNIT_ID, this.b);
        if (!TextUtils.isEmpty(this.c)) {
            com.mbridge.msdk.foundation.same.net.g.b.a(dVar, MBridgeConstans.PLACEMENT_ID, this.c);
        }
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "sign", md5);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "req_type", i + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ad_num", "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "tnum", "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "only_impression", "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ping_mode", "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ttc_ids", strA2);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.b, strA);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.c, strB);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "install_ids", strA3);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.a, str);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ad_type", "298");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "offset", this.v + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "unit_size", this.m + "x" + this.l);
        return dVar;
    }

    public final void a(com.mbridge.msdk.advanced.a.a aVar) {
        this.e = aVar;
    }

    public final void b() throws Throwable {
        if (this.e != null) {
            this.e = null;
        }
        a("LoadManager release", this.n, this.o);
        if (this.r != null) {
            this.r = null;
        }
        if (this.s != null) {
            this.s = null;
        }
    }

    private void a(CampaignEx campaignEx, int i) throws Throwable {
        this.i = campaignEx;
        if (c.a(this.g, campaignEx, this.c, this.b)) {
            b(campaignEx, i);
        } else {
            c(campaignEx, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CampaignEx campaignEx, int i) {
        if (!c.a(this.g, campaignEx, this.c, this.b) || this.q) {
            return;
        }
        d();
        c.a(campaignEx, this.b);
        this.q = true;
        if (this.e != null) {
            this.e.a(campaignEx, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, int i) throws Throwable {
        if (this.p) {
            this.p = false;
            a(str, i, str2);
        } else {
            b(str, i);
        }
    }

    private void a(String str, int i, String str2) throws Throwable {
        CampaignEx campaignExA = c.a(this.g, this.c, this.b, str2, this.j, true, true);
        if (campaignExA != null) {
            s.d(a, "load failed cache ");
            a(campaignExA, i);
        } else {
            b(str, i);
        }
    }

    private void b(String str, int i) {
        if (this.q) {
            return;
        }
        d();
        s.d(a, "real failed: " + str);
        this.q = true;
        if (this.e != null) {
            this.e.a(str, i);
        }
    }

    private void d() {
        this.x.removeCallbacks(this.y);
    }

    private void c(CampaignEx campaignEx, int i) throws Throwable {
        if (this.g != null) {
            this.g.clearResState();
        }
        if (!TextUtils.isEmpty(campaignEx.getAdZip())) {
            s.a(a, "开始下载zip： " + campaignEx.getAdZip());
            h(campaignEx, i);
        }
        if (!TextUtils.isEmpty(campaignEx.getAdHtml())) {
            s.a(a, "开始下载HTML： " + campaignEx.getAdHtml());
            g(campaignEx, i);
        }
        if (!TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
            s.a(a, "开始下载Video： " + campaignEx.getVideoUrlEncode());
            i(campaignEx, i);
        }
        if (!TextUtils.isEmpty(campaignEx.getImageUrl())) {
            s.a(a, "开始下载image： " + campaignEx.getImageUrl());
            f(campaignEx, i);
        }
        if (!TextUtils.isEmpty(campaignEx.getendcard_url())) {
            s.a(a, "开始下载EndCard： " + campaignEx.getendcard_url());
            e(campaignEx, i);
        }
        if (TextUtils.isEmpty(campaignEx.getGifUrl())) {
            return;
        }
        s.a(a, "开始下载gitUrl： " + campaignEx.getGifUrl());
        d(campaignEx, i);
    }

    private void d(final CampaignEx campaignEx, final int i) {
        com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(campaignEx.getGifUrl(), new com.mbridge.msdk.foundation.same.c.c() { // from class: com.mbridge.msdk.advanced.b.a.8
            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onSuccessLoad(Bitmap bitmap, String str) {
                s.a(a.a, "giturl 下载成功： " + str);
                if (a.this.x != null) {
                    a.this.x.post(new Runnable() { // from class: com.mbridge.msdk.advanced.b.a.8.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            a.this.b(campaignEx, i);
                        }
                    });
                }
            }

            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onFailedLoad(String str, String str2) {
                s.a(a.a, "gifurl 下载失败： " + str2);
                if (a.this.x != null) {
                    a.this.x.post(new Runnable() { // from class: com.mbridge.msdk.advanced.b.a.8.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            a.this.b(campaignEx, i);
                        }
                    });
                }
            }
        });
    }

    private void e(final CampaignEx campaignEx, final int i) {
        this.t = new H5DownLoadManager.ZipDownloadListener() { // from class: com.mbridge.msdk.advanced.b.a.9
            @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
            public final void onSuccess(String str) {
                s.a(a.a, "endcard 下载成功： " + str);
                if (a.this.x != null) {
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 4;
                    messageObtain.obj = campaignEx;
                    messageObtain.arg1 = i;
                    a.this.x.sendMessage(messageObtain);
                }
            }

            @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
            public final void onFailed(String str, String str2) {
                s.a(a.a, "endcard 下载失败： " + str2);
                if (a.this.g != null) {
                    a.this.g.setEndCardReady(false);
                }
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                messageObtain.obj = str;
                a.this.x.sendMessage(messageObtain);
            }
        };
        H5DownLoadManager.getInstance().downloadH5Res(campaignEx.getendcard_url(), this.t);
    }

    private void f(final CampaignEx campaignEx, final int i) {
        com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(campaignEx.getImageUrl(), new com.mbridge.msdk.foundation.same.c.c() { // from class: com.mbridge.msdk.advanced.b.a.10
            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onSuccessLoad(Bitmap bitmap, String str) {
                s.a(a.a, "image 下载成功： " + str);
                if (a.this.x != null) {
                    a.this.x.post(new Runnable() { // from class: com.mbridge.msdk.advanced.b.a.10.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            a.this.b(campaignEx, i);
                        }
                    });
                }
            }

            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onFailedLoad(String str, String str2) {
                s.a(a.a, "image 下载失败： " + str2);
                if (a.this.x != null) {
                    a.this.x.post(new Runnable() { // from class: com.mbridge.msdk.advanced.b.a.10.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            a.this.b(campaignEx, i);
                        }
                    });
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x008c A[Catch: Exception -> 0x00f3, PHI: r0 r4
  0x008c: PHI (r0v4 java.io.FileOutputStream) = (r0v14 java.io.FileOutputStream), (r0v16 java.io.FileOutputStream) binds: [B:33:0x00ac, B:21:0x008a] A[DONT_GENERATE, DONT_INLINE]
  0x008c: PHI (r4v1 java.io.File) = (r4v3 java.io.File), (r4v7 java.io.File) binds: [B:33:0x00ac, B:21:0x008a] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x00f3, blocks: (B:46:0x00ef, B:49:0x00f5, B:35:0x00af, B:37:0x00b5, B:39:0x00bb, B:41:0x00c1, B:43:0x00d3, B:44:0x00de, B:22:0x008c), top: B:57:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void g(final com.mbridge.msdk.foundation.entity.CampaignEx r7, final int r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.advanced.b.a.g(com.mbridge.msdk.foundation.entity.CampaignEx, int):void");
    }

    private void h(final CampaignEx campaignEx, final int i) {
        this.s = new H5DownLoadManager.ZipDownloadListener() { // from class: com.mbridge.msdk.advanced.b.a.12
            @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
            public final void onSuccess(String str) {
                s.a(a.a, "zip 下载成功： " + str);
                Message messageObtain = Message.obtain();
                messageObtain.what = 1;
                messageObtain.obj = campaignEx;
                messageObtain.arg1 = i;
                a.this.x.sendMessage(messageObtain);
            }

            @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
            public final void onFailed(String str, String str2) {
                s.a(a.a, "zip 下载失败： " + str2 + " " + str);
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                messageObtain.obj = str;
                a.this.x.sendMessage(messageObtain);
            }
        };
        H5DownLoadManager.getInstance().downloadH5Res(campaignEx.getAdZip(), this.s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CampaignEx campaignEx, String str, int i) throws Throwable {
        a(str, this.n, i);
        b(campaignEx, str, 2);
    }

    private void b(CampaignEx campaignEx, String str, int i) {
        com.mbridge.msdk.advanced.d.a.b(com.mbridge.msdk.advanced.common.c.a().c(campaignEx.getId()).b(this.b).d(campaignEx.getRequestId()).g(campaignEx.getRequestIdNotice()).f(str).b(i).a(campaignEx.isBidCampaign()), this.b);
    }

    private void i(final CampaignEx campaignEx, int i) throws ClassNotFoundException {
        this.r = new com.mbridge.msdk.videocommon.listener.a() { // from class: com.mbridge.msdk.advanced.b.a.4
            @Override // com.mbridge.msdk.videocommon.listener.a
            public final void a(String str) {
                s.a(a.a, "Video 下载成功： " + str);
                Message messageObtain = Message.obtain();
                messageObtain.obj = campaignEx;
                messageObtain.what = 5;
                a.this.x.sendMessage(messageObtain);
            }

            @Override // com.mbridge.msdk.videocommon.listener.a
            public final void a(String str, String str2) {
                s.a(a.a, "Video 下载失败： " + str);
                Message messageObtain = Message.obtain();
                messageObtain.obj = str;
                messageObtain.what = 2;
                a.this.x.sendMessage(messageObtain);
            }
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add(campaignEx);
        com.mbridge.msdk.videocommon.download.b.getInstance().createUnitCache(this.f, this.b, arrayList, 298, this.r);
        if (!com.mbridge.msdk.videocommon.download.b.getInstance().a(298, this.b, campaignEx.isBidCampaign())) {
            s.a(a, " load Video");
            com.mbridge.msdk.videocommon.download.b.getInstance().load(this.b);
        } else {
            s.a(a, " load Video isReady true");
            this.g.setVideoReady(true);
            b(campaignEx, i);
        }
    }

    public final String a(String str) {
        int iF;
        if (this.i == null) {
            return "";
        }
        try {
            com.mbridge.msdk.videocommon.download.a aVarA = com.mbridge.msdk.videocommon.download.b.getInstance().a(this.b, this.i.getId() + this.i.getVideoUrlEncode() + this.i.getBidToken());
            if (aVarA == null || (iF = aVarA.f()) != 5) {
                return str;
            }
            String strD = aVarA.d();
            if (!new File(strD).exists()) {
                return str;
            }
            s.d(a, "本地已下载完 拿本地播放地址：" + strD + " state：" + iF);
            return strD;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    static /* synthetic */ void a(a aVar, final String str, final CampaignEx campaignEx, final int i) throws Throwable {
        if (aVar.g == null || aVar.g.getAdvancedNativeWebview() == null) {
            return;
        }
        com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.advanced.common.c.a().c(campaignEx.getId()).d(campaignEx.getRequestId()).g(campaignEx.getRequestIdNotice()).b(aVar.b).a(campaignEx.isBidCampaign()), aVar.b);
        com.mbridge.msdk.advanced.js.a aVar2 = new com.mbridge.msdk.advanced.js.a(aVar.g.getContext(), aVar.c, aVar.b);
        ArrayList arrayList = new ArrayList();
        arrayList.add(campaignEx);
        aVar2.a(arrayList);
        aVar2.a(aVar.j);
        aVar2.b(aVar.k);
        aVar.g.setAdvancedNativeJSBridgeImpl(aVar2);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        final MBNativeAdvancedWebview advancedNativeWebview = aVar.g.getAdvancedNativeWebview();
        if (advancedNativeWebview == null) {
            aVar.a(campaignEx, "webview is null", i);
            return;
        }
        if (advancedNativeWebview != null && advancedNativeWebview.isDestoryed()) {
            aVar.a(campaignEx, "webview is destroyed", i);
            return;
        }
        advancedNativeWebview.setWebViewListener(new com.mbridge.msdk.mbjscommon.c.b() { // from class: com.mbridge.msdk.advanced.b.a.2
            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, int i2) throws Throwable {
                super.a(webView, i2);
                s.d("NativeAdvancedLoadManager", "=========readyState: " + i2);
                if (i2 == 1) {
                    a.this.g.setH5Ready(true);
                    com.mbridge.msdk.advanced.common.b.a(campaignEx.getRequestId(), true);
                    a.b(a.this, campaignEx, i);
                    com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, a.this.b, "", jCurrentTimeMillis, 1);
                    return;
                }
                a.this.a(campaignEx, "readyState 2", i);
                com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, a.this.b, "readyState 2", jCurrentTimeMillis, 3);
            }

            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, int i2, String str2, String str3) throws Throwable {
                super.a(webView, i2, str2, str3);
                s.d("NativeAdvancedLoadManager", "onReceivedError： " + i2 + "  " + str2 + "  " + str3);
                a.this.a(campaignEx, str2, i);
                com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, a.this.b, "error code:" + i2 + str2, jCurrentTimeMillis, 3);
            }

            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) throws Throwable {
                super.a(webView, sslErrorHandler, sslError);
                s.d("NativeAdvancedLoadManager", "onReceivedSslError: " + sslError.getPrimaryError());
                a.this.a(campaignEx, "onReceivedSslError:" + sslError.getUrl(), i);
                com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, a.this.b, "error url:" + sslError.getUrl(), jCurrentTimeMillis, 3);
            }

            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, String str2) {
                super.a(webView, str2);
                s.d("NativeAdvancedLoadManager", "onPageFinished");
                if (!campaignEx.isHasMBTplMark()) {
                    a.this.g.setH5Ready(true);
                    s.d("NativeAdvancedLoadManager", "=======onPageFinished OK");
                    com.mbridge.msdk.advanced.common.b.a(campaignEx.getRequestId(), true);
                    a.b(a.this, campaignEx, i);
                    com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, a.this.b, "", jCurrentTimeMillis, 1);
                }
                com.mbridge.msdk.advanced.js.b.a(webView);
            }
        });
        if (!advancedNativeWebview.isDestoryed()) {
            s.d(a, "=======开始渲染: " + str);
            if (aVar.x != null) {
                aVar.x.post(new Runnable() { // from class: com.mbridge.msdk.advanced.b.a.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        advancedNativeWebview.loadUrl(str);
                    }
                });
                return;
            }
            return;
        }
        aVar.a("webview has destory", aVar.n, i);
        com.mbridge.msdk.advanced.d.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, aVar.b, "webview had destory", jCurrentTimeMillis, 3);
    }

    static /* synthetic */ void a(a aVar, CampaignUnit campaignUnit, int i, String str, String str2) throws Throwable {
        ArrayList arrayList;
        if (campaignUnit == null || campaignUnit.getAds() == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            final CampaignEx campaignEx = campaignUnit.getAds().get(0);
            campaignEx.setCampaignUnitId(aVar.b);
            new Thread(new Runnable() { // from class: com.mbridge.msdk.advanced.b.a.7
                @Override // java.lang.Runnable
                public final void run() {
                    m.a(i.a(a.this.f)).b();
                    w.a(a.this.f, campaignEx);
                }
            }).start();
            aVar.u = campaignUnit.getSessionId();
            if (campaignEx.getOfferType() != 99 && (!TextUtils.isEmpty(campaignEx.getAdZip()) || !TextUtils.isEmpty(campaignEx.getAdHtml()))) {
                if (w.b(campaignEx)) {
                    campaignEx.setRtinsType(w.c(aVar.f, campaignEx.getPackageName()) ? 1 : 2);
                }
                if (campaignEx.getWtick() == 1 || !w.c(aVar.f, campaignEx.getPackageName()) || w.b(campaignEx)) {
                    arrayList.add(campaignEx);
                } else {
                    w.a(aVar.b, campaignEx, com.mbridge.msdk.foundation.same.a.D);
                }
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            s.b(a, "onload load失败 返回的compaign没有可以用的");
            aVar.a("invalid  campaign", str2, i);
            return;
        }
        try {
            aVar.v++;
            if (aVar.h == null || aVar.v > aVar.h.x()) {
                s.b(a, "onload 重置offset为0");
                aVar.v = 0;
            }
            s.b(a, "onload 算出 下次的offset是:" + aVar.v);
        } catch (Exception e) {
            e.printStackTrace();
        }
        s.b(a, "onload load成功 size:" + arrayList.size());
        CampaignEx campaignEx2 = (CampaignEx) arrayList.get(0);
        if (!TextUtils.isEmpty(campaignEx2.getAdZip()) || (!TextUtils.isEmpty(campaignEx2.getAdHtml()) && campaignEx2.getAdHtml().contains("<MBTPLMARK>"))) {
            campaignEx2.setHasMBTplMark(true);
            campaignEx2.setIsMraid(false);
        } else {
            campaignEx2.setHasMBTplMark(false);
            campaignEx2.setIsMraid(true);
        }
        aVar.a(campaignEx2, i);
    }

    static /* synthetic */ void b(a aVar, CampaignEx campaignEx, int i) {
        if (aVar.g.isH5Ready()) {
            aVar.b(campaignEx, i);
            aVar.b(campaignEx, "", 1);
        }
    }
}
