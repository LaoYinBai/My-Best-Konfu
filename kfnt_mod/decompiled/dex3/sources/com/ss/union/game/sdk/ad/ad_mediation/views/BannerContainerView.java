package com.ss.union.game.sdk.ad.ad_mediation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BannerContainerView extends FrameLayout {
    private int a;
    private int b;
    private a c;

    public interface a {
        void a();
    }

    public BannerContainerView(@NonNull Context context) {
        super(context);
    }

    public BannerContainerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BannerContainerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (getChildCount() > 0 || this.c == null) {
            return;
        }
        this.c.a();
        this.c = null;
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if ((this.a != 0 || this.b != 0) && (layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.topMargin = this.b;
            marginLayoutParams.leftMargin = this.a;
        }
        super.setLayoutParams(layoutParams);
    }

    public BannerContainerView a(int i, int i2) {
        this.a = i;
        this.b = i2;
        return this;
    }

    public BannerContainerView a(a aVar) {
        this.c = aVar;
        return this;
    }
}
