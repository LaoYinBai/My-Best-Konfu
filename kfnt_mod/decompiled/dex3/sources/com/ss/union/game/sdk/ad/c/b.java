package com.ss.union.game.sdk.ad.c;

import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdBaseConfigAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdSplashAdDTO;
import com.ss.union.game.sdk.core.base.cps.CPSHelper;
import com.ss.union.game.sdk.core.base.cps.CpsLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static volatile b a;

    private b() {
    }

    public static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    public <T extends LGMediationAdBaseConfigAdDTO> T a(T t) {
        if (!CPSHelper.isEnable()) {
            return t;
        }
        String adCpsCodeId = CPSHelper.getAdCpsCodeId(t.codeID);
        if (TextUtils.isEmpty(adCpsCodeId)) {
            CpsLog.forceE("找不到对应的广告位id:" + t.codeID);
            return t;
        }
        CpsLog.d("替换广告位id成功: 旧的广告位id:" + t.codeID + ",新的广告位id:" + adCpsCodeId);
        t.codeID = adCpsCodeId;
        if (t instanceof LGMediationAdSplashAdDTO) {
            CpsLog.d("开屏广告，需要替换兜底id");
            LGMediationAdSplashAdDTO lGMediationAdSplashAdDTO = (LGMediationAdSplashAdDTO) t;
            String str = lGMediationAdSplashAdDTO.defaultAdRitId;
            String adCpsCodeId2 = CPSHelper.getAdCpsCodeId(str);
            if (!TextUtils.isEmpty(adCpsCodeId2)) {
                CpsLog.d("替换兜底广告位id成功: 旧的广告位id:" + str + ",新的广告位id:" + adCpsCodeId2);
                lGMediationAdSplashAdDTO.defaultAdRitId = adCpsCodeId2;
            } else {
                CpsLog.d("替换兜底广告位id失败: 旧的兜底广告位id:" + str);
            }
        }
        return t;
    }

    public AdSlot a(AdSlot adSlot) throws IllegalAccessException, IllegalArgumentException {
        if (!CPSHelper.isEnable()) {
            return adSlot;
        }
        String adCpsCodeId = CPSHelper.getAdCpsCodeId(adSlot.getCodeId());
        if (TextUtils.isEmpty(adCpsCodeId)) {
            CpsLog.forceE("找不到对应的广告位id:" + adSlot.getCodeId());
            return adSlot;
        }
        CpsLog.d("开始替换广告位id: 旧的广告位id:" + adSlot.getCodeId() + ",新的广告位id:" + adCpsCodeId);
        a.a(adSlot, adSlot.getCodeId(), adCpsCodeId);
        StringBuilder sb = new StringBuilder();
        sb.append("新的广告位是: ");
        sb.append(adSlot.getCodeId());
        CpsLog.d(sb.toString());
        return adSlot;
    }
}
