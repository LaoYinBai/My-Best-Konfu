package com.sigmob.sdk.base.models;

import android.graphics.Rect;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ExposureChange {
    float a;
    Rect b;
    List c;

    public ExposureChange(float f, Rect rect, List list) {
        this.a = f;
        this.b = rect;
        this.c = list;
    }

    public String toString() {
        return "\"exposureChange\"={\"exposedPercentage\"=" + this.a + ", \"visibleRectangle\"={\"x\"=" + this.b.left + ",\"y\"=" + this.b.top + ",\"width\"=" + this.b.width() + ",\"height\"=" + this.b.height() + "}, \"occlusionRectangles\"=[]}";
    }
}
