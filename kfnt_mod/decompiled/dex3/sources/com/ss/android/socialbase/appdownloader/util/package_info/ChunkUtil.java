package com.ss.android.socialbase.appdownloader.util.package_info;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ChunkUtil {
    ChunkUtil() {
    }

    public static final void readCheckType(IntReader intReader, int i) throws IOException {
        int i2 = intReader.readInt();
        if (i2 == i) {
            return;
        }
        throw new IOException("Expected chunk of type 0x" + Integer.toHexString(i) + ", read 0x" + Integer.toHexString(i2) + ".");
    }
}
