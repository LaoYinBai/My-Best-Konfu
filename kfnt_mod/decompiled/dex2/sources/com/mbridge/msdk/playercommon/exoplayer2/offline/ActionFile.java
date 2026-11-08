package com.mbridge.msdk.playercommon.exoplayer2.offline;

import com.mbridge.msdk.playercommon.exoplayer2.offline.DownloadAction;
import com.mbridge.msdk.playercommon.exoplayer2.util.AtomicFile;
import com.mbridge.msdk.playercommon.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class ActionFile {
    static final int VERSION = 0;
    private final File actionFile;
    private final AtomicFile atomicFile;

    public ActionFile(File file) {
        this.actionFile = file;
        this.atomicFile = new AtomicFile(file);
    }

    public final DownloadAction[] load(DownloadAction.Deserializer... deserializerArr) throws Throwable {
        InputStream inputStreamOpenRead;
        if (!this.actionFile.exists()) {
            return new DownloadAction[0];
        }
        try {
            inputStreamOpenRead = this.atomicFile.openRead();
            try {
                DataInputStream dataInputStream = new DataInputStream(inputStreamOpenRead);
                int i = dataInputStream.readInt();
                if (i > 0) {
                    throw new IOException("Unsupported action file version: " + i);
                }
                int i2 = dataInputStream.readInt();
                DownloadAction[] downloadActionArr = new DownloadAction[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    downloadActionArr[i3] = DownloadAction.deserializeFromStream(deserializerArr, dataInputStream);
                }
                Util.closeQuietly(inputStreamOpenRead);
                return downloadActionArr;
            } catch (Throwable th) {
                th = th;
                Util.closeQuietly(inputStreamOpenRead);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpenRead = null;
        }
    }

    public final void store(DownloadAction... downloadActionArr) throws Throwable {
        DataOutputStream dataOutputStream;
        try {
            dataOutputStream = new DataOutputStream(this.atomicFile.startWrite());
            try {
                dataOutputStream.writeInt(0);
                dataOutputStream.writeInt(downloadActionArr.length);
                for (DownloadAction downloadAction : downloadActionArr) {
                    DownloadAction.serializeToStream(downloadAction, dataOutputStream);
                }
                this.atomicFile.endWrite(dataOutputStream);
                Util.closeQuietly((Closeable) null);
            } catch (Throwable th) {
                th = th;
                Util.closeQuietly(dataOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            dataOutputStream = null;
        }
    }
}
