package com.ss.union.game.sdk.core.init.b;

import android.text.TextUtils;
import android.text.format.DateUtils;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.init.bean.GameSDKOption;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import com.ss.union.game.sdk.core.upgrade.fragment.AppUpgradeFragment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "AppUpgradeInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        a(GameOptionConfig.GameOption.AppUpgrade.mAppUpgradeConfig);
    }

    private void a(GameSDKOption.d dVar) {
        if (dVar != null && !TextUtils.isEmpty(dVar.f)) {
            long j = SPUtils.getInstance().getLong("key_app_upgrade_tip_time", -1L);
            if (dVar.g || j < 0 || !DateUtils.isToday(j)) {
                SPUtils.getInstance().put("key_app_upgrade_tip_time", System.currentTimeMillis());
                com.ss.union.game.sdk.core.upgrade.b.a.a();
                AppUpgradeFragment.a(dVar.e, dVar.c, dVar.f, dVar.d, dVar.g, new com.ss.union.game.sdk.core.upgrade.a.a() { // from class: com.ss.union.game.sdk.core.init.b.f.1
                    @Override // com.ss.union.game.sdk.core.upgrade.a.a
                    public void a() {
                        f.this.finish();
                    }

                    @Override // com.ss.union.game.sdk.core.upgrade.a.a
                    public void b() {
                        f.this.finish();
                    }
                });
                return;
            }
            finish();
            return;
        }
        finish();
    }
}
