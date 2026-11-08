package com.tachikoma.core.event;

import com.kwad.v8.V8Function;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IEventListener {
    void addEventListener(String str, V8Function v8Function);

    void removeEventListener(String str, V8Function v8Function);
}
