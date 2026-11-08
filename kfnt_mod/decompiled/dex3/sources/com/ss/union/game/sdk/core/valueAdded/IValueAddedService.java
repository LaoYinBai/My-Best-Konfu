package com.ss.union.game.sdk.core.valueAdded;

import com.ss.union.game.sdk.core.valueAdded.callback.IDeeplinkInnerCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IValueAddedService {
    void registerDeeplinkInnerCallback(IDeeplinkInnerCallback iDeeplinkInnerCallback);

    void startBrowser(String str);

    int verifySensitiveWords(String str);
}
