package com.kwad.sdk.e;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a implements c {
    private List<e<a>> mHolders;

    private List<e<a>> getHolders() {
        if (this.mHolders == null) {
            this.mHolders = new ArrayList();
            for (Class<?> superclass = getClass(); superclass != null && a.class.isAssignableFrom(superclass); superclass = superclass.getSuperclass()) {
                e<a> holder = getHolderMananger().getHolder(superclass);
                if (holder != null) {
                    this.mHolders.add(0, holder);
                }
            }
        }
        return this.mHolders;
    }

    public void afterParseJson(@Nullable JSONObject jSONObject) {
    }

    public void afterToJson(JSONObject jSONObject) {
    }

    protected abstract b getHolderMananger();

    @Override // com.kwad.sdk.e.c
    public void parseJson(@Nullable JSONObject jSONObject) {
        List<e<a>> holders = getHolders();
        for (int size = holders.size() - 1; size >= 0; size--) {
            holders.get(size).a(this, jSONObject);
        }
        afterParseJson(jSONObject);
    }

    @Override // com.kwad.sdk.e.c
    public JSONObject toJson() {
        List<e<a>> holders = getHolders();
        JSONObject jSONObject = new JSONObject();
        for (int size = holders.size() - 1; size >= 0; size--) {
            holders.get(size).b(this, jSONObject);
        }
        afterToJson(jSONObject);
        return jSONObject;
    }
}
