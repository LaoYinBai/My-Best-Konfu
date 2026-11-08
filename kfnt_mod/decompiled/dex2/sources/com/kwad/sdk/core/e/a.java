package com.kwad.sdk.core.e;

import android.view.View;
import androidx.annotation.NonNull;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.reward.a;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a<T extends com.kwad.sdk.reward.a> extends com.kwad.sdk.h.a {
    protected T mCallerContext;
    protected Presenter mPresenter;
    protected View mRootView;

    private void notifyOnCreate() {
        if (this.mCallerContext == null) {
            return;
        }
        Iterator<com.kwad.sdk.reward.a.a> it = this.mCallerContext.a.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    private void notifyOnDestroy() {
        if (this.mCallerContext == null) {
            return;
        }
        Iterator<com.kwad.sdk.reward.a.a> it = this.mCallerContext.a.iterator();
        while (it.hasNext()) {
            it.next().d(this);
        }
    }

    private void notifyOnPause() {
        if (this.mCallerContext == null) {
            return;
        }
        Iterator<com.kwad.sdk.reward.a.a> it = this.mCallerContext.a.iterator();
        while (it.hasNext()) {
            it.next().c(this);
        }
    }

    private void notifyOnResume() {
        if (this.mCallerContext == null) {
            return;
        }
        Iterator<com.kwad.sdk.reward.a.a> it = this.mCallerContext.a.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    protected void onActivityCreated(@NonNull View view) {
        this.mRootView = view;
        this.mCallerContext = (T) onCreateCallerContext();
        if (this.mPresenter == null) {
            this.mPresenter = onCreatePresenter();
            this.mPresenter.c(this.mRootView);
        }
        this.mPresenter.a(this.mCallerContext);
        notifyOnCreate();
    }

    protected abstract T onCreateCallerContext();

    protected abstract Presenter onCreatePresenter();

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onDestroy() {
        notifyOnDestroy();
        if (this.mCallerContext != null) {
            this.mCallerContext.a();
        }
        if (this.mPresenter != null) {
            this.mPresenter.k();
        }
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onPause() {
        notifyOnPause();
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onResume() {
        super.onResume();
        notifyOnResume();
    }
}
