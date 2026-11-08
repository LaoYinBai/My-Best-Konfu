package com.ss.union.game.sdk.core.glide.load.data.mediastore;

import android.net.Uri;
import com.sigmob.sdk.common.mta.PointCategory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class MediaStoreUtil {
    private static final int a = 512;
    private static final int b = 384;

    public static boolean isThumbnailSize(int i, int i2) {
        return i != Integer.MIN_VALUE && i2 != Integer.MIN_VALUE && i <= 512 && i2 <= b;
    }

    private MediaStoreUtil() {
    }

    public static boolean isMediaStoreUri(Uri uri) {
        return uri != null && com.umeng.commonsdk.framework.c.a.equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    private static boolean a(Uri uri) {
        return uri.getPathSegments().contains(PointCategory.VIDEO);
    }

    public static boolean isMediaStoreVideoUri(Uri uri) {
        return isMediaStoreUri(uri) && a(uri);
    }

    public static boolean isMediaStoreImageUri(Uri uri) {
        return isMediaStoreUri(uri) && !a(uri);
    }
}
