package com.umeng.commonsdk.utils;

import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface FileLockCallback {
    boolean onFileLock(File file, int i);

    boolean onFileLock(String str);

    boolean onFileLock(String str, Object obj);
}
