package com.ss.union.game.sdk.core.glide.load.model;

import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.Encoder;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ByteBufferEncoder implements Encoder<ByteBuffer> {
    private static final String a = "ByteBufferEncoder";

    @Override // com.ss.union.game.sdk.core.glide.load.Encoder
    public boolean encode(ByteBuffer byteBuffer, File file, Options options) throws Throwable {
        try {
            ByteBufferUtil.toFile(byteBuffer, file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Failed to write data", e);
            }
            return false;
        }
    }
}
