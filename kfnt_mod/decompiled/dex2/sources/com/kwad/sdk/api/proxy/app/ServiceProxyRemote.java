package com.kwad.sdk.api.proxy.app;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.loader.Loader;
import com.kwad.sdk.api.proxy.BaseProxyService;
import com.kwad.sdk.api.proxy.IServiceProxy;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ServiceProxyRemote extends BaseProxyService {
    @Override // com.kwad.sdk.api.proxy.BaseProxyService
    @NonNull
    protected IServiceProxy getDelegate(Context context) {
        return (IServiceProxy) Loader.get().newComponentProxyNewProcess(context, ServiceProxyRemote.class, this);
    }
}
