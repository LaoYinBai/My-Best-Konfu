package com.mbridge.msdk.mbbid.out;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.mbbid.common.a;
import com.mbridge.msdk.mbbid.common.a.b;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BidManager {
    private b a;
    private BidListennning b;
    private boolean c;

    public BidManager(String str, String str2) {
        this(str, str2, PushConstants.PUSH_TYPE_NOTIFY);
    }

    public BidManager(String str, String str2, String str3) {
        this.c = false;
        this.a = new b(str, str2, str3);
    }

    public <T extends CommonBidRequestParams> BidManager(T t) {
        this(t == null ? "" : t.getmPlacementId(), t == null ? "" : t.getmUnitId(), t == null ? "" : t.getmFloorPrice());
        if (t instanceof BannerBidRequestParams) {
            BannerBidRequestParams bannerBidRequestParams = (BannerBidRequestParams) t;
            this.a.a(bannerBidRequestParams.getHeight());
            this.a.b(bannerBidRequestParams.getWidth());
            this.a.b(296);
            if (t instanceof SplashBidRequestParams) {
                SplashBidRequestParams splashBidRequestParams = (SplashBidRequestParams) t;
                this.a.a(splashBidRequestParams.a());
                this.a.a(splashBidRequestParams.getOrientation());
                this.a.b(297);
                return;
            }
            return;
        }
        if (t instanceof AdvancedNativeBidRequestParams) {
            AdvancedNativeBidRequestParams advancedNativeBidRequestParams = (AdvancedNativeBidRequestParams) t;
            this.a.a(advancedNativeBidRequestParams.getHeight());
            this.a.b(advancedNativeBidRequestParams.getWidth());
            this.a.b(298);
        }
    }

    public void setRewardPlus(boolean z) {
        this.c = z;
    }

    public void bid() {
        if (this.a != null) {
            this.a.b(this.c);
        } else if (this.b != null) {
            this.b.onFailed("you need init the class :BidManager");
        }
    }

    public static String getBuyerUid(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return a.a(context, str);
    }

    public void setBidListener(BidListennning bidListennning) {
        this.b = bidListennning;
        if (this.a != null) {
            this.a.a(bidListennning);
        }
    }

    public static String getBuyerUid(Context context) {
        return a.a(context, "");
    }
}
