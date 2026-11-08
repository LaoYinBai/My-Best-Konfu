package com.kwad.sdk.crash.utils;

import java.io.File;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class FileExistsException extends IOException {
    private static final long serialVersionUID = 1;

    public FileExistsException() {
    }

    public FileExistsException(File file) {
        super("File " + file + " exists");
    }

    public FileExistsException(String str) {
        super(str);
    }
}
