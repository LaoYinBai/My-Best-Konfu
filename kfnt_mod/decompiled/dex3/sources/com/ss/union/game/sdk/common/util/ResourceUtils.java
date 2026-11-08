package com.ss.union.game.sdk.common.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import java.lang.reflect.Field;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ResourceUtils {
    private ResourceUtils() {
    }

    public static int getIdByName(String str) {
        return getResIdByType(AdDownloadModel.JsonKey.ID, str);
    }

    public static int getIntegerIdByName(String str) {
        return getResIdByType(PushMultiProcessSharedProvider.INT_TYPE, str);
    }

    public static int getStringIdByName(String str) {
        return getResIdByType(PushMultiProcessSharedProvider.STRING_TYPE, str);
    }

    public static int getColorIdByName(String str) {
        return getResIdByType("color", str);
    }

    public static int getDimenIdByName(String str) {
        return getResIdByType("dimen", str);
    }

    public static int getDrawableIdByName(String str) {
        return getResIdByType("drawable", str);
    }

    public static int getMipmapIdByName(String str) {
        return getResIdByType("mipmap", str);
    }

    public static int getLayoutIdByName(String str) {
        return getResIdByType("layout", str);
    }

    public static int getStyleIdByName(String str) {
        return getResIdByType("style", str);
    }

    public static int getAnimIdByName(String str) {
        return getResIdByType("anim", str);
    }

    public static int getAnimatorIdByName(String str) {
        return getResIdByType("animator", str);
    }

    public static int getResIdByType(String str, String str2) {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier(str2, str, context.getPackageName());
    }

    public static String getString(String str) {
        return getString(str, "");
    }

    public static String getString(String str, String str2) {
        try {
            return GlobalApplicationUtils.getContext().getString(getStringIdByName(str));
        } catch (Throwable unused) {
            return str2;
        }
    }

    public String getString(String str, Object... objArr) {
        try {
            return GlobalApplicationUtils.getContext().getString(getStringIdByName(str), objArr);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int getColor(String str) {
        try {
            return GlobalApplicationUtils.getContext().getResources().getColor(getStringIdByName(str));
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static ColorStateList getColorStateList(String str) {
        try {
            return GlobalApplicationUtils.getContext().getResources().getColorStateList(getStringIdByName(str));
        } catch (Throwable unused) {
            return ColorStateList.valueOf(0);
        }
    }

    public static int getDimen(String str) {
        try {
            return GlobalApplicationUtils.getContext().getResources().getDimensionPixelSize(getDimenIdByName(str));
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static Drawable getDrawable(String str) {
        try {
            return GlobalApplicationUtils.getContext().getResources().getDrawable(getDrawableIdByName(str));
        } catch (Throwable unused) {
            return new Drawable() { // from class: com.ss.union.game.sdk.common.util.ResourceUtils.1
                @Override // android.graphics.drawable.Drawable
                public void draw(@NonNull Canvas canvas) {
                }

                @Override // android.graphics.drawable.Drawable
                public int getOpacity() {
                    return 0;
                }

                @Override // android.graphics.drawable.Drawable
                public void setAlpha(int i) {
                }

                @Override // android.graphics.drawable.Drawable
                public void setColorFilter(@Nullable ColorFilter colorFilter) {
                }
            };
        }
    }

    public static int getInteger(String str, int i) {
        try {
            return GlobalApplicationUtils.getContext().getResources().getInteger(getIntegerIdByName(str));
        } catch (Throwable unused) {
            return i;
        }
    }

    public static int[] getStyleableInts(String str, String str2) throws NoSuchFieldException {
        try {
            Field declaredField = Class.forName(GlobalApplicationUtils.getContext().getPackageName() + ".R$" + str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            int[] iArr = (int[]) declaredField.get(null);
            if (iArr != null) {
                return iArr;
            }
        } catch (Exception unused) {
        }
        return new int[0];
    }

    public static int getStyleableInt(String str, String str2) throws NoSuchFieldException {
        try {
            Field declaredField = Class.forName(GlobalApplicationUtils.getContext().getPackageName() + ".R$" + str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            Integer num = (Integer) declaredField.get(null);
            if (num != null) {
                return num.intValue();
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static boolean getBoolean(String str, boolean z) {
        try {
            return GlobalApplicationUtils.getContext().getResources().getBoolean(getResIdByType("bool", str));
        } catch (Throwable unused) {
            return z;
        }
    }

    public static int[] getIntegerArray(String str, int[] iArr) {
        try {
            return GlobalApplicationUtils.getContext().getResources().getIntArray(getResIdByType("array", str));
        } catch (Throwable unused) {
            return iArr;
        }
    }

    public static String[] getStringArray(String str, String[] strArr) {
        try {
            return GlobalApplicationUtils.getContext().getResources().getStringArray(getResIdByType("array", str));
        } catch (Throwable unused) {
            return strArr;
        }
    }
}
