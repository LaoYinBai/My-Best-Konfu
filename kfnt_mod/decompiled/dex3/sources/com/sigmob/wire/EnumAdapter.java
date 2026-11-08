package com.sigmob.wire;

import com.sigmob.wire.ProtoAdapter;
import com.sigmob.wire.WireEnum;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class EnumAdapter<E extends WireEnum> extends ProtoAdapter<E> {
    protected EnumAdapter(Class<E> cls) {
        super(FieldEncoding.VARINT, cls);
    }

    @Override // com.sigmob.wire.ProtoAdapter
    public final E decode(ProtoReader protoReader) {
        int varint32 = protoReader.readVarint32();
        E e = (E) fromValue(varint32);
        if (e != null) {
            return e;
        }
        throw new ProtoAdapter.EnumConstantNotFoundException(varint32, this.javaType);
    }

    @Override // com.sigmob.wire.ProtoAdapter
    public final void encode(ProtoWriter protoWriter, E e) {
        protoWriter.writeVarint32(e.getValue());
    }

    @Override // com.sigmob.wire.ProtoAdapter
    public final int encodedSize(E e) {
        return ProtoWriter.varint32Size(e.getValue());
    }

    protected abstract E fromValue(int i);
}
