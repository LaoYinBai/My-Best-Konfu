package com.mbridge.msdk.mbbanner.common.a;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbbanner.common.util.BannerUtils;
import com.mbridge.msdk.mbbid.out.BidResponsed;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;

/* compiled from: BannerV3Params.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private static final String a = "d";

    public static com.mbridge.msdk.foundation.same.net.h.d a(boolean z, Context context, String str, String str2, int i, b bVar) throws JSONException, NoSuchAlgorithmException {
        String strK = com.mbridge.msdk.foundation.controller.a.f().k();
        String md5 = SameMD5.getMD5(com.mbridge.msdk.foundation.controller.a.f().k() + com.mbridge.msdk.foundation.controller.a.f().l());
        String ttcIds = BannerUtils.getTtcIds(context, str);
        String strB = w.b(context, str);
        String installIds = BannerUtils.getInstallIds();
        String strA = com.mbridge.msdk.foundation.same.a.d.a(str, "banner");
        String closeIds = BannerUtils.getCloseIds(str);
        com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "app_id", strK);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, MBridgeConstans.PROPERTIES_UNIT_ID, str);
        if (!TextUtils.isEmpty(bVar.d())) {
            com.mbridge.msdk.foundation.same.net.g.b.a(dVar, MBridgeConstans.PLACEMENT_ID, bVar.d());
        }
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "sign", md5);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "only_impression", "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ping_mode", "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ttc_ids", ttcIds);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.c, strB);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "install_ids", installIds);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, CampaignEx.JSON_KEY_AD_SOURCE_ID, "1");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.a, str2);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "ad_type", "296");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "offset", i + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, com.mbridge.msdk.foundation.same.net.h.d.b, strA);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "close_id", closeIds);
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "unit_size", bVar.a() + "");
        com.mbridge.msdk.foundation.same.net.g.b.a(dVar, "refresh_time", bVar.b() + "");
        if (!TextUtils.isEmpty(bVar.c())) {
            com.mbridge.msdk.foundation.same.net.g.b.a(dVar, BidResponsed.KEY_TOKEN, bVar.c() + "");
        }
        return dVar;
    }
}
