package com.umeng.message.proguard;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class m {
    private static final String A = "com.umeng.message.proguard.m";
    private static m B = null;
    private static final String D = " Asc ";
    private static final String E = " Desc ";
    public static final String a = " And ";
    public static final String b = "MsgLogStore.db";
    public static final int c = 7;
    public static final String d = "MsgLogStore";
    public static final String e = "MsgLogIdTypeStore";
    public static final String f = "MsgLogStoreForAgoo";
    public static final String g = "MsgLogIdTypeStoreForAgoo";
    public static final String h = "MsgConfigInfo";
    public static final String i = "InAppLogStore";
    public static final String j = "MsgId";
    public static final String k = "MsgType";
    public static final String l = "ActionType";
    public static final String m = "pa";
    public static final String n = "Time";
    public static final String o = "TaskId";
    public static final String p = "MsgStatus";
    public static final String q = "SerialNo";
    public static final String r = "AppLaunchAt";
    public static final String s = "UpdateResponse";
    public static final String t = "NumDisplay";
    public static final String u = "NumOpenFull";
    public static final String v = "NumOpenTop";
    public static final String w = "NumOpenBottom";
    public static final String x = "NumClose";
    public static final String y = "NumDuration";
    public static final String z = "NumCustom";
    private Context C;

    public boolean a(String str, String str2, String str3, long j2) {
        return false;
    }

    public static m a(Context context) {
        if (B == null) {
            B = new m(context);
            B.h();
        }
        return B;
    }

    private m(Context context) {
        this.C = context.getApplicationContext();
    }

    private void h() {
        if (MessageSharedPrefs.getInstance(this.C).hasTransferedCacheFileDataToSQL()) {
            return;
        }
        File[] fileArrListFiles = this.C.getCacheDir().listFiles(new FilenameFilter() { // from class: com.umeng.message.proguard.m.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return !TextUtils.isEmpty(str) && str.startsWith(MsgConstant.CACHE_LOG_FILE_PREFIX);
            }
        });
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                a(file);
                file.delete();
            }
        }
        MessageSharedPrefs.getInstance(this.C).finishTransferedCacheFileDataToSQL();
    }

    private void a(File file) {
        try {
            JSONObject jSONObject = new JSONObject(b(file));
            a(jSONObject.optString("msg_id"), jSONObject.optInt(MsgConstant.KEY_ACTION_TYPE), jSONObject.optLong("ts"), jSONObject.optString("pa"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String b(File file) throws Throwable {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }
                String string = sb.toString();
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return string;
            } catch (Throwable th) {
                th = th;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    public boolean a(String str, int i2, long j2, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str2 == null) {
            str2 = "";
        }
        a aVar = new a(str, i2, j2, str2);
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        return contentResolver.insert(com.umeng.message.provider.a.f, aVar.a()) != null;
    }

    public boolean a(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = {str, i2 + ""};
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        return contentResolver.delete(com.umeng.message.provider.a.f, "MsgId=? And ActionType=?", strArr) == 1;
    }

    public a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.f, null, "MsgId=?", new String[0], null);
        a aVar = cursorQuery.moveToFirst() ? new a(cursorQuery) : null;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return aVar;
    }

    public ArrayList<a> a(int i2) {
        if (i2 < 1) {
            return null;
        }
        ArrayList<a> arrayList = new ArrayList<>();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = this.C.getContentResolver().query(com.umeng.message.provider.a.f, null, null, null, "Time Asc  limit " + i2);
        for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
            arrayList.add(new a(cursorQuery));
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public ArrayList<a> a() {
        ArrayList<a> arrayList = new ArrayList<>();
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.f, null, null, null, "Time Asc ");
        if (cursorQuery == null) {
            return arrayList;
        }
        for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
            arrayList.add(new a(cursorQuery));
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        c cVar = new c(str, str2);
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        return contentResolver.insert(com.umeng.message.provider.a.g, cVar.a()) != null;
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = {str};
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        return contentResolver.delete(com.umeng.message.provider.a.g, "MsgId=?", strArr) == 1;
    }

    public ArrayList<c> b(int i2) {
        if (i2 < 1) {
            return null;
        }
        ArrayList<c> arrayList = new ArrayList<>();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = this.C.getContentResolver().query(com.umeng.message.provider.a.g.buildUpon().appendQueryParameter("limit", i2 + "").build(), null, null, null, "MsgId Asc ");
        for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
            arrayList.add(new c(cursorQuery));
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public ArrayList<c> b() {
        ArrayList<c> arrayList = new ArrayList<>();
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.g, null, null, null, "MsgId Asc ");
        for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
            arrayList.add(new c(cursorQuery));
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = {str, str2};
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        return contentResolver.delete(com.umeng.message.provider.a.h, "MsgId=? And MsgStatus=?", strArr) == 1;
    }

    public b c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.h, null, "MsgId=?", new String[]{str}, null);
        b bVar = cursorQuery.moveToFirst() ? new b(cursorQuery) : null;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return bVar;
    }

    public ArrayList<b> c(int i2) {
        if (i2 < 1) {
            return null;
        }
        ArrayList<b> arrayList = new ArrayList<>();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = this.C.getContentResolver().query(com.umeng.message.provider.a.h.buildUpon().appendQueryParameter("limit", i2 + "").build(), null, null, null, "Time Asc ");
        for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
            arrayList.add(new b(cursorQuery));
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public ArrayList<b> c() {
        ArrayList<b> arrayList = new ArrayList<>();
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.h, null, null, null, "Time Asc ");
        for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
            arrayList.add(new b(cursorQuery));
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public boolean a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        d dVar = new d(str, str2, str3);
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        return contentResolver.insert(com.umeng.message.provider.a.i, dVar.a()) != null;
    }

    public boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = {str};
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        return contentResolver.delete(com.umeng.message.provider.a.i, "MsgId=?", strArr) == 1;
    }

    public ArrayList<d> d(int i2) {
        if (i2 < 1) {
            return null;
        }
        ArrayList<d> arrayList = new ArrayList<>();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = this.C.getContentResolver().query(com.umeng.message.provider.a.i.buildUpon().appendQueryParameter("limit", i2 + "").build(), null, null, null, "MsgId Asc ");
        for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
            arrayList.add(new d(cursorQuery));
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public ArrayList<d> d() {
        ArrayList<d> arrayList = new ArrayList<>();
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.i, null, null, null, "MsgId Asc ");
        for (boolean zMoveToFirst = cursorQuery.moveToFirst(); zMoveToFirst; zMoveToFirst = cursorQuery.moveToNext()) {
            arrayList.add(new d(cursorQuery));
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public int e() {
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.j, new String[]{q}, null, null, null);
        int i2 = cursorQuery.moveToFirst() ? cursorQuery.getInt(cursorQuery.getColumnIndex(q)) : 0;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return i2;
    }

    public void e(int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(q, i2 + "");
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        contentResolver.update(com.umeng.message.provider.a.j, contentValues, null, null);
    }

    public long f() {
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.j, new String[]{r}, null, null, null);
        if (cursorQuery == null) {
            return 0L;
        }
        long j2 = cursorQuery.moveToFirst() ? cursorQuery.getLong(cursorQuery.getColumnIndex(r)) : 0L;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        Log.d(A, "appLaunchAt=" + j2);
        return j2;
    }

    public void a(long j2) {
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.j, new String[]{r}, null, null, null);
        int count = cursorQuery.getCount();
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        if (count > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(r, j2 + "");
            ContentResolver contentResolver2 = this.C.getContentResolver();
            com.umeng.message.provider.a.a(this.C);
            contentResolver2.update(com.umeng.message.provider.a.j, contentValues, null, null);
            return;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(r, j2 + "");
        ContentResolver contentResolver3 = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        contentResolver3.insert(com.umeng.message.provider.a.j, contentValues2);
    }

    public Object g() {
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        Cursor cursorQuery = contentResolver.query(com.umeng.message.provider.a.j, new String[]{s}, null, null, null);
        String string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndex(s)) : null;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        Log.d(A, "updateResponse=" + string);
        return h.f(string);
    }

    public void a(Object obj) throws IOException {
        String strA = h.a(obj);
        ContentValues contentValues = new ContentValues();
        contentValues.put(s, strA);
        ContentResolver contentResolver = this.C.getContentResolver();
        com.umeng.message.provider.a.a(this.C);
        contentResolver.update(com.umeng.message.provider.a.j, contentValues, null, null);
    }

    public class a {
        public String a;
        public long b;
        public int c;
        public String d;

        public a(String str, int i, long j, String str2) {
            this.a = str;
            this.c = i;
            this.b = j;
            this.d = str2;
        }

        public a(Cursor cursor) {
            this.a = cursor.getString(cursor.getColumnIndex(m.j));
            this.b = cursor.getLong(cursor.getColumnIndex(m.n));
            this.c = cursor.getInt(cursor.getColumnIndex("ActionType"));
            this.d = cursor.getString(cursor.getColumnIndex("pa"));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(m.j, this.a);
            contentValues.put(m.n, Long.valueOf(this.b));
            contentValues.put("ActionType", Integer.valueOf(this.c));
            contentValues.put("pa", this.d);
            return contentValues;
        }
    }

    public class c {
        public String a;
        public String b;

        public c(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public c(Cursor cursor) {
            this.a = cursor.getString(cursor.getColumnIndex(m.j));
            this.b = cursor.getString(cursor.getColumnIndex(m.k));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(m.j, this.a);
            contentValues.put(m.k, this.b);
            return contentValues;
        }
    }

    public class b {
        public String a;
        public String b;
        public String c;
        public long d;

        public b(String str, String str2, String str3, long j) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = j;
        }

        public b(Cursor cursor) {
            this.a = cursor.getString(cursor.getColumnIndex(m.j));
            this.b = cursor.getString(cursor.getColumnIndex(m.o));
            this.c = cursor.getString(cursor.getColumnIndex(m.p));
            this.d = cursor.getLong(cursor.getColumnIndex(m.n));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(m.j, this.a);
            contentValues.put(m.o, this.b);
            contentValues.put(m.p, this.c);
            contentValues.put(m.n, Long.valueOf(this.d));
            return contentValues;
        }
    }

    public class d {
        public String a;
        public String b;
        public String c;

        public d(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public d(Cursor cursor) {
            this.a = cursor.getString(cursor.getColumnIndex(m.j));
            this.b = cursor.getString(cursor.getColumnIndex(m.o));
            this.c = cursor.getString(cursor.getColumnIndex(m.p));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(m.j, this.a);
            contentValues.put(m.o, this.b);
            contentValues.put(m.p, this.c);
            return contentValues;
        }
    }
}
