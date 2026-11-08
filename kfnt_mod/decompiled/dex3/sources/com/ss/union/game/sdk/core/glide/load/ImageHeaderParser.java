package com.ss.union.game.sdk.core.glide.load;

import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ImageHeaderParser {
    public static final int UNKNOWN_ORIENTATION = -1;

    int getOrientation(InputStream inputStream, ArrayPool arrayPool) throws IOException;

    int getOrientation(ByteBuffer byteBuffer, ArrayPool arrayPool) throws IOException;

    ImageType getType(InputStream inputStream) throws IOException;

    ImageType getType(ByteBuffer byteBuffer) throws IOException;

    public enum ImageType {
        GIF(true),
        JPEG(false),
        RAW(false),
        PNG_A(true),
        PNG(false),
        WEBP_A(true),
        WEBP(false),
        UNKNOWN(false);

        private final boolean a;

        ImageType(boolean z) {
            this.a = z;
        }

        public boolean hasAlpha() {
            return this.a;
        }
    }
}
