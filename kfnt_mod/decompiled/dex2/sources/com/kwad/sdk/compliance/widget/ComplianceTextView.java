package com.kwad.sdk.compliance.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.AdWebViewActivityProxy;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.l;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ComplianceTextView extends TextView {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private String g;
    private AdTemplate h;

    @ColorInt
    private int i;
    private ViewTreeObserver.OnGlobalLayoutListener j;

    public ComplianceTextView(Context context) {
        this(context, null);
    }

    public ComplianceTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ComplianceTextView(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        this.f = 154;
        this.j = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.kwad.sdk.compliance.widget.ComplianceTextView.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (ComplianceTextView.this.getWidth() > 0) {
                    ComplianceTextView.this.a(ComplianceTextView.this.a, ComplianceTextView.this.b, ComplianceTextView.this.c);
                    ComplianceTextView.this.a();
                }
            }
        };
        a(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.j != null) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.j);
            this.j = null;
        }
    }

    @SuppressLint({"CustomViewStyleable"})
    private void a(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ksad_ComplianceTextView);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.ksad_ComplianceTextView_ksad_width_in_landscape, 154);
        this.i = typedArrayObtainStyledAttributes.getColor(R.styleable.ksad_ComplianceTextView_ksad_privacy_color, getResources().getColor(R.color.ksad_default_privacy_link_color));
        if (getBackground() == null) {
            setBackground(ContextCompat.getDrawable(context, R.drawable.ksad_compliance_view_bg));
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.j);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void a(final String str, final String str2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) this.g);
        if (!TextUtils.isEmpty(this.d)) {
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) str);
            spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.kwad.sdk.compliance.widget.ComplianceTextView.1
                @Override // android.text.style.ClickableSpan
                public void onClick(@NonNull View view) {
                    AdWebViewActivityProxy.launch(ComplianceTextView.this.getContext(), new AdWebViewActivityProxy.a.C0042a().a(str).b(ComplianceTextView.this.d).a(ComplianceTextView.this.h).a());
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(@NonNull TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(ComplianceTextView.this.i);
                    textPaint.setUnderlineText(true);
                }
            }, length, spannableStringBuilder.length(), 33);
        }
        if (!TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(this.e)) {
            spannableStringBuilder.append((CharSequence) " | ");
        }
        if (!TextUtils.isEmpty(this.e)) {
            int length2 = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) str2);
            spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.kwad.sdk.compliance.widget.ComplianceTextView.2
                @Override // android.text.style.ClickableSpan
                public void onClick(@NonNull View view) {
                    AdWebViewActivityProxy.launch(ComplianceTextView.this.getContext(), new AdWebViewActivityProxy.a.C0042a().a(str2).b(ComplianceTextView.this.e).a(ComplianceTextView.this.h).a());
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(@NonNull TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(ComplianceTextView.this.i);
                    textPaint.setUnderlineText(true);
                }
            }, length2, spannableStringBuilder.length(), 33);
        }
        setMovementMethod(LinkMovementMethod.getInstance());
        setHighlightColor(ContextCompat.getColor(getContext(), R.color.ksad_translucent));
        setText(spannableStringBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3) {
        StringBuilder sb;
        String str4;
        if (TextUtils.isEmpty(str)) {
            setVisibility(8);
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        if (!TextUtils.isEmpty(this.d) || !TextUtils.isEmpty(this.e)) {
            sb2.append("   ");
        }
        if (!TextUtils.isEmpty(this.d)) {
            sb2.append(str2);
        }
        if (!TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(this.e)) {
            sb2.append(" | ");
        }
        if (!TextUtils.isEmpty(this.e)) {
            sb2.append(str3);
        }
        int width = ((!af.e(getContext()) ? this.f : getWidth()) - getPaddingLeft()) - getPaddingRight();
        if (width < 0) {
            return;
        }
        if (a(str, sb2.toString(), width)) {
            sb = new StringBuilder();
            sb.append(str);
            str4 = "\n";
        } else {
            sb = new StringBuilder();
            sb.append(str);
            str4 = "   ";
        }
        sb.append(str4);
        this.g = sb.toString();
        a(str2, str3);
    }

    private boolean a(String str, String str2, int i) {
        StaticLayout staticLayout;
        StaticLayout staticLayout2;
        String str3 = str + str2;
        if (Build.VERSION.SDK_INT >= 23) {
            staticLayout = StaticLayout.Builder.obtain(str, 0, str.length(), getPaint(), i).build();
            staticLayout2 = StaticLayout.Builder.obtain(str3, 0, str3.length(), getPaint(), i).build();
        } else {
            staticLayout = new StaticLayout(str, getPaint(), i, Layout.Alignment.ALIGN_NORMAL, 0.0f, 0.0f, true);
            staticLayout2 = new StaticLayout(str3, getPaint(), i, Layout.Alignment.ALIGN_NORMAL, 0.0f, 0.0f, true);
        }
        return staticLayout2.getLineCount() > staticLayout.getLineCount();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public void setAdTemplate(AdTemplate adTemplate) {
        this.h = adTemplate;
        AdInfo adInfoJ = d.j(adTemplate);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(adInfoJ.adBaseInfo.appName)) {
            sb.append("应用名：");
            sb.append(adInfoJ.adBaseInfo.appName);
            sb.append("；");
        }
        if (!TextUtils.isEmpty(adInfoJ.adBaseInfo.appVersion)) {
            sb.append("版本号：");
            sb.append(adInfoJ.adBaseInfo.appVersion);
            sb.append("；");
        }
        if (adInfoJ.adBaseInfo.packageSize > 0) {
            sb.append("应用大小：");
            sb.append(l.a(adInfoJ.adBaseInfo.packageSize, true));
            sb.append("；");
        }
        if (!TextUtils.isEmpty(adInfoJ.adBaseInfo.corporationName)) {
            sb.append("开发者：");
            sb.append(adInfoJ.adBaseInfo.corporationName);
            sb.append("；");
        }
        this.a = sb.toString();
        this.b = "权限信息";
        this.c = "隐私政策";
        this.d = adInfoJ.downloadSafeInfo.appPermissionInfoUrl;
        this.e = adInfoJ.downloadSafeInfo.appPrivacyUrl;
        if (!af.e(getContext())) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = bb.a(getContext(), this.f);
            setLayoutParams(layoutParams);
        }
        a(this.a, this.b, this.c);
    }
}
