package com.facebook.stetho.inspector.elements;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface DocumentProviderListener {
    void onAttributeModified(Object obj, String str, String str2);

    void onAttributeRemoved(Object obj, String str);

    void onInspectRequested(Object obj);

    void onPossiblyChanged();
}
