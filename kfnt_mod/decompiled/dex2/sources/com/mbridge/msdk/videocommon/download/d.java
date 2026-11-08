package com.mbridge.msdk.videocommon.download;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.download.download.HTMLResourceManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

/* compiled from: UnitCacheCtroller.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    private com.mbridge.msdk.videocommon.listener.a e;
    private ConcurrentHashMap<String, com.mbridge.msdk.videocommon.listener.a> f;
    private ExecutorService j;
    private String l;
    private com.mbridge.msdk.videocommon.d.c m;
    private int o;
    private List<CampaignEx> c = new ArrayList();
    private boolean d = true;
    private c g = new c() { // from class: com.mbridge.msdk.videocommon.download.d.1
        @Override // com.mbridge.msdk.videocommon.download.c
        public final void a(long j, int i) {
            if (i == 5 || i == 4) {
                d.this.d = true;
                d.this.a();
            }
            if (i == 2) {
                d.this.d = true;
            }
        }
    };
    private CopyOnWriteArrayList<Map<String, a>> h = new CopyOnWriteArrayList<>();
    private long k = 3600;
    private int n = 2;
    com.mbridge.msdk.b.d a = null;
    com.mbridge.msdk.b.d b = null;
    private Context i = com.mbridge.msdk.foundation.controller.a.f().j();

    public d(Context context, List<CampaignEx> list, ExecutorService executorService, String str, int i) throws ClassNotFoundException {
        this.o = 1;
        if (this.c != null && list != null) {
            this.c.addAll(list);
        }
        this.j = executorService;
        this.l = str;
        this.o = i;
        b(this.c);
    }

    public d(Context context, CampaignEx campaignEx, ExecutorService executorService, String str, int i) throws ClassNotFoundException {
        this.o = 1;
        if (this.c != null && campaignEx != null) {
            this.c.add(campaignEx);
        }
        this.j = executorService;
        this.l = str;
        this.o = i;
        b(this.c);
    }

    public final void a(com.mbridge.msdk.videocommon.listener.a aVar) {
        this.e = aVar;
    }

    public final void a(String str, com.mbridge.msdk.videocommon.listener.a aVar) {
        if (this.f == null) {
            this.f = new ConcurrentHashMap<>();
        }
        this.f.put(str, aVar);
    }

    public final void a(List<CampaignEx> list) throws ClassNotFoundException {
        if (this.c != null && list != null) {
            this.c.addAll(list);
        }
        b(this.c);
    }

    public final void a(CampaignEx campaignEx) throws ClassNotFoundException {
        if (this.c != null && campaignEx != null) {
            this.c.add(campaignEx);
        }
        b(this.c);
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x009e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.util.List<com.mbridge.msdk.foundation.entity.CampaignEx> r12) throws java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instructions count: 550
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.videocommon.download.d.b(java.util.List):void");
    }

    private void d() {
        if (this.h != null) {
            try {
                synchronized (this.h) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    int i = 0;
                    while (i < this.h.size()) {
                        Map<String, a> map = this.h.get(i);
                        Iterator<Map.Entry<String, a>> it = map.entrySet().iterator();
                        while (it.hasNext()) {
                            a value = it.next().getValue();
                            if (value != null) {
                                if (jCurrentTimeMillis - value.c() > this.k * 1000 && value.f() == 1) {
                                    value.b("download timeout");
                                    value.a(this.n);
                                    value.m();
                                    this.h.remove(map);
                                    i--;
                                }
                                if (value.f() != 1 && value.f() != 5 && value.f() != 0) {
                                    value.m();
                                    this.h.remove(map);
                                    i--;
                                }
                            }
                        }
                        i++;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void e() {
        if (this.h != null) {
            try {
                synchronized (this.h) {
                    int i = 0;
                    while (i < this.h.size()) {
                        Map<String, a> map = this.h.get(i);
                        Iterator<Map.Entry<String, a>> it = map.entrySet().iterator();
                        while (it.hasNext()) {
                            a value = it.next().getValue();
                            if (value != null && value.g() != null && value.b()) {
                                value.l();
                                this.h.remove(map);
                                i--;
                            }
                        }
                        i++;
                    }
                }
            } catch (Throwable unused) {
                s.d("UnitCacheCtroller", "cleanDisplayTask ERROR");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0163 A[Catch: all -> 0x02c2, Exception -> 0x02c4, TryCatch #1 {Exception -> 0x02c4, blocks: (B:6:0x0014, B:7:0x0019, B:9:0x0021, B:10:0x0031, B:12:0x0037, B:14:0x0045, B:17:0x004d, B:18:0x0056, B:20:0x005c, B:23:0x0066, B:25:0x0070, B:27:0x007a, B:29:0x008a, B:36:0x00a3, B:38:0x00bd, B:43:0x00cb, B:41:0x00c5, B:44:0x00ef, B:46:0x00fb, B:48:0x0101, B:49:0x0109, B:66:0x0163, B:68:0x0188, B:70:0x018e, B:73:0x01a9, B:75:0x01b1, B:76:0x01d3, B:78:0x01de, B:80:0x01e5, B:81:0x01f0, B:86:0x01fb, B:88:0x0206, B:90:0x0212, B:94:0x0228, B:102:0x023b, B:104:0x0241, B:105:0x0249, B:107:0x024d, B:109:0x0259, B:111:0x025f, B:117:0x0285, B:119:0x028f, B:121:0x0295, B:122:0x0299, B:52:0x0114, B:54:0x011a, B:55:0x0123, B:57:0x0129, B:59:0x012f, B:60:0x013b, B:62:0x0141, B:63:0x014d, B:65:0x0157, B:123:0x02ad), top: B:135:0x0014, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.mbridge.msdk.videocommon.download.a> a(java.lang.String r21, int r22, boolean r23, java.util.List<com.mbridge.msdk.foundation.entity.CampaignEx> r24, boolean r25) {
        /*
            Method dump skipped, instructions count: 717
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.videocommon.download.d.a(java.lang.String, int, boolean, java.util.List, boolean):java.util.List");
    }

    private int b(CampaignEx campaignEx) {
        if (campaignEx == null) {
            return -1;
        }
        if (campaignEx.getReady_rate() != -1) {
            int ready_rate = campaignEx.getReady_rate();
            s.a("UnitCacheCtroller", "ready_rate(campaign): " + ready_rate);
            return ready_rate;
        }
        int iE = e(campaignEx);
        s.a("UnitCacheCtroller", "ready_rate(reward_unit_setting): " + iE);
        return iE;
    }

    /* JADX WARN: Removed duplicated region for block: B:146:0x0306  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.mbridge.msdk.videocommon.download.a a(int r23, boolean r24) {
        /*
            Method dump skipped, instructions count: 900
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.videocommon.download.d.a(int, boolean):com.mbridge.msdk.videocommon.download.a");
    }

    public static boolean a(a aVar, int i) {
        long jH = aVar.h();
        long jE = aVar.e();
        if (TextUtils.isEmpty(aVar.a())) {
            s.a("UnitCacheCtroller", "checkVideoDownload video done return true");
            return true;
        }
        if (i == 0) {
            if (aVar.g() != null && !TextUtils.isEmpty(aVar.g().getVideoUrlEncode())) {
                return true;
            }
        } else if (jE > 0 && jH * 100 >= jE * i) {
            if (i != 100 || aVar.f() == 5) {
                return true;
            }
            aVar.m();
            return false;
        }
        return false;
    }

    private static boolean a(a aVar, int i, boolean z) {
        long jH = aVar.h();
        long jE = aVar.e();
        if (TextUtils.isEmpty(aVar.a())) {
            s.a("UnitCacheCtroller", "checkVideoDownload video done return true");
            return true;
        }
        CampaignEx campaignExG = aVar.g();
        if (campaignExG != null) {
            if (campaignExG.getIsTimeoutCheckVideoStatus() == 1 && campaignExG.getVideoCheckType() == 1) {
                return true;
            }
            if (z && campaignExG.getVideoCheckType() == 1) {
                if (i == 0) {
                    return true;
                }
                if ((jE != 0 || jH != 0) && jH >= (i / 100) * jE) {
                    campaignExG.setIsTimeoutCheckVideoStatus(1);
                    return true;
                }
            }
            if (campaignExG.getRsIgnoreCheckRule() != null && campaignExG.getRsIgnoreCheckRule().size() > 0 && campaignExG.getRsIgnoreCheckRule().contains(0)) {
                s.b("UnitCacheCtroller", "Is not check video download status");
                return true;
            }
        }
        return a(aVar, i);
    }

    private boolean a(String str, CampaignEx campaignEx) {
        try {
        } catch (Throwable th) {
            s.b("UnitCacheCtroller", th.getMessage(), th);
        }
        if (campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0 && campaignEx.getRsIgnoreCheckRule().contains(2)) {
            s.b("UnitCacheCtroller", "Is not check endCard download status : " + str);
            return true;
        }
        if (z.a(str)) {
            s.b("UnitCacheCtroller", "checkEndcardDownload endcardUrl is null return true");
            return true;
        }
        if (b(str, campaignEx)) {
            s.b("UnitCacheCtroller", "checkEndcardDownload endcardUrl done return true");
            return true;
        }
        s.b("UnitCacheCtroller", "checkEndcardDownload endcardUrl return false");
        return false;
    }

    private boolean b(String str, CampaignEx campaignEx) {
        if (campaignEx.isMraid() || TextUtils.isEmpty(str)) {
            s.b("UnitCacheCtroller", "Campaign is Mraid, do not need download endcardurl or Campaign load timeout");
            return true;
        }
        if (campaignEx.getLoadTimeoutState() == 1 && !c(campaignEx)) {
            return true;
        }
        if (campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0 && campaignEx.getRsIgnoreCheckRule().contains(2)) {
            s.b("UnitCacheCtroller", "Is not check endCard download status : " + str);
            return true;
        }
        if (z.b(H5DownLoadManager.getInstance().getH5ResAddress(str))) {
            s.b("UnitCacheCtroller", "endcard zip 下载完成 return true endcardUrl:" + str);
            return true;
        }
        if (z.b(HTMLResourceManager.getInstance().getHtmlContentFromUrl(str))) {
            s.b("UnitCacheCtroller", "endcard url 源码 下载完成 return true endcardUrl:" + str);
            return true;
        }
        s.b("UnitCacheCtroller", "checkEndcardZipOrSourceDownLoad endcardUrl return false endcardUrl:" + str);
        return false;
    }

    private boolean a(int i, CampaignEx campaignEx, String str) {
        s.a("UnitCacheCtroller", "check template " + str);
        if (campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0 && campaignEx.getRsIgnoreCheckRule().contains(1)) {
            s.b("UnitCacheCtroller", "Is not check template download status");
            return true;
        }
        if (!TextUtils.isEmpty(str) && campaignEx.getLoadTimeoutState() == 0) {
            s.a("UnitCacheCtroller", "check template 下载情况：" + H5DownLoadManager.getInstance().getH5ResAddress(str));
            if (H5DownLoadManager.getInstance().getH5ResAddress(str) == null) {
                return false;
            }
        }
        return true;
    }

    public final a b(int i, boolean z) {
        try {
            return a(i, z);
        } catch (Throwable th) {
            s.b("UnitCacheCtroller", th.getMessage(), th);
            return null;
        }
    }

    public final a a(String str) {
        if (this.h == null) {
            return null;
        }
        synchronized (this.h) {
            try {
                try {
                    Iterator<Map<String, a>> it = this.h.iterator();
                    while (it.hasNext()) {
                        Map<String, a> next = it.next();
                        if (next != null && next.containsKey(str)) {
                            return next.get(str);
                        }
                    }
                } catch (Throwable unused) {
                    s.d("UnitCacheCtroller", "failed to get campaignTast by cid");
                }
                return null;
            } finally {
            }
        }
    }

    private boolean a(CopyOnWriteArrayList<Map<String, a>> copyOnWriteArrayList) {
        try {
            Iterator<Map<String, a>> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                Map<String, a> next = it.next();
                if (next != null) {
                    Iterator<Map.Entry<String, a>> it2 = next.entrySet().iterator();
                    while (it2.hasNext()) {
                        if (it2.next().getValue().f() == 1) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            if (!MBridgeConstans.DEBUG) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d7 A[Catch: all -> 0x01f0, TryCatch #0 {, blocks: (B:6:0x000c, B:8:0x0015, B:9:0x0017, B:10:0x001d, B:12:0x0023, B:15:0x002c, B:16:0x0034, B:18:0x003a, B:21:0x0049, B:24:0x0050, B:26:0x0056, B:27:0x0058, B:30:0x0064, B:31:0x0074, B:33:0x0084, B:35:0x0088, B:36:0x0090, B:37:0x0094, B:39:0x00a1, B:41:0x00a5, B:44:0x00aa, B:46:0x00ae, B:48:0x00b2, B:49:0x00c6, B:51:0x00ca, B:53:0x00d3, B:55:0x00d7, B:57:0x00db, B:58:0x00ef, B:60:0x00f3, B:72:0x0125, B:74:0x012a, B:76:0x0130, B:78:0x0163, B:80:0x0167, B:82:0x016d, B:84:0x0171, B:85:0x017a, B:87:0x017e, B:89:0x0186, B:92:0x0196, B:98:0x01a5, B:100:0x01b0, B:102:0x01b4, B:105:0x01b8, B:108:0x01be, B:110:0x01c9, B:112:0x01cd, B:114:0x01d3, B:115:0x01d8, B:117:0x01dc, B:119:0x01e2, B:122:0x01e9, B:121:0x01e6, B:61:0x00fa, B:63:0x00fe, B:67:0x0113, B:68:0x0119, B:70:0x011d, B:123:0x01ee), top: B:129:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            Method dump skipped, instructions count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.videocommon.download.d.a():void");
    }

    public final void b() {
        if (this.h != null) {
            try {
                synchronized (this.h) {
                    Iterator<Map<String, a>> it = this.h.iterator();
                    while (it.hasNext()) {
                        Map<String, a> next = it.next();
                        if (next != null) {
                            Iterator<Map.Entry<String, a>> it2 = next.entrySet().iterator();
                            while (it2.hasNext()) {
                                a value = it2.next().getValue();
                                if (value != null && value.f() == 1) {
                                    s.b("UnitCacheCtroller", "暂停所有下载");
                                    value.b("playing and stop download");
                                    value.m();
                                    this.h.remove(next);
                                    return;
                                }
                            }
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public final CopyOnWriteArrayList<Map<String, a>> c() {
        return this.h;
    }

    private boolean c(CampaignEx campaignEx) {
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

    private int d(CampaignEx campaignEx) {
        try {
            if (this.a != null) {
                return this.a.g();
            }
            return 100;
        } catch (Exception unused) {
            return 100;
        }
    }

    public final void b(String str) {
        try {
            synchronized (this.h) {
                if (!TextUtils.isEmpty(str) && this.h != null && this.h.size() > 0) {
                    Iterator<Map<String, a>> it = this.h.iterator();
                    while (it.hasNext()) {
                        Map<String, a> next = it.next();
                        if (next != null) {
                            for (Map.Entry<String, a> entry : next.entrySet()) {
                                if (entry != null && TextUtils.equals(entry.getKey(), str)) {
                                    this.h.remove(next);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    private int e(CampaignEx campaignEx) {
        try {
            if (campaignEx.getAdType() == 298) {
                if (this.b == null) {
                    this.b = com.mbridge.msdk.b.b.a().d(com.mbridge.msdk.foundation.controller.a.f().k(), this.l);
                }
                return this.b.g();
            }
            if (campaignEx.getAdType() == 42) {
                return d(null);
            }
            if (this.m == null) {
                this.m = com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.l, false);
            }
            return this.m.q();
        } catch (Throwable th) {
            s.b("UnitCacheCtroller", th.getMessage(), th);
            return 100;
        }
    }
}
