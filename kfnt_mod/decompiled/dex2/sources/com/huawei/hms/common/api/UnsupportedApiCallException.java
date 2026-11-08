package com.huawei.hms.common.api;

import com.huawei.hms.common.Feature;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature a;

    public UnsupportedApiCallException(Feature feature) {
        this.a = feature;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        return this.a + " is unsupported";
    }
}
