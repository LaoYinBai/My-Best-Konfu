package com.bytedance.sdk.adnet.game.http.bean;

import com.bytedance.sdk.adnet.game.http.b.c;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.MediaType;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class FileWrapper {
    public MediaType contentType;
    public File file;
    public String fileName;
    public long fileSize;

    public FileWrapper(File file) {
        this(file, c.c(file.getName()));
    }

    public FileWrapper(File file, MediaType mediaType) {
        this.file = file;
        this.fileName = file.getName();
        this.fileSize = file.length();
        this.contentType = mediaType;
    }
}
