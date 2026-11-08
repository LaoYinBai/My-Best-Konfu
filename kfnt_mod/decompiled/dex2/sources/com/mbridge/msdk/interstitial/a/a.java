package com.mbridge.msdk.interstitial.a;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.b.d;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.entity.j;
import com.mbridge.msdk.foundation.same.b.c;
import com.mbridge.msdk.foundation.same.b.e;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.ab;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.interstitial.c.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: IntersAdapter.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private Context a;
    private String b;
    private String c;
    private int d;
    private boolean e;
    private int f;
    private String g;
    private String h;
    private String i;
    private a.C0116a j;
    private d k;
    private Handler l;
    private b m;
    private boolean n = false;
    private boolean o = false;

    public a(Context context, String str, String str2, String str3, boolean z) {
        this.a = context;
        this.b = str;
        this.c = str2;
        this.i = str3;
        this.e = z;
        this.k = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), str);
        if (this.k == null) {
            s.b("IntersAdapter", "获取默认的unitsetting");
            this.k = d.f(this.b);
        }
        this.l = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.interstitial.a.a.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                try {
                    switch (message.what) {
                        case 1:
                            s.b("IntersAdapter", "handler id获取成功 开始load mTtcIds:" + a.this.g + "  mExcludes:" + a.this.h);
                            a.this.c();
                            break;
                        case 2:
                            s.b("IntersAdapter", "handler id获取超时  开始load mTtcIds:" + a.this.g + "  mExcludes:" + a.this.h);
                            a.this.c();
                            break;
                        case 3:
                            if (a.this.j != null) {
                                s.b("IntersAdapter", "handler 数据load成功");
                                if (message.obj != null && (message.obj instanceof String)) {
                                    a.this.j.a(a.this.e, (String) message.obj);
                                    break;
                                }
                            }
                            break;
                        case 4:
                            if (a.this.j != null) {
                                s.b("IntersAdapter", "handler 数据load失败");
                                if (message.obj != null && (message.obj instanceof String)) {
                                    a.this.j.b(a.this.e, (String) message.obj);
                                    break;
                                }
                            }
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public final CampaignEx a() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            s.d("IntersAdapter", "==getIntersAvaCampaign 获取campaign 出错");
        }
        if (TextUtils.isEmpty(this.b)) {
            return null;
        }
        g();
        List<CampaignEx> listH = h();
        if (listH != null && listH.size() > 0) {
            for (int i = 0; i < listH.size(); i++) {
                CampaignEx campaignEx = listH.get(i);
                s.a("IntersAdapter", "html url:" + campaignEx.getHtmlUrl());
                if (campaignEx != null && (!TextUtils.isEmpty(campaignEx.getHtmlUrl()) || !TextUtils.isEmpty(campaignEx.getMraid()))) {
                    s.b("IntersAdapter", "adapter htmlurl:" + campaignEx.getHtmlUrl() + " id:" + campaignEx.getId());
                    return campaignEx;
                }
            }
            return null;
        }
        s.b("IntersAdapter", "adapter allCamp is null");
        return null;
    }

    public final void b() {
        if (this.a == null) {
            b("context is null");
            return;
        }
        if (TextUtils.isEmpty(this.b)) {
            b("unitid is null");
            return;
        }
        if (this.k == null) {
            b("unitSetting is null please call load");
            return;
        }
        int iR = this.k.r();
        if (iR <= 0) {
            s.b("IntersAdapter", "aqn为-1和0 不请求 直接返回失败 apiRepNum：" + iR);
            b("controller don't request ad");
            return;
        }
        s.b("IntersAdapter", "load 开始清除过期数据");
        g();
        List<CampaignEx> listH = h();
        if (listH != null && listH.size() > 0) {
            s.b("IntersAdapter", "load 本地已有缓存数量：" + listH.size());
            CampaignEx campaignEx = listH.get(0);
            a(campaignEx != null ? campaignEx.getRequestId() : "");
            return;
        }
        new Thread(new RunnableC0115a()).start();
        if (this.l != null) {
            this.m = new b();
            this.l.postDelayed(this.m, 90000L);
        } else {
            s.b("IntersAdapter", "handler 为空 直接load");
            c();
        }
    }

    public final void c() {
        try {
            if (this.a == null) {
                b("context is null");
                return;
            }
            if (TextUtils.isEmpty(this.b)) {
                b("unitid is null");
                return;
            }
            if (this.k == null) {
                b("unitSetting is null please call load");
                return;
            }
            s.b("IntersAdapter", "load 开始准备请求参数");
            com.mbridge.msdk.foundation.same.net.h.d dVarE = e();
            if (dVarE == null) {
                s.b("IntersAdapter", "load 请求参数为空 load失败");
                b("request parameter is null");
                return;
            }
            String strE = w.e(this.b);
            if (!TextUtils.isEmpty(strE)) {
                dVarE.a("j", strE);
            }
            com.mbridge.msdk.interstitial.d.a aVar = new com.mbridge.msdk.interstitial.d.a(this.a);
            com.mbridge.msdk.interstitial.d.b bVar = new com.mbridge.msdk.interstitial.d.b() { // from class: com.mbridge.msdk.interstitial.a.a.2
                @Override // com.mbridge.msdk.interstitial.d.b
                public final void a(CampaignUnit campaignUnit) {
                    try {
                        s.b("IntersAdapter", "onLoadCompaginSuccess 数据刚请求回来");
                        a.a(a.this, campaignUnit);
                    } catch (Exception e) {
                        e.printStackTrace();
                        s.b("IntersAdapter", "onLoadCompaginSuccess 数据刚请求失败");
                        a.this.b("can't show because unknow error");
                        a.this.k();
                    }
                }

                @Override // com.mbridge.msdk.interstitial.d.b
                public final void a(int i, String str) {
                    s.d("IntersAdapter", str);
                    s.b("IntersAdapter", "onLoadCompaginFailed load失败 errorCode:" + i + " msg:" + str);
                    a.this.b(str);
                    a.this.k();
                }
            };
            bVar.d = this.b;
            bVar.e = this.c;
            bVar.f = 279;
            if (com.mbridge.msdk.foundation.same.net.g.d.c().b()) {
                aVar.b(1, com.mbridge.msdk.foundation.same.net.g.d.c().h, dVarE, bVar);
            } else {
                aVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().f, dVarE, bVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
            b("can't show because unknow error");
            k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.l != null) {
            Message messageObtain = Message.obtain();
            messageObtain.obj = str;
            messageObtain.what = 3;
            this.l.sendMessage(messageObtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        try {
            if (this.l != null) {
                Message messageObtain = Message.obtain();
                messageObtain.obj = str;
                messageObtain.what = 4;
                this.l.sendMessage(messageObtain);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private com.mbridge.msdk.foundation.same.net.h.d e() throws JSONException, NoSuchAlgorithmException {
        String strK = com.mbridge.msdk.foundation.controller.a.f().k();
        String md5 = SameMD5.getMD5(com.mbridge.msdk.foundation.controller.a.f().k() + com.mbridge.msdk.foundation.controller.a.f().l());
        int i = this.e ? 3 : 2;
        this.f = 1;
        if (this.k.s() > 0) {
            this.f = this.k.s();
        }
        int iR = this.k.r() > 0 ? this.k.r() : 1;
        String str = this.h;
        String str2 = this.g;
        String strA = com.mbridge.msdk.foundation.same.a.d.a(this.b, "interstitial");
        String strM = m();
        this.d = j();
        String strL = l();
        if (TextUtils.isEmpty(this.i)) {
            this.i = PushConstants.PUSH_TYPE_NOTIFY;
        }
        com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "app_id", strK);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, MBridgeConstans.PROPERTIES_UNIT_ID, this.b);
        if (!TextUtils.isEmpty(this.c)) {
            com.mbridge.msdk.foundation.same.net.g.b.a(dVar, MBridgeConstans.PLACEMENT_ID, this.c);
        }
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "sign", md5);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "category", this.i);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "req_type", i + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ad_num", iR + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "tnum", this.f + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "only_impression", "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ping_mode", "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ttc_ids", str2);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.b, strA);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.c, str);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "install_ids", strM);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, CampaignEx.JSON_KEY_AD_SOURCE_ID, "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.a, strL);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ad_type", "279");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "offset", this.d + "");
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String f() {
        String strA;
        strA = "";
        try {
            JSONArray jSONArrayA = w.a(this.a, this.b);
            strA = jSONArrayA.length() > 0 ? w.a(jSONArrayA) : "";
            s.b("IntersAdapter", "get excludes:" + strA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strA;
    }

    private void g() {
        try {
            if (com.mbridge.msdk.interstitial.b.a.a() != null) {
                com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
                if (aVarB == null) {
                    aVarB = com.mbridge.msdk.b.b.a().b();
                }
                com.mbridge.msdk.interstitial.b.a.a().a(aVarB.L() * 1000, this.b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<CampaignEx> h() {
        try {
            if (com.mbridge.msdk.interstitial.b.a.a() != null) {
                return com.mbridge.msdk.interstitial.b.a.a().a(this.b, 1);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void a(a.C0116a c0116a) {
        this.j = c0116a;
    }

    private List<CampaignEx> a(List<CampaignEx> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            try {
                if (list.size() > 0) {
                    s.b("IntersAdapter", "onload 总共返回 的compaign有：" + list.size());
                    int iS = this.k.s();
                    for (int i = 0; i < list.size() && i < this.f && arrayList.size() < iS; i++) {
                        CampaignEx campaignEx = list.get(i);
                        if (campaignEx != null && campaignEx.getOfferType() == 1 && TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
                            s.b("IntersAdapter", "offertype=1 但是videourl为空");
                        } else if (campaignEx != null && ((!TextUtils.isEmpty(campaignEx.getHtmlUrl()) || campaignEx.isMraid()) && campaignEx.getOfferType() != 99)) {
                            if (w.b(campaignEx)) {
                                campaignEx.setRtinsType(w.c(this.a, campaignEx.getPackageName()) ? 1 : 2);
                            }
                            if (campaignEx.getWtick() == 1 || !w.c(this.a, campaignEx.getPackageName())) {
                                arrayList.add(campaignEx);
                            } else if (w.b(campaignEx)) {
                                arrayList.add(campaignEx);
                            } else {
                                w.a(this.b, campaignEx, com.mbridge.msdk.foundation.same.a.D);
                            }
                        }
                    }
                    s.b("IntersAdapter", "onload 返回有以下有效的compaign：" + arrayList.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<CampaignEx> b(List<CampaignEx> list) throws Throwable {
        FileOutputStream fileOutputStream;
        Exception e;
        File file;
        ArrayList arrayList = new ArrayList(list.size());
        for (CampaignEx campaignEx : list) {
            if (campaignEx.isMraid() && !TextUtils.isEmpty(campaignEx.getMraid())) {
                try {
                    String strB = e.b(c.MBRIDGE_700_HTML);
                    String md5 = SameMD5.getMD5(ab.a(campaignEx.getMraid()));
                    if (TextUtils.isEmpty(md5)) {
                        md5 = String.valueOf(System.currentTimeMillis());
                    }
                    File file2 = new File(strB, md5.concat(".html"));
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        try {
                            StringBuilder sb = new StringBuilder();
                            String strB2 = com.mbridge.msdk.b.b.b.a().b();
                            if (!TextUtils.isEmpty(strB2)) {
                                sb.append("<script>");
                                sb.append(strB2);
                                sb.append("</script>");
                            }
                            sb.append(campaignEx.getMraid());
                            fileOutputStream.write(sb.toString().getBytes());
                            fileOutputStream.flush();
                            campaignEx.setMraid(file2.getAbsolutePath());
                            com.mbridge.msdk.foundation.same.report.b.a(campaignEx, "", this.b, CampaignEx.CLICKMODE_ON);
                            try {
                                fileOutputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        e.printStackTrace();
                        campaignEx.setMraid("");
                        com.mbridge.msdk.foundation.same.report.b.a(campaignEx, e.getMessage(), this.b, CampaignEx.CLICKMODE_ON);
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        file = new File(campaignEx.getMraid());
                        if (file.exists()) {
                        }
                        b("mraid resource write fail");
                    }
                } catch (Exception e5) {
                    fileOutputStream = null;
                    e = e5;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                }
                file = new File(campaignEx.getMraid());
                if (file.exists() || !file.isFile() || !file.canRead()) {
                    b("mraid resource write fail");
                }
            }
            arrayList.add(campaignEx);
        }
        return arrayList;
    }

    private int i() {
        try {
            Map<String, Integer> map = com.mbridge.msdk.interstitial.c.a.d;
            int iIntValue = (TextUtils.isEmpty(this.b) || map == null || !map.containsKey(this.b)) ? 1 : map.get(this.b).intValue();
            if (iIntValue <= 0) {
                return 1;
            }
            return iIntValue;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    private int j() {
        int i = 0;
        try {
            int iA = !TextUtils.isEmpty(this.b) ? com.mbridge.msdk.interstitial.c.a.a(this.b) : 0;
            if (iA <= i()) {
                i = iA;
            }
            s.b("IntersAdapter", "getCurrentOffset:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        try {
            if (TextUtils.isEmpty(this.b)) {
                return;
            }
            com.mbridge.msdk.interstitial.c.a.a(this.b, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String l() {
        try {
            return !TextUtils.isEmpty(com.mbridge.msdk.interstitial.c.a.a) ? com.mbridge.msdk.interstitial.c.a.a : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String m() {
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
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* compiled from: IntersAdapter.java */
    /* renamed from: com.mbridge.msdk.interstitial.a.a$a, reason: collision with other inner class name */
    public class RunnableC0115a implements Runnable {
        public RunnableC0115a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            i iVarA;
            try {
                s.b("IntersAdapter", "=====getTtcRunnable 开始获取 mTtcIds:" + a.this.g + "  mExcludes:" + a.this.h);
                if (a.this.a != null && (iVarA = i.a(a.this.a)) != null) {
                    com.mbridge.msdk.foundation.db.c cVarA = com.mbridge.msdk.foundation.db.c.a(iVarA);
                    cVarA.a();
                    a.this.g = cVarA.a(a.this.b);
                }
                a.this.h = a.this.f();
                s.b("IntersAdapter", "=====getTtcRunnable 获取完毕 mTtcIds:" + a.this.g + "  mExcludes:" + a.this.h);
                if (a.this.o) {
                    s.b("IntersAdapter", "=====getTtcRunnable 获取ttcid和excludeids超时 mIsGetTtcExcIdsTimeout：" + a.this.o + " mIsGetTtcExcIdsSuccess:" + a.this.n);
                    return;
                }
                s.b("IntersAdapter", "=====getTtcRunnable 获取ttcid和excludeids没有超时 mIsGetTtcExcIdsTimeout:" + a.this.o + " mIsGetTtcExcIdsSuccess:" + a.this.n);
                if (a.this.m != null) {
                    s.b("IntersAdapter", "=====getTtcRunnable 删除 获取ttcid的超时任务");
                    a.this.l.removeCallbacks(a.this.m);
                }
                a.this.n = true;
                s.b("IntersAdapter", "=====getTtcRunnable 给handler发送消息 mTtcIds:" + a.this.g + "  mExcludes:" + a.this.h);
                if (a.this.l != null) {
                    a.this.l.sendEmptyMessage(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: IntersAdapter.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                s.b("IntersAdapter", "=====超时task 开始执行 mTtcIds:" + a.this.g + "  mExcludes:" + a.this.h);
                if (a.this.n) {
                    s.b("IntersAdapter", "超时task 已经成功获取ttcid excludeids mIsGetTtcExcIdsTimeout:" + a.this.o + " mIsGetTtcExcIdsSuccess:" + a.this.n + "超时task不做处理");
                    return;
                }
                s.b("IntersAdapter", "获取ttcid excludeids超时 mIsGetTtcExcIdsTimeout:" + a.this.o + " mIsGetTtcExcIdsSuccess:" + a.this.n);
                a.this.o = true;
                if (a.this.l != null) {
                    a.this.l.sendEmptyMessage(2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final boolean d() {
        return this.e;
    }

    static /* synthetic */ void a(a aVar, CampaignUnit campaignUnit) {
        if (campaignUnit == null || campaignUnit.getAds() == null || campaignUnit.getAds().size() <= 0) {
            aVar.b("no server ads available");
            return;
        }
        final ArrayList<CampaignEx> ads = campaignUnit.getAds();
        final List<CampaignEx> listA = aVar.a(ads);
        if (campaignUnit != null) {
            String sessionId = campaignUnit.getSessionId();
            if (!TextUtils.isEmpty(sessionId)) {
                s.b("IntersAdapter", "onload sessionId:" + sessionId);
                com.mbridge.msdk.interstitial.c.a.a = sessionId;
            }
        }
        try {
            s.b("IntersAdapter", "onload offset相加前 " + aVar.d + " mTnum:" + aVar.f);
            aVar.d = aVar.d + aVar.f;
            int i = aVar.i();
            if (aVar.d > i) {
                s.b("IntersAdapter", "onload 重置offset为0 :" + i);
                aVar.d = 0;
            }
            s.b("IntersAdapter", "onload 算出 下次的offset是:" + aVar.d);
            if (!TextUtils.isEmpty(aVar.b)) {
                com.mbridge.msdk.interstitial.c.a.a(aVar.b, aVar.d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ads != null && ads.size() > 0) {
            s.b("IntersAdapter", "在主线程 开始处理vba");
            if (ads == null || ads.size() == 0 || aVar.k == null || aVar.a == null) {
                s.b("IntersAdapter", "处理vba null retun");
            }
        }
        w.a((List<CampaignEx>) ads);
        new Thread(new Runnable() { // from class: com.mbridge.msdk.interstitial.a.a.3
            @Override // java.lang.Runnable
            public final void run() {
                s.b("IntersAdapter", "在子线程处理业务逻辑 开始");
                if (listA != null && listA.size() > 0) {
                    s.d("IntersAdapter", "onload load成功 size:" + listA.size());
                    s.b("IntersAdapter", "onload 把广告存在本地 size:" + listA.size());
                    a.a(a.this, a.this.b, a.this.b((List<CampaignEx>) listA));
                    CampaignEx campaignEx = (CampaignEx) listA.get(0);
                    a.this.a(campaignEx != null ? campaignEx.getRequestId() : "");
                } else {
                    s.d("IntersAdapter", "onload load失败 size:0");
                    a.this.b("no ads available");
                }
                m.a(i.a(a.this.a)).b();
                if (ads != null && ads.size() > 0) {
                    a.b(a.this, ads);
                }
                s.b("IntersAdapter", "在子线程处理业务逻辑 完成");
            }
        }).start();
    }

    static /* synthetic */ void a(a aVar, String str, List list) {
        if (com.mbridge.msdk.interstitial.b.a.a() != null) {
            com.mbridge.msdk.interstitial.b.a aVarA = com.mbridge.msdk.interstitial.b.a.a();
            try {
                if (TextUtils.isEmpty(str) || list == null || list.size() <= 0) {
                    return;
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    aVarA.b((CampaignEx) it.next(), str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ void b(a aVar, List list) {
        s.b("IntersAdapter", "onload 开始 更新本机已安装广告列表");
        if (aVar.a == null || list == null || list.size() == 0) {
            s.b("IntersAdapter", "onload 列表为空 不做更新本机已安装广告列表");
            return;
        }
        m mVarA = m.a(i.a(aVar.a));
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            CampaignEx campaignEx = (CampaignEx) list.get(i);
            if (campaignEx != null) {
                if (w.c(aVar.a, campaignEx.getPackageName())) {
                    if (com.mbridge.msdk.foundation.controller.a.e() != null) {
                        com.mbridge.msdk.foundation.controller.a.e().add(new j(campaignEx.getId(), campaignEx.getPackageName()));
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
            s.b("IntersAdapter", "更新安装列表");
            com.mbridge.msdk.foundation.controller.a.f().h();
        }
    }
}
