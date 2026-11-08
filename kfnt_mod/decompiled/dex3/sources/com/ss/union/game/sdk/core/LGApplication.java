package com.ss.union.game.sdk.core;

import android.app.Application;
import com.ss.union.game.sdk.core.base.component.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        d.a(this);
    }
}
