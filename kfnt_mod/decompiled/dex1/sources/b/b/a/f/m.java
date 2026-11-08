package b.b.a.f;

import android.annotation.SuppressLint;
import android.content.Context;
import com.bytedance.common.utility.NetworkUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class m extends c {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1081a;

    public m(Context context) {
        super(true, true);
        this.f1081a = context;
    }

    @Override // b.b.a.f.c
    @SuppressLint({"MissingPermission"})
    public boolean a(JSONObject jSONObject) throws JSONException {
        h.a(jSONObject, "access", NetworkUtils.getNetworkAccessType(this.f1081a));
        return true;
    }
}
