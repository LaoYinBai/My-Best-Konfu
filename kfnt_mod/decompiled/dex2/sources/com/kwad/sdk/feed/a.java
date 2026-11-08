package com.kwad.sdk.feed;

import android.content.Context;
import com.kwad.sdk.feed.widget.c;
import com.kwad.sdk.feed.widget.d;
import com.kwad.sdk.feed.widget.e;
import com.kwad.sdk.feed.widget.f;
import com.kwad.sdk.feed.widget.g;
import com.kwad.sdk.feed.widget.h;
import com.kwad.sdk.feed.widget.i;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static com.kwad.sdk.feed.widget.base.a a(Context context, FeedType feedType) {
        switch (feedType) {
            case FEED_TYPE_TEXT_IMMERSE:
                return new g(context);
            case FEED_TYPE_TEXT_ABOVE:
                return new c(context);
            case FEED_TYPE_TEXT_BELOW:
                return new e(context);
            case FEED_TYPE_TEXT_LEFT:
                return new h(context);
            case FEED_TYPE_TEXT_RIGHT:
                return new i(context);
            case FEED_TYPE_TEXT_ABOVE_GROUP:
            case FEED_TYPE_UNKNOWN:
                com.kwad.sdk.core.d.a.e("KSFeedFactory", "getSingleImageView type is unknown:" + feedType);
                return null;
            default:
                return null;
        }
    }

    public static com.kwad.sdk.feed.widget.base.a a(Context context, FeedType feedType, int i) {
        if (feedType == FeedType.FEED_TYPE_TEXT_NEW) {
            return i == 1 ? new f(context) : new e(context);
        }
        switch (i) {
            case 1:
                return c(context, feedType);
            case 2:
                return a(context, feedType);
            case 3:
                return b(context, feedType);
            default:
                com.kwad.sdk.core.d.a.e("KSFeedFactory", "getNewFeedView materialType is unknown");
                return null;
        }
    }

    private static com.kwad.sdk.feed.widget.base.a b(Context context, FeedType feedType) {
        switch (feedType) {
            case FEED_TYPE_TEXT_IMMERSE:
                return new g(context);
            case FEED_TYPE_TEXT_ABOVE:
                return new c(context);
            case FEED_TYPE_TEXT_BELOW:
                return new e(context);
            case FEED_TYPE_TEXT_LEFT:
                return new h(context);
            case FEED_TYPE_TEXT_RIGHT:
                return new i(context);
            case FEED_TYPE_TEXT_ABOVE_GROUP:
                return new com.kwad.sdk.feed.widget.b(context);
            case FEED_TYPE_UNKNOWN:
                com.kwad.sdk.core.d.a.e("KSFeedFactory", "getVideoView type is unknown" + feedType);
                return null;
            default:
                return null;
        }
    }

    private static com.kwad.sdk.feed.widget.base.a c(Context context, FeedType feedType) {
        switch (feedType) {
            case FEED_TYPE_TEXT_ABOVE:
                return new d(context);
            case FEED_TYPE_TEXT_BELOW:
                return new f(context);
            default:
                com.kwad.sdk.core.d.a.e("KSFeedFactory", "getVideoView type is unknown:" + feedType);
                return null;
        }
    }
}
