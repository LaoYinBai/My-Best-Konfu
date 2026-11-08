package com.kwad.sdk.core.h;

import com.kwad.sdk.api.core.SpeedLimitApi;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements SpeedLimitApi {
    @Override // com.kwad.sdk.api.core.SpeedLimitApi
    public InputStream wrapInputStream(InputStream inputStream) {
        return b.a().b(inputStream);
    }
}
