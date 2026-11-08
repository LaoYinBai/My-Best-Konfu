package com.ss.union.game.sdk.feedback.callback;

import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGUserFeedbackCallback {
    void onClose(boolean z);

    void onOpenFail(int i, String str);

    void onOpenSuccess();
}
