package com.mbridge.msdk.mbdownload;

import com.mbridge.msdk.MBridgeConstans;

/* compiled from: MBDownloadAgent.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends b {
    public f(String str, String str2) {
        super(str, "mb", str2);
        if (MBridgeConstans.RICH_NOTIFICATION) {
            setRichNotification(true);
        } else {
            setRichNotification(false);
        }
        if (MBridgeConstans.SLIENT_DOWNLOAD) {
            setSilentDownload(true);
        } else {
            setSilentDownload(false);
        }
        setDownloadClz("com.mbridge.msdk.shell.MBService");
    }

    public f(String str, String str2, String str3, boolean z) {
        super(str, str2, str3);
        setRichNotification(z);
        setDownloadClz("com.mbridge.msdk.shell.MBService");
    }
}
