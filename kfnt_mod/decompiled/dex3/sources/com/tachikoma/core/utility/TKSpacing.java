package com.tachikoma.core.utility;

import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKSpacing {
    public static final int ALL = 8;
    public static final int BOTTOM = 3;
    public static final int END = 5;
    public static final int HORIZONTAL = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    public static final int START = 4;
    public static final int TOP = 1;
    public static final int VERTICAL = 7;
    private final float mDefaultValue;
    private boolean mHasAliasesSet;
    private final float[] mSpacing;
    private int mValueFlags;
    private static final int[] sFlagsMap = {1, 2, 4, 8, 16, 32, 64, 128, ApkDownloadComplianceInterface.INSTALL_BITS};
    private static float UNDEFINED = 1.0E21f;

    public TKSpacing() {
        this(0.0f);
    }

    public TKSpacing(float f) {
        this.mValueFlags = 0;
        this.mDefaultValue = f;
        this.mSpacing = newFullSpacingArray();
    }

    public TKSpacing(TKSpacing tKSpacing) {
        this.mValueFlags = 0;
        this.mDefaultValue = tKSpacing.mDefaultValue;
        this.mSpacing = Arrays.copyOf(tKSpacing.mSpacing, tKSpacing.mSpacing.length);
        this.mValueFlags = tKSpacing.mValueFlags;
        this.mHasAliasesSet = tKSpacing.mHasAliasesSet;
    }

    public static boolean isUndefined(float f) {
        return Float.compare(f, UNDEFINED) == 0;
    }

    private static float[] newFullSpacingArray() {
        return new float[]{UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED};
    }

    public float get(int i) {
        float f = (i == 4 || i == 5) ? UNDEFINED : this.mDefaultValue;
        if (this.mValueFlags == 0) {
            return f;
        }
        if ((this.mValueFlags & sFlagsMap[i]) != 0) {
            return this.mSpacing[i];
        }
        if (this.mHasAliasesSet) {
            char c = (i == 1 || i == 3) ? (char) 7 : (char) 6;
            if ((this.mValueFlags & sFlagsMap[c]) != 0) {
                return this.mSpacing[c];
            }
            if ((this.mValueFlags & sFlagsMap[8]) != 0) {
                return this.mSpacing[8];
            }
        }
        return f;
    }

    public float getRaw(int i) {
        return this.mSpacing[i];
    }

    float getWithFallback(int i, int i2) {
        return (this.mValueFlags & sFlagsMap[i]) != 0 ? this.mSpacing[i] : get(i2);
    }

    public void reset() {
        Arrays.fill(this.mSpacing, UNDEFINED);
        this.mHasAliasesSet = false;
        this.mValueFlags = 0;
    }

    public boolean set(int i, float f) {
        if (TKFloatUtil.floatsEqual(this.mSpacing[i], f)) {
            return false;
        }
        this.mSpacing[i] = f;
        this.mValueFlags = isUndefined(f) ? (~sFlagsMap[i]) & this.mValueFlags : sFlagsMap[i] | this.mValueFlags;
        this.mHasAliasesSet = ((this.mValueFlags & sFlagsMap[8]) == 0 && (this.mValueFlags & sFlagsMap[7]) == 0 && (this.mValueFlags & sFlagsMap[6]) == 0) ? false : true;
        return true;
    }
}
