package com.ss.union.game.sdk.core.glide.request.transition;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ViewTransition<R> implements Transition<R> {
    private final a a;

    interface a {
        Animation a(Context context);
    }

    ViewTransition(a aVar) {
        this.a = aVar;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.transition.Transition
    public boolean transition(R r, Transition.ViewAdapter viewAdapter) {
        View view = viewAdapter.getView();
        if (view == null) {
            return false;
        }
        view.clearAnimation();
        view.startAnimation(this.a.a(view.getContext()));
        return false;
    }
}
