package com.facebook.stetho.inspector.elements.android;

import com.facebook.stetho.inspector.elements.Descriptor;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
interface AndroidDescriptorHost extends Descriptor.Host {
    @Nullable
    HighlightableDescriptor getHighlightableDescriptor(@Nullable Object obj);
}
