package com.mbridge.msdk.videocommon.a;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.f;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.c;
import com.mbridge.msdk.foundation.same.a.d;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.videocommon.d.b;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: VideoCampaignCache.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "com.mbridge.msdk.videocommon.a.a";
    private static a b;
    private f c;

    private a() {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ != null) {
                this.c = f.a(i.a(contextJ));
            } else {
                s.d(a, "RewardCampaignCache get Context is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public final List<CampaignEx> a(String str, int i) {
        ArrayList arrayList;
        List<CampaignEx> listA;
        try {
            if (TextUtils.isEmpty(str) || (listA = this.c.a(str, 0, 0, i)) == null) {
                return null;
            }
            arrayList = new ArrayList();
            try {
                for (CampaignEx campaignEx : listA) {
                    if (campaignEx != null) {
                        arrayList.add(campaignEx);
                    }
                }
                return arrayList;
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                return arrayList;
            }
        } catch (Exception e2) {
            e = e2;
            arrayList = null;
        }
    }

    public final void a(String str, List<CampaignEx> list, String str2, int i) {
        if (TextUtils.isEmpty(str) || list == null || list.size() <= 0 || TextUtils.isEmpty(str2) || this.c == null) {
            return;
        }
        this.c.a(str, list, str2, i);
    }

    public final void a(String str, List<CampaignEx> list) {
        if (TextUtils.isEmpty(str) || list == null || list.size() <= 0 || this.c == null) {
            return;
        }
        this.c.b(str, list);
    }

    public final void a(String str, String str2) {
        if (this.c == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.c.g(str, str2);
    }

    public final List<CampaignEx> a(String str, int i, boolean z) {
        return a(str, i, z, "");
    }

    public final List<CampaignEx> a(String str, int i, boolean z, String str2) {
        List<CampaignEx> listA;
        ArrayList arrayList = null;
        try {
            com.mbridge.msdk.videocommon.d.a aVarB = b.a().b();
            long jD = aVarB != null ? aVarB.d() : 0L;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (z) {
                listA = this.c.a(str, 0, 0, i, str2);
            } else {
                listA = this.c.a(str, 0, 0, i, false);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (listA == null) {
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            try {
                for (CampaignEx campaignEx : listA) {
                    if (campaignEx != null && (campaignEx.getReadyState() == 0 || campaignEx.getLoadTimeoutState() == 1)) {
                        long plct = campaignEx.getPlct() * 1000;
                        long timestamp = jCurrentTimeMillis - campaignEx.getTimestamp();
                        if ((plct > 0 && plct >= timestamp) || (plct <= 0 && jD >= timestamp)) {
                            arrayList2.add(campaignEx);
                        }
                    }
                }
                return arrayList2;
            } catch (Exception e) {
                e = e;
                arrayList = arrayList2;
                e.printStackTrace();
                return arrayList;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final List<c> a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return this.c.g(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final int b(String str, int i, boolean z, String str2) {
        List<CampaignEx> listA;
        try {
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            ArrayList arrayList = new ArrayList();
            if (z) {
                listA = this.c.a(str, 0, 0, i, str2);
            } else {
                listA = this.c.a(str, 0, 0, i, false);
            }
            if (listA == null) {
                return 0;
            }
            for (CampaignEx campaignEx : listA) {
                if (campaignEx != null && campaignEx.getReadyState() == 0) {
                    arrayList.add(campaignEx);
                }
            }
            return arrayList.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final List<CampaignEx> b(String str, int i, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return this.c.a(str, 0, 0, i, z);
        } catch (Exception e) {
            s.d(a, e.getLocalizedMessage());
            return null;
        }
    }

    public final List<CampaignEx> c(String str, int i, boolean z, String str2) {
        long jB;
        List<CampaignEx> listA;
        ArrayList arrayList = null;
        try {
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB != null) {
                jB = aVarB.B() * 1000;
            } else {
                jB = com.mbridge.msdk.b.b.a().b().B() * 1000;
            }
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (z) {
                listA = this.c.a(str, 0, 0, i, str2);
            } else {
                listA = this.c.a(str, 0, 0, i, false);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (listA == null) {
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            try {
                for (CampaignEx campaignEx : listA) {
                    if (campaignEx != null && campaignEx.getReadyState() == 0) {
                        long plctb = campaignEx.getPlctb() * 1000;
                        long timestamp = jCurrentTimeMillis - campaignEx.getTimestamp();
                        if ((plctb <= 0 && jB >= timestamp) || (plctb > 0 && plctb >= timestamp)) {
                            arrayList2.add(campaignEx);
                        }
                    }
                }
                return arrayList2;
            } catch (Exception e) {
                e = e;
                arrayList = arrayList2;
                e.printStackTrace();
                return arrayList;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final void a(CampaignEx campaignEx, String str) {
        if (campaignEx != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                if (campaignEx.isBidCampaign()) {
                    d.d(str, campaignEx.getRequestId());
                }
                this.c.a(campaignEx.getId(), str, campaignEx.isBidCampaign(), campaignEx.getRequestId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void a(CampaignEx campaignEx) {
        if (campaignEx != null) {
            try {
                if (TextUtils.isEmpty(campaignEx.getId())) {
                    return;
                }
                this.c.b(campaignEx.getId(), campaignEx.getRequestId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void b(String str) {
        ConcurrentHashMap<String, c> concurrentHashMapH;
        try {
            if (TextUtils.isEmpty(str) || (concurrentHashMapH = this.c.h(str)) == null || concurrentHashMapH.size() <= 0) {
                return;
            }
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            long jB = aVarB != null ? aVarB.B() : 0L;
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (c cVar : concurrentHashMapH.values()) {
                if (cVar != null) {
                    long jD = cVar.d();
                    if (jD <= 0) {
                        jD = jB;
                    }
                    if (cVar.e() + (jD * 1000) < jCurrentTimeMillis && !TextUtils.isEmpty(cVar.a())) {
                        s.d("HBOPTIMIZE", "不在有效期范围内 删除" + cVar.a());
                        b(str, cVar.a());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void b(String str, int i) throws Throwable {
        int size;
        try {
            List<c> listG = this.c.g(str);
            if (listG == null || listG.size() <= 0 || (size = listG.size() - i) <= 0) {
                return;
            }
            for (int i2 = 0; i2 < size; i2++) {
                b(str, listG.get(i2).a());
                d.d(str, listG.get(i2).a());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final synchronized void b(String str, String str2) {
        try {
            this.c.d(str2, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a(String str, String str2, List<CampaignEx> list) {
        try {
            if (TextUtils.isEmpty(str2) || list == null || list.size() <= 0) {
                return;
            }
            this.c.a(list, str, str2, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final synchronized void a(long j, String str) {
        try {
            this.c.a(j, str);
        } catch (Exception e) {
            e.printStackTrace();
            s.d(a, e.getMessage());
        }
    }

    public final synchronized void c(String str, int i) {
        int iAt;
        try {
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            iAt = aVarB.at();
        } catch (Exception e) {
            s.d(a, e.getMessage());
        }
        if (iAt == 0) {
            return;
        }
        List<CampaignEx> listB = this.c.b(str, i, iAt == 2);
        if (listB != null && listB.size() > 0) {
            for (CampaignEx campaignEx : listB) {
                String requestIdNotice = campaignEx.getRequestIdNotice();
                String id = campaignEx.getId();
                com.mbridge.msdk.videocommon.a.b(campaignEx.getCampaignUnitId() + "_" + id + "_" + requestIdNotice + "_" + campaignEx.getMof_template_url());
            }
        }
        this.c.a(str, i, iAt == 2);
    }
}
