package b.b.a.f;

import android.annotation.SuppressLint;
import android.content.Context;
import b.b.a.k.v;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class n extends c {

    /* renamed from: a, reason: collision with root package name */
    public Context f1082a;

    public n(Context context) {
        super(true, false);
        this.f1082a = context;
    }

    @Override // b.b.a.f.c
    @SuppressLint({"MissingPermission"})
    public boolean a(JSONObject jSONObject) {
        j.a(this.f1082a);
        if (!v.f1150b) {
            return true;
        }
        v.a("new user mode = false", (Throwable) null);
        return true;
    }
}
