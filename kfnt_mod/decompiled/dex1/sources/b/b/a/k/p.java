package b.b.a.k;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class p {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f1140a;

    /* renamed from: b, reason: collision with root package name */
    public static b.b.a.k.a<SharedPreferences> f1141b = new a();

    public static class a extends b.b.a.k.a<SharedPreferences> {
        @Override // b.b.a.k.a
        public SharedPreferences a(Object[] objArr) {
            return ((Context) objArr[0]).getSharedPreferences("ug_install_settings_pref", 0);
        }
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (f1140a) {
            return true;
        }
        return f1141b.b(context).getBoolean("_install_started_v2", false);
    }
}
