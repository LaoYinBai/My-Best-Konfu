package com.ss.union.game.sdk.core.glide;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum MemoryCategory {
    LOW(0.5f),
    NORMAL(1.0f),
    HIGH(1.5f);

    private final float a;

    MemoryCategory(float f) {
        this.a = f;
    }

    public float getMultiplier() {
        return this.a;
    }
}
