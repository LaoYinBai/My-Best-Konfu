package com.ss.cgpa;

import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeDataHelp {
    public final native void closeDataClientHelp();

    public final native void flushCacheMsg();

    public final native void freeMsgInfo(long j);

    public final native int getCgpMsgInfoSizes(long j);

    public final native byte[] packCgpMsgInfo(long j, int i);

    public final native boolean procReadCgpMsg(long j);

    public final native void setDataClientHelp(DataClientHelp dataClientHelp);

    public final native void stopReport();

    public final native long unpackCgpMsgInfo(byte[] bArr);
}
