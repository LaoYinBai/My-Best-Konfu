package com.kwai.filedownloader.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.kwai.filedownloader.a.a;
import com.kwai.filedownloader.e.f;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements com.kwai.filedownloader.a.a {
    private static boolean b = false;
    private SQLiteDatabase a;

    public class a implements a.InterfaceC0090a {
        private final SparseArray<com.kwai.filedownloader.c.c> b;
        private b c;
        private final SparseArray<com.kwai.filedownloader.c.c> d;
        private final SparseArray<List<com.kwai.filedownloader.c.a>> e;

        a(d dVar) {
            this(null, null);
        }

        a(SparseArray<com.kwai.filedownloader.c.c> sparseArray, SparseArray<List<com.kwai.filedownloader.c.a>> sparseArray2) {
            this.b = new SparseArray<>();
            this.d = sparseArray;
            this.e = sparseArray2;
        }

        @Override // com.kwai.filedownloader.a.a.InterfaceC0090a
        public void a() throws SQLException {
            if (this.c != null) {
                this.c.b();
            }
            if (d.this.a == null) {
                return;
            }
            int size = this.b.size();
            try {
                try {
                    if (size < 0) {
                        return;
                    }
                    try {
                        d.this.a.beginTransaction();
                        for (int i = 0; i < size; i++) {
                            int iKeyAt = this.b.keyAt(i);
                            com.kwai.filedownloader.c.c cVar = this.b.get(iKeyAt);
                            d.this.a.delete("ksad_file_download", "_id = ?", new String[]{String.valueOf(iKeyAt)});
                            d.this.a.insert("ksad_file_download", null, cVar.p());
                            if (cVar.n() > 1) {
                                List<com.kwai.filedownloader.c.a> listC = d.this.c(iKeyAt);
                                if (listC.size() > 0) {
                                    d.this.a.delete("ksad_file_download_connection", "id = ?", new String[]{String.valueOf(iKeyAt)});
                                    for (com.kwai.filedownloader.c.a aVar : listC) {
                                        aVar.a(cVar.a());
                                        d.this.a.insert("ksad_file_download_connection", null, aVar.f());
                                    }
                                }
                            }
                        }
                        if (this.d != null && this.e != null) {
                            synchronized (this.d) {
                                int size2 = this.d.size();
                                for (int i2 = 0; i2 < size2; i2++) {
                                    int iA = this.d.valueAt(i2).a();
                                    List<com.kwai.filedownloader.c.a> listC2 = d.this.c(iA);
                                    if (listC2 != null && listC2.size() > 0) {
                                        synchronized (this.e) {
                                            this.e.put(iA, listC2);
                                        }
                                    }
                                }
                            }
                        }
                        d.this.a.setTransactionSuccessful();
                        if (d.this.a != null) {
                            d.this.a.endTransaction();
                        }
                    } catch (SQLiteException e) {
                        d.this.a(e);
                        if (d.this.a != null) {
                            d.this.a.endTransaction();
                        }
                    } catch (Exception e2) {
                        d.b(e2);
                        if (d.this.a != null) {
                            d.this.a.endTransaction();
                        }
                    }
                } catch (Exception e3) {
                    d.b(e3);
                }
            } catch (Throwable th) {
                if (d.this.a != null) {
                    try {
                        d.this.a.endTransaction();
                    } catch (Exception e4) {
                        d.b(e4);
                    }
                }
                throw th;
            }
        }

        @Override // com.kwai.filedownloader.a.a.InterfaceC0090a
        public void a(int i, com.kwai.filedownloader.c.c cVar) {
            this.b.put(i, cVar);
        }

        @Override // com.kwai.filedownloader.a.a.InterfaceC0090a
        public void a(com.kwai.filedownloader.c.c cVar) {
        }

        @Override // com.kwai.filedownloader.a.a.InterfaceC0090a
        public void b(com.kwai.filedownloader.c.c cVar) {
            if (this.d != null) {
                synchronized (this.d) {
                    this.d.put(cVar.a(), cVar);
                }
            }
        }

        @Override // java.lang.Iterable
        public Iterator<com.kwai.filedownloader.c.c> iterator() {
            b bVar = d.this.new b();
            this.c = bVar;
            return bVar;
        }
    }

    class b implements Iterator<com.kwai.filedownloader.c.c> {
        private Cursor b;
        private final List<Integer> c = new ArrayList();
        private int d;

        b() throws SQLException {
            if (d.this.a != null) {
                try {
                    this.b = d.this.a.rawQuery("SELECT * FROM ksad_file_download", null);
                } catch (SQLiteException e) {
                    d.this.a(e);
                } catch (Exception e2) {
                    d.b(e2);
                }
            }
        }

        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public com.kwai.filedownloader.c.c next() {
            com.kwai.filedownloader.c.c cVarB = d.b(this.b);
            this.d = cVarB.a();
            return cVarB;
        }

        void b() throws SQLException {
            if (d.this.a == null || this.b == null) {
                return;
            }
            this.b.close();
            if (this.c.isEmpty()) {
                return;
            }
            String strJoin = TextUtils.join(", ", this.c);
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "delete %s", strJoin);
            }
            try {
                d.this.a.execSQL(f.a("DELETE FROM %s WHERE %s IN (%s);", "ksad_file_download", FileDownloadModel.ID, strJoin));
                d.this.a.execSQL(f.a("DELETE FROM %s WHERE %s IN (%s);", "ksad_file_download_connection", "id", strJoin));
            } catch (SQLiteException e) {
                d.this.a(e);
            } catch (Exception e2) {
                d.b(e2);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.b != null) {
                return this.b.moveToNext();
            }
            return false;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.c.add(Integer.valueOf(this.d));
        }
    }

    public d() throws SQLException {
        try {
            this.a = new e(com.kwai.filedownloader.e.c.a()).getWritableDatabase();
        } catch (SQLiteException e) {
            a(e);
        }
    }

    private void a(int i, ContentValues contentValues) throws SQLException {
        if (this.a != null) {
            try {
                this.a.update("ksad_file_download", contentValues, "_id = ? ", new String[]{String.valueOf(i)});
            } catch (SQLiteException e) {
                a(i, e);
            } catch (Exception e2) {
                b(e2);
            }
        }
    }

    private void a(int i, @Nullable SQLiteException sQLiteException) throws SQLException {
        if (!(sQLiteException instanceof SQLiteFullException)) {
            b(sQLiteException);
            return;
        }
        if (i != -1) {
            e(i);
            d(i);
        }
        a(sQLiteException, !b);
        b = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SQLiteException sQLiteException) throws SQLException {
        a(-1, sQLiteException);
    }

    private static void a(Throwable th, boolean z) {
        if (th != null) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static com.kwai.filedownloader.c.c b(Cursor cursor) {
        com.kwai.filedownloader.c.c cVar = new com.kwai.filedownloader.c.c();
        if (cursor == null) {
            return cVar;
        }
        cVar.a(cursor.getInt(cursor.getColumnIndex(FileDownloadModel.ID)));
        cVar.a(cursor.getString(cursor.getColumnIndex("url")));
        cVar.a(cursor.getString(cursor.getColumnIndex(FileDownloadModel.PATH)), cursor.getShort(cursor.getColumnIndex(FileDownloadModel.PATH_AS_DIRECTORY)) == 1);
        cVar.a((byte) cursor.getShort(cursor.getColumnIndex("status")));
        cVar.a(cursor.getLong(cursor.getColumnIndex(FileDownloadModel.SOFAR)));
        cVar.c(cursor.getLong(cursor.getColumnIndex(FileDownloadModel.TOTAL)));
        cVar.c(cursor.getString(cursor.getColumnIndex(FileDownloadModel.ERR_MSG)));
        cVar.b(cursor.getString(cursor.getColumnIndex("etag")));
        cVar.d(cursor.getString(cursor.getColumnIndex(FileDownloadModel.FILENAME)));
        cVar.b(cursor.getInt(cursor.getColumnIndex(FileDownloadModel.CONNECTION_COUNT)));
        return cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Throwable th) {
        a(th, true);
    }

    public a.InterfaceC0090a a(SparseArray<com.kwai.filedownloader.c.c> sparseArray, SparseArray<List<com.kwai.filedownloader.c.a>> sparseArray2) {
        return new a(sparseArray, sparseArray2);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a() throws SQLException {
        if (this.a != null) {
            try {
                this.a.delete("ksad_file_download", null, null);
            } catch (SQLiteException e) {
                a(e);
            }
            try {
                this.a.delete("ksad_file_download_connection", null, null);
            } catch (SQLiteException e2) {
                a(e2);
            }
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i) {
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, int i2) throws SQLException {
        if (this.a == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.CONNECTION_COUNT, Integer.valueOf(i2));
        try {
            this.a.update("ksad_file_download", contentValues, "_id = ? ", new String[]{Integer.toString(i)});
        } catch (SQLiteException e) {
            a(i, e);
        } catch (Exception e2) {
            b(e2);
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, int i2, long j) throws SQLException {
        if (this.a == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConnectionModel.CURRENT_OFFSET, Long.valueOf(j));
        try {
            this.a.update("ksad_file_download_connection", contentValues, "id = ? AND connectionIndex = ?", new String[]{Integer.toString(i), Integer.toString(i2)});
        } catch (SQLiteException e) {
            a(i, e);
        } catch (Exception e2) {
            b(e2);
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, long j) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Byte) (byte) 3);
        contentValues.put(FileDownloadModel.SOFAR, Long.valueOf(j));
        a(i, contentValues);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, long j, String str, String str2) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Byte) (byte) 2);
        contentValues.put(FileDownloadModel.TOTAL, Long.valueOf(j));
        contentValues.put("etag", str);
        contentValues.put(FileDownloadModel.FILENAME, str2);
        a(i, contentValues);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, String str, long j, long j2, int i2) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.SOFAR, Long.valueOf(j));
        contentValues.put(FileDownloadModel.TOTAL, Long.valueOf(j2));
        contentValues.put("etag", str);
        contentValues.put(FileDownloadModel.CONNECTION_COUNT, Integer.valueOf(i2));
        a(i, contentValues);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, Throwable th) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.ERR_MSG, th.toString());
        contentValues.put("status", (Byte) (byte) 5);
        a(i, contentValues);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(int i, Throwable th, long j) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.ERR_MSG, th.toString());
        contentValues.put("status", (Byte) (byte) -1);
        contentValues.put(FileDownloadModel.SOFAR, Long.valueOf(j));
        a(i, contentValues);
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(com.kwai.filedownloader.c.a aVar) throws SQLException {
        if (aVar == null || this.a == null) {
            return;
        }
        try {
            this.a.insert("ksad_file_download_connection", null, aVar.f());
        } catch (SQLiteException e) {
            a(aVar.a(), e);
        } catch (Exception e2) {
            b(e2);
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public void a(com.kwai.filedownloader.c.c cVar) throws SQLException {
        if (this.a == null) {
            return;
        }
        if (cVar == null) {
            com.kwai.filedownloader.e.d.d(this, "update but model == null!", new Object[0]);
            return;
        }
        if (b(cVar.a()) == null) {
            b(cVar);
            return;
        }
        try {
            this.a.update("ksad_file_download", cVar.p(), "_id = ? ", new String[]{String.valueOf(cVar.a())});
        } catch (SQLiteException e) {
            cVar.c(e.toString());
            cVar.a((byte) -1);
            a(cVar.a(), e);
        } catch (Exception e2) {
            b(e2);
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public a.InterfaceC0090a b() {
        return new a(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004f A[PHI: r0
  0x004f: PHI (r0v5 android.database.Cursor) = (r0v3 android.database.Cursor), (r0v4 android.database.Cursor), (r0v7 android.database.Cursor) binds: [B:24:0x0047, B:28:0x004d, B:12:0x0037] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.Cursor] */
    @Override // com.kwai.filedownloader.a.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.kwai.filedownloader.c.c b(int r8) throws java.lang.Throwable {
        /*
            r7 = this;
            android.database.sqlite.SQLiteDatabase r0 = r7.a
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            android.database.sqlite.SQLiteDatabase r0 = r7.a     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40 android.database.sqlite.SQLiteException -> L48
            java.lang.String r2 = "SELECT * FROM %s WHERE %s = ?"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40 android.database.sqlite.SQLiteException -> L48
            java.lang.String r4 = "ksad_file_download"
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40 android.database.sqlite.SQLiteException -> L48
            java.lang.String r4 = "_id"
            r6 = 1
            r3[r6] = r4     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40 android.database.sqlite.SQLiteException -> L48
            java.lang.String r2 = com.kwai.filedownloader.e.f.a(r2, r3)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40 android.database.sqlite.SQLiteException -> L48
            java.lang.String[] r3 = new java.lang.String[r6]     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40 android.database.sqlite.SQLiteException -> L48
            java.lang.String r4 = java.lang.Integer.toString(r8)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40 android.database.sqlite.SQLiteException -> L48
            r3[r5] = r4     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40 android.database.sqlite.SQLiteException -> L48
            android.database.Cursor r0 = r0.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40 android.database.sqlite.SQLiteException -> L48
            boolean r2 = r0.moveToNext()     // Catch: java.lang.Exception -> L3a android.database.sqlite.SQLiteException -> L3c java.lang.Throwable -> L53
            if (r2 == 0) goto L37
            com.kwai.filedownloader.c.c r2 = b(r0)     // Catch: java.lang.Exception -> L3a android.database.sqlite.SQLiteException -> L3c java.lang.Throwable -> L53
            if (r0 == 0) goto L36
            r0.close()
        L36:
            return r2
        L37:
            if (r0 == 0) goto L52
            goto L4f
        L3a:
            r8 = move-exception
            goto L42
        L3c:
            r2 = move-exception
            goto L4a
        L3e:
            r8 = move-exception
            goto L55
        L40:
            r8 = move-exception
            r0 = r1
        L42:
            b(r8)     // Catch: java.lang.Throwable -> L53
            if (r0 == 0) goto L52
            goto L4f
        L48:
            r2 = move-exception
            r0 = r1
        L4a:
            r7.a(r8, r2)     // Catch: java.lang.Throwable -> L53
            if (r0 == 0) goto L52
        L4f:
            r0.close()
        L52:
            return r1
        L53:
            r8 = move-exception
            r1 = r0
        L55:
            if (r1 == 0) goto L5a
            r1.close()
        L5a:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.filedownloader.a.d.b(int):com.kwai.filedownloader.c.c");
    }

    @Override // com.kwai.filedownloader.a.a
    public void b(int i, long j) {
        e(i);
    }

    public void b(com.kwai.filedownloader.c.c cVar) throws SQLException {
        if (this.a != null) {
            try {
                this.a.insert("ksad_file_download", null, cVar.p());
            } catch (SQLiteException e) {
                cVar.c(e.toString());
                cVar.a((byte) -1);
                a(cVar.a(), e);
            } catch (Exception e2) {
                b(e2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x008e, code lost:
    
        if (r2 == null) goto L28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.sqlite.SQLiteException] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [android.database.sqlite.SQLiteException] */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.kwai.filedownloader.a.d] */
    @Override // com.kwai.filedownloader.a.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.kwai.filedownloader.c.a> c(int r10) throws java.lang.Throwable {
        /*
            r9 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r9.a
            if (r1 != 0) goto La
            return r0
        La:
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r9.a     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d android.database.sqlite.SQLiteException -> L87
            java.lang.String r3 = "SELECT * FROM %s WHERE %s = ?"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d android.database.sqlite.SQLiteException -> L87
            java.lang.String r5 = "ksad_file_download_connection"
            r6 = 0
            r4[r6] = r5     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d android.database.sqlite.SQLiteException -> L87
            java.lang.String r5 = "id"
            r7 = 1
            r4[r7] = r5     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d android.database.sqlite.SQLiteException -> L87
            java.lang.String r3 = com.kwai.filedownloader.e.f.a(r3, r4)     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d android.database.sqlite.SQLiteException -> L87
            java.lang.String[] r4 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d android.database.sqlite.SQLiteException -> L87
            java.lang.String r5 = java.lang.Integer.toString(r10)     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d android.database.sqlite.SQLiteException -> L87
            r4[r6] = r5     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d android.database.sqlite.SQLiteException -> L87
            android.database.Cursor r2 = r2.rawQuery(r3, r4)     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d android.database.sqlite.SQLiteException -> L87
        L2c:
            boolean r1 = r2.moveToNext()     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            if (r1 == 0) goto L72
            com.kwai.filedownloader.c.a r1 = new com.kwai.filedownloader.c.a     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            r1.<init>()     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            r1.a(r10)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            java.lang.String r3 = "connectionIndex"
            int r3 = r2.getColumnIndex(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            int r3 = r2.getInt(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            r1.b(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            java.lang.String r3 = "startOffset"
            int r3 = r2.getColumnIndex(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            long r3 = r2.getLong(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            r1.a(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            java.lang.String r3 = "currentOffset"
            int r3 = r2.getColumnIndex(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            long r3 = r2.getLong(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            r1.b(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            java.lang.String r3 = "endOffset"
            int r3 = r2.getColumnIndex(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            long r3 = r2.getLong(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            r1.c(r3)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            r0.add(r1)     // Catch: java.lang.Exception -> L75 android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> L94
            goto L2c
        L72:
            if (r2 == 0) goto L93
            goto L90
        L75:
            r10 = move-exception
            r1 = r2
            goto L7e
        L78:
            r1 = move-exception
            goto L8b
        L7a:
            r10 = move-exception
            r2 = r1
            goto L95
        L7d:
            r10 = move-exception
        L7e:
            b(r10)     // Catch: java.lang.Throwable -> L7a
            if (r1 == 0) goto L93
            r1.close()
            goto L93
        L87:
            r2 = move-exception
            r8 = r2
            r2 = r1
            r1 = r8
        L8b:
            r9.a(r10, r1)     // Catch: java.lang.Throwable -> L94
            if (r2 == 0) goto L93
        L90:
            r2.close()
        L93:
            return r0
        L94:
            r10 = move-exception
        L95:
            if (r2 == 0) goto L9a
            r2.close()
        L9a:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.filedownloader.a.d.c(int):java.util.List");
    }

    @Override // com.kwai.filedownloader.a.a
    public void c(int i, long j) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Byte) (byte) -2);
        contentValues.put(FileDownloadModel.SOFAR, Long.valueOf(j));
        a(i, contentValues);
    }

    @Override // com.kwai.filedownloader.a.a
    public void d(int i) throws SQLException {
        if (this.a == null) {
            return;
        }
        try {
            this.a.execSQL("DELETE FROM ksad_file_download_connection WHERE id = " + i);
        } catch (SQLiteException | Exception e) {
            b(e);
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public boolean e(int i) {
        if (this.a == null) {
            return false;
        }
        try {
            return this.a.delete("ksad_file_download", "_id = ?", new String[]{String.valueOf(i)}) != 0;
        } catch (SQLiteException | Exception e) {
            b(e);
            return false;
        }
    }

    @Override // com.kwai.filedownloader.a.a
    public void f(int i) {
    }
}
