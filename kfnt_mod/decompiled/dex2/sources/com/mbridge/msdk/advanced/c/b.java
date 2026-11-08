package com.mbridge.msdk.advanced.c;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.out.NativeAdvancedAdListener;
import java.util.ArrayList;

/* compiled from: NativeAdvancedLoadListenerImpl.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b implements com.mbridge.msdk.advanced.a.a {
    private NativeAdvancedAdListener a;
    private c b;
    private MBridgeIds c;
    private String d;
    private String e;

    public b(c cVar, MBridgeIds mBridgeIds) {
        this.b = cVar;
        this.c = mBridgeIds;
        this.d = mBridgeIds.getUnitId();
    }

    public final void a(NativeAdvancedAdListener nativeAdvancedAdListener) {
        this.a = nativeAdvancedAdListener;
    }

    public final void a(String str) {
        this.e = str;
    }

    @Override // com.mbridge.msdk.advanced.a.a
    public final void a(CampaignEx campaignEx, int i) {
        s.d("NativeAdvancedLoadManager", "onLoadSuccessed: " + i);
        if (this.b == null || !this.b.a() || campaignEx == null) {
            return;
        }
        if (this.a != null && this.b != null) {
            this.a.onLoadSuccessed(this.c);
        }
        this.b.a(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(campaignEx);
        com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), arrayList, this.d, campaignEx.isBidCampaign());
        if (i != 2 || this.b == null) {
            return;
        }
        this.b.a(campaignEx, true);
    }

    @Override // com.mbridge.msdk.advanced.a.a
    public final void a(String str, int i) {
        s.d("NativeAdvancedLoadManager", "onLoadFailed: " + i + str);
        if (this.b == null || !this.b.a()) {
            return;
        }
        if (this.a != null) {
            this.a.onLoadFailed(this.c, str);
        }
        this.b.a(false);
        com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, this.d, !TextUtils.isEmpty(this.e));
    }
}
