package com.kuaishou.tachikoma.api.app;

import com.kuaishou.tachikoma.api.model.BaseJsonApiParse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKError extends BaseJsonApiParse {
    public int code;
    public String msg;
    public String name;

    public String toString() {
        return toJson().toString();
    }
}
