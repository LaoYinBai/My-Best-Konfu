package com.sigmob.sdk.common.e;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.sigmob.volley.toolbox.k;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class e extends com.sigmob.volley.toolbox.k {
    private final int a;

    e(com.sigmob.volley.m mVar, Context context, k.b bVar) {
        int iMin;
        super(mVar, bVar);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            iMin = 320;
        } else {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            iMin = Math.min(point.x, point.y);
        }
        this.a = iMin;
    }

    @Override // com.sigmob.volley.toolbox.k
    public k.c a(String str, k.d dVar) {
        return super.a(str, dVar, this.a, 0);
    }
}
