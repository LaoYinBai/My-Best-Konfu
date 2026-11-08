package com.sigmob.windad.natives;

import com.sigmob.windad.WindAdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface NativeADEventListener {
    void onADClicked();

    void onADError(WindAdError windAdError);

    void onADExposed();

    void onADStatusChanged(String str);
}
