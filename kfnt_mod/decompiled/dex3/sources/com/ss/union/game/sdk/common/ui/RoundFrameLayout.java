package com.ss.union.game.sdk.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.ss.union.game.sdk.common.ui.RoundedUI;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RoundFrameLayout extends FrameLayout implements RoundedUI.RoundedView {
    RoundedUI a;

    public RoundFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    public RoundFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public RoundFrameLayout(Context context) {
        super(context);
        a(null);
    }

    private void a(AttributeSet attributeSet) {
        this.a = new RoundedUI(this, attributeSet);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.a.a(canvas);
    }

    @Override // com.ss.union.game.sdk.common.ui.RoundedUI.RoundedView
    public void radius(int i) {
        this.a.a(i);
    }

    @Override // com.ss.union.game.sdk.common.ui.RoundedUI.RoundedView
    public void radius(int i, int i2, int i3, int i4) {
        this.a.a(i, i2, i3, i4);
    }

    @Override // com.ss.union.game.sdk.common.ui.RoundedUI.RoundedView
    public void setCircle(boolean z) {
        this.a.a(z);
    }

    @Override // com.ss.union.game.sdk.common.ui.RoundedUI.RoundedView
    public void drawSuper(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // com.ss.union.game.sdk.common.ui.RoundedUI.RoundedView
    public RoundedUI getRoundedUI() {
        return this.a;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        invalidate();
        return super.invalidateChildInParent(iArr, rect);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void childDrawableStateChanged(View view) {
        invalidate();
        super.childDrawableStateChanged(view);
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        invalidate();
    }
}
