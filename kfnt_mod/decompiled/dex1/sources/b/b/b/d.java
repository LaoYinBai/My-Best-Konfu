package b.b.b;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    public static b<String> f1161a = new a();

    public static class a extends b<String> {
        @Override // b.b.b.b
        public String a(Object[] objArr) {
            SharedPreferences sharedPreferences = (SharedPreferences) objArr[0];
            String string = sharedPreferences.getString("cdid", "");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            String string2 = UUID.randomUUID().toString();
            b.a.a.a.a.a(sharedPreferences, "cdid", string2);
            return string2;
        }
    }
}
