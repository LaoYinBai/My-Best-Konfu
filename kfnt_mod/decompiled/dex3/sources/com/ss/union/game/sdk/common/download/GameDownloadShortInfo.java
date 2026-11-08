package com.ss.union.game.sdk.common.download;

import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GameDownloadShortInfo {
    public String fileName;
    public boolean onlyWifi;
    public long id = -1;
    public int status = -1;
    public long totalBytes = -1;
    public long currentBytes = -1;
    public int failStatus = 0;

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.id), Integer.valueOf(this.status), Long.valueOf(this.totalBytes), this.fileName});
    }
}
