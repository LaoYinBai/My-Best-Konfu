package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.cache2;

import android.support.v4.media.session.PlaybackStateCompat;
import com.bytedance.sdk.adnet.game.ok3.okio.Buffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class FileOperator {
    private static final int BUFFER_SIZE = 8192;
    private final byte[] byteArray = new byte[8192];
    private final ByteBuffer byteBuffer = ByteBuffer.wrap(this.byteArray);
    private final FileChannel fileChannel;

    FileOperator(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    public void read(long j, Buffer buffer, long j2) {
        if (j2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            try {
                this.byteBuffer.limit((int) Math.min(PlaybackStateCompat.ACTION_PLAY_FROM_URI, j2));
                if (this.fileChannel.read(this.byteBuffer, j) == -1) {
                    throw new EOFException();
                }
                int iPosition = this.byteBuffer.position();
                buffer.write(this.byteArray, 0, iPosition);
                long j3 = iPosition;
                j += j3;
                j2 -= j3;
            } finally {
                this.byteBuffer.clear();
            }
        }
    }

    public void write(long j, Buffer buffer, long j2) {
        if (j2 < 0 || j2 > buffer.size()) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            try {
                int iMin = (int) Math.min(PlaybackStateCompat.ACTION_PLAY_FROM_URI, j2);
                buffer.read(this.byteArray, 0, iMin);
                this.byteBuffer.limit(iMin);
                do {
                    j += this.fileChannel.write(this.byteBuffer, j);
                } while (this.byteBuffer.hasRemaining());
                j2 -= iMin;
            } finally {
                this.byteBuffer.clear();
            }
        }
    }
}
