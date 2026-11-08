package com.tachikoma.core.component.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.tachikoma.core.utility.TKColorUtil;
import com.tachikoma.core.utility.TKFloatUtil;
import com.tachikoma.core.utility.TKSpacing;
import java.util.Arrays;
import java.util.Locale;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKViewBackgroundDrawable extends Drawable {
    private static final int ALL_BITS_SET = -1;
    private static final int ALL_BITS_UNSET = 0;
    private static final int DEFAULT_BORDER_ALPHA = 255;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final int DEFAULT_BORDER_RGB = 0;
    private static float UNDEFINED = 1.0E21f;
    private TKSpacing mBorderAlpha;
    private String mBorderColor;
    private float[] mBorderCornerRadii;
    private TKSpacing mBorderRGB;
    private BorderStyle mBorderStyle;
    private TKSpacing mBorderWidth;
    private Path mCenterDrawPath;
    private final Context mContext;
    private Gradient mGradient;
    private PointF mInnerBottomLeftCorner;
    private PointF mInnerBottomRightCorner;
    private Path mInnerClipPathForBorderRadius;
    private RectF mInnerClipTempRectForBorderRadius;
    private PointF mInnerTopLeftCorner;
    private PointF mInnerTopRightCorner;
    private int mLayoutDirection;
    private Path mOuterClipPathForBorderRadius;
    private RectF mOuterClipTempRectForBorderRadius;
    private PathEffect mPathEffectForBorderStyle;
    private Path mPathForBorder;
    private Path mPathForBorderRadiusOutline;
    private Shadow mShadow;
    private RectF mTempRectForBorderRadiusOutline;
    private RectF mTempRectForCenterDrawPath;
    private boolean mNeedUpdatePathForBorderRadius = false;
    private float mBorderRadius = UNDEFINED;
    private final Paint mPaint = new Paint(1);
    private final Paint mGradientPaint = new Paint(1);
    private final Paint mShadowPaint = new Paint(1);
    private int mColor = 0;
    private int mAlpha = 255;

    public enum BorderRadiusLocation {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_START,
        TOP_END,
        BOTTOM_START,
        BOTTOM_END
    }

    private enum BorderStyle {
        SOLID,
        DASHED,
        DOTTED;

        public static PathEffect getPathEffect(BorderStyle borderStyle, float f) {
            switch (borderStyle) {
                case SOLID:
                    return null;
                case DASHED:
                    float f2 = f * 3.0f;
                    return new DashPathEffect(new float[]{f2, f2, f2, f2}, 0.0f);
                case DOTTED:
                    return new DashPathEffect(new float[]{f, f, f, f}, 0.0f);
                default:
                    return null;
            }
        }
    }

    public static class Gradient {
        public int[] colors;
        public int deg;
        public float[] positions;
    }

    public static class Shadow {
        public int color;
        public float dx;
        public float dy;
        public float radius;

        public Shadow(float f, float f2, float f3, int i) {
            this.radius = f;
            this.dx = f2;
            this.dy = f3;
            this.color = i;
        }
    }

    public TKViewBackgroundDrawable(Context context) {
        this.mContext = context;
    }

    private static int colorFromAlphaAndRGBComponents(float f, float f2) {
        return ((((int) f) << 24) & (-16777216)) | (((int) f2) & 16777215);
    }

    private void drawGradient(@NonNull Canvas canvas, @NonNull Gradient gradient) {
        float[] gradientLine = getGradientLine(gradient, getBoundsExcludeShadow());
        this.mGradientPaint.setShader(new LinearGradient(gradientLine[0], gradientLine[1], gradientLine[2], gradientLine[3], gradient.colors, gradient.positions, Shader.TileMode.CLAMP));
        if (hasRoundedBorders()) {
            canvas.drawPath(this.mInnerClipPathForBorderRadius, this.mGradientPaint);
        } else {
            canvas.drawRect(getBoundsExcludeShadow(), this.mGradientPaint);
        }
    }

    private void drawQuadrilateral(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (i == 0) {
            return;
        }
        if (this.mPathForBorder == null) {
            this.mPathForBorder = new Path();
        }
        this.mPaint.setColor(i);
        this.mPathForBorder.reset();
        this.mPathForBorder.moveTo(f, f2);
        this.mPathForBorder.lineTo(f3, f4);
        this.mPathForBorder.lineTo(f5, f6);
        this.mPathForBorder.lineTo(f7, f8);
        this.mPathForBorder.lineTo(f, f2);
        canvas.drawPath(this.mPathForBorder, this.mPaint);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00ae A[PHI: r2
  0x00ae: PHI (r2v1 int) = (r2v0 int), (r2v14 int) binds: [B:16:0x0075, B:37:0x00a7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void drawRectangularBackgroundWithBorders(android.graphics.Canvas r25) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.view.TKViewBackgroundDrawable.drawRectangularBackgroundWithBorders(android.graphics.Canvas):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00f5 A[PHI: r1
  0x00f5: PHI (r1v11 int) = (r1v10 int), (r1v16 int) binds: [B:29:0x00bf, B:49:0x00ef] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0196  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void drawRoundedBackgroundWithBorders(android.graphics.Canvas r23) {
        /*
            Method dump skipped, instructions count: 443
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.view.TKViewBackgroundDrawable.drawRoundedBackgroundWithBorders(android.graphics.Canvas):void");
    }

    private void drawShadow(Canvas canvas) {
        this.mShadowPaint.setColor(0);
        this.mShadowPaint.setStyle(Paint.Style.FILL);
        this.mShadowPaint.setShadowLayer(this.mShadow.radius, this.mShadow.dx, this.mShadow.dy, this.mShadow.color);
        if (hasRoundedBorders()) {
            canvas.drawPath(this.mOuterClipPathForBorderRadius, this.mShadowPaint);
        } else {
            canvas.drawRect(getBoundsExcludeShadow(), this.mShadowPaint);
        }
    }

    private void drawShadowBelow_9_0(Canvas canvas) {
        try {
            int i = (int) (this.mShadow.radius * 2.4f);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(getBounds().width() + i, getBounds().height() + i, Bitmap.Config.ARGB_4444);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f = i;
            canvas2.translate(f, f);
            this.mShadowPaint.setColor(0);
            this.mShadowPaint.setStyle(Paint.Style.FILL);
            this.mShadowPaint.setShadowLayer(this.mShadow.radius, this.mShadow.dx, this.mShadow.dy, this.mShadow.color);
            if (hasRoundedBorders()) {
                canvas2.drawPath(this.mOuterClipPathForBorderRadius, this.mShadowPaint);
            } else {
                canvas2.drawRect(getBoundsExcludeShadow(), this.mShadowPaint);
            }
            float f2 = -i;
            canvas.drawBitmap(bitmapCreateBitmap, f2, f2, (Paint) null);
            if (bitmapCreateBitmap.isRecycled()) {
                return;
            }
            bitmapCreateBitmap.recycle();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void drawShadowIfNeed(Canvas canvas) {
        if (this.mShadow == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            drawShadow(canvas);
        } else {
            drawShadowBelow_9_0(canvas);
        }
    }

    private static int fastBorderCompatibleColorOrZero(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = (i4 > 0 ? i8 : -1) & (i > 0 ? i5 : -1) & (i2 > 0 ? i6 : -1) & (i3 > 0 ? i7 : -1);
        if (i <= 0) {
            i5 = 0;
        }
        if (i2 <= 0) {
            i6 = 0;
        }
        int i10 = i5 | i6;
        if (i3 <= 0) {
            i7 = 0;
        }
        int i11 = i10 | i7;
        if (i4 <= 0) {
            i8 = 0;
        }
        if (i9 == (i11 | i8)) {
            return i9;
        }
        return 0;
    }

    private int getBorderColor(int i) {
        return colorFromAlphaAndRGBComponents(this.mBorderAlpha != null ? this.mBorderAlpha.get(i) : 255.0f, this.mBorderRGB != null ? this.mBorderRGB.get(i) : 0.0f);
    }

    private int getBorderWidth(int i) {
        if (this.mBorderWidth == null) {
            return 0;
        }
        float f = this.mBorderWidth.get(i);
        if (isUndefined(f)) {
            return -1;
        }
        return Math.round(f);
    }

    private Rect getBoundsExcludeShadow() {
        float f = this.mShadow != null ? this.mShadow.radius : 0.0f;
        if (f <= 0.0f) {
            return getBounds();
        }
        Rect rect = new Rect(getBounds());
        rect.left = (int) (rect.left + f);
        rect.top = (int) (rect.top + f);
        rect.right = (int) (rect.right - f);
        rect.bottom = (int) (rect.bottom - f);
        return rect;
    }

    private static void getEllipseIntersectionWithLine(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointF pointF) {
        double d9 = (d + d3) / 2.0d;
        double d10 = (d2 + d4) / 2.0d;
        double d11 = d5 - d9;
        double d12 = d6 - d10;
        double dAbs = Math.abs(d3 - d) / 2.0d;
        double dAbs2 = Math.abs(d4 - d2) / 2.0d;
        double d13 = ((d8 - d10) - d12) / ((d7 - d9) - d11);
        double d14 = d12 - (d11 * d13);
        double d15 = dAbs2 * dAbs2;
        double d16 = dAbs * dAbs;
        double d17 = (d16 * d13 * d13) + d15;
        double d18 = dAbs * 2.0d * dAbs * d14 * d13;
        double d19 = (-(d16 * ((d14 * d14) - d15))) / d17;
        double d20 = d17 * 2.0d;
        double dSqrt = ((-d18) / d20) - Math.sqrt(d19 + Math.pow(d18 / d20, 2.0d));
        double d21 = (d13 * dSqrt) + d14;
        double d22 = dSqrt + d9;
        double d23 = d21 + d10;
        if (Double.isNaN(d22) || Double.isNaN(d23)) {
            return;
        }
        pointF.x = (float) d22;
        pointF.y = (float) d23;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private float[] getGradientLine(Gradient gradient, Rect rect) {
        int i = ((gradient.deg % 360) + 360) % 360;
        float[] fArr = new float[4];
        float f = (rect.left + rect.right) / 2.0f;
        float f2 = (rect.top + rect.bottom) / 2.0f;
        int i2 = i / 45;
        float fTan = (float) Math.tan(Math.toRadians(i % 45));
        float fTan2 = (float) Math.tan(Math.toRadians(45 - r10));
        switch (i2) {
            case 0:
                float f3 = fTan * f;
                fArr[0] = rect.left;
                fArr[1] = f2 + f3;
                fArr[2] = rect.right;
                fArr[3] = f2 - f3;
                return fArr;
            case 1:
                float f4 = f - (fTan2 * f2);
                fArr[0] = f4;
                fArr[1] = rect.bottom;
                fArr[2] = rect.right - f4;
                fArr[3] = rect.top;
                return fArr;
            case 2:
                float f5 = fTan * f2;
                fArr[0] = f + f5;
                fArr[1] = rect.bottom;
                fArr[2] = f - f5;
                fArr[3] = rect.top;
                return fArr;
            case 3:
                float f6 = f2 - (fTan2 * f);
                fArr[0] = rect.right;
                fArr[1] = rect.bottom - f6;
                fArr[2] = rect.left;
                fArr[3] = f6;
                return fArr;
            case 4:
                float f7 = fTan * f;
                fArr[2] = rect.left;
                fArr[3] = f2 + f7;
                fArr[0] = rect.right;
                fArr[1] = f2 - f7;
                return fArr;
            case 5:
                float f8 = f - (fTan2 * f2);
                fArr[2] = f8;
                fArr[3] = rect.bottom;
                fArr[0] = rect.right - f8;
                fArr[1] = rect.top;
                return fArr;
            case 6:
                float f9 = fTan * f2;
                fArr[2] = f + f9;
                fArr[3] = rect.bottom;
                fArr[0] = f - f9;
                fArr[1] = rect.top;
                return fArr;
            case 7:
                float f10 = f2 - (fTan2 * f);
                fArr[2] = rect.right;
                fArr[3] = rect.bottom - f10;
                fArr[0] = rect.left;
                fArr[1] = f10;
                return fArr;
            default:
                return fArr;
        }
    }

    private boolean isBorderColorDefined(int i) {
        return (isUndefined(this.mBorderRGB != null ? this.mBorderRGB.get(i) : UNDEFINED) || isUndefined(this.mBorderAlpha != null ? this.mBorderAlpha.get(i) : UNDEFINED)) ? false : true;
    }

    public static boolean isUndefined(float f) {
        return Float.compare(f, UNDEFINED) == 0;
    }

    private void setBorderAlpha(int i, float f) {
        if (this.mBorderAlpha == null) {
            this.mBorderAlpha = new TKSpacing(255.0f);
        }
        if (TKFloatUtil.floatsEqual(this.mBorderAlpha.getRaw(i), f)) {
            return;
        }
        this.mBorderAlpha.set(i, f);
        invalidateSelf();
    }

    private void setBorderRGB(int i, float f) {
        if (this.mBorderRGB == null) {
            this.mBorderRGB = new TKSpacing(0.0f);
        }
        if (TKFloatUtil.floatsEqual(this.mBorderRGB.getRaw(i), f)) {
            return;
        }
        this.mBorderRGB.set(i, f);
        invalidateSelf();
    }

    private void updatePath() {
        if (this.mNeedUpdatePathForBorderRadius) {
            this.mNeedUpdatePathForBorderRadius = false;
            if (this.mInnerClipPathForBorderRadius == null) {
                this.mInnerClipPathForBorderRadius = new Path();
            }
            if (this.mOuterClipPathForBorderRadius == null) {
                this.mOuterClipPathForBorderRadius = new Path();
            }
            if (this.mPathForBorderRadiusOutline == null) {
                this.mPathForBorderRadiusOutline = new Path();
            }
            if (this.mCenterDrawPath == null) {
                this.mCenterDrawPath = new Path();
            }
            if (this.mInnerClipTempRectForBorderRadius == null) {
                this.mInnerClipTempRectForBorderRadius = new RectF();
            }
            if (this.mOuterClipTempRectForBorderRadius == null) {
                this.mOuterClipTempRectForBorderRadius = new RectF();
            }
            if (this.mTempRectForBorderRadiusOutline == null) {
                this.mTempRectForBorderRadiusOutline = new RectF();
            }
            if (this.mTempRectForCenterDrawPath == null) {
                this.mTempRectForCenterDrawPath = new RectF();
            }
            this.mInnerClipPathForBorderRadius.reset();
            this.mOuterClipPathForBorderRadius.reset();
            this.mPathForBorderRadiusOutline.reset();
            this.mCenterDrawPath.reset();
            Rect rect = new Rect(getBoundsExcludeShadow());
            this.mInnerClipTempRectForBorderRadius.set(rect);
            this.mOuterClipTempRectForBorderRadius.set(rect);
            this.mTempRectForBorderRadiusOutline.set(rect);
            this.mTempRectForCenterDrawPath.set(rect);
            float fullBorderWidth = getFullBorderWidth();
            if (fullBorderWidth > 0.0f) {
                float f = fullBorderWidth * 0.5f;
                this.mTempRectForCenterDrawPath.inset(f, f);
            }
            RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
            this.mInnerClipTempRectForBorderRadius.top += directionAwareBorderInsets.top;
            this.mInnerClipTempRectForBorderRadius.bottom -= directionAwareBorderInsets.bottom;
            this.mInnerClipTempRectForBorderRadius.left += directionAwareBorderInsets.left;
            this.mInnerClipTempRectForBorderRadius.right -= directionAwareBorderInsets.right;
            float fullBorderRadius = getFullBorderRadius();
            float borderRadiusOrDefaultTo = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.TOP_LEFT);
            float borderRadiusOrDefaultTo2 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.TOP_RIGHT);
            float borderRadiusOrDefaultTo3 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.BOTTOM_LEFT);
            float borderRadiusOrDefaultTo4 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.BOTTOM_RIGHT);
            if (Build.VERSION.SDK_INT >= 17) {
                boolean z = getResolvedLayoutDirection() == 1;
                float borderRadius = getBorderRadius(BorderRadiusLocation.TOP_START);
                float borderRadius2 = getBorderRadius(BorderRadiusLocation.TOP_END);
                float borderRadius3 = getBorderRadius(BorderRadiusLocation.BOTTOM_START);
                float borderRadius4 = getBorderRadius(BorderRadiusLocation.BOTTOM_END);
                float f2 = z ? borderRadius2 : borderRadius;
                if (!z) {
                    borderRadius = borderRadius2;
                }
                float f3 = z ? borderRadius4 : borderRadius3;
                if (!z) {
                    borderRadius3 = borderRadius4;
                }
                if (!isUndefined(f2)) {
                    borderRadiusOrDefaultTo = f2;
                }
                if (!isUndefined(borderRadius)) {
                    borderRadiusOrDefaultTo2 = borderRadius;
                }
                if (!isUndefined(f3)) {
                    borderRadiusOrDefaultTo3 = f3;
                }
                if (!isUndefined(borderRadius3)) {
                    borderRadiusOrDefaultTo4 = borderRadius3;
                }
            }
            float fMax = Math.max(borderRadiusOrDefaultTo - directionAwareBorderInsets.left, 0.0f);
            float fMax2 = Math.max(borderRadiusOrDefaultTo - directionAwareBorderInsets.top, 0.0f);
            float fMax3 = Math.max(borderRadiusOrDefaultTo2 - directionAwareBorderInsets.right, 0.0f);
            float fMax4 = Math.max(borderRadiusOrDefaultTo2 - directionAwareBorderInsets.top, 0.0f);
            float fMax5 = Math.max(borderRadiusOrDefaultTo4 - directionAwareBorderInsets.right, 0.0f);
            float fMax6 = Math.max(borderRadiusOrDefaultTo4 - directionAwareBorderInsets.bottom, 0.0f);
            float fMax7 = Math.max(borderRadiusOrDefaultTo3 - directionAwareBorderInsets.left, 0.0f);
            float fMax8 = Math.max(borderRadiusOrDefaultTo3 - directionAwareBorderInsets.bottom, 0.0f);
            float f4 = borderRadiusOrDefaultTo3;
            float f5 = borderRadiusOrDefaultTo4;
            this.mInnerClipPathForBorderRadius.addRoundRect(this.mInnerClipTempRectForBorderRadius, new float[]{fMax, fMax2, fMax3, fMax4, fMax5, fMax6, fMax7, fMax8}, Path.Direction.CW);
            this.mOuterClipPathForBorderRadius.addRoundRect(this.mOuterClipTempRectForBorderRadius, new float[]{borderRadiusOrDefaultTo, borderRadiusOrDefaultTo, borderRadiusOrDefaultTo2, borderRadiusOrDefaultTo2, f5, f5, f4, f4}, Path.Direction.CW);
            float f6 = this.mBorderWidth != null ? this.mBorderWidth.get(8) / 2.0f : 0.0f;
            float f7 = borderRadiusOrDefaultTo + f6;
            float f8 = borderRadiusOrDefaultTo2 + f6;
            float f9 = f5 + f6;
            float f10 = f4 + f6;
            this.mPathForBorderRadiusOutline.addRoundRect(this.mTempRectForBorderRadiusOutline, new float[]{f7, f7, f8, f8, f9, f9, f10, f10}, Path.Direction.CW);
            this.mCenterDrawPath.addRoundRect(this.mTempRectForCenterDrawPath, new float[]{fMax + f6, fMax2 + f6, fMax3 + f6, fMax4 + f6, fMax5 + f6, fMax6 + f6, fMax7 + f6, fMax8 + f6}, Path.Direction.CW);
            if (this.mInnerTopLeftCorner == null) {
                this.mInnerTopLeftCorner = new PointF();
            }
            this.mInnerTopLeftCorner.x = this.mInnerClipTempRectForBorderRadius.left;
            this.mInnerTopLeftCorner.y = this.mInnerClipTempRectForBorderRadius.top;
            getEllipseIntersectionWithLine(this.mInnerClipTempRectForBorderRadius.left, this.mInnerClipTempRectForBorderRadius.top, this.mInnerClipTempRectForBorderRadius.left + (fMax * 2.0f), this.mInnerClipTempRectForBorderRadius.top + (fMax2 * 2.0f), this.mOuterClipTempRectForBorderRadius.left, this.mOuterClipTempRectForBorderRadius.top, this.mInnerClipTempRectForBorderRadius.left, this.mInnerClipTempRectForBorderRadius.top, this.mInnerTopLeftCorner);
            if (this.mInnerBottomLeftCorner == null) {
                this.mInnerBottomLeftCorner = new PointF();
            }
            this.mInnerBottomLeftCorner.x = this.mInnerClipTempRectForBorderRadius.left;
            this.mInnerBottomLeftCorner.y = this.mInnerClipTempRectForBorderRadius.bottom;
            getEllipseIntersectionWithLine(this.mInnerClipTempRectForBorderRadius.left, this.mInnerClipTempRectForBorderRadius.bottom - (fMax8 * 2.0f), this.mInnerClipTempRectForBorderRadius.left + (fMax7 * 2.0f), this.mInnerClipTempRectForBorderRadius.bottom, this.mOuterClipTempRectForBorderRadius.left, this.mOuterClipTempRectForBorderRadius.bottom, this.mInnerClipTempRectForBorderRadius.left, this.mInnerClipTempRectForBorderRadius.bottom, this.mInnerBottomLeftCorner);
            if (this.mInnerTopRightCorner == null) {
                this.mInnerTopRightCorner = new PointF();
            }
            this.mInnerTopRightCorner.x = this.mInnerClipTempRectForBorderRadius.right;
            this.mInnerTopRightCorner.y = this.mInnerClipTempRectForBorderRadius.top;
            getEllipseIntersectionWithLine(this.mInnerClipTempRectForBorderRadius.right - (fMax3 * 2.0f), this.mInnerClipTempRectForBorderRadius.top, this.mInnerClipTempRectForBorderRadius.right, this.mInnerClipTempRectForBorderRadius.top + (fMax4 * 2.0f), this.mOuterClipTempRectForBorderRadius.right, this.mOuterClipTempRectForBorderRadius.top, this.mInnerClipTempRectForBorderRadius.right, this.mInnerClipTempRectForBorderRadius.top, this.mInnerTopRightCorner);
            if (this.mInnerBottomRightCorner == null) {
                this.mInnerBottomRightCorner = new PointF();
            }
            this.mInnerBottomRightCorner.x = this.mInnerClipTempRectForBorderRadius.right;
            this.mInnerBottomRightCorner.y = this.mInnerClipTempRectForBorderRadius.bottom;
            getEllipseIntersectionWithLine(this.mInnerClipTempRectForBorderRadius.right - (fMax5 * 2.0f), this.mInnerClipTempRectForBorderRadius.bottom - (fMax6 * 2.0f), this.mInnerClipTempRectForBorderRadius.right, this.mInnerClipTempRectForBorderRadius.bottom, this.mOuterClipTempRectForBorderRadius.right, this.mOuterClipTempRectForBorderRadius.bottom, this.mInnerClipTempRectForBorderRadius.right, this.mInnerClipTempRectForBorderRadius.bottom, this.mInnerBottomRightCorner);
        }
    }

    private void updatePathEffect() {
        this.mPathEffectForBorderStyle = this.mBorderStyle != null ? BorderStyle.getPathEffect(this.mBorderStyle, getFullBorderWidth()) : null;
        this.mPaint.setPathEffect(this.mPathEffectForBorderStyle);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        updatePathEffect();
        drawShadowIfNeed(canvas);
        if (hasRoundedBorders()) {
            drawRoundedBackgroundWithBorders(canvas);
        } else {
            drawRectangularBackgroundWithBorders(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    public float getBorderRadius(BorderRadiusLocation borderRadiusLocation) {
        return getBorderRadiusOrDefaultTo(UNDEFINED, borderRadiusLocation);
    }

    public float getBorderRadiusOrDefaultTo(float f, BorderRadiusLocation borderRadiusLocation) {
        if (this.mBorderCornerRadii == null) {
            return f;
        }
        float f2 = this.mBorderCornerRadii[borderRadiusLocation.ordinal()];
        return isUndefined(f2) ? f : f2;
    }

    public float getBorderWidthOrDefaultTo(float f, int i) {
        if (this.mBorderWidth == null) {
            return f;
        }
        float raw = this.mBorderWidth.getRaw(i);
        return isUndefined(raw) ? f : raw;
    }

    public int getColor() {
        return this.mColor;
    }

    public RectF getDirectionAwareBorderInsets() {
        float borderWidthOrDefaultTo = getBorderWidthOrDefaultTo(0.0f, 8);
        float borderWidthOrDefaultTo2 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 1);
        float borderWidthOrDefaultTo3 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 3);
        float borderWidthOrDefaultTo4 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 0);
        float borderWidthOrDefaultTo5 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 2);
        if (Build.VERSION.SDK_INT >= 17 && this.mBorderWidth != null) {
            boolean z = getResolvedLayoutDirection() == 1;
            float raw = this.mBorderWidth.getRaw(4);
            float raw2 = this.mBorderWidth.getRaw(5);
            float f = z ? raw2 : raw;
            if (!z) {
                raw = raw2;
            }
            if (!isUndefined(f)) {
                borderWidthOrDefaultTo4 = f;
            }
            if (!isUndefined(raw)) {
                borderWidthOrDefaultTo5 = raw;
            }
        }
        return new RectF(borderWidthOrDefaultTo4, borderWidthOrDefaultTo2, borderWidthOrDefaultTo5, borderWidthOrDefaultTo3);
    }

    public float getFullBorderRadius() {
        if (isUndefined(this.mBorderRadius)) {
            return 0.0f;
        }
        return this.mBorderRadius;
    }

    public float getFullBorderWidth() {
        if (this.mBorderWidth == null || isUndefined(this.mBorderWidth.getRaw(8))) {
            return 0.0f;
        }
        return this.mBorderWidth.getRaw(8);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return TKColorUtil.getOpacityFromColor(TKColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha));
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (Build.VERSION.SDK_INT < 21) {
            super.getOutline(outline);
            return;
        }
        if ((isUndefined(this.mBorderRadius) || this.mBorderRadius <= 0.0f) && this.mBorderCornerRadii == null) {
            outline.setRect(getBounds());
        } else {
            updatePath();
            outline.setConvexPath(this.mPathForBorderRadiusOutline);
        }
    }

    public int getResolvedLayoutDirection() {
        return this.mLayoutDirection;
    }

    public boolean hasRoundedBorders() {
        if (!isUndefined(this.mBorderRadius) && this.mBorderRadius > 0.0f) {
            return true;
        }
        if (this.mBorderCornerRadii != null) {
            for (float f : this.mBorderCornerRadii) {
                if (!isUndefined(f) && f > 0.0f) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    public boolean onResolvedLayoutDirectionChanged(int i) {
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    public void setBackgroundGradientColor(int i, int[] iArr, float[] fArr) {
        this.mGradient = new Gradient();
        this.mGradient.deg = i;
        this.mGradient.colors = iArr;
        this.mGradient.positions = fArr;
        invalidateSelf();
    }

    public void setBorderColor(int i, float f, float f2) {
        setBorderRGB(i, f);
        setBorderAlpha(i, f2);
    }

    public void setBorderColor(String str) {
        this.mBorderColor = str;
    }

    public void setBorderStyle(String str) {
        BorderStyle borderStyleValueOf = str == null ? null : BorderStyle.valueOf(str.toUpperCase(Locale.US));
        if (this.mBorderStyle != borderStyleValueOf) {
            this.mBorderStyle = borderStyleValueOf;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setBorderWidth(int r2, float r3) {
        /*
            r1 = this;
            com.tachikoma.core.utility.TKSpacing r0 = r1.mBorderWidth
            if (r0 != 0) goto Lb
            com.tachikoma.core.utility.TKSpacing r0 = new com.tachikoma.core.utility.TKSpacing
            r0.<init>()
            r1.mBorderWidth = r0
        Lb:
            com.tachikoma.core.utility.TKSpacing r0 = r1.mBorderWidth
            float r0 = r0.getRaw(r2)
            boolean r0 = com.tachikoma.core.utility.TKFloatUtil.floatsEqual(r0, r3)
            if (r0 != 0) goto L2a
            com.tachikoma.core.utility.TKSpacing r0 = r1.mBorderWidth
            r0.set(r2, r3)
            r3 = 8
            if (r2 == r3) goto L24
            switch(r2) {
                case 0: goto L24;
                case 1: goto L24;
                case 2: goto L24;
                case 3: goto L24;
                case 4: goto L24;
                case 5: goto L24;
                default: goto L23;
            }
        L23:
            goto L27
        L24:
            r2 = 1
            r1.mNeedUpdatePathForBorderRadius = r2
        L27:
            r1.invalidateSelf()
        L2a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.view.TKViewBackgroundDrawable.setBorderWidth(int, float):void");
    }

    public void setColor(int i) {
        this.mColor = i;
        this.mGradient = null;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setRadius(float f) {
        if (TKFloatUtil.floatsEqual(this.mBorderRadius, f)) {
            return;
        }
        this.mBorderRadius = f;
        this.mNeedUpdatePathForBorderRadius = true;
        invalidateSelf();
    }

    public void setRadius(float f, int i) {
        if (this.mBorderCornerRadii == null) {
            this.mBorderCornerRadii = new float[8];
            Arrays.fill(this.mBorderCornerRadii, UNDEFINED);
        }
        if (TKFloatUtil.floatsEqual(this.mBorderCornerRadii[i], f)) {
            return;
        }
        this.mBorderCornerRadii[i] = f;
        this.mNeedUpdatePathForBorderRadius = true;
        invalidateSelf();
    }

    public boolean setResolvedLayoutDirection(int i) {
        if (this.mLayoutDirection == i) {
            return false;
        }
        this.mLayoutDirection = i;
        return onResolvedLayoutDirectionChanged(i);
    }

    public void setShadow(float f, float f2, float f3, int i) {
        this.mShadow = new Shadow(f, f2, f3, i);
        this.mNeedUpdatePathForBorderRadius = true;
        invalidateSelf();
    }
}
