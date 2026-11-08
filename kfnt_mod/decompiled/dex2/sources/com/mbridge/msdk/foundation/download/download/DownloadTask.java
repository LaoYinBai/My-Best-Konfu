package com.mbridge.msdk.foundation.download.download;

import com.mbridge.msdk.foundation.controller.a;
import com.mbridge.msdk.foundation.same.e.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownloadTask {
    private b mLoader;

    private static class DownloadTaskHolder {
        public static DownloadTask instance = new DownloadTask();

        private DownloadTaskHolder() {
        }
    }

    private DownloadTask() {
        init();
    }

    public static DownloadTask getInstance() {
        return DownloadTaskHolder.instance;
    }

    private void init() {
        if (a.f().j() != null) {
            this.mLoader = new b(a.f().j());
        }
    }

    public void runTask(com.mbridge.msdk.foundation.same.e.a aVar) {
        if (this.mLoader != null) {
            this.mLoader.b(aVar);
        }
    }
}
