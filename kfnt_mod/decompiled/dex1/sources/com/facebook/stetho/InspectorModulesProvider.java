package com.facebook.stetho;

import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface InspectorModulesProvider {
    Iterable<ChromeDevtoolsDomain> get();
}
