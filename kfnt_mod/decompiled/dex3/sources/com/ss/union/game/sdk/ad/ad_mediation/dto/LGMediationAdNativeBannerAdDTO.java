package com.ss.union.game.sdk.ad.ad_mediation.dto;

import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdBaseConfigAdDTO;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGMediationAdNativeBannerAdDTO extends LGMediationAdBaseConfigAdDTO {
    public boolean allowShowCloseBtn;

    public LGMediationAdNativeBannerAdDTO() {
        this.expectedImageSize = new LGMediationAdBaseConfigAdDTO.ExpectedImageSize(DownloadErrorCode.ERROR_TTNET_NOT_MODIFIED, 360);
    }
}
