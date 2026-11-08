package com.kwai.filedownloader.services;

import android.content.Intent;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    public static void a(com.kwai.filedownloader.c.c cVar) {
        if (cVar == null) {
            throw new IllegalArgumentException();
        }
        if (cVar.f() != -3) {
            throw new IllegalStateException();
        }
        Intent intent = new Intent(FileDownloadBroadcastHandler.ACTION_COMPLETED);
        intent.putExtra(FileDownloadBroadcastHandler.KEY_MODEL, cVar);
        com.kwai.filedownloader.e.c.a().sendBroadcast(intent);
    }
}
