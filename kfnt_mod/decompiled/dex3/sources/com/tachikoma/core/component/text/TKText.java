package com.tachikoma.core.component.text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.appcompat.widget.AppCompatTextView;
import com.kwad.v8.V8Object;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.utility.TKColorUtil;
import com.tachikoma.core.utility.TKConverter;
import java.util.HashMap;
import java.util.List;
import kotlin.ULong;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKText extends TKBase<AppCompatTextView> {
    public String formattedText;
    private int mFontSize;
    private float mMinimumScaleFactor;
    public String text;

    public TKText(Context context, List<Object> list) {
        super(context, list);
        this.mMinimumScaleFactor = -1.0f;
    }

    public static Spanned fromHtml(String str) {
        return Build.VERSION.SDK_INT >= 24 ? Html.fromHtml(str, 0) : Html.fromHtml(str);
    }

    public static void setLineHeight(@NonNull TextView textView, @IntRange(from = ULong.MIN_VALUE) @Px int i) {
        int fontMetricsInt;
        if (i >= 0 && i != (fontMetricsInt = textView.getPaint().getFontMetricsInt((Paint.FontMetricsInt) null))) {
            textView.setLineSpacing(i - fontMetricsInt, 1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public AppCompatTextView createViewInstance(Context context) {
        return new AppCompatTextView(context);
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onCreate() {
        super.onCreate();
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
    }

    public void setColor(String str) {
        getView().setTextColor(Color.parseColor(TKColorUtil.rgba2argb(str)));
    }

    public void setFontFamily(String str) {
        getView().setTypeface(FontManager.getInstance().getTypeface(str, getView().getTypeface() != null ? getView().getTypeface().getStyle() : 0, getView().getContext().getAssets(), TextUtils.isEmpty(str) ? "" : getRootDir().concat(str)));
    }

    public void setFontSize(int i) {
        this.mFontSize = i;
        getView().setTextSize(1, i);
        setMinimumScaleFactor(this.mMinimumScaleFactor);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setFontWeight(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = -1039745817(0xffffffffc206bce7, float:-33.684475)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L1b
            r1 = 3029637(0x2e3a85, float:4.245426E-39)
            if (r0 == r1) goto L11
            goto L25
        L11:
            java.lang.String r0 = "bold"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L25
            r5 = 0
            goto L26
        L1b:
            java.lang.String r0 = "normal"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L25
            r5 = 1
            goto L26
        L25:
            r5 = -1
        L26:
            switch(r5) {
                case 0: goto L3e;
                case 1: goto L2a;
                default: goto L29;
            }
        L29:
            goto L51
        L2a:
            android.view.View r5 = r4.getView()
            androidx.appcompat.widget.AppCompatTextView r5 = (androidx.appcompat.widget.AppCompatTextView) r5
            android.view.View r0 = r4.getView()
            androidx.appcompat.widget.AppCompatTextView r0 = (androidx.appcompat.widget.AppCompatTextView) r0
            android.graphics.Typeface r0 = r0.getTypeface()
            r5.setTypeface(r0, r2)
            goto L51
        L3e:
            android.view.View r5 = r4.getView()
            androidx.appcompat.widget.AppCompatTextView r5 = (androidx.appcompat.widget.AppCompatTextView) r5
            android.view.View r0 = r4.getView()
            androidx.appcompat.widget.AppCompatTextView r0 = (androidx.appcompat.widget.AppCompatTextView) r0
            android.graphics.Typeface r0 = r0.getTypeface()
            r5.setTypeface(r0, r3)
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.text.TKText.setFontWeight(java.lang.String):void");
    }

    public void setFormattedText(String str) {
        this.formattedText = str;
        getView().setText(fromHtml(this.formattedText));
    }

    public void setIncludeFontPadding(boolean z) {
        getView().setIncludeFontPadding(z);
    }

    @SuppressLint({"RestrictedApi"})
    public void setMinimumScaleFactor(float f) {
        this.mMinimumScaleFactor = f;
        if (f < 0.0f || f > 1.0f || this.mFontSize == 0) {
            return;
        }
        getView().setAutoSizeTextTypeUniformWithConfiguration((int) (this.mFontSize * f), this.mFontSize, 1, 1);
    }

    public void setSpan(String str, V8Object v8Object) {
        JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
        try {
            TKSpan tKSpan = (TKSpan) associateReference.nativeObject;
            if (tKSpan == null) {
                return;
            }
            this.text = str;
            getDomNode().getYogaNode().dirty();
            getView().setText(tKSpan.getSpannableString(str, (TextView) getView()));
        } finally {
            associateReference.close();
        }
    }

    @Override // com.tachikoma.core.component.TKBase
    public void setStyle(HashMap map) {
        takeControlOfPaddingSet(map);
        super.setStyle(map);
    }

    public void setText(String str) {
        this.text = str;
        getDomNode().getYogaNode().dirty();
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
            androidx.appcompat.widget.AppCompatTextView r3 = (androidx.appcompat.widget.AppCompatTextView) r3
            r0 = 5
            goto L4f
        L3f:
            android.view.View r3 = r2.getView()
            androidx.appcompat.widget.AppCompatTextView r3 = (androidx.appcompat.widget.AppCompatTextView) r3
            r0 = 3
            goto L4f
        L47:
            android.view.View r3 = r2.getView()
            androidx.appcompat.widget.AppCompatTextView r3 = (androidx.appcompat.widget.AppCompatTextView) r3
            r0 = 17
        L4f:
            r3.setGravity(r0)
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.text.TKText.setTextAlign(java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setTextDecoration(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -1171789332(0xffffffffba27e9ec, float:-6.4054015E-4)
            if (r0 == r1) goto L19
            r1 = -1026963764(0xffffffffc2c9c6cc, float:-100.888275)
            if (r0 == r1) goto Lf
            goto L23
        Lf:
            java.lang.String r0 = "underline"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L23
            r3 = 0
            goto L24
        L19:
            java.lang.String r0 = "line-through"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L23
            r3 = 1
            goto L24
        L23:
            r3 = -1
        L24:
            switch(r3) {
                case 0: goto L35;
                case 1: goto L28;
                default: goto L27;
            }
        L27:
            goto L44
        L28:
            android.view.View r3 = r2.getView()
            androidx.appcompat.widget.AppCompatTextView r3 = (androidx.appcompat.widget.AppCompatTextView) r3
            android.text.TextPaint r3 = r3.getPaint()
            r0 = 16
            goto L41
        L35:
            android.view.View r3 = r2.getView()
            androidx.appcompat.widget.AppCompatTextView r3 = (androidx.appcompat.widget.AppCompatTextView) r3
            android.text.TextPaint r3 = r3.getPaint()
            r0 = 8
        L41:
            r3.setFlags(r0)
        L44:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.text.TKText.setTextDecoration(java.lang.String):void");
    }

    public void setTextLineClamp(int i) {
        getView().setMaxLines(i);
    }

    public void setTextLineHeight(Number number) {
        if (number != null) {
            setLineHeight(getView(), (int) TKConverter.dp2px(getContext(), number.floatValue()));
        }
    }

    public void setTextLineSpace(Number number) {
        if (number != null) {
            getView().setLineSpacing(TKConverter.dp2px(getContext(), number.floatValue()), 1.0f);
        }
    }

    public void setTextOverflow(String str) {
        AppCompatTextView view;
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
