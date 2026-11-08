package com.mbridge.msdk.reward.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebView;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.l;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.download.download.ResDownloadCheckManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.mbbid.out.BidResponsed;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;
import com.mbridge.msdk.reward.adapter.c;
import com.mbridge.msdk.reward.player.MBRewardVideoActivity;
import com.mbridge.msdk.video.bt.module.b.h;
import com.mbridge.msdk.video.js.a.j;
import com.mbridge.msdk.videocommon.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RewardMVVideoAdapter.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d implements com.mbridge.msdk.reward.adapter.a {
    private boolean A;
    private int C;
    private int D;
    private int E;
    private CampaignUnit F;
    private List<CampaignEx> G;
    private List<CampaignEx> M;
    private List<CampaignEx> N;
    private Context i;
    private String j;
    private String k;
    private int l;
    private int m;
    private int n;
    private boolean o;
    private String p;
    private String q;
    private h r;
    private volatile com.mbridge.msdk.reward.adapter.b s;
    private com.mbridge.msdk.videocommon.d.c t;
    private e u;
    private boolean z;
    private boolean v = false;
    private boolean w = false;
    private CopyOnWriteArrayList<CampaignEx> x = new CopyOnWriteArrayList<>();
    private int y = 2;
    private String B = "";
    private boolean H = false;
    public String a = "";
    public String b = "";
    private long I = 0;
    private Handler J = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.reward.adapter.d.1
        /* JADX WARN: Removed duplicated region for block: B:123:0x0408 A[Catch: Exception -> 0x0312, Throwable -> 0x0876, TRY_LEAVE, TryCatch #7 {Throwable -> 0x0876, blocks: (B:3:0x0004, B:4:0x000e, B:6:0x0013, B:7:0x001a, B:9:0x003b, B:11:0x0041, B:15:0x0051, B:17:0x005e, B:19:0x0063, B:21:0x0083, B:24:0x0091, B:26:0x0099, B:28:0x00a1, B:29:0x00b0, B:36:0x00d3, B:38:0x00db, B:40:0x00e8, B:42:0x00f4, B:44:0x0112, B:45:0x011b, B:47:0x0147, B:49:0x0159, B:50:0x0162, B:52:0x016a, B:54:0x0170, B:56:0x0180, B:57:0x0195, B:58:0x01a0, B:60:0x01a8, B:62:0x01be, B:64:0x01ca, B:66:0x01f1, B:68:0x021a, B:70:0x0226, B:71:0x023b, B:73:0x0245, B:75:0x0264, B:76:0x0276, B:78:0x027c, B:80:0x0288, B:82:0x0294, B:84:0x02a3, B:92:0x02e3, B:91:0x02df, B:87:0x02d4, B:93:0x02e8, B:95:0x02fb, B:97:0x0307, B:98:0x0312, B:100:0x0318, B:102:0x0320, B:104:0x032a, B:106:0x0338, B:107:0x033f, B:111:0x0389, B:113:0x0393, B:115:0x03a1, B:116:0x03a8, B:119:0x03f0, B:121:0x03fa, B:123:0x0408, B:124:0x040f, B:128:0x0442, B:130:0x0472, B:131:0x0479, B:134:0x04a9, B:136:0x04af, B:138:0x04bd, B:140:0x04cf, B:141:0x051d, B:144:0x0529, B:146:0x0533, B:148:0x0569, B:150:0x0571, B:152:0x059e, B:154:0x05aa, B:155:0x05b7, B:157:0x05bb, B:159:0x05c3, B:160:0x05cc, B:162:0x05dc, B:164:0x05e2, B:166:0x061d, B:165:0x0602, B:167:0x062a, B:169:0x0634, B:171:0x063c, B:172:0x0645, B:174:0x0655, B:176:0x065b, B:178:0x0696, B:177:0x067b, B:179:0x06a3, B:181:0x06ab, B:182:0x06b4, B:184:0x06df, B:186:0x06e5, B:188:0x071e, B:187:0x0704, B:192:0x073c, B:194:0x0744, B:195:0x074d, B:197:0x0774, B:199:0x077a, B:201:0x07b9, B:200:0x079c, B:202:0x07d9, B:204:0x07e1, B:205:0x07fa, B:206:0x0802, B:208:0x0830, B:209:0x0836, B:210:0x083c, B:212:0x086a, B:213:0x0870, B:33:0x00c1, B:35:0x00ce), top: B:237:0x0004 }] */
        /* JADX WARN: Removed duplicated region for block: B:126:0x043e  */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void handleMessage(android.os.Message r22) {
            /*
                Method dump skipped, instructions count: 2222
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.reward.adapter.d.AnonymousClass1.handleMessage(android.os.Message):void");
        }
    };
    boolean c = false;
    private long K = 0;
    volatile boolean d = false;
    volatile boolean e = false;
    volatile boolean f = false;
    volatile boolean g = false;
    volatile boolean h = false;
    private String L = "";

    public final void a(boolean z) {
        this.z = z;
    }

    public final void b(boolean z) {
        this.A = z;
    }

    public final void a(String str) {
        this.B = str;
    }

    public final void a(int i) {
        this.y = i;
    }

    public final String a() {
        return this.j;
    }

    public final String c(boolean z) {
        List<CampaignEx> listA;
        CampaignEx campaignEx;
        if (z) {
            if (TextUtils.isEmpty(this.b) && (listA = com.mbridge.msdk.videocommon.download.b.getInstance().a(this.j)) != null && listA.size() > 0 && (campaignEx = listA.get(0)) != null) {
                this.b = campaignEx.getRequestId();
            }
            return this.b;
        }
        return this.a;
    }

    /* compiled from: RewardMVVideoAdapter.java */
    private class b implements Runnable {
        private String b;
        private CampaignEx c;
        private String d;
        private String e;
        private com.mbridge.msdk.videocommon.d.c f;
        private int g;
        private d h;

        public b(String str, CampaignEx campaignEx, String str2, String str3, com.mbridge.msdk.videocommon.d.c cVar, int i, d dVar) {
            this.b = str;
            this.c = campaignEx;
            this.d = str2;
            this.e = str3;
            this.f = cVar;
            this.g = i;
            this.h = dVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            j jVar;
            try {
                s.b("RewardMVVideoAdapter", "retry load tpl url = " + this.d);
                a.C0146a c0146a = new a.C0146a();
                WindVaneWebView windVaneWebView = new WindVaneWebView(com.mbridge.msdk.foundation.controller.a.f().j());
                c0146a.a(windVaneWebView);
                String strB = com.mbridge.msdk.video.bt.a.c.a().b();
                c0146a.a(strB);
                if (d.this.F.getAds() != null && d.this.F.getAds().size() > 0) {
                    jVar = new j(null, this.c, d.this.F.getAds());
                } else {
                    jVar = new j(null, this.c);
                }
                j jVar2 = jVar;
                jVar2.a(this.g);
                jVar2.a(this.e);
                jVar2.c(strB);
                jVar2.a(this.f);
                jVar2.e(d.this.H);
                windVaneWebView.setWebViewListener(new a(this.d, this.b, c0146a, this.c, this.h, null, null));
                windVaneWebView.setObject(jVar2);
                windVaneWebView.loadUrl(this.d);
            } catch (Exception e) {
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                s.a("RewardMVVideoAdapter", th.getMessage());
            }
        }
    }

    /* compiled from: RewardMVVideoAdapter.java */
    private class c implements Runnable {
        private WindVaneWebView b;
        private String c;
        private CampaignEx d;
        private List<CampaignEx> e;
        private String f;
        private String g;
        private com.mbridge.msdk.videocommon.d.c h;
        private int i;
        private d j;

        public c(WindVaneWebView windVaneWebView, String str, CampaignEx campaignEx, List<CampaignEx> list, String str2, String str3, com.mbridge.msdk.videocommon.d.c cVar, int i, d dVar) {
            this.b = windVaneWebView;
            this.c = str;
            this.d = campaignEx;
            this.e = list;
            this.f = str2;
            this.g = str3;
            this.h = cVar;
            this.i = i;
            this.j = dVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            j jVar;
            try {
                s.b("RewardMVVideoAdapter", "retry load template url = " + this.f);
                a.C0146a c0146a = new a.C0146a();
                WindVaneWebView windVaneWebView = new WindVaneWebView(com.mbridge.msdk.foundation.controller.a.f().j());
                c0146a.a(windVaneWebView);
                if (this.e != null && this.e.size() > 0) {
                    jVar = new j(null, this.d, this.e);
                } else {
                    jVar = new j(null, this.d);
                }
                jVar.a(this.i);
                jVar.a(this.g);
                jVar.a(this.h);
                jVar.e(d.this.H);
                windVaneWebView.setWebViewListener(new f(this.b, this.f, this.c, c0146a, this.d, this.j, null, null));
                windVaneWebView.setObject(jVar);
                windVaneWebView.loadUrl(this.f);
            } catch (Exception e) {
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                s.a("RewardMVVideoAdapter", th.getMessage());
            }
        }
    }

    /* compiled from: RewardMVVideoAdapter.java */
    private static class a extends com.mbridge.msdk.mbjscommon.c.a {
        private d a;
        private String b;
        private String c;
        private a.C0146a d;
        private CampaignEx e;
        private boolean f;
        private boolean g;
        private b h;
        private Handler i;

        @Override // com.mbridge.msdk.mbjscommon.c.a
        public final void a(WebView webView, String str, String str2, int i, int i2) {
        }

        public a(String str, String str2, a.C0146a c0146a, CampaignEx campaignEx, d dVar, b bVar, Handler handler) {
            this.b = str;
            this.c = str2;
            this.d = c0146a;
            if (dVar != null) {
                this.a = dVar;
            }
            this.e = campaignEx;
            this.h = bVar;
            this.i = handler;
        }

        @Override // com.mbridge.msdk.mbjscommon.c.a, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i) {
            if (this.g) {
                return;
            }
            if (this.h != null && this.i != null) {
                this.i.removeCallbacks(this.h);
            }
            s.a("RVWindVaneWebView", "CampaignTPL templete preload readyState state = " + i);
            ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.b, true);
            String str = this.c + "_" + this.b;
            if (this.d != null) {
                this.d.a(true);
            }
            List<CampaignEx> listA = com.mbridge.msdk.videocommon.a.a.a().a(this.c, 1, this.a.A);
            if (listA != null && listA.size() > 0) {
                Iterator<CampaignEx> it = listA.iterator();
                while (it.hasNext()) {
                    CampaignEx.c rewardTemplateMode = it.next().getRewardTemplateMode();
                    if (rewardTemplateMode != null && !TextUtils.isEmpty(rewardTemplateMode.d()) && TextUtils.equals(rewardTemplateMode.d(), this.b)) {
                        if (TextUtils.isEmpty(this.e.getMof_template_url()) && this.e.getMof_tplid() == -1) {
                            String str2 = this.c + "_" + this.e.getRequestIdNotice();
                        } else {
                            String str3 = this.c + "_" + this.e.getMof_tplid() + "_" + this.e.getRequestIdNotice();
                        }
                        if (this.a != null && this.a.z) {
                            if (this.e.isBidCampaign()) {
                                s.a("RVWindVaneWebView", "Tempalte put templeteCache in bidIVCache ");
                                com.mbridge.msdk.videocommon.a.a(287, this.e.getRequestIdNotice(), this.d);
                            } else {
                                s.a("RVWindVaneWebView", "Tempalte put templeteCache in iVCache ");
                            }
                        } else if (this.e.isBidCampaign()) {
                            s.a("RVWindVaneWebView", "Tempalte put templeteCache in bidRVCache ");
                            com.mbridge.msdk.videocommon.a.a(94, this.e.getRequestIdNotice(), this.d);
                        } else {
                            s.a("RVWindVaneWebView", "Tempalte put templeteCache in rVCache ");
                        }
                    }
                }
            }
            try {
                s.d("RVWindVaneWebView", "CampaignTPL TempalteWindVaneWebviewClient tempalte load SUCCESS ");
                if (this.a != null) {
                    synchronized (this.a) {
                        s.d("RVWindVaneWebView", "CampaignTPL adapter 341");
                        if (this.a != null && this.a.b()) {
                            s.d("RVWindVaneWebView", "CampaignTPL TempalteWindVaneWebviewClient tempalte load  callback success");
                            if (this.a.J != null) {
                                Message messageObtain = Message.obtain();
                                messageObtain.what = 6;
                                s.a("RVWindVaneWebView", "WHAT_ON_RES_LOAD_SUCCESS TPL");
                                messageObtain.obj = this.e;
                                this.a.J.sendMessage(messageObtain);
                                this.a.J.removeMessages(5);
                                this.a = null;
                            }
                        }
                    }
                } else {
                    s.d("RVWindVaneWebView", "CampaignTPL TempalteWindVaneWebviewClient tempalte load SUCCESS  mRewardMVVideoAdapter is null");
                }
            } catch (Throwable th) {
                s.b("RVWindVaneWebView", th.getMessage(), th);
            }
            this.g = true;
        }

        @Override // com.mbridge.msdk.mbjscommon.c.a, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, String str) {
            super.a(webView, str);
            if (this.f) {
                return;
            }
            com.mbridge.msdk.mbjscommon.windvane.h.a().a(webView);
            this.f = true;
        }

        @Override // com.mbridge.msdk.mbjscommon.c.a, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i, String str, String str2) {
            super.a(webView, i, str, str2);
            try {
                s.d("RVWindVaneWebView", "CampaignTPL TempalteWindVaneWebviewClient tempalte load failed");
                if (this.a != null) {
                    synchronized (this.a) {
                        s.d("RVWindVaneWebView", "CampaignTPL TempalteWindVaneWebviewClient tempalte load callback failed");
                        d.a(this.a, str, str2);
                        this.a = null;
                    }
                }
            } catch (Throwable th) {
                s.b("RVWindVaneWebView", th.getMessage(), th);
            }
        }
    }

    /* compiled from: RewardMVVideoAdapter.java */
    private static class f extends com.mbridge.msdk.mbjscommon.c.b {
        private d a;
        private WindVaneWebView b;
        private String c;
        private String d;
        private a.C0146a e;
        private CampaignEx f;
        private boolean g;
        private boolean h;
        private c i;
        private Handler j;

        public f(WindVaneWebView windVaneWebView, String str, String str2, a.C0146a c0146a, CampaignEx campaignEx, d dVar, c cVar, Handler handler) {
            this.b = windVaneWebView;
            this.c = str;
            this.d = str2;
            this.e = c0146a;
            if (dVar != null) {
                this.a = dVar;
            }
            this.f = campaignEx;
            this.i = cVar;
            this.j = handler;
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i) throws JSONException {
            if (this.h) {
                return;
            }
            if (this.i != null && this.j != null) {
                this.j.removeCallbacks(this.i);
            }
            String str = this.d + "_" + this.c;
            if (this.e != null) {
                this.e.a(true);
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(com.heytap.mcssdk.mode.Message.TYPE, 1);
                jSONObject.put("id", str);
                jSONObject.put("unitid", this.d);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            s.a("WindVaneWebView", "Tempalte templete preload readyState state = " + i);
            ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.c, true);
            com.mbridge.msdk.videocommon.a.a.a().a(this.d, 1, this.a.A);
            if (this.a != null && this.a.z) {
                if (this.f.isBidCampaign()) {
                    s.a("WindVaneWebView", "Tempalte put templeteCache in bidIVCache ");
                    com.mbridge.msdk.videocommon.a.a(287, this.f.getRequestIdNotice(), this.e);
                } else {
                    s.a("WindVaneWebView", "Tempalte put templeteCache in iVCache ");
                }
            } else if (this.f.isBidCampaign()) {
                s.a("WindVaneWebView", "Tempalte put templeteCache in bidRVCache ");
                com.mbridge.msdk.videocommon.a.a(94, this.f.getRequestIdNotice(), this.e);
            } else {
                s.a("WindVaneWebView", "Tempalte put templeteCache in rVCache ");
            }
            try {
                s.d("WindVaneWebView", "Tempalte TempalteWindVaneWebviewClient tempalte load SUCCESS ");
                if (this.a != null) {
                    synchronized (this.a) {
                        s.d("WindVaneWebView", "Tempalte adapter 341");
                        if (this.a != null && this.a.b()) {
                            s.d("WindVaneWebView", "Tempalte TempalteWindVaneWebviewClient tempalte load  callback success");
                            if (this.a.J != null) {
                                Message messageObtain = Message.obtain();
                                messageObtain.what = 6;
                                s.a("WindVaneWebView", "WHAT_ON_RES_LOAD_SUCCESS Template");
                                messageObtain.obj = this.f;
                                this.a.J.sendMessage(messageObtain);
                                this.a.J.removeMessages(5);
                                this.a = null;
                            }
                        }
                    }
                } else {
                    s.d("WindVaneWebView", "Tempalte TempalteWindVaneWebviewClient tempalte load SUCCESS  mRewardMVVideoAdapter is null");
                }
            } catch (Throwable th) {
                s.b("WindVaneWebView", th.getMessage(), th);
            }
            this.h = true;
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, String str) {
            super.a(webView, str);
            if (this.g) {
                return;
            }
            com.mbridge.msdk.mbjscommon.windvane.h.a().a(webView);
            this.g = true;
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i, String str, String str2) {
            super.a(webView, i, str, str2);
            try {
                s.d("WindVaneWebView", "Tempalte TempalteWindVaneWebviewClient tempalte load failed");
                if (this.a != null) {
                    synchronized (this.a) {
                        s.d("WindVaneWebView", "Tempalte TempalteWindVaneWebviewClient tempalte load callback failed");
                        d.a(this.a, str, str2);
                        this.a = null;
                    }
                }
            } catch (Throwable th) {
                s.b("WindVaneWebView", th.getMessage(), th);
            }
        }
    }

    public d(Context context, String str, String str2) {
        try {
            this.i = context.getApplicationContext();
            this.j = str2;
            this.k = str;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void a(com.mbridge.msdk.videocommon.d.c cVar) {
        try {
            this.t = cVar;
            if (this.t == null || this.t.J() * 1000 == com.mbridge.msdk.foundation.same.a.u) {
                return;
            }
            com.mbridge.msdk.foundation.same.a.u = this.t.J();
        } catch (Throwable th) {
            s.b("RewardMVVideoAdapter", th.getMessage(), th);
        }
    }

    public final boolean a(List<CampaignEx> list, boolean z, int i) {
        return a(list, z, i, false);
    }

    public final boolean a(List<CampaignEx> list, boolean z, int i, boolean z2) {
        if (list != null && list.size() > 0) {
            CampaignEx campaignEx = list.get(0);
            if (com.mbridge.msdk.videocommon.download.b.getInstance().a(94, this.j, this.A, list.size(), z, i, list, z2)) {
                if (z) {
                    if (campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0 && campaignEx.getRsIgnoreCheckRule().contains(3)) {
                        s.b("RewardMVVideoAdapter", "Is not check big template download status");
                        return true;
                    }
                    if (ResDownloadCheckManager.getInstance().checkPreLoadState(this.j + "_" + campaignEx.getRequestId() + "_" + campaignEx.getMof_template_url())) {
                        s.a("RewardVideoController", "大模板业务，开始检查大模板预加载情况，大模板预加载成功");
                        return true;
                    }
                } else if (campaignEx.getRewardTemplateMode() != null && !TextUtils.isEmpty(campaignEx.getRewardTemplateMode().d())) {
                    if (campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0 && campaignEx.getRsIgnoreCheckRule().contains(1)) {
                        s.b("RewardMVVideoAdapter", "Is not check template download status");
                        return true;
                    }
                    if (ResDownloadCheckManager.getInstance().checkPreLoadState(this.j + "_" + campaignEx.getRequestId() + "_" + campaignEx.getRewardTemplateMode().d())) {
                        s.a("RewardVideoController", "非大模板业务，存在播放模板，播放模板预加载成功");
                        return true;
                    }
                } else {
                    s.a("RewardVideoController", "非大模板 没有 template");
                    return true;
                }
            }
        } else {
            s.a("RewardVideoController", "数据为空");
        }
        return false;
    }

    public final boolean b() {
        List<CampaignEx> listA = com.mbridge.msdk.videocommon.a.a.a().a(this.j, 1, this.A, this.B);
        if (listA != null && listA.size() > 0) {
            CampaignEx campaignEx = listA.get(0);
            int iB = com.mbridge.msdk.videocommon.a.a.a().b(this.j, 1, this.A, this.B);
            if (TextUtils.isEmpty(campaignEx.getMof_template_url()) || campaignEx.getNscpt() != 1 || listA.size() >= iB) {
                return a(listA, !TextUtils.isEmpty(campaignEx.getMof_template_url()), campaignEx.getNscpt());
            }
            return false;
        }
        s.a("RewardVideoController", "数据库可用的缓存数据条数：0");
        return false;
    }

    public final boolean c() {
        s.a("test_isReay_db", "isRisSpareOfferReady check");
        List<CampaignEx> listC = com.mbridge.msdk.videocommon.a.a.a().c(this.j, 1, this.A, this.B);
        if (listC != null && listC.size() > 0) {
            CampaignEx campaignEx = listC.get(0);
            int iB = com.mbridge.msdk.videocommon.a.a.a().b(this.j, 1, this.A, this.B);
            if (TextUtils.isEmpty(campaignEx.getMof_template_url()) || campaignEx.getNscpt() != 1 || listC.size() >= iB) {
                return a(listC, !TextUtils.isEmpty(campaignEx.getMof_template_url()), campaignEx.getNscpt());
            }
            return false;
        }
        s.a("test_isReay_db", "数据库可用的缓存数据条数：0");
        return false;
    }

    public final void a(h hVar, String str, String str2, int i, String str3) throws Throwable {
        try {
            this.r = hVar;
            s.b("RewardMVVideoAdapter", "show 进来");
            if (this.i != null && !z.a(this.j)) {
                s.b("RewardMVVideoAdapter", "show isReady true 打开播放器页面");
                Intent intent = new Intent(this.i, (Class<?>) MBRewardVideoActivity.class);
                intent.addFlags(268435456);
                intent.putExtra(MBRewardVideoActivity.INTENT_UNITID, this.j);
                intent.putExtra(MBridgeConstans.PLACEMENT_ID, this.k);
                intent.putExtra(MBRewardVideoActivity.INTENT_REWARD, str);
                intent.putExtra(MBRewardVideoActivity.INTENT_MUTE, i);
                intent.putExtra(MBRewardVideoActivity.INTENT_ISIV, this.z);
                intent.putExtra(MBRewardVideoActivity.INTENT_ISBID, this.A);
                intent.putExtra(MBRewardVideoActivity.INTENT_EXTRADATA, str3);
                List<CampaignEx> listA = com.mbridge.msdk.videocommon.download.b.getInstance().a(this.j);
                StringBuilder sb = new StringBuilder();
                sb.append("当前展示的Offer requestId");
                boolean z = false;
                sb.append(listA.get(0).getRequestId());
                s.d("HBOPTIMIZE", sb.toString());
                if (listA != null && listA.size() > 0) {
                    s.a("RewardMVVideoAdapter", "可以 show 的数据： " + listA.size());
                    CampaignEx campaignEx = listA.get(0);
                    if (campaignEx != null) {
                        this.b = campaignEx.getRequestId();
                    }
                    if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getMof_template_url())) {
                        z = true;
                    }
                    d();
                } else {
                    s.a("RewardMVVideoAdapter", "可以 show 的数据： 0");
                    if (this.r != null) {
                        this.r.a("load failed");
                        return;
                    }
                }
                intent.putExtra(MBRewardVideoActivity.INTENT_ISBIG_OFFER, z);
                if (this.z) {
                    intent.putExtra(MBRewardVideoActivity.INTENT_IVREWARD_MODETYPE, this.C);
                    intent.putExtra(MBRewardVideoActivity.INTENT_IVREWARD_VALUETYPE, this.D);
                    intent.putExtra(MBRewardVideoActivity.INTENT_IVREWARD_VALUE, this.E);
                }
                if (!TextUtils.isEmpty(str2)) {
                    intent.putExtra(MBRewardVideoActivity.INTENT_USERID, str2);
                }
                RewardUnitCacheManager.getInstance().add(this.k, this.j, this.t);
                this.i.startActivity(intent);
                return;
            }
            if (this.r != null) {
                this.r.a("context or unitid is null");
            }
            s.b("RewardMVVideoAdapter", "show context munitid null");
        } catch (Exception e2) {
            e2.printStackTrace();
            if (this.r != null) {
                this.r.a("show failed, exception is " + e2.getMessage());
            }
        }
    }

    private void d() throws Throwable {
        List<com.mbridge.msdk.foundation.entity.d> listB = com.mbridge.msdk.foundation.db.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).b(this.j, this.b);
        if (listB != null) {
            int size = listB.size();
            String strC = "";
            String strB = "";
            String strB2 = com.mbridge.msdk.foundation.same.a.d.b(this.j);
            if (size == 0) {
                return;
            }
            try {
                if (size == 1) {
                    if (listB.get(0) != null) {
                        strC = listB.get(0).c();
                        strB = listB.get(0).b();
                    }
                } else if (!TextUtils.isEmpty(strB2)) {
                    Iterator<com.mbridge.msdk.foundation.entity.d> it = listB.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        com.mbridge.msdk.foundation.entity.d next = it.next();
                        if (next != null && strB2.equals(next.a())) {
                            strC = next.c();
                            strB = next.b();
                            break;
                        }
                    }
                }
                if (TextUtils.isEmpty(strC) || !strC.equals(this.b)) {
                    return;
                }
                com.mbridge.msdk.foundation.db.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(strB2, this.j);
                com.mbridge.msdk.foundation.same.a.d.a(this.j, this.b, strB);
                com.mbridge.msdk.foundation.same.a.d.a(this.j);
            } catch (Exception e2) {
                s.a("RewardMVVideoAdapter", e2.getMessage());
            }
        }
    }

    private void a(String str, boolean z) {
        new Thread(new RunnableC0142d(str)).start();
        if (this.J != null) {
            this.u = new e(str);
            this.J.postDelayed(this.u, 90000L);
        } else {
            s.b("RewardMVVideoAdapter", "handler 为空 直接load");
            b(str, z);
        }
    }

    public final void a(int i, int i2, boolean z) {
        a(i, i2, z, "", this.H);
    }

    public final void a(int i, int i2, boolean z, String str, boolean z2) {
        this.m = i;
        this.n = i2;
        this.o = z;
        this.H = z2;
        if (this.G != null) {
            this.G.clear();
        }
        if (this.M != null) {
            this.M.clear();
        }
        this.d = false;
        this.e = false;
        this.f = false;
        this.h = false;
        this.g = false;
        if (this.i == null) {
            c("Context is null");
            return;
        }
        if (z.a(this.j)) {
            c("UnitId is null");
            return;
        }
        if (this.t == null) {
            c("RewardUnitSetting is null");
            return;
        }
        k();
        l();
        s.b("RewardMVVideoAdapter", "load 开始清除过期数据");
        f();
        a(str, z2);
    }

    private void b(String str, boolean z) {
        try {
            if (this.i == null) {
                c("Context is null");
                return;
            }
            if (z.a(this.j)) {
                c("UnitId is null");
                return;
            }
            if (this.t == null) {
                c("RewardUnitSetting is null");
                return;
            }
            if (TextUtils.isEmpty(str)) {
                if (System.currentTimeMillis() - this.I < this.t.b() * 1000) {
                    c("EXCEPTION_RETURN_EMPTY");
                    return;
                }
            }
            s.b("RewardMVVideoAdapter", "load 开始准备请求参数");
            com.mbridge.msdk.foundation.same.net.h.d dVarE = e();
            if (dVarE == null) {
                s.b("RewardMVVideoAdapter", "load 请求参数为空 load失败");
                c("Load param is null");
                return;
            }
            String strA = com.mbridge.msdk.foundation.same.net.g.d.c().a(str);
            if (dVarE != null && !TextUtils.isEmpty(str)) {
                dVarE.a(BidResponsed.KEY_TOKEN, str);
            }
            if (this.z) {
                if (this.C == com.mbridge.msdk.foundation.same.a.w || this.C == com.mbridge.msdk.foundation.same.a.v) {
                    dVarE.a("ivrwd", "1");
                } else {
                    dVarE.a("ivrwd", PushConstants.PUSH_TYPE_NOTIFY);
                }
            }
            com.mbridge.msdk.videocommon.d.c cVarA = com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.j, this.z);
            if (cVarA != null && !TextUtils.isEmpty(cVarA.a())) {
                dVarE.a("u_stid", cVarA.a());
            }
            com.mbridge.msdk.videocommon.d.a aVarB = com.mbridge.msdk.videocommon.d.b.a().b();
            if (aVarB != null && !TextUtils.isEmpty(aVarB.a())) {
                dVarE.a("r_stid", aVarB.a());
            }
            dVarE.a("rw_plus", z ? "1" : PushConstants.PUSH_TYPE_NOTIFY);
            String strE = w.e(this.j);
            if (!TextUtils.isEmpty(strE)) {
                dVarE.a("j", strE);
            }
            this.K = System.currentTimeMillis();
            try {
                com.mbridge.msdk.reward.b.a.a(null, this.i, "start load offer from server", this.j, this.A, "", "", System.currentTimeMillis() - this.K);
            } catch (Exception unused) {
            }
            com.mbridge.msdk.reward.c.b bVar = new com.mbridge.msdk.reward.c.b(this.i);
            com.mbridge.msdk.reward.c.a aVar = new com.mbridge.msdk.reward.c.a() { // from class: com.mbridge.msdk.reward.adapter.d.2
                @Override // com.mbridge.msdk.reward.c.a
                public final void a(CampaignUnit campaignUnit) {
                    try {
                        s.b("RewardMVVideoAdapter", "onLoadCompaginSuccess 数据刚请求回来");
                        s.b("HBOPTIMIZE", "V3 数据刚请求回来 requestId " + campaignUnit.getRequestId());
                        if (campaignUnit != null && campaignUnit.getAds() != null && campaignUnit.getAds().size() > 0) {
                            try {
                                CampaignEx campaignEx = campaignUnit.getAds().get(0);
                                Context context = d.this.i;
                                StringBuilder sb = new StringBuilder();
                                sb.append("load offer success tpl ");
                                sb.append(!TextUtils.isEmpty(campaignUnit.getAds().get(0).getMof_template_url()));
                                com.mbridge.msdk.reward.b.a.a(campaignEx, context, sb.toString(), d.this.j, d.this.A, "", "", System.currentTimeMillis() - d.this.K);
                            } catch (Exception unused2) {
                            }
                        }
                        d.a(d.this, campaignUnit);
                        d.this.a = campaignUnit.getRequestId();
                    } catch (Exception e2) {
                        if (MBridgeConstans.DEBUG) {
                            e2.printStackTrace();
                        }
                        s.b("RewardMVVideoAdapter", "onLoadCompaginSuccess 数据刚请求失败");
                        if (d.this.G != null) {
                            d.this.G.clear();
                        }
                        if (d.this.M != null) {
                            d.this.M.clear();
                        }
                        d.this.d = false;
                        d.this.e = false;
                        d.this.f = false;
                        d.this.h = false;
                        d.this.g = false;
                        try {
                            com.mbridge.msdk.reward.b.a.a(null, d.this.i, "load offer failed: " + e2.getMessage(), d.this.j, d.this.A, "", "", System.currentTimeMillis() - d.this.K);
                        } catch (Exception unused3) {
                        }
                        d.this.c("exception after load success");
                        d.this.h();
                    }
                }

                @Override // com.mbridge.msdk.reward.c.a
                public final void a(int i, String str2) {
                    s.d("RewardMVVideoAdapter", str2);
                    s.b("HBOPTIMIZE", "V3 数据请求失败 errorCode " + i + "errorMsg " + str2);
                    try {
                        com.mbridge.msdk.reward.b.a.a(null, d.this.i, "load offer failed: " + str2, d.this.j, d.this.A, "", "", System.currentTimeMillis() - d.this.K);
                    } catch (Exception unused2) {
                    }
                    s.b("RewardMVVideoAdapter", "onLoadCompaginFailed load failed errorCode:" + i + " msg:" + str2);
                    if (i == -1) {
                        d.this.I = System.currentTimeMillis();
                    }
                    if (d.this.G != null) {
                        d.this.G.clear();
                    }
                    if (d.this.M != null) {
                        d.this.M.clear();
                    }
                    d.this.d = false;
                    d.this.e = false;
                    d.this.f = false;
                    d.this.h = false;
                    d.this.g = false;
                    d.this.c(str2);
                    d.this.h();
                }
            };
            aVar.a(str);
            aVar.d = this.j;
            aVar.e = this.k;
            aVar.f = this.z ? 287 : 94;
            com.mbridge.msdk.foundation.same.net.b bVar2 = new com.mbridge.msdk.foundation.same.net.b(this.n * 1000, 1);
            if (com.mbridge.msdk.foundation.same.net.g.d.c().b() && TextUtils.isEmpty(str)) {
                bVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().a(str), dVarE, aVar, bVar2);
            } else {
                bVar.a(1, strA, dVarE, bVar2, aVar);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            c("Load exception");
            h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (this.J != null) {
            if (TextUtils.isEmpty(str)) {
                this.J.sendEmptyMessage(4);
                return;
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 4;
            messageObtain.obj = str;
            if (str.contains("exception")) {
                this.J.sendMessageAtFrontOfQueue(messageObtain);
            } else {
                this.J.sendMessage(messageObtain);
            }
        }
    }

    private com.mbridge.msdk.foundation.same.net.h.d e() throws JSONException, NoSuchAlgorithmException {
        int iT;
        String strK = com.mbridge.msdk.foundation.controller.a.f().k();
        String md5 = SameMD5.getMD5(com.mbridge.msdk.foundation.controller.a.f().k() + com.mbridge.msdk.foundation.controller.a.f().l());
        int i = this.o ? 2 : 3;
        int iS = 0;
        if (this.t != null) {
            iS = this.t.s();
            iT = this.t.t();
        } else {
            iT = 0;
        }
        String str = this.q;
        String str2 = this.p;
        String strA = com.mbridge.msdk.foundation.same.a.d.a(this.j, "reward");
        String strJ = j();
        this.l = g();
        String strI = i();
        int i2 = this.m;
        int i3 = this.z ? 287 : 94;
        com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
        int i4 = iT;
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "app_id", strK);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, MBridgeConstans.PROPERTIES_UNIT_ID, this.j);
        if (!TextUtils.isEmpty(this.k)) {
            com.mbridge.msdk.foundation.same.net.g.b.a(dVar, MBridgeConstans.PLACEMENT_ID, this.k);
        }
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "sign", md5);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "req_type", i + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ad_num", iS + "");
        StringBuilder sb = new StringBuilder();
        sb.append(this.z ? 1 : i4);
        sb.append("");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "tnum", sb.toString());
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "only_impression", "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ping_mode", "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ttc_ids", str2);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.b, strA);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.c, str);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "install_ids", strJ);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, CampaignEx.JSON_KEY_AD_SOURCE_ID, i2 + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.a, strI);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ad_type", i3 + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "offset", this.l + "");
        return dVar;
    }

    private void f() {
        if (com.mbridge.msdk.videocommon.a.a.a() != null) {
            try {
                com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
                if (aVarB == null) {
                    aVarB = com.mbridge.msdk.b.b.a().b();
                }
                com.mbridge.msdk.videocommon.a.a.a().a(aVarB.B() * 1000, this.j);
            } catch (Exception e2) {
                if (MBridgeConstans.DEBUG) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // com.mbridge.msdk.reward.adapter.a
    public final void a(com.mbridge.msdk.reward.adapter.b bVar) {
        if (bVar != null) {
            s.b("RewardMVVideoAdapter", "======set listener is not null");
        } else {
            s.b("RewardMVVideoAdapter", "======set listener is  null");
        }
        this.s = bVar;
    }

    /* compiled from: RewardMVVideoAdapter.java */
    /* renamed from: com.mbridge.msdk.reward.adapter.d$3, reason: invalid class name */
    final class AnonymousClass3 implements c.InterfaceC0138c {
        final /* synthetic */ boolean a;
        final /* synthetic */ CampaignEx b;
        final /* synthetic */ int c;

        AnonymousClass3(boolean z, CampaignEx campaignEx, int i) {
            this.a = z;
            this.b = campaignEx;
            this.c = i;
        }

        @Override // com.mbridge.msdk.reward.adapter.c.InterfaceC0138c
        public final void a(final String str, final String str2, final String str3, final List<CampaignEx> list) throws Throwable {
            s.a("RewardMVVideoAdapter", "Campaign 下载成功： " + list.size());
            d.this.d = true;
            if (list != null && list.size() > 0) {
                try {
                    com.mbridge.msdk.reward.b.a.a(list.get(0), d.this.i, "resource download success is tpl :" + this.a, d.this.j, d.this.A, str3, this.b.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                } catch (Exception unused) {
                }
            }
            if (!this.a) {
                s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板");
                for (final CampaignEx campaignEx : list) {
                    if (campaignEx != null && campaignEx.getRewardTemplateMode() != null && !TextUtils.isEmpty(campaignEx.getRewardTemplateMode().d()) && d.this.J != null) {
                        s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，开始预加载播放模板");
                        d.this.J.post(new Runnable() { // from class: com.mbridge.msdk.reward.adapter.d.3.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                c.m.a.a(d.this.H, d.this.J, d.this.z, d.this.A, null, campaignEx.getRewardTemplateMode().d(), d.this.y, AnonymousClass3.this.b, d.this.G, H5DownLoadManager.getInstance().getH5ResAddress(campaignEx.getRewardTemplateMode().d()), str, str2, str3, campaignEx.getRequestIdNotice(), d.this.t, new c.j() { // from class: com.mbridge.msdk.reward.adapter.d.3.1.1
                                    @Override // com.mbridge.msdk.reward.adapter.c.j
                                    public final void a(String str4, String str5, String str6, String str7, String str8, a.C0146a c0146a) throws Throwable {
                                        s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，播放模板预加载成功");
                                        s.a("HBOPTIMIZE", "模板加载成功 requestId " + str7);
                                        try {
                                            com.mbridge.msdk.reward.b.a.a((CampaignEx) list.get(0), d.this.i, "preload template success is tpl :" + AnonymousClass3.this.a, d.this.j, d.this.A, str3, AnonymousClass3.this.b.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                                        } catch (Exception unused2) {
                                        }
                                        if (d.this.a(d.this.G, AnonymousClass3.this.a, AnonymousClass3.this.c)) {
                                            d.a(d.this, d.this.j, list, d.this.N, d.this.A);
                                            s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，播放模板预加载成功,isReay true");
                                            if (d.this.J != null) {
                                                d.this.J.removeMessages(5);
                                            }
                                            if (d.this.s == null || d.this.g) {
                                                return;
                                            }
                                            d.this.g = true;
                                            if (!TextUtils.isEmpty(d.this.j)) {
                                                com.mbridge.msdk.reward.b.a.a(d.this.i, (List<CampaignEx>) d.this.G, d.this.j);
                                            }
                                            s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，播放模板预加载成功,isReay true onVideoLoadSuccess");
                                            d.this.s.a();
                                            return;
                                        }
                                        s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，播放模板预加载成功,isReay false");
                                        d.a(d.this, str6, d.this.M, d.this.A);
                                        if (d.this.J != null) {
                                            d.this.J.removeMessages(5);
                                        }
                                        if (d.this.s == null || d.this.h) {
                                            return;
                                        }
                                        d.this.h = true;
                                        if (!TextUtils.isEmpty(d.this.j)) {
                                            com.mbridge.msdk.reward.b.a.a(d.this.i, "temp preload success but isReady false", d.this.j, d.this.A, str7, campaignEx.getRequestIdNotice());
                                        }
                                        d.this.s.a("errorCode: 3502 errorMessage: temp preload success but isReady false");
                                        s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，播放模板预加载成功,isReay false onVideoLoadFail");
                                    }

                                    @Override // com.mbridge.msdk.reward.adapter.c.j
                                    public final void a(String str4, String str5, String str6, String str7, String str8, a.C0146a c0146a, String str9) {
                                        s.a("RewardMVVideoAdapter", "Campaign 下载失败： 非大模板，播放模板预加载失败");
                                        s.d("HBOPTIMIZE", "模板加载失败 requestId " + str7);
                                        try {
                                            com.mbridge.msdk.reward.b.a.a((CampaignEx) list.get(0), d.this.i, "preload template failed is tpl :" + AnonymousClass3.this.a + " " + str9, d.this.j, d.this.A, str3, AnonymousClass3.this.b.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                                        } catch (Exception unused2) {
                                        }
                                        if (AnonymousClass3.this.b.getRsIgnoreCheckRule() == null || AnonymousClass3.this.b.getRsIgnoreCheckRule().size() <= 0 || !AnonymousClass3.this.b.getRsIgnoreCheckRule().contains(1)) {
                                            d.a(d.this, str6, d.this.M, d.this.A);
                                            if (d.this.J != null) {
                                                d.this.J.removeMessages(5);
                                            }
                                            if (d.this.s == null || d.this.h) {
                                                return;
                                            }
                                            d.this.h = true;
                                            if (!TextUtils.isEmpty(d.this.j)) {
                                                com.mbridge.msdk.reward.b.a.a(d.this.i, "temp preload failed: " + str9, d.this.j, d.this.A, str7, campaignEx.getRequestIdNotice());
                                            }
                                            d.this.s.a("errorCode: 3301 errorMessage: temp preload failed: " + str9);
                                            s.a("RewardMVVideoAdapter", "Campaign 下载失败： 非大模板，播放模板预加载失败 onVideoLoadFail");
                                            return;
                                        }
                                        s.b("RewardMVVideoAdapter", "template preload fail but hit ignoreCheckRule");
                                    }
                                });
                            }
                        });
                    } else {
                        s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，不存在播放模板");
                        if (d.this.a(d.this.G, this.a, this.c)) {
                            s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，不存在播放模板,isReay true");
                            s.a("HBOPTIMIZE", "模板加载成功 requestId " + list.get(0).getRequestId());
                            d.a(d.this, d.this.j, list, d.this.N, d.this.A);
                            if (d.this.J != null) {
                                d.this.J.removeMessages(5);
                            }
                            if (d.this.s != null && !d.this.g) {
                                d.this.g = true;
                                if (!TextUtils.isEmpty(d.this.j)) {
                                    com.mbridge.msdk.reward.b.a.a(d.this.i, (List<CampaignEx>) d.this.G, d.this.j);
                                }
                                d.this.s.a();
                                s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，播放模板预加载成功,isReay true onVideoLoadSuccess");
                            }
                        } else {
                            s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，不存在播放模板,isReay false");
                            s.d("HBOPTIMIZE", "模板加载失败 requestId " + list.get(0).getRequestId());
                            d.a(d.this, str2, d.this.M, d.this.A);
                            if (d.this.J != null) {
                                d.this.J.removeMessages(5);
                            }
                            if (d.this.s != null && !d.this.h) {
                                d.this.h = true;
                                if (!TextUtils.isEmpty(d.this.j)) {
                                    String requestIdNotice = "";
                                    if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getRequestIdNotice())) {
                                        requestIdNotice = campaignEx.getRequestIdNotice();
                                    }
                                    com.mbridge.msdk.reward.b.a.a(d.this.i, "have no temp but isReady false", d.this.j, d.this.A, str3, requestIdNotice);
                                }
                                d.this.s.a("errorCode: 3503 errorMessage: have no temp but isReady false");
                                s.a("RewardMVVideoAdapter", "Campaign 下载成功： 非大模板，不存在播放模板,isReay false onVideoLoadFail");
                            }
                        }
                    }
                }
                return;
            }
            s.a("RewardMVVideoAdapter", "Campaign 下载成功： 大模板");
            if (d.this.e && !d.this.f && d.this.J != null) {
                s.a("RewardMVVideoAdapter", "Campaign 下载成功： 大模板，大模板下载成功，开始预加载大模板");
                d.this.f = true;
                s.a("test_pre_load_tpl", "Campaign 下载成功，大模板下载成功，开始预加载大模板");
                d.this.J.post(new Runnable() { // from class: com.mbridge.msdk.reward.adapter.d.3.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        c.m.a.a(d.this.H, d.this.J, d.this.z, d.this.A, str3, AnonymousClass3.this.b.getRequestIdNotice(), d.this.k, str2, AnonymousClass3.this.b.getMof_template_url(), d.this.y, AnonymousClass3.this.b, d.this.G, H5DownLoadManager.getInstance().getH5ResAddress(AnonymousClass3.this.b.getMof_template_url()), str2, d.this.t, new c.j() { // from class: com.mbridge.msdk.reward.adapter.d.3.2.1
                            @Override // com.mbridge.msdk.reward.adapter.c.j
                            public final void a(String str4, String str5, String str6, String str7, String str8, a.C0146a c0146a) throws Throwable {
                                try {
                                    s.a("HBOPTIMIZE", "模板加载成功 requestId " + str7);
                                    com.mbridge.msdk.reward.b.a.a((CampaignEx) list.get(0), d.this.i, "preload template success is tpl :" + AnonymousClass3.this.a, d.this.j, d.this.A, str3, AnonymousClass3.this.b.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                                } catch (Exception unused2) {
                                }
                                s.a("RewardMVVideoAdapter", "Campaign 下载成功： 大模板预加载成功");
                                if (d.this.a(d.this.G, AnonymousClass3.this.a, AnonymousClass3.this.c)) {
                                    s.a("RewardMVVideoAdapter", "Campaign 下载成功： 大模板预加载成功,isReady true");
                                    d.a(d.this, d.this.j, list, d.this.N, d.this.A);
                                    if (d.this.J != null) {
                                        d.this.J.removeMessages(5);
                                    }
                                    if (d.this.s == null || d.this.g) {
                                        return;
                                    }
                                    d.this.g = true;
                                    if (!TextUtils.isEmpty(d.this.j)) {
                                        com.mbridge.msdk.reward.b.a.a(d.this.i, (List<CampaignEx>) d.this.G, d.this.j);
                                    }
                                    d.this.s.a();
                                    s.a("RewardMVVideoAdapter", "Campaign 下载成功： 大模板预加载成功,isReady true onVideoLoadSuccess");
                                    return;
                                }
                                s.a("RewardMVVideoAdapter", "Campaign 下载成功： 大模板预加载成功,isReady false");
                                d.a(d.this, str6, d.this.M, d.this.A);
                                if (d.this.J != null) {
                                    d.this.J.removeMessages(5);
                                }
                                if (d.this.s == null || d.this.h) {
                                    return;
                                }
                                d.this.h = true;
                                if (!TextUtils.isEmpty(d.this.j)) {
                                    com.mbridge.msdk.reward.b.a.a(d.this.i, "tpl temp preload success but isReady false", d.this.j, d.this.A, str7, AnonymousClass3.this.b.getRequestIdNotice());
                                }
                                d.this.s.a("errorCode: 3505 errorMessage: tpl temp preload success but isReady false");
                                s.a("RewardMVVideoAdapter", "Campaign 下载成功： 大模板预加载成功,isReady false onVideoLoadFail");
                            }

                            @Override // com.mbridge.msdk.reward.adapter.c.j
                            public final void a(String str4, String str5, String str6, String str7, String str8, a.C0146a c0146a, String str9) {
                                s.a("RewardMVVideoAdapter", "Campaign 下载成功： 大模板预加载失败");
                                s.d("HBOPTIMIZE", "模板加载失败 requestId " + str7);
                                try {
                                    com.mbridge.msdk.reward.b.a.a((CampaignEx) list.get(0), d.this.i, "preload template failed is tpl :" + AnonymousClass3.this.a + " " + str9, d.this.j, d.this.A, str3, AnonymousClass3.this.b.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                                } catch (Exception unused2) {
                                }
                                if (AnonymousClass3.this.b.getRsIgnoreCheckRule() == null || AnonymousClass3.this.b.getRsIgnoreCheckRule().size() <= 0 || !AnonymousClass3.this.b.getRsIgnoreCheckRule().contains(3)) {
                                    d.a(d.this, str6, d.this.M, d.this.A);
                                    if (d.this.J != null) {
                                        d.this.J.removeMessages(5);
                                    }
                                    if (d.this.s == null || d.this.h) {
                                        return;
                                    }
                                    d.this.h = true;
                                    com.mbridge.msdk.reward.b.a.a(d.this.i, "tpl temp preload failed: " + str9, d.this.j, d.this.A, str7, AnonymousClass3.this.b.getRequestIdNotice());
                                    d.this.s.a("errorCode: 3303 errorMessage: tpl temp preload failed: " + str9);
                                    s.a("RewardMVVideoAdapter", "Campaign 下载成功： 大模板预加载失败 onVideoLoadFail");
                                    return;
                                }
                                s.b("RewardMVVideoAdapter", "template download fail but hit ignoreCheckRule");
                            }
                        }, false);
                    }
                });
                return;
            }
            s.a("RewardMVVideoAdapter", "Campaign 下载成功： 大模板，大模板下载不成功： isCampaignTPLDownloadSuccess： " + d.this.e + "  isCampaignTPLProLoad： " + d.this.f);
        }

        @Override // com.mbridge.msdk.reward.adapter.c.InterfaceC0138c
        public final void a(String str, final String str2, final String str3, List<CampaignEx> list, final String str4) {
            s.a("RewardMVVideoAdapter", "Campaign 下载失败： " + list.size());
            s.d("HBOPTIMIZE", "模板加载失败 requestId " + str3);
            d.this.d = false;
            if (list != null && list.size() > 0) {
                try {
                    com.mbridge.msdk.reward.b.a.a(list.get(0), d.this.i, "resource download failed: " + str4 + " is tpl: " + this.a, d.this.j, d.this.A, str3, this.b.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                } catch (Exception unused) {
                }
            }
            if (d.this.s == null || d.this.J == null) {
                return;
            }
            d.this.J.post(new Runnable() { // from class: com.mbridge.msdk.reward.adapter.d.3.3
                @Override // java.lang.Runnable
                public final void run() {
                    d.a(d.this, str2, d.this.M, d.this.A);
                    if (d.this.J != null) {
                        d.this.J.removeMessages(5);
                    }
                    if (d.this.h || d.this.s == null) {
                        return;
                    }
                    d.this.h = true;
                    if (!TextUtils.isEmpty(d.this.j)) {
                        com.mbridge.msdk.reward.b.a.a(d.this.i, "" + str4, d.this.j, d.this.A, str3, AnonymousClass3.this.b.getRequestIdNotice());
                    }
                    d.this.s.a("errorCode: 3201 errorMessage: campaign resource download failed");
                    s.a("RewardMVVideoAdapter", "Campaign 下载失败：onVideoLoadFail");
                }
            });
        }
    }

    /* compiled from: RewardMVVideoAdapter.java */
    /* renamed from: com.mbridge.msdk.reward.adapter.d$5, reason: invalid class name */
    final class AnonymousClass5 implements c.i {
        final /* synthetic */ CampaignEx a;
        final /* synthetic */ boolean b;
        final /* synthetic */ int c;

        AnonymousClass5(CampaignEx campaignEx, boolean z, int i) {
            this.a = campaignEx;
            this.b = z;
            this.c = i;
        }

        @Override // com.mbridge.msdk.reward.adapter.c.i
        public final void a(final String str, final String str2, final String str3, String str4) {
            s.a("RewardMVVideoAdapter", "大模板业务，大模板下载成功");
            try {
                com.mbridge.msdk.reward.b.a.a(this.a, d.this.i, "resource download success is tpl :" + this.b, d.this.j, d.this.A, str3, this.a.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
            } catch (Exception unused) {
            }
            d.this.e = true;
            if (d.this.d && !d.this.f && d.this.J != null) {
                s.a("RewardMVVideoAdapter", "大模板业务，大模板下载成功，Campaign 下载成功，开始预加载大模板");
                s.a("test_pre_load_tpl", "大模板下载成功，Campaign 下载成功，开始预加载大模板");
                d.this.f = true;
                d.this.J.post(new Runnable() { // from class: com.mbridge.msdk.reward.adapter.d.5.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        c.m.a.a(d.this.H, d.this.J, d.this.z, d.this.A, str3, AnonymousClass5.this.a.getRequestIdNotice(), str, str2, AnonymousClass5.this.a.getMof_template_url(), d.this.y, AnonymousClass5.this.a, d.this.G, H5DownLoadManager.getInstance().getH5ResAddress(AnonymousClass5.this.a.getMof_template_url()), str2, d.this.t, new c.j() { // from class: com.mbridge.msdk.reward.adapter.d.5.1.1
                            @Override // com.mbridge.msdk.reward.adapter.c.j
                            public final void a(String str5, String str6, String str7, String str8, String str9, a.C0146a c0146a) throws Throwable {
                                s.a("HBOPTIMIZE", "模板加载成功 requestId " + str8);
                                try {
                                    com.mbridge.msdk.reward.b.a.a(AnonymousClass5.this.a, d.this.i, "preload template success is tpl :" + AnonymousClass5.this.b, d.this.j, d.this.A, str3, AnonymousClass5.this.a.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                                } catch (Exception unused2) {
                                }
                                if (d.this.a(d.this.G, AnonymousClass5.this.b, AnonymousClass5.this.c)) {
                                    s.a("RewardMVVideoAdapter", "大模板业务，大模板预加载成功，isReady true");
                                    d.a(d.this, d.this.j, d.this.M, d.this.N, d.this.A);
                                    if (d.this.J != null) {
                                        d.this.J.removeMessages(5);
                                    }
                                    if (d.this.s == null || d.this.g) {
                                        return;
                                    }
                                    d.this.g = true;
                                    if (!TextUtils.isEmpty(d.this.j)) {
                                        com.mbridge.msdk.reward.b.a.a(d.this.i, (List<CampaignEx>) d.this.G, d.this.j);
                                    }
                                    d.this.s.a();
                                    s.a("RewardMVVideoAdapter", "大模板业务，大模板预加载成功，isReady true onVideoLoadSuccess");
                                    return;
                                }
                                s.a("RewardMVVideoAdapter", "大模板业务，大模板预加载成功，isReady false");
                                d.a(d.this, str7, d.this.M, d.this.A);
                                if (d.this.J != null) {
                                    d.this.J.removeMessages(5);
                                }
                                if (d.this.s == null || d.this.h) {
                                    return;
                                }
                                d.this.h = true;
                                com.mbridge.msdk.reward.b.a.a(d.this.i, "tpl temp preload success but isReady false", d.this.j, d.this.A, str8, AnonymousClass5.this.a.getRequestIdNotice());
                                d.this.s.a("errorCode: 3505 errorMessage: tpl temp preload success but isReady false");
                                s.a("RewardMVVideoAdapter", "大模板业务，大模板预加载成功 isReady false  onVideoLoadFail");
                            }

                            @Override // com.mbridge.msdk.reward.adapter.c.j
                            public final void a(String str5, String str6, String str7, String str8, String str9, a.C0146a c0146a, String str10) {
                                s.a("RewardMVVideoAdapter", "大模板业务，大模板预加载失败");
                                s.d("HBOPTIMIZE", "模板加载失败 requestId " + str8);
                                d.a(d.this, str7, d.this.M, d.this.A);
                                try {
                                    com.mbridge.msdk.reward.b.a.a(AnonymousClass5.this.a, d.this.i, "preload template failed is tpl :" + AnonymousClass5.this.b + " " + str10, d.this.j, d.this.A, str3, AnonymousClass5.this.a.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                                } catch (Exception unused2) {
                                }
                                if (d.this.J != null) {
                                    d.this.J.removeMessages(5);
                                }
                                if (d.this.s == null || d.this.h) {
                                    return;
                                }
                                d.this.h = true;
                                com.mbridge.msdk.reward.b.a.a(d.this.i, "tpl temp preload failed: " + str10, d.this.j, d.this.A, str8, AnonymousClass5.this.a.getRequestIdNotice());
                                d.this.s.a("errorCode: 3303 errorMessage: tpl temp preload failed: " + str10);
                                s.a("RewardMVVideoAdapter", "大模板业务，大模板预加载失败 onVideoLoadFail");
                            }
                        }, false);
                    }
                });
                return;
            }
            s.a("RewardMVVideoAdapter", "大模板业务，大模板下载成功，Campaign 下载不成功，isCampaignsDownloadSuccess： " + d.this.d + "  isCampaignTPLProLoad： " + d.this.f);
        }

        @Override // com.mbridge.msdk.reward.adapter.c.i
        public final void a(String str, String str2, String str3, String str4, String str5) {
            s.a("RewardMVVideoAdapter", "大模板业务，大模板下载失败");
            s.d("HBOPTIMIZE", "模板加载成功 requestId " + str3);
            if (d.this.M.get(0) != null && d.this.M.size() > 0) {
                try {
                    com.mbridge.msdk.reward.b.a.a((CampaignEx) d.this.M.get(0), d.this.i, "resource download failed: " + str5 + " is tpl: " + this.b, d.this.j, d.this.A, str3, ((CampaignEx) d.this.M.get(0)).getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                } catch (Exception unused) {
                }
            }
            if (this.a.getRsIgnoreCheckRule() != null && this.a.getRsIgnoreCheckRule().size() > 0 && this.a.getRsIgnoreCheckRule().contains(3)) {
                s.b("RewardMVVideoAdapter", "tpl download fail but hit ignoreCheckRule");
                return;
            }
            d.this.e = false;
            d.a(d.this, str2, d.this.M, d.this.A);
            if (d.this.J != null) {
                d.this.J.removeMessages(5);
            }
            if (d.this.s == null || d.this.h) {
                return;
            }
            d.this.h = true;
            com.mbridge.msdk.reward.b.a.a(d.this.i, "" + str5, d.this.j, d.this.A, str3, this.a.getRequestIdNotice());
            d.this.s.a("errorCode: 3203 errorMessage: tpl temp resource download failed");
            s.a("RewardMVVideoAdapter", "大模板业务，大模板下载失败 onVideoLoadFail");
        }
    }

    public final void d(boolean z) {
        if (z || this.G == null || this.G.size() <= 0) {
            return;
        }
        com.mbridge.msdk.videocommon.a.a.a().a(this.j, this.G);
    }

    public final void e(boolean z) {
        if (z) {
            if (this.N == null || this.N.size() <= 0) {
                return;
            }
            for (CampaignEx campaignEx : this.N) {
                if (campaignEx != null) {
                    campaignEx.setLoadTimeoutState(0);
                    if (campaignEx.getRewardTemplateMode() != null && !TextUtils.isEmpty(campaignEx.getRewardTemplateMode().d())) {
                        ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.j + "_" + campaignEx.getRequestId() + "_" + campaignEx.getRewardTemplateMode().d(), false);
                    }
                }
            }
            com.mbridge.msdk.videocommon.a.a.a().a(this.j, this.N, "load_timeout", 0);
            return;
        }
        if (this.G == null || this.G.size() <= 0) {
            return;
        }
        for (CampaignEx campaignEx2 : this.G) {
            if (campaignEx2 != null) {
                campaignEx2.setLoadTimeoutState(0);
                if (campaignEx2.getRewardTemplateMode() != null && !TextUtils.isEmpty(campaignEx2.getRewardTemplateMode().d())) {
                    ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.j + "_" + campaignEx2.getRequestId() + "_" + campaignEx2.getRewardTemplateMode().d(), false);
                }
            }
        }
        com.mbridge.msdk.videocommon.a.a.a().a(this.j, this.G, "load_timeout", 0);
    }

    public final boolean f(boolean z) {
        if (z) {
            if (this.N == null || this.N.size() <= 0) {
                return false;
            }
            for (CampaignEx campaignEx : this.N) {
                if (campaignEx != null) {
                    campaignEx.setLoadTimeoutState(1);
                    if (campaignEx.getRewardTemplateMode() != null && !TextUtils.isEmpty(campaignEx.getRewardTemplateMode().d())) {
                        ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.j + "_" + campaignEx.getRequestId() + "_" + campaignEx.getRewardTemplateMode().d(), true);
                    }
                }
            }
            com.mbridge.msdk.videocommon.a.a.a().a(this.j, this.N, "load_timeout", 1);
            return true;
        }
        if (this.G == null || this.G.size() <= 0) {
            return false;
        }
        for (CampaignEx campaignEx2 : this.G) {
            if (campaignEx2 != null) {
                campaignEx2.setLoadTimeoutState(1);
                if (campaignEx2.getRewardTemplateMode() != null && !TextUtils.isEmpty(campaignEx2.getRewardTemplateMode().d())) {
                    ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.j + "_" + campaignEx2.getRequestId() + "_" + campaignEx2.getRewardTemplateMode().d(), true);
                }
            }
        }
        com.mbridge.msdk.videocommon.a.a.a().a(this.j, this.G, "load_timeout", 1);
        return true;
    }

    private void a(String str, List<CampaignEx> list, List<CampaignEx> list2) throws Throwable {
        if (com.mbridge.msdk.videocommon.a.a.a() != null && this.F != null) {
            com.mbridge.msdk.videocommon.a.a.a().b(str);
        }
        if (com.mbridge.msdk.videocommon.a.a.a() != null && this.F != null) {
            com.mbridge.msdk.videocommon.a.a.a().b(str, this.F.getVcn());
        }
        if (list != null && list.size() > 0) {
            s.d("HBOPTIMIZE", "最新返回的数据RequestId " + this.F.getRequestId());
            if (this.F != null && list.get(0) != null) {
                com.mbridge.msdk.reward.a.a.a(this.F.getRequestId(), list.get(0).getBidToken());
            }
            for (CampaignEx campaignEx : list) {
                if (campaignEx != null) {
                    com.mbridge.msdk.foundation.same.a.d.a(campaignEx.getCampaignUnitId(), campaignEx.getRequestId(), campaignEx.getId(), campaignEx.getPlct(), campaignEx.getPlctb(), campaignEx.getTimestamp());
                }
            }
        }
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        for (CampaignEx campaignEx2 : list2) {
            if (campaignEx2 != null) {
                com.mbridge.msdk.foundation.same.a.d.d(campaignEx2.getCampaignUnitId(), campaignEx2.getRequestId());
            }
        }
        com.mbridge.msdk.foundation.same.a.d.a(str);
        com.mbridge.msdk.foundation.db.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a();
    }

    private void a(CampaignEx campaignEx) {
        try {
            com.mbridge.msdk.videocommon.a.a aVarA = com.mbridge.msdk.videocommon.a.a.a();
            s.a("RewardMVVideoAdapter", "删除 Campaign id： " + campaignEx.getId());
            if (aVarA != null) {
                aVarA.a(campaignEx);
            }
        } catch (Throwable th) {
            s.b("RewardMVVideoAdapter", th.getMessage(), th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x0159  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.mbridge.msdk.foundation.entity.CampaignEx> a(com.mbridge.msdk.foundation.entity.CampaignUnit r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 507
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.reward.adapter.d.a(com.mbridge.msdk.foundation.entity.CampaignUnit):java.util.List");
    }

    private int g() {
        try {
            int iA = z.b(this.j) ? com.mbridge.msdk.reward.a.a.a(this.j) : 0;
            if (this.t == null) {
                return 0;
            }
            if (iA > this.t.u()) {
                return 0;
            }
            return iA;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        try {
            if (z.b(this.j)) {
                com.mbridge.msdk.reward.a.a.a(this.j, 0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String i() {
        try {
            return z.b(com.mbridge.msdk.reward.a.a.a) ? com.mbridge.msdk.reward.a.a.a : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private String j() {
        try {
            JSONArray jSONArray = new JSONArray();
            List<Long> listI = com.mbridge.msdk.foundation.controller.a.f().i();
            if (listI != null && listI.size() > 0) {
                Iterator<Long> it = listI.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().longValue());
                }
            }
            return jSONArray.length() > 0 ? w.a(jSONArray) : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private void k() {
        try {
            if (com.mbridge.msdk.foundation.same.a.d.a == null || com.mbridge.msdk.foundation.same.a.d.a.size() <= 0) {
                return;
            }
            com.mbridge.msdk.foundation.same.a.d.a.clear();
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
        }
    }

    private void l() {
        try {
            l.a(i.a(this.i)).a(this.j);
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
        }
    }

    /* compiled from: RewardMVVideoAdapter.java */
    /* renamed from: com.mbridge.msdk.reward.adapter.d$d, reason: collision with other inner class name */
    public class RunnableC0142d implements Runnable {
        private String b;

        public RunnableC0142d(String str) {
            this.b = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            i iVarA;
            try {
                s.b("RewardMVVideoAdapter", "=====getTtcRunnable 开始获取 mTtcIds:" + d.this.p + "  mExcludes:" + d.this.q);
                if (d.this.i != null && (iVarA = i.a(d.this.i)) != null) {
                    com.mbridge.msdk.foundation.db.c cVarA = com.mbridge.msdk.foundation.db.c.a(iVarA);
                    cVarA.a();
                    d.this.p = cVarA.a(d.this.j);
                }
                d.this.q = w.b(d.this.i, d.this.j);
                s.b("RewardMVVideoAdapter", "=====getTtcRunnable 获取完毕 mTtcIds:" + d.this.p + "  mExcludes:" + d.this.q);
                if (d.this.w) {
                    s.b("RewardMVVideoAdapter", "=====getTtcRunnable 获取ttcid和excludeids超时 mIsGetTtcExcIdsTimeout：" + d.this.w + " mIsGetTtcExcIdsSuccess:" + d.this.v);
                    return;
                }
                s.b("RewardMVVideoAdapter", "=====getTtcRunnable 获取ttcid和excludeids没有超时 mIsGetTtcExcIdsTimeout:" + d.this.w + " mIsGetTtcExcIdsSuccess:" + d.this.v);
                if (d.this.u != null && d.this.J != null) {
                    s.b("RewardMVVideoAdapter", "=====getTtcRunnable 删除 获取ttcid的超时任务");
                    d.this.J.removeCallbacks(d.this.u);
                }
                d.this.v = true;
                s.b("RewardMVVideoAdapter", "=====getTtcRunnable 给handler发送消息 mTtcIds:" + d.this.p + "  mExcludes:" + d.this.q);
                if (d.this.J != null) {
                    Message messageObtainMessage = d.this.J.obtainMessage();
                    messageObtainMessage.obj = this.b;
                    messageObtainMessage.what = 1;
                    d.this.J.sendMessage(messageObtainMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: RewardMVVideoAdapter.java */
    public class e implements Runnable {
        private String b;

        public e(String str) {
            this.b = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                s.b("RewardMVVideoAdapter", "=====超时task 开始执行 mTtcIds:" + d.this.p + "  RewardMVVideoAdapter.this.mExcludes:" + d.this.q);
                if (d.this.v) {
                    s.b("RewardMVVideoAdapter", "超时task 已经成功获取ttcid excludeids mIsGetTtcExcIdsTimeout:" + d.this.w + " mIsGetTtcExcIdsSuccess:" + d.this.v + "超时task不做处理");
                    return;
                }
                s.b("RewardMVVideoAdapter", "获取ttcid excludeids超时 mIsGetTtcExcIdsTimeout:" + d.this.w + " mIsGetTtcExcIdsSuccess:" + d.this.v);
                d.this.w = true;
                if (d.this.J != null) {
                    Message messageObtainMessage = d.this.J.obtainMessage();
                    messageObtainMessage.obj = this.b;
                    messageObtainMessage.what = 2;
                    d.this.J.sendMessage(messageObtainMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean b(CampaignEx campaignEx) {
        if (campaignEx == null) {
            return false;
        }
        try {
            return campaignEx.getPlayable_ads_without_video() == 2;
        } catch (Throwable th) {
            if (!MBridgeConstans.DEBUG) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public final void a(List<CampaignEx> list) {
        this.N = list;
    }

    public final void a(int i, int i2, int i3) {
        this.C = i;
        this.D = i2;
        this.E = i3;
    }

    public final void b(String str) {
        if (this.t != null && TextUtils.isEmpty(str)) {
            int iC = this.t.c() * 1000;
            if (this.N != null && this.N.size() > 0) {
                if (System.currentTimeMillis() - this.N.get(0).getTimestamp() < iC) {
                    c("hit ltorwc");
                    return;
                }
            }
        }
        b(str, this.H);
    }

    static /* synthetic */ void a(d dVar, String str, List list, List list2, boolean z) throws Throwable {
        com.mbridge.msdk.foundation.db.f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(str, (List<CampaignEx>) list, (List<CampaignEx>) list2, z);
        if (list2 != null && list2.size() > 0) {
            s.a("test_isReay_db", "标记缓存数据 ： " + list2.size());
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                CampaignEx campaignEx = (CampaignEx) it.next();
                if (campaignEx.getRewardTemplateMode() != null && !TextUtils.isEmpty(campaignEx.getRewardTemplateMode().d())) {
                    com.mbridge.msdk.videocommon.a.b(str + "_" + campaignEx.getId() + "_" + campaignEx.getRequestId() + "_" + campaignEx.getRewardTemplateMode().d());
                    com.mbridge.msdk.videocommon.a.b(campaignEx.getAdType(), campaignEx);
                }
            }
        }
        if (z) {
            dVar.a(str, (List<CampaignEx>) list, (List<CampaignEx>) list2);
        }
    }

    static /* synthetic */ void a(d dVar, String str, List list, boolean z) {
        if (TextUtils.isEmpty(str) || list == null || list.size() <= 0) {
            return;
        }
        s.a("RewardMVVideoAdapter", "删除失败的数据 ： " + list.size());
        com.mbridge.msdk.foundation.db.f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(str, (List<CampaignEx>) list, z);
    }

    static /* synthetic */ void a(d dVar, WindVaneWebView windVaneWebView, String str, CampaignEx campaignEx, List list, String str2, String str3, com.mbridge.msdk.videocommon.d.c cVar, String str4) {
        j jVar;
        try {
            a.C0146a c0146a = new a.C0146a();
            WindVaneWebView windVaneWebView2 = new WindVaneWebView(com.mbridge.msdk.foundation.controller.a.f().j());
            c0146a.a(windVaneWebView2);
            if (list != null && list.size() > 0) {
                jVar = new j(null, campaignEx, list);
            } else {
                jVar = new j(null, campaignEx);
            }
            j jVar2 = jVar;
            jVar2.a(dVar.y);
            jVar2.a(str3);
            jVar2.a(cVar);
            jVar2.e(dVar.H);
            c cVar2 = dVar.new c(windVaneWebView, str3, campaignEx, list, str2, str3, cVar, dVar.y, dVar);
            windVaneWebView2.setWebViewListener(new f(windVaneWebView, str, str3, c0146a, campaignEx, dVar, cVar2, dVar.J));
            windVaneWebView2.setObject(jVar2);
            windVaneWebView2.loadUrl(str2);
            dVar.J.postDelayed(cVar2, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
        } catch (Throwable th) {
            s.a("RewardMVVideoAdapter", th.getMessage());
        }
    }

    static /* synthetic */ void a(d dVar, String str, CampaignEx campaignEx, String str2, String str3, com.mbridge.msdk.videocommon.d.c cVar) {
        j jVar;
        try {
            a.C0146a c0146a = new a.C0146a();
            WindVaneWebView windVaneWebView = new WindVaneWebView(com.mbridge.msdk.foundation.controller.a.f().j());
            c0146a.a(windVaneWebView);
            String strB = com.mbridge.msdk.video.bt.a.c.a().b();
            c0146a.a(strB);
            if (dVar.F != null && dVar.F.getAds() != null && dVar.F.getAds().size() > 0) {
                jVar = new j(null, campaignEx, dVar.F.getAds());
            } else {
                jVar = new j(null, campaignEx);
            }
            j jVar2 = jVar;
            jVar2.a(dVar.y);
            jVar2.a(str3);
            jVar2.c(strB);
            jVar2.a(cVar);
            jVar2.e(dVar.H);
            b bVar = dVar.new b(str3, campaignEx, str2, str3, cVar, dVar.y, dVar);
            windVaneWebView.setWebViewListener(new a(str, str3, c0146a, campaignEx, dVar, bVar, dVar.J));
            windVaneWebView.setObject(jVar2);
            windVaneWebView.loadUrl(str2);
            dVar.J.postDelayed(bVar, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
        } catch (Throwable th) {
            s.a("RewardMVVideoAdapter", th.getMessage());
        }
    }

    static /* synthetic */ void a(d dVar, String str, String str2) {
        try {
            s.d("RewardMVVideoAdapter", "====delCampaignFromDownLoadCampaignListByUrld");
            if (dVar.x != null && !TextUtils.isEmpty(str2)) {
                Iterator<CampaignEx> it = dVar.x.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CampaignEx next = it.next();
                    if (next != null) {
                        String videoUrlEncode = next.getVideoUrlEncode();
                        if (!TextUtils.isEmpty(videoUrlEncode) && str2.equals(videoUrlEncode)) {
                            dVar.x.remove(next);
                            dVar.a(next);
                            break;
                        }
                        String str3 = next.getendcard_url();
                        if (!TextUtils.isEmpty(str3) && str2.equals(str3)) {
                            dVar.x.remove(next);
                            dVar.a(next);
                            break;
                        }
                        CampaignEx.c rewardTemplateMode = next.getRewardTemplateMode();
                        if (rewardTemplateMode != null) {
                            List<CampaignEx.c.a> listE = rewardTemplateMode.e();
                            if (listE != null) {
                                Iterator<CampaignEx.c.a> it2 = listE.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    CampaignEx.c.a next2 = it2.next();
                                    if (next2 != null && next2.b != null && next2.b.contains(str2)) {
                                        dVar.x.remove(next);
                                        dVar.a(next);
                                        break;
                                    }
                                }
                            }
                            String strD = rewardTemplateMode.d();
                            if (!TextUtils.isEmpty(strD) && str2.equals(strD)) {
                                dVar.x.remove(next);
                                dVar.a(next);
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                if (dVar.s == null || dVar.x == null || dVar.x.size() != 0) {
                    return;
                }
                if (dVar.J != null) {
                    dVar.J.removeMessages(5);
                }
                s.d("RewardMVVideoAdapter", "====del campaign and callback failed");
                dVar.c(str);
                dVar.s.a(str);
                return;
            }
            if (dVar.s != null) {
                if (dVar.J != null) {
                    dVar.J.removeMessages(5);
                }
                s.d("RewardMVVideoAdapter", "====del campaign and callback failed");
                dVar.c(str);
                dVar.s.a(str);
            }
        } catch (Throwable th) {
            s.b("RewardMVVideoAdapter", th.getMessage(), th);
            try {
                if (dVar.s != null) {
                    if (dVar.J != null) {
                        dVar.J.removeMessages(5);
                    }
                    s.d("RewardMVVideoAdapter", "====del campaign and callback failed");
                    dVar.c(str);
                }
            } catch (Throwable th2) {
                s.b("RewardMVVideoAdapter", th2.getMessage(), th2);
            }
        }
    }

    static /* synthetic */ void a(d dVar, final CampaignUnit campaignUnit) {
        dVar.F = campaignUnit;
        s.a("RewardMVVideoAdapter", "Campaign 请求成功： " + campaignUnit.getAds().size() + " 条");
        dVar.G = dVar.a(campaignUnit);
        try {
            CampaignEx campaignEx = campaignUnit.getAds().get(0);
            Context context = dVar.i;
            StringBuilder sb = new StringBuilder();
            sb.append("start download resource tpl ");
            sb.append(!TextUtils.isEmpty(campaignUnit.getAds().get(0).getMof_template_url()));
            com.mbridge.msdk.reward.b.a.a(campaignEx, context, sb.toString(), dVar.j, dVar.A, "", "", System.currentTimeMillis() - dVar.K);
        } catch (Exception unused) {
        }
        List<CampaignEx> list = dVar.G;
        new Thread(new Runnable() { // from class: com.mbridge.msdk.reward.adapter.d.6
            @Override // java.lang.Runnable
            public final void run() {
                s.b("RewardMVVideoAdapter", "在子线程处理业务逻辑 开始");
                m.a(i.a(d.this.i)).b();
                if (campaignUnit != null && campaignUnit.getAds() != null && campaignUnit.getAds().size() > 0) {
                    d.a(d.this, campaignUnit.getAds());
                }
                s.b("RewardMVVideoAdapter", "在子线程处理业务逻辑 完成");
            }
        }).start();
        if (dVar.G != null && dVar.G.size() > 0) {
            s.b("RewardMVVideoAdapter", "onload load成功 size:" + dVar.G.size());
            if (dVar.J != null) {
                dVar.J.sendEmptyMessage(3);
            }
            if (campaignUnit != null) {
                String sessionId = campaignUnit.getSessionId();
                if (z.b(sessionId)) {
                    s.b("RewardMVVideoAdapter", "onload sessionId:" + sessionId);
                    com.mbridge.msdk.reward.a.a.a = sessionId;
                }
            }
            List<CampaignEx> list2 = dVar.G;
            if (list2 != null) {
                try {
                    if (list2.size() > 0) {
                        dVar.l += list2.size();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (dVar.t == null || dVar.l > dVar.t.u()) {
                s.b("RewardMVVideoAdapter", "onload 重置offset为0");
                dVar.l = 0;
            }
            s.b("RewardMVVideoAdapter", "onload 算出 下次的offset是:" + dVar.l);
            if (z.b(dVar.j)) {
                com.mbridge.msdk.reward.a.a.a(dVar.j, dVar.l);
            }
            if (dVar.G != null && dVar.G.size() > 0) {
                s.b("RewardMVVideoAdapter", "#######onload 把广告存在本地 size:" + dVar.G.size());
                String str = dVar.k;
                String str2 = dVar.j;
                List<CampaignEx> list3 = dVar.G;
                if (com.mbridge.msdk.videocommon.a.a.a() != null) {
                    com.mbridge.msdk.videocommon.a.a.a().a(str, str2, list3);
                }
            }
            final CampaignEx campaignEx2 = dVar.G.get(0);
            final boolean z = !TextUtils.isEmpty(campaignEx2.getMof_template_url());
            final int nscpt = campaignEx2.getNscpt();
            dVar.d = false;
            dVar.e = false;
            dVar.f = false;
            dVar.h = false;
            dVar.g = false;
            if (dVar.A && campaignEx2 != null) {
                com.mbridge.msdk.foundation.db.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(campaignEx2.getBidToken(), campaignEx2.getCampaignUnitId(), campaignEx2.getEncryptPrice());
            }
            c.m.a.a(dVar.i, z, nscpt, dVar.A, dVar.z ? 287 : 94, dVar.k, dVar.j, campaignEx2.getRequestId(), dVar.G, dVar.new AnonymousClass3(z, campaignEx2, nscpt), new c.i() { // from class: com.mbridge.msdk.reward.adapter.d.4
                @Override // com.mbridge.msdk.reward.adapter.c.i
                public final void a(String str3, String str4, String str5, String str6) {
                    s.a("RewardMVVideoAdapter", "template 下载成功： ");
                }

                @Override // com.mbridge.msdk.reward.adapter.c.i
                public final void a(String str3, final String str4, final String str5, String str6, final String str7) {
                    s.a("RewardMVVideoAdapter", "template 下载失败： ");
                    if (!z && d.this.s != null && d.this.J != null) {
                        s.a("RewardMVVideoAdapter", "播放模板下载失败，非大模板");
                        try {
                            com.mbridge.msdk.reward.b.a.a(campaignEx2, d.this.i, "resource download failed: " + str7 + " is tpl: " + z, d.this.j, d.this.A, str5, campaignEx2.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                        } catch (Exception unused2) {
                        }
                        if (campaignEx2.getRsIgnoreCheckRule() == null || campaignEx2.getRsIgnoreCheckRule().size() <= 0 || !campaignEx2.getRsIgnoreCheckRule().contains(1)) {
                            d.this.J.post(new Runnable() { // from class: com.mbridge.msdk.reward.adapter.d.4.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    d.a(d.this, str4, d.this.M, d.this.A);
                                    if (d.this.J != null) {
                                        d.this.J.removeMessages(5);
                                    }
                                    if (d.this.h || d.this.s == null) {
                                        return;
                                    }
                                    d.this.h = true;
                                    com.mbridge.msdk.reward.b.a.a(d.this.i, "" + str7, d.this.j, d.this.A, str5, campaignEx2.getRequestIdNotice());
                                    d.this.s.a("errorCode: 3202 errorMessage: temp resource download failed");
                                    s.a("RewardMVVideoAdapter", "播放模板下载失败，非大模板 onVideoLoadFail");
                                }
                            });
                            return;
                        } else {
                            s.b("RewardMVVideoAdapter", "template download fail but hit ignoreCheckRule");
                            return;
                        }
                    }
                    try {
                        com.mbridge.msdk.reward.b.a.a(campaignEx2, d.this.i, "resource download failed: " + str7 + " is tpl: " + z, d.this.j, d.this.A, str5, campaignEx2.getRequestIdNotice(), System.currentTimeMillis() - d.this.K);
                    } catch (Exception unused3) {
                    }
                    if (campaignEx2.getRsIgnoreCheckRule() != null && campaignEx2.getRsIgnoreCheckRule().size() > 0 && campaignEx2.getRsIgnoreCheckRule().contains(3)) {
                        s.b("RewardMVVideoAdapter", "tpl download fail but hit ignoreCheckRule");
                        return;
                    }
                    if (nscpt == 1) {
                        s.a("RewardMVVideoAdapter", "播放模板下载失败，大模板，nscpt 1");
                        if (d.this.s == null || d.this.J == null) {
                            return;
                        }
                        d.this.J.post(new Runnable() { // from class: com.mbridge.msdk.reward.adapter.d.4.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                d.a(d.this, str4, d.this.M, d.this.A);
                                if (d.this.J != null) {
                                    d.this.J.removeMessages(5);
                                }
                                if (d.this.h || d.this.s == null) {
                                    return;
                                }
                                d.this.h = true;
                                com.mbridge.msdk.reward.b.a.a(d.this.i, "tpl temp resource download failed", d.this.j, d.this.A, str5, campaignEx2.getRequestIdNotice());
                                d.this.s.a("errorCode: 3203 errorMessage: tpl temp resource download failed");
                                s.a("RewardMVVideoAdapter", "播放模板下载失败，大模板，nscpt 1 onVideoLoadFail");
                            }
                        });
                    }
                }
            });
            if (z) {
                c.m.a.a(dVar.i, campaignEx2, dVar.k, dVar.j, campaignEx2.getRequestId(), dVar.new AnonymousClass5(campaignEx2, z, nscpt));
                return;
            }
            return;
        }
        s.b("RewardMVVideoAdapter", "onload load失败 返回的compaign 没有带视频素材");
        if (TextUtils.isEmpty(dVar.L)) {
            dVar.L = "APP ALREADY INSTALLED";
        }
        dVar.c(dVar.L);
    }

    static /* synthetic */ void a(d dVar, List list) {
        s.b("RewardMVVideoAdapter", "onload 开始 更新本机已安装广告列表");
        if (dVar.i == null || list == null || list.size() == 0) {
            s.b("RewardMVVideoAdapter", "onload 列表为空 不做更新本机已安装广告列表");
            return;
        }
        m mVarA = m.a(i.a(dVar.i));
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            CampaignEx campaignEx = (CampaignEx) list.get(i);
            if (campaignEx != null) {
                if (w.c(dVar.i, campaignEx.getPackageName())) {
                    if (com.mbridge.msdk.foundation.controller.a.e() != null) {
                        com.mbridge.msdk.foundation.controller.a.e().add(new com.mbridge.msdk.foundation.entity.j(campaignEx.getId(), campaignEx.getPackageName()));
                        z = true;
                    }
                } else if (mVarA != null && !mVarA.a(campaignEx.getId())) {
                    com.mbridge.msdk.foundation.entity.i iVar = new com.mbridge.msdk.foundation.entity.i();
                    iVar.a(campaignEx.getId());
                    iVar.a(campaignEx.getFca());
                    iVar.b(campaignEx.getFcb());
                    iVar.d(0);
                    iVar.c(0);
                    iVar.a(System.currentTimeMillis());
                    mVarA.a(iVar);
                }
            }
        }
        if (z) {
            s.b("RewardMVVideoAdapter", "更新安装列表");
            com.mbridge.msdk.foundation.controller.a.f().h();
        }
    }
}
