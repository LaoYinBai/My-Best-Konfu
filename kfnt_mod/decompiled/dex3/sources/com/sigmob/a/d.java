package com.sigmob.a;

import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public c a(String str, InputStream inputStream) throws b {
        if (str == null) {
            throw new IllegalArgumentException("Archivername must not be null.");
        }
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream must not be null.");
        }
        if ("tar".equalsIgnoreCase(str)) {
            return new com.sigmob.a.a.b(inputStream);
        }
        throw new b("Archiver: " + str + " not found.");
    }
}
