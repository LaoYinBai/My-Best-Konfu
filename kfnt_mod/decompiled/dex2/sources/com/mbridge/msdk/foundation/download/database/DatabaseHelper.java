package com.mbridge.msdk.foundation.download.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import com.mbridge.msdk.foundation.download.core.GlobalComponent;
import com.mbridge.msdk.foundation.download.database.IDatabaseHelper;
import com.mbridge.msdk.foundation.download.utils.Objects;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DatabaseHelper implements IDatabaseHelper {
    private volatile SQLiteDatabase database;
    private IDatabaseOpenHelper databaseOpenHelper;
    private final Handler handler;
    Object object = new Object();
    private String tableName = GlobalComponent.getInstance().getDatabaseTableName();

    public DatabaseHelper(Context context, Handler handler, IDatabaseOpenHelper iDatabaseOpenHelper) {
        this.handler = handler;
        this.databaseOpenHelper = iDatabaseOpenHelper;
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public void find(final String str, final String str2, final IDatabaseHelper.IDatabaseListener iDatabaseListener) {
        this.handler.post(new Runnable() { // from class: com.mbridge.msdk.foundation.download.database.DatabaseHelper.1
            /* JADX WARN: Removed duplicated region for block: B:32:0x009e  */
            /* JADX WARN: Removed duplicated region for block: B:35:0x00a5  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() throws java.lang.Throwable {
                /*
                    r3 = this;
                    com.mbridge.msdk.foundation.download.database.DatabaseHelper r0 = com.mbridge.msdk.foundation.download.database.DatabaseHelper.this
                    android.database.sqlite.SQLiteDatabase r0 = com.mbridge.msdk.foundation.download.database.DatabaseHelper.access$000(r0)
                    boolean r0 = com.mbridge.msdk.foundation.download.utils.Objects.isNull(r0)
                    if (r0 == 0) goto L1b
                    com.mbridge.msdk.foundation.download.database.DatabaseHelper r0 = com.mbridge.msdk.foundation.download.database.DatabaseHelper.this
                    com.mbridge.msdk.foundation.download.database.DatabaseHelper r1 = com.mbridge.msdk.foundation.download.database.DatabaseHelper.this
                    com.mbridge.msdk.foundation.download.database.IDatabaseOpenHelper r1 = com.mbridge.msdk.foundation.download.database.DatabaseHelper.access$100(r1)
                    android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()
                    com.mbridge.msdk.foundation.download.database.DatabaseHelper.access$002(r0, r1)
                L1b:
                    r0 = 0
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    r1.<init>()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = "SELECT * FROM "
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    com.mbridge.msdk.foundation.download.database.DatabaseHelper r2 = com.mbridge.msdk.foundation.download.database.DatabaseHelper.this     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = com.mbridge.msdk.foundation.download.database.DatabaseHelper.access$200(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = " WHERE "
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = "download_id"
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = " = '"
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = r2     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = "' AND "
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = "director_path"
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = " = '"
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = r3     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r2 = "'"
                    r1.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    com.mbridge.msdk.foundation.download.database.DatabaseHelper r2 = com.mbridge.msdk.foundation.download.database.DatabaseHelper.this     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    android.database.sqlite.SQLiteDatabase r2 = com.mbridge.msdk.foundation.download.database.DatabaseHelper.access$000(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    android.database.Cursor r1 = r2.rawQuery(r1, r0)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                    if (r1 == 0) goto L7a
                    boolean r2 = r1.moveToFirst()     // Catch: java.lang.Exception -> L78 java.lang.Throwable -> L9b
                    if (r2 == 0) goto L7a
                    com.mbridge.msdk.foundation.download.database.DownloadModel r2 = com.mbridge.msdk.foundation.download.database.DownloadModel.create(r1)     // Catch: java.lang.Exception -> L78 java.lang.Throwable -> L9b
                    r0 = r2
                    goto L7a
                L78:
                    r2 = move-exception
                    goto L89
                L7a:
                    if (r1 == 0) goto L7f
                    r1.close()
                L7f:
                    com.mbridge.msdk.foundation.download.database.IDatabaseHelper$IDatabaseListener r1 = r4
                    if (r1 == 0) goto L9a
                    goto L95
                L84:
                    r2 = move-exception
                    r1 = r0
                    goto L9c
                L87:
                    r2 = move-exception
                    r1 = r0
                L89:
                    r2.printStackTrace()     // Catch: java.lang.Throwable -> L9b
                    if (r1 == 0) goto L91
                    r1.close()
                L91:
                    com.mbridge.msdk.foundation.download.database.IDatabaseHelper$IDatabaseListener r1 = r4
                    if (r1 == 0) goto L9a
                L95:
                    com.mbridge.msdk.foundation.download.database.IDatabaseHelper$IDatabaseListener r1 = r4
                    r1.onDatabase(r0)
                L9a:
                    return
                L9b:
                    r2 = move-exception
                L9c:
                    if (r1 == 0) goto La1
                    r1.close()
                La1:
                    com.mbridge.msdk.foundation.download.database.IDatabaseHelper$IDatabaseListener r1 = r4
                    if (r1 == 0) goto Laa
                    com.mbridge.msdk.foundation.download.database.IDatabaseHelper$IDatabaseListener r1 = r4
                    r1.onDatabase(r0)
                Laa:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.download.database.DatabaseHelper.AnonymousClass1.run():void");
            }
        });
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public void findByDownloadUrl(final String str, final IDatabaseHelper.IDatabaseListener iDatabaseListener) {
        this.handler.post(new Runnable() { // from class: com.mbridge.msdk.foundation.download.database.DatabaseHelper.2
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                Cursor cursorRawQuery;
                if (Objects.isNull(DatabaseHelper.this.database)) {
                    DatabaseHelper.this.database = DatabaseHelper.this.databaseOpenHelper.getWritableDatabase();
                }
                DownloadModel downloadModelCreate = null;
                try {
                    cursorRawQuery = DatabaseHelper.this.database.rawQuery("SELECT * FROM " + DatabaseHelper.this.tableName + " WHERE " + DownloadModel.DOWNLOAD_URL + " = '" + str + "'", null);
                    if (cursorRawQuery != null) {
                        try {
                            if (cursorRawQuery.moveToFirst()) {
                                downloadModelCreate = DownloadModel.create(cursorRawQuery);
                            }
                        } catch (Exception unused) {
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (iDatabaseListener == null) {
                                return;
                            }
                            iDatabaseListener.onDatabase(downloadModelCreate);
                        } catch (Throwable th) {
                            th = th;
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (iDatabaseListener != null) {
                                iDatabaseListener.onDatabase(null);
                            }
                            throw th;
                        }
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    if (iDatabaseListener == null) {
                        return;
                    }
                } catch (Exception unused2) {
                    cursorRawQuery = null;
                } catch (Throwable th2) {
                    th = th2;
                    cursorRawQuery = null;
                }
                iDatabaseListener.onDatabase(downloadModelCreate);
            }
        });
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public void insert(final DownloadModel downloadModel) {
        this.handler.postAtFrontOfQueue(new Runnable() { // from class: com.mbridge.msdk.foundation.download.database.DatabaseHelper.3
            @Override // java.lang.Runnable
            public void run() {
                SQLiteDatabase sQLiteDatabase;
                synchronized (DatabaseHelper.this.object) {
                    if (Objects.isNull(DatabaseHelper.this.database)) {
                        DatabaseHelper.this.database = DatabaseHelper.this.databaseOpenHelper.getWritableDatabase();
                    }
                    DatabaseHelper.this.database.beginTransaction();
                    try {
                        try {
                            DatabaseHelper.this.database.insert(DatabaseHelper.this.tableName, null, DownloadModel.create(downloadModel));
                            DatabaseHelper.this.database.setTransactionSuccessful();
                            sQLiteDatabase = DatabaseHelper.this.database;
                        } catch (Exception e) {
                            e.printStackTrace();
                            sQLiteDatabase = DatabaseHelper.this.database;
                        }
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th) {
                        DatabaseHelper.this.database.endTransaction();
                        throw th;
                    }
                }
            }
        });
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public void update(final DownloadModel downloadModel, final String str) {
        this.handler.post(new Runnable() { // from class: com.mbridge.msdk.foundation.download.database.DatabaseHelper.4
            @Override // java.lang.Runnable
            public void run() {
                if (Objects.isNull(DatabaseHelper.this.database)) {
                    DatabaseHelper.this.database = DatabaseHelper.this.databaseOpenHelper.getWritableDatabase();
                }
                synchronized (DatabaseHelper.this.object) {
                    try {
                        DatabaseHelper.this.database.update(DatabaseHelper.this.tableName, DownloadModel.create(downloadModel), "download_id = ? AND director_path = ?", new String[]{downloadModel.getDownloadId(), str});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public void updateProgress(final String str, final String str2, final DownloadModel downloadModel) {
        this.handler.post(new Runnable() { // from class: com.mbridge.msdk.foundation.download.database.DatabaseHelper.5
            @Override // java.lang.Runnable
            public void run() {
                if (Objects.isNull(DatabaseHelper.this.database)) {
                    DatabaseHelper.this.database = DatabaseHelper.this.databaseOpenHelper.getWritableDatabase();
                }
                synchronized (DatabaseHelper.this.object) {
                    try {
                        DatabaseHelper.this.database.update(DatabaseHelper.this.tableName, DownloadModel.create(downloadModel), "download_id = ? AND director_path = ?", new String[]{str, str2});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public void remove(final String str, final String str2) {
        this.handler.post(new Runnable() { // from class: com.mbridge.msdk.foundation.download.database.DatabaseHelper.6
            @Override // java.lang.Runnable
            public void run() {
                if (Objects.isNull(DatabaseHelper.this.database)) {
                    DatabaseHelper.this.database = DatabaseHelper.this.databaseOpenHelper.getWritableDatabase();
                }
                synchronized (DatabaseHelper.this.object) {
                    try {
                        DatabaseHelper.this.database.execSQL("DELETE FROM " + DatabaseHelper.this.tableName + " WHERE " + DownloadModel.DOWNLOAD_ID + " = '" + str + "' AND " + DownloadModel.DIRECTORY_PATH + " = '" + str2 + "'");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public void remove(final String str) {
        this.handler.post(new Runnable() { // from class: com.mbridge.msdk.foundation.download.database.DatabaseHelper.7
            @Override // java.lang.Runnable
            public void run() {
                if (Objects.isNull(DatabaseHelper.this.database)) {
                    DatabaseHelper.this.database = DatabaseHelper.this.databaseOpenHelper.getWritableDatabase();
                }
                synchronized (DatabaseHelper.this.object) {
                    try {
                        DatabaseHelper.this.database.execSQL("DELETE FROM " + DatabaseHelper.this.tableName + " WHERE " + DownloadModel.DOWNLOAD_ID + " = '" + str + "'");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public List<DownloadModel> getUnwantedModels(long j) throws Throwable {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                if (Objects.isNull(this.database)) {
                    this.database = this.databaseOpenHelper.getWritableDatabase();
                }
                long jCurrentTimeMillis = System.currentTimeMillis() - j;
                Cursor cursorRawQuery = this.database.rawQuery("SELECT * FROM " + this.tableName + " WHERE " + DownloadModel.LAST_MODIFIED_TIME + " <= " + jCurrentTimeMillis, null);
                if (cursorRawQuery != null) {
                    try {
                        if (cursorRawQuery.moveToFirst()) {
                            do {
                                arrayList.add(DownloadModel.create(cursorRawQuery));
                            } while (cursorRawQuery.moveToNext());
                        }
                    } catch (Exception e) {
                        cursor = cursorRawQuery;
                        e = e;
                        e.printStackTrace();
                        if (cursor != null) {
                            cursor.close();
                        }
                        return arrayList;
                    } catch (Throwable th) {
                        cursor = cursorRawQuery;
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (Exception e2) {
                e = e2;
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public void updateUnzipResource(final String str, String str2, long j) {
        this.handler.post(new Runnable() { // from class: com.mbridge.msdk.foundation.download.database.DatabaseHelper.8
            @Override // java.lang.Runnable
            public void run() {
                if (Objects.isNull(DatabaseHelper.this.database)) {
                    DatabaseHelper.this.database = DatabaseHelper.this.databaseOpenHelper.getWritableDatabase();
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(DownloadModel.DOWNLOAD_ID, str);
                DatabaseHelper.this.database.update(DatabaseHelper.this.tableName, contentValues, "download_id = '" + str + "'", null);
            }
        });
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public Cursor rawQuery(String str, String[] strArr) {
        try {
            if (Objects.isNull(this.database)) {
                this.database = this.databaseOpenHelper.getWritableDatabase();
            }
            this.database.beginTransaction();
            Cursor cursorRawQuery = this.database.rawQuery(str, strArr);
            this.database.setTransactionSuccessful();
            return cursorRawQuery;
        } catch (Exception unused) {
            return null;
        } finally {
            this.database.endTransaction();
        }
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public List<DownloadModel> findAll() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                if (Objects.isNull(this.database)) {
                    this.database = this.databaseOpenHelper.getWritableDatabase();
                }
                Cursor cursorRawQuery = rawQuery("SELECT * FROM " + this.tableName, null);
                if (cursorRawQuery != null) {
                    while (cursorRawQuery.moveToNext()) {
                        try {
                            arrayList.add(DownloadModel.create(cursorRawQuery));
                        } catch (Throwable unused) {
                            cursor = cursorRawQuery;
                            if (cursor != null) {
                                cursor.close();
                            }
                            return arrayList;
                        }
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return arrayList;
            } catch (Throwable unused2) {
            }
        } catch (Exception unused3) {
            return arrayList;
        }
    }

    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
    public void clear() {
        this.handler.post(new Runnable() { // from class: com.mbridge.msdk.foundation.download.database.DatabaseHelper.9
            @Override // java.lang.Runnable
            public void run() {
                SQLiteDatabase sQLiteDatabase;
                if (Objects.isNull(DatabaseHelper.this.database)) {
                    DatabaseHelper.this.database = DatabaseHelper.this.databaseOpenHelper.getWritableDatabase();
                }
                synchronized (DatabaseHelper.this.object) {
                    try {
                        try {
                            DatabaseHelper.this.database.beginTransaction();
                            DatabaseHelper.this.database.delete(DatabaseHelper.this.tableName, null, null);
                            DatabaseHelper.this.database.setTransactionSuccessful();
                            sQLiteDatabase = DatabaseHelper.this.database;
                        } catch (Exception e) {
                            e.printStackTrace();
                            sQLiteDatabase = DatabaseHelper.this.database;
                        }
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th) {
                        DatabaseHelper.this.database.endTransaction();
                        throw th;
                    }
                }
            }
        });
    }
}
