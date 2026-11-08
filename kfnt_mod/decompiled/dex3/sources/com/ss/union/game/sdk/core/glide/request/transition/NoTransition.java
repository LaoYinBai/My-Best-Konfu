package com.ss.union.game.sdk.core.glide.request.transition;

import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NoTransition<R> implements Transition<R> {
    static final NoTransition<?> a = new NoTransition<>();
    private static final TransitionFactory<?> b = new NoAnimationFactory();

    @Override // com.ss.union.game.sdk.core.glide.request.transition.Transition
    public boolean transition(Object obj, Transition.ViewAdapter viewAdapter) {
        return false;
    }

    public static class NoAnimationFactory<R> implements TransitionFactory<R> {
        @Override // com.ss.union.game.sdk.core.glide.request.transition.TransitionFactory
        public Transition<R> build(DataSource dataSource, boolean z) {
            return NoTransition.a;
        }
    }

    public static <R> TransitionFactory<R> getFactory() {
        return (TransitionFactory<R>) b;
    }

    public static <R> Transition<R> get() {
        return a;
    }
}
