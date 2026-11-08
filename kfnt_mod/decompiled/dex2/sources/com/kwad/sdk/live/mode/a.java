package com.kwad.sdk.live.mode;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static String a(@NonNull LiveInfo liveInfo) {
        return liveInfo.liveStreamId;
    }

    public static long b(@NonNull LiveInfo liveInfo) {
        return liveInfo.user.user_id;
    }
}
