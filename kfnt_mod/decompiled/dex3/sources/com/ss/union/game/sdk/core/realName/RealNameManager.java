package com.ss.union.game.sdk.core.realName;

import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import com.ss.union.game.sdk.core.realName.callback.CheckDeviceRealNameCallback;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.d.a;
import com.ss.union.game.sdk.core.realName.fragment.AwardRealNameFragment;
import com.ss.union.game.sdk.core.realName.fragment.NormalRealNameFragment;
import com.ss.union.game.sdk.core.realName.fragment.RealNameFragment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RealNameManager {
    public static void checkDeviceRealName(LGRealNameCallback lGRealNameCallback) {
        a.a(lGRealNameCallback);
    }

    public static void checkDeviceRealName(CheckDeviceRealNameCallback checkDeviceRealNameCallback) {
        a.a(checkDeviceRealNameCallback);
    }

    public static void showRealNameWindow(int i, LGRealNameCallback lGRealNameCallback) {
        new OperationBuilder(createRealNameFragment(i, true, lGRealNameCallback)).show();
    }

    public static RealNameFragment createRealNameFragment(int i, boolean z, LGRealNameCallback lGRealNameCallback) {
        if (GameOptionConfig.GameOption.IdentifyAward.awardEnable()) {
            return AwardRealNameFragment.a(i, z, GameOptionConfig.GameOption.IdentifyAward.awardName(), GameOptionConfig.GameOption.IdentifyAward.awardIcon(), GameOptionConfig.GameOption.IdentifyAward.awardCustomInfo(), lGRealNameCallback);
        }
        return NormalRealNameFragment.a(i, z, lGRealNameCallback);
    }
}
