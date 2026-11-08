package com.mbridge.msdk.advanced.c;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.out.NativeAdvancedAdListener;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.Random;

/* compiled from: NativeAdvancedShowListenerImpl.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    private NativeAdvancedAdListener a;
    private CampaignEx b;
    private boolean c;
    private c d;

    public d(c cVar, NativeAdvancedAdListener nativeAdvancedAdListener, double d, CampaignEx campaignEx) {
        this.d = cVar;
        this.a = nativeAdvancedAdListener;
        this.b = campaignEx;
        this.c = a(d, campaignEx);
    }

    public final void a(MBridgeIds mBridgeIds) {
        if (this.d != null) {
            this.d.a = true;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("load_to=");
        stringBuffer.append(PushConstants.PUSH_TYPE_NOTIFY);
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("allow_skip=");
        stringBuffer.append(this.d.c());
        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
        com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, mBridgeIds.getUnitId(), stringBuffer.toString());
        if (this.a == null || this.c) {
            return;
        }
        this.a.onLogImpression(mBridgeIds);
    }

    public final void a(MBridgeIds mBridgeIds, String str) {
        com.mbridge.msdk.foundation.same.report.d.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.b, mBridgeIds.getUnitId(), str);
    }

    public final void b(MBridgeIds mBridgeIds) {
        if (this.a == null || this.c) {
            return;
        }
        this.a.onClick(mBridgeIds);
    }

    public final void c(MBridgeIds mBridgeIds) {
        if (this.a != null) {
            this.a.onClose(mBridgeIds);
            if (this.d != null) {
                this.d.a = false;
            }
        }
    }

    public final void d(MBridgeIds mBridgeIds) {
        if (this.a != null) {
            this.a.onLeaveApp(mBridgeIds);
        }
    }

    public final void e(MBridgeIds mBridgeIds) {
        if (this.a != null) {
            this.a.showFullScreen(mBridgeIds);
        }
    }

    public final void f(MBridgeIds mBridgeIds) {
        if (this.a != null) {
            this.a.closeFullScreen(mBridgeIds);
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
            s.d("NativeAdvancedShowListenerImpl", "cbp : " + d + " plct : " + j2 + " plctb : " + j);
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
            s.d("NativeAdvancedShowListenerImpl", sb.toString());
            return dNextDouble > d;
        } catch (Exception e) {
            s.b("NativeAdvancedShowListenerImpl", "CBPERROR", e);
            return false;
        }
    }
}
