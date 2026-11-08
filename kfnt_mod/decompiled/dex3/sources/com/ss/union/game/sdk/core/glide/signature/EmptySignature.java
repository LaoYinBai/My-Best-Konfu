package com.ss.union.game.sdk.core.glide.signature;

import com.ss.union.game.sdk.core.glide.load.Key;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class EmptySignature implements Key {
    private static final EmptySignature a = new EmptySignature();

    public String toString() {
        return "EmptySignature";
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    public static EmptySignature obtain() {
        return a;
    }

    private EmptySignature() {
    }
}
