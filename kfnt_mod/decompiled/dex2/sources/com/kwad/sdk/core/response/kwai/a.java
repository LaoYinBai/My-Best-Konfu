package com.kwad.sdk.core.response.kwai;

import androidx.annotation.Nullable;
import com.kwad.sdk.core.b;
import com.kwad.sdk.core.b.kwai.cf;
import com.kwad.sdk.core.d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements b {
    private List<d<a>> mHolders;

    private List<d<a>> getHolders() {
        if (this.mHolders == null) {
            this.mHolders = new ArrayList();
            for (Class<?> superclass = getClass(); superclass != null && a.class.isAssignableFrom(superclass); superclass = superclass.getSuperclass()) {
                d<a> dVarA = cf.a(superclass);
                if (dVarA != null) {
                    this.mHolders.add(0, dVarA);
                }
            }
        }
        return this.mHolders;
    }

    public void afterParseJson(@Nullable JSONObject jSONObject) {
    }

    public void afterToJson(JSONObject jSONObject) {
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
        List<d<a>> holders = getHolders();
        for (int size = holders.size() - 1; size >= 0; size--) {
            holders.get(size).a(this, jSONObject);
        }
        afterParseJson(jSONObject);
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() {
        List<d<a>> holders = getHolders();
        JSONObject jSONObject = new JSONObject();
        for (int size = holders.size() - 1; size >= 0; size--) {
            holders.get(size).b(this, jSONObject);
        }
        afterToJson(jSONObject);
        return jSONObject;
    }
}
