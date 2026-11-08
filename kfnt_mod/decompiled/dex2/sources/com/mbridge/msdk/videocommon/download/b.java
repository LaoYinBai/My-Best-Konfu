package com.mbridge.msdk.videocommon.download;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: DownLoadManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static b a;
    private Map<String, List<Map<String, a>>> e;
    private Map<String, List<CampaignEx>> f;
    private Map<String, List<a>> g;
    private boolean c = false;
    private ConcurrentHashMap<String, d> d = new ConcurrentHashMap<>();
    private ThreadPoolExecutor b = new ThreadPoolExecutor(5, 15, 15, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ThreadPoolExecutor.DiscardPolicy());

    private b() {
        this.b.allowCoreThreadTimeOut(true);
    }

    public static b getInstance() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    public final a a(String str, String str2) {
        d dVarC = c(str);
        if (dVarC != null) {
            return dVarC.a(str2);
        }
        return null;
    }

    public final List<CampaignEx> a(String str) {
        if (this.f == null || !this.f.containsKey(str)) {
            return null;
        }
        return this.f.get(str);
    }

    public final List<a> b(String str) {
        if (this.g == null || !this.g.containsKey(str)) {
            return null;
        }
        return this.g.get(str);
    }

    public final d c(String str) {
        if (this.d == null || !this.d.containsKey(str)) {
            return null;
        }
        return this.d.get(str);
    }

    public final boolean a(int i, String str, boolean z) {
        try {
            d dVarC = c(str);
            if (dVarC != null) {
                return dVarC.a(i, z) != null;
            }
            return false;
        } catch (Exception e) {
            if (!MBridgeConstans.DEBUG) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    public final boolean a(int i, String str, boolean z, int i2, boolean z2, int i3, List<CampaignEx> list) {
        return a(i, str, z, i2, z2, i3, list, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(int r14, java.lang.String r15, boolean r16, int r17, boolean r18, int r19, java.util.List<com.mbridge.msdk.foundation.entity.CampaignEx> r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 577
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.videocommon.download.b.a(int, java.lang.String, boolean, int, boolean, int, java.util.List, boolean):boolean");
    }

    public void load(String str) {
        d dVarC = c(str);
        if (dVarC != null) {
            dVarC.a();
        }
    }

    public d createUnitCache(Context context, String str, List<CampaignEx> list, int i, com.mbridge.msdk.videocommon.listener.a aVar) throws ClassNotFoundException {
        if (TextUtils.isEmpty(str) || list == null || list.size() == 0) {
            return null;
        }
        if (this.d.containsKey(str)) {
            d dVar = this.d.get(str);
            if (i == 94 || i == 287) {
                dVar.a(list.get(0).getRequestId(), aVar);
            } else {
                dVar.a(aVar);
            }
            dVar.a(list);
            return dVar;
        }
        d dVar2 = new d(context, list, this.b, str, i);
        if (aVar != null) {
            dVar2.a(aVar);
        }
        this.d.put(str, dVar2);
        return dVar2;
    }

    public d createUnitCache(Context context, String str, CampaignEx campaignEx, int i, com.mbridge.msdk.videocommon.listener.a aVar) throws ClassNotFoundException {
        if (TextUtils.isEmpty(str) || campaignEx == null) {
            return null;
        }
        if (this.d.containsKey(str)) {
            d dVar = this.d.get(str);
            if (i == 94 || i == 287) {
                dVar.a(campaignEx.getRequestId(), aVar);
            } else {
                dVar.a(aVar);
            }
            dVar.a(campaignEx);
            return dVar;
        }
        d dVar2 = new d(context, campaignEx, this.b, str, i);
        if (aVar != null) {
            dVar2.a(aVar);
        }
        this.d.put(str, dVar2);
        return dVar2;
    }

    public final void a(boolean z) {
        this.c = z;
        if (this.d != null) {
            Iterator<Map.Entry<String, d>> it = this.d.entrySet().iterator();
            while (it.hasNext()) {
                d value = it.next().getValue();
                if (value != null) {
                    value.b();
                }
            }
        }
    }

    public final int b(String str, String str2) {
        CopyOnWriteArrayList<Map<String, a>> copyOnWriteArrayListC;
        a value;
        CampaignEx campaignExG;
        if (this.d != null) {
            Iterator<Map.Entry<String, d>> it = this.d.entrySet().iterator();
            while (it.hasNext()) {
                d value2 = it.next().getValue();
                if (value2 != null && (copyOnWriteArrayListC = value2.c()) != null) {
                    int size = copyOnWriteArrayListC.size();
                    for (int i = 0; i < size; i++) {
                        Map<String, a> map = copyOnWriteArrayListC.get(i);
                        if (map != null) {
                            Iterator<Map.Entry<String, a>> it2 = map.entrySet().iterator();
                            if (it2.hasNext() && (value = it2.next().getValue()) != null && (campaignExG = value.g()) != null) {
                                String videoUrlEncode = campaignExG.getVideoUrlEncode();
                                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(videoUrlEncode) && str2.equals(videoUrlEncode)) {
                                    return value.f();
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public final void b(boolean z) {
        if (z) {
            if (this.c) {
                return;
            }
        } else {
            this.c = false;
        }
        if (this.d != null) {
            Iterator<Map.Entry<String, d>> it = this.d.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().a();
            }
        }
    }

    public final void a() throws ClassNotFoundException {
        if (this.d != null) {
            for (Map.Entry<String, d> entry : this.d.entrySet()) {
                d value = entry.getValue();
                String key = entry.getKey();
                try {
                    Class.forName("com.mbridge.msdk.videocommon.d.a");
                    com.mbridge.msdk.videocommon.d.c cVarA = com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), key);
                    if (cVarA != null) {
                        if (cVarA.v() == 2) {
                            value.b();
                        } else {
                            value.a();
                        }
                    }
                } catch (Exception e) {
                    s.d("DownLoadManager", e.getMessage());
                    try {
                        if (!TextUtils.isEmpty(key)) {
                            com.mbridge.msdk.b.d dVarF = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), key);
                            if (dVarF == null) {
                                dVarF = com.mbridge.msdk.b.d.e(key);
                            }
                            if (dVarF.l() == 2) {
                                value.b();
                            } else {
                                value.a();
                            }
                        }
                    } catch (Exception e2) {
                        s.d("DownLoadManager", e2.getMessage());
                    }
                }
            }
        }
    }
}
