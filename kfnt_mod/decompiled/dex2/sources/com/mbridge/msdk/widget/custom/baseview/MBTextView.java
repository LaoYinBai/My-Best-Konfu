package com.mbridge.msdk.widget.custom.baseview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.AbsListView;
import androidx.appcompat.widget.AppCompatTextView;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBTextView extends AppCompatTextView {
    private boolean a;
    public AttributeSet attrs;

    public MBTextView(Context context, AttributeSet attributeSet) throws IllegalAccessException, NoSuchFieldException, InstantiationException, ClassNotFoundException {
        super(context);
        this.a = false;
        this.attrs = attributeSet;
        setAttributeSet(attributeSet);
    }

    public boolean isFocused() {
        if (this.a) {
            return true;
        }
        return super.isFocused();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
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
                            this.a = true;
                            setFocusable(true);
                            setFocusableInTouchMode(true);
                            setEllipsize(TextUtils.TruncateAt.END);
                            setMarqueeRepeatLimit(1000);
                            setHorizontallyScrolling(true);
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
                    case singleLine:
                        setSingleLine(attributeSet.getAttributeBooleanValue(i, true));
                        break;
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
                        } else if (attributeValue2.equalsIgnoreCase("visible")) {
                            setVisibility(0);
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
                        setGravity(com.mbridge.msdk.widget.custom.a.b.a().d(attributeSet.getAttributeValue(i)));
                        break;
                }
            }
        }
    }

    public AbsListView.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new AbsListView.LayoutParams(-1, -1);
        }
        HashMap<String, com.mbridge.msdk.widget.custom.a.c> mapC = com.mbridge.msdk.widget.custom.a.b.a().c();
        int attributeCount = attributeSet.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            com.mbridge.msdk.widget.custom.a.c cVar = mapC.get(attributeSet.getAttributeName(i));
            if (cVar != null) {
                int i2 = AnonymousClass1.a[cVar.ordinal()];
                if (i2 != 4) {
                    if (i2 == 9) {
                        String attributeValue = attributeSet.getAttributeValue(i);
                        if (!TextUtils.isEmpty(attributeValue)) {
                            if (attributeValue.equals("invisible")) {
                                setVisibility(4);
                            } else if (attributeValue.equalsIgnoreCase("gone")) {
                                setVisibility(8);
                            }
                        }
                    } else {
                        switch (i2) {
                            case 14:
                                setGravity(com.mbridge.msdk.widget.custom.a.b.a().d(attributeSet.getAttributeValue(i)));
                                break;
                            case 15:
                                String attributeValue2 = attributeSet.getAttributeValue(i);
                                if (attributeValue2.startsWith("f") || attributeValue2.startsWith("m")) {
                                    layoutParams.width = -1;
                                    break;
                                } else if (attributeValue2.startsWith("wrap")) {
                                    layoutParams.width = -2;
                                    break;
                                } else {
                                    layoutParams.width = com.mbridge.msdk.widget.custom.a.b.a().c(attributeValue2);
                                    break;
                                }
                            case 16:
                                String attributeValue3 = attributeSet.getAttributeValue(i);
                                if (attributeValue3.startsWith("f") || attributeValue3.startsWith("m")) {
                                    layoutParams.height = -1;
                                    break;
                                } else if (attributeValue3.startsWith("wrap")) {
                                    layoutParams.height = -2;
                                    break;
                                } else {
                                    layoutParams.height = com.mbridge.msdk.widget.custom.a.b.a().c(attributeValue3);
                                    break;
                                }
                                break;
                        }
                    }
                } else {
                    setHorizontalFadingEdgeEnabled(attributeSet.getAttributeBooleanValue(i, false));
                }
            }
        }
        return layoutParams;
    }
}
