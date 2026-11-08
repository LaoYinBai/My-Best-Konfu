package com.kuaishou.tachikoma.api.model;

import com.kwad.sdk.e.a;
import com.kwad.sdk.e.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BaseJsonApiParse extends a {
    @Override // com.kwad.sdk.e.a
    protected b getHolderMananger() {
        return KsJsonHolderManangerImpl.getInstance();
    }
}
