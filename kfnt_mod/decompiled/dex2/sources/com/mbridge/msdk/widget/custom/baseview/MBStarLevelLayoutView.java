package com.mbridge.msdk.widget.custom.baseview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mbridge.msdk.foundation.tools.m;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBStarLevelLayoutView extends MBLinearLayout {
    public MBStarLevelLayoutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setRating(int i) {
        removeAllViews();
        if (i == 0) {
            i = 5;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(com.mbridge.msdk.widget.custom.b.a.a(getContext(), 10), com.mbridge.msdk.widget.custom.b.a.a(getContext(), 10));
            if (i2 < i) {
                imageView.setImageResource(m.a(getContext(), "mbridge_download_message_dialog_star_sel", "drawable"));
            } else {
                imageView.setImageResource(m.a(getContext(), "mbridge_download_message_dilaog_star_nor", "drawable"));
            }
            layoutParams.leftMargin = dip2px(getContext(), com.mbridge.msdk.widget.custom.b.a.a(getContext(), 1));
            addView(imageView, layoutParams);
        }
    }

    public static int dip2px(Context context, float f) {
        Resources resources;
        if (context == null || (resources = context.getResources()) == null) {
            return 0;
        }
        return (int) ((f * resources.getDisplayMetrics().density) + 0.5f);
    }
}
