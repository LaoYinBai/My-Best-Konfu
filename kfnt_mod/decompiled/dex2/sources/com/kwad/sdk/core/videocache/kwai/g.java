package com.kwad.sdk.core.videocache.kwai;

import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends e {
    private final long a;

    public g(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.a = j;
    }

    @Override // com.kwad.sdk.core.videocache.kwai.e
    protected boolean a(File file, long j, int i) {
        return j <= this.a;
    }
}
