package com.ss.union.game.sdk.common.net.http.base.in;

import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ACoreRequestDownload implements ICoreNetRequestGet<ACoreRequestDownload> {
    public abstract CoreNetResponse<File, ACoreRequestDownload> download(File file);

    public abstract void download(File file, ICoreNetCallback<File, ACoreRequestDownload> iCoreNetCallback);

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
    public String method() {
        return "DOWNLOAD";
    }
}
