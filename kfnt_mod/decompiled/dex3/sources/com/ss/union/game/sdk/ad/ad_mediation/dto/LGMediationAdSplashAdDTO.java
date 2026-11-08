package com.ss.union.game.sdk.ad.ad_mediation.dto;

import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdBaseConfigAdDTO;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGMediationAdSplashAdDTO extends LGMediationAdBaseConfigAdDTO {
    public static final int SPLASH_BUTTON_TYPE_DOWNLOAD_BAR = 2;
    public static final int SPLASH_BUTTON_TYPE_FULL_SCREEN = 1;
    public String defaultAdRitId;
    public boolean preLoad;
    public int splashButtonType;
    public int timeOut;

    public LGMediationAdSplashAdDTO() {
        this.expectedImageSize = new LGMediationAdBaseConfigAdDTO.ExpectedImageSize(DownloadErrorCode.ERROR_TTNET_NOT_MODIFIED, 1920);
        this.defaultAdRitId = "";
        this.timeOut = 3000;
    }
}
