package com.kwad.sdk.collector.a;

import com.heytap.mcssdk.mode.CommandMessage;
import com.kwad.sdk.collector.model.e;
import com.kwad.sdk.core.network.d;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends d {
    public b(Collection<e> collection) {
        a("collectData", a(collection));
        a("collectType", 1);
        b(CommandMessage.SDK_VERSION, "3.3.17");
        a("sdkVersionCode", 3031700);
    }

    private JSONArray a(Collection<e> collection) {
        JSONArray jSONArray = new JSONArray();
        Iterator<e> it = collection.iterator();
        while (it.hasNext()) {
            JSONObject jSONObjectA = it.next().a();
            if (jSONObjectA != null) {
                jSONArray.put(jSONObjectA);
            }
        }
        return jSONArray;
    }

    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
    public String a() {
        return com.kwad.sdk.d.m();
    }
}
