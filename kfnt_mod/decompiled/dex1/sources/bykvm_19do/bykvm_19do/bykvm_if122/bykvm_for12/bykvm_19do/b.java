package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.o;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u;
import com.bytedance.msdk.adapter.util.Logger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements a<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> {

    /* renamed from: a, reason: collision with root package name */
    private final Context f1942a;

    /* renamed from: b, reason: collision with root package name */
    private final h f1943b;

    /* renamed from: c, reason: collision with root package name */
    private final u f1944c = u.a("tt_adevent", a());

    public b(Context context) {
        this.f1942a = context;
        this.f1943b = h.a(this.f1942a);
    }

    private synchronized void b(int i, int i2, long j) {
        long jCurrentTimeMillis = System.currentTimeMillis() - j;
        int iA = this.f1943b.a().a(c(i), "gen_time < ? AND retry >?", new String[]{jCurrentTimeMillis + "", i2 + ""});
        StringBuilder sb = new StringBuilder();
        sb.append("客户端容灾状态下事件过期时间(7天)：");
        sb.append(j);
        sb.append("，成功删除日志条数：delete count=");
        sb.append(iA);
        Logger.e("AdEventThread", sb.toString());
    }

    public static String c() {
        return "CREATE TABLE IF NOT EXISTS adevent (_id INTEGER PRIMARY KEY AUTOINCREMENT,id TEXT UNIQUE," + com.bytedance.b.a.a.a.n + " TEXT ,gen_time TEXT , retry INTEGER default 0)";
    }

    private String c(int i) {
        return (i != 1 && i == 2) ? "adevent_applog" : "adevent";
    }

    public Context a() {
        Context context = this.f1942a;
        return context == null ? bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d() : context;
    }

    /* JADX WARN: Finally extract failed */
    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a
    public synchronized List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> a(int i, int i2, String str) {
        LinkedList linkedList;
        String str2 = null;
        if (i2 > 0) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    str2 = str + " DESC limit " + i2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        linkedList = new LinkedList();
        Cursor cursorA = h.a(a()).a().a(c(i), new String[]{"id", com.bytedance.b.a.a.a.n}, null, null, null, null, str2, null);
        if (cursorA != null) {
            while (cursorA.moveToNext()) {
                try {
                    linkedList.add(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a.a(cursorA.getString(cursorA.getColumnIndex("id")), cursorA.getString(cursorA.getColumnIndex(com.bytedance.b.a.a.a.n))));
                } catch (Throwable th2) {
                    if (cursorA != null) {
                        cursorA.close();
                    }
                    throw th2;
                }
            }
            if (cursorA != null) {
                cursorA.close();
            }
        }
        return linkedList;
    }

    @VisibleForTesting
    private synchronized void b(int i, List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> list) {
        LinkedList linkedList = new LinkedList();
        Iterator<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> it = list.iterator();
        while (it.hasNext()) {
            linkedList.add(it.next().f1937a);
        }
        this.f1943b.a().a("UPDATE " + c(i) + " SET retry = retry+1 WHERE " + a("id", (List<?>) linkedList, 1000, true));
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a
    public int b(int i) {
        if (i == 1) {
            return this.f1944c.a("serverbusy_retrycount", 0);
        }
        if (i == 2) {
            return this.f1944c.a("applog_serverbusy_retrycount", 0);
        }
        return 0;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a
    public synchronized void a(int i, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", aVar.f1937a);
        JSONObject jSONObject = aVar.f1938b;
        contentValues.put(com.bytedance.b.a.a.a.n, jSONObject != null ? jSONObject.toString() : "");
        contentValues.put("gen_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("retry", (Integer) 0);
        this.f1943b.a().a(c(i), (String) null, contentValues);
    }

    public static String b() {
        return "CREATE TABLE IF NOT EXISTS adevent_applog (_id INTEGER PRIMARY KEY AUTOINCREMENT,id TEXT UNIQUE," + com.bytedance.b.a.a.a.n + " TEXT ,gen_time TEXT , retry INTEGER default 0)";
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a
    public synchronized void a(int i, List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> list) {
        if (o.a(list)) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        Iterator<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> it = list.iterator();
        while (it.hasNext()) {
            linkedList.add(it.next().f1937a);
        }
        this.f1943b.a().a("DELETE FROM " + c(i) + " WHERE " + a("id", (List<?>) linkedList, 1000, true));
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a
    public synchronized void a(int i, int i2, long j) {
        b(i, i2, j);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a
    public synchronized void a(int i, List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> list, int i2, long j) {
        if (o.a(list)) {
            return;
        }
        try {
            b(i, list);
            b(i, i2, j);
        } catch (Exception unused) {
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a
    public void a(int i, boolean z) {
        if (i == 1) {
            this.f1944c.b("serverbusy_flag", z);
        } else if (i == 2) {
            this.f1944c.b("applog_serverbusy_flag", z);
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a
    public boolean a(int i) {
        if (i == 1) {
            return this.f1944c.a("serverbusy_flag", false);
        }
        if (i == 2) {
            return this.f1944c.a("applog_serverbusy_flag", false);
        }
        return false;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a
    public void a(int i, int i2) {
        if (i == 1) {
            this.f1944c.b("serverbusy_retrycount", i2);
        } else if (i == 2) {
            this.f1944c.b("applog_serverbusy_retrycount", i2);
        }
    }

    private static String a(String str, List<?> list, int i, boolean z) {
        int i2;
        String str2 = z ? " IN " : " NOT IN ";
        String str3 = z ? " OR " : " AND ";
        int iMin = Math.min(i, 1000);
        int size = list.size();
        int i3 = size % iMin;
        Logger.d("", "len=" + size + ",size=" + i3);
        if (i3 == 0) {
            i2 = size / iMin;
        } else {
            i2 = (size / iMin) + 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 * iMin;
            String strA = a(TextUtils.join("','", list.subList(i5, Math.min(i5 + iMin, size))), "");
            if (i4 != 0) {
                sb.append(str3);
            }
            sb.append(str);
            sb.append(str2);
            sb.append("('");
            sb.append(strA);
            sb.append("')");
        }
        String strA2 = a(sb.toString(), str + str2 + "('')");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("sql=");
        sb2.append(strA2);
        Logger.d("", sb2.toString());
        return strA2;
    }

    private static String a(String str, String str2) {
        return !TextUtils.isEmpty(str) ? str : str2;
    }
}
