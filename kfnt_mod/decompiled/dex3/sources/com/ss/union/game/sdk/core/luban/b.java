package com.ss.union.game.sdk.core.luban;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b {
    private InputStreamProvider a;
    private File b;
    private int c;
    private int d;
    private boolean e;

    b(InputStreamProvider inputStreamProvider, File file, boolean z) throws IOException {
        this.b = file;
        this.a = inputStreamProvider;
        this.e = z;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeStream(inputStreamProvider.open(), null, options);
        this.c = options.outWidth;
        this.d = options.outHeight;
    }

    private int b() {
        this.c = this.c % 2 == 1 ? this.c + 1 : this.c;
        this.d = this.d % 2 == 1 ? this.d + 1 : this.d;
        int iMax = Math.max(this.c, this.d);
        float fMin = Math.min(this.c, this.d) / iMax;
        if (fMin > 1.0f || fMin <= 0.5625d) {
            double d = fMin;
            if (d <= 0.5625d && d > 0.5d) {
                int i = iMax / 1280;
                if (i == 0) {
                    return 1;
                }
                return i;
            }
            return (int) Math.ceil(iMax / (1280.0d / d));
        }
        if (iMax < 1664) {
            return 1;
        }
        if (iMax < 4990) {
            return 2;
        }
        if (iMax > 4990 && iMax < 10240) {
            return 4;
        }
        int i2 = iMax / 1280;
        if (i2 == 0) {
            return 1;
        }
        return i2;
    }

    private Bitmap a(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    File a() throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = b();
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(this.a.open(), null, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (a.SINGLE.a(this.a.open())) {
            bitmapDecodeStream = a(bitmapDecodeStream, a.SINGLE.b(this.a.open()));
        }
        bitmapDecodeStream.compress(this.e ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
        bitmapDecodeStream.recycle();
        FileOutputStream fileOutputStream = new FileOutputStream(this.b);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.flush();
        fileOutputStream.close();
        byteArrayOutputStream.close();
        return this.b;
    }
}
