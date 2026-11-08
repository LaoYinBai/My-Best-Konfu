package com.tachikoma.core.config;

import com.tachikoma.core.Tachikoma;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BlockOptConfig {
    private static final String BLOCK_OPT_ENABLE = "blockOptEnable";
    private static Boolean sBlockTrackEnable;

    public static boolean blockOpt() {
        Boolean boolValueOf;
        if (sBlockTrackEnable == null) {
            Map<String, Object> config = Tachikoma.getInstance().getConfig();
            if (config == null || !config.containsKey(BLOCK_OPT_ENABLE)) {
                boolValueOf = Boolean.FALSE;
            } else {
                boolValueOf = Boolean.valueOf(config.get(BLOCK_OPT_ENABLE) == Boolean.TRUE);
            }
            sBlockTrackEnable = boolValueOf;
        }
        return sBlockTrackEnable.booleanValue();
    }
}
