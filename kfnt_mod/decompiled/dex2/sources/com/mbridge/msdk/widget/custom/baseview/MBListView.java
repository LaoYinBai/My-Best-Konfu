package com.mbridge.msdk.widget.custom.baseview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBListView extends ListView {
    public MBListView(Context context, AttributeSet attributeSet) {
        super(context);
        setAttributeSet(attributeSet);
        setLayoutParams(generateLayoutParams(attributeSet));
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public AbsListView.LayoutParams generateLayoutParams(AttributeSet attributeSet) throws NumberFormatException {
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) generateDefaultLayoutParams();
        HashMap<String, com.mbridge.msdk.widget.custom.a.c> mapC = com.mbridge.msdk.widget.custom.a.b.a().c();
        int attributeCount = attributeSet.getAttributeCount();
        int iC = 0;
        int iC2 = 0;
        int iC3 = 0;
        int iC4 = 0;
        for (int i = 0; i < attributeCount; i++) {
            com.mbridge.msdk.widget.custom.a.c cVar = mapC.get(attributeSet.getAttributeName(i));
            if (cVar != null) {
                switch (cVar) {
                    case layout_width:
                        String attributeValue = attributeSet.getAttributeValue(i);
                        if (attributeValue.startsWith("f") || attributeValue.startsWith("m")) {
                            layoutParams.width = -1;
                            break;
                        } else if (attributeValue.startsWith("wrap")) {
                            layoutParams.width = -2;
                            break;
                        } else {
                            layoutParams.width = com.mbridge.msdk.widget.custom.a.b.a().c(attributeValue);
                            break;
                        }
                        break;
                    case layout_height:
                        String attributeValue2 = attributeSet.getAttributeValue(i);
                        if (attributeValue2.startsWith("f") || attributeValue2.startsWith("m")) {
                            layoutParams.height = -1;
                            break;
                        } else if (attributeValue2.startsWith("wrap")) {
                            layoutParams.height = -2;
                            break;
                        } else {
                            layoutParams.height = com.mbridge.msdk.widget.custom.a.b.a().c(attributeValue2);
                            break;
                        }
                        break;
                    case fadingEdge:
                        setHorizontalFadingEdgeEnabled(attributeSet.getAttributeBooleanValue(i, false));
                        break;
                    case visibility:
                        String attributeValue3 = attributeSet.getAttributeValue(i);
                        if (TextUtils.isEmpty(attributeValue3)) {
                            break;
                        } else if (attributeValue3.equals("invisible")) {
                            setVisibility(4);
                            break;
                        } else if (attributeValue3.equalsIgnoreCase("gone")) {
                            setVisibility(8);
                            break;
                        } else {
                            break;
                        }
                    case layout_marginTop:
                        iC2 = com.mbridge.msdk.widget.custom.a.b.a().c(attributeSet.getAttributeValue(i));
                        break;
                    case layout_marginLeft:
                        iC = com.mbridge.msdk.widget.custom.a.b.a().c(attributeSet.getAttributeValue(i));
                        break;
                    case layout_marginRight:
                        iC3 = com.mbridge.msdk.widget.custom.a.b.a().c(attributeSet.getAttributeValue(i));
                        break;
                    case layout_marginBottom:
                        iC4 = com.mbridge.msdk.widget.custom.a.b.a().c(attributeSet.getAttributeValue(i));
                        break;
                }
            }
        }
        setPadding(iC, iC2, iC3, iC4);
        return layoutParams;
    }

    public void setAttributeSet(AttributeSet attributeSet) {
        HashMap<String, com.mbridge.msdk.widget.custom.a.c> mapC = com.mbridge.msdk.widget.custom.a.b.a().c();
        int attributeCount = attributeSet.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            com.mbridge.msdk.widget.custom.a.c cVar = mapC.get(attributeSet.getAttributeName(i));
            if (cVar != null) {
                switch (cVar) {
                    case id:
                        String attributeValue = attributeSet.getAttributeValue(i);
                        if (attributeValue.startsWith("@+id/")) {
                            setId(attributeValue.substring("@+id/".length()).hashCode());
                            break;
                        } else {
                            break;
                        }
                    case divider:
                        setDivider(new ColorDrawable(com.mbridge.msdk.widget.custom.a.b.a().b(attributeSet.getAttributeValue(i))));
                        break;
                    case splitMotionEvents:
                        boolean z = !attributeSet.getAttributeValue(i).equals("false");
                        if (Build.VERSION.SDK_INT >= 11) {
                            setMotionEventSplittingEnabled(z);
                            break;
                        } else {
                            break;
                        }
                }
            }
        }
    }
}
