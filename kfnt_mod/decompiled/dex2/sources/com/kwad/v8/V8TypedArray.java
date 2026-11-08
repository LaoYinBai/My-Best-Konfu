package com.kwad.v8;

import com.liulishuo.filedownloader.model.FileDownloadStatus;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class V8TypedArray extends V8Array {

    private static class V8ArrayData {
        private V8ArrayBuffer buffer;
        private int offset;
        private int size;
        private int type;

        public V8ArrayData(V8ArrayBuffer v8ArrayBuffer, int i, int i2, int i3) {
            this.buffer = v8ArrayBuffer;
            this.offset = i;
            this.size = i2;
            this.type = i3;
        }
    }

    private V8TypedArray(V8 v8) {
        super(v8);
    }

    public V8TypedArray(V8 v8, V8ArrayBuffer v8ArrayBuffer, int i, int i2, int i3) {
        super(v8, new V8ArrayData(v8ArrayBuffer, i2, i3, i));
    }

    private void checkArrayProperties(V8ArrayData v8ArrayData) {
        checkOffset(v8ArrayData);
        checkSize(v8ArrayData);
    }

    private void checkOffset(V8ArrayData v8ArrayData) {
        if (v8ArrayData.offset % getStructureSize(v8ArrayData.type) == 0) {
            return;
        }
        throw new IllegalStateException("RangeError: Start offset of Int32Array must be a multiple of " + getStructureSize(v8ArrayData.type));
    }

    private void checkSize(V8ArrayData v8ArrayData) {
        if (v8ArrayData.size < 0) {
            throw new IllegalStateException("RangeError: Invalid typed array length");
        }
        if ((v8ArrayData.size * getStructureSize(v8ArrayData.type)) + v8ArrayData.offset > v8ArrayData.buffer.limit()) {
            throw new IllegalStateException("RangeError: Invalid typed array length");
        }
    }

    private long createTypedArray(long j, V8ArrayData v8ArrayData) {
        int i = v8ArrayData.type;
        if (i == 9) {
            return this.v8.initNewV8Int8Array(j, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
        }
        switch (i) {
            case 1:
                return this.v8.initNewV8Int32Array(j, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
            case 2:
                return this.v8.initNewV8Float64Array(j, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
            default:
                switch (i) {
                    case 11:
                        return this.v8.initNewV8UInt8Array(j, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                    case 12:
                        return this.v8.initNewV8UInt8ClampedArray(j, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                    case 13:
                        return this.v8.initNewV8Int16Array(j, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                    case 14:
                        return this.v8.initNewV8UInt16Array(j, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                    case 15:
                        return this.v8.initNewV8UInt32Array(j, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                    case 16:
                        return this.v8.initNewV8Float32Array(j, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                    default:
                        throw new IllegalArgumentException("Cannot create a typed array of type " + V8Value.getStringRepresentation(v8ArrayData.type));
                }
        }
    }

    public static int getStructureSize(int i) {
        if (i == 9) {
            return 1;
        }
        switch (i) {
            case 1:
                return 4;
            case 2:
                return 8;
            default:
                switch (i) {
                    case 11:
                    case 12:
                        return 1;
                    case 13:
                    case 14:
                        return 2;
                    case 15:
                    case 16:
                        return 4;
                    default:
                        throw new IllegalArgumentException("Cannot create a typed array of type " + V8Value.getStringRepresentation(i));
                }
        }
    }

    @Override // com.kwad.v8.V8Array, com.kwad.v8.V8Object, com.kwad.v8.V8Value
    protected V8Value createTwin() {
        this.v8.checkThread();
        checkReleased();
        return new V8TypedArray(this.v8);
    }

    @Override // com.kwad.v8.V8Array
    public Object get(int i) {
        this.v8.checkThread();
        checkReleased();
        int type = getType();
        if (type == 9) {
            return Byte.valueOf(((Number) super.get(i)).byteValue());
        }
        switch (type) {
            case 1:
                return super.get(i);
            case 2:
                return super.get(i);
            default:
                switch (type) {
                    case 11:
                        return Short.valueOf((short) (((Number) super.get(i)).shortValue() & 255));
                    case 12:
                        return Short.valueOf((short) (((Number) super.get(i)).byteValue() & FileDownloadStatus.error));
                    case 13:
                        return Short.valueOf(((Number) super.get(i)).shortValue());
                    case 14:
                        return Integer.valueOf(((Integer) super.get(i)).intValue() & 65535);
                    case 15:
                        return Long.valueOf((-1) & ((Number) super.get(i)).longValue());
                    case 16:
                        return Float.valueOf(((Number) super.get(i)).floatValue());
                    default:
                        return null;
                }
        }
    }

    public V8ArrayBuffer getBuffer() {
        return (V8ArrayBuffer) get("buffer");
    }

    @Override // com.kwad.v8.V8Array, com.kwad.v8.V8Value
    protected void initialize(long j, Object obj) {
        this.v8.checkThread();
        if (obj == null) {
            super.initialize(j, obj);
            return;
        }
        V8ArrayData v8ArrayData = (V8ArrayData) obj;
        checkArrayProperties(v8ArrayData);
        long jCreateTypedArray = createTypedArray(j, v8ArrayData);
        this.released = false;
        addObjectReference(jCreateTypedArray);
    }
}
