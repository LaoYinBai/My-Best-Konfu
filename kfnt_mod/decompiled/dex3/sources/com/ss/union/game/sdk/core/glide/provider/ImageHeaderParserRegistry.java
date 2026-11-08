package com.ss.union.game.sdk.core.glide.provider;

import com.ss.union.game.sdk.core.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ImageHeaderParserRegistry {
    private final List<ImageHeaderParser> a = new ArrayList();

    public synchronized List<ImageHeaderParser> getParsers() {
        return this.a;
    }

    public synchronized void add(ImageHeaderParser imageHeaderParser) {
        this.a.add(imageHeaderParser);
    }
}
