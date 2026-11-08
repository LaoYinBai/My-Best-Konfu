package com.ss.union.game.sdk.core.base.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a a;
    private SQLiteDatabase b;
    private c c = new c();

    public static a a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context.getApplicationContext());
                }
            }
        }
        return a;
    }

    private a(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.c);
        this.b = new C0066a(context, arrayList).getWritableDatabase();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(this.b);
        }
    }

    public c a() {
        return this.c;
    }

    /* renamed from: com.ss.union.game.sdk.core.base.a.a$a, reason: collision with other inner class name */
    private static class C0066a extends SQLiteOpenHelper {
        private static final String a = "tt_light_game.db";
        private static final int b = 1;
        private List<b> c;

        C0066a(Context context, List<b> list) {
            super(context, a, (SQLiteDatabase.CursorFactory) null, 1);
            this.c = list;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            if (this.c == null || this.c.size() <= 0) {
                return;
            }
            for (b bVar : this.c) {
                if (bVar != null) {
                    bVar.b(sQLiteDatabase);
                }
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (this.c == null || this.c.size() <= 0) {
                return;
            }
            for (b bVar : this.c) {
                if (bVar != null) {
                    bVar.a(sQLiteDatabase, i, i2);
                }
            }
        }
    }
}
