package com.ss.union.game.sdk.ad.ad_mediation.dto;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGMediationAdBaseConfigAdDTO {
    public static final int ORIENTATION_HORIZONTAL = 2;
    public static final int ORIENTATION_VERTICAL = 1;
    public String codeID;
    public Context context;
    public ExpectedImageSize expectedImageSize;
    public boolean isPopDownLoadWindow;
    public int videoPlayOrientation = 2;

    public static class ExpectedImageSize {
        public int height;
        public int width;

        public ExpectedImageSize(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }
}
