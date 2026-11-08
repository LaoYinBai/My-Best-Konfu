package com.ss.union.game.sdk.account.result;

import android.text.TextUtils;
import androidx.annotation.Keep;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import java.io.Serializable;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGCarrierQueryResult implements Serializable {
    private String mCarrier;
    private String mCarrierProtocol;
    private String mCarrierProtocolUrl;
    private String mCarrierTitle;
    private String mPhone;

    public static LGCarrierQueryResult parse(String str, String str2) {
        LGCarrierQueryResult lGCarrierQueryResult = new LGCarrierQueryResult();
        lGCarrierQueryResult.setPhone(str);
        if (TextUtils.isEmpty(str2)) {
            return lGCarrierQueryResult;
        }
        if (str2.contains("mobile")) {
            lGCarrierQueryResult.mCarrierTitle = ResourceUtils.getString("lg_login_mobile_carrier");
            lGCarrierQueryResult.mCarrierProtocol = ResourceUtils.getString("lg_login_mobile_carrier_terms_of_service");
            lGCarrierQueryResult.mCarrierProtocolUrl = LGAccountConstant.MOBILE_AGREEMENT;
            lGCarrierQueryResult.mCarrier = "mobile";
        } else if (str2.contains("unicom")) {
            lGCarrierQueryResult.mCarrierTitle = ResourceUtils.getString("lg_login_unicom_carrier");
            lGCarrierQueryResult.mCarrierProtocol = ResourceUtils.getString("lg_login_unicom_carrier_terms_of_service");
            lGCarrierQueryResult.mCarrierProtocolUrl = LGAccountConstant.UNICOM_AGREEMENT;
            lGCarrierQueryResult.mCarrier = "unicom";
        } else if (str2.contains(BDAccountPlatformEntity.PLAT_NAME_TELECOM)) {
            lGCarrierQueryResult.mCarrierTitle = ResourceUtils.getString("lg_login_telecom_carrier");
            lGCarrierQueryResult.mCarrierProtocol = ResourceUtils.getString("lg_login_telecom_carrier_terms_of_service");
            lGCarrierQueryResult.mCarrierProtocolUrl = LGAccountConstant.TELECOM_AGREEMENT;
            lGCarrierQueryResult.mCarrier = BDAccountPlatformEntity.PLAT_NAME_TELECOM;
        }
        return lGCarrierQueryResult;
    }

    public String getCarrierTitle() {
        return this.mCarrierTitle;
    }

    public void setCarrierTitle(String str) {
        this.mCarrierTitle = str;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public void setPhone(String str) {
        this.mPhone = str;
    }

    public String getCarrierProtocol() {
        return this.mCarrierProtocol;
    }

    public void setCarrierProtocol(String str) {
        this.mCarrierProtocol = str;
    }

    public String getCarrierProtocolUrl() {
        return this.mCarrierProtocolUrl;
    }

    public void setCarrierProtocolUrl(String str) {
        this.mCarrierProtocolUrl = str;
    }

    public String getCarrier() {
        return this.mCarrier;
    }

    public void setCarrier(String str) {
        this.mCarrier = str;
    }
}
