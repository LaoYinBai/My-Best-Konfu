package com.yxcorp.kuaishou.addfp.a.b.d;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a b;
    private Context a;

    private a(Context context) {
        this.a = context;
    }

    public static a a(Context context) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context);
                }
            }
        }
        return b;
    }

    private static String a(Context context, String str) throws Throwable {
        Cursor cursorQuery;
        String string;
        Cursor cursor = null;
        str = null;
        String str2 = null;
        cursor = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{str}, null);
                if (cursorQuery != null) {
                    try {
                        try {
                            if (cursorQuery.moveToNext()) {
                                string = cursorQuery.getString(cursorQuery.getColumnIndex("value"));
                                try {
                                    StringBuilder sb = new StringBuilder("getIdFromContentProvider meizu ");
                                    sb.append(string);
                                    sb.append(" ");
                                    sb.append(str);
                                    str2 = string;
                                } catch (Throwable th) {
                                    th = th;
                                    cursor = cursorQuery;
                                    th.printStackTrace();
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    return string;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        string = null;
                    }
                }
                if (cursorQuery == null) {
                    return str2;
                }
                cursorQuery.close();
                return str2;
            } catch (Throwable th4) {
                th = th4;
                string = null;
            }
        } catch (Throwable th5) {
            th = th5;
            cursorQuery = cursor;
        }
    }

    public final String a(String str) {
        try {
            return a(this.a, str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final boolean a() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.a.getPackageManager().resolveContentProvider("com.meizu.flyme.openidsdk", 0) != null;
    }
}
