package com.ss.union.game.sdk.core.glide.request.target;

import com.ss.union.game.sdk.core.glide.util.Util;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class SimpleTarget<Z> extends BaseTarget<Z> {
    private final int a;
    private final int b;

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public void removeCallback(SizeReadyCallback sizeReadyCallback) {
    }

    public SimpleTarget() {
        this(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    public SimpleTarget(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final void getSize(SizeReadyCallback sizeReadyCallback) {
        if (!Util.isValidDimensions(this.a, this.b)) {
            throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + this.a + " and height: " + this.b + ", either provide dimensions in the constructor or call override()");
        }
        sizeReadyCallback.onSizeReady(this.a, this.b);
    }
}
