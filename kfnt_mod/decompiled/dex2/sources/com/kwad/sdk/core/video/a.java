package com.kwad.sdk.core.video;

import android.content.Context;
import android.view.TextureView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends TextureView {
    private int a;
    private int b;

    public a(Context context) {
        super(context);
    }

    public void a(int i, int i2) {
        if (this.b == i || this.a == i2) {
            return;
        }
        this.b = i;
        this.a = i2;
        requestLayout();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0059, code lost:
    
        if ((r6.b * r8) > (r6.a * r7)) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005b, code lost:
    
        r1 = (r6.a * r7) / r6.b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0081, code lost:
    
        if (r1 > r7) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x009a, code lost:
    
        if (r1 > r7) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0047  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            float r0 = r6.getRotation()
            r1 = 1119092736(0x42b40000, float:90.0)
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 == 0) goto L10
            r1 = 1132920832(0x43870000, float:270.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L13
        L10:
            r5 = r8
            r8 = r7
            r7 = r5
        L13:
            int r0 = r6.b
            int r0 = getDefaultSize(r0, r7)
            int r1 = r6.a
            int r1 = getDefaultSize(r1, r8)
            int r2 = r6.b
            if (r2 <= 0) goto L9d
            int r2 = r6.a
            if (r2 <= 0) goto L9d
            int r0 = android.view.View.MeasureSpec.getMode(r7)
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            int r8 = android.view.View.MeasureSpec.getSize(r8)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 != r2) goto L64
            if (r1 != r2) goto L64
            int r0 = r6.b
            int r0 = r0 * r8
            int r1 = r6.a
            int r1 = r1 * r7
            if (r0 >= r1) goto L51
        L47:
            int r7 = r6.b
            int r7 = r7 * r8
            int r0 = r6.a
            int r0 = r7 / r0
            r7 = r0
            goto L9f
        L51:
            int r0 = r6.b
            int r0 = r0 * r8
            int r1 = r6.a
            int r1 = r1 * r7
            if (r0 <= r1) goto L9f
        L5b:
            int r8 = r6.a
            int r8 = r8 * r7
            int r0 = r6.b
            int r1 = r8 / r0
            goto L9e
        L64:
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r2) goto L76
            int r0 = r6.a
            int r0 = r0 * r7
            int r2 = r6.b
            int r0 = r0 / r2
            if (r1 != r3) goto L74
            if (r0 <= r8) goto L74
            goto L47
        L74:
            r8 = r0
            goto L9f
        L76:
            if (r1 != r2) goto L86
            int r1 = r6.b
            int r1 = r1 * r8
            int r2 = r6.a
            int r1 = r1 / r2
            if (r0 != r3) goto L84
            if (r1 <= r7) goto L84
            goto L5b
        L84:
            r7 = r1
            goto L9f
        L86:
            int r2 = r6.b
            int r4 = r6.a
            if (r1 != r3) goto L96
            if (r4 <= r8) goto L96
            int r1 = r6.b
            int r1 = r1 * r8
            int r2 = r6.a
            int r1 = r1 / r2
            goto L98
        L96:
            r1 = r2
            r8 = r4
        L98:
            if (r0 != r3) goto L84
            if (r1 <= r7) goto L84
            goto L5b
        L9d:
            r7 = r0
        L9e:
            r8 = r1
        L9f:
            r6.setMeasuredDimension(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.video.a.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public void setRotation(float f) {
        if (f != getRotation()) {
            super.setRotation(f);
            requestLayout();
        }
    }
}
