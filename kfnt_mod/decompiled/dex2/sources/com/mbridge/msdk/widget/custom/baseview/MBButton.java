package com.mbridge.msdk.widget.custom.baseview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBButton extends MBCircularProgressButton {
    public MBButton(Context context, AttributeSet attributeSet) throws IllegalAccessException, NoSuchFieldException, InstantiationException, ClassNotFoundException {
        super(context);
        setAttributeSet(attributeSet);
    }

    public void setAttributeSet(AttributeSet attributeSet) throws IllegalAccessException, NoSuchFieldException, InstantiationException, ClassNotFoundException {
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
                    case text:
                        setText(com.mbridge.msdk.widget.custom.a.b.a().e(attributeSet.getAttributeValue(i)));
                        break;
                    case ellipsize:
                        if (attributeSet.getAttributeBooleanValue(i, false)) {
                            setFocusable(true);
                            setFocusableInTouchMode(true);
                            setSingleLine(true);
                            setEllipsize(TextUtils.TruncateAt.MARQUEE);
                            setMarqueeRepeatLimit(1000);
                            setSingleLine();
                            setHorizontallyScrolling(true);
                            requestFocus();
                            break;
                        } else {
                            break;
                        }
                    case fadingEdge:
                        setHorizontalFadingEdgeEnabled(attributeSet.getAttributeBooleanValue(i, false));
                        break;
                    case scrollHorizontally:
                        setHorizontallyScrolling(attributeSet.getAttributeBooleanValue(i, false));
                        break;
                    case textColor:
                        setTextColor(com.mbridge.msdk.widget.custom.a.b.a().b(attributeSet.getAttributeValue(i)));
                        break;
                    case textSize:
                        if (TextUtils.isEmpty(attributeSet.getAttributeValue(i))) {
                            break;
                        } else {
                            setTextSize(com.mbridge.msdk.widget.custom.a.b.a().c(r4));
                            break;
                        }
                    case visibility:
                        String attributeValue2 = attributeSet.getAttributeValue(i);
                        if (TextUtils.isEmpty(attributeValue2)) {
                            break;
                        } else if (attributeValue2.equals("invisible")) {
                            setVisibility(4);
                            break;
                        } else if (attributeValue2.equalsIgnoreCase("gone")) {
                            setVisibility(8);
                            break;
                        } else {
                            break;
                        }
                    case background:
                        String attributeValue3 = attributeSet.getAttributeValue(i);
                        if (attributeValue3.startsWith("#")) {
                            setBackgroundColor(com.mbridge.msdk.widget.custom.a.b.a().b(attributeSet.getAttributeValue(i)));
                            break;
                        } else {
                            if (attributeValue3.startsWith("@drawable/")) {
                                attributeValue3 = attributeValue3.substring(10);
                            }
                            setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeFile(getContext().getFilesDir().toString() + "/" + attributeValue3 + ".png")));
                            break;
                        }
                    case textStyle:
                        if (TtmlNode.BOLD.equalsIgnoreCase(attributeSet.getAttributeValue(i))) {
                            setTypeface(Typeface.defaultFromStyle(1));
                            break;
                        } else {
                            break;
                        }
                    case style:
                        String attributeValue4 = attributeSet.getAttributeValue(i);
                        setTextAppearance(getContext(), com.mbridge.msdk.widget.custom.a.b.a().a(getContext(), "R.style." + attributeValue4.substring(attributeValue4.indexOf("/") + 1)));
                        break;
                    case contentDescription:
                        setContentDescription(attributeSet.getAttributeValue(i));
                        break;
                    case gravity:
                        setGravity(17);
                        break;
                    case paddingBottom:
                        String attributeValue5 = attributeSet.getAttributeValue(i);
                        if (TextUtils.isEmpty(attributeValue5)) {
                            break;
                        } else {
                            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), com.mbridge.msdk.widget.custom.a.b.a().c(attributeValue5));
                            break;
                        }
                    case paddingTop:
                        String attributeValue6 = attributeSet.getAttributeValue(i);
                        if (TextUtils.isEmpty(attributeValue6)) {
                            break;
                        } else {
                            setPadding(getPaddingLeft(), com.mbridge.msdk.widget.custom.a.b.a().c(attributeValue6), getPaddingRight(), getPaddingBottom());
                            break;
                        }
                    case letterSpacing:
                        String attributeValue7 = attributeSet.getAttributeValue(i);
                        if (TextUtils.isEmpty(attributeValue7) || Build.VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            setLetterSpacing(Float.parseFloat(attributeValue7));
                            break;
                        }
                        break;
                }
            }
        }
    }
}
