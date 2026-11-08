package com.huawei.hms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DataBufferRef {
    private int a;
    protected final DataHolder mDataHolder;
    protected int mDataRow;

    public DataBufferRef(DataHolder dataHolder, int i) {
        Preconditions.checkNotNull(dataHolder, "dataHolder cannot be null");
        this.mDataHolder = dataHolder;
        getWindowIndex(i);
    }

    protected int getDataRow() {
        return this.mDataRow;
    }

    protected final void getWindowIndex(int i) {
        Preconditions.checkArgument(i >= 0 && i < this.mDataHolder.getCount(), "rowNum is out of index");
        this.mDataRow = i;
        this.a = this.mDataHolder.getWindowIndex(this.mDataRow);
    }

    public boolean isDataValid() {
        return !this.mDataHolder.isClosed();
    }

    public boolean hasColumn(String str) {
        return this.mDataHolder.hasColumn(str);
    }

    protected long getLong(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.a, DataHolder.TYPE_LONG);
        if (value != null) {
            return ((Long) value).longValue();
        }
        return -1L;
    }

    protected int getInteger(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.a, DataHolder.TYPE_INT);
        if (value != null) {
            return ((Integer) value).intValue();
        }
        return -1;
    }

    protected boolean getBoolean(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.a, DataHolder.TYPE_BOOLEAN);
        if (value != null) {
            return ((Boolean) value).booleanValue();
        }
        return false;
    }

    protected String getString(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.a, DataHolder.TYPE_STRING);
        return value != null ? (String) value : "";
    }

    protected float getFloat(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.a, DataHolder.TYPE_FLOAT);
        if (value != null) {
            return ((Float) value).floatValue();
        }
        return -1.0f;
    }

    protected double getDouble(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.a, DataHolder.TYPE_DOUBLE);
        if (value != null) {
            return ((Double) value).doubleValue();
        }
        return -1.0d;
    }

    protected byte[] getByteArray(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.a, DataHolder.TYPE_BYTE_ARRAY);
        if (value != null) {
            return (byte[]) value;
        }
        return null;
    }

    protected Uri parseUri(String str) {
        String str2 = (String) this.mDataHolder.getValue(str, this.mDataRow, this.a, DataHolder.TYPE_STRING);
        if (str2 == null) {
            return null;
        }
        return Uri.parse(str2);
    }

    protected void copyToBuffer(String str, CharArrayBuffer charArrayBuffer) {
        this.mDataHolder.copyToBuffer(str, this.mDataRow, this.a, charArrayBuffer);
    }

    protected boolean hasNull(String str) {
        return this.mDataHolder.hasNull(str, this.mDataRow, this.a);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mDataRow), Integer.valueOf(this.a), this.mDataHolder);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DataBufferRef)) {
            return false;
        }
        DataBufferRef dataBufferRef = (DataBufferRef) obj;
        return dataBufferRef.mDataRow == this.mDataRow && dataBufferRef.a == this.a && dataBufferRef.mDataHolder == this.mDataHolder;
    }
}
