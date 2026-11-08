package b.b.a.f;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class e extends c {

    /* renamed from: a, reason: collision with root package name */
    public final g f1067a;

    public e(g gVar) {
        super(true, false);
        this.f1067a = gVar;
    }

    @Override // b.b.a.f.c
    public boolean a(JSONObject jSONObject) throws JSONException {
        String strA = b.b.b.f.a(this.f1067a.e);
        if (TextUtils.isEmpty(strA)) {
            return false;
        }
        jSONObject.put("cdid", strA);
        return true;
    }
}
