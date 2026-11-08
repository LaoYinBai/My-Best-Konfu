package com.tachikoma.core.component.text;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CustomTypefaceSpan extends TypefaceSpan {

    @Nullable
    private final Typeface mTypeface;

    public CustomTypefaceSpan(@Nullable String str, @Nullable Typeface typeface) {
        super(str);
        this.mTypeface = typeface;
    }

    @Override // android.text.style.TypefaceSpan
    @Nullable
    public Typeface getTypeface() {
        return this.mTypeface;
    }

    @Override // android.text.style.TypefaceSpan, android.text.style.CharacterStyle
    public void updateDrawState(@NonNull TextPaint textPaint) {
        if (this.mTypeface != null) {
            textPaint.setTypeface(this.mTypeface);
        } else {
            super.updateDrawState(textPaint);
        }
    }

    @Override // android.text.style.TypefaceSpan, android.text.style.MetricAffectingSpan
    public void updateMeasureState(@NonNull TextPaint textPaint) {
        if (this.mTypeface != null) {
            textPaint.setTypeface(this.mTypeface);
        } else {
            super.updateMeasureState(textPaint);
        }
    }
}
