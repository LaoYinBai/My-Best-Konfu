package com.bytedance.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.net.SocketException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class dc extends af {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3491a;
    public final az f;
    public final be g;

    public dc(Context context, az azVar, be beVar) {
        super(true, false);
        this.f3491a = context;
        this.f = azVar;
        this.g = beVar;
    }

    @Override // com.bytedance.a.af
    @SuppressLint({"HardwareIds"})
    public boolean a(JSONObject jSONObject) throws JSONException, SocketException {
        String[] strArrC;
        if (!bo.a(this.f3491a)) {
            jSONObject.put("build_serial", m.f(this.f3491a));
        }
        be.a(jSONObject, "aliyun_uuid", this.f.f3386b.getAliyunUdid());
        if (this.f.f3386b.isMacEnable()) {
            String strD = m.d(this.f3491a);
            SharedPreferences sharedPreferences = this.f.e;
            String string = sharedPreferences.getString("mac_address", null);
            if (!TextUtils.isEmpty(strD)) {
                if (!TextUtils.equals(string, strD)) {
                    a.a(sharedPreferences, "mac_address", strD);
                }
                jSONObject.put("mc", strD);
            } else if (!TextUtils.isEmpty(string)) {
                jSONObject.put("mc", string);
            }
        }
        be.a(jSONObject, "udid", ((ah) this.g.h).d());
        JSONArray jSONArrayE = ((ah) this.g.h).e();
        if (m.a(jSONArrayE)) {
            jSONObject.put("udid_list", jSONArrayE);
        }
        be.a(jSONObject, "serial_number", ((ah) this.g.h).b());
        if (this.g.j() && (strArrC = ((ah) this.g.h).c()) != null) {
            JSONArray jSONArray = new JSONArray();
            for (String str : strArrC) {
                jSONArray.put(new JSONObject().put("sim_serial_number", str));
            }
            jSONObject.put("sim_serial_number", jSONArray);
        }
        if (!bo.a(this.f3491a)) {
            return true;
        }
        ((ah) this.g.h).f();
        throw null;
    }
}
