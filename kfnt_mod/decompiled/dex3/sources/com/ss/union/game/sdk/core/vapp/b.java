package com.ss.union.game.sdk.core.vapp;

import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.widget.TextView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static final String a = "TextUtils";

    public static void a(TextView textView, float f) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText());
        spannableStringBuilder.setSpan(new a(f), 0, spannableStringBuilder.length(), 33);
        textView.setText(spannableStringBuilder);
    }

    public static class a extends CharacterStyle {
        float a;

        public a(float f) {
            this.a = f;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            textPaint.setStrokeWidth(this.a);
        }
    }
}
