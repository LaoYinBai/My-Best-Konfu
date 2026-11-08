package com.mbridge.msdk.splash.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
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
import com.mbridge.msdk.splash.c.d;
import com.mbridge.msdk.splash.view.MBSplashView;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* compiled from: SplashLoadManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private static String a = "SplashLoadManager";
    private String b;
    private String c;
    private long d;
    private long e;
    private com.mbridge.msdk.splash.b.a f;
    private MBSplashView h;
    private com.mbridge.msdk.b.d i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private String n;
    private int o;
    private boolean p;
    private volatile boolean q;
    private com.mbridge.msdk.videocommon.listener.a r;
    private H5DownLoadManager.ZipDownloadListener s;
    private String t;
    private int u;
    private String v = "";
    private Handler w = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.splash.c.b.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) throws Throwable {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    Object obj = message.obj;
                    int i = message.arg1;
                    if (obj instanceof CampaignEx) {
                        CampaignEx campaignEx = (CampaignEx) obj;
                        b.this.a(H5DownLoadManager.getInstance().getH5ResAddress(campaignEx.getAdZip()), campaignEx, i);
                        break;
                    }
                    break;
                case 2:
                    Object obj2 = message.obj;
                    if (obj2 instanceof String) {
                        b.this.a(obj2.toString(), b.this.n, b.this.o);
                        break;
                    }
                    break;
                case 3:
                    Object obj3 = message.obj;
                    if (obj3 instanceof CampaignEx) {
                        b.this.b((CampaignEx) obj3, b.this.o);
                        break;
                    }
                    break;
            }
        }
    };
    private Runnable x = new Runnable() { // from class: com.mbridge.msdk.splash.c.b.2
        @Override // java.lang.Runnable
        public final void run() throws Throwable {
            b.this.a("load timeout", b.this.n, b.this.o);
        }
    };
    private Context g = com.mbridge.msdk.foundation.controller.a.f().j();

    public b(String str, String str2, long j) {
        this.c = str;
        this.b = str2;
        this.e = j;
    }

    public final void a(com.mbridge.msdk.b.d dVar) {
        this.i = dVar;
    }

    public final void a(MBSplashView mBSplashView) {
        this.h = mBSplashView;
    }

    public final void a(boolean z) {
        this.j = z;
    }

    public final void a(int i) {
        this.k = i;
    }

    public final void a(int i, int i2) {
        this.m = i;
        this.l = i2;
    }

    public final String a() {
        return this.v;
    }

    public final void a(String str, int i) throws Throwable {
        this.q = false;
        this.n = str;
        this.o = i;
        CampaignEx campaignExA = a.a(this.h, this.c, this.b, str, this.j, this.k, false, false);
        long timestamp = campaignExA != null ? campaignExA.getTimestamp() : 0L;
        if (this.i.p() == 1 && i != 1 && campaignExA != null) {
            a(campaignExA, i);
            return;
        }
        this.p = true;
        if (i == 1) {
            List<Integer> listU = this.i.u();
            if (listU != null && listU.size() > 0) {
                this.d = listU.get(0).intValue() * 1000;
            } else {
                this.d = 30000L;
            }
        } else if (this.e <= 0) {
            this.d = this.i.q();
        } else {
            this.d = this.e;
        }
        if (this.i == null || timestamp <= 0 || !TextUtils.isEmpty(str)) {
            a(this.d);
            a(this.g, str, i);
            return;
        }
        if (System.currentTimeMillis() - timestamp > this.i.z() * 1000) {
            a(this.d);
            a(this.g, str, i);
        } else {
            a(campaignExA, i);
        }
    }

    private void a(long j) {
        this.w.postDelayed(this.x, j);
    }

    private void a(Context context, final String str, final int i) throws Throwable {
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
            com.mbridge.msdk.foundation.same.net.h.d dVarA = a(i, this.t);
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
            com.mbridge.msdk.splash.f.b bVar = new com.mbridge.msdk.splash.f.b(context);
            com.mbridge.msdk.splash.f.a aVar = new com.mbridge.msdk.splash.f.a(i) { // from class: com.mbridge.msdk.splash.c.b.3
                @Override // com.mbridge.msdk.splash.f.a
                public final void a(CampaignUnit campaignUnit, int i2) throws Throwable {
                    try {
                        s.b(b.a, "onLoadCompaginSuccess 数据刚请求回来");
                        b.a(b.this, campaignUnit, i2, b.this.b, str);
                        b.this.v = campaignUnit.getRequestId();
                    } catch (Exception e) {
                        e.printStackTrace();
                        s.b(b.a, "onLoadCompaginSuccess 数据刚请求失败： " + e.getMessage());
                        b.this.a("Exception after load success", str, i2);
                        b.this.u = 0;
                    }
                }

                @Override // com.mbridge.msdk.splash.f.a
                public final void a(int i2, String str2) throws Throwable {
                    s.d(b.a, str2);
                    s.b(b.a, "onLoadCompaginFailed load failed errorCode:" + i2 + " msg:" + str2);
                    b.this.a(str2, str, i);
                    b.this.u = 0;
                }
            };
            aVar.a(str);
            aVar.d = this.b;
            aVar.e = this.c;
            aVar.f = 297;
            if (com.mbridge.msdk.foundation.same.net.g.d.c().b() && TextUtils.isEmpty(str)) {
                bVar.b(1, com.mbridge.msdk.foundation.same.net.g.d.c().a(str), dVarA, aVar);
            } else {
                bVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().a(str), dVarA, aVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
            a("Load exception", str, i);
            this.u = 0;
        }
    }

    private com.mbridge.msdk.foundation.same.net.h.d a(int i, String str) throws JSONException, NoSuchAlgorithmException {
        String strK = com.mbridge.msdk.foundation.controller.a.f().k();
        String md5 = SameMD5.getMD5(com.mbridge.msdk.foundation.controller.a.f().k() + com.mbridge.msdk.foundation.controller.a.f().l());
        com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
        String strA = com.mbridge.msdk.foundation.same.a.d.a(this.b, "splash");
        String strA2 = com.mbridge.msdk.splash.a.a.a.a(this.g, this.b);
        String strB = w.b(this.g, this.b);
        String strA3 = com.mbridge.msdk.splash.a.a.a.a();
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
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ad_type", "297");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "offset", this.u + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "unit_size", this.m + "x" + this.l);
        return dVar;
    }

    public final void a(com.mbridge.msdk.splash.b.a aVar) {
        this.f = aVar;
    }

    public final void b() {
        if (this.f != null) {
            this.f = null;
        }
        if (this.r != null) {
            this.r = null;
        }
        if (this.s != null) {
            this.s = null;
        }
    }

    private void a(CampaignEx campaignEx, int i) throws Throwable {
        if (a.a(this.h, campaignEx)) {
            b(campaignEx, i);
        } else {
            c(campaignEx, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CampaignEx campaignEx, int i) {
        if (!a.a(this.h, campaignEx) || this.q) {
            return;
        }
        d();
        if (this.p) {
            a.a(campaignEx, this.b);
        }
        this.q = true;
        if (this.f != null) {
            this.f.a(campaignEx, i);
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
        CampaignEx campaignExA = a.a(this.h, this.c, this.b, str2, this.j, this.k, true, false);
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
        s.d(a, "real failed ");
        this.q = true;
        if (this.f != null) {
            this.f.a(str, i);
        }
    }

    private void d() {
        this.w.removeCallbacks(this.x);
    }

    private void c(CampaignEx campaignEx, int i) throws Throwable {
        this.h.clearResState();
        if (!TextUtils.isEmpty(campaignEx.getAdZip())) {
            f(campaignEx, i);
        }
        if (!TextUtils.isEmpty(campaignEx.getAdHtml())) {
            e(campaignEx, i);
        }
        if (!TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
            g(campaignEx, i);
        }
        if (TextUtils.isEmpty(campaignEx.getImageUrl())) {
            return;
        }
        d(campaignEx, i);
    }

    private void d(final CampaignEx campaignEx, final int i) {
        com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(campaignEx.getImageUrl(), new com.mbridge.msdk.foundation.same.c.c() { // from class: com.mbridge.msdk.splash.c.b.5
            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onFailedLoad(String str, String str2) {
            }

            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onSuccessLoad(Bitmap bitmap, String str) {
                b.this.b(campaignEx, i);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x008c A[Catch: Exception -> 0x00ef, PHI: r0 r4
  0x008c: PHI (r0v4 java.io.FileOutputStream) = (r0v12 java.io.FileOutputStream), (r0v14 java.io.FileOutputStream) binds: [B:33:0x00ac, B:21:0x008a] A[DONT_GENERATE, DONT_INLINE]
  0x008c: PHI (r4v1 java.io.File) = (r4v3 java.io.File), (r4v7 java.io.File) binds: [B:33:0x00ac, B:21:0x008a] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x00ef, blocks: (B:44:0x00eb, B:47:0x00f1, B:35:0x00af, B:37:0x00b5, B:39:0x00bb, B:41:0x00c1, B:42:0x00e1, B:22:0x008c), top: B:52:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(com.mbridge.msdk.foundation.entity.CampaignEx r7, int r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.splash.c.b.e(com.mbridge.msdk.foundation.entity.CampaignEx, int):void");
    }

    private void f(final CampaignEx campaignEx, final int i) {
        this.s = new H5DownLoadManager.ZipDownloadListener() { // from class: com.mbridge.msdk.splash.c.b.6
            @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
            public final void onSuccess(String str) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 1;
                messageObtain.obj = campaignEx;
                messageObtain.arg1 = i;
                b.this.w.sendMessage(messageObtain);
            }

            @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
            public final void onFailed(String str, String str2) throws Throwable {
                b.this.a(str, b.this.n, i);
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                messageObtain.obj = str;
                b.this.w.sendMessage(messageObtain);
            }
        };
        H5DownLoadManager.getInstance().downloadH5Res(campaignEx.getAdZip(), this.s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, final CampaignEx campaignEx, final int i) {
        d.c cVar = new d.c();
        cVar.c(this.b);
        cVar.b(this.c);
        cVar.a(campaignEx);
        cVar.a(str);
        cVar.a(this.j);
        cVar.a(this.k);
        d.a.a.a(this.h, cVar, new d.b() { // from class: com.mbridge.msdk.splash.c.b.7
            @Override // com.mbridge.msdk.splash.c.d.b
            public final void a() {
                if (campaignEx.isHasMBTplMark()) {
                    return;
                }
                b.b(b.this, campaignEx, i);
            }

            @Override // com.mbridge.msdk.splash.c.d.b
            public final void a(String str2) throws Throwable {
                b.a(b.this, str2, i);
            }

            @Override // com.mbridge.msdk.splash.c.d.b
            public final void a(int i2) throws Throwable {
                if (i2 == 1) {
                    b.b(b.this, campaignEx, i);
                } else {
                    b.a(b.this, "readyState 2", i);
                }
            }
        });
    }

    private void g(final CampaignEx campaignEx, int i) throws ClassNotFoundException {
        this.r = new com.mbridge.msdk.videocommon.listener.a() { // from class: com.mbridge.msdk.splash.c.b.8
            @Override // com.mbridge.msdk.videocommon.listener.a
            public final void a(String str) {
                b.this.h.setVideoReady(true);
                s.a(b.a, "========VIDEO SUC");
                Message messageObtain = Message.obtain();
                messageObtain.obj = campaignEx;
                messageObtain.what = 3;
                b.this.w.sendMessage(messageObtain);
            }

            @Override // com.mbridge.msdk.videocommon.listener.a
            public final void a(String str, String str2) {
                b.this.h.setVideoReady(false);
                s.a(b.a, "========VIDEO FAI");
                Message messageObtain = Message.obtain();
                messageObtain.obj = str;
                messageObtain.what = 2;
                b.this.w.sendMessage(messageObtain);
            }
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add(campaignEx);
        com.mbridge.msdk.videocommon.download.b.getInstance().createUnitCache(this.g, this.b, arrayList, 297, this.r);
        if (!com.mbridge.msdk.videocommon.download.b.getInstance().a(297, this.b, campaignEx.isBidCampaign())) {
            com.mbridge.msdk.videocommon.download.b.getInstance().load(this.b);
        } else {
            this.h.setVideoReady(true);
            b(campaignEx, i);
        }
    }

    static /* synthetic */ void a(b bVar, CampaignUnit campaignUnit, int i, String str, String str2) throws Throwable {
        ArrayList arrayList;
        if (campaignUnit == null || campaignUnit.getAds() == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            final CampaignEx campaignEx = campaignUnit.getAds().get(0);
            campaignEx.setCampaignUnitId(bVar.b);
            new Thread(new Runnable() { // from class: com.mbridge.msdk.splash.c.b.4
                @Override // java.lang.Runnable
                public final void run() {
                    m.a(i.a(b.this.g)).b();
                    w.a(b.this.g, campaignEx);
                }
            }).start();
            bVar.t = campaignUnit.getSessionId();
            if (campaignEx.getOfferType() != 99 && (!TextUtils.isEmpty(campaignEx.getAdZip()) || !TextUtils.isEmpty(campaignEx.getAdHtml()))) {
                if (w.b(campaignEx)) {
                    campaignEx.setRtinsType(w.c(bVar.g, campaignEx.getPackageName()) ? 1 : 2);
                }
                if (campaignEx.getWtick() == 1 || !w.c(bVar.g, campaignEx.getPackageName()) || w.b(campaignEx)) {
                    arrayList.add(campaignEx);
                } else {
                    w.a(bVar.b, campaignEx, com.mbridge.msdk.foundation.same.a.D);
                }
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            s.b(a, "onload load失败 返回的compaign没有可以用的");
            bVar.a("invalid  campaign", str2, i);
            return;
        }
        try {
            bVar.u++;
            if (bVar.i == null || bVar.u > bVar.i.x()) {
                s.b(a, "onload 重置offset为0");
                bVar.u = 0;
            }
            s.b(a, "onload 算出 下次的offset是:" + bVar.u);
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
        bVar.a(campaignEx2, i);
    }

    static /* synthetic */ void b(b bVar, CampaignEx campaignEx, int i) {
        if (bVar.h.isH5Ready()) {
            return;
        }
        bVar.h.setH5Ready(true);
        bVar.b(campaignEx, i);
    }

    static /* synthetic */ void a(b bVar, String str, int i) throws Throwable {
        bVar.a(str, bVar.n, i);
    }
}
