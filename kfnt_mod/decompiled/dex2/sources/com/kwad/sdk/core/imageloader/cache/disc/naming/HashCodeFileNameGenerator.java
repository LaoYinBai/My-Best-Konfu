package com.kwad.sdk.core.imageloader.cache.disc.naming;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HashCodeFileNameGenerator implements FileNameGenerator {
    @Override // com.kwad.sdk.core.imageloader.cache.disc.naming.FileNameGenerator
    public String generate(String str) {
        return String.valueOf(str.hashCode());
    }
}
