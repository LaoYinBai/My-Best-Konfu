package com.mbridge.msdk.splash.c;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.f;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.splash.c.d;
import com.mbridge.msdk.splash.view.MBSplashView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ResManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static f a = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
    private static String b = "ResManager";
    private static int c = 1;

    public static CampaignEx a(MBSplashView mBSplashView, String str, String str2, String str3, boolean z, int i, boolean z2, boolean z3) {
        if (a == null) {
            a = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        }
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        long jL = aVarB.L() * 1000;
        long jB = aVarB.B() * 1000;
        if (a == null) {
            a = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        }
        a.b(jB, str2);
        List<CampaignEx> listA = a.a(str2, 0, 0, c, !TextUtils.isEmpty(str3));
        if (listA == null || listA.size() <= 0) {
            return null;
        }
        CampaignEx campaignEx = listA.get(0);
        if (TextUtils.isEmpty(campaignEx.getAdZip()) && TextUtils.isEmpty(campaignEx.getAdHtml())) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - jL;
        if (!z2) {
            if ((campaignEx.getPlct() > 0 && (campaignEx.getPlct() * 1000) + campaignEx.getTimestamp() >= jCurrentTimeMillis) || (campaignEx.getPlct() <= 0 && campaignEx.getTimestamp() >= j)) {
                if (!a(mBSplashView, campaignEx, str, str2, z, i, z3)) {
                    return null;
                }
                s.d(b, "cache campain is picked:" + campaignEx.getAppName());
                return a(campaignEx);
            }
            s.a(b, "========已经超了缓存时间");
            return null;
        }
        if ((campaignEx.getPlct() > 0 && (campaignEx.getPlct() * 1000) + campaignEx.getTimestamp() >= jCurrentTimeMillis) || (campaignEx.getPlct() <= 0 && campaignEx.getTimestamp() >= j)) {
            if (!a(mBSplashView, campaignEx, str, str2, z, i, z3)) {
                return null;
            }
            s.d(b, "cache campain is picked:" + campaignEx.getAppName());
            return a(campaignEx);
        }
        if (a(mBSplashView, campaignEx, str, str2, z, i, z3) && campaignEx.isSpareOffer(jL, jB)) {
            return a(campaignEx);
        }
        return null;
    }

    private static CampaignEx a(CampaignEx campaignEx) {
        if (!TextUtils.isEmpty(campaignEx.getAdZip()) || (!TextUtils.isEmpty(campaignEx.getAdHtml()) && campaignEx.getAdHtml().contains("<MBTPLMARK>"))) {
            campaignEx.setHasMBTplMark(true);
            campaignEx.setIsMraid(false);
        } else {
            campaignEx.setHasMBTplMark(false);
            campaignEx.setIsMraid(true);
        }
        return campaignEx;
    }

    private static boolean a(MBSplashView mBSplashView, CampaignEx campaignEx, String str, String str2, boolean z, int i, boolean z2) throws ClassNotFoundException {
        boolean z3;
        if (campaignEx == null) {
            return false;
        }
        mBSplashView.clearResState();
        if (TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
            z3 = true;
        } else {
            boolean z4 = mBSplashView.isVideoReady() || com.mbridge.msdk.videocommon.download.b.getInstance().a(297, str2, campaignEx.isBidCampaign());
            if (!z4) {
                a(mBSplashView, campaignEx, str2);
            } else {
                mBSplashView.setVideoReady(true);
            }
            z3 = z4;
        }
        if (!TextUtils.isEmpty(campaignEx.getAdZip()) && !mBSplashView.isH5Ready()) {
            String h5ResAddress = H5DownLoadManager.getInstance().getH5ResAddress(campaignEx.getAdZip());
            if (TextUtils.isEmpty(h5ResAddress)) {
                z3 = false;
            } else if (!z2) {
                a(mBSplashView, h5ResAddress, campaignEx, str, str2, z, i);
            }
        }
        if (TextUtils.isEmpty(campaignEx.getAdZip()) && !TextUtils.isEmpty(campaignEx.getAdHtml()) && !mBSplashView.isH5Ready()) {
            String strB = b(campaignEx.getAdHtml());
            if (TextUtils.isEmpty(strB)) {
                return false;
            }
            if (!z2) {
                a(mBSplashView, strB, campaignEx, str, str2, z, i);
            }
        }
        return z3;
    }

    private static void a(final MBSplashView mBSplashView, CampaignEx campaignEx, String str) throws ClassNotFoundException {
        com.mbridge.msdk.videocommon.listener.a aVar = new com.mbridge.msdk.videocommon.listener.a() { // from class: com.mbridge.msdk.splash.c.a.1
            @Override // com.mbridge.msdk.videocommon.listener.a
            public final void a(String str2) {
                mBSplashView.setVideoReady(true);
                s.a(a.b, "========VIDEO SUC");
            }

            @Override // com.mbridge.msdk.videocommon.listener.a
            public final void a(String str2, String str3) {
                mBSplashView.setVideoReady(false);
                s.a(a.b, "========VIDEO FAILED");
            }
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add(campaignEx);
        com.mbridge.msdk.videocommon.download.b.getInstance().createUnitCache(com.mbridge.msdk.foundation.controller.a.f().j(), str, arrayList, 297, aVar);
        if (!com.mbridge.msdk.videocommon.download.b.getInstance().a(297, str, campaignEx.isBidCampaign())) {
            com.mbridge.msdk.videocommon.download.b.getInstance().load(str);
        } else {
            mBSplashView.setVideoReady(true);
        }
    }

    private static void a(MBSplashView mBSplashView, String str, CampaignEx campaignEx, String str2, String str3, boolean z, int i) {
        d.c cVar = new d.c();
        cVar.c(str3);
        cVar.b(str2);
        cVar.a(campaignEx);
        cVar.a(str);
        cVar.a(z);
        cVar.a(i);
        d.a.a.a(mBSplashView, cVar, null);
    }

    private static String b(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                return "";
            }
            return "file:///" + file.getAbsolutePath();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Exception e) {
                e.getMessage();
                return "";
            }
        }
    }

    public static boolean a(MBSplashView mBSplashView, CampaignEx campaignEx) {
        boolean zIsH5Ready;
        if (mBSplashView == null) {
            s.d(b, "mbSplashView  is null");
            return false;
        }
        boolean zIsH5Ready2 = true;
        if (!TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
            zIsH5Ready2 = mBSplashView.isVideoReady();
            s.d(b, "======isReady isVideoReady:" + zIsH5Ready2);
        }
        if (zIsH5Ready2 && !TextUtils.isEmpty(campaignEx.getAdZip())) {
            zIsH5Ready2 = mBSplashView.isH5Ready();
            s.d(b, "======isReady getAdZip:" + zIsH5Ready2);
        }
        if (zIsH5Ready2 && TextUtils.isEmpty(campaignEx.getAdZip()) && !TextUtils.isEmpty(campaignEx.getAdHtml())) {
            zIsH5Ready = mBSplashView.isH5Ready();
            s.d(b, "======isReady getAdHtml:" + zIsH5Ready);
        } else {
            zIsH5Ready = zIsH5Ready2;
        }
        if (!TextUtils.isEmpty(campaignEx.getAdZip()) || !TextUtils.isEmpty(campaignEx.getAdHtml())) {
            return zIsH5Ready;
        }
        s.d(b, "======isReady getAdHtml  getAdZip all are empty");
        return false;
    }

    public static void a(CampaignEx campaignEx, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(campaignEx);
        if (a == null) {
            a = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        }
        a.b(arrayList, str);
    }

    public static void a(String str) {
        if (a == null) {
            a = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        }
        a.a(str, 0, c);
    }
}
