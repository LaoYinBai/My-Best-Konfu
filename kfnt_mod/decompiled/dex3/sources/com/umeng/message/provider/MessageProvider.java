package com.umeng.message.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.l;
import com.umeng.message.proguard.m;
import com.umeng.message.provider.a;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MessageProvider extends ContentProvider {
    private static final String a = "MessageProvider";
    private static final UriMatcher b = new UriMatcher(-1);
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;
    private static final int i = 5;
    private static final int j = 6;
    private static final int k = 7;
    private static final int l = 8;
    private static final int m = 9;
    private static final int n = 10;
    private static Context o;
    private a c;
    private b d;

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        o = getContext();
        b();
        UriMatcher uriMatcher = b;
        com.umeng.message.provider.a.a(o);
        uriMatcher.addURI(com.umeng.message.provider.a.a, "MessageStores", 1);
        UriMatcher uriMatcher2 = b;
        com.umeng.message.provider.a.a(o);
        uriMatcher2.addURI(com.umeng.message.provider.a.a, "MsgTemps", 2);
        UriMatcher uriMatcher3 = b;
        com.umeng.message.provider.a.a(o);
        uriMatcher3.addURI(com.umeng.message.provider.a.a, l.e, 3);
        UriMatcher uriMatcher4 = b;
        com.umeng.message.provider.a.a(o);
        uriMatcher4.addURI(com.umeng.message.provider.a.a, "MsgAliasDeleteAll", 4);
        UriMatcher uriMatcher5 = b;
        com.umeng.message.provider.a.a(o);
        uriMatcher5.addURI(com.umeng.message.provider.a.a, "MsgLogStores", 5);
        UriMatcher uriMatcher6 = b;
        com.umeng.message.provider.a.a(o);
        uriMatcher6.addURI(com.umeng.message.provider.a.a, "MsgLogIdTypeStores", 6);
        UriMatcher uriMatcher7 = b;
        com.umeng.message.provider.a.a(o);
        uriMatcher7.addURI(com.umeng.message.provider.a.a, "MsgLogStoreForAgoos", 7);
        UriMatcher uriMatcher8 = b;
        com.umeng.message.provider.a.a(o);
        uriMatcher8.addURI(com.umeng.message.provider.a.a, "MsgLogIdTypeStoreForAgoos", 8);
        UriMatcher uriMatcher9 = b;
        com.umeng.message.provider.a.a(o);
        uriMatcher9.addURI(com.umeng.message.provider.a.a, "MsgConfigInfos", 9);
        UriMatcher uriMatcher10 = b;
        com.umeng.message.provider.a.a(o);
        uriMatcher10.addURI(com.umeng.message.provider.a.a, "InAppLogStores", 10);
        return true;
    }

    private void b() {
        try {
            synchronized (this) {
                this.c = new a(getContext());
                this.d = new b(getContext());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e8  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.database.Cursor query(android.net.Uri r10, java.lang.String[] r11, java.lang.String r12, java.lang.String[] r13, java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.provider.MessageProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        switch (b.match(uri)) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 8:
            case 9:
                return a.C0151a.k;
            case 4:
            case 6:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        switch (b.match(uri)) {
            case 1:
                long jInsertWithOnConflict = (this.c == null || this.c.getWritableDatabase() == null) ? 0L : this.c.getWritableDatabase().insertWithOnConflict(l.c, null, contentValues, 5);
                if (jInsertWithOnConflict > 0) {
                    com.umeng.message.provider.a.a(o);
                    Uri uriWithAppendedId = ContentUris.withAppendedId(com.umeng.message.provider.a.b, jInsertWithOnConflict);
                    getContext().getContentResolver().notifyChange(uriWithAppendedId, null);
                    return uriWithAppendedId;
                }
                return null;
            case 2:
                long jInsertWithOnConflict2 = (this.c == null || this.c.getWritableDatabase() == null) ? 0L : this.c.getWritableDatabase().insertWithOnConflict(l.d, null, contentValues, 5);
                if (jInsertWithOnConflict2 > 0) {
                    com.umeng.message.provider.a.a(o);
                    Uri uriWithAppendedId2 = ContentUris.withAppendedId(com.umeng.message.provider.a.b, jInsertWithOnConflict2);
                    getContext().getContentResolver().notifyChange(uriWithAppendedId2, null);
                    return uriWithAppendedId2;
                }
                return null;
            case 3:
                long jInsertWithOnConflict3 = (this.c == null || this.c.getWritableDatabase() == null) ? 0L : this.c.getWritableDatabase().insertWithOnConflict(l.e, null, contentValues, 5);
                if (jInsertWithOnConflict3 > 0) {
                    com.umeng.message.provider.a.a(o);
                    Uri uriWithAppendedId3 = ContentUris.withAppendedId(com.umeng.message.provider.a.d, jInsertWithOnConflict3);
                    getContext().getContentResolver().notifyChange(uriWithAppendedId3, null);
                    return uriWithAppendedId3;
                }
                return null;
            case 4:
            default:
                return null;
            case 5:
                long jInsertWithOnConflict4 = (this.d == null || this.d.getWritableDatabase() == null) ? 0L : this.d.getWritableDatabase().insertWithOnConflict(m.d, null, contentValues, 5);
                if (jInsertWithOnConflict4 > 0) {
                    com.umeng.message.provider.a.a(o);
                    Uri uriWithAppendedId4 = ContentUris.withAppendedId(com.umeng.message.provider.a.f, jInsertWithOnConflict4);
                    getContext().getContentResolver().notifyChange(uriWithAppendedId4, null);
                    return uriWithAppendedId4;
                }
                return null;
            case 6:
                long jInsertWithOnConflict5 = (this.d == null || this.d.getWritableDatabase() == null) ? 0L : this.d.getWritableDatabase().insertWithOnConflict(m.e, null, contentValues, 5);
                if (jInsertWithOnConflict5 > 0) {
                    com.umeng.message.provider.a.a(o);
                    return ContentUris.withAppendedId(com.umeng.message.provider.a.g, jInsertWithOnConflict5);
                }
                return null;
            case 7:
                long jInsertWithOnConflict6 = (this.d == null || this.d.getWritableDatabase() == null) ? 0L : this.d.getWritableDatabase().insertWithOnConflict(m.f, null, contentValues, 5);
                if (jInsertWithOnConflict6 > 0) {
                    com.umeng.message.provider.a.a(o);
                    return ContentUris.withAppendedId(com.umeng.message.provider.a.h, jInsertWithOnConflict6);
                }
                return null;
            case 8:
                long jInsertWithOnConflict7 = (this.d == null || this.d.getWritableDatabase() == null) ? 0L : this.d.getWritableDatabase().insertWithOnConflict(m.g, null, contentValues, 5);
                if (jInsertWithOnConflict7 > 0) {
                    com.umeng.message.provider.a.a(o);
                    return ContentUris.withAppendedId(com.umeng.message.provider.a.i, jInsertWithOnConflict7);
                }
                return null;
            case 9:
                long jInsertWithOnConflict8 = (this.d == null || this.d.getWritableDatabase() == null) ? 0L : this.d.getWritableDatabase().insertWithOnConflict(m.h, null, contentValues, 5);
                if (jInsertWithOnConflict8 > 0) {
                    com.umeng.message.provider.a.a(o);
                    return ContentUris.withAppendedId(com.umeng.message.provider.a.j, jInsertWithOnConflict8);
                }
                return null;
            case 10:
                long jInsertWithOnConflict9 = (this.d == null || this.d.getWritableDatabase() == null) ? 0L : this.d.getWritableDatabase().insertWithOnConflict(m.i, null, contentValues, 5);
                if (jInsertWithOnConflict9 > 0) {
                    com.umeng.message.provider.a.a(o);
                    return ContentUris.withAppendedId(com.umeng.message.provider.a.k, jInsertWithOnConflict9);
                }
                return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d7  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int delete(android.net.Uri r4, java.lang.String r5, java.lang.String[] r6) {
        /*
            r3 = this;
            android.content.UriMatcher r0 = com.umeng.message.provider.MessageProvider.b
            int r0 = r0.match(r4)
            r1 = 0
            switch(r0) {
                case 1: goto Ld7;
                case 2: goto Lbe;
                case 3: goto La5;
                case 4: goto L8c;
                case 5: goto L73;
                case 6: goto L5a;
                case 7: goto L40;
                case 8: goto L26;
                case 9: goto La;
                case 10: goto Lc;
                default: goto La;
            }
        La:
            goto Ld7
        Lc:
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            java.lang.String r2 = "InAppLogStore"
            int r5 = r0.delete(r2, r5, r6)
            goto Ld8
        L26:
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            java.lang.String r2 = "MsgLogIdTypeStoreForAgoo"
            int r5 = r0.delete(r2, r5, r6)
            goto Ld8
        L40:
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            java.lang.String r2 = "MsgLogStoreForAgoo"
            int r5 = r0.delete(r2, r5, r6)
            goto Ld8
        L5a:
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            java.lang.String r2 = "MsgLogIdTypeStore"
            int r5 = r0.delete(r2, r5, r6)
            goto Ld8
        L73:
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$b r0 = r3.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            java.lang.String r2 = "MsgLogStore"
            int r5 = r0.delete(r2, r5, r6)
            goto Ld8
        L8c:
            com.umeng.message.provider.MessageProvider$a r5 = r3.c
            if (r5 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$a r5 = r3.c
            android.database.sqlite.SQLiteDatabase r5 = r5.getWritableDatabase()
            if (r5 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$a r5 = r3.c
            android.database.sqlite.SQLiteDatabase r5 = r5.getWritableDatabase()
            java.lang.String r6 = "MsgAlias"
            int r5 = r5.delete(r6, r1, r1)
            goto Ld8
        La5:
            com.umeng.message.provider.MessageProvider$a r0 = r3.c
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$a r0 = r3.c
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$a r0 = r3.c
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            java.lang.String r2 = "MsgAlias"
            int r5 = r0.delete(r2, r5, r6)
            goto Ld8
        Lbe:
            com.umeng.message.provider.MessageProvider$a r0 = r3.c
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$a r0 = r3.c
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto Ld7
            com.umeng.message.provider.MessageProvider$a r0 = r3.c
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            java.lang.String r2 = "MsgTemp"
            int r5 = r0.delete(r2, r5, r6)
            goto Ld8
        Ld7:
            r5 = 0
        Ld8:
            android.content.Context r6 = r3.getContext()
            android.content.ContentResolver r6 = r6.getContentResolver()
            r6.notifyChange(r4, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.provider.MessageProvider.delete(android.net.Uri, java.lang.String, java.lang.String[]):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009f  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int update(android.net.Uri r8, android.content.ContentValues r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            r7 = this;
            android.content.UriMatcher r0 = com.umeng.message.provider.MessageProvider.b
            int r0 = r0.match(r8)
            switch(r0) {
                case 1: goto L82;
                case 2: goto L65;
                case 3: goto L49;
                default: goto L9;
            }
        L9:
            switch(r0) {
                case 9: goto L2c;
                case 10: goto Le;
                default: goto Lc;
            }
        Lc:
            goto L9f
        Le:
            com.umeng.message.provider.MessageProvider$b r0 = r7.d
            if (r0 == 0) goto L9f
            com.umeng.message.provider.MessageProvider$b r0 = r7.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto L9f
            com.umeng.message.provider.MessageProvider$b r0 = r7.d
            android.database.sqlite.SQLiteDatabase r1 = r0.getWritableDatabase()
            java.lang.String r2 = "InAppLogStore"
            r6 = 5
            r3 = r9
            r4 = r10
            r5 = r11
            int r9 = r1.updateWithOnConflict(r2, r3, r4, r5, r6)
            goto La0
        L2c:
            com.umeng.message.provider.MessageProvider$b r0 = r7.d
            if (r0 == 0) goto L9f
            com.umeng.message.provider.MessageProvider$b r0 = r7.d
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto L9f
            com.umeng.message.provider.MessageProvider$b r0 = r7.d
            android.database.sqlite.SQLiteDatabase r1 = r0.getWritableDatabase()
            java.lang.String r2 = "MsgConfigInfo"
            r6 = 5
            r3 = r9
            r4 = r10
            r5 = r11
            int r9 = r1.updateWithOnConflict(r2, r3, r4, r5, r6)
            goto La0
        L49:
            com.umeng.message.provider.MessageProvider$a r0 = r7.c
            if (r0 == 0) goto L9f
            com.umeng.message.provider.MessageProvider$a r0 = r7.c
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto L9f
            com.umeng.message.provider.MessageProvider$a r0 = r7.c
            android.database.sqlite.SQLiteDatabase r1 = r0.getWritableDatabase()
            java.lang.String r2 = "MsgAlias"
            r6 = 5
            r3 = r9
            r4 = r10
            r5 = r11
            r1.updateWithOnConflict(r2, r3, r4, r5, r6)
            goto L9f
        L65:
            com.umeng.message.provider.MessageProvider$a r0 = r7.c
            if (r0 == 0) goto L9f
            com.umeng.message.provider.MessageProvider$a r0 = r7.c
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto L9f
            com.umeng.message.provider.MessageProvider$a r0 = r7.c
            android.database.sqlite.SQLiteDatabase r1 = r0.getWritableDatabase()
            java.lang.String r2 = "MsgTemp"
            r6 = 5
            r3 = r9
            r4 = r10
            r5 = r11
            int r9 = r1.updateWithOnConflict(r2, r3, r4, r5, r6)
            goto La0
        L82:
            com.umeng.message.provider.MessageProvider$a r0 = r7.c
            if (r0 == 0) goto L9f
            com.umeng.message.provider.MessageProvider$a r0 = r7.c
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto L9f
            com.umeng.message.provider.MessageProvider$a r0 = r7.c
            android.database.sqlite.SQLiteDatabase r1 = r0.getWritableDatabase()
            java.lang.String r2 = "MessageStore"
            r6 = 5
            r3 = r9
            r4 = r10
            r5 = r11
            int r9 = r1.updateWithOnConflict(r2, r3, r4, r5, r6)
            goto La0
        L9f:
            r9 = 0
        La0:
            android.content.Context r10 = r7.getContext()
            android.content.ContentResolver r10 = r10.getContentResolver()
            r11 = 0
            r10.notifyChange(r8, r11)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.provider.MessageProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    @Override // android.content.ContentProvider
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        this.d.getWritableDatabase().beginTransaction();
        try {
            ContentProviderResult[] contentProviderResultArrApplyBatch = super.applyBatch(arrayList);
            this.d.getWritableDatabase().setTransactionSuccessful();
            return contentProviderResultArrApplyBatch;
        } finally {
            this.d.getWritableDatabase().endTransaction();
            Iterator<ContentProviderOperation> it = arrayList.iterator();
            Object uri = null;
            while (it.hasNext()) {
                ContentProviderOperation next = it.next();
                if (!next.getUri().equals(uri)) {
                    uri = next.getUri();
                    getContext().getContentResolver().notifyChange(next.getUri(), null);
                    Log.i(a, "notifychange endTransaction..uri:" + next.getUri());
                }
            }
        }
    }

    private class a extends SQLiteOpenHelper {
        public a(Context context) {
            super(context, l.b, (SQLiteDatabase.CursorFactory) null, 3);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
            Log.i(MessageProvider.a, "MessageStoreHelper-->onCreate-->start");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MessageStore(_id Integer  PRIMARY KEY  AUTOINCREMENT  , MsdId Varchar  , Json Varchar  , SdkVersion Varchar  , ArrivalTime Long  , ActionType Integer )");
            sQLiteDatabase.execSQL("create table if not exists MsgTemp(id INTEGER AUTO_INCREMENT,tempkey varchar default NULL, tempvalue varchar default NULL,PRIMARY KEY(id))");
            sQLiteDatabase.execSQL("create table if not exists MsgAlias(time long,type varchar default NULL,alias varchar default NULL,exclusive int,error int,message varchar,PRIMARY KEY(time))");
            Log.i(MessageProvider.a, "MessageStoreHelper-->onCreate-->end");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) throws SQLException {
            if (i <= 2) {
                sQLiteDatabase.execSQL("drop table MsgTemp");
            }
            onCreate(sQLiteDatabase);
            Log.i(MessageProvider.a, "MessageStoreHelper-->onUpgrade");
        }
    }

    private class b extends SQLiteOpenHelper {
        public b(Context context) {
            super(context, m.b, (SQLiteDatabase.CursorFactory) null, 7);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("create table if not exists MsgLogStore (MsgId varchar, ActionType Integer, Time long, pa varchar, PRIMARY KEY(MsgId, ActionType))");
            sQLiteDatabase.execSQL("create table if not exists MsgLogIdTypeStore (MsgId varchar, MsgType varchar, PRIMARY KEY(MsgId))");
            sQLiteDatabase.execSQL("create table if not exists MsgLogStoreForAgoo (MsgId varchar, TaskId varchar, MsgStatus varchar, Time long, PRIMARY KEY(MsgId, MsgStatus))");
            sQLiteDatabase.execSQL("create table if not exists MsgLogIdTypeStoreForAgoo (MsgId varchar, TaskId varchar, MsgStatus varchar, PRIMARY KEY(MsgId))");
            sQLiteDatabase.execSQL("create table if not exists MsgConfigInfo (SerialNo integer default 1, AppLaunchAt long default 0, UpdateResponse varchar default NULL)");
            sQLiteDatabase.execSQL("create table if not exists InAppLogStore (Time long, MsgId varchar, MsgType Integer, NumDisplay Integer, NumOpenFull Integer, NumOpenTop Integer, NumOpenBottom Integer, NumClose Integer, NumDuration Integer, NumCustom Integer, PRIMARY KEY(Time))");
            Log.i(MessageProvider.a, "MsgLogStoreHelper-->onCreate");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) throws SQLException {
            Log.i(MessageProvider.a, "oldVersion:" + i + ",newVersion:" + i2);
            if (i <= 4) {
                Log.i(MessageProvider.a, "MsgLogStoreHelper-->drop delete");
                sQLiteDatabase.execSQL("drop table MsgConfigInfo");
            }
            if (i <= 5) {
                Log.i(MessageProvider.a, "MsgLogStoreHelper-->drop MsgLogStore");
                sQLiteDatabase.execSQL("ALTER TABLE MsgLogStore ADD COLUMN pa varchar");
            }
            if (i <= 6 && a(sQLiteDatabase, m.i)) {
                Log.i(MessageProvider.a, "MsgLogStoreHelper-->alter InAppLogStore");
                sQLiteDatabase.execSQL("ALTER TABLE InAppLogStore ADD COLUMN NumCustom Integer");
            }
            onCreate(sQLiteDatabase);
            Log.i(MessageProvider.a, "MsgLogStoreHelper-->onUpgrade");
        }

        private boolean a(SQLiteDatabase sQLiteDatabase, String str) {
            boolean z = false;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type = 'table' and name = '" + str.trim() + "'", null);
                if (cursorRawQuery.moveToNext() && cursorRawQuery.getInt(0) > 0) {
                    z = true;
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (Exception unused) {
            }
            return z;
        }
    }
}
