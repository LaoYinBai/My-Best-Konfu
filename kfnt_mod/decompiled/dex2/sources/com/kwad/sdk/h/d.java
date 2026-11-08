package com.kwad.sdk.h;

import com.kwad.sdk.api.core.fragment.KsFragment;
import com.kwad.sdk.utils.n;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends KsFragment implements com.kwad.sdk.h.kwai.b {
    private final com.kwad.sdk.h.kwai.a a = new com.kwad.sdk.h.kwai.a();
    private boolean b = true;

    public boolean a_() {
        return this.a.a();
    }

    @Override // com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onDestroy() {
        super.onDestroy();
        if (!this.b || getHost() == null) {
            return;
        }
        n.a(this);
    }
}
