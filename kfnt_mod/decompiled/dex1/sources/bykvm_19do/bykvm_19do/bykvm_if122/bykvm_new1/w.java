package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class w {
    public static void a(Context context) {
        try {
            context.getSharedPreferences("tt_mediation_ppe_info", 0).edit().putString("tt_gromore_sdk_version", "3.1.0.1").commit();
            context.getSharedPreferences("pangle_com.byted.pangle_tt_mediation_ppe_info", 0).edit().putString("tt_gromore_sdk_version", "3.1.0.1").commit();
        } catch (Throwable unused) {
        }
    }
}
