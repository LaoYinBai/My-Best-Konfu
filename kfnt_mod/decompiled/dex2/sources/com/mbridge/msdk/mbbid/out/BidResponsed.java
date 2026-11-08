package com.mbridge.msdk.mbbid.out;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.click.b;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.system.NoProGuard;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BidResponsed implements NoProGuard {
    public static final String KEY_BID_ID = "bid";
    public static final String KEY_CUR = "cur";
    public static final String KEY_LN = "ln";
    public static final String KEY_PRICE = "price";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_WN = "wn";
    private String bidId;
    private String bidToken;
    private String cur;
    protected String ln;
    private String price;
    protected String wn;

    public String getBidId() {
        return this.bidId;
    }

    protected void setBidId(String str) {
        this.bidId = str;
    }

    public String getPrice() {
        return this.price;
    }

    protected void setPrice(String str) {
        this.price = str;
    }

    public String getBidToken() {
        return this.bidToken;
    }

    protected void setBidToken(String str) {
        this.bidToken = str;
    }

    public String getCur() {
        return this.cur;
    }

    protected void setCur(String str) {
        this.cur = str;
    }

    public String getLn() {
        return this.ln;
    }

    public String getWn() {
        return this.wn;
    }

    public void sendLossNotice(Context context, BidLossCode bidLossCode) {
        if (TextUtils.isEmpty(this.ln) || context == null || bidLossCode == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(this.ln);
        sb.append(this.ln.contains("?") ? ContainerUtils.FIELD_DELIMITER : "?");
        sb.append("reason=");
        sb.append(bidLossCode.getCurrentCode());
        b.a(context, (CampaignEx) null, "", sb.toString(), false, false);
    }

    public void sendWinNotice(Context context) {
        if (TextUtils.isEmpty(this.wn) || context == null) {
            return;
        }
        b.a(context, (CampaignEx) null, "", this.wn, false, false);
    }
}
