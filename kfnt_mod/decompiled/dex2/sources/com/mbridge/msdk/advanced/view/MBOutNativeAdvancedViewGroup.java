package com.mbridge.msdk.advanced.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.mbridge.msdk.advanced.c.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBOutNativeAdvancedViewGroup extends RelativeLayout {
    private c a;

    public void setProvider(c cVar) {
        this.a = cVar;
    }

    public MBOutNativeAdvancedViewGroup(Context context) {
        super(context);
    }

    public MBOutNativeAdvancedViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MBOutNativeAdvancedViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            a(1);
            if (getVisibility() == 0) {
                a(2);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            this.a.e(1);
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            a(2);
            if (getParent() != null) {
                a(1);
                return;
            }
            return;
        }
        if (this.a != null) {
            this.a.e(2);
        }
    }

    private void a(final int i) {
        postDelayed(new Runnable() { // from class: com.mbridge.msdk.advanced.view.MBOutNativeAdvancedViewGroup.1
            @Override // java.lang.Runnable
            public final void run() {
                if (MBOutNativeAdvancedViewGroup.this.a != null) {
                    MBOutNativeAdvancedViewGroup.this.a.d(i);
                }
            }
        }, 200L);
    }
}
