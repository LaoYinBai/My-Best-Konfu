package com.tachikoma.core.layout;

import android.content.Context;
import android.util.AttributeSet;
import com.kwad.yoga.YogaAlign;
import com.kwad.yoga.YogaJustify;
import com.kwad.yoga.YogaNode;
import com.tachikoma.core.yoga.layout.YogaLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKLayout extends YogaLayout {
    public TKLayout(Context context) {
        super(context);
        init();
    }

    public TKLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public TKLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        YogaNode yogaNode = getYogaNode();
        yogaNode.setAlignContent(YogaAlign.CENTER);
        yogaNode.setAlignItems(YogaAlign.CENTER);
        yogaNode.setJustifyContent(YogaJustify.CENTER);
    }
}
