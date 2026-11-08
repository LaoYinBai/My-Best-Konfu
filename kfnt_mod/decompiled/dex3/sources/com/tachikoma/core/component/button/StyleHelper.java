package com.tachikoma.core.component.button;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import com.tachikoma.core.utility.TKColorUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StyleHelper {
    private static final String KEY_BACKGROUND_COLOR = "backgroundColor";
    private static final String KEY_ONDISABLE = "disabled";
    private static final String KEY_ONNORMAL = "normal";
    private static final String KEY_ONPRESS = "pressed";
    private static final String KEY_TEXT_COLOR = "color";

    public static GradientDrawable createGradientDrawable(HashMap map) {
        for (Map.Entry entry : map.entrySet()) {
            String string = entry.getKey().toString();
            Object value = entry.getValue();
            if (value instanceof String) {
                String str = (String) value;
                if ("backgroundColor".equals(string) && str.contains("#")) {
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setColor(Color.parseColor(TKColorUtil.rgba2argb(str)));
                    return gradientDrawable;
                }
            }
        }
        return null;
    }

    private static int fetchTextColor(HashMap map) {
        for (Map.Entry entry : map.entrySet()) {
            String string = entry.getKey().toString();
            Object value = entry.getValue();
            if (value instanceof String) {
                String str = (String) value;
                if (KEY_TEXT_COLOR.equals(string) && str.contains("#")) {
                    return Color.parseColor(TKColorUtil.rgba2argb(str));
                }
            }
        }
        return 0;
    }

    public static ColorStateList getTextColor(HashMap map) {
        int i;
        int iFetchTextColor;
        int[] iArr;
        Iterator it = map.entrySet().iterator();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            String string = entry.getKey().toString();
            Object value = entry.getValue();
            if (KEY_ONDISABLE.equals(string) && (value instanceof HashMap)) {
                iFetchTextColor = fetchTextColor((HashMap) value);
                iArr = new int[]{-16842910};
            } else if (KEY_ONPRESS.equals(string)) {
                iFetchTextColor = fetchTextColor((HashMap) value);
                iArr = new int[]{R.attr.state_pressed};
            } else if ("normal".equals(string)) {
                iFetchTextColor = fetchTextColor((HashMap) value);
                iArr = new int[]{R.attr.state_enabled};
            }
            arrayList.add(iArr);
            arrayList2.add(Integer.valueOf(iFetchTextColor));
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        int[][] iArr2 = new int[arrayList.size()][];
        int[] iArr3 = new int[arrayList2.size()];
        for (i = 0; i < arrayList2.size(); i++) {
            iArr3[i] = ((Integer) arrayList2.get(i)).intValue();
        }
        return new ColorStateList((int[][]) arrayList.toArray(iArr2), iArr3);
    }
}
