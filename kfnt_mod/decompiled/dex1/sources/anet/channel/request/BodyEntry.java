package anet.channel.request;

import android.os.Parcelable;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface BodyEntry extends Parcelable {
    String getContentType();

    int writeTo(OutputStream outputStream) throws IOException;
}
