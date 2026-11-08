package com.sigmob.sdk.common.a;

import android.database.Cursor;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.a.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final a a = new a();
    private final Object c = new Object();
    private final ThreadPoolExecutor b = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1, TimeUnit.MINUTES, new ArrayBlockingQueue(10));

    /* renamed from: com.sigmob.sdk.common.a.a$a, reason: collision with other inner class name */
    public interface InterfaceC0016a {
        void a(Error error);

        void a(List<Map> list);
    }

    private class b implements Runnable {
        private final String b;
        private final String c;
        private final c.a d;

        b(String str, String str2, c.a aVar) {
            this.b = str;
            this.c = str2;
            this.d = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                com.sigmob.sdk.common.a.c.a().getWritableDatabase().delete(this.b, this.c, null);
                if (this.d != null) {
                    this.d.onSuccess();
                }
            } catch (Throwable th) {
                if (this.d != null) {
                    this.d.onFailed(th);
                }
            }
        }
    }

    private class c implements Runnable {
        private final String b;
        private final String[] c;
        private final String d;
        private final String[] e;
        private final String f;
        private final String g;
        private final String h;
        private final String i;
        private final InterfaceC0016a j;

        c(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, InterfaceC0016a interfaceC0016a) {
            this.b = str;
            this.c = strArr;
            this.d = str2;
            this.e = strArr2;
            this.f = str3;
            this.g = str4;
            this.h = str5;
            this.i = str6;
            this.j = interfaceC0016a;
        }

        private List<Map> a(Cursor cursor) {
            Object objValueOf;
            ArrayList arrayList = new ArrayList();
            while (cursor.moveToNext()) {
                String[] columnNames = cursor.getColumnNames();
                HashMap map = new HashMap();
                for (String str : columnNames) {
                    switch (cursor.getType(cursor.getColumnIndex(str))) {
                        case 1:
                            objValueOf = Long.valueOf(cursor.getLong(cursor.getColumnIndex(str)));
                            map.put(str, objValueOf);
                            break;
                        case 2:
                            objValueOf = Double.valueOf(cursor.getDouble(cursor.getColumnIndex(str)));
                            map.put(str, objValueOf);
                            break;
                        case 3:
                            objValueOf = cursor.getString(cursor.getColumnIndex(str));
                            map.put(str, objValueOf);
                            break;
                        case 4:
                            objValueOf = cursor.getBlob(cursor.getColumnIndex(str));
                            map.put(str, objValueOf);
                            break;
                    }
                }
                arrayList.add(map);
            }
            return arrayList;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            Cursor cursorQuery;
            Cursor cursor = null;
            try {
                try {
                    cursorQuery = com.sigmob.sdk.common.a.c.a().getReadableDatabase().query(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                List<Map> listA = a(cursorQuery);
                if (this.j != null) {
                    this.j.a(listA);
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = cursorQuery;
                if (this.j != null) {
                    this.j.a(new Error(th.getMessage()));
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    private a() {
    }

    public static synchronized a a() {
        return a;
    }

    public int a(String str) {
        return a(str, null);
    }

    public int a(String str, String str2) throws Throwable {
        int count;
        Cursor cursorQuery;
        Cursor cursor = null;
        try {
            try {
                cursorQuery = com.sigmob.sdk.common.a.c.a().getReadableDatabase().query(str, null, str2, null, null, null, null, null);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            count = cursorQuery.getCount();
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = cursorQuery;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return count;
    }

    public void a(String str, String str2, c.a aVar) {
        try {
            this.b.submit(new b(str, str2, aVar));
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            aVar.onFailed(th);
        }
    }

    public void a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, InterfaceC0016a interfaceC0016a) {
        this.b.submit(new c(str, strArr, str2, strArr2, str3, str4, str5, str6, interfaceC0016a));
    }
}
