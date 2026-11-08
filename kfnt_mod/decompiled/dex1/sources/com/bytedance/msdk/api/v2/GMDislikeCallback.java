package com.bytedance.msdk.api.v2;

import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface GMDislikeCallback {
    void onCancel();

    void onRefuse();

    void onSelected(int i, @Nullable String str);

    void onShow();
}
