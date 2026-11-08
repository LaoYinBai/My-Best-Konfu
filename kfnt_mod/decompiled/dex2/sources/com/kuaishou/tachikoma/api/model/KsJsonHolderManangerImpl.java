package com.kuaishou.tachikoma.api.model;

import com.kuaishou.tachikoma.api.app.TKBaseResponse;
import com.kuaishou.tachikoma.api.app.TKError;
import com.kwad.sdk.e.a;
import com.kwad.sdk.e.b;
import com.kwad.sdk.e.e;
import com.kwad.sdk.e.kwai.c;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsJsonHolderManangerImpl implements b {
    private static KsJsonHolderManangerImpl mInstance;
    private static Map<Class<? extends a>, e> sMap = new HashMap(3);

    static {
        sMap.put(TKBaseResponse.class, new com.kwad.sdk.e.kwai.a());
        sMap.put(TKCDNUrl.class, new c());
        sMap.put(TKError.class, new com.kwad.sdk.e.kwai.e());
    }

    protected static KsJsonHolderManangerImpl getInstance() {
        if (mInstance == null) {
            synchronized (KsJsonHolderManangerImpl.class) {
                if (mInstance == null) {
                    mInstance = new KsJsonHolderManangerImpl();
                }
            }
        }
        return mInstance;
    }

    @Override // com.kwad.sdk.e.b
    public e getHolder(Class<? extends a> cls) {
        return sMap.get(cls);
    }
}
