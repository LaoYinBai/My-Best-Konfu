package com.kuaishou.tachikoma.api.app;

import com.kuaishou.tachikoma.api.model.BaseJsonApiParse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKBaseResponse extends BaseJsonApiParse {
    public String allHeaderFields;
    public String body;
    public int statusCode;

    public String toJsonString() {
        return toJson().toString();
    }
}
