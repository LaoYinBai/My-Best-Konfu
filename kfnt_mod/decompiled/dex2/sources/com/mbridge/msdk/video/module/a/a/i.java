package com.mbridge.msdk.video.module.a.a;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.r;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: RoundedImageLoaderListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class i extends d {
    private int a;

    public i(ImageView imageView, int i) {
        super(imageView);
        this.a = i;
    }

    @Override // com.mbridge.msdk.video.module.a.a.d, com.mbridge.msdk.foundation.same.c.c
    public final void onSuccessLoad(Bitmap bitmap, String str) {
        Bitmap bitmapA;
        try {
            if (bitmap == null) {
                s.d("ImageLoaderListener", "bitmap=null");
            } else {
                if (this.b == null || bitmap.isRecycled() || (bitmapA = r.a(bitmap, 1, this.a)) == null) {
                    return;
                }
                this.b.setImageBitmap(bitmapA);
            }
        } catch (Throwable th) {
            if (MBridgeConstans.DEBUG) {
                th.printStackTrace();
            }
        }
    }
}
