package com.apm.insight.e.b;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.apm.insight.l.q;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class a<T> {

    /* renamed from: a, reason: collision with root package name */
    protected final String f2828a = "_id";

    /* renamed from: b, reason: collision with root package name */
    protected final String f2829b;

    protected a(String str) {
        this.f2829b = str;
    }

    protected abstract ContentValues a(T t);

    protected abstract HashMap<String, String> a();

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ");
            sb.append(this.f2829b);
            sb.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            HashMap<String, String> mapA = a();
            if (mapA != null) {
                for (String str : mapA.keySet()) {
                    sb.append(str);
                    sb.append(" ");
                    sb.append(mapA.get(str));
                    sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                }
                sb.delete(sb.length() - 1, sb.length());
                sb.append(")");
                sQLiteDatabase.execSQL(sb.toString());
            }
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase, T t) {
        if (sQLiteDatabase == null || t == null) {
            return;
        }
        try {
            sQLiteDatabase.insert(this.f2829b, null, a((a<T>) t));
        } catch (Throwable th) {
            q.b(th);
        }
    }
}
