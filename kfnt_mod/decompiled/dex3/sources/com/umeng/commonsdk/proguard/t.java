package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.t;
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface t<T extends t<?, ?>, F extends aa> extends Serializable {
    void clear();

    t<T, F> deepCopy();

    F fieldForId(int i);

    void read(as asVar) throws z;

    void write(as asVar) throws z;
}
