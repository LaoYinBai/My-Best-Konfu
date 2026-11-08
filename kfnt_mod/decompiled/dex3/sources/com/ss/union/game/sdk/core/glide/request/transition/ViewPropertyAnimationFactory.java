package com.ss.union.game.sdk.core.glide.request.transition;

import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.request.transition.ViewPropertyTransition;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ViewPropertyAnimationFactory<R> implements TransitionFactory<R> {
    private final ViewPropertyTransition.Animator a;
    private ViewPropertyTransition<R> b;

    public ViewPropertyAnimationFactory(ViewPropertyTransition.Animator animator) {
        this.a = animator;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.transition.TransitionFactory
    public Transition<R> build(DataSource dataSource, boolean z) {
        if (dataSource == DataSource.MEMORY_CACHE || !z) {
            return NoTransition.get();
        }
        if (this.b == null) {
            this.b = new ViewPropertyTransition<>(this.a);
        }
        return this.b;
    }
}
