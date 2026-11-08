package b.b.a.j;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l extends c {

    /* renamed from: a, reason: collision with root package name */
    public final SharedPreferences f1121a;

    /* renamed from: b, reason: collision with root package name */
    public final SharedPreferences f1122b;

    public l(Context context, String str) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        this.f1121a = context.getSharedPreferences("snssdk_openudid", 0);
        this.f1122b = context.getSharedPreferences(str, 0);
    }

    @Override // b.b.a.j.c
    public String a(String str) {
        return d(str).getString(str, null);
    }

    @Override // b.b.a.j.c
    public void a(String str, String str2) {
        e(str, str2);
    }

    @Override // b.b.a.j.c
    public String[] b(String str) {
        String string = d(str).getString(str, null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string.split("\n");
    }

    public final SharedPreferences d(String str) {
        return com.bytedance.b.a.c.h.x.equals(str) ? this.f1122b : this.f1121a;
    }

    public final void e(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = d(str).edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    @Override // b.b.a.j.c
    public void a(String str, String[] strArr) {
        if (str == null || strArr == null) {
            return;
        }
        e(str, TextUtils.join("\n", strArr));
    }

    @Override // b.b.a.j.c
    public void c(String str) {
        SharedPreferences sharedPreferencesD = d(str);
        if (sharedPreferencesD != null && sharedPreferencesD.contains(str)) {
            d(str).edit().remove(str).apply();
        }
        super.c(str);
    }
}
