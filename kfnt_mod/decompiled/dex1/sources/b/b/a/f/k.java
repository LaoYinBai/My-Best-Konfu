package b.b.a.f;

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
public class k extends c {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1079a;

    public k(Context context) {
        super(true, false);
        this.f1079a = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r0v25, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11, types: [int[]] */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v18, types: [int] */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    @Override // b.b.a.f.c
    public boolean a(JSONObject jSONObject) throws JSONException, NoSuchMethodException, SecurityException {
        String str;
        Class<Display> cls;
        int iIntValue;
        ?? r3;
        ?? IntValue;
        int i = this.f1079a.getResources().getDisplayMetrics().densityDpi;
        switch (i) {
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
        jSONObject.put("density_dpi", i);
        jSONObject.put("display_density", str);
        WindowManager windowManager = (WindowManager) this.f1079a.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        try {
        } catch (NoSuchMethodException e) {
            e = e;
            cls = null;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
            ?? r0 = displayMetrics.widthPixels;
            try {
                r3 = r0;
                iIntValue = displayMetrics.heightPixels;
            } catch (NoSuchMethodException e2) {
                e = e2;
                cls = r0;
                e.printStackTrace();
                iIntValue = 0;
                r3 = cls;
                ?? r1 = {r3, iIntValue};
                jSONObject.put("resolution", r1[1] + "x" + r1[0]);
                return true;
            }
            ?? r12 = {r3, iIntValue};
            jSONObject.put("resolution", r12[1] + "x" + r12[0]);
            return true;
        }
        Method method = Display.class.getMethod("getRawHeight", new Class[0]);
        cls = Display.class;
        try {
            try {
                IntValue = ((Integer) cls.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                try {
                    iIntValue = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
                    r3 = IntValue;
                } catch (IllegalAccessException e3) {
                    e = e3;
                    e.printStackTrace();
                    cls = IntValue;
                    iIntValue = 0;
                    r3 = cls;
                    ?? r122 = {r3, iIntValue};
                    jSONObject.put("resolution", r122[1] + "x" + r122[0]);
                    return true;
                } catch (IllegalArgumentException e4) {
                    e = e4;
                    e.printStackTrace();
                    cls = IntValue;
                    iIntValue = 0;
                    r3 = cls;
                    ?? r1222 = {r3, iIntValue};
                    jSONObject.put("resolution", r1222[1] + "x" + r1222[0]);
                    return true;
                } catch (InvocationTargetException e5) {
                    e = e5;
                    e.printStackTrace();
                    cls = IntValue;
                    iIntValue = 0;
                    r3 = cls;
                    ?? r12222 = {r3, iIntValue};
                    jSONObject.put("resolution", r12222[1] + "x" + r12222[0]);
                    return true;
                }
            } catch (IllegalAccessException e6) {
                e = e6;
                IntValue = 0;
            } catch (IllegalArgumentException e7) {
                e = e7;
                IntValue = 0;
            } catch (InvocationTargetException e8) {
                e = e8;
                IntValue = 0;
            }
        } catch (NoSuchMethodException e9) {
            e = e9;
            e.printStackTrace();
            iIntValue = 0;
            r3 = cls;
            ?? r122222 = {r3, iIntValue};
            jSONObject.put("resolution", r122222[1] + "x" + r122222[0]);
            return true;
        }
        ?? r1222222 = {r3, iIntValue};
        jSONObject.put("resolution", r1222222[1] + "x" + r1222222[0]);
        return true;
        iIntValue = 0;
        r3 = cls;
        ?? r12222222 = {r3, iIntValue};
        jSONObject.put("resolution", r12222222[1] + "x" + r12222222[0]);
        return true;
    }
}
