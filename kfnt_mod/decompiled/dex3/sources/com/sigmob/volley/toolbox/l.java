package com.sigmob.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import com.sigmob.volley.l;
import com.sigmob.volley.n;
import com.sigmob.volley.p;
import com.sigmob.volley.t;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l extends com.sigmob.volley.l<Bitmap> {
    private static final Object b = new Object();
    private final Object c;
    private final Bitmap.Config d;
    private final int e;
    private final int f;
    private final ImageView.ScaleType g;
    private n.b<Bitmap> h;

    public l(String str, n.b<Bitmap> bVar, int i, int i2, ImageView.ScaleType scaleType, Bitmap.Config config, n.a aVar) {
        super(0, str, aVar);
        this.c = new Object();
        a((p) new com.sigmob.volley.c(1000, 2, 2.0f));
        this.h = bVar;
        this.d = config;
        this.e = i;
        this.f = i2;
        this.g = scaleType;
    }

    static int a(int i, int i2, int i3, int i4) {
        double dMin = Math.min(i / i3, i2 / i4);
        float f = 1.0f;
        while (true) {
            float f2 = 2.0f * f;
            if (f2 > dMin) {
                return (int) f;
            }
            f = f2;
        }
    }

    private static int a(int i, int i2, int i3, int i4, ImageView.ScaleType scaleType) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (scaleType == ImageView.ScaleType.FIT_XY) {
            return i == 0 ? i3 : i;
        }
        if (i == 0) {
            return (int) (i3 * (i2 / i4));
        }
        if (i2 == 0) {
            return i;
        }
        double d = i4 / i3;
        if (scaleType == ImageView.ScaleType.CENTER_CROP) {
            double d2 = i2;
            return ((double) i) * d < d2 ? (int) (d2 / d) : i;
        }
        double d3 = i2;
        return ((double) i) * d > d3 ? (int) (d3 / d) : i;
    }

    private com.sigmob.volley.n<Bitmap> b(com.sigmob.volley.i iVar) {
        Bitmap bitmapDecodeByteArray;
        byte[] bArr = iVar.b;
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (this.e == 0 && this.f == 0) {
            options.inPreferredConfig = this.d;
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            int iA = a(this.e, this.f, i, i2, this.g);
            int iA2 = a(this.f, this.e, i2, i, this.g);
            options.inJustDecodeBounds = false;
            options.inSampleSize = a(i, i2, iA, iA2);
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (bitmapDecodeByteArray != null && (bitmapDecodeByteArray.getWidth() > iA || bitmapDecodeByteArray.getHeight() > iA2)) {
                Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeByteArray, iA, iA2, true);
                bitmapDecodeByteArray.recycle();
                bitmapDecodeByteArray = bitmapCreateScaledBitmap;
            }
        }
        return bitmapDecodeByteArray == null ? com.sigmob.volley.n.a(new com.sigmob.volley.k(iVar)) : com.sigmob.volley.n.a(bitmapDecodeByteArray, h.a(iVar));
    }

    @Override // com.sigmob.volley.l
    protected com.sigmob.volley.n<Bitmap> a(com.sigmob.volley.i iVar) {
        com.sigmob.volley.n<Bitmap> nVarB;
        synchronized (b) {
            try {
                try {
                    nVarB = b(iVar);
                } catch (OutOfMemoryError e) {
                    t.c("Caught OOM for %d byte image, url=%s", Integer.valueOf(iVar.b.length), h());
                    return com.sigmob.volley.n.a(new com.sigmob.volley.k(e));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return nVarB;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sigmob.volley.l
    public void a(Bitmap bitmap) {
        n.b<Bitmap> bVar;
        synchronized (this.c) {
            bVar = this.h;
        }
        if (bVar != null) {
            bVar.a(bitmap);
        }
    }

    @Override // com.sigmob.volley.l
    public void j() {
        super.j();
        synchronized (this.c) {
            this.h = null;
        }
    }

    @Override // com.sigmob.volley.l
    public l.b o() {
        return l.b.LOW;
    }
}
