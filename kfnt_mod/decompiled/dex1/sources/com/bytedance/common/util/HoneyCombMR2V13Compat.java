package com.bytedance.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class HoneyCombMR2V13Compat {
    static final BaseImpl IMPL;

    private static class BaseImpl {
        private BaseImpl() {
        }

        public void getDisplaySize(Display display, Point point) {
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
    }

    private static class HoneyCombMR2Impl extends BaseImpl {
        private HoneyCombMR2Impl() {
            super();
        }

        @Override // com.bytedance.common.util.HoneyCombMR2V13Compat.BaseImpl
        @TargetApi(13)
        public void getDisplaySize(Display display, Point point) {
            display.getSize(point);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 13) {
            IMPL = new HoneyCombMR2Impl();
        } else {
            IMPL = new BaseImpl();
        }
    }

    public static void getDisplaySize(Display display, Point point) {
        IMPL.getDisplaySize(display, point);
    }

    public static int getDisplayWidth(Display display) {
        Point point = new Point();
        getDisplaySize(display, point);
        return point.x;
    }

    public static void getDisplaySize(Context context, Point point) {
        getDisplaySize(((WindowManager) context.getSystemService("window")).getDefaultDisplay(), point);
    }
}
