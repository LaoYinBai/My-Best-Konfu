package com.ss.union.game.sdk.core.base.debug;

import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.base.component.TTAdComponent;
import com.ss.union.game.sdk.core.base.debug.sdkVersion.SdkVersionFragment;
import com.ss.union.game.sdk.core.debug.IDebugService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements IDebugService {
    public static IDebugService a() {
        return C0067a.a;
    }

    /* renamed from: com.ss.union.game.sdk.core.base.debug.a$a, reason: collision with other inner class name */
    private static class C0067a {
        private static final IDebugService a = new a();

        private C0067a() {
        }
    }

    @Override // com.ss.union.game.sdk.core.debug.IDebugService
    public void showSdkVersion() {
        if (LGSDKCore.isSdkInitSuccess()) {
            SdkVersionFragment.a();
        }
    }

    @Override // com.ss.union.game.sdk.core.debug.IDebugService
    public String getSdkVersion() {
        return "2333 - " + TTAdComponent.getSDKVersion();
    }

    @Override // com.ss.union.game.sdk.core.debug.IDebugService
    public void playBackgroundMusic() {
        com.ss.union.game.sdk.core.base.debug.a.a.a().b();
    }

    @Override // com.ss.union.game.sdk.core.debug.IDebugService
    public void stopBackgroundMusic() {
        com.ss.union.game.sdk.core.base.debug.a.a.a().c();
    }
}
