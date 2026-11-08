package b.b.a.f;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class o extends c {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1083a;
    public final g f;

    public o(Context context, g gVar) {
        super(true, false);
        this.f1083a = context;
        this.f = gVar;
    }

    @Override // b.b.a.f.c
    public boolean a(JSONObject jSONObject) throws Throwable {
        SharedPreferences sharedPreferences = this.f.e;
        Map mapA = b.b.b.f.a(this.f1083a);
        if (mapA == null) {
            return false;
        }
        jSONObject.put("oaid", new JSONObject(mapA));
        return true;
    }
}
