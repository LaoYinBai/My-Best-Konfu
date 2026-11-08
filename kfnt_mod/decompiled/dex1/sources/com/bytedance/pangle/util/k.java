package com.bytedance.pangle.util;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class k {
    public static long a(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            return a(randomAccessFile, a(randomAccessFile));
        } finally {
            randomAccessFile.close();
        }
    }

    private static a a(RandomAccessFile randomAccessFile) throws IOException {
        long length = randomAccessFile.length() - 22;
        if (length < 0) {
            throw new ZipException("File too short to be a zip file: " + randomAccessFile.length());
        }
        long j = length - PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        long j2 = j >= 0 ? j : 0L;
        int iReverseBytes = Integer.reverseBytes(101010256);
        do {
            randomAccessFile.seek(length);
            if (randomAccessFile.readInt() == iReverseBytes) {
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                a aVar = new a();
                aVar.f4720b = Integer.reverseBytes(randomAccessFile.readInt()) & 4294967295L;
                aVar.f4719a = Integer.reverseBytes(randomAccessFile.readInt()) & 4294967295L;
                return aVar;
            }
            length--;
        } while (length >= j2);
        throw new ZipException("End Of Central Directory signature not found");
    }

    private static long a(RandomAccessFile randomAccessFile, a aVar) throws IOException {
        CRC32 crc32 = new CRC32();
        long j = aVar.f4720b;
        randomAccessFile.seek(aVar.f4719a);
        int iMin = (int) Math.min(PlaybackStateCompat.ACTION_PREPARE, j);
        byte[] bArr = new byte[16384];
        while (true) {
            int i = randomAccessFile.read(bArr, 0, iMin);
            if (i == -1) {
                break;
            }
            crc32.update(bArr, 0, i);
            j -= i;
            if (j == 0) {
                break;
            }
            iMin = (int) Math.min(PlaybackStateCompat.ACTION_PREPARE, j);
        }
        return crc32.getValue();
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        long f4719a;

        /* renamed from: b, reason: collision with root package name */
        long f4720b;

        a() {
        }
    }
}
