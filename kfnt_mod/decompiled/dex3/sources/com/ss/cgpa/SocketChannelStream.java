package com.ss.cgpa;

import androidx.annotation.Keep;
import java.nio.ByteBuffer;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
interface SocketChannelStream {
    void close();

    ByteBuffer getReadByteBuffer();

    ByteBuffer getWriteByteBuffer();

    boolean procByteBuffer();
}
