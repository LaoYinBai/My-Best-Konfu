package com.ss.union.game.sdk.core.glide.util.pool;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class StateVerifier {
    private static final boolean a = false;

    abstract void a(boolean z);

    public abstract void throwIfRecycled();

    public static StateVerifier newInstance() {
        return new b();
    }

    private StateVerifier() {
    }

    private static class b extends StateVerifier {
        private volatile boolean a;

        b() {
            super();
        }

        @Override // com.ss.union.game.sdk.core.glide.util.pool.StateVerifier
        public void throwIfRecycled() {
            if (this.a) {
                throw new IllegalStateException("Already released");
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.util.pool.StateVerifier
        public void a(boolean z) {
            this.a = z;
        }
    }

    private static class a extends StateVerifier {
        private volatile RuntimeException a;

        a() {
            super();
        }

        @Override // com.ss.union.game.sdk.core.glide.util.pool.StateVerifier
        public void throwIfRecycled() {
            if (this.a != null) {
                throw new IllegalStateException("Already released", this.a);
            }
        }

        @Override // com.ss.union.game.sdk.core.glide.util.pool.StateVerifier
        void a(boolean z) {
            if (z) {
                this.a = new RuntimeException("Released");
            } else {
                this.a = null;
            }
        }
    }
}
