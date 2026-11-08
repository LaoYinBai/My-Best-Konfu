package com.sigmob.sdk.common.mta;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Base64;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.a.c;
import com.sigmob.sdk.common.e.a;
import com.sigmob.volley.s;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.xiaomi.mipush.sdk.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.zip.DeflaterOutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BuriedPointManager {
    private static BuriedPointManager a = null;
    private static int b = 20;
    private final ReadWriteLock c = new ReentrantReadWriteLock();
    private HashMap<Integer, String> d = null;
    private volatile boolean e;

    private static class BuriedPointManagerFactory {
        static BuriedPointManager a = new BuriedPointManager();

        private BuriedPointManagerFactory() {
        }
    }

    private static synchronized HashMap<Integer, String> a(int i) {
        HashMap<Integer, String> map;
        Cursor cursorRawQuery;
        map = new HashMap<>();
        Cursor cursor = null;
        try {
            try {
                cursorRawQuery = c.a().getReadableDatabase().rawQuery("select * from point where item not null order by point_id", null);
                if (cursorRawQuery != null) {
                    try {
                        if (cursorRawQuery.moveToFirst()) {
                            int columnIndex = cursorRawQuery.getColumnIndex("item");
                            int columnIndex2 = cursorRawQuery.getColumnIndex("point_id");
                            for (int i2 = 0; i2 < i; i2++) {
                                String string = cursorRawQuery.getString(columnIndex);
                                Integer numValueOf = Integer.valueOf(cursorRawQuery.getInt(columnIndex2));
                                if (!TextUtils.isEmpty(string)) {
                                    map.put(numValueOf, string);
                                }
                                if (!cursorRawQuery.moveToNext()) {
                                    break;
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        throw th;
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            cursorRawQuery = cursor;
        }
        return map;
    }

    private static void a() {
        try {
            long jQueryNumEntries = DatabaseUtils.queryNumEntries(c.a().getWritableDatabase(), "point");
            if (jQueryNumEntries <= AppLinkMonitor.DELAY_TIME) {
                return;
            }
            a(jQueryNumEntries - AppLinkMonitor.DELAY_TIME);
        } catch (Throwable th) {
            SigmobLog.e("clearLogDB fail", th);
        }
    }

    private static void a(long j) {
        try {
            SigmobLog.d("begin numRows " + DatabaseUtils.queryNumEntries(c.a().getReadableDatabase(), "point"));
            StringBuilder sb = new StringBuilder();
            sb.append("delete from ");
            sb.append("point");
            sb.append(" where point_id in ( ");
            sb.append(" select point_id from ");
            sb.append("point");
            sb.append(" order by point_id ");
            sb.append(" limit " + j);
            sb.append(" )");
            SQLiteDatabase writableDatabase = c.a().getWritableDatabase();
            writableDatabase.execSQL(sb.toString());
            SigmobLog.d("end numRows " + DatabaseUtils.queryNumEntries(writableDatabase, "point"));
        } catch (Throwable th) {
            SigmobLog.e("clearLogDB fail", th);
        }
    }

    private void a(String str) {
        this.e = true;
        a.a(str, new a.InterfaceC0019a() { // from class: com.sigmob.sdk.common.mta.BuriedPointManager.1
            @Override // com.sigmob.sdk.common.e.a.InterfaceC0019a
            public void onErrorResponse(s sVar) {
                BuriedPointManager.this.e = false;
                BuriedPointManager.this.d = null;
                SigmobLog.e(sVar.getMessage());
            }

            @Override // com.sigmob.sdk.common.e.a.InterfaceC0019a
            public void onSuccess() {
                BuriedPointManager.this.clearLog();
            }
        });
    }

    private static void a(Set<Integer> set) {
        try {
            Iterator<Integer> it = set.iterator();
            if (!it.hasNext()) {
                return;
            }
            SigmobLog.d("begin numRows " + DatabaseUtils.queryNumEntries(c.a().getReadableDatabase(), "point"));
            StringBuilder sb = new StringBuilder();
            sb.append("delete from ");
            sb.append("point");
            sb.append(" where point_id in ( ");
            while (true) {
                sb.append(it.next());
                if (!it.hasNext()) {
                    sb.append(" )");
                    SQLiteDatabase writableDatabase = c.a().getWritableDatabase();
                    writableDatabase.execSQL(sb.toString());
                    SigmobLog.d("end numRows " + DatabaseUtils.queryNumEntries(writableDatabase, "point"));
                    return;
                }
                sb.append(',');
                sb.append(' ');
            }
        } catch (Throwable th) {
            SigmobLog.e("clearLogDB fail", th);
        }
    }

    private static void b() {
        try {
            SigmobLog.d("begin numRows " + DatabaseUtils.queryNumEntries(c.a().getReadableDatabase(), "point"));
            SQLiteDatabase writableDatabase = c.a().getWritableDatabase();
            writableDatabase.execSQL("delete from point where item is null");
            SigmobLog.d("end numRows " + DatabaseUtils.queryNumEntries(writableDatabase, "point"));
        } catch (Throwable th) {
            SigmobLog.e("clearLogDB fail", th);
        }
    }

    public static void clearLogDB() {
        b();
        a();
    }

    public static String deflateAndBase64(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
        deflaterOutputStream.write(str.getBytes(Charset.forName("UTF-8")));
        deflaterOutputStream.flush();
        deflaterOutputStream.close();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    public static BuriedPointManager getInstance() {
        return BuriedPointManagerFactory.a;
    }

    public void clearLog() {
        this.c.writeLock().lock();
        if (this.d != null && this.d.size() != 0) {
            a(this.d.keySet());
            this.e = false;
            this.d = null;
        }
        this.c.writeLock().unlock();
    }

    public String sendPoint() {
        String uRLEncoded;
        this.c.readLock().lock();
        String str = null;
        if (!this.e && (this.d == null || this.d.size() <= 0)) {
            this.d = a(b);
            try {
                if (this.d.size() != 0) {
                    try {
                        StringBuilder sb = new StringBuilder();
                        sb.append("[");
                        Iterator<String> it = this.d.values().iterator();
                        while (it.hasNext()) {
                            sb.append(it.next());
                            if (it.hasNext()) {
                                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                            }
                        }
                        sb.append("]");
                        String string = sb.toString();
                        String str2 = "_uniq_key=" + ("sigandroid_" + com.sigmob.sdk.common.a.n().ab()) + "&_batch_value=" + string;
                        try {
                            SigmobLog.d("BPLog_Count: " + this.d.size());
                            uRLEncoded = PointEntitySuper.toURLEncoded(deflateAndBase64(str2));
                        } catch (IOException e) {
                            e = e;
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        a(uRLEncoded);
                    } catch (IOException e2) {
                        str = uRLEncoded;
                        e = e2;
                        SigmobLog.e(e.getMessage());
                        uRLEncoded = str;
                        return uRLEncoded;
                    } catch (Throwable th2) {
                        str = uRLEncoded;
                        th = th2;
                        SigmobLog.e(th.getMessage());
                        this.c.readLock().unlock();
                        return str;
                    }
                    return uRLEncoded;
                }
            } finally {
                this.c.readLock().unlock();
            }
        }
        return null;
    }
}
