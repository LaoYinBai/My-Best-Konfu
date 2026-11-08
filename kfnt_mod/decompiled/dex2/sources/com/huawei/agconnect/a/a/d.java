package com.huawei.agconnect.a.a;

import android.util.Log;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class d implements b {
    private final JSONObject a;

    d(InputStream inputStream) {
        this.a = a(inputStream);
    }

    private JSONObject a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                return new JSONObject(h.a(inputStream, C.UTF8_NAME));
            } catch (IOException unused) {
                Log.e("InputStreamReader", "IOException when reading the 'Config' from InputStream.");
            } catch (JSONException unused2) {
                Log.e("InputStreamReader", "JSONException when reading the 'Config' from InputStream.");
            }
        }
        return new JSONObject();
    }

    @Override // com.huawei.agconnect.a.a.b
    public String a(String str, String str2) throws JSONException {
        if (str.endsWith("/")) {
            return str2;
        }
        String[] strArrSplit = str.split("/");
        try {
            JSONObject jSONObject = this.a;
            for (int i = 1; i < strArrSplit.length; i++) {
                if (i == strArrSplit.length - 1) {
                    return jSONObject.get(strArrSplit[i]).toString();
                }
                jSONObject = jSONObject.getJSONObject(strArrSplit[i]);
            }
        } catch (JSONException unused) {
            Log.w("InputStreamReader", "JSONException when reading 'path': " + str);
        }
        return str2;
    }
}
