package com.ss.union.game.sdk.ad.b;

import com.ss.union.game.sdk.core.base.checker.IChecker;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends IChecker {
    @Override // com.ss.union.game.sdk.core.base.checker.IChecker
    protected String check() {
        return "AdConfigChecker\nsIsPaid : " + com.ss.union.game.sdk.ad.ad_mediation.b.a.b() + "\nsUseTextureView : " + com.ss.union.game.sdk.ad.ad_mediation.b.a.c() + "\nsAllowShowPageWhenScreenLock : " + com.ss.union.game.sdk.ad.ad_mediation.b.a.d() + "\nsAllowShowNotify : " + com.ss.union.game.sdk.ad.ad_mediation.b.a.e() + "\nsData : " + com.ss.union.game.sdk.ad.ad_mediation.b.a.f() + "\nmKeywords : " + com.ss.union.game.sdk.ad.ad_mediation.b.a.g() + "\nsDirectDownloadNetworkType : " + Arrays.toString(com.ss.union.game.sdk.ad.ad_mediation.b.a.h());
    }
}
