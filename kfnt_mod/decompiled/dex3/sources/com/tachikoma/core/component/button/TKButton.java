package com.tachikoma.core.component.button;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.StateSet;
import android.widget.Button;
import androidx.annotation.Nullable;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.component.text.FontManager;
import com.tachikoma.core.utility.TKColorUtil;
import java.util.HashMap;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKButton extends TKBase<Button> {
    public HashMap disabled;
    private StateListDrawable mStateListDrawable;
    public HashMap pressed;
    public String text;

    public TKButton(Context context, @Nullable List<Object> list) {
        super(context, list);
        getView().setAllCaps(false);
        if (Build.VERSION.SDK_INT >= 21) {
            getView().setStateListAnimator(null);
        }
    }

    private void updateDrawable(int[] iArr, HashMap map) {
        ColorStateList textColor;
        GradientDrawable gradientDrawableCreateGradientDrawable = StyleHelper.createGradientDrawable(map);
        if (this.mStateListDrawable == null) {
            this.mStateListDrawable = new StateListDrawable();
        }
        if (gradientDrawableCreateGradientDrawable != null) {
            gradientDrawableCreateGradientDrawable.setCornerRadius(this.mBorderRadius);
            this.mStateListDrawable.addState(iArr, gradientDrawableCreateGradientDrawable);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(this.mBorderRadius);
            if (this.mBackgroundColor != null) {
                gradientDrawable.setColor(Color.parseColor(TKColorUtil.rgba2argb(this.mBackgroundColor)));
            }
            this.mStateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
            getView().setBackground(this.mStateListDrawable);
        }
        if (this.style == null || (textColor = StyleHelper.getTextColor(this.style)) == null) {
            return;
        }
        getView().setTextColor(textColor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public Button createViewInstance(Context context) {
        Button button = new Button(context);
        button.setMinimumWidth(0);
        button.setMinimumHeight(0);
        button.setMinWidth(0);
        button.setMinHeight(0);
        return button;
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onCreate() {
        super.onCreate();
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
        if (getView() != null) {
            getView().setOnClickListener(null);
        }
    }

    public void setColor(String str) {
        getView().setTextColor(Color.parseColor(TKColorUtil.rgba2argb(str)));
    }

    public void setDisabled(HashMap map) {
        this.disabled = map;
        updateDrawable(new int[]{R.attr.state_enabled}, this.disabled);
    }

    public void setFontFamily(String str) {
        getView().setTypeface(FontManager.getInstance().getTypeface(str, getView().getTypeface() != null ? getView().getTypeface().getStyle() : 0, getView().getContext().getAssets(), TextUtils.isEmpty(str) ? "" : getRootDir().concat(str)));
    }

    public void setFontSize(int i) {
        getView().setTextSize(1, i);
    }

    public void setFontWeight(String str) {
        Button view;
        Typeface typeface;
        int i;
        if (TextUtils.equals(str, "bold")) {
            view = getView();
            typeface = getView().getTypeface();
            i = 1;
        } else {
            if (!TextUtils.equals(str, "normal")) {
                return;
            }
            view = getView();
            typeface = getView().getTypeface();
            i = 0;
        }
        view.setTypeface(typeface, i);
    }

    public void setPressed(HashMap map) {
        this.pressed = map;
        updateDrawable(new int[]{R.attr.state_pressed, R.attr.state_enabled}, this.pressed);
    }

    @Override // com.tachikoma.core.component.TKBase
    public void setStyle(HashMap map) {
        takeControlOfPaddingSet(map);
        super.setStyle(map);
    }

    public void setText(String str) {
        this.text = str;
        getView().setText(this.text);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setTextAlign(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            if (r0 == r1) goto L28
            r1 = 3317767(0x32a007, float:4.649182E-39)
            if (r0 == r1) goto L1e
            r1 = 108511772(0x677c21c, float:4.6598146E-35)
            if (r0 == r1) goto L14
            goto L32
        L14:
            java.lang.String r0 = "right"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L32
            r3 = 2
            goto L33
        L1e:
            java.lang.String r0 = "left"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L32
            r3 = 1
            goto L33
        L28:
            java.lang.String r0 = "center"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L32
            r3 = 0
            goto L33
        L32:
            r3 = -1
        L33:
            switch(r3) {
                case 0: goto L47;
                case 1: goto L3f;
                case 2: goto L37;
                default: goto L36;
            }
        L36:
            goto L52
        L37:
            android.view.View r3 = r2.getView()
            android.widget.Button r3 = (android.widget.Button) r3
            r0 = 5
            goto L4f
        L3f:
            android.view.View r3 = r2.getView()
            android.widget.Button r3 = (android.widget.Button) r3
            r0 = 3
            goto L4f
        L47:
            android.view.View r3 = r2.getView()
            android.widget.Button r3 = (android.widget.Button) r3
            r0 = 17
        L4f:
            r3.setGravity(r0)
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.button.TKButton.setTextAlign(java.lang.String):void");
    }

    public void setTextLineClamp(int i) {
        getView().setMaxLines(i);
    }

    public void setTextOverflow(String str) {
        Button view;
        TextUtils.TruncateAt truncateAtValueOf;
        if ("clip".equalsIgnoreCase(str)) {
            view = getView();
            truncateAtValueOf = null;
        } else {
            if (!"ellipsis".equalsIgnoreCase(str)) {
                return;
            }
            view = getView();
            truncateAtValueOf = TextUtils.TruncateAt.valueOf("END");
        }
        view.setEllipsize(truncateAtValueOf);
    }
}
