package com.tachikoma.core.component.network;

import com.tachikoma.core.component.network.delegate.TKBaseResponseInner;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ResponseCallBackInner {
    void onComplete(TKBaseResponseInner tKBaseResponseInner);

    void onError(TKErrorInner tKErrorInner);
}
