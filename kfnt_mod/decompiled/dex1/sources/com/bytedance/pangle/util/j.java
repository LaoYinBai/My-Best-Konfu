package com.bytedance.pangle.util;

import android.content.SharedPreferences;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusConstants;
import com.bytedance.pangle.log.ZeusLogger;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {

    /* renamed from: b, reason: collision with root package name */
    private static volatile j f4717b;

    /* renamed from: a, reason: collision with root package name */
    public SharedPreferences f4718a = Zeus.getAppApplication().getSharedPreferences(ZeusConstants.BASE_LIB_NAME + "_meta_data_sp", 0);

    public static j a() {
        if (f4717b == null) {
            synchronized (j.class) {
                if (f4717b == null) {
                    f4717b = new j();
                }
            }
        }
        return f4717b;
    }

    private j() {
    }

    public final int a(String str) {
        int i = this.f4718a.getInt("PLUGIN_API_VERSION_".concat(String.valueOf(str)), 0);
        ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusSpUtils getPluginApiVersion pluginPKg = " + str + ", pluginApiVersion = " + i);
        return i;
    }

    public final void b(String str) {
        SharedPreferences.Editor editorEdit = this.f4718a.edit();
        editorEdit.putBoolean("OFFLINE__" + com.bytedance.pangle.g.a().f4632b.getHostVersionCode() + "_" + str, true);
        editorEdit.apply();
        ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusSpUtils markOfflineFlag packageName=" + str + " hostUpdateVerCode= " + com.bytedance.pangle.g.a().f4632b.getHostVersionCode());
    }

    public final void c(String str) {
        SharedPreferences.Editor editorEdit = this.f4718a.edit();
        editorEdit.remove("OFFLINE__" + com.bytedance.pangle.g.a().f4632b.getHostVersionCode() + "_" + str);
        editorEdit.apply();
        ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusSpUtils clearOfflineFlag packageName=" + str + " hostUpdateVerCode= " + com.bytedance.pangle.g.a().f4632b.getHostVersionCode());
    }

    public final boolean d(String str) {
        return this.f4718a.getBoolean("OFFLINE__" + com.bytedance.pangle.g.a().f4632b.getHostVersionCode() + "_" + str, false);
    }

    public final void e(String str) {
        SharedPreferences.Editor editorEdit = this.f4718a.edit();
        editorEdit.putBoolean("DELETED__".concat(String.valueOf(str)), true);
        editorEdit.apply();
        ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusSpUtils markDeletedFlag packageName=".concat(String.valueOf(str)));
    }

    public final void a(String str, int i, boolean z) {
        SharedPreferences.Editor editorEdit = this.f4718a.edit();
        String str2 = "INSTALLED_" + str + "-" + i;
        if (z) {
            editorEdit.putBoolean(str2, true);
        } else {
            editorEdit.remove(str2);
        }
        editorEdit.apply();
    }

    public final boolean a(String str, int i) {
        return this.f4718a.getBoolean(String.format(Locale.getDefault(), "INSTALLED_%s-%d", str, Integer.valueOf(i)), false);
    }
}
