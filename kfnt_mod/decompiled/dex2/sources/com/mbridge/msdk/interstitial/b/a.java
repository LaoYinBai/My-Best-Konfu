package com.mbridge.msdk.interstitial.b;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.f;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: InterstitialCamapignCache.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "com.mbridge.msdk.interstitial.b.a";
    private static a c;
    private f b;

    private a() {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ != null) {
                this.b = f.a(i.a(contextJ));
            } else {
                s.d(a, "InterstitialCamapignCache get Context is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static a a() {
        if (c == null) {
            try {
                synchronized (a.class) {
                    if (c == null) {
                        c = new a();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return c;
    }

    public final List<CampaignEx> a(String str, int i) {
        ArrayList arrayList;
        List<CampaignEx> listA;
        try {
            if (TextUtils.isEmpty(str) || (listA = this.b.a(str, i, 0, 1)) == null) {
                return null;
            }
            arrayList = new ArrayList();
            try {
                Iterator<CampaignEx> it = listA.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next());
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

    public final void a(CampaignEx campaignEx, String str) {
        if (campaignEx != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                this.b.a(campaignEx.getId(), str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void a(List<CampaignEx> list, String str) {
        if (list == null || list.size() <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        Iterator<CampaignEx> it = list.iterator();
        while (it.hasNext()) {
            a(it.next(), str);
        }
    }

    public final void b(CampaignEx campaignEx, String str) {
        try {
            if (this.b == null || campaignEx == null || TextUtils.isEmpty(str)) {
                return;
            }
            this.b.a(campaignEx, str, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final synchronized void a(long j, String str) {
        try {
            if (this.b != null && j != 0 && !TextUtils.isEmpty(str)) {
                this.b.b(j, str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            s.d(a, e.getMessage());
        }
    }
}
