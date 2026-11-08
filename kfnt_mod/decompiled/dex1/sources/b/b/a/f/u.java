package b.b.a.f;

import android.content.Context;
import android.telephony.TelephonyManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class u extends c {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1087a;

    public u(Context context) {
        super(true, false);
        this.f1087a = context;
    }

    @Override // b.b.a.f.c
    public boolean a(JSONObject jSONObject) throws JSONException {
        h.a(jSONObject, "sim_region", ((TelephonyManager) this.f1087a.getSystemService("phone")).getSimCountryIso());
        return true;
    }
}
