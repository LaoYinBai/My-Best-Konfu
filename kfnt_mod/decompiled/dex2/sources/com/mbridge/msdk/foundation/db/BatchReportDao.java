package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mbridge.msdk.foundation.same.report.BatchReportMessage;
import com.mbridge.msdk.system.NoProGuard;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BatchReportDao extends a<BatchReportMessage> implements NoProGuard, Serializable {
    private static volatile BatchReportDao instance;

    protected BatchReportDao(h hVar) {
        super(hVar);
    }

    public static BatchReportDao getInstance(h hVar) {
        if (instance == null) {
            synchronized (BatchReportDao.class) {
                if (instance == null) {
                    instance = new BatchReportDao(hVar);
                }
            }
        }
        return instance;
    }

    public static BatchReportDao getInstance(Context context) {
        if (instance == null) {
            synchronized (BatchReportDao.class) {
                if (instance == null) {
                    instance = new BatchReportDao(i.a(context));
                }
            }
        }
        return instance;
    }

    public void addReportMessage(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase == null) {
            return;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("report_message", str);
            contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("uuid", UUID.randomUUID().toString().replace("-", ""));
            contentValues.put("report_state", (Integer) 0);
            writableDatabase.insert("batch_report", null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BatchReportMessage> getBatchReportMessages(long j) throws Throwable {
        Cursor cursorRawQuery;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase == null) {
            return null;
        }
        ArrayList<BatchReportMessage> arrayList = new ArrayList<>();
        try {
            try {
                cursorRawQuery = readableDatabase.rawQuery("SELECT * FROM batch_report WHERE time <= " + j + " AND report_state = 0", null);
                if (cursorRawQuery != null) {
                    while (cursorRawQuery.moveToNext()) {
                        try {
                            arrayList.add(new BatchReportMessage(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("uuid")), cursorRawQuery.getString(cursorRawQuery.getColumnIndex("report_message")), cursorRawQuery.getLong(cursorRawQuery.getColumnIndex("time"))));
                        } catch (Throwable th) {
                            th = th;
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            throw th;
                        }
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("report_state", (Integer) 1);
                readableDatabase.update("batch_report", contentValues, "time <= " + j, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorRawQuery = null;
        }
    }

    public void updateMessagesReportState(ArrayList<BatchReportMessage> arrayList) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (arrayList == null || arrayList.size() == 0 || writableDatabase == null) {
            return;
        }
        Iterator<BatchReportMessage> it = arrayList.iterator();
        while (it.hasNext()) {
            BatchReportMessage next = it.next();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("report_state", (Integer) 0);
                writableDatabase.update("batch_report", contentValues, "uuid = '" + next.getUuid() + "'", null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteBatchReportMessagesByTimestamp(long j) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase == null) {
            return;
        }
        try {
            readableDatabase.rawQuery("delete from batch_report where time <= " + j, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
