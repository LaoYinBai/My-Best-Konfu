package com.tachikoma.core.component.network.delegate;

import com.kwad.sdk.e.a;
import com.kwad.sdk.e.b;
import com.kwad.sdk.e.e;
import com.kwad.sdk.e.kwai.d;
import com.kwad.sdk.e.kwai.f;
import com.tachikoma.core.component.imageview.TKCDNUrlInner;
import com.tachikoma.core.component.network.TKErrorInner;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class KsJsonHolderManangerImpl implements b {
    private static KsJsonHolderManangerImpl mInstance;
    private static Map<Class<? extends a>, e> sMap = new HashMap(3);

    static {
        sMap.put(TKCDNUrlInner.class, new d());
        sMap.put(TKErrorInner.class, new f());
        sMap.put(TKBaseResponseInner.class, new com.kwad.sdk.e.kwai.b());
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

    public e getHolder(Class<? extends a> cls) {
        return sMap.get(cls);
    }
}
