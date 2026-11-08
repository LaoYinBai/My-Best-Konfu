package com.huawei.hms.framework.network.grs.b.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.b.b.c;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "a";
    private static c b;

    public static synchronized c a(Context context) {
        if (b != null) {
            return b;
        }
        String strA = com.huawei.hms.framework.network.grs.c.c.a("grs_sdk_server_config.json", context);
        ArrayList arrayList = null;
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(strA).getJSONObject("grs_server");
            JSONArray jSONArray = jSONObject.getJSONArray("grs_base_url");
            if (jSONArray != null && jSONArray.length() > 0) {
                arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.get(i).toString());
                }
            }
            b = new c();
            b.a(arrayList);
            b.a(jSONObject.getString("grs_query_endpoint_1.0"));
            b.b(jSONObject.getString("grs_query_endpoint_2.0"));
            b.a(jSONObject.getInt("grs_query_timeout"));
        } catch (JSONException e) {
            Logger.w(a, "getGrsServerBean catch JSONException", e);
        }
        return b;
    }
}
