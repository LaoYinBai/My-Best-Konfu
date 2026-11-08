package com.mbridge.msdk.widget.custom.baseview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBImageView extends AppCompatImageView {
    public MBImageView(Context context, AttributeSet attributeSet) {
        super(context);
        setAttributeSet(attributeSet);
    }

    public void setAttributeSet(AttributeSet attributeSet) {
        HashMap<String, com.mbridge.msdk.widget.custom.a.c> mapC = com.mbridge.msdk.widget.custom.a.b.a().c();
        int attributeCount = attributeSet.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            com.mbridge.msdk.widget.custom.a.c cVar = mapC.get(attributeSet.getAttributeName(i));
            if (cVar != null) {
                switch (cVar) {
                    case layout_width:
                        String attributeValue = attributeSet.getAttributeValue(i);
                        getLayoutParams();
                        if (attributeValue.startsWith("f")) {
                            break;
                        } else {
                            attributeValue.startsWith("m");
                            break;
                        }
                    case layout_height:
                        String attributeValue2 = attributeSet.getAttributeValue(i);
                        if (attributeValue2.startsWith("f")) {
                            break;
                        } else {
                            attributeValue2.startsWith("m");
                            break;
                        }
                    case id:
                        String attributeValue3 = attributeSet.getAttributeValue(i);
                        if (attributeValue3.startsWith("@+id/")) {
                            setId(attributeValue3.substring("@+id/".length()).hashCode());
                            break;
                        } else {
                            break;
                        }
                    case src:
                        attributeSet.getAttributeValue(i);
                        break;
                    case background:
                        String attributeValue4 = attributeSet.getAttributeValue(i);
                        if (attributeValue4.startsWith("#")) {
                            setBackgroundColor(com.mbridge.msdk.widget.custom.a.b.a().b(attributeSet.getAttributeValue(i)));
                            break;
                        } else {
                            if (attributeValue4.startsWith("@drawable/")) {
                                attributeValue4 = attributeValue4.substring(10);
                            }
                            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(getContext().getFilesDir().toString() + "/" + attributeValue4 + ".png");
                            if (bitmapDecodeFile != null) {
                                setBackgroundDrawable(new BitmapDrawable(bitmapDecodeFile));
                                break;
                            } else {
                                break;
                            }
                        }
                    case contentDescription:
                        setContentDescription(attributeSet.getAttributeValue(i));
                        break;
                    case visibility:
                        String attributeValue5 = attributeSet.getAttributeValue(i);
                        if (TextUtils.isEmpty(attributeValue5)) {
                            break;
                        } else if (attributeValue5.equals("invisible")) {
                            setVisibility(4);
                            break;
                        } else if (attributeValue5.equalsIgnoreCase("gone")) {
                            setVisibility(8);
                            break;
                        } else if (attributeValue5.equalsIgnoreCase("invisible")) {
                            setVisibility(4);
                            break;
                        } else {
                            break;
                        }
                    case scaleType:
                        if (TextUtils.isEmpty(attributeSet.getAttributeValue(i))) {
                            break;
                        } else {
                            setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                            break;
                        }
                }
            }
        }
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -1);
        }
        HashMap<String, com.mbridge.msdk.widget.custom.a.c> mapC = com.mbridge.msdk.widget.custom.a.b.a().c();
        for (int i = 0; i < 2; i++) {
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
                }
            }
        }
        return layoutParams;
    }
}
