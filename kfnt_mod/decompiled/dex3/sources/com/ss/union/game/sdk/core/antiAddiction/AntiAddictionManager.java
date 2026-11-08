package com.ss.union.game.sdk.core.antiAddiction;

import com.ss.union.game.sdk.core.antiAddiction.b.a;
import com.ss.union.game.sdk.core.base.config.ConfigManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AntiAddictionManager {
    private static volatile AntiAddictionManager a;
    private a b = new a();

    private AntiAddictionManager() {
    }

    public static AntiAddictionManager getInstance() {
        if (a == null) {
            synchronized (AntiAddictionManager.class) {
                if (a == null) {
                    a = new AntiAddictionManager();
                }
            }
        }
        return a;
    }

    public void antiAddictionStart() {
        if (ConfigManager.LoginConfig.isNoUserLogin()) {
            this.b.antiAddictionStart();
        }
    }
}
