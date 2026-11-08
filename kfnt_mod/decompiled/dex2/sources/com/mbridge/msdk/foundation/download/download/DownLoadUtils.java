package com.mbridge.msdk.foundation.download.download;

import android.webkit.URLUtil;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.same.e.a;
import com.mbridge.msdk.foundation.tools.z;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownLoadUtils {
    private static final int DOWNLOAD_CONNECTION_TIME_OUT = 20000;
    private static final int DOWNLOAD_READ_TIME_OUT = 30000;
    public static final String END_TAG = "<mbridgeloadend></mbridgeloadend>";
    private static final String TAG = "DownLoadUtils";

    public static void getSourceCodeFromNetUrl(final String str, final H5DownLoadManager.IOnDownLoadH5Source iOnDownLoadH5Source, final boolean z) {
        try {
            if (!z.a(str) && URLUtil.isNetworkUrl(str)) {
                DownloadTask.getInstance().runTask(new a() { // from class: com.mbridge.msdk.foundation.download.download.DownLoadUtils.1
                    @Override // com.mbridge.msdk.foundation.same.e.a
                    public void cancelTask() {
                    }

                    @Override // com.mbridge.msdk.foundation.same.e.a
                    public void pauseTask(boolean z2) {
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Removed duplicated region for block: B:104:0x00e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:49:0x00d2 A[Catch: Exception -> 0x00d6, TRY_ENTER, TryCatch #10 {Exception -> 0x00d6, blocks: (B:36:0x009d, B:38:0x00a2, B:49:0x00d2, B:53:0x00da), top: B:114:0x0004 }] */
                    /* JADX WARN: Removed duplicated region for block: B:53:0x00da A[Catch: Exception -> 0x00d6, TRY_LEAVE, TryCatch #10 {Exception -> 0x00d6, blocks: (B:36:0x009d, B:38:0x00a2, B:49:0x00d2, B:53:0x00da), top: B:114:0x0004 }] */
                    /* JADX WARN: Removed duplicated region for block: B:66:0x00fc  */
                    @Override // com.mbridge.msdk.foundation.same.e.a
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public void runTask() throws java.lang.Throwable {
                        /*
                            Method dump skipped, instructions count: 362
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.download.download.DownLoadUtils.AnonymousClass1.runTask():void");
                    }
                });
                return;
            }
            if (iOnDownLoadH5Source != null) {
                iOnDownLoadH5Source.onFailed("url is error");
            }
        } catch (Throwable th) {
            if (MBridgeConstans.DEBUG) {
                th.printStackTrace();
            }
        }
    }
}
