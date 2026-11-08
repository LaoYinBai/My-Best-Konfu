package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import android.os.Parcelable;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class MessageSnapshot implements IMessageSnapshot, Parcelable {
    public static final Parcelable.Creator<MessageSnapshot> CREATOR = new Parcelable.Creator<MessageSnapshot>() { // from class: com.liulishuo.filedownloader.message.MessageSnapshot.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MessageSnapshot createFromParcel(Parcel parcel) {
            MessageSnapshot warnMessageSnapshot;
            boolean z = parcel.readByte() == 1;
            byte b = parcel.readByte();
            switch (b) {
                case -4:
                    if (z) {
                        warnMessageSnapshot = new LargeMessageSnapshot.WarnMessageSnapshot(parcel);
                        break;
                    } else {
                        warnMessageSnapshot = new SmallMessageSnapshot.WarnMessageSnapshot(parcel);
                        break;
                    }
                case -3:
                    if (z) {
                        warnMessageSnapshot = new LargeMessageSnapshot.CompletedSnapshot(parcel);
                        break;
                    } else {
                        warnMessageSnapshot = new SmallMessageSnapshot.CompletedSnapshot(parcel);
                        break;
                    }
                case -2:
                case 0:
                case 4:
                default:
                    warnMessageSnapshot = null;
                    break;
                case -1:
                    if (z) {
                        warnMessageSnapshot = new LargeMessageSnapshot.ErrorMessageSnapshot(parcel);
                        break;
                    } else {
                        warnMessageSnapshot = new SmallMessageSnapshot.ErrorMessageSnapshot(parcel);
                        break;
                    }
                case 1:
                    if (z) {
                        warnMessageSnapshot = new LargeMessageSnapshot.PendingMessageSnapshot(parcel);
                        break;
                    } else {
                        warnMessageSnapshot = new SmallMessageSnapshot.PendingMessageSnapshot(parcel);
                        break;
                    }
                case 2:
                    if (z) {
                        warnMessageSnapshot = new LargeMessageSnapshot.ConnectedMessageSnapshot(parcel);
                        break;
                    } else {
                        warnMessageSnapshot = new SmallMessageSnapshot.ConnectedMessageSnapshot(parcel);
                        break;
                    }
                case 3:
                    if (z) {
                        warnMessageSnapshot = new LargeMessageSnapshot.ProgressMessageSnapshot(parcel);
                        break;
                    } else {
                        warnMessageSnapshot = new SmallMessageSnapshot.ProgressMessageSnapshot(parcel);
                        break;
                    }
                case 5:
                    if (z) {
                        warnMessageSnapshot = new LargeMessageSnapshot.RetryMessageSnapshot(parcel);
                        break;
                    } else {
                        warnMessageSnapshot = new SmallMessageSnapshot.RetryMessageSnapshot(parcel);
                        break;
                    }
                case 6:
                    warnMessageSnapshot = new StartedMessageSnapshot(parcel);
                    break;
            }
            if (warnMessageSnapshot != null) {
                warnMessageSnapshot.isLargeFile = z;
                return warnMessageSnapshot;
            }
            throw new IllegalStateException("Can't restore the snapshot because unknown status: " + ((int) b));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MessageSnapshot[] newArray(int i) {
            return new MessageSnapshot[i];
        }
    };
    private final int id;
    protected boolean isLargeFile;

    public interface IWarnMessageSnapshot {
        MessageSnapshot turnToPending();
    }

    public int describeContents() {
        return 0;
    }

    MessageSnapshot(int i) {
        this.id = i;
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public int getId() {
        return this.id;
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public Throwable getThrowable() {
        throw new NoFieldException("getThrowable", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public int getRetryingTimes() {
        throw new NoFieldException("getRetryingTimes", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public boolean isResuming() {
        throw new NoFieldException("isResuming", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public String getEtag() {
        throw new NoFieldException("getEtag", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public long getLargeSofarBytes() {
        throw new NoFieldException("getLargeSofarBytes", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public long getLargeTotalBytes() {
        throw new NoFieldException("getLargeTotalBytes", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public int getSmallSofarBytes() {
        throw new NoFieldException("getSmallSofarBytes", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public int getSmallTotalBytes() {
        throw new NoFieldException("getSmallTotalBytes", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public boolean isReusedDownloadedFile() {
        throw new NoFieldException("isReusedDownloadedFile", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public String getFileName() {
        throw new NoFieldException("getFileName", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    public boolean isLargeFile() {
        return this.isLargeFile;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isLargeFile ? (byte) 1 : (byte) 0);
        parcel.writeByte(getStatus());
        parcel.writeInt(this.id);
    }

    MessageSnapshot(Parcel parcel) {
        this.id = parcel.readInt();
    }

    public static class NoFieldException extends IllegalStateException {
        NoFieldException(String str, MessageSnapshot messageSnapshot) {
            super(FileDownloadUtils.formatString("There isn't a field for '%s' in this message %d %d %s", str, Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus()), messageSnapshot.getClass().getName()));
        }
    }

    public static class StartedMessageSnapshot extends MessageSnapshot {
        @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
        public byte getStatus() {
            return (byte) 6;
        }

        StartedMessageSnapshot(int i) {
            super(i);
        }

        StartedMessageSnapshot(Parcel parcel) {
            super(parcel);
        }
    }
}
