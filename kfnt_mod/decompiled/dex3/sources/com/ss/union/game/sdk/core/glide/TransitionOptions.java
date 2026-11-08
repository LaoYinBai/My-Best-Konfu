package com.ss.union.game.sdk.core.glide;

import com.ss.union.game.sdk.core.glide.TransitionOptions;
import com.ss.union.game.sdk.core.glide.request.transition.NoTransition;
import com.ss.union.game.sdk.core.glide.request.transition.TransitionFactory;
import com.ss.union.game.sdk.core.glide.request.transition.ViewAnimationFactory;
import com.ss.union.game.sdk.core.glide.request.transition.ViewPropertyAnimationFactory;
import com.ss.union.game.sdk.core.glide.request.transition.ViewPropertyTransition;
import com.ss.union.game.sdk.core.glide.util.Preconditions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class TransitionOptions<CHILD extends TransitionOptions<CHILD, TranscodeType>, TranscodeType> implements Cloneable {
    private TransitionFactory<? super TranscodeType> a = NoTransition.getFactory();

    private CHILD b() {
        return this;
    }

    public final CHILD dontTransition() {
        return (CHILD) transition(NoTransition.getFactory());
    }

    public final CHILD transition(int i) {
        return (CHILD) transition(new ViewAnimationFactory(i));
    }

    public final CHILD transition(ViewPropertyTransition.Animator animator) {
        return (CHILD) transition(new ViewPropertyAnimationFactory(animator));
    }

    public final CHILD transition(TransitionFactory<? super TranscodeType> transitionFactory) {
        this.a = (TransitionFactory) Preconditions.checkNotNull(transitionFactory);
        return (CHILD) b();
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final CHILD m4clone() {
        try {
            return (CHILD) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    final TransitionFactory<? super TranscodeType> a() {
        return this.a;
    }
}
