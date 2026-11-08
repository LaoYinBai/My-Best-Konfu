package com.kwad.sdk.core.response.a;

import androidx.annotation.NonNull;
import com.kwad.sdk.core.response.model.NewsInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    public static long a(@NonNull NewsInfo newsInfo) {
        return newsInfo.photoId;
    }

    public static int b(@NonNull NewsInfo newsInfo) {
        return newsInfo.contentSourceType;
    }

    public static int c(@NonNull NewsInfo newsInfo) {
        if (d(newsInfo)) {
            return 1;
        }
        if (e(newsInfo)) {
            return 3;
        }
        return f(newsInfo) ? 2 : 0;
    }

    public static boolean d(@NonNull NewsInfo newsInfo) {
        return newsInfo.thumbnailInfo == null || newsInfo.thumbnailInfo.isEmpty();
    }

    public static boolean e(@NonNull NewsInfo newsInfo) {
        return newsInfo.thumbnailInfo != null && newsInfo.thumbnailInfo.size() > 0 && newsInfo.thumbnailInfo.size() < 3;
    }

    public static boolean f(@NonNull NewsInfo newsInfo) {
        return newsInfo.thumbnailInfo != null && newsInfo.thumbnailInfo.size() >= 3;
    }

    public static String g(@NonNull NewsInfo newsInfo) {
        return newsInfo.authorInfo.authorIcon;
    }
}
