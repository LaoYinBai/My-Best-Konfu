package com.sigmob.sdk.blurkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static a a;
    private static RenderScript b;

    public static a a() {
        if (a != null) {
            return a;
        }
        throw new RuntimeException("BlurKit not initialized!");
    }

    public static void a(Context context) {
        if (a != null) {
            return;
        }
        a = new a();
        b = RenderScript.create(context.getApplicationContext());
    }

    public Bitmap a(Bitmap bitmap, int i) {
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(b, bitmap);
        Allocation allocationCreateTyped = Allocation.createTyped(b, allocationCreateFromBitmap.getType());
        if (Build.VERSION.SDK_INT >= 17) {
            ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(b, Element.U8_4(b));
            scriptIntrinsicBlurCreate.setRadius(i);
            scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
            scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
        }
        allocationCreateTyped.copyTo(bitmap);
        return bitmap;
    }
}
