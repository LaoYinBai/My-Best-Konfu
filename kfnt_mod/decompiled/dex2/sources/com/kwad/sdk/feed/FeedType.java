package com.kwad.sdk.feed;

import androidx.annotation.NonNull;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdTemplate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum FeedType {
    FEED_TYPE_UNKNOWN(0),
    FEED_TYPE_TEXT_IMMERSE(1),
    FEED_TYPE_TEXT_LEFT(2),
    FEED_TYPE_TEXT_RIGHT(3),
    FEED_TYPE_TEXT_ABOVE(4),
    FEED_TYPE_TEXT_BELOW(5),
    FEED_TYPE_TEXT_ABOVE_GROUP(6),
    FEED_TYPE_TEXT_NEW(7),
    FEED_TYPE_CONTENT_11(11),
    FEED_TYPE_CONTENT_12(12),
    FEED_TYPE_CONTENT_13(13);

    private int type;

    FeedType(int i) {
        this.type = i;
    }

    public static boolean checkTypeValid(@NonNull AdTemplate adTemplate) {
        int iR = com.kwad.sdk.core.response.a.a.R(d.j(adTemplate));
        if (adTemplate.type > FEED_TYPE_TEXT_ABOVE_GROUP.type) {
            return true;
        }
        FeedType feedTypeFromInt = fromInt(adTemplate.type);
        switch (iR) {
            case 1:
                return feedTypeFromInt == FEED_TYPE_TEXT_ABOVE || feedTypeFromInt == FEED_TYPE_TEXT_BELOW;
            case 2:
                return (feedTypeFromInt == FEED_TYPE_UNKNOWN || feedTypeFromInt == FEED_TYPE_TEXT_ABOVE_GROUP) ? false : true;
            case 3:
                return feedTypeFromInt != FEED_TYPE_UNKNOWN;
            default:
                return false;
        }
    }

    @NonNull
    public static FeedType fromInt(int i) {
        for (FeedType feedType : values()) {
            if (feedType.type == i) {
                return feedType;
            }
        }
        return isH5Type(i) ? FEED_TYPE_TEXT_NEW : FEED_TYPE_UNKNOWN;
    }

    private static boolean isH5Type(int i) {
        return i == 7 || i == 8 || i >= 2000;
    }

    public int getType() {
        return this.type;
    }
}
