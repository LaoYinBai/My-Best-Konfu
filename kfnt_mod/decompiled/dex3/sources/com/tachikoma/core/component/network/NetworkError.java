package com.tachikoma.core.component.network;

import android.content.Context;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NetworkError {
    public String msg;
    public int responseCode;

    public NetworkError(Context context, List<Object> list) {
    }

    public void setCode(int i) {
        this.responseCode = i;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
