package com.ss.union.game.sdk.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import androidx.annotation.Keep;
import com.ss.union.game.sdk.core.glide.request.target.Target;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGGridView extends GridView {
    public LGGridView(Context context) {
        super(context);
    }

    public LGGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LGGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        int iMakeMeasureSpec;
        if (!isInEditMode()) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(536870911, Target.SIZE_ORIGINAL);
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(360, Target.SIZE_ORIGINAL);
        }
        super.onMeasure(i, iMakeMeasureSpec);
    }
}
