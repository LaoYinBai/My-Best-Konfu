package com.mbridge.msdk.mbbid.out;

import com.mbridge.msdk.foundation.controller.a;
import com.mbridge.msdk.foundation.tools.o;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SplashBidRequestParams extends BannerBidRequestParams {
    private static int b = 1;
    private static int c;
    private static int d;
    private boolean a;

    public SplashBidRequestParams(String str, String str2) {
        this(str, str2, "");
    }

    public SplashBidRequestParams(String str, String str2, String str3) {
        this(str, str2, str3, false, b, d, c);
    }

    public SplashBidRequestParams(String str, String str2, boolean z, int i, int i2, int i3) {
        this(str, str2, "", z, i, i3, i2);
    }

    public SplashBidRequestParams(String str, String str2, String str3, boolean z, int i, int i2, int i3) {
        super(str, str2, str3, 0, 0);
        this.a = false;
        b = i;
        int iW = o.w(a.f().j());
        int iX = o.x(a.f().j());
        if (b == 1) {
            if (iX > i3 * 4) {
                setHeight(iX - i3);
                setWidth(iW);
            } else {
                setHeight(0);
                setWidth(0);
            }
        } else if (b == 2) {
            if (iW > i2 * 4) {
                setWidth(iW - i2);
                setHeight(iX);
            } else {
                setHeight(0);
                setWidth(0);
            }
        }
        this.a = z;
    }

    protected final boolean a() {
        return this.a;
    }

    public int getOrientation() {
        return b;
    }
}
