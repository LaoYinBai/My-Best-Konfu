package com.facebook.stetho.dumpapp;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface DumperPlugin {
    void dump(DumperContext dumperContext) throws DumpException;

    String getName();
}
