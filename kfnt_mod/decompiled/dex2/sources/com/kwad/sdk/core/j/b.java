package com.kwad.sdk.core.j;

import android.view.View;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.core.fragment.KsFragment;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends a {
    private final AtomicBoolean b;
    private KsFragment c;
    private String d;

    public b(@NonNull KsFragment ksFragment, @NonNull View view, int i) {
        super(view, i);
        this.b = new AtomicBoolean(false);
        this.c = ksFragment;
    }

    private boolean a(@NonNull KsFragment ksFragment) {
        return (ksFragment.isResumed() && !ksFragment.isAllFragmentIsHidden() && ksFragment.isVisible()) ? false : true;
    }

    public void a(boolean z) {
    }

    @Override // com.kwad.sdk.core.j.a
    public boolean c() {
        if (this.c == null) {
            return false;
        }
        if (a(this.c)) {
            this.d = "message fragment";
            return false;
        }
        this.d = "message view";
        return d();
    }

    @Override // com.kwad.sdk.core.j.a
    public void g() {
        super.g();
        this.c = null;
    }

    public void h() {
    }

    public void i() {
        com.kwad.sdk.core.d.a.c("FragmentPageVisibleHelper", "onFragmentPause");
        this.d = "onFragmentPause";
        f();
    }
}
