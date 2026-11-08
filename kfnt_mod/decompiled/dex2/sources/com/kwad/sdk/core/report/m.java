package com.kwad.sdk.core.report;

import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.kwad.sdk.utils.t;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class m extends ReportAction {
    private static final SparseArray<com.kwad.sdk.core.report.kwai.b> bP = new SparseArray<>();
    public String bN;
    private final Map<String, Object> bO;

    static {
        bP.put(1, new com.kwad.sdk.core.report.kwai.a());
    }

    public m(long j, Map<String, Object> map) {
        super(j);
        this.bN = com.kwad.sdk.core.network.k.c();
        this.bO = new HashMap();
        if (map != null && !map.isEmpty()) {
            this.bO.putAll(map);
        }
        this.bK = 2;
    }

    public m(JSONObject jSONObject) {
        super(jSONObject);
        this.bN = com.kwad.sdk.core.network.k.c();
        this.bO = new HashMap();
        com.kwad.sdk.core.report.kwai.b bVarA = a(jSONObject.optInt("actionType"));
        if (bVarA != null) {
            bVarA.a(jSONObject, this.bO);
        }
        this.bK = 2;
    }

    private com.kwad.sdk.core.report.kwai.b a(int i) {
        return bP.get(b(i));
    }

    private int b(int i) {
        return 1;
    }

    @Override // com.kwad.sdk.core.report.ReportAction, com.kwad.sdk.core.report.c, com.kwad.sdk.core.response.kwai.a
    public void afterToJson(JSONObject jSONObject) throws JSONException {
        com.kwad.sdk.core.report.kwai.b bVarA;
        super.afterToJson(jSONObject);
        if (this.bO.isEmpty() || (bVarA = a(jSONObject.optInt("actionType"))) == null) {
            return;
        }
        bVarA.b(jSONObject, this.bO);
        try {
            jSONObject.put("ecIdentityFlag", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.kwad.sdk.core.response.kwai.a, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        try {
            this.bN = jSONObject.optString("ua");
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.core.response.kwai.a, com.kwad.sdk.core.b
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        t.a(json, "ua", this.bN);
        return json;
    }
}
