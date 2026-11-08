package com.ss.union.game.sdk.core.antiAddiction.bean;

import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.base.callback.GameSDKResult;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGAntiAddictionGlobalResult extends GameSDKResult {
    public static final int ERRNO_TEENAGER_ONLINE_TIME_LIMITED = -5001;
    public boolean autoPopup;
    public int canPlayTime = 0;

    public LGAntiAddictionGlobalResult() {
        this.map.put(-5001, ResourceUtils.getString("lg_tt_ss_anti_addi_real_name_teenager"));
    }
}
