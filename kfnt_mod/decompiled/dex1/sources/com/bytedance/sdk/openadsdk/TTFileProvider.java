package com.bytedance.sdk.openadsdk;

import android.app.Application;
import android.content.Context;
import androidx.core.content.FileProvider;
import com.bytedance.pangle.Zeus;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTFileProvider extends FileProvider {
    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public boolean onCreate() {
        boolean zOnCreate = super.onCreate();
        Zeus.preInit();
        Context context = getContext();
        if (context instanceof Application) {
            Zeus.setAppContext((Application) context);
        }
        return zOnCreate;
    }
}
