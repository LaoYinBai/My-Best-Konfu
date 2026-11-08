package com.bytedance.gameprotect;

import androidx.annotation.Keep;
import androidx.annotation.MainThread;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IEmulatorCallback {
    @MainThread
    void OnFailure(String str);

    @MainThread
    void OnSuccess(boolean z, String str);
}
