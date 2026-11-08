package com.huawei.hms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.common.internal.safeparcel.AbstractSafeParcelable;
import com.huawei.hms.common.internal.safeparcel.SafeParcelWriter;
import com.huawei.hms.support.log.HMSLog;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    private static final String TAG = "DataHolder";
    public static final String TYPE_BOOLEAN = "type_boolean";
    public static final String TYPE_BYTE_ARRAY = "type_byte_array";
    public static final String TYPE_DOUBLE = "type_double";
    public static final String TYPE_FLOAT = "type_float";
    public static final String TYPE_INT = "type_int";
    public static final String TYPE_LONG = "type_long";
    public static final String TYPE_STRING = "type_string";
    private String[] columns;
    private Bundle columnsBundle;
    private CursorWindow[] cursorWindows;
    private int dataCount;
    private boolean isInstance;
    private boolean mClosed;
    private Bundle metadata;
    private int[] perCursorCounts;
    private int statusCode;
    private int version;
    public static final Parcelable.Creator<DataHolder> CREATOR = new DataHolderCreator();
    private static final Builder builder = new a(new String[0], null);

    public static class Builder {
        private String[] a;
        private final ArrayList<HashMap<String, Object>> b;
        private final String c;
        private final HashMap<Object, Integer> d;

        private Builder(String[] strArr, String str) {
            Preconditions.checkNotNull(strArr, "builderColumnsP cannot be null");
            this.a = strArr;
            this.b = new ArrayList<>();
            this.c = str;
            this.d = new HashMap<>();
        }

        Builder(String[] strArr, String str, a aVar) {
            this(strArr, null);
        }

        public Builder setDataForContentValuesHashMap(HashMap<String, Object> map) {
            boolean z;
            Object obj;
            Preconditions.checkNotNull(map, "contentValuesHashMap cannot be null");
            int iIntValue = 0;
            if (this.c == null || (obj = map.get(this.c)) == null) {
                z = false;
            } else {
                Integer num = this.d.get(obj);
                if (num != null) {
                    iIntValue = num.intValue();
                    z = true;
                } else {
                    this.d.put(obj, Integer.valueOf(this.b.size()));
                    z = false;
                }
            }
            if (z) {
                this.b.remove(iIntValue);
                this.b.add(iIntValue, map);
            } else {
                this.b.add(map);
            }
            return this;
        }

        public Builder withRow(ContentValues contentValues) {
            Preconditions.checkNotNull(contentValues, "contentValues cannot be null");
            HashMap<String, Object> map = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            return setDataForContentValuesHashMap(map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DataHolder build(int i) {
            return new DataHolder(this, i, (Bundle) null);
        }

        public DataHolder build(int i, Bundle bundle) {
            return new DataHolder(this, i, bundle, -1);
        }
    }

    public static class DataHolderException extends RuntimeException {
        public DataHolderException(String str) {
            super(str);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.isInstance = true;
        this.version = i;
        this.columns = strArr;
        this.cursorWindows = cursorWindowArr;
        this.statusCode = i2;
        this.metadata = bundle;
        collectColumsAndCount();
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        Preconditions.checkNotNull(strArr, "columnsP cannot be null");
        Preconditions.checkNotNull(strArr, "cursorWindowP cannot be null");
        this.mClosed = false;
        this.isInstance = true;
        this.version = 1;
        this.columns = strArr;
        this.cursorWindows = cursorWindowArr;
        this.statusCode = i;
        this.metadata = bundle;
        collectColumsAndCount();
    }

    private DataHolder(com.huawei.hms.common.a.a aVar, int i, Bundle bundle) {
        this(aVar.getColumnNames(), getCursorWindows(aVar), i, bundle);
    }

    public DataHolder(Cursor cursor, int i, Bundle bundle) {
        this(new com.huawei.hms.common.a.a(cursor), i, bundle);
    }

    private DataHolder(Builder builder2, int i, Bundle bundle) {
        this(builder2.a, getCursorWindows(builder2, -1), i, (Bundle) null);
    }

    private DataHolder(Builder builder2, int i, Bundle bundle, int i2) {
        this(builder2.a, getCursorWindows(builder2, -1), i, bundle);
    }

    public final void collectColumsAndCount() {
        this.columnsBundle = new Bundle();
        for (int i = 0; i < this.columns.length; i++) {
            this.columnsBundle.putInt(this.columns[i], i);
        }
        this.perCursorCounts = new int[this.cursorWindows.length];
        int startPosition = 0;
        for (int i2 = 0; i2 < this.cursorWindows.length; i2++) {
            this.perCursorCounts[i2] = startPosition;
            startPosition = this.cursorWindows[i2].getStartPosition() + this.cursorWindows[i2].getNumRows();
        }
        this.dataCount = startPosition;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.columns, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.cursorWindows, i, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.version);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        if ((i & 1) != 0) {
            close();
        }
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final Bundle getMetadata() {
        return this.metadata;
    }

    private static CursorWindow[] getCursorWindows(com.huawei.hms.common.a.a aVar) {
        int numRows;
        ArrayList arrayList = new ArrayList();
        try {
            int count = aVar.getCount();
            CursorWindow window = aVar.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                numRows = 0;
            } else {
                window.acquireReference();
                aVar.a(null);
                arrayList.add(window);
                numRows = window.getNumRows();
            }
            arrayList.addAll(iterCursorWrapper(aVar, numRows, count));
            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
        } catch (Throwable th) {
            HMSLog.e(TAG, "fail to getCursorWindows: " + th.getMessage());
            return new CursorWindow[0];
        } finally {
            aVar.close();
        }
    }

    private static ArrayList<CursorWindow> iterCursorWrapper(com.huawei.hms.common.a.a aVar, int i, int i2) {
        ArrayList<CursorWindow> arrayList = new ArrayList<>();
        while (i < i2 && aVar.moveToPosition(i)) {
            CursorWindow window = aVar.getWindow();
            if (window == null) {
                window = new CursorWindow((String) null);
                window.setStartPosition(i);
                aVar.fillWindow(i, window);
            } else {
                window.acquireReference();
                aVar.a(null);
            }
            if (window.getNumRows() == 0) {
                break;
            }
            arrayList.add(window);
            i = window.getNumRows() + window.getStartPosition();
        }
        return arrayList;
    }

    private static CursorWindow[] getCursorWindows(Builder builder2, int i) {
        if (builder2.a.length != 0) {
            if (i < 0 || i >= builder2.b.size()) {
                i = builder2.b.size();
            }
            ArrayList<CursorWindow> arrayListIterCursorWindow = iterCursorWindow(builder2, i, builder2.b.subList(0, i));
            return (CursorWindow[]) arrayListIterCursorWindow.toArray(new CursorWindow[arrayListIterCursorWindow.size()]);
        }
        return new CursorWindow[0];
    }

    private static ArrayList<CursorWindow> iterCursorWindow(Builder builder2, int i, List list) {
        ArrayList<CursorWindow> arrayList = new ArrayList<>();
        CursorWindow cursorWindow = new CursorWindow((String) null);
        cursorWindow.setNumColumns(builder2.a.length);
        arrayList.add(cursorWindow);
        CursorWindow cursorWindow2 = cursorWindow;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            try {
                if (!cursorWindow2.allocRow()) {
                    HMSLog.d(TAG, "Failed to allocate a row");
                    cursorWindow2 = new CursorWindow((String) null);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(builder2.a.length);
                    if (!cursorWindow2.allocRow()) {
                        HMSLog.e(TAG, "Failed to retry to allocate a row");
                        return arrayList;
                    }
                    arrayList.add(cursorWindow2);
                }
                HashMap map = (HashMap) list.get(i2);
                boolean zPutValue = true;
                for (int i3 = 0; i3 < builder2.a.length && (zPutValue = putValue(cursorWindow2, map.get(builder2.a[i3]), i2, i3)); i3++) {
                }
                if (!zPutValue) {
                    HMSLog.d(TAG, "fail to put data for row " + i2);
                    cursorWindow2.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow((String) null);
                    cursorWindow3.setStartPosition(i2);
                    cursorWindow3.setNumColumns(builder2.a.length);
                    arrayList.add(cursorWindow3);
                    break;
                }
                i2++;
            } catch (RuntimeException e) {
                Iterator<CursorWindow> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().close();
                }
                throw e;
            }
        }
        return arrayList;
    }

    private static boolean putValue(CursorWindow cursorWindow, Object obj, int i, int i2) throws IllegalArgumentException {
        if (obj == null) {
            return cursorWindow.putNull(i, i2);
        }
        if (obj instanceof Boolean) {
            return cursorWindow.putLong(((Boolean) obj).booleanValue() ? 1L : 0L, i, i2);
        }
        if (obj instanceof Integer) {
            return cursorWindow.putLong(((Integer) obj).intValue(), i, i2);
        }
        if (obj instanceof Long) {
            return cursorWindow.putLong(((Long) obj).longValue(), i, i2);
        }
        if (obj instanceof Float) {
            return cursorWindow.putDouble(((Float) obj).floatValue(), i, i2);
        }
        if (obj instanceof Double) {
            return cursorWindow.putDouble(((Double) obj).doubleValue(), i, i2);
        }
        if (obj instanceof String) {
            return cursorWindow.putString((String) obj, i, i2);
        }
        if (obj instanceof byte[]) {
            return cursorWindow.putBlob((byte[]) obj, i, i2);
        }
        throw new IllegalArgumentException("unsupported type for column: " + obj);
    }

    private void checkAvailable(String str, int i) {
        String str2 = "";
        if (this.columnsBundle == null || !this.columnsBundle.containsKey(str)) {
            str2 = "cannot find column: " + str;
        } else if (isClosed()) {
            str2 = "buffer has been closed";
        } else if (i < 0 || i >= this.dataCount) {
            str2 = "row is out of index:" + i;
        }
        Preconditions.checkArgument(str2.isEmpty(), str2);
    }

    public final boolean hasColumn(String str) {
        return this.columnsBundle.containsKey(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getValue(java.lang.String r4, int r5, int r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.common.data.DataHolder.getValue(java.lang.String, int, int, java.lang.String):java.lang.Object");
    }

    public final void copyToBuffer(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        checkAvailable(str, i);
        this.cursorWindows[i2].copyStringToBuffer(i, this.columnsBundle.getInt(str), charArrayBuffer);
    }

    public final boolean hasNull(String str, int i, int i2) {
        checkAvailable(str, i);
        return this.cursorWindows[i2].getType(i, this.columnsBundle.getInt(str)) == 0;
    }

    public final int getCount() {
        return this.dataCount;
    }

    public final int getWindowIndex(int i) {
        int i2 = 0;
        Preconditions.checkArgument(i >= 0 || i < this.dataCount, "rowIndex is out of index:" + i);
        while (true) {
            if (i2 >= this.perCursorCounts.length) {
                break;
            }
            if (i < this.perCursorCounts[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.perCursorCounts.length ? i2 - 1 : i2;
    }

    public final synchronized boolean isClosed() {
        return this.mClosed;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (!this.mClosed) {
            for (CursorWindow cursorWindow : this.cursorWindows) {
                cursorWindow.close();
            }
            this.mClosed = true;
        }
    }

    protected final void finalize() throws Throwable {
        if (this.isInstance && this.cursorWindows.length > 0 && !isClosed()) {
            close();
        }
        super.finalize();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Builder builder(String[] strArr) {
        return new Builder(strArr, (String) null);
    }

    public static DataHolder empty(int i) {
        return new DataHolder(builder, i, (Bundle) null);
    }
}
