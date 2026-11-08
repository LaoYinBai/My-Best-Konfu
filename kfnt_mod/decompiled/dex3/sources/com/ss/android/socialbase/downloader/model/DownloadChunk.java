package com.ss.android.socialbase.downloader.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.os.Parcel;
import android.os.Parcelable;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.thread.DownloadChunkRunnable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadChunk implements Parcelable {
    public static final Parcelable.Creator<DownloadChunk> CREATOR = new Parcelable.Creator<DownloadChunk>() { // from class: com.ss.android.socialbase.downloader.model.DownloadChunk.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadChunk createFromParcel(Parcel parcel) {
            return new DownloadChunk(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadChunk[] newArray(int i) {
            return new DownloadChunk[i];
        }
    };
    private static final String TAG = "DownloadChunk";
    private int bindValueCount;
    private int chunkIndex;
    private DownloadChunkRunnable chunkRunnable;
    private long contentLength;
    private AtomicLong currentOffset;
    private long endOffset;
    private DownloadChunk hostChunk;
    private AtomicInteger hostChunkIndex;
    private int id;
    private AtomicBoolean isDownloading;
    private long oldOffset;
    private boolean reuseingFirstConnection;
    private long startOffset;
    private List<DownloadChunk> subChunkList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private DownloadChunk(Builder builder) {
        if (builder == null) {
            return;
        }
        this.id = builder.id;
        this.startOffset = builder.startOffset;
        this.currentOffset = new AtomicLong(builder.currentOffset);
        this.endOffset = builder.endOffset;
        this.contentLength = builder.contentLength;
        this.chunkIndex = builder.chunkIndex;
        this.oldOffset = builder.oldOffset;
        this.hostChunkIndex = new AtomicInteger(-1);
        setHostChunk(builder.hostChunk);
        this.isDownloading = new AtomicBoolean(false);
    }

    public DownloadChunk(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        this.id = cursor.getInt(cursor.getColumnIndex("_id"));
        this.chunkIndex = cursor.getInt(cursor.getColumnIndex(DBDefinition.CHUNK_INDEX));
        this.startOffset = cursor.getLong(cursor.getColumnIndex(DBDefinition.START_OFFSET));
        int columnIndex = cursor.getColumnIndex(DBDefinition.CUR_OFFSET);
        if (columnIndex != -1) {
            this.currentOffset = new AtomicLong(cursor.getLong(columnIndex));
        } else {
            this.currentOffset = new AtomicLong(0L);
        }
        this.endOffset = cursor.getLong(cursor.getColumnIndex(DBDefinition.END_OFFSET));
        int columnIndex2 = cursor.getColumnIndex(DBDefinition.HOST_CHUNK_INDEX);
        if (columnIndex2 != -1) {
            this.hostChunkIndex = new AtomicInteger(cursor.getInt(columnIndex2));
        } else {
            this.hostChunkIndex = new AtomicInteger(-1);
        }
        int columnIndex3 = cursor.getColumnIndex(DBDefinition.CHUNK_CONTENT_LEN);
        if (columnIndex3 != -1) {
            this.contentLength = cursor.getLong(columnIndex3);
        }
        this.isDownloading = new AtomicBoolean(false);
    }

    protected DownloadChunk(Parcel parcel) {
        this.id = parcel.readInt();
        this.startOffset = parcel.readLong();
        this.currentOffset = new AtomicLong(parcel.readLong());
        this.endOffset = parcel.readLong();
        this.contentLength = parcel.readLong();
        this.chunkIndex = parcel.readInt();
        this.hostChunkIndex = new AtomicInteger(parcel.readInt());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeLong(this.startOffset);
        parcel.writeLong(this.currentOffset != null ? this.currentOffset.get() : 0L);
        parcel.writeLong(this.endOffset);
        parcel.writeLong(this.contentLength);
        parcel.writeInt(this.chunkIndex);
        parcel.writeInt(this.hostChunkIndex != null ? this.hostChunkIndex.get() : -1);
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(this.id));
        contentValues.put(DBDefinition.CHUNK_INDEX, Integer.valueOf(this.chunkIndex));
        contentValues.put(DBDefinition.START_OFFSET, Long.valueOf(this.startOffset));
        contentValues.put(DBDefinition.CUR_OFFSET, Long.valueOf(getCurrentOffset()));
        contentValues.put(DBDefinition.END_OFFSET, Long.valueOf(this.endOffset));
        contentValues.put(DBDefinition.CHUNK_CONTENT_LEN, Long.valueOf(this.contentLength));
        contentValues.put(DBDefinition.HOST_CHUNK_INDEX, Integer.valueOf(getHostChunkIndex()));
        return contentValues;
    }

    public void bindValue(SQLiteStatement sQLiteStatement) {
        if (sQLiteStatement == null) {
            return;
        }
        this.bindValueCount = 0;
        sQLiteStatement.clearBindings();
        int i = this.bindValueCount + 1;
        this.bindValueCount = i;
        sQLiteStatement.bindLong(i, this.id);
        int i2 = this.bindValueCount + 1;
        this.bindValueCount = i2;
        sQLiteStatement.bindLong(i2, this.chunkIndex);
        int i3 = this.bindValueCount + 1;
        this.bindValueCount = i3;
        sQLiteStatement.bindLong(i3, this.startOffset);
        int i4 = this.bindValueCount + 1;
        this.bindValueCount = i4;
        sQLiteStatement.bindLong(i4, getCurrentOffset());
        int i5 = this.bindValueCount + 1;
        this.bindValueCount = i5;
        sQLiteStatement.bindLong(i5, this.endOffset);
        int i6 = this.bindValueCount + 1;
        this.bindValueCount = i6;
        sQLiteStatement.bindLong(i6, this.contentLength);
        int i7 = this.bindValueCount + 1;
        this.bindValueCount = i7;
        sQLiteStatement.bindLong(i7, getHostChunkIndex());
    }

    public int getHostChunkIndex() {
        if (this.hostChunkIndex == null) {
            return -1;
        }
        return this.hostChunkIndex.get();
    }

    public void setHostChunkIndex(int i) {
        if (this.hostChunkIndex == null) {
            this.hostChunkIndex = new AtomicInteger(i);
        } else {
            this.hostChunkIndex.set(i);
        }
    }

    public boolean isDownloading() {
        if (this.isDownloading == null) {
            return false;
        }
        return this.isDownloading.get();
    }

    public void setChunkRunnable(DownloadChunkRunnable downloadChunkRunnable) {
        this.chunkRunnable = downloadChunkRunnable;
        setOldOffset();
    }

    public void setDownloading(boolean z) {
        if (this.isDownloading == null) {
            this.isDownloading = new AtomicBoolean(z);
        } else {
            this.isDownloading.set(z);
        }
        this.chunkRunnable = null;
    }

    public void setHostChunk(DownloadChunk downloadChunk) {
        this.hostChunk = downloadChunk;
        if (this.hostChunk != null) {
            setHostChunkIndex(this.hostChunk.getChunkIndex());
        }
    }

    public DownloadChunk getHostChunk() {
        return this.hostChunk;
    }

    public boolean isHostChunk() {
        return getHostChunkIndex() == -1;
    }

    public DownloadChunk getFirstReuseChunk() {
        DownloadChunk downloadChunk = !isHostChunk() ? this.hostChunk : this;
        if (downloadChunk == null || !downloadChunk.hasChunkDivided()) {
            return null;
        }
        return downloadChunk.getSubChunkList().get(0);
    }

    public boolean hasChunkDivided() {
        return this.subChunkList != null && this.subChunkList.size() > 0;
    }

    public void setSubChunkList(List<DownloadChunk> list) {
        this.subChunkList = list;
    }

    public long getOldOffset() {
        return this.oldOffset;
    }

    public void setOldOffset(long j) {
        this.oldOffset = j;
    }

    public List<DownloadChunk> getSubChunkList() {
        return this.subChunkList;
    }

    public boolean canRefreshCurOffsetForReuseChunk() {
        if (this.hostChunk == null) {
            return true;
        }
        if (!this.hostChunk.hasChunkDivided()) {
            return false;
        }
        for (int i = 0; i < this.hostChunk.getSubChunkList().size(); i++) {
            DownloadChunk downloadChunk = this.hostChunk.getSubChunkList().get(i);
            if (downloadChunk != null) {
                int iIndexOf = this.hostChunk.getSubChunkList().indexOf(this);
                if (iIndexOf > i && !downloadChunk.hasNoBytesDownload()) {
                    return false;
                }
                if (iIndexOf == i) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setReuseingFirstConnection(boolean z) {
        this.reuseingFirstConnection = z;
    }

    public boolean hasNoBytesDownload() {
        long j = this.startOffset;
        if (isHostChunk() && this.oldOffset > this.startOffset) {
            j = this.oldOffset;
        }
        return getCurrentOffset() - j >= this.contentLength;
    }

    public long getNextChunkCurOffset() {
        if (this.hostChunk == null || this.hostChunk.getSubChunkList() == null) {
            return -1L;
        }
        int iIndexOf = this.hostChunk.getSubChunkList().indexOf(this);
        boolean z = false;
        for (int i = 0; i < this.hostChunk.getSubChunkList().size(); i++) {
            DownloadChunk downloadChunk = this.hostChunk.getSubChunkList().get(i);
            if (downloadChunk != null) {
                if (z) {
                    return downloadChunk.getCurrentOffset();
                }
                if (iIndexOf == i) {
                    z = true;
                }
            }
        }
        return -1L;
    }

    public boolean isReuseingFirstConnection() {
        return this.chunkIndex == 0 && this.reuseingFirstConnection;
    }

    public int getBindValueCount() {
        return this.bindValueCount;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setChunkIndex(int i) {
        this.chunkIndex = i;
    }

    public void setContentLength(long j) {
        this.contentLength = j;
    }

    public int getId() {
        return this.id;
    }

    public long getStartOffset() {
        return this.startOffset;
    }

    public long getCurOffset() {
        if (this.currentOffset != null) {
            return this.currentOffset.get();
        }
        return 0L;
    }

    public long getCurrentOffset() {
        if (isHostChunk() && hasChunkDivided()) {
            long curOffset = 0;
            for (int i = 0; i < this.subChunkList.size(); i++) {
                DownloadChunk downloadChunk = this.subChunkList.get(i);
                if (downloadChunk != null) {
                    if (!downloadChunk.hasNoBytesDownload()) {
                        return downloadChunk.getCurOffset();
                    }
                    if (curOffset < downloadChunk.getCurOffset()) {
                        curOffset = downloadChunk.getCurOffset();
                    }
                }
            }
            return curOffset;
        }
        return getCurOffset();
    }

    public void setCurrentOffset(long j) {
        if (this.currentOffset != null) {
            this.currentOffset.set(j);
        } else {
            this.currentOffset = new AtomicLong(j);
        }
    }

    public long getDownloadChunkBytes() {
        long currentOffset = getCurrentOffset() - this.startOffset;
        if (hasChunkDivided()) {
            currentOffset = 0;
            for (int i = 0; i < this.subChunkList.size(); i++) {
                DownloadChunk downloadChunk = this.subChunkList.get(i);
                if (downloadChunk != null) {
                    currentOffset += downloadChunk.getCurrentOffset() - downloadChunk.getStartOffset();
                }
            }
        }
        return currentOffset;
    }

    public long getEndOffset() {
        return this.endOffset;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public void setOldOffset() {
        this.oldOffset = getCurrentOffset();
    }

    public long getRetainLength(boolean z) {
        long currentOffset = getCurrentOffset();
        long j = this.contentLength - (currentOffset - this.oldOffset);
        if (!z && currentOffset == this.oldOffset) {
            j = this.contentLength - (currentOffset - this.startOffset);
        }
        Logger.d("DownloadChunk", "contentLength:" + this.contentLength + " curOffset:" + getCurrentOffset() + " oldOffset:" + this.oldOffset + " retainLen:" + j);
        if (j < 0) {
            return 0L;
        }
        return j;
    }

    public List<DownloadChunk> divideChunkForReuse(int i, long j) {
        long endOffset;
        long j2;
        long j3;
        long startOffset;
        long j4;
        if (!isHostChunk() || hasChunkDivided()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        long curOffset = getCurOffset();
        int i2 = 1;
        long retainLength = getRetainLength(true);
        long j5 = retainLength / i;
        Logger.d(TAG, "retainLen:" + retainLength + " divideChunkForReuse chunkSize:" + j5 + " current host downloadChunk index:" + this.chunkIndex);
        long j6 = curOffset;
        int i3 = 0;
        while (i3 < i) {
            if (i3 == 0) {
                j2 = (j6 + j5) - 1;
                j3 = j5;
                startOffset = getStartOffset();
            } else {
                int i4 = i - 1;
                if (i3 == i4) {
                    long endOffset2 = getEndOffset();
                    j3 = endOffset2 > j6 ? 1 + (endOffset2 - j6) : retainLength - (i4 * j5);
                    j4 = endOffset2;
                    startOffset = j6;
                    Builder builderOldOffset = new Builder(this.id).chunkIndex((-i3) - i2).startOffset(startOffset).currentOffset(j6).oldOffset(j6);
                    long j7 = j4;
                    long j8 = j6;
                    long j9 = j3;
                    DownloadChunk downloadChunkBuild = builderOldOffset.endOffset(j7).contentLength(j9).hostChunk(this).build();
                    Logger.d(TAG, "divide sub chunk : " + i3 + " startOffset:" + startOffset + " curOffset:" + j8 + " endOffset:" + j7 + " contentLen:" + j9);
                    arrayList.add(downloadChunkBuild);
                    j6 = j8 + j5;
                    i3++;
                    retainLength = retainLength;
                    i2 = 1;
                } else {
                    j2 = (j6 + j5) - 1;
                    j3 = j5;
                    startOffset = j6;
                }
            }
            j4 = j2;
            Builder builderOldOffset2 = new Builder(this.id).chunkIndex((-i3) - i2).startOffset(startOffset).currentOffset(j6).oldOffset(j6);
            long j72 = j4;
            long j82 = j6;
            long j92 = j3;
            DownloadChunk downloadChunkBuild2 = builderOldOffset2.endOffset(j72).contentLength(j92).hostChunk(this).build();
            Logger.d(TAG, "divide sub chunk : " + i3 + " startOffset:" + startOffset + " curOffset:" + j82 + " endOffset:" + j72 + " contentLen:" + j92);
            arrayList.add(downloadChunkBuild2);
            j6 = j82 + j5;
            i3++;
            retainLength = retainLength;
            i2 = 1;
        }
        long contentLength = 0;
        for (int size = arrayList.size() - 1; size > 0; size--) {
            DownloadChunk downloadChunk = arrayList.get(size);
            if (downloadChunk != null) {
                contentLength += downloadChunk.getContentLength();
            }
        }
        Logger.d(TAG, "reuseChunkContentLen:" + contentLength);
        DownloadChunk downloadChunk2 = arrayList.get(0);
        if (downloadChunk2 != null) {
            if (getEndOffset() == 0) {
                endOffset = j - getStartOffset();
            } else {
                endOffset = (getEndOffset() - getStartOffset()) + 1;
            }
            downloadChunk2.setContentLength(endOffset - contentLength);
            downloadChunk2.setChunkIndex(this.chunkIndex);
            if (this.chunkRunnable != null) {
                this.chunkRunnable.refreshResponseHandleOffset(downloadChunk2.getEndOffset(), getContentLength() - contentLength);
            }
        }
        setSubChunkList(arrayList);
        return arrayList;
    }

    public int getChunkIndex() {
        return this.chunkIndex;
    }

    public static class Builder {
        private int chunkIndex;
        private long contentLength;
        private long currentOffset;
        private long endOffset;
        private DownloadChunk hostChunk;
        private int id;
        private long oldOffset;
        private long startOffset;

        public Builder(int i) {
            this.id = i;
        }

        public Builder id(int i) {
            this.id = i;
            return this;
        }

        public Builder startOffset(long j) {
            this.startOffset = j;
            return this;
        }

        public Builder currentOffset(long j) {
            this.currentOffset = j;
            return this;
        }

        public Builder endOffset(long j) {
            this.endOffset = j;
            return this;
        }

        public Builder contentLength(long j) {
            this.contentLength = j;
            return this;
        }

        public Builder chunkIndex(int i) {
            this.chunkIndex = i;
            return this;
        }

        public Builder hostChunk(DownloadChunk downloadChunk) {
            this.hostChunk = downloadChunk;
            return this;
        }

        public Builder oldOffset(long j) {
            this.oldOffset = j;
            return this;
        }

        public DownloadChunk build() {
            return new DownloadChunk(this);
        }
    }
}
