package com.bytedance.msdk.adapter.baidu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class VisibleStateFrameLayout extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private VisibilityChanged f3965a;

    public interface VisibilityChanged {
        void callback(boolean z);
    }

    public VisibleStateFrameLayout(@NonNull Context context) {
        super(context);
    }

    public VisibleStateFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VisibleStateFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        VisibilityChanged visibilityChanged = this.f3965a;
        if (visibilityChanged == null || view != this) {
            return;
        }
        visibilityChanged.callback(i == 0);
    }

    public void setVisibilityChanged(VisibilityChanged visibilityChanged) {
        this.f3965a = visibilityChanged;
    }
}
