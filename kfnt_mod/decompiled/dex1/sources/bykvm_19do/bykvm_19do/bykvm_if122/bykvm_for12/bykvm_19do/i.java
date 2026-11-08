package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do;

import android.content.ContentValues;
import android.content.Context;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class i {

    /* renamed from: c, reason: collision with root package name */
    private static final Object f1947c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private c f1948a;

    /* renamed from: b, reason: collision with root package name */
    private Context f1949b;

    private class b extends AbstractCursor {
        private b(i iVar) {
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public String[] getColumnNames() {
            return new String[0];
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public int getCount() {
            return 0;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public double getDouble(int i) {
            return 0.0d;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public float getFloat(int i) {
            return 0.0f;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public int getInt(int i) {
            return 0;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public long getLong(int i) {
            return 0L;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public short getShort(int i) {
            return (short) 0;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public String getString(int i) {
            return null;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public boolean isNull(int i) {
            return true;
        }
    }

    i(Context context) {
        try {
            this.f1949b = context == null ? bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d() : context.getApplicationContext();
            if (this.f1948a == null) {
                this.f1948a = new c();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context c() {
        Context context = this.f1949b;
        return context == null ? bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d() : context;
    }

    public c a() {
        return this.f1948a;
    }

    public class c {

        /* renamed from: a, reason: collision with root package name */
        private SQLiteDatabase f1950a = null;

        public c() {
        }

        private void a() {
            try {
                synchronized (i.f1947c) {
                    if (this.f1950a == null || !this.f1950a.isOpen()) {
                        this.f1950a = new j(i.this.c(), "tt_mediation_open_sdk.db", 2).getWritableDatabase();
                        this.f1950a.setLockingEnabled(false);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                b();
            }
        }

        private boolean b() {
            SQLiteDatabase sQLiteDatabase = this.f1950a;
            return sQLiteDatabase != null && sQLiteDatabase.isOpen() && sQLiteDatabase.inTransaction();
        }

        public void a(String str) throws SQLException {
            boolean zB;
            try {
                a();
                this.f1950a.execSQL(str);
            } finally {
                if (!zB) {
                }
            }
        }

        public Cursor a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            try {
                a();
                return this.f1950a.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
            } catch (Throwable th) {
                th.printStackTrace();
                b bVar = new b();
                if (b()) {
                    throw th;
                }
                return bVar;
            }
        }

        public long a(String str, String str2, ContentValues contentValues) throws Exception {
            try {
                a();
                return this.f1950a.insert(str, str2, contentValues);
            } catch (Exception e) {
                e.printStackTrace();
                if (b()) {
                    throw e;
                }
                return -1L;
            }
        }

        public int a(String str, String str2, String[] strArr) throws Exception {
            try {
                a();
                return this.f1950a.delete(str, str2, strArr);
            } catch (Exception e) {
                e.printStackTrace();
                if (b()) {
                    throw e;
                }
                return 0;
            }
        }
    }
}
