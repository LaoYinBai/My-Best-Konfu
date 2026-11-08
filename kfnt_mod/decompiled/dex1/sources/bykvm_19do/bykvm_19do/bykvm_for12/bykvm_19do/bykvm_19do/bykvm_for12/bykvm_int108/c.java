package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108;

import android.content.Context;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.h;
import java.util.LinkedHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static void a(Context context, int i, String str, int i2) {
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (i == 1) {
                linkedHashMap.put(a(i2), str);
            }
            b.a("MultiProcessFileUtils", "saveData = " + str);
            if (h.a().a(i2).b() != null) {
                h.a().a(i2).b().a(context, linkedHashMap);
            }
        } catch (Exception unused) {
        }
    }

    public static String a(Context context, int i, int i2) {
        String strA = "";
        if (i == 1) {
            try {
                if (h.a().a(i2).b() != null) {
                    strA = h.a().a(i2).b().a(context, a(i2), "");
                }
            } catch (Exception unused) {
                strA = "";
            }
        }
        return strA instanceof String ? String.valueOf(strA) : "";
    }

    private static String a(int i) {
        return "tnc_config" + i;
    }
}
