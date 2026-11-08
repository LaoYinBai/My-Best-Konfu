package com.mbridge.msdk.mbnative.a;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.out.Campaign;
import java.util.ArrayList;
import java.util.List;

/* compiled from: APICache.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a extends b<String, List<Campaign>> {
    private com.mbridge.msdk.foundation.db.f a = com.mbridge.msdk.foundation.db.f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
    private int b;

    @Override // com.mbridge.msdk.mbnative.a.b
    public final void a(String str) {
    }

    @Override // com.mbridge.msdk.mbnative.a.b
    public final /* synthetic */ List<Campaign> a(String str, int i) {
        String str2 = str;
        List<CampaignEx> listA = this.a.a(str2, i, 2, this.b);
        if (listA == null) {
            return null;
        }
        if (a(listA, 2)) {
            this.a.a(str2, 2, this.b);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(listA);
        return arrayList;
    }

    @Override // com.mbridge.msdk.mbnative.a.b
    public final /* synthetic */ void a(String str, List<Campaign> list) {
        String str2 = str;
        List<Campaign> list2 = list;
        if (TextUtils.isEmpty(str2) || list2 == null || list2.size() <= 0) {
            return;
        }
        this.a.a(str2, 1, this.b, false);
        this.a.a(str2, 2, this.b, false);
        for (int i = 0; i < list2.size(); i++) {
            CampaignEx campaignEx = (CampaignEx) list2.get(i);
            campaignEx.setCacheLevel(1);
            this.a.a(campaignEx, str2, 1);
        }
    }

    @Override // com.mbridge.msdk.mbnative.a.b
    public final /* synthetic */ void a(String str, List<Campaign> list, String str2) {
        String str3 = str;
        List<Campaign> list2 = list;
        boolean z = !TextUtils.isEmpty(str2);
        if (TextUtils.isEmpty(str3) || list2 == null || list2.size() <= 0) {
            return;
        }
        this.a.a(str3, 1, this.b, z);
        this.a.a(str3, 2, this.b, z);
        for (int i = 0; i < list2.size(); i++) {
            CampaignEx campaignEx = (CampaignEx) list2.get(i);
            campaignEx.setCacheLevel(1);
            this.a.a(campaignEx, str3, 1);
        }
    }

    @Override // com.mbridge.msdk.mbnative.a.b
    public final /* synthetic */ List<Campaign> b(String str, int i) {
        String str2 = str;
        List<CampaignEx> listA = this.a.a(str2, i, 1, this.b);
        if (listA == null) {
            return null;
        }
        if (!a(listA, 1)) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(listA);
            return arrayList;
        }
        for (int i2 = 0; i2 < listA.size(); i2++) {
            CampaignEx campaignEx = listA.get(i2);
            campaignEx.setCacheLevel(2);
            this.a.a(campaignEx, str2, 1);
        }
        return null;
    }

    public a(int i) {
        this.b = i;
    }

    private boolean a(List<CampaignEx> list, int i) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long timestamp = list.get(0).getTimestamp();
        long jA = 0;
        switch (i) {
            case 1:
                jA = a();
                break;
            case 2:
                com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
                if (aVarB == null) {
                    aVarB = com.mbridge.msdk.b.b.a().b();
                }
                jA = 1000 * aVarB.B();
                break;
        }
        return jCurrentTimeMillis - timestamp > jA;
    }

    @Override // com.mbridge.msdk.mbnative.a.b
    public final void a(String str, Campaign campaign, String str2) {
        boolean z = !TextUtils.isEmpty(str2);
        if (campaign == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            CampaignEx campaignEx = (CampaignEx) campaign;
            if (this.a.a(campaignEx.getId(), campaignEx.getTab(), str, campaignEx.getCacheLevel(), campaignEx.getType(), z)) {
                this.a.a(campaignEx.getId(), str, campaignEx.getCacheLevel(), this.b, z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
