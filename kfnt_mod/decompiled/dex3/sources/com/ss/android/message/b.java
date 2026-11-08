package com.ss.android.message;

import android.content.Context;
import com.ss.android.pushmanager.IMessageContext;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface b extends IPushLifeCycleListener {

    public interface a {
    }

    void a(Context context, IMessageContext iMessageContext);

    void a(Context context, Map<String, String> map);

    void a(a aVar);
}
