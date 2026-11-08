package org.android.agoo.message;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.MsgDO;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class MessageService {
    public static final String MSG_ACCS_NOTIFY_CLICK = "8";
    public static final String MSG_ACCS_NOTIFY_DISMISS = "9";
    public static final String MSG_ACCS_READY_REPORT = "4";
    public static final String MSG_DB_COMPLETE = "100";
    public static final String MSG_DB_NOTIFY_CLICK = "2";
    public static final String MSG_DB_NOTIFY_DISMISS = "3";
    public static final String MSG_DB_NOTIFY_REACHED = "1";
    public static final String MSG_DB_READY_REPORT = "0";
    private static Context a;
    private static Map<String, Integer> c;
    private volatile SQLiteOpenHelper b = null;

    public void a(Context context) {
        c = new HashMap();
        a = context;
        this.b = new a(context);
    }

    private static class a extends SQLiteOpenHelper {
        public a(Context context) {
            super(context, "message_accs_db", (SQLiteDatabase.CursorFactory) null, 3);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public SQLiteDatabase getWritableDatabase() {
            if (j.a(super.getWritableDatabase().getPath(), 102400)) {
                return super.getWritableDatabase();
            }
            return null;
        }

        private String a() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("create table accs_message");
            stringBuffer.append("(");
            stringBuffer.append("id text UNIQUE not null,");
            stringBuffer.append("state text,");
            stringBuffer.append("message text,");
            stringBuffer.append("create_time date");
            stringBuffer.append(");");
            return stringBuffer.toString();
        }

        private String b() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("create table message");
            stringBuffer.append("(");
            stringBuffer.append("id text UNIQUE not null,");
            stringBuffer.append("state integer,");
            stringBuffer.append("body_code integer,");
            stringBuffer.append("report long,");
            stringBuffer.append("target_time long,");
            stringBuffer.append("interval integer,");
            stringBuffer.append("type text,");
            stringBuffer.append("message text,");
            stringBuffer.append("notify integer,");
            stringBuffer.append("create_time date");
            stringBuffer.append(");");
            return stringBuffer.toString();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.execSQL(b());
                    sQLiteDatabase.execSQL("CREATE INDEX id_index ON message(id)");
                    sQLiteDatabase.execSQL("CREATE INDEX body_code_index ON message(body_code)");
                    sQLiteDatabase.execSQL(a());
                } catch (Throwable th) {
                    ALog.e("MessageService", "messagedbhelper create", th, new Object[0]);
                }
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            try {
                if (sQLiteDatabase != null) {
                    try {
                        try {
                            sQLiteDatabase.execSQL("delete from message where create_time< date('now','-7 day') and state=1");
                            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS accs_message");
                            sQLiteDatabase.execSQL(a());
                        } catch (Throwable th) {
                            ALog.e("MessageService", "messagedbhelper create", th, new Object[0]);
                            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS accs_message");
                            sQLiteDatabase.execSQL(a());
                        }
                    } catch (Throwable th2) {
                        try {
                            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS accs_message");
                            sQLiteDatabase.execSQL(a());
                        } catch (Throwable th3) {
                            ALog.e("MessageService", "MessageService onUpgrade is error", th3, new Object[0]);
                        }
                        throw th2;
                    }
                } else {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS accs_message");
                    sQLiteDatabase.execSQL(a());
                }
            } catch (Throwable th4) {
                ALog.e("MessageService", "MessageService onUpgrade is error", th4, new Object[0]);
            }
        }
    }

    public void a(String str, String str2) throws Throwable {
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i("MessageService", "updateAccsMessage sqlite3--->[" + str + ",state=" + str2 + "]", new Object[0]);
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
                    if (writableDatabase == null) {
                        if (writableDatabase != null) {
                            writableDatabase.close();
                            return;
                        }
                        return;
                    }
                    try {
                        if (TextUtils.equals(str2, MSG_DB_NOTIFY_REACHED)) {
                            writableDatabase.execSQL("UPDATE accs_message set state = ? where id = ? and state = ?", new Object[]{str2, str, MSG_DB_READY_REPORT});
                        } else {
                            writableDatabase.execSQL("UPDATE accs_message set state = ? where id = ?", new Object[]{str2, str});
                        }
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        sQLiteDatabase = writableDatabase;
                        if (ALog.isPrintLog(ALog.Level.E)) {
                            ALog.e("MessageService", "updateAccsMessage error,e--->[" + th + "],ex=" + th.getStackTrace().toString(), new Object[0]);
                        }
                        UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.add_agoo_message", j.b(a), "updateAccsMessageFailed", th.toString());
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0101  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r11, java.lang.String r12, java.lang.String r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.android.agoo.message.MessageService.a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    private String a(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                stringBuffer.append(stackTraceElement.toString());
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

    public void a(String str, String str2, String str3, int i) throws Throwable {
        a(str, str2, str3, 1, -1L, -1, i);
    }

    private void a(String str, String str2, String str3, int i, long j, int i2, int i3) throws Throwable {
        String str4;
        StringBuilder sb;
        int iHashCode;
        ALog.d("MessageService", "add sqlite3--->[" + str + "]", new Object[0]);
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                if (TextUtils.isEmpty(str2)) {
                    str2 = "";
                    iHashCode = -1;
                } else {
                    iHashCode = str2.hashCode();
                }
                if (TextUtils.isEmpty(str3)) {
                    str3 = "";
                }
                if (!c.containsKey(str)) {
                    c.put(str, Integer.valueOf(iHashCode));
                    if (ALog.isPrintLog(ALog.Level.I)) {
                        ALog.i("MessageService", "addMessage,messageId=" + str + ",messageStores＝" + c.toString(), new Object[0]);
                    }
                }
                SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
                if (writableDatabase == null) {
                    if (writableDatabase != null) {
                        try {
                            writableDatabase.close();
                            return;
                        } catch (Throwable th) {
                            if (ALog.isPrintLog(ALog.Level.E)) {
                                ALog.e("MessageService", "addMessage,db.close(),error,e--->[" + th + "]", new Object[0]);
                            }
                            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.add_agoo_message", j.b(a), "addMessageDBcloseFailed", th.toString());
                            return;
                        }
                    }
                    return;
                }
                try {
                    writableDatabase.execSQL("INSERT INTO message VALUES(?,?,?,?,?,?,?,?,?,date('now'))", new Object[]{str, Integer.valueOf(i), Integer.valueOf(iHashCode), 0, Long.valueOf(j), Integer.valueOf(i2), str3, str2, Integer.valueOf(i3)});
                    if (writableDatabase != null) {
                        try {
                            writableDatabase.close();
                        } catch (Throwable th2) {
                            th = th2;
                            if (ALog.isPrintLog(ALog.Level.E)) {
                                str4 = "MessageService";
                                sb = new StringBuilder();
                                sb.append("addMessage,db.close(),error,e--->[");
                                sb.append(th);
                                sb.append("]");
                                ALog.e(str4, sb.toString(), new Object[0]);
                            }
                            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.add_agoo_message", j.b(a), "addMessageDBcloseFailed", th.toString());
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    sQLiteDatabase = writableDatabase;
                    if (ALog.isPrintLog(ALog.Level.E)) {
                        ALog.e("MessageService", "addMessage error,e--->[" + th + "]", new Object[0]);
                    }
                    UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.add_agoo_message", j.b(a), "addMessageFailed", th.toString());
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.close();
                        } catch (Throwable th4) {
                            th = th4;
                            if (ALog.isPrintLog(ALog.Level.E)) {
                                str4 = "MessageService";
                                sb = new StringBuilder();
                                sb.append("addMessage,db.close(),error,e--->[");
                                sb.append(th);
                                sb.append("]");
                                ALog.e(str4, sb.toString(), new Object[0]);
                            }
                            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.add_agoo_message", j.b(a), "addMessageDBcloseFailed", th.toString());
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
            }
        } catch (Throwable th6) {
            th = th6;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x003b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a() throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 0
            android.database.sqlite.SQLiteOpenHelper r1 = r6.b     // Catch: java.lang.Throwable -> L21 java.lang.Throwable -> L26
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> L21 java.lang.Throwable -> L26
            if (r1 != 0) goto Lf
            if (r1 == 0) goto Le
            r1.close()     // Catch: java.lang.Throwable -> Le
        Le:
            return
        Lf:
            java.lang.String r0 = "delete from message where create_time< date('now','-7 day') and state=1"
            r1.execSQL(r0)     // Catch: java.lang.Throwable -> L1f java.lang.Throwable -> L38
            java.lang.String r0 = "delete from accs_message where create_time< date('now','-1 day') "
            r1.execSQL(r0)     // Catch: java.lang.Throwable -> L1f java.lang.Throwable -> L38
            if (r1 == 0) goto L37
        L1b:
            r1.close()     // Catch: java.lang.Throwable -> L37
            goto L37
        L1f:
            r0 = move-exception
            goto L2a
        L21:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L39
        L26:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L2a:
            java.lang.String r2 = "MessageService"
            java.lang.String r3 = "deleteCacheMessage sql Throwable"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L38
            com.taobao.accs.utl.ALog.e(r2, r3, r0, r4)     // Catch: java.lang.Throwable -> L38
            if (r1 == 0) goto L37
            goto L1b
        L37:
            return
        L38:
            r0 = move-exception
        L39:
            if (r1 == 0) goto L3e
            r1.close()     // Catch: java.lang.Throwable -> L3e
        L3e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.android.agoo.message.MessageService.a():void");
    }

    public ArrayList<MsgDO> b() throws Throwable {
        Cursor cursor;
        Throwable th;
        SQLiteDatabase readableDatabase;
        ArrayList<MsgDO> arrayList;
        Cursor cursorRawQuery;
        Throwable th2;
        String str;
        StringBuilder sb;
        try {
            try {
                readableDatabase = this.b.getReadableDatabase();
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            cursor = null;
            th = th4;
            readableDatabase = null;
        }
        if (readableDatabase == null) {
            if (readableDatabase != null) {
                try {
                    readableDatabase.close();
                } catch (Throwable th5) {
                    if (ALog.isPrintLog(ALog.Level.E)) {
                        ALog.e("MessageService", "getUnReportMsg close cursor or db, e: " + th5, new Object[0]);
                    }
                }
            }
            return null;
        }
        try {
            try {
                arrayList = new ArrayList<>();
                try {
                    cursorRawQuery = readableDatabase.rawQuery("select * from accs_message where state = ? or state = ? or state = ?", new String[]{MSG_DB_READY_REPORT, MSG_DB_NOTIFY_CLICK, MSG_DB_NOTIFY_DISMISS});
                    if (cursorRawQuery != null) {
                        try {
                            int columnIndex = cursorRawQuery.getColumnIndex(AgooConstants.MESSAGE_ID);
                            int columnIndex2 = cursorRawQuery.getColumnIndex("state");
                            int columnIndex3 = cursorRawQuery.getColumnIndex("message");
                            int columnIndex4 = cursorRawQuery.getColumnIndex("create_time");
                            while (cursorRawQuery.moveToNext() && !TextUtils.isEmpty(cursorRawQuery.getString(columnIndex3))) {
                                String string = cursorRawQuery.getString(columnIndex2);
                                String string2 = cursorRawQuery.getString(columnIndex3);
                                if (ALog.isPrintLog(ALog.Level.I)) {
                                    ALog.i("MessageService", "state: " + string + " ,cursor.message:" + string2 + " ,cursor.id:" + cursorRawQuery.getString(columnIndex) + " ,cursor.time:" + cursorRawQuery.getString(columnIndex4), new Object[0]);
                                }
                                String str2 = TextUtils.equals(MSG_DB_READY_REPORT, string) ? MSG_ACCS_READY_REPORT : TextUtils.equals(MSG_DB_NOTIFY_CLICK, string) ? MSG_ACCS_NOTIFY_CLICK : TextUtils.equals(MSG_DB_NOTIFY_DISMISS, string) ? MSG_ACCS_NOTIFY_DISMISS : null;
                                new MsgDO();
                                if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(str2)) {
                                    MsgDO msgDOB = b(string2, str2);
                                    msgDOB.messageSource = "cache";
                                    arrayList.add(msgDOB);
                                }
                            }
                        } catch (Throwable th6) {
                            th2 = th6;
                            if (ALog.isPrintLog(ALog.Level.E)) {
                                ALog.e("MessageService", "getUnReportMsg, e: " + th2, new Object[0]);
                            }
                            if (cursorRawQuery != null) {
                                try {
                                    cursorRawQuery.close();
                                } catch (Throwable th7) {
                                    th = th7;
                                    if (ALog.isPrintLog(ALog.Level.E)) {
                                        str = "MessageService";
                                        sb = new StringBuilder();
                                        sb.append("getUnReportMsg close cursor or db, e: ");
                                        sb.append(th);
                                        ALog.e(str, sb.toString(), new Object[0]);
                                    }
                                }
                            }
                            if (readableDatabase != null) {
                                readableDatabase.close();
                            }
                            return arrayList;
                        }
                    }
                    if (cursorRawQuery != null) {
                        try {
                            cursorRawQuery.close();
                        } catch (Throwable th8) {
                            th = th8;
                            if (ALog.isPrintLog(ALog.Level.E)) {
                                str = "MessageService";
                                sb = new StringBuilder();
                                sb.append("getUnReportMsg close cursor or db, e: ");
                                sb.append(th);
                                ALog.e(str, sb.toString(), new Object[0]);
                            }
                        }
                    }
                    if (readableDatabase != null) {
                        readableDatabase.close();
                    }
                } catch (Throwable th9) {
                    cursorRawQuery = null;
                    th2 = th9;
                }
            } catch (Throwable th10) {
                cursor = null;
                th = th10;
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th11) {
                        if (ALog.isPrintLog(ALog.Level.E)) {
                            ALog.e("MessageService", "getUnReportMsg close cursor or db, e: " + th11, new Object[0]);
                        }
                        throw th;
                    }
                }
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
                throw th;
            }
        } catch (Throwable th12) {
            cursorRawQuery = null;
            th2 = th12;
            arrayList = null;
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0064 A[Catch: Throwable -> 0x0083, PHI: r2 r4
  0x0064: PHI (r2v4 boolean) = (r2v2 boolean), (r2v10 boolean) binds: [B:43:0x0080, B:27:0x0062] A[DONT_GENERATE, DONT_INLINE]
  0x0064: PHI (r4v4 android.database.sqlite.SQLiteDatabase) = (r4v3 android.database.sqlite.SQLiteDatabase), (r4v7 android.database.sqlite.SQLiteDatabase) binds: [B:43:0x0080, B:27:0x0062] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #5 {Throwable -> 0x0083, blocks: (B:26:0x005f, B:28:0x0064, B:42:0x007d), top: B:54:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.lang.String r9) throws java.lang.Throwable {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            java.util.Map<java.lang.String, java.lang.Integer> r2 = org.android.agoo.message.MessageService.c     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
            boolean r2 = r2.containsKey(r9)     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
            r3 = 1
            if (r2 == 0) goto L2d
            com.taobao.accs.utl.ALog$Level r2 = com.taobao.accs.utl.ALog.Level.E     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
            boolean r2 = com.taobao.accs.utl.ALog.isPrintLog(r2)     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
            if (r2 == 0) goto L2b
            java.lang.String r2 = "MessageService"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
            r4.<init>()     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
            java.lang.String r5 = "hasMessageDuplicate,msgid="
            r4.append(r5)     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
            r4.append(r9)     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
            com.taobao.accs.utl.ALog.e(r2, r4, r5)     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L79
        L2b:
            r2 = 1
            goto L2e
        L2d:
            r2 = 0
        L2e:
            android.database.sqlite.SQLiteOpenHelper r4 = r8.b     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L6c
            android.database.sqlite.SQLiteDatabase r4 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L6c
            if (r4 != 0) goto L3c
            if (r4 == 0) goto L3b
            r4.close()     // Catch: java.lang.Throwable -> L3b
        L3b:
            return r2
        L3c:
            java.lang.String r5 = "select count(1) from message where id = ?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L7b
            r6[r1] = r9     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L7b
            android.database.Cursor r9 = r4.rawQuery(r5, r6)     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L7b
            if (r9 == 0) goto L5d
            boolean r0 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L5b
            if (r0 == 0) goto L5d
            int r0 = r9.getInt(r1)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L5b
            if (r0 <= 0) goto L5d
            r2 = 1
            goto L5d
        L56:
            r0 = move-exception
            r7 = r0
            r0 = r9
            r9 = r7
            goto L6e
        L5b:
            r0 = r9
            goto L7b
        L5d:
            if (r9 == 0) goto L62
            r9.close()     // Catch: java.lang.Throwable -> L83
        L62:
            if (r4 == 0) goto L83
        L64:
            r4.close()     // Catch: java.lang.Throwable -> L83
            goto L83
        L68:
            r9 = move-exception
            goto L6e
        L6a:
            r4 = r0
            goto L7b
        L6c:
            r9 = move-exception
            r4 = r0
        L6e:
            if (r0 == 0) goto L73
            r0.close()     // Catch: java.lang.Throwable -> L78
        L73:
            if (r4 == 0) goto L78
            r4.close()     // Catch: java.lang.Throwable -> L78
        L78:
            throw r9
        L79:
            r4 = r0
            r2 = 0
        L7b:
            if (r0 == 0) goto L80
            r0.close()     // Catch: java.lang.Throwable -> L83
        L80:
            if (r4 == 0) goto L83
            goto L64
        L83:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.android.agoo.message.MessageService.a(java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0083 A[Catch: Throwable -> 0x00a2, PHI: r2 r4
  0x0083: PHI (r2v4 boolean) = (r2v2 boolean), (r2v10 boolean) binds: [B:45:0x009f, B:29:0x0081] A[DONT_GENERATE, DONT_INLINE]
  0x0083: PHI (r4v4 android.database.sqlite.SQLiteDatabase) = (r4v3 android.database.sqlite.SQLiteDatabase), (r4v7 android.database.sqlite.SQLiteDatabase) binds: [B:45:0x009f, B:29:0x0081] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #4 {Throwable -> 0x00a2, blocks: (B:28:0x007e, B:30:0x0083, B:44:0x009c), top: B:52:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.lang.String r9, int r10) throws java.lang.Throwable {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            java.util.Map<java.lang.String, java.lang.Integer> r2 = org.android.agoo.message.MessageService.c     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            boolean r2 = r2.containsKey(r9)     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            r3 = 1
            if (r2 == 0) goto L39
            java.util.Map<java.lang.String, java.lang.Integer> r2 = org.android.agoo.message.MessageService.c     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            boolean r2 = r2.containsValue(r4)     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            if (r2 == 0) goto L39
            com.taobao.accs.utl.ALog$Level r2 = com.taobao.accs.utl.ALog.Level.I     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            boolean r2 = com.taobao.accs.utl.ALog.isPrintLog(r2)     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            if (r2 == 0) goto L37
            java.lang.String r2 = "MessageService"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            r4.<init>()     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            java.lang.String r5 = "addMessage,messageStores hasMessageDuplicate,msgid="
            r4.append(r5)     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            r4.append(r9)     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
            com.taobao.accs.utl.ALog.i(r2, r4, r5)     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L98
        L37:
            r2 = 1
            goto L3a
        L39:
            r2 = 0
        L3a:
            android.database.sqlite.SQLiteOpenHelper r4 = r8.b     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L8b
            android.database.sqlite.SQLiteDatabase r4 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L8b
            if (r4 != 0) goto L48
            if (r4 == 0) goto L47
            r4.close()     // Catch: java.lang.Throwable -> L47
        L47:
            return r2
        L48:
            java.lang.String r5 = "select count(1) from message where id = ? and body_code=? create_time< date('now','-1 day')"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> L9a
            r6[r1] = r9     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> L9a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> L9a
            r9.<init>()     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> L9a
            java.lang.String r7 = ""
            r9.append(r7)     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> L9a
            r9.append(r10)     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> L9a
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> L9a
            r6[r3] = r9     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> L9a
            android.database.Cursor r9 = r4.rawQuery(r5, r6)     // Catch: java.lang.Throwable -> L87 java.lang.Throwable -> L9a
            if (r9 == 0) goto L7c
            boolean r10 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L7a
            if (r10 == 0) goto L7c
            int r10 = r9.getInt(r1)     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L7a
            if (r10 <= 0) goto L7c
            r2 = 1
            goto L7c
        L76:
            r10 = move-exception
            r0 = r9
            r9 = r10
            goto L8d
        L7a:
            r0 = r9
            goto L9a
        L7c:
            if (r9 == 0) goto L81
            r9.close()     // Catch: java.lang.Throwable -> La2
        L81:
            if (r4 == 0) goto La2
        L83:
            r4.close()     // Catch: java.lang.Throwable -> La2
            goto La2
        L87:
            r9 = move-exception
            goto L8d
        L89:
            r4 = r0
            goto L9a
        L8b:
            r9 = move-exception
            r4 = r0
        L8d:
            if (r0 == 0) goto L92
            r0.close()     // Catch: java.lang.Throwable -> L97
        L92:
            if (r4 == 0) goto L97
            r4.close()     // Catch: java.lang.Throwable -> L97
        L97:
            throw r9
        L98:
            r4 = r0
            r2 = 0
        L9a:
            if (r0 == 0) goto L9f
            r0.close()     // Catch: java.lang.Throwable -> La2
        L9f:
            if (r4 == 0) goto La2
            goto L83
        La2:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.android.agoo.message.MessageService.a(java.lang.String, int):boolean");
    }

    private MsgDO b(String str, String str2) {
        boolean z;
        int i = 0;
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i("MessageService", "msgRecevie,message--->[" + str + "],utdid=" + j.b(a), new Object[0]);
        }
        if (TextUtils.isEmpty(str)) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.dealMessage", j.b(a), "message==null");
            if (ALog.isPrintLog(ALog.Level.I)) {
                ALog.i("MessageService", "handleMessage message==null,utdid=" + j.b(a), new Object[0]);
            }
            return null;
        }
        MsgDO msgDO = new MsgDO();
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            new Bundle();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            String string = null;
            int i2 = 0;
            while (i2 < length) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject != null) {
                    String string2 = jSONObject.getString("p");
                    String string3 = jSONObject.getString("i");
                    String string4 = jSONObject.getString("b");
                    long j = jSONObject.getLong("f");
                    sb.append(string3);
                    if (!jSONObject.isNull("ext")) {
                        string = jSONObject.getString("ext");
                    }
                    int i3 = length - 1;
                    if (i2 < i3) {
                        sb.append(",");
                    }
                    msgDO.msgIds = string3;
                    msgDO.extData = string;
                    msgDO.messageSource = AgooConstants.MESSAGE_SOURCE_ACCS;
                    msgDO.type = "cache";
                    if (TextUtils.isEmpty(string4)) {
                        msgDO.errorCode = AgooConstants.ACK_BODY_NULL;
                    } else if (TextUtils.isEmpty(string2)) {
                        msgDO.errorCode = AgooConstants.ACK_PACK_NULL;
                    } else if (j == -1) {
                        msgDO.errorCode = AgooConstants.ACK_FLAG_NULL;
                    } else if (!a(a, string2)) {
                        ALog.d("MessageService", "ondata checkpackage is del,pack=" + string2, new Object[i]);
                        UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.dealMessage", j.b(a), "deletePack", string2);
                        sb3.append(string2);
                        sb2.append(string3);
                        msgDO.removePacks = string2;
                        if (i2 < i3) {
                            sb3.append(",");
                            sb2.append(",");
                        }
                    } else {
                        String string5 = a(j, msgDO).getString(AgooConstants.MESSAGE_ENCRYPTED);
                        if (!a.getPackageName().equals(string2)) {
                            z = true;
                        } else if (TextUtils.equals(Integer.toString(0), string5) || TextUtils.equals(Integer.toString(4), string5)) {
                            z = false;
                        } else {
                            msgDO.errorCode = AgooConstants.ACK_PACK_ERROR;
                            ALog.e("MessageService", "error encrypted: " + string5, new Object[0]);
                        }
                        msgDO.agooFlag = z;
                        if (!TextUtils.isEmpty(str2)) {
                            msgDO.msgStatus = str2;
                        }
                    }
                }
                i2++;
                i = 0;
            }
        } catch (Throwable th) {
            if (ALog.isPrintLog(ALog.Level.E)) {
                ALog.e("MessageService", "createMsg is error,e: " + th, new Object[0]);
            }
        }
        return msgDO;
    }

    public static final boolean a(Context context, String str) {
        return context.getPackageManager().getApplicationInfo(str, 0) != null;
    }

    private static Bundle a(long j, MsgDO msgDO) {
        Bundle bundle = new Bundle();
        try {
            char[] charArray = Long.toBinaryString(j).toCharArray();
            if (charArray != null && 8 <= charArray.length) {
                if (8 <= charArray.length) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(Integer.parseInt("" + charArray[1] + charArray[2] + charArray[3] + charArray[4], 2));
                    bundle.putString(AgooConstants.MESSAGE_ENCRYPTED, sb.toString());
                    if (charArray[6] == '1') {
                        bundle.putString(AgooConstants.MESSAGE_REPORT, MSG_DB_NOTIFY_REACHED);
                        msgDO.reportStr = MSG_DB_NOTIFY_REACHED;
                    }
                    if (charArray[7] == '1') {
                        bundle.putString(AgooConstants.MESSAGE_NOTIFICATION, MSG_DB_NOTIFY_REACHED);
                    }
                }
                if (9 <= charArray.length && charArray[8] == '1') {
                    bundle.putString(AgooConstants.MESSAGE_HAS_TEST, MSG_DB_NOTIFY_REACHED);
                }
                if (10 <= charArray.length && charArray[9] == '1') {
                    bundle.putString(AgooConstants.MESSAGE_DUPLICATE, MSG_DB_NOTIFY_REACHED);
                }
                if (11 <= charArray.length && charArray[10] == '1') {
                    bundle.putInt(AgooConstants.MESSAGE_POPUP, 1);
                }
            }
        } catch (Throwable unused) {
        }
        return bundle;
    }
}
