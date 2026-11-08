package com.sigmob.sdk.videoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VideoPlayerTextureView extends TextureView {
    public int a;
    public int b;

    public VideoPlayerTextureView(Context context) {
        super(context);
        this.a = 0;
        this.b = 0;
        this.a = 0;
        this.b = 0;
    }

    public VideoPlayerTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        this.b = 0;
        this.a = 0;
        this.b = 0;
    }

    public void a(int i, int i2) {
        if (this.a == i && this.b == i2) {
            return;
        }
        this.a = i;
        this.b = i2;
        requestLayout();
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ee, code lost:
    
        if (r6 > r11) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f0, code lost:
    
        r6 = (r11 * r3) / r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0100, code lost:
    
        if (r6 > r11) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0142  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r18, int r19) {
        /*
            Method dump skipped, instructions count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.videoplayer.VideoPlayerTextureView.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public void setRotation(float f) {
        if (f != getRotation()) {
            super.setRotation(f);
            requestLayout();
        }
    }
}
