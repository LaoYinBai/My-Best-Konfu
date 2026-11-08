package com.sigmob.wire;

import com.sigmob.wire.Message;
import com.sigmob.wire.WireField;
import com.sigmob.wire.okio.Buffer;
import com.sigmob.wire.okio.BufferedSink;
import com.sigmob.wire.okio.BufferedSource;
import com.sigmob.wire.okio.ByteString;
import com.sigmob.wire.okio.Okio;
import com.sigmob.wire.okio.Utf8;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ProtoAdapter<E> {
    private static final int FIXED_32_SIZE = 4;
    private static final int FIXED_64_SIZE = 8;
    private static final int FIXED_BOOL_SIZE = 1;
    private final FieldEncoding fieldEncoding;
    final Class<?> javaType;
    ProtoAdapter<List<E>> packedAdapter;
    ProtoAdapter<List<E>> repeatedAdapter;
    public static final ProtoAdapter<Boolean> BOOL = new ProtoAdapter<Boolean>(FieldEncoding.VARINT, Boolean.class) { // from class: com.sigmob.wire.ProtoAdapter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Boolean decode(ProtoReader protoReader) throws IOException {
            int varint32 = protoReader.readVarint32();
            if (varint32 == 0) {
                return Boolean.FALSE;
            }
            if (varint32 == 1) {
                return Boolean.TRUE;
            }
            throw new IOException(String.format("Invalid boolean value 0x%02x", Integer.valueOf(varint32)));
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Boolean bool) {
            protoWriter.writeVarint32(bool.booleanValue() ? 1 : 0);
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Boolean bool) {
            return 1;
        }
    };
    public static final ProtoAdapter<Integer> INT32 = new ProtoAdapter<Integer>(FieldEncoding.VARINT, Integer.class) { // from class: com.sigmob.wire.ProtoAdapter.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Integer decode(ProtoReader protoReader) {
            return Integer.valueOf(protoReader.readVarint32());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Integer num) {
            protoWriter.writeSignedVarint32(num.intValue());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Integer num) {
            return ProtoWriter.int32Size(num.intValue());
        }
    };
    public static final ProtoAdapter<Integer> UINT32 = new ProtoAdapter<Integer>(FieldEncoding.VARINT, Integer.class) { // from class: com.sigmob.wire.ProtoAdapter.3
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Integer decode(ProtoReader protoReader) {
            return Integer.valueOf(protoReader.readVarint32());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Integer num) {
            protoWriter.writeVarint32(num.intValue());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Integer num) {
            return ProtoWriter.varint32Size(num.intValue());
        }
    };
    public static final ProtoAdapter<Integer> SINT32 = new ProtoAdapter<Integer>(FieldEncoding.VARINT, Integer.class) { // from class: com.sigmob.wire.ProtoAdapter.4
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Integer decode(ProtoReader protoReader) {
            return Integer.valueOf(ProtoWriter.decodeZigZag32(protoReader.readVarint32()));
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Integer num) {
            protoWriter.writeVarint32(ProtoWriter.encodeZigZag32(num.intValue()));
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Integer num) {
            return ProtoWriter.varint32Size(ProtoWriter.encodeZigZag32(num.intValue()));
        }
    };
    public static final ProtoAdapter<Integer> FIXED32 = new ProtoAdapter<Integer>(FieldEncoding.FIXED32, Integer.class) { // from class: com.sigmob.wire.ProtoAdapter.5
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Integer decode(ProtoReader protoReader) {
            return Integer.valueOf(protoReader.readFixed32());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Integer num) {
            protoWriter.writeFixed32(num.intValue());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Integer num) {
            return 4;
        }
    };
    public static final ProtoAdapter<Integer> SFIXED32 = FIXED32;
    public static final ProtoAdapter<Long> INT64 = new ProtoAdapter<Long>(FieldEncoding.VARINT, Long.class) { // from class: com.sigmob.wire.ProtoAdapter.6
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Long decode(ProtoReader protoReader) {
            return Long.valueOf(protoReader.readVarint64());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Long l) {
            protoWriter.writeVarint64(l.longValue());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Long l) {
            return ProtoWriter.varint64Size(l.longValue());
        }
    };
    public static final ProtoAdapter<Long> UINT64 = new ProtoAdapter<Long>(FieldEncoding.VARINT, Long.class) { // from class: com.sigmob.wire.ProtoAdapter.7
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Long decode(ProtoReader protoReader) {
            return Long.valueOf(protoReader.readVarint64());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Long l) {
            protoWriter.writeVarint64(l.longValue());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Long l) {
            return ProtoWriter.varint64Size(l.longValue());
        }
    };
    public static final ProtoAdapter<Long> SINT64 = new ProtoAdapter<Long>(FieldEncoding.VARINT, Long.class) { // from class: com.sigmob.wire.ProtoAdapter.8
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Long decode(ProtoReader protoReader) {
            return Long.valueOf(ProtoWriter.decodeZigZag64(protoReader.readVarint64()));
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Long l) {
            protoWriter.writeVarint64(ProtoWriter.encodeZigZag64(l.longValue()));
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Long l) {
            return ProtoWriter.varint64Size(ProtoWriter.encodeZigZag64(l.longValue()));
        }
    };
    public static final ProtoAdapter<Long> FIXED64 = new ProtoAdapter<Long>(FieldEncoding.FIXED64, Long.class) { // from class: com.sigmob.wire.ProtoAdapter.9
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Long decode(ProtoReader protoReader) {
            return Long.valueOf(protoReader.readFixed64());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Long l) {
            protoWriter.writeFixed64(l.longValue());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Long l) {
            return 8;
        }
    };
    public static final ProtoAdapter<Long> SFIXED64 = FIXED64;
    public static final ProtoAdapter<Float> FLOAT = new ProtoAdapter<Float>(FieldEncoding.FIXED32, Float.class) { // from class: com.sigmob.wire.ProtoAdapter.10
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Float decode(ProtoReader protoReader) {
            return Float.valueOf(Float.intBitsToFloat(protoReader.readFixed32()));
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Float f) {
            protoWriter.writeFixed32(Float.floatToIntBits(f.floatValue()));
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Float f) {
            return 4;
        }
    };
    public static final ProtoAdapter<Double> DOUBLE = new ProtoAdapter<Double>(FieldEncoding.FIXED64, Double.class) { // from class: com.sigmob.wire.ProtoAdapter.11
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Double decode(ProtoReader protoReader) {
            return Double.valueOf(Double.longBitsToDouble(protoReader.readFixed64()));
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Double d) {
            protoWriter.writeFixed64(Double.doubleToLongBits(d.doubleValue()));
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Double d) {
            return 8;
        }
    };
    public static final ProtoAdapter<String> STRING = new ProtoAdapter<String>(FieldEncoding.LENGTH_DELIMITED, String.class) { // from class: com.sigmob.wire.ProtoAdapter.12
        @Override // com.sigmob.wire.ProtoAdapter
        public String decode(ProtoReader protoReader) {
            return protoReader.readString();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, String str) {
            protoWriter.writeString(str);
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(String str) {
            return (int) Utf8.size(str);
        }
    };
    public static final ProtoAdapter<ByteString> BYTES = new ProtoAdapter<ByteString>(FieldEncoding.LENGTH_DELIMITED, ByteString.class) { // from class: com.sigmob.wire.ProtoAdapter.13
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public ByteString decode(ProtoReader protoReader) {
            return protoReader.readBytes();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, ByteString byteString) {
            protoWriter.writeBytes(byteString);
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(ByteString byteString) {
            return byteString.size();
        }
    };

    public static final class EnumConstantNotFoundException extends IllegalArgumentException {
        public final int value;

        public EnumConstantNotFoundException(int i, Class<?> cls) {
            super("Unknown enum tag " + i + " for " + cls.getCanonicalName());
            this.value = i;
        }
    }

    private static final class MapEntryProtoAdapter<K, V> extends ProtoAdapter<Map.Entry<K, V>> {
        final ProtoAdapter<K> keyAdapter;
        final ProtoAdapter<V> valueAdapter;

        MapEntryProtoAdapter(ProtoAdapter<K> protoAdapter, ProtoAdapter<V> protoAdapter2) {
            super(FieldEncoding.LENGTH_DELIMITED, Map.Entry.class);
            this.keyAdapter = protoAdapter;
            this.valueAdapter = protoAdapter2;
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Map.Entry<K, V> decode(ProtoReader protoReader) {
            throw new UnsupportedOperationException();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Map.Entry<K, V> entry) {
            this.keyAdapter.encodeWithTag(protoWriter, 1, entry.getKey());
            this.valueAdapter.encodeWithTag(protoWriter, 2, entry.getValue());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Map.Entry<K, V> entry) {
            return this.keyAdapter.encodedSizeWithTag(1, entry.getKey()) + this.valueAdapter.encodedSizeWithTag(2, entry.getValue());
        }
    }

    private static final class MapProtoAdapter<K, V> extends ProtoAdapter<Map<K, V>> {
        private final MapEntryProtoAdapter<K, V> entryAdapter;

        MapProtoAdapter(ProtoAdapter<K> protoAdapter, ProtoAdapter<V> protoAdapter2) {
            super(FieldEncoding.LENGTH_DELIMITED, Map.class);
            this.entryAdapter = new MapEntryProtoAdapter<>(protoAdapter, protoAdapter2);
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Map<K, V> decode(ProtoReader protoReader) {
            long jBeginMessage = protoReader.beginMessage();
            K kDecode = null;
            V vDecode = null;
            while (true) {
                int iNextTag = protoReader.nextTag();
                if (iNextTag == -1) {
                    protoReader.endMessage(jBeginMessage);
                    if (kDecode == null) {
                        throw new IllegalStateException("Map entry with null key");
                    }
                    if (vDecode != null) {
                        return Collections.singletonMap(kDecode, vDecode);
                    }
                    throw new IllegalStateException("Map entry with null value");
                }
                switch (iNextTag) {
                    case 1:
                        kDecode = this.entryAdapter.keyAdapter.decode(protoReader);
                        break;
                    case 2:
                        vDecode = this.entryAdapter.valueAdapter.decode(protoReader);
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Map<K, V> map) {
            throw new UnsupportedOperationException("Repeated values can only be encoded with a tag.");
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encodeWithTag(ProtoWriter protoWriter, int i, Map<K, V> map) {
            Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                this.entryAdapter.encodeWithTag(protoWriter, i, it.next());
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Map<K, V> map) {
            throw new UnsupportedOperationException("Repeated values can only be sized with a tag.");
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSizeWithTag(int i, Map<K, V> map) {
            Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
            int iEncodedSizeWithTag = 0;
            while (it.hasNext()) {
                iEncodedSizeWithTag += this.entryAdapter.encodedSizeWithTag(i, it.next());
            }
            return iEncodedSizeWithTag;
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Map<K, V> redact(Map<K, V> map) {
            return Collections.emptyMap();
        }
    }

    public ProtoAdapter(FieldEncoding fieldEncoding, Class<?> cls) {
        this.fieldEncoding = fieldEncoding;
        this.javaType = cls;
    }

    private ProtoAdapter<List<E>> createPacked() {
        if (this.fieldEncoding != FieldEncoding.LENGTH_DELIMITED) {
            return new ProtoAdapter<List<E>>(FieldEncoding.LENGTH_DELIMITED, List.class) { // from class: com.sigmob.wire.ProtoAdapter.14
                @Override // com.sigmob.wire.ProtoAdapter
                public List<E> decode(ProtoReader protoReader) {
                    return Collections.singletonList(ProtoAdapter.this.decode(protoReader));
                }

                @Override // com.sigmob.wire.ProtoAdapter
                public void encode(ProtoWriter protoWriter, List<E> list) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        ProtoAdapter.this.encode(protoWriter, (ProtoWriter) list.get(i));
                    }
                }

                @Override // com.sigmob.wire.ProtoAdapter
                public void encodeWithTag(ProtoWriter protoWriter, int i, List<E> list) {
                    if (list.isEmpty()) {
                        return;
                    }
                    super.encodeWithTag(protoWriter, i, (int) list);
                }

                @Override // com.sigmob.wire.ProtoAdapter
                public int encodedSize(List<E> list) {
                    int size = list.size();
                    int iEncodedSize = 0;
                    for (int i = 0; i < size; i++) {
                        iEncodedSize += ProtoAdapter.this.encodedSize(list.get(i));
                    }
                    return iEncodedSize;
                }

                @Override // com.sigmob.wire.ProtoAdapter
                public int encodedSizeWithTag(int i, List<E> list) {
                    if (list.isEmpty()) {
                        return 0;
                    }
                    return super.encodedSizeWithTag(i, (int) list);
                }

                @Override // com.sigmob.wire.ProtoAdapter
                public List<E> redact(List<E> list) {
                    return Collections.emptyList();
                }
            };
        }
        throw new IllegalArgumentException("Unable to pack a length-delimited type.");
    }

    private ProtoAdapter<List<E>> createRepeated() {
        return new ProtoAdapter<List<E>>(this.fieldEncoding, List.class) { // from class: com.sigmob.wire.ProtoAdapter.15
            @Override // com.sigmob.wire.ProtoAdapter
            public List<E> decode(ProtoReader protoReader) {
                return Collections.singletonList(ProtoAdapter.this.decode(protoReader));
            }

            @Override // com.sigmob.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, List<E> list) {
                throw new UnsupportedOperationException("Repeated values can only be encoded with a tag.");
            }

            @Override // com.sigmob.wire.ProtoAdapter
            public void encodeWithTag(ProtoWriter protoWriter, int i, List<E> list) {
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ProtoAdapter.this.encodeWithTag(protoWriter, i, list.get(i2));
                }
            }

            @Override // com.sigmob.wire.ProtoAdapter
            public int encodedSize(List<E> list) {
                throw new UnsupportedOperationException("Repeated values can only be sized with a tag.");
            }

            @Override // com.sigmob.wire.ProtoAdapter
            public int encodedSizeWithTag(int i, List<E> list) {
                int size = list.size();
                int iEncodedSizeWithTag = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    iEncodedSizeWithTag += ProtoAdapter.this.encodedSizeWithTag(i, list.get(i2));
                }
                return iEncodedSizeWithTag;
            }

            @Override // com.sigmob.wire.ProtoAdapter
            public List<E> redact(List<E> list) {
                return Collections.emptyList();
            }
        };
    }

    public static <M extends Message> ProtoAdapter<M> get(M m) {
        return get(m.getClass());
    }

    public static <M> ProtoAdapter<M> get(Class<M> cls) {
        try {
            return (ProtoAdapter) cls.getField("ADAPTER").get(null);
        } catch (Throwable th) {
            throw new IllegalArgumentException("failed to access " + cls.getName() + "#ADAPTER", th);
        }
    }

    public static ProtoAdapter<?> get(String str) {
        try {
            int iIndexOf = str.indexOf(35);
            return (ProtoAdapter) Class.forName(str.substring(0, iIndexOf)).getField(str.substring(iIndexOf + 1)).get(null);
        } catch (Throwable th) {
            throw new IllegalArgumentException("failed to access " + str, th);
        }
    }

    public static <E extends WireEnum> RuntimeEnumAdapter<E> newEnumAdapter(Class<E> cls) {
        return new RuntimeEnumAdapter<>(cls);
    }

    public static <K, V> ProtoAdapter<Map<K, V>> newMapAdapter(ProtoAdapter<K> protoAdapter, ProtoAdapter<V> protoAdapter2) {
        return new MapProtoAdapter(protoAdapter, protoAdapter2);
    }

    public static <M extends Message<M, B>, B extends Message.Builder<M, B>> ProtoAdapter<M> newMessageAdapter(Class<M> cls) {
        return RuntimeMessageAdapter.create(cls);
    }

    public final ProtoAdapter<List<E>> asPacked() {
        ProtoAdapter<List<E>> protoAdapter = this.packedAdapter;
        if (protoAdapter != null) {
            return protoAdapter;
        }
        ProtoAdapter<List<E>> protoAdapterCreatePacked = createPacked();
        this.packedAdapter = protoAdapterCreatePacked;
        return protoAdapterCreatePacked;
    }

    public final ProtoAdapter<List<E>> asRepeated() {
        ProtoAdapter<List<E>> protoAdapter = this.repeatedAdapter;
        if (protoAdapter != null) {
            return protoAdapter;
        }
        ProtoAdapter<List<E>> protoAdapterCreateRepeated = createRepeated();
        this.repeatedAdapter = protoAdapterCreateRepeated;
        return protoAdapterCreateRepeated;
    }

    public abstract E decode(ProtoReader protoReader);

    public final E decode(BufferedSource bufferedSource) {
        Preconditions.checkNotNull(bufferedSource, "source == null");
        return decode(new ProtoReader(bufferedSource));
    }

    public final E decode(ByteString byteString) {
        Preconditions.checkNotNull(byteString, "bytes == null");
        return decode(new Buffer().write(byteString));
    }

    public final E decode(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream, "stream == null");
        return decode(Okio.buffer(Okio.source(inputStream)));
    }

    public final E decode(byte[] bArr) {
        Preconditions.checkNotNull(bArr, "bytes == null");
        return decode(new Buffer().write(bArr));
    }

    public abstract void encode(ProtoWriter protoWriter, E e);

    public final void encode(BufferedSink bufferedSink, E e) {
        Preconditions.checkNotNull(e, "value == null");
        Preconditions.checkNotNull(bufferedSink, "sink == null");
        encode(new ProtoWriter(bufferedSink), (ProtoWriter) e);
    }

    public final void encode(OutputStream outputStream, E e) {
        Preconditions.checkNotNull(e, "value == null");
        Preconditions.checkNotNull(outputStream, "stream == null");
        BufferedSink bufferedSinkBuffer = Okio.buffer(Okio.sink(outputStream));
        encode(bufferedSinkBuffer, (BufferedSink) e);
        bufferedSinkBuffer.emit();
    }

    public final byte[] encode(E e) {
        Preconditions.checkNotNull(e, "value == null");
        Buffer buffer = new Buffer();
        try {
            encode((BufferedSink) buffer, (Buffer) e);
            return buffer.readByteArray();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public void encodeWithTag(ProtoWriter protoWriter, int i, E e) {
        if (e == null) {
            return;
        }
        protoWriter.writeTag(i, this.fieldEncoding);
        if (this.fieldEncoding == FieldEncoding.LENGTH_DELIMITED) {
            protoWriter.writeVarint32(encodedSize(e));
        }
        encode(protoWriter, (ProtoWriter) e);
    }

    public abstract int encodedSize(E e);

    public int encodedSizeWithTag(int i, E e) {
        if (e == null) {
            return 0;
        }
        int iEncodedSize = encodedSize(e);
        if (this.fieldEncoding == FieldEncoding.LENGTH_DELIMITED) {
            iEncodedSize += ProtoWriter.varint32Size(iEncodedSize);
        }
        return iEncodedSize + ProtoWriter.tagSize(i);
    }

    public E redact(E e) {
        return null;
    }

    public String toString(E e) {
        return e.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    ProtoAdapter<?> withLabel(WireField.Label label) {
        return label.isRepeated() ? label.isPacked() ? asPacked() : asRepeated() : this;
    }
}
