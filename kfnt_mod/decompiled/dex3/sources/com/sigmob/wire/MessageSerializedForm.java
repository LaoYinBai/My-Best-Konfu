package com.sigmob.wire;

import com.sigmob.wire.Message;
import com.sigmob.wire.Message.Builder;
import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class MessageSerializedForm<M extends Message<M, B>, B extends Message.Builder<M, B>> implements Serializable {
    private static final long serialVersionUID = 0;
    private final byte[] bytes;
    private final Class<M> messageClass;

    MessageSerializedForm(byte[] bArr, Class<M> cls) {
        this.bytes = bArr;
        this.messageClass = cls;
    }

    Object readResolve() throws StreamCorruptedException {
        try {
            return ProtoAdapter.get(this.messageClass).decode(this.bytes);
        } catch (IOException e) {
            throw new StreamCorruptedException(e.getMessage());
        }
    }
}
