package com.mbridge.msdk.splash.d;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.MBSplashShowListener;
import com.mbridge.msdk.out.MBridgeIds;
import java.util.Random;

/* compiled from: SplashShowListenerImpl.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    private MBSplashShowListener a;
    private CampaignEx b;
    private boolean c;
    private c d;

    public d(c cVar, MBSplashShowListener mBSplashShowListener, double d, CampaignEx campaignEx) {
        this.d = cVar;
        this.a = mBSplashShowListener;
        this.b = campaignEx;
        this.c = a(d, campaignEx);
    }

    public final void a(MBridgeIds mBridgeIds) {
        if (this.d != null) {
            this.d.a = true;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("load_to=");
        stringBuffer.append(this.d.b());
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("allow_skip=");
        stringBuffer.append(this.d.c() ? 1 : 0);
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("countdown=");
        stringBuffer.append(this.d.d());
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, mBridgeIds.getUnitId(), stringBuffer.toString());
        if (this.a == null || this.c) {
            return;
        }
        this.a.onShowSuccessed(mBridgeIds);
    }

    public final void a(MBridgeIds mBridgeIds, String str) {
        if (this.d != null) {
            this.d.a = false;
        }
        com.mbridge.msdk.foundation.same.report.d.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, mBridgeIds.getUnitId(), str);
        if (this.a != null) {
            this.a.onShowFailed(mBridgeIds, str);
        }
    }

    public final void b(MBridgeIds mBridgeIds) {
        if (this.a == null || this.c) {
            return;
        }
        this.a.onAdClicked(mBridgeIds);
    }

    public final void a(MBridgeIds mBridgeIds, int i) {
        if (this.a != null) {
            this.a.onDismiss(mBridgeIds, i);
        }
        if (this.d != null) {
            this.d.a = false;
        }
        if (i == 6 || i == 4 || i == 5) {
            CampaignEx campaignEx = this.b;
            String unitId = mBridgeIds.getUnitId();
            com.mbridge.msdk.splash.a.b bVarA = null;
            if (campaignEx != null) {
                try {
                    bVarA = com.mbridge.msdk.splash.a.b.a().b(unitId).d(campaignEx.getRequestId()).g(campaignEx.getRequestIdNotice()).c(campaignEx.getId()).e(campaignEx.getCreativeId() + "").a(campaignEx.isBidCampaign());
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        e.printStackTrace();
                        return;
                    }
                    return;
                }
            }
            com.mbridge.msdk.splash.e.a.a(bVarA, unitId, i);
        }
    }

    public final void a(MBridgeIds mBridgeIds, long j) {
        if (this.a != null) {
            this.a.onAdTick(mBridgeIds, j);
        }
    }

    public final void a(MBridgeIds mBridgeIds, int i, int i2, int i3) {
        if (this.a != null) {
            this.a.onZoomOutPlayStart(mBridgeIds);
        }
        try {
            com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, mBridgeIds.getUnitId(), "flb_size=" + i2 + "x" + i + "&flb_type=" + i3 + ContainerUtils.FIELD_DELIMITER);
        } catch (Exception e) {
            try {
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                if (MBridgeConstans.DEBUG) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private boolean a(double d, CampaignEx campaignEx) {
        long j;
        com.mbridge.msdk.b.a aVarB;
        try {
            String strK = com.mbridge.msdk.foundation.controller.a.f().k();
            long j2 = 0;
            if (TextUtils.isEmpty(strK) || (aVarB = com.mbridge.msdk.b.b.a().b(strK)) == null) {
                j = 0;
            } else {
                long jB = aVarB.B() * 1000;
                long jL = 1000 * aVarB.L();
                j = jB;
                j2 = jL;
            }
            s.d("SplashShowListenerImpl", "cbp : " + d + " plct : " + j2 + " plctb : " + j);
            if (campaignEx != null) {
                if (campaignEx.isSpareOffer(j2, j)) {
                    campaignEx.setSpareOfferFlag(1);
                    return true;
                }
                campaignEx.setSpareOfferFlag(0);
            }
            if (campaignEx == null || campaignEx.isBidCampaign() || d == 1.0d) {
                return false;
            }
            double dNextDouble = new Random().nextDouble();
            StringBuilder sb = new StringBuilder();
            sb.append("hit : ");
            sb.append(dNextDouble);
            sb.append(" ");
            sb.append(dNextDouble > d);
            s.d("SplashShowListenerImpl", sb.toString());
            return dNextDouble > d;
        } catch (Exception e) {
            s.b("SplashShowListenerImpl", "CBPERROR", e);
            return false;
        }
    }
}
