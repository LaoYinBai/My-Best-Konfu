package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.text.TextUtils;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class y {
    public static void a(TTBaseAd tTBaseAd, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar, AdSlot adSlot, boolean z) {
        if (tTBaseAd == null || !tTBaseAd.isMultiBiddingAd()) {
            return;
        }
        if (iVar == null) {
            tTBaseAd.setCpm(0.0d);
        }
        String levelTag = tTBaseAd.getLevelTag();
        Map<String, String> mapO = iVar.o();
        if (TextUtils.isEmpty(levelTag)) {
            if (z) {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(iVar, adSlot, tTBaseAd, AdError.ERROR_CODE_NO_LEVEL_TAG, AdError.getMessage(AdError.ERROR_CODE_NO_LEVEL_TAG));
            }
            tTBaseAd.setCpm(0.0d);
        } else {
            if (mapO == null) {
                if (z) {
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(iVar, adSlot, tTBaseAd, AdError.ERROR_CODE_NO_MATCH_LEVEL_TAG, AdError.getMessage(AdError.ERROR_CODE_NO_MATCH_LEVEL_TAG));
                }
                tTBaseAd.setCpm(0.0d);
                return;
            }
            String str = mapO.get(levelTag);
            if (TextUtils.isEmpty(str)) {
                if (z) {
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(iVar, adSlot, tTBaseAd, AdError.ERROR_CODE_NO_MATCH_LEVEL_TAG, AdError.getMessage(AdError.ERROR_CODE_NO_MATCH_LEVEL_TAG));
                }
                tTBaseAd.setCpm(0.0d);
            } else {
                try {
                    tTBaseAd.setCpm(Double.valueOf(str).doubleValue());
                } catch (Exception unused) {
                    tTBaseAd.setCpm(0.0d);
                }
            }
        }
    }
}
