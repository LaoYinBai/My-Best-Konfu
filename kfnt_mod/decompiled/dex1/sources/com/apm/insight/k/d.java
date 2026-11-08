package com.apm.insight.k;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import com.apm.insight.CrashType;
import com.apm.insight.Npth;
import com.apm.insight.l.o;
import com.apm.insight.l.q;
import com.apm.insight.l.r;
import com.apm.insight.runtime.p;
import com.apm.insight.runtime.r;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private static volatile d f2915a;

    /* renamed from: b, reason: collision with root package name */
    private volatile Context f2916b;

    private d(@NonNull Context context) {
        this.f2916b = context;
    }

    public static d a() {
        if (f2915a == null) {
            f2915a = new d(com.apm.insight.h.g());
        }
        return f2915a;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        try {
            String strF = e.f();
            File file = new File(o.a(this.f2916b), o.c());
            com.apm.insight.l.i.a(file, file.getName(), strF, jSONObject, e.b());
            if (e.a(strF, jSONObject.toString()).a()) {
                com.apm.insight.l.i.a(file);
            }
        } catch (Throwable th) {
            q.b(th);
        }
    }

    public void a(JSONObject jSONObject, long j, boolean z) {
        File[] fileArr;
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        try {
            String strC = e.c();
            int i = 0;
            File file = new File(o.a(this.f2916b), com.apm.insight.h.a(j, CrashType.ANR, false, false));
            com.apm.insight.l.i.a(file, file.getName(), strC, jSONObject, e.b());
            if (z && !Npth.isStopUpload()) {
                jSONObject.put("upload_scene", "direct");
                jSONObject.put("crash_uuid", file.getName());
                r.a(jSONObject);
                if (com.apm.insight.runtime.a.j()) {
                    HashMap<String, r.a> mapA = com.apm.insight.runtime.r.a(j, "anr_trace");
                    fileArr = new File[mapA.size() + 2];
                    for (Map.Entry<String, r.a> entry : mapA.entrySet()) {
                        if (!entry.getKey().equals(com.apm.insight.l.a.c(this.f2916b))) {
                            fileArr[i] = o.a(this.f2916b, entry.getValue().f3064b);
                            i++;
                        }
                    }
                } else {
                    fileArr = new File[2];
                }
                fileArr[fileArr.length - 1] = o.a(this.f2916b, com.apm.insight.h.f());
                fileArr[fileArr.length - 2] = com.apm.insight.runtime.r.a(j);
                if (e.a(strC, jSONObject.toString(), fileArr).a()) {
                    com.apm.insight.l.i.a(file);
                    if (Npth.hasCrash()) {
                        return;
                    }
                    com.apm.insight.l.i.a(o.e(com.apm.insight.h.g()));
                }
            }
        } catch (Throwable unused) {
        }
    }

    public boolean a(long j, JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() > 0) {
            try {
                String strC = e.c();
                File file = new File(o.a(this.f2916b), o.a(com.apm.insight.h.e()));
                com.apm.insight.l.i.a(file, file.getName(), strC, jSONObject, e.a());
                jSONObject.put("upload_scene", "direct");
                com.apm.insight.l.r.a(jSONObject);
                if (!e.b(strC, jSONObject.toString()).a()) {
                    return false;
                }
                com.apm.insight.l.i.a(file);
                return true;
            } catch (Throwable th) {
                q.b(th);
            }
        }
        return false;
    }

    public boolean a(JSONObject jSONObject, File file, File file2) {
        try {
            String strG = e.g();
            com.apm.insight.l.r.a(jSONObject);
            return e.a(strG, jSONObject.toString(), file, file2, com.apm.insight.runtime.r.a(System.currentTimeMillis()), new File(com.apm.insight.h.b.a())).a();
        } catch (Throwable th) {
            q.b(th);
            return false;
        }
    }

    public void b(final JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        p.b().a(new Runnable() { // from class: com.apm.insight.k.d.1
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                String strC = e.c();
                try {
                    jSONObject.put("upload_scene", "direct");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                e.b(strC, jSONObject.toString());
            }
        });
    }
}
