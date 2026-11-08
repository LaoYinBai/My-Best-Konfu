package anetwork.channel.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import anet.channel.bytes.ByteArray;
import anet.channel.bytes.a;
import anet.channel.request.BodyEntry;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BodyHandlerEntry implements BodyEntry {
    public static final Parcelable.Creator<BodyHandlerEntry> CREATOR = new a();

    /* renamed from: a, reason: collision with root package name */
    anetwork.channel.aidl.d f684a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // anet.channel.request.BodyEntry
    public String getContentType() {
        return null;
    }

    /* synthetic */ BodyHandlerEntry(a aVar) {
        this();
    }

    private BodyHandlerEntry() {
        this.f684a = null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongInterface(this.f684a);
    }

    @Override // anet.channel.request.BodyEntry
    public int writeTo(OutputStream outputStream) throws IOException {
        try {
            ByteArray byteArrayA = a.C0007a.f408a.a(2048);
            int i = 0;
            while (!this.f684a.a()) {
                int iA = this.f684a.a(byteArrayA.getBuffer());
                outputStream.write(byteArrayA.getBuffer(), 0, iA);
                i += iA;
            }
            byteArrayA.recycle();
            return i;
        } catch (RemoteException e) {
            throw new IOException("RemoteException", e);
        }
    }
}
