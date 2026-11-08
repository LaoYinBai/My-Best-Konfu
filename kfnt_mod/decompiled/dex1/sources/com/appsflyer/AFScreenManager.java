package com.appsflyer;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AFScreenManager {
    public static final String SCREEN_KEY = "dim";

    public static Map<String, String> getScreenMetrics(@NonNull Context context) {
        HashMap map = new HashMap();
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = context.getResources().getConfiguration().screenLayout & 15;
            map.put("x_px", String.valueOf(displayMetrics.widthPixels));
            map.put("y_px", String.valueOf(displayMetrics.heightPixels));
            map.put("d_dpi", String.valueOf(displayMetrics.densityDpi));
            map.put("size", String.valueOf(i));
            map.put("xdp", String.valueOf(displayMetrics.xdpi));
            map.put("ydp", String.valueOf(displayMetrics.ydpi));
        } catch (Throwable th) {
            AFLogger.afErrorLog("Couldn't aggregate screen stats: ", th);
        }
        return map;
    }
}
