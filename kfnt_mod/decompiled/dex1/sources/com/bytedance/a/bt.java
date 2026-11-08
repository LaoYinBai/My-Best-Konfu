package com.bytedance.a;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bt extends af {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3431a;

    public bt(Context context) {
        super(true, false);
        this.f3431a = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v13, types: [int] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.Class, java.lang.Class<android.view.Display>] */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Override // com.bytedance.a.af
    public boolean a(JSONObject jSONObject) throws JSONException, NoSuchMethodException, SecurityException {
        String str;
        ?? IntValue;
        int iIntValue;
        int i;
        int i2 = this.f3431a.getResources().getDisplayMetrics().densityDpi;
        switch (i2) {
            case 120:
                str = "ldpi";
                break;
            case 240:
                str = "hdpi";
                break;
            case 260:
            case 280:
            case TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX /* 300 */:
            case 320:
                str = "xhdpi";
                break;
            case 340:
            case 360:
            case 400:
            case 420:
            case 440:
            case 480:
                str = "xxhdpi";
                break;
            case 560:
            case 640:
                str = "xxxhdpi";
                break;
            default:
                str = "mdpi";
                break;
        }
        jSONObject.put("density_dpi", i2);
        jSONObject.put("display_density", str);
        WindowManager windowManager = (WindowManager) this.f3431a.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        try {
        } catch (NoSuchMethodException e) {
            e = e;
            IntValue = 0;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
            int i3 = displayMetrics.widthPixels;
            try {
                i = i3;
                iIntValue = displayMetrics.heightPixels;
            } catch (NoSuchMethodException e2) {
                e = e2;
                IntValue = i3;
                de.a("", e);
                iIntValue = 0;
                i = IntValue;
                int[] iArr = {i, iIntValue};
                jSONObject.put("resolution", iArr[1] + "x" + iArr[0]);
                return true;
            }
            int[] iArr2 = {i, iIntValue};
            jSONObject.put("resolution", iArr2[1] + "x" + iArr2[0]);
            return true;
        }
        Method method = Display.class.getMethod("getRawHeight", new Class[0]);
        IntValue = Display.class;
        try {
            try {
                IntValue = ((Integer) IntValue.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e3) {
                e = e3;
                IntValue = 0;
            }
        } catch (NoSuchMethodException e4) {
            e = e4;
            de.a("", e);
            iIntValue = 0;
            i = IntValue;
            int[] iArr22 = {i, iIntValue};
            jSONObject.put("resolution", iArr22[1] + "x" + iArr22[0]);
            return true;
        }
        try {
            iIntValue = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
            i = IntValue;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e5) {
            e = e5;
            de.a("", e);
            iIntValue = 0;
            i = IntValue;
            int[] iArr222 = {i, iIntValue};
            jSONObject.put("resolution", iArr222[1] + "x" + iArr222[0]);
            return true;
        }
        int[] iArr2222 = {i, iIntValue};
        jSONObject.put("resolution", iArr2222[1] + "x" + iArr2222[0]);
        return true;
        iIntValue = 0;
        i = IntValue;
        int[] iArr22222 = {i, iIntValue};
        jSONObject.put("resolution", iArr22222[1] + "x" + iArr22222[0]);
        return true;
    }
}
