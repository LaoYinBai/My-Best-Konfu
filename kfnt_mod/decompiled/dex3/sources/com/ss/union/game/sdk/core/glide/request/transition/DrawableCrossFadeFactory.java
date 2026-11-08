package com.ss.union.game.sdk.core.glide.request.transition;

import android.graphics.drawable.Drawable;
import com.ss.union.game.sdk.core.glide.load.DataSource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DrawableCrossFadeFactory implements TransitionFactory<Drawable> {
    private final int a;
    private final boolean b;
    private DrawableCrossFadeTransition c;

    protected DrawableCrossFadeFactory(int i, boolean z) {
        this.a = i;
        this.b = z;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.transition.TransitionFactory
    public Transition<Drawable> build(DataSource dataSource, boolean z) {
        return dataSource == DataSource.MEMORY_CACHE ? NoTransition.get() : a();
    }

    private Transition<Drawable> a() {
        if (this.c == null) {
            this.c = new DrawableCrossFadeTransition(this.a, this.b);
        }
        return this.c;
    }

    public static class Builder {
        private static final int a = 300;
        private final int b;
        private boolean c;

        public Builder() {
            this(300);
        }

        public Builder(int i) {
            this.b = i;
        }

        public Builder setCrossFadeEnabled(boolean z) {
            this.c = z;
            return this;
        }

        public DrawableCrossFadeFactory build() {
            return new DrawableCrossFadeFactory(this.b, this.c);
        }
    }
}
