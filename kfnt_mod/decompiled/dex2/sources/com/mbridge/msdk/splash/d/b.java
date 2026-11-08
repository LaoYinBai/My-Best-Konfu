package com.mbridge.msdk.splash.d;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.out.MBSplashLoadListener;
import com.mbridge.msdk.out.MBridgeIds;
import java.util.ArrayList;

/* compiled from: SplashLoadListenerImpl.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b implements com.mbridge.msdk.splash.b.a {
    private MBSplashLoadListener a;
    private c b;
    private MBridgeIds c;
    private String d;
    private String e;

    public b(c cVar, MBridgeIds mBridgeIds) {
        this.b = cVar;
        this.c = mBridgeIds;
        if (mBridgeIds != null) {
            this.d = mBridgeIds.getUnitId();
        }
    }

    public final void a(MBSplashLoadListener mBSplashLoadListener) {
        this.a = mBSplashLoadListener;
    }

    public final void a(String str) {
        this.e = str;
    }

    @Override // com.mbridge.msdk.splash.b.a
    public final void a(CampaignEx campaignEx, int i) {
        if (this.b == null || !this.b.a() || campaignEx == null) {
            return;
        }
        if (this.a != null) {
            this.a.onLoadSuccessed(this.c, i);
            this.a.isSupportZoomOut(this.c, campaignEx.getFlb() == 1);
        }
        this.b.a(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(campaignEx);
        com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), arrayList, this.d, campaignEx.isBidCampaign());
        if (i != 2 || this.b == null) {
            return;
        }
        this.b.a(campaignEx, 0, true);
    }

    @Override // com.mbridge.msdk.splash.b.a
    public final void a(String str, int i) {
        if (this.b == null || !this.b.a()) {
            return;
        }
        if (this.a != null) {
            this.a.onLoadFailed(this.c, str, i);
        }
        this.b.a(false);
        com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, this.d, !TextUtils.isEmpty(this.e));
    }
}
