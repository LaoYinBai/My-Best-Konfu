package com.ss.android.message.log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bytedance.common.push.utility.i;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.union.game.sdk.core.base.event.INetUploadStrategy;
import com.umeng.message.MsgConstant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    static final String[] a = {"_id", "category", "tag", MsgConstant.INAPP_LABEL, "value", "ext_value", TTDelegateActivity.EXT_JSON};
    private static final Object b = new Object();
    private static a c;
    private SQLiteDatabase d;

    public static a a(Context context) {
        synchronized (b) {
            if (c == null) {
                c = new a(context.getApplicationContext());
            }
        }
        return c;
    }

    protected static void a(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.isClosed()) {
                    return;
                }
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    private a(Context context) {
        this.d = new C0033a(context).getWritableDatabase();
    }

    public synchronized long a(b bVar) {
        if (this.d != null && this.d.isOpen()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("category", bVar.a);
            contentValues.put("tag", bVar.b);
            if (!i.a(bVar.c)) {
                contentValues.put(MsgConstant.INAPP_LABEL, bVar.c);
            }
            contentValues.put("value", Long.valueOf(bVar.d));
            contentValues.put("ext_value", Long.valueOf(bVar.e));
            if (!i.a(bVar.f)) {
                contentValues.put(TTDelegateActivity.EXT_JSON, bVar.f);
            }
            return this.d.insert(INetUploadStrategy.KEY_EVENT, null, contentValues);
        }
        com.bytedance.common.push.utility.a.d("PushLog", "db not establish and open");
        return -1L;
    }

    public synchronized boolean a(long j) {
        if (this.d != null && this.d.isOpen()) {
            return this.d.delete(INetUploadStrategy.KEY_EVENT, "_id = ?", new String[]{String.valueOf(j)}) > 0;
        }
        com.bytedance.common.push.utility.a.d("PushLog", "db not establish and open");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x0107 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0109 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized org.json.JSONArray a(long r23, int r25) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.message.log.a.a(long, int):org.json.JSONArray");
    }

    /* renamed from: com.ss.android.message.log.a$a, reason: collision with other inner class name */
    private static class C0033a extends SQLiteOpenHelper {
        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }

        public C0033a(Context context) {
            super(context, "ss_push_log.db", (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
            try {
                sQLiteDatabase.execSQL("CREATE TABLE event ( _id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, tag VARCHAR, label VARCHAR, value INTEGER, ext_value INTEGER, ext_json TEXT )");
            } catch (Exception e) {
                com.bytedance.common.push.utility.a.e("PushLog", "create db exception " + e);
            }
        }
    }
}
