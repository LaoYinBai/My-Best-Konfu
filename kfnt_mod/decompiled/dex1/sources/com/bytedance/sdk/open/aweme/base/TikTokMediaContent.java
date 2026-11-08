package com.bytedance.sdk.open.aweme.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TikTokMediaContent {
    private static final String TAG = "AWEME.SDK.TikTokMediaContent";
    public IMediaObject mMediaObject;

    public TikTokMediaContent() {
    }

    public TikTokMediaContent(IMediaObject iMediaObject) {
        this.mMediaObject = iMediaObject;
    }

    public final int getType() {
        if (this.mMediaObject == null) {
            return 0;
        }
        return this.mMediaObject.type();
    }

    public final boolean checkArgs() {
        return this.mMediaObject.checkArgs();
    }

    public static class Builder {
        public static final String KEY_IDENTIFIER = "_dyobject_identifier_";

        public static Bundle toBundle(TikTokMediaContent tikTokMediaContent, boolean z) {
            Bundle bundle = new Bundle();
            if (tikTokMediaContent.mMediaObject != null) {
                String name = tikTokMediaContent.mMediaObject.getClass().getName();
                if (name.contains("sdk")) {
                    name = name.replace("sdk", "sdk.account");
                }
                if (z) {
                    name = name.replace("TikTok", "DY");
                }
                bundle.putString(KEY_IDENTIFIER, name);
                tikTokMediaContent.mMediaObject.serialize(bundle);
            }
            return bundle;
        }

        @SuppressLint({"LongLogTag"})
        public static TikTokMediaContent fromBundle(Bundle bundle) {
            TikTokMediaContent tikTokMediaContent = new TikTokMediaContent();
            String string = bundle.getString(KEY_IDENTIFIER);
            if (string == null || string.length() <= 0) {
                return tikTokMediaContent;
            }
            try {
                if (string.contains("sdk")) {
                    string = string.replace("sdk", "sdk.account");
                }
                tikTokMediaContent.mMediaObject = (IMediaObject) Class.forName(string).newInstance();
                tikTokMediaContent.mMediaObject.unserialize(bundle);
                return tikTokMediaContent;
            } catch (Exception e) {
                Log.e(TikTokMediaContent.TAG, "get media object from bundle failed: unknown ident " + string + ", ex = " + e.getMessage());
                return tikTokMediaContent;
            }
        }
    }
}
