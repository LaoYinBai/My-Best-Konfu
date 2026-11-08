package com.bytedance.a;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.applog.network.INetworkClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class s implements INetworkClient {
    @Override // com.bytedance.applog.network.INetworkClient
    public String get(String str, Map<String, String> map) {
        return bf.a(0, str, (HashMap) map, null, -1);
    }

    @Override // com.bytedance.applog.network.INetworkClient
    public String post(String str, List<Pair<String, String>> list) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (list != null) {
            try {
                for (Pair<String, String> pair : list) {
                    jSONObject.put((String) pair.first, pair.second);
                }
            } catch (JSONException e) {
                de.a("", e);
            }
        }
        return post(str, jSONObject.toString().getBytes(), com.bytedance.b.a.a.c.e.f3610c);
    }

    @Override // com.bytedance.applog.network.INetworkClient
    public String post(String str, byte[] bArr, String str2) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            map.put("Content-Type", str2);
        }
        return post(str, bArr, map);
    }

    @Override // com.bytedance.applog.network.INetworkClient
    public String post(String str, byte[] bArr, Map<String, String> map) {
        return bf.a(1, str, (HashMap) map, bArr, -1);
    }

    @Override // com.bytedance.applog.network.INetworkClient
    public byte[] postStream(String str, byte[] bArr, Map<String, String> map) {
        return bf.a(1, str, (HashMap<String, String>) map, bArr, 1, -1).f3402b;
    }
}
