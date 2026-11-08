package b.b.a.f;

import android.content.Context;
import android.telephony.TelephonyManager;
import anet.channel.strategy.dispatch.DispatchConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i extends c {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1075a;
    public final h f;

    public i(Context context, h hVar) {
        super(false, false);
        this.f1075a = context;
        this.f = hVar;
    }

    @Override // b.b.a.f.c
    public boolean a(JSONObject jSONObject) throws JSONException {
        TelephonyManager telephonyManager = (TelephonyManager) this.f1075a.getSystemService("phone");
        if (telephonyManager != null) {
            h.a(jSONObject, DispatchConstants.CARRIER, telephonyManager.getNetworkOperatorName());
            h.a(jSONObject, "mcc_mnc", telephonyManager.getNetworkOperator());
        }
        h.a(jSONObject, "clientudid", ((b.b.a.k.g) this.f.g).a());
        h.a(jSONObject, "openudid", ((b.b.a.k.g) this.f.g).a(true));
        j.a(this.f1075a);
        return true;
    }
}
