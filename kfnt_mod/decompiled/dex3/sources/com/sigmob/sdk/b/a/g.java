package com.sigmob.sdk.b.a;

import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g extends e {
    private final long a;

    public g(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.a = j;
    }

    @Override // com.sigmob.sdk.b.a.e
    protected boolean a(File file, long j, int i) {
        return j <= this.a;
    }
}
