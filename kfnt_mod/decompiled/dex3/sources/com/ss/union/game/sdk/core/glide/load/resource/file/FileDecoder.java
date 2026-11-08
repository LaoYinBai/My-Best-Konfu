package com.ss.union.game.sdk.core.glide.load.resource.file;

import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FileDecoder implements ResourceDecoder<File, File> {
    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(File file, Options options) {
        return true;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public Resource<File> decode(File file, int i, int i2, Options options) {
        return new FileResource(file);
    }
}
