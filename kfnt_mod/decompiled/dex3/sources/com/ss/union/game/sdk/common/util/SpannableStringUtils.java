package com.ss.union.game.sdk.common.util;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.view.View;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SpannableStringUtils {
    private SpannableStringBuilder a;

    private SpannableStringUtils(SpannableStringBuilder spannableStringBuilder) {
        this.a = spannableStringBuilder;
    }

    public static SpannableStringUtils create(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return new SpannableStringUtils(new SpannableStringBuilder(str));
    }

    public SpannableStringUtils append(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.a.append((CharSequence) str);
        }
        return this;
    }

    public SpannableStringUtils insert(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            if (i >= this.a.length()) {
                this.a.append((CharSequence) str);
            } else {
                this.a.insert(i, (CharSequence) str);
            }
        }
        return this;
    }

    public SpannableStringUtils setTextForegroundColor(int i, int i2, int i3) {
        this.a.setSpan(new ForegroundColorSpan(i), i2, i3, 33);
        return this;
    }

    public SpannableStringUtils setTextBackgroundColor(int i, int i2, int i3) {
        this.a.setSpan(new BackgroundColorSpan(i), i2, i3, 33);
        return this;
    }

    public SpannableStringUtils setTextStrokeBackground(int i, int i2, int i3, int i4, int i5, int i6) {
        this.a.setSpan(new c(i, i2, i3, i4), i5, i6, 33);
        return this;
    }

    public SpannableStringUtils setTextSize(float f, int i, int i2) {
        this.a.setSpan(new RelativeSizeSpan(f), i, i2, 33);
        return this;
    }

    public SpannableStringUtils setStrikeThrough(int i, int i2) {
        this.a.setSpan(new StrikethroughSpan(), i, i2, 33);
        return this;
    }

    public SpannableStringUtils setUnderLine(int i, int i2) {
        this.a.setSpan(new UnderlineSpan(), i, i2, 33);
        return this;
    }

    public SpannableStringUtils setSuperscript(int i, int i2) {
        this.a.setSpan(new SuperscriptSpan(), i, i2, 33);
        return this;
    }

    public SpannableStringUtils setSubscript(int i, int i2) {
        this.a.setSpan(new SubscriptSpan(), i, i2, 33);
        return this;
    }

    public SpannableStringUtils setBold(int i, int i2) {
        this.a.setSpan(new StyleSpan(1), i, i2, 33);
        return this;
    }

    public SpannableStringUtils setNormal(int i, int i2) {
        this.a.setSpan(new StyleSpan(0), i, i2, 33);
        return this;
    }

    public SpannableStringUtils setItalic(int i, int i2) {
        this.a.setSpan(new StyleSpan(2), i, i2, 33);
        return this;
    }

    public SpannableStringUtils setBoldItalic(int i, int i2) {
        this.a.setSpan(new StyleSpan(3), i, i2, 33);
        return this;
    }

    public SpannableStringUtils setImage(Drawable drawable, int i, int i2, int i3, final View view) {
        if (drawable == null || i <= 0) {
            return this;
        }
        if ((drawable instanceof AnimationDrawable) && view != null) {
            Drawable.Callback callback = new Drawable.Callback() { // from class: com.ss.union.game.sdk.common.util.SpannableStringUtils.1
                Handler a = new Handler(Looper.myLooper());

                @Override // android.graphics.drawable.Drawable.Callback
                public void invalidateDrawable(Drawable drawable2) {
                    view.invalidate();
                }

                @Override // android.graphics.drawable.Drawable.Callback
                public void scheduleDrawable(Drawable drawable2, Runnable runnable, long j) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    this.a.postDelayed(runnable, j > jUptimeMillis ? j - jUptimeMillis : 0L);
                }

                @Override // android.graphics.drawable.Drawable.Callback
                public void unscheduleDrawable(Drawable drawable2, Runnable runnable) {
                    this.a.removeCallbacksAndMessages(null);
                }
            };
            drawable.setCallback(callback);
            view.setTag(callback);
        }
        drawable.setBounds(0, 0, (int) (((drawable.getIntrinsicWidth() * 1.0f) * i) / drawable.getIntrinsicHeight()), i);
        this.a.setSpan(new a(drawable), i2, i3, 33);
        return this;
    }

    public SpannableStringUtils setClickable(ClickableSpan clickableSpan, int i, int i2) {
        try {
            this.a.setSpan(clickableSpan, i, i2, 33);
        } catch (Throwable unused) {
        }
        return this;
    }

    public SpannableStringUtils setGradient(int[] iArr, Shader.TileMode tileMode, int i, int i2, int i3) {
        this.a.setSpan(new b(iArr, i3, tileMode), i, i2, 33);
        return this;
    }

    public SpannableStringUtils setGradient(b bVar, int i, int i2) {
        this.a.setSpan(bVar, i, i2, 33);
        return this;
    }

    public SpannableStringBuilder makeText() {
        return this.a;
    }

    private static class b extends CharacterStyle implements UpdateAppearance {
        private Shader a;
        private int[] b;
        private int c;
        private Shader.TileMode d;
        private Matrix e = new Matrix();

        public b(int[] iArr, int i, Shader.TileMode tileMode) {
            this.b = iArr;
            this.c = i;
            this.d = tileMode;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            if (this.b == null || this.b.length <= 0) {
                return;
            }
            if (this.a == null) {
                this.a = new LinearGradient(0.0f, 0.0f, this.c, 0.0f, this.b, (float[]) null, this.d);
            }
            textPaint.setShader(this.a);
        }

        public void a(float f) {
            if (f < 0.0f || this.a == null) {
                return;
            }
            this.e.reset();
            this.e.setTranslate(f * this.c, 0.0f);
            this.a.setLocalMatrix(this.e);
        }
    }

    private static class c extends ReplacementSpan {
        private int a;
        private int b;
        private int c;
        private int d;
        private int e;

        public c(int i, int i2, int i3, int i4) {
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.a = i4;
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            this.e = (int) (paint.measureText(charSequence, i, i2) + (this.a * 2));
            return this.e + 10;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            int color = paint.getColor();
            float strokeWidth = paint.getStrokeWidth();
            paint.setColor(this.b);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(this.c);
            paint.setAntiAlias(true);
            float f2 = i4;
            canvas.drawRoundRect(new RectF(f + (this.c / 2.0f) + 5.0f, (this.c / 2.0f) + f2 + paint.ascent(), f + this.e + 5.0f, paint.descent() + f2), this.a, this.a, paint);
            paint.setColor(this.d);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(strokeWidth);
            canvas.drawText(charSequence, i, i2, f + this.a, f2, paint);
            paint.setColor(color);
        }
    }

    private static class a extends ImageSpan {
        public static final int a = 3;
        public static final int b = 4;
        private WeakReference<Drawable> c;

        public a(Drawable drawable) {
            this(drawable, 4);
        }

        public a(Drawable drawable, int i) {
            super(drawable, i);
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            Rect bounds = a().getBounds();
            if (fontMetricsInt != null) {
                Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                float f = fontMetrics.top - fontMetrics.ascent;
                float f2 = fontMetrics.bottom - fontMetrics.descent;
                int iHeight = bounds.height();
                int i3 = this.mVerticalAlignment;
                if (i3 == 1) {
                    int i4 = -iHeight;
                    fontMetricsInt.ascent = i4;
                    fontMetricsInt.top = (int) (i4 + f);
                } else {
                    switch (i3) {
                        case 3:
                            float f3 = iHeight + fontMetrics.ascent;
                            fontMetricsInt.descent = (int) f3;
                            fontMetricsInt.bottom = (int) (f3 + f2);
                            break;
                        case 4:
                            float f4 = fontMetrics.descent - ((fontMetrics.descent - fontMetrics.ascent) / 2.0f);
                            float f5 = iHeight / 2;
                            float f6 = f4 - f5;
                            float f7 = f4 + f5;
                            fontMetricsInt.ascent = (int) f6;
                            fontMetricsInt.top = (int) (f6 + f);
                            fontMetricsInt.descent = (int) f7;
                            fontMetricsInt.bottom = (int) (f7 + f2);
                            break;
                        default:
                            float f8 = fontMetrics.descent - iHeight;
                            fontMetricsInt.ascent = (int) f8;
                            fontMetricsInt.top = (int) (f8 + f);
                            break;
                    }
                }
            }
            return bounds.right;
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            float fHeight;
            Drawable drawable = getDrawable();
            Rect bounds = drawable.getBounds();
            int i6 = this.mVerticalAlignment;
            if (i6 == 1) {
                fHeight = i4 - bounds.height();
            } else {
                switch (i6) {
                    case 3:
                        fHeight = i3;
                        break;
                    case 4:
                        fHeight = (((i5 - i3) - bounds.height()) / 2) + i3;
                        break;
                    default:
                        fHeight = i5 - bounds.height();
                        break;
                }
            }
            canvas.save();
            canvas.translate(f, fHeight);
            drawable.draw(canvas);
            canvas.restore();
        }

        private Drawable a() {
            WeakReference<Drawable> weakReference = this.c;
            Drawable drawable = weakReference != null ? weakReference.get() : null;
            if (drawable != null) {
                return drawable;
            }
            Drawable drawable2 = getDrawable();
            this.c = new WeakReference<>(drawable2);
            return drawable2;
        }
    }
}
