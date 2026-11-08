package com.kwad.sdk.api.core;

import androidx.annotation.Nullable;
import com.kwad.sdk.api.loader.Loader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SpeedLimitApiHolder {
    private static volatile SpeedLimitApi instance;

    @Nullable
    public static SpeedLimitApi getInstance() {
        if (instance == null) {
            synchronized (SpeedLimitApiHolder.class) {
                if (instance == null) {
                    instance = (SpeedLimitApi) Loader.get().newInstance(SpeedLimitApi.class);
                }
            }
        }
        return instance;
    }
}
