package com.ss.union.game.sdk.feedback.module;

import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGFeedbackStatus {
    public int unreadReplyCount;
    public int unreadThreadCount;

    public LGFeedbackStatus(int i, int i2) {
        this.unreadReplyCount = i2;
        this.unreadThreadCount = i;
    }

    public String toString() {
        return "LGFeedbackStatus{unreadThreadCount=" + this.unreadThreadCount + ", unreadReplyCount=" + this.unreadReplyCount + '}';
    }
}
