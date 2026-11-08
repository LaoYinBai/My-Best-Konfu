package c.a.a.a.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class o {

    /* renamed from: a, reason: collision with root package name */
    public static int f2109a;

    /* renamed from: b, reason: collision with root package name */
    private static volatile o f2110b;

    /* renamed from: c, reason: collision with root package name */
    private SQLiteDatabase f2111c;
    private l d;

    private o(Context context) {
        this.d = null;
        if (context != null) {
            this.d = new l(context);
        }
    }

    public static int a() {
        int i = f2109a;
        f2109a = i + 1;
        return i;
    }

    public static o a(@NonNull Context context) {
        if (f2110b == null) {
            synchronized (o.class) {
                if (f2110b == null && context != null) {
                    f2110b = new o(context.getApplicationContext());
                }
            }
        }
        return f2110b;
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

    private void b() {
        if (this.f2111c != null) {
            return;
        }
        try {
            this.f2111c = this.d.getWritableDatabase();
        } catch (SQLException unused) {
            this.f2111c = null;
        }
    }

    public synchronized boolean a(long j, String str) {
        long jDelete;
        b();
        SQLiteDatabase sQLiteDatabase = this.f2111c;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            try {
                jDelete = this.f2111c.delete("SCCheckLog", "timestamp" + str, new String[]{"" + j});
            } catch (Exception e) {
                e.printStackTrace();
                jDelete = -1;
            }
            return jDelete > 0;
        }
        return false;
    }

    public synchronized boolean a(String str, long j, String str2) {
        b();
        SQLiteDatabase sQLiteDatabase = this.f2111c;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("tag", str);
            contentValues.put("timestamp", Long.valueOf(j));
            contentValues.put("log", str2);
            return this.f2111c.insert("SCCheckLog", null, contentValues) > 0;
        }
        return false;
    }

    public synchronized String[] a(long j, String str, long j2) {
        Cursor cursor;
        Cursor cursorQuery;
        b();
        SQLiteDatabase sQLiteDatabase = this.f2111c;
        String[] strArr = null;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            String[] strArr2 = {"log"};
            String str2 = "timestamp" + str;
            int i = 0;
            String[] strArr3 = {String.valueOf("" + j)};
            try {
                if (j2 >= 0) {
                    cursorQuery = this.f2111c.query("SCCheckLog", strArr2, str2, strArr3, null, null, null, "" + j2);
                } else {
                    cursorQuery = this.f2111c.query("SCCheckLog", strArr2, str2, strArr3, null, null, null);
                }
                cursor = cursorQuery;
                if (cursor != null) {
                    try {
                        strArr = new String[cursor.getCount()];
                        while (cursor.moveToNext()) {
                            int columnIndex = cursor.getColumnIndex("log");
                            if (columnIndex != -1) {
                                strArr[i] = cursor.getString(columnIndex);
                                i++;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        a(cursor);
                        throw th;
                    }
                }
                a(cursor);
                return strArr;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
            }
        }
        return null;
    }

    public synchronized String b(long j, String str, long j2) {
        String strSubstring;
        String string;
        String str2 = "[";
        try {
            String[] strArrA = a(j, str, j2);
            int i = 0;
            while (i < strArrA.length) {
                i++;
                str2 = str2 + strArrA[i] + IWeiboService.Scope.EMPTY_SCOPE;
            }
            strSubstring = str2.substring(0, str2.length() - 1);
        } catch (Throwable unused) {
            strSubstring = str2;
        }
        string = strSubstring + "]";
        try {
            JSONArray jSONArray = new JSONArray(string);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("logs", jSONArray);
            string = jSONObject.toString();
        } catch (JSONException unused2) {
        }
        return string;
    }
}
