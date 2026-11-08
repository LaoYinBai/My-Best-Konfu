package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import java.util.concurrent.locks.Lock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class a {
    private static final String a = "DrawableToBitmap";
    private static final BitmapPool b = new BitmapPoolAdapter() { // from class: com.ss.union.game.sdk.core.glide.load.resource.bitmap.a.1
        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPoolAdapter, com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
        public void put(Bitmap bitmap) {
        }
    };

    private a() {
    }

    static Resource<Bitmap> a(BitmapPool bitmapPool, Drawable drawable, int i, int i2) {
        Bitmap bitmapB;
        Drawable current = drawable.getCurrent();
        boolean z = false;
        if (current instanceof BitmapDrawable) {
            bitmapB = ((BitmapDrawable) current).getBitmap();
        } else if (current instanceof Animatable) {
            bitmapB = null;
        } else {
            bitmapB = b(bitmapPool, current, i, i2);
            z = true;
        }
        if (!z) {
            bitmapPool = b;
        }
        return BitmapResource.obtain(bitmapB, bitmapPool);
    }

    private static Bitmap b(BitmapPool bitmapPool, Drawable drawable, int i, int i2) {
        if (i == Integer.MIN_VALUE && drawable.getIntrinsicWidth() <= 0) {
            if (Log.isLoggable(a, 5)) {
                Log.w(a, "Unable to draw " + drawable + " to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic width");
            }
            return null;
        }
        if (i2 == Integer.MIN_VALUE && drawable.getIntrinsicHeight() <= 0) {
            if (Log.isLoggable(a, 5)) {
                Log.w(a, "Unable to draw " + drawable + " to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic height");
            }
            return null;
        }
        if (drawable.getIntrinsicWidth() > 0) {
            i = drawable.getIntrinsicWidth();
        }
        if (drawable.getIntrinsicHeight() > 0) {
            i2 = drawable.getIntrinsicHeight();
        }
        Lock bitmapDrawableLock = TransformationUtils.getBitmapDrawableLock();
        bitmapDrawableLock.lock();
        Bitmap bitmap = bitmapPool.get(i, i2, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, i, i2);
            drawable.draw(canvas);
            canvas.setBitmap(null);
            return bitmap;
        } finally {
            bitmapDrawableLock.unlock();
        }
    }
}
