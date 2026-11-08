package com.tachikoma.core.yoga.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class YogaViewLayoutFactory implements LayoutInflater.Factory {
    private static YogaViewLayoutFactory sYogaViewLayoutFactory;

    YogaViewLayoutFactory() {
    }

    public static YogaViewLayoutFactory getInstance() {
        if (sYogaViewLayoutFactory == null) {
            sYogaViewLayoutFactory = new YogaViewLayoutFactory();
        }
        return sYogaViewLayoutFactory;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        if (YogaLayout.class.getSimpleName().equals(str)) {
            return new YogaLayout(context, attributeSet);
        }
        if (VirtualYogaLayout.class.getSimpleName().equals(str)) {
            return new VirtualYogaLayout(context, attributeSet);
        }
        return null;
    }
}
