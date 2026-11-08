package com.ss.union.game.sdk.core.base.debug.automatic_detection.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGScreenOrientationFrameLayout extends FrameLayout {
    private a a;

    public interface a {
        void a(Configuration configuration);
    }

    public LGScreenOrientationFrameLayout(Context context) {
        this(context, null);
    }

    public LGScreenOrientationFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LGScreenOrientationFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.a != null) {
            this.a.a(configuration);
        }
    }

    public void setScreenOrientationListener(a aVar) {
        this.a = aVar;
    }
}
