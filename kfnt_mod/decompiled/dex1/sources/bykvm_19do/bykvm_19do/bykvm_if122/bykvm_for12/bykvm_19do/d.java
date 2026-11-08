package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d implements c<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.g> {

    /* renamed from: a, reason: collision with root package name */
    private final h f1945a;

    public d(Context context) {
        this.f1945a = h.a(context);
    }

    private void c(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.g gVar) throws Exception {
        a(gVar.b());
        ContentValues contentValues = new ContentValues();
        contentValues.put("rit_id", gVar.b());
        contentValues.put("waterfall_show_rules_version", gVar.c());
        contentValues.put("waterfall_timing_mode", Integer.valueOf(gVar.d()));
        contentValues.put("waterfall_show_freqctl_rules", gVar.f());
        this.f1945a.a().a("show_freqctl", (String) null, contentValues);
    }

    /* JADX WARN: Finally extract failed */
    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.g
    public synchronized bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.g b(String str) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.g gVar;
        Cursor cursorA = this.f1945a.a().a("show_freqctl", new String[]{"rit_id", "waterfall_show_rules_version", "waterfall_timing_mode", "waterfall_show_freqctl_rules"}, "rit_id=?", new String[]{str}, null, null, null, null);
        gVar = null;
        if (cursorA != null) {
            try {
                try {
                    if (cursorA.getCount() > 0) {
                        cursorA.moveToNext();
                        gVar = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.g(cursorA.getString(cursorA.getColumnIndex("rit_id")), cursorA.getString(cursorA.getColumnIndex("waterfall_show_rules_version")), cursorA.getInt(cursorA.getColumnIndex("waterfall_timing_mode")), cursorA.getString(cursorA.getColumnIndex("waterfall_show_freqctl_rules")));
                    }
                    if (cursorA != null) {
                        cursorA.close();
                    }
                } catch (Exception unused) {
                    if (cursorA != null) {
                        cursorA.close();
                    }
                }
            } catch (Throwable th) {
                if (cursorA != null) {
                    cursorA.close();
                }
                throw th;
            }
        }
        return gVar;
    }

    public static String a() {
        return "CREATE TABLE IF NOT EXISTS show_freqctl (_id INTEGER PRIMARY KEY AUTOINCREMENT,rit_id TEXT UNIQUE,waterfall_show_rules_version TEXT , waterfall_timing_mode INTEGER default 0 ,waterfall_show_freqctl_rules TEXT)";
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.g
    public synchronized void a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.g gVar) {
        if (gVar != null) {
            try {
                a(gVar.b());
                ContentValues contentValues = new ContentValues();
                contentValues.put("rit_id", gVar.b());
                contentValues.put("waterfall_show_rules_version", gVar.c());
                contentValues.put("waterfall_timing_mode", Integer.valueOf(gVar.d()));
                contentValues.put("waterfall_show_freqctl_rules", gVar.g());
                this.f1945a.a().a("show_freqctl", (String) null, contentValues);
            } catch (Exception unused) {
            }
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.g
    public synchronized void a(String str) {
        this.f1945a.a().a("DELETE FROM show_freqctl WHERE rit_id=" + str);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.c
    public synchronized void a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.g gVar, String str, int i) {
        if (gVar != null) {
            c(gVar);
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.c
    public synchronized void a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.g gVar, String str, long j) {
        if (gVar != null) {
            c(gVar);
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.c
    public synchronized void b(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.g gVar) {
        if (gVar != null) {
            c(gVar);
        }
    }
}
