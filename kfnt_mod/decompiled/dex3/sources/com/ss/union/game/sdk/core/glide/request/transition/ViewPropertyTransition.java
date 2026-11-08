package com.ss.union.game.sdk.core.glide.request.transition;

import android.view.View;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ViewPropertyTransition<R> implements Transition<R> {
    private final Animator a;

    public interface Animator {
        void animate(View view);
    }

    public ViewPropertyTransition(Animator animator) {
        this.a = animator;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.transition.Transition
    public boolean transition(R r, Transition.ViewAdapter viewAdapter) {
        if (viewAdapter.getView() == null) {
            return false;
        }
        this.a.animate(viewAdapter.getView());
        return false;
    }
}
