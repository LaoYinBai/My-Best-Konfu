package com.ss.union.game.sdk.common.net.http.base.in;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ICoreNetApi2Callback {
    public abstract void onNetError(int i, String str);

    public abstract void onNetSuccess(JSONObject jSONObject);
}
