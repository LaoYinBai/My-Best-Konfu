package com.kwad.sdk.core.report;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import com.kwad.sdk.utils.ae;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class l implements h<ReportAction> {
    private static volatile l a;
    private SQLiteDatabase b;

    private l(Context context) {
        this.b = new k(context, k.a).getWritableDatabase();
    }

    private synchronized ReportAction a(@NonNull Cursor cursor) {
        return ReportAction.a(cursor.getString(cursor.getColumnIndex("aLog")));
    }

    public static l a(Context context) {
        if (a == null) {
            synchronized (l.class) {
                if (a == null) {
                    a = new l(context);
                }
            }
        }
        return a;
    }

    private synchronized void b(ReportAction reportAction) {
        com.kwad.sdk.core.d.a.a("ReportActionDBManager", "deleteAction action = " + reportAction);
        try {
            this.b.delete("ksad_actions", "actionId=?", new String[]{reportAction.a});
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.core.report.h
    public synchronized long a() {
        long j;
        Cursor cursorRawQuery;
        j = 0;
        Cursor cursor = null;
        try {
            try {
                cursorRawQuery = this.b.rawQuery("select count(*) from ksad_actions", null);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            cursorRawQuery.moveToFirst();
            cursor = null;
            long j2 = cursorRawQuery.getLong(0);
            ae.a(cursorRawQuery);
            j = j2;
        } catch (Exception e2) {
            e = e2;
            cursor = cursorRawQuery;
            com.kwad.sdk.core.d.a.b(e);
            ae.a(cursor);
            return j;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorRawQuery;
            ae.a(cursor);
            throw th;
        }
        return j;
    }

    @Override // com.kwad.sdk.core.report.h
    public synchronized void a(ReportAction reportAction) {
        com.kwad.sdk.core.d.a.a("ReportActionDBManager", "write = " + reportAction);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("actionId", reportAction.a);
            contentValues.put("aLog", reportAction.toJson().toString());
            try {
                this.b.insert("ksad_actions", null, contentValues);
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        } catch (Exception e2) {
            com.kwad.sdk.core.d.a.a(e2);
        }
    }

    @Override // com.kwad.sdk.core.report.h
    public synchronized void a(List<ReportAction> list) {
        com.kwad.sdk.core.d.a.a("ReportActionDBManager", "delete size= " + list.size());
        try {
            this.b.beginTransaction();
            Iterator<ReportAction> it = list.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
            this.b.setTransactionSuccessful();
            this.b.endTransaction();
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.core.report.h
    public synchronized List<ReportAction> b() {
        Throwable th;
        Cursor cursorRawQuery;
        Exception e;
        try {
            try {
                cursorRawQuery = this.b.rawQuery("select  * from ksad_actions", null);
                if (cursorRawQuery != null) {
                    try {
                        ArrayList arrayList = new ArrayList();
                        while (cursorRawQuery.moveToNext()) {
                            try {
                                arrayList.add(a(cursorRawQuery));
                            } catch (Exception e2) {
                                com.kwad.sdk.core.d.a.a(e2);
                            }
                        }
                        com.kwad.sdk.core.d.a.a("ReportActionDBManager", "read size= " + arrayList.size());
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            com.kwad.sdk.core.d.a.a("ReportActionDBManager", "read action=" + ((ReportAction) it.next()));
                        }
                        ae.a(cursorRawQuery);
                        return arrayList;
                    } catch (Exception e3) {
                        e = e3;
                        com.kwad.sdk.core.d.a.a(e);
                        ae.a(cursorRawQuery);
                        return new ArrayList();
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                ae.a((Cursor) null);
                throw th;
            }
        } catch (Exception e4) {
            cursorRawQuery = null;
            e = e4;
        } catch (Throwable th3) {
            th = th3;
            ae.a((Cursor) null);
            throw th;
        }
        ae.a(cursorRawQuery);
        return new ArrayList();
    }
}
