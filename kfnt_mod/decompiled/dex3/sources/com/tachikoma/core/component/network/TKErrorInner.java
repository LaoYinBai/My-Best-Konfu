package com.tachikoma.core.component.network;

import com.tachikoma.core.component.network.delegate.BaseJsonCoreParse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKErrorInner extends BaseJsonCoreParse {
    public int code;
    public String msg;
    public String name;

    public String toString() {
        return toJson().toString();
    }
}
