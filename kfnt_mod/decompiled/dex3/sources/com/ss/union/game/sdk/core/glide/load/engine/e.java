package com.ss.union.game.sdk.core.glide.load.engine;

import com.ss.union.game.sdk.core.glide.load.Encoder;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class e<DataType> implements DiskCache.Writer {
    private final Encoder<DataType> a;
    private final DataType b;
    private final Options c;

    e(Encoder<DataType> encoder, DataType datatype, Options options) {
        this.a = encoder;
        this.b = datatype;
        this.c = options;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache.Writer
    public boolean write(File file) {
        return this.a.encode(this.b, file, this.c);
    }
}
