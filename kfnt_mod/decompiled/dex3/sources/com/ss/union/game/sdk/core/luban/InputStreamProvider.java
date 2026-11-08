package com.ss.union.game.sdk.core.luban;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface InputStreamProvider {
    void close();

    String getPath();

    InputStream open() throws IOException;
}
