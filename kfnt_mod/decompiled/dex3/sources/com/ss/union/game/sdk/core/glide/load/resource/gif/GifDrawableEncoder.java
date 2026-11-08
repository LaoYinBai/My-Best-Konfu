package com.ss.union.game.sdk.core.glide.load.resource.gif;

import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.EncodeStrategy;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceEncoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GifDrawableEncoder implements ResourceEncoder<GifDrawable> {
    private static final String a = "GifEncoder";

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceEncoder
    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.SOURCE;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Encoder
    public boolean encode(Resource<GifDrawable> resource, File file, Options options) throws Throwable {
        try {
            ByteBufferUtil.toFile(resource.get().getBuffer(), file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable(a, 5)) {
                Log.w(a, "Failed to encode GIF drawable data", e);
            }
            return false;
        }
    }
}
