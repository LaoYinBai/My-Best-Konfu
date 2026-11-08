package com.kwad.sdk.feed.widget.base;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.j.d;
import com.kwad.sdk.core.j.f;
import com.kwad.sdk.core.page.widget.a;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.widget.KSFrameLayout;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a extends KSFrameLayout implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener, d {
    private f a;

    @NonNull
    protected AdTemplate b;
    protected AdInfo c;
    protected InterfaceC0062a d;

    @NonNull
    protected Context e;

    /* renamed from: com.kwad.sdk.feed.widget.base.a$a, reason: collision with other inner class name */
    public interface InterfaceC0062a {
        void a();

        void b();

        void c();

        void d();

        void e();
    }

    public a(@NonNull Context context) {
        this(context, null);
    }

    public a(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = context;
        d();
    }

    private void a(ViewGroup viewGroup) {
        com.kwad.sdk.core.page.widget.a aVarB = b(viewGroup);
        if (aVarB == null) {
            aVarB = new com.kwad.sdk.core.page.widget.a(viewGroup.getContext(), viewGroup);
            viewGroup.addView(aVarB);
        }
        aVarB.setViewCallback(new a.InterfaceC0044a() { // from class: com.kwad.sdk.feed.widget.base.a.1
            @Override // com.kwad.sdk.core.page.widget.a.InterfaceC0044a
            public void a() {
            }

            @Override // com.kwad.sdk.core.page.widget.a.InterfaceC0044a
            public void a(View view) {
                a.this.j();
            }

            @Override // com.kwad.sdk.core.page.widget.a.InterfaceC0044a
            public void a(boolean z) {
            }

            @Override // com.kwad.sdk.core.page.widget.a.InterfaceC0044a
            public void b() {
            }
        });
        aVarB.setNeedCheckingShow(true);
    }

    private com.kwad.sdk.core.page.widget.a b(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof com.kwad.sdk.core.page.widget.a) {
                return (com.kwad.sdk.core.page.widget.a) childAt;
            }
        }
        return null;
    }

    private void d() {
        inflate(this.e, getLayoutId(), this);
        setRatio(getHWRatio());
        c();
        this.a = new f(this, 70);
        a((ViewGroup) this);
    }

    @Override // com.kwad.sdk.widget.KSFrameLayout
    protected void a() {
        super.a();
        this.a.a(this);
        this.a.a();
    }

    public void a(@NonNull AdTemplate adTemplate) {
        this.b = adTemplate;
        this.c = com.kwad.sdk.core.response.a.d.j(adTemplate);
    }

    @Override // com.kwad.sdk.widget.KSFrameLayout
    protected void b() {
        super.b();
        this.a.b(this);
        this.a.b();
    }

    protected abstract void c();

    public void e() {
    }

    @Override // com.kwad.sdk.core.j.d
    public void f() {
    }

    protected float getHWRatio() {
        return 0.0f;
    }

    protected abstract int getLayoutId();

    protected void j() {
        if (!this.b.mPvReported && this.d != null) {
            this.d.b();
        }
        com.kwad.sdk.core.report.a.a(this.b, (JSONObject) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void k() {
        com.kwad.sdk.core.report.a.a(this.b, getTouchCoords());
        if (this.d != null) {
            this.d.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void l() {
        if (this.d != null) {
            this.d.a();
        }
    }

    protected void m() {
        com.kwad.sdk.core.report.a.a(this.b);
        if (this.d != null) {
            this.d.c();
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.d != null) {
            this.d.e();
        }
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        if (this.d != null) {
            this.d.d();
        }
    }

    public void setInnerAdInteractionListener(InterfaceC0062a interfaceC0062a) {
        this.d = interfaceC0062a;
    }

    public void setMargin(int i) {
        setPadding(i, i, i, i);
        setBackgroundColor(-1);
    }
}
