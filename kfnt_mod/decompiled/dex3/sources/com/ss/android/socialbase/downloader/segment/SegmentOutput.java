package com.ss.android.socialbase.downloader.segment;

import androidx.annotation.NonNull;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.RandomAccessOutputStream;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class SegmentOutput implements IOutput {
    private final RandomAccessOutputStream output;
    private final Segment segment;
    private final IOutput stub;

    public SegmentOutput(DownloadInfo downloadInfo, BufferQueue bufferQueue, Segment segment) throws BaseException {
        this.segment = segment;
        this.output = createOutStream(downloadInfo, segment);
        this.stub = new OutputStub(bufferQueue, this);
    }

    public IOutput getStub() {
        return this.stub;
    }

    @Override // com.ss.android.socialbase.downloader.segment.IOutput
    public void write(@NonNull Buffer buffer) throws IOException {
        this.output.write(buffer.data, 0, buffer.size);
        this.segment.increaseCurrentOffset(buffer.size);
    }

    public void flush() throws IOException {
        this.output.flush();
    }

    public void sync() throws IOException {
        this.output.sync();
    }

    public void close() {
        DownloadUtils.safeClose(this.output);
    }

    public Segment getSegment() {
        return this.segment;
    }

    private RandomAccessOutputStream createOutStream(DownloadInfo downloadInfo, Segment segment) throws InterruptedException, IOException, BaseException {
        RandomAccessOutputStream randomAccessOutputStreamCreateOutputStream = DownloadUtils.createOutputStream(downloadInfo, downloadInfo.getTempPath(), downloadInfo.getTempName(), DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.FLUSH_BUFFER_SIZE_BYTE, -1));
        try {
            randomAccessOutputStreamCreateOutputStream.seek(segment.getCurrentOffsetRead());
            return randomAccessOutputStreamCreateOutputStream;
        } catch (IOException e) {
            throw new BaseException(DownloadErrorCode.ERROR_RANDOM_ACCESS_SEEK_IO, e);
        }
    }
}
