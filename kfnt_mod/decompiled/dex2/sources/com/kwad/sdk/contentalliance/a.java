package com.kwad.sdk.contentalliance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.e.b;
import com.kwad.sdk.h.d;
import com.kwad.sdk.mvp.Presenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a<T extends com.kwad.sdk.core.e.b> extends d {
    protected Presenter a;
    protected T b;
    protected ViewGroup c;

    protected void a() {
        if (this.a != null) {
            this.a.k();
        }
        this.c = null;
    }

    protected abstract int b();

    protected abstract T c();

    @NonNull
    public abstract Presenter d();

    @Override // com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.b = (T) c();
        if (this.a == null) {
            this.a = d();
            this.a.c(this.c);
        }
        this.a.a(this.b);
    }

    @Override // com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.c == null) {
            this.c = (ViewGroup) layoutInflater.inflate(b(), viewGroup, false);
        }
        return this.c;
    }

    @Override // com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onDestroyView() {
        super.onDestroyView();
        if (this.b != null) {
            this.b.a();
        }
        a();
    }
}
