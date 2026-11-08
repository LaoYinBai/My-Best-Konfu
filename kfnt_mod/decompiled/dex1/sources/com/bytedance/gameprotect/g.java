package com.bytedance.gameprotect;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class g extends b {
    public g(@NonNull Context context, @NonNull UserConfig userConfig) {
        super(context, userConfig);
    }

    @Override // com.bytedance.gameprotect.b
    public void a() {
    }

    @Override // com.bytedance.gameprotect.b
    public void a(Context context) throws PackageManager.NameNotFoundException {
        s.a(context);
        k.a(context);
        i.a(context);
        a.a(context);
        ContentVerifier.getInstance().initContentVerifier(context, this.f3864a.appId);
    }

    @Override // com.bytedance.gameprotect.b
    public void a(@NonNull UserConfig userConfig) {
        IlIlllIiI.a(userConfig.appId, userConfig.productFlavor, userConfig.region, userConfig.isSandbox, false, userConfig.accountless);
    }
}
