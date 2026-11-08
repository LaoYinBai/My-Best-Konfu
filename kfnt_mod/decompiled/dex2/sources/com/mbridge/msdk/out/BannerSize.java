package com.mbridge.msdk.out;

import com.mbridge.msdk.foundation.controller.a;
import com.mbridge.msdk.foundation.tools.o;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BannerSize {
    public static final int DEV_SET_TYPE = 5;
    public static final int LARGE_TYPE = 1;
    public static final int MEDIUM_TYPE = 2;
    public static final int SMART_TYPE = 3;
    public static final int STANDARD_TYPE = 4;
    private int height;
    private int width;

    public BannerSize(int i, int i2, int i3) {
        switch (i) {
            case 1:
                this.height = 90;
                this.width = 320;
                break;
            case 2:
                this.height = 250;
                this.width = 300;
                break;
            case 3:
                setSmartMode();
                break;
            case 4:
                this.height = 50;
                this.width = 320;
                break;
            case 5:
                this.height = i3;
                this.width = i2;
                break;
        }
    }

    private void setSmartMode() {
        if (o.x(a.f().j()) < 720) {
            this.height = 50;
            this.width = 320;
        } else {
            this.height = 90;
            this.width = 728;
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
