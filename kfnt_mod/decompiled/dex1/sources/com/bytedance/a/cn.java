package com.bytedance.a;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class cn extends af {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3474a;
    public final az f;

    public cn(Context context, az azVar) {
        super(true, false);
        this.f3474a = context;
        this.f = azVar;
    }

    @Override // com.bytedance.a.af
    public boolean a(JSONObject jSONObject) throws Throwable {
        SharedPreferences sharedPreferences = this.f.e;
        Map mapA = o.a(this.f3474a);
        if (mapA == null) {
            return false;
        }
        jSONObject.put("oaid", new JSONObject(mapA));
        return true;
    }
}
