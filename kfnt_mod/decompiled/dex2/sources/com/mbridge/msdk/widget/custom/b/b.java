package com.mbridge.msdk.widget.custom.b;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.widget.custom.DownloadMessageDialog;
import com.mbridge.msdk.widget.custom.baseview.MBImageView;
import java.lang.reflect.Array;

/* compiled from: FastBlurUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    public static String a = "FastBlurUtil";
    public static int b = 2048;

    public static Bitmap a(Bitmap bitmap, int i) {
        int i2;
        int[] iArr;
        int i3 = i <= 0 ? 10 : i;
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / i3, bitmap.getHeight() / i3, false);
        int width = bitmapCreateScaledBitmap.getWidth();
        int height = bitmapCreateScaledBitmap.getHeight();
        int i4 = width * height;
        int[] iArr2 = new int[i4];
        bitmapCreateScaledBitmap.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i5 = width - 1;
        int i6 = height - 1;
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[i4];
        int[] iArr5 = new int[i4];
        int[] iArr6 = new int[Math.max(width, height)];
        int[] iArr7 = new int[20736];
        for (int i7 = 0; i7 < 20736; i7++) {
            iArr7[i7] = i7 / 81;
        }
        int[][] iArr8 = (int[][]) Array.newInstance((Class<?>) int.class, 17, 3);
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            if (i8 >= height) {
                break;
            }
            int i11 = -8;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            for (int i21 = 8; i11 <= i21; i21 = 8) {
                Bitmap bitmap2 = bitmapCreateScaledBitmap;
                int i22 = iArr2[Math.min(i5, Math.max(i11, 0)) + i9];
                int[] iArr9 = iArr8[i11 + 8];
                iArr9[0] = (i22 & 16711680) >> 16;
                iArr9[1] = (i22 & 65280) >> 8;
                iArr9[2] = i22 & 255;
                int iAbs = 9 - Math.abs(i11);
                i12 += iArr9[0] * iAbs;
                i13 += iArr9[1] * iAbs;
                i14 += iArr9[2] * iAbs;
                if (i11 > 0) {
                    i15 += iArr9[0];
                    i16 += iArr9[1];
                    i17 += iArr9[2];
                } else {
                    i18 += iArr9[0];
                    i19 += iArr9[1];
                    i20 += iArr9[2];
                }
                i11++;
                bitmapCreateScaledBitmap = bitmap2;
            }
            Bitmap bitmap3 = bitmapCreateScaledBitmap;
            int i23 = 0;
            int i24 = 8;
            while (i23 < width) {
                iArr3[i9] = iArr7[i12];
                iArr4[i9] = iArr7[i13];
                iArr5[i9] = iArr7[i14];
                int i25 = i12 - i18;
                int i26 = i13 - i19;
                int i27 = i14 - i20;
                int[] iArr10 = iArr8[((i24 - 8) + 17) % 17];
                int i28 = i18 - iArr10[0];
                int i29 = i19 - iArr10[1];
                int i30 = i20 - iArr10[2];
                if (i8 == 0) {
                    iArr = iArr7;
                    iArr6[i23] = Math.min(i23 + 8 + 1, i5);
                } else {
                    iArr = iArr7;
                }
                int i31 = iArr2[i10 + iArr6[i23]];
                iArr10[0] = (i31 & 16711680) >> 16;
                iArr10[1] = (i31 & 65280) >> 8;
                iArr10[2] = i31 & 255;
                int i32 = i15 + iArr10[0];
                int i33 = i16 + iArr10[1];
                int i34 = i17 + iArr10[2];
                i12 = i25 + i32;
                i13 = i26 + i33;
                i14 = i27 + i34;
                i24 = (i24 + 1) % 17;
                int[] iArr11 = iArr8[i24 % 17];
                i18 = i28 + iArr11[0];
                i19 = i29 + iArr11[1];
                i20 = i30 + iArr11[2];
                i15 = i32 - iArr11[0];
                i16 = i33 - iArr11[1];
                i17 = i34 - iArr11[2];
                i9++;
                i23++;
                iArr7 = iArr;
            }
            i10 += width;
            i8++;
            bitmapCreateScaledBitmap = bitmap3;
        }
        Bitmap bitmap4 = bitmapCreateScaledBitmap;
        int[] iArr12 = iArr7;
        int i35 = 0;
        while (i35 < width) {
            int i36 = (-8) * width;
            int[] iArr13 = iArr6;
            int i37 = -8;
            int i38 = 0;
            int i39 = 0;
            int i40 = 0;
            int i41 = 0;
            int i42 = 0;
            int i43 = 0;
            int i44 = 0;
            int i45 = 0;
            int i46 = 0;
            for (int i47 = 8; i37 <= i47; i47 = 8) {
                int iMax = Math.max(0, i36) + i35;
                int[] iArr14 = iArr8[i37 + 8];
                iArr14[0] = iArr3[iMax];
                iArr14[1] = iArr4[iMax];
                iArr14[2] = iArr5[iMax];
                int iAbs2 = 9 - Math.abs(i37);
                i38 += iArr3[iMax] * iAbs2;
                i39 += iArr4[iMax] * iAbs2;
                i40 += iArr5[iMax] * iAbs2;
                if (i37 > 0) {
                    i41 += iArr14[0];
                    i42 += iArr14[1];
                    i43 += iArr14[2];
                } else {
                    i44 += iArr14[0];
                    i45 += iArr14[1];
                    i46 += iArr14[2];
                }
                if (i37 < i6) {
                    i36 += width;
                }
                i37++;
            }
            int i48 = i35;
            int i49 = 0;
            int i50 = 8;
            while (i49 < height) {
                iArr2[i48] = (iArr2[i48] & (-16777216)) | (iArr12[i38] << 16) | (iArr12[i39] << 8) | iArr12[i40];
                int i51 = i38 - i44;
                int i52 = i39 - i45;
                int i53 = i40 - i46;
                int[] iArr15 = iArr8[((i50 - 8) + 17) % 17];
                int i54 = i44 - iArr15[0];
                int i55 = i45 - iArr15[1];
                int i56 = i46 - iArr15[2];
                if (i35 == 0) {
                    i2 = height;
                    iArr13[i49] = Math.min(i49 + 9, i6) * width;
                } else {
                    i2 = height;
                }
                int i57 = iArr13[i49] + i35;
                iArr15[0] = iArr3[i57];
                iArr15[1] = iArr4[i57];
                iArr15[2] = iArr5[i57];
                int i58 = i41 + iArr15[0];
                int i59 = i42 + iArr15[1];
                int i60 = i43 + iArr15[2];
                i38 = i51 + i58;
                i39 = i52 + i59;
                i40 = i53 + i60;
                i50 = (i50 + 1) % 17;
                int[] iArr16 = iArr8[i50];
                i44 = i54 + iArr16[0];
                i45 = i55 + iArr16[1];
                i46 = i56 + iArr16[2];
                i41 = i58 - iArr16[0];
                i42 = i59 - iArr16[1];
                i43 = i60 - iArr16[2];
                i48 += width;
                i49++;
                height = i2;
            }
            i35++;
            iArr6 = iArr13;
            height = height;
        }
        bitmap4.setPixels(iArr2, 0, width, 0, 0, width, height);
        return bitmap4;
    }

    public static void a(final MBImageView mBImageView, String str) {
        com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(str, new com.mbridge.msdk.foundation.same.c.c() { // from class: com.mbridge.msdk.widget.custom.b.b.1
            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onSuccessLoad(final Bitmap bitmap, String str2) {
                mBImageView.post(new Runnable() { // from class: com.mbridge.msdk.widget.custom.b.b.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (mBImageView == null || bitmap == null || bitmap.isRecycled()) {
                            return;
                        }
                        mBImageView.setImageBitmap(bitmap);
                    }
                });
            }

            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onFailedLoad(String str2, String str3) {
                mBImageView.post(new Runnable() { // from class: com.mbridge.msdk.widget.custom.b.b.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        mBImageView.setBackgroundColor(Color.parseColor("#e6e6e6"));
                    }
                });
            }
        });
    }

    /* compiled from: FastBlurUtil.java */
    /* renamed from: com.mbridge.msdk.widget.custom.b.b$2, reason: invalid class name */
    final class AnonymousClass2 implements com.mbridge.msdk.foundation.same.c.c {
        final /* synthetic */ MBImageView a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ ImageView d;
        final /* synthetic */ DownloadMessageDialog e;

        AnonymousClass2(MBImageView mBImageView, int i, int i2, ImageView imageView, DownloadMessageDialog downloadMessageDialog) {
            this.a = mBImageView;
            this.b = i;
            this.c = i2;
            this.d = imageView;
            this.e = downloadMessageDialog;
        }

        @Override // com.mbridge.msdk.foundation.same.c.c
        public final void onSuccessLoad(final Bitmap bitmap, String str) {
            this.a.post(new Runnable() { // from class: com.mbridge.msdk.widget.custom.b.b.2.1
                @Override // java.lang.Runnable
                public final void run() {
                    if (bitmap == null || bitmap.isRecycled()) {
                        return;
                    }
                    if (bitmap.getWidth() >= bitmap.getHeight()) {
                        if (AnonymousClass2.this.b >= AnonymousClass2.this.c) {
                            b.a(bitmap, AnonymousClass2.this.a, AnonymousClass2.this.b, AnonymousClass2.this.d);
                        } else {
                            b.b(bitmap, AnonymousClass2.this.a, AnonymousClass2.this.c, AnonymousClass2.this.d);
                        }
                        AnonymousClass2.this.e.registerOrientationListener(new DownloadMessageDialog.a() { // from class: com.mbridge.msdk.widget.custom.b.b.2.1.1
                            @Override // com.mbridge.msdk.widget.custom.DownloadMessageDialog.a
                            public final void a(int i) {
                                if (i == 1) {
                                    b.a(bitmap, AnonymousClass2.this.a, AnonymousClass2.this.b, AnonymousClass2.this.d);
                                } else {
                                    b.b(bitmap, AnonymousClass2.this.a, AnonymousClass2.this.c, AnonymousClass2.this.d);
                                }
                            }
                        });
                        return;
                    }
                    b.c(bitmap, AnonymousClass2.this.a, AnonymousClass2.this.b, AnonymousClass2.this.d);
                }
            });
        }

        @Override // com.mbridge.msdk.foundation.same.c.c
        public final void onFailedLoad(String str, String str2) {
            this.a.post(new Runnable() { // from class: com.mbridge.msdk.widget.custom.b.b.2.2
                @Override // java.lang.Runnable
                public final void run() {
                    if (AnonymousClass2.this.d != null) {
                        AnonymousClass2.this.d.setBackgroundColor(Color.parseColor("#e6e6e6"));
                    }
                }
            });
        }
    }

    public static void a(MBImageView mBImageView, ImageView imageView, String str, int i, int i2, DownloadMessageDialog downloadMessageDialog) {
        com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(str, new AnonymousClass2(mBImageView, i, i2, imageView, downloadMessageDialog));
    }

    private static void a(ImageView imageView, Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (bitmap.isRecycled()) {
                    return;
                }
                Bitmap bitmapA = a(bitmap, 5);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageBitmap(bitmapA);
            } catch (Throwable th) {
                s.d(a, th.getMessage());
            }
        }
    }

    public static void a(Bitmap bitmap, ImageView imageView, int i, ImageView imageView2) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    imageView.setImageBitmap(bitmap);
                }
            } catch (Throwable th) {
                s.d(a, th.getMessage());
                return;
            }
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        int iC = i - com.mbridge.msdk.widget.custom.a.b.a().c("20dp");
        int i2 = (int) (iC * 0.5225f);
        if (imageView2 != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView2.getLayoutParams();
            layoutParams.width = iC;
            layoutParams.height = i2;
            imageView2.setLayoutParams(layoutParams);
            a(imageView2, bitmap);
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams2.width = iC;
        layoutParams2.height = i2;
        layoutParams2.setMargins(com.mbridge.msdk.widget.custom.a.b.a().c("1dp"), com.mbridge.msdk.widget.custom.a.b.a().c("15dp"), com.mbridge.msdk.widget.custom.a.b.a().c("1dp"), com.mbridge.msdk.widget.custom.a.b.a().c("15dp"));
        layoutParams2.addRule(9, -1);
        layoutParams2.addRule(11, -1);
        imageView.setLayoutParams(layoutParams2);
    }

    public static void b(Bitmap bitmap, ImageView imageView, int i, ImageView imageView2) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    imageView.setImageBitmap(bitmap);
                }
            } catch (Throwable th) {
                s.d(a, th.getMessage());
                return;
            }
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        int iC = (int) ((i * 0.5d) - com.mbridge.msdk.widget.custom.a.b.a().c("20dp"));
        int i2 = (int) (iC * 0.5225f);
        if (imageView2 != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView2.getLayoutParams();
            layoutParams.width = iC;
            layoutParams.height = i2;
            imageView2.setLayoutParams(layoutParams);
            a(imageView2, bitmap);
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams2.width = iC;
        layoutParams2.height = i2;
        layoutParams2.setMargins(com.mbridge.msdk.widget.custom.a.b.a().c("1dp"), com.mbridge.msdk.widget.custom.a.b.a().c("15dp"), com.mbridge.msdk.widget.custom.a.b.a().c("1dp"), com.mbridge.msdk.widget.custom.a.b.a().c("15dp"));
        layoutParams2.addRule(9, -1);
        layoutParams2.addRule(11, -1);
        imageView.setLayoutParams(layoutParams2);
    }

    public static void c(Bitmap bitmap, ImageView imageView, int i, ImageView imageView2) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    imageView.setImageBitmap(bitmap);
                }
            } catch (Throwable th) {
                s.d(a, th.getMessage());
                return;
            }
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        int iC = i - com.mbridge.msdk.widget.custom.a.b.a().c("20dp");
        int i2 = (int) (iC * 0.5225f);
        if (imageView2 != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView2.getLayoutParams();
            layoutParams.width = iC;
            layoutParams.height = i2;
            imageView2.setLayoutParams(layoutParams);
            a(imageView2, bitmap);
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams2.height = i2;
        layoutParams2.addRule(10, -1);
        layoutParams2.addRule(12, -1);
        imageView.setLayoutParams(layoutParams2);
    }
}
