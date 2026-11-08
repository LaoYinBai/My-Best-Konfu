package com.ss.android.download.api.clean;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IJsonable {
    void parse(JSONObject jSONObject);

    JSONObject toJson();
}
