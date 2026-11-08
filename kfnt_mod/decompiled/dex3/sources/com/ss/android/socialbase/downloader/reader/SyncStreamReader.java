package com.ss.android.socialbase.downloader.reader;

import com.ss.android.socialbase.downloader.segment.Buffer;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SyncStreamReader implements IStreamReader {
    private final Buffer buffer;
    private final InputStream inputStream;

    @Override // com.ss.android.socialbase.downloader.reader.IStreamReader
    public void recycle(Buffer buffer) {
    }

    public SyncStreamReader(InputStream inputStream, int i) {
        this.inputStream = inputStream;
        this.buffer = new Buffer(i);
    }

    @Override // com.ss.android.socialbase.downloader.reader.IStreamReader
    public Buffer read() throws IOException {
        this.buffer.size = this.inputStream.read(this.buffer.data);
        return this.buffer;
    }

    @Override // com.ss.android.socialbase.downloader.reader.IStreamReader
    public void close() {
        DownloadUtils.safeClose(this.inputStream);
    }
}
