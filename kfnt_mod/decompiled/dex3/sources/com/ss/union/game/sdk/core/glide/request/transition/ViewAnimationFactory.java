package com.ss.union.game.sdk.core.glide.request.transition;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.request.transition.ViewTransition;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ViewAnimationFactory<R> implements TransitionFactory<R> {
    private final ViewTransition.a a;
    private Transition<R> b;

    public ViewAnimationFactory(Animation animation) {
        this(new a(animation));
    }

    public ViewAnimationFactory(int i) {
        this(new b(i));
    }

    ViewAnimationFactory(ViewTransition.a aVar) {
        this.a = aVar;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.transition.TransitionFactory
    public Transition<R> build(DataSource dataSource, boolean z) {
        if (dataSource == DataSource.MEMORY_CACHE || !z) {
            return NoTransition.get();
        }
        if (this.b == null) {
            this.b = new ViewTransition(this.a);
        }
        return this.b;
    }

    private static class a implements ViewTransition.a {
        private final Animation a;

        a(Animation animation) {
            this.a = animation;
        }

        @Override // com.ss.union.game.sdk.core.glide.request.transition.ViewTransition.a
        public Animation a(Context context) {
            return this.a;
        }
    }

    private static class b implements ViewTransition.a {
        private final int a;

        b(int i) {
            this.a = i;
        }

        @Override // com.ss.union.game.sdk.core.glide.request.transition.ViewTransition.a
        public Animation a(Context context) {
            return AnimationUtils.loadAnimation(context, this.a);
        }
    }
}
