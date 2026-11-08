package com.apm.insight.runtime;

import android.content.Context;
import androidx.annotation.Nullable;
import com.apm.insight.entity.Header;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class s {

    /* renamed from: a, reason: collision with root package name */
    private static s f3066a;

    /* renamed from: b, reason: collision with root package name */
    private File f3067b;

    /* renamed from: c, reason: collision with root package name */
    private File f3068c;
    private File d;
    private Context e;
    private a f = null;

    private static class a {

        /* renamed from: a, reason: collision with root package name */
        private long f3071a;

        /* renamed from: b, reason: collision with root package name */
        private long f3072b;

        /* renamed from: c, reason: collision with root package name */
        private File f3073c;
        private JSONObject d;

        private a(File file) {
            this.d = null;
            this.f3073c = file;
            String[] strArrSplit = file.getName().split("-|\\.");
            this.f3071a = Long.parseLong(strArrSplit[0]);
            this.f3072b = Long.parseLong(strArrSplit[1]);
        }

        private String a() {
            return this.f3071a + "-" + this.f3072b + ".ctx";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(long j) {
            this.f3072b = j;
            this.f3073c.renameTo(new File(this.f3073c.getParent(), a()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public JSONObject b() {
            if (this.d == null) {
                try {
                    this.d = new JSONObject(com.apm.insight.l.i.c(this.f3073c.getAbsolutePath()));
                } catch (Throwable unused) {
                }
                if (this.d == null) {
                    this.d = new JSONObject();
                }
            }
            return this.d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b(long j) {
            if (this.f3071a > j && this.f3071a - j > 604800000) {
                return true;
            }
            if (this.f3072b >= j || j - this.f3072b <= 604800000) {
                return this.f3073c.lastModified() < j && j - this.f3073c.lastModified() > 604800000;
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            this.f3073c.delete();
        }
    }

    private s(Context context) {
        File fileC = com.apm.insight.l.o.c(context);
        if (!fileC.exists() || (!fileC.isDirectory() && fileC.delete())) {
            fileC.mkdirs();
            com.apm.insight.runtime.a.b.a();
        }
        this.f3067b = fileC;
        this.f3068c = new File(fileC, "did");
        this.d = new File(fileC, "device_uuid");
        this.e = context;
    }

    public static int a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (Header.c(jSONObject)) {
            return 2;
        }
        if (Header.c(jSONObject2)) {
            return 0;
        }
        return (String.valueOf(jSONObject2.opt("update_version_code")).equals(String.valueOf(jSONObject.opt("update_version_code"))) && Header.d(jSONObject)) ? 1 : 2;
    }

    public static s a() {
        if (f3066a == null) {
            f3066a = new s(com.apm.insight.h.g());
        }
        return f3066a;
    }

    private void a(long j, long j2, JSONObject jSONObject, JSONArray jSONArray) throws Throwable {
        File file = new File(this.f3067b, "" + j + "-" + j2 + ".ctx");
        File file2 = new File(this.f3067b, "" + j + "-" + j2 + ".allData");
        try {
            com.apm.insight.l.i.a(file, jSONObject, false);
            com.apm.insight.l.i.a(file2, jSONArray, false);
            this.f = new a(file);
        } catch (IOException e) {
            com.apm.insight.b.a().a("NPTH_CATCH", e);
        }
    }

    private a c() {
        if (this.f == null) {
            d(".ctx");
        }
        return this.f;
    }

    private void c(long j) {
        try {
            ArrayList<a> arrayListD = d("");
            if (arrayListD.size() <= 6) {
                return;
            }
            Iterator<a> it = arrayListD.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next.b(j)) {
                    next.c();
                }
            }
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
        }
    }

    private File d(long j) {
        Iterator<a> it = d(".ctx").iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (j >= next.f3071a && j <= next.f3072b) {
                return next.f3073c;
            }
        }
        return null;
    }

    private ArrayList<a> d(final String str) {
        File[] fileArrListFiles = this.f3067b.listFiles(new FilenameFilter() { // from class: com.apm.insight.runtime.s.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str2) {
                return str2.endsWith(str) && Pattern.compile("^\\d{1,13}-\\d{1,13}.*").matcher(str2).matches();
            }
        });
        com.apm.insight.l.q.a((Object) ("foundRuntimeContextFiles " + fileArrListFiles.length));
        ArrayList<a> arrayList = new ArrayList<>();
        if (fileArrListFiles == null) {
            return arrayList;
        }
        a aVar = null;
        for (File file : fileArrListFiles) {
            try {
                a aVar2 = new a(file);
                arrayList.add(aVar2);
                if (this.f == null && ".ctx".equals(str) && (aVar == null || aVar2.f3072b >= aVar.f3072b)) {
                    aVar = aVar2;
                }
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
        }
        if (this.f == null && aVar != null) {
            this.f = aVar;
        }
        return arrayList;
    }

    private File e(long j) {
        Iterator<a> it = d(".allData").iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (j >= next.f3071a && j <= next.f3072b) {
                return next.f3073c;
            }
        }
        return null;
    }

    private File f(long j) {
        Iterator<a> it = d(".ctx").iterator();
        a aVar = null;
        while (it.hasNext()) {
            a next = it.next();
            if (aVar == null || Math.abs(aVar.f3072b - j) > Math.abs(next.f3072b - j)) {
                aVar = next;
            }
        }
        if (aVar == null) {
            return null;
        }
        return aVar.f3073c;
    }

    private File g(long j) {
        Iterator<a> it = d(".allData").iterator();
        a aVar = null;
        while (it.hasNext()) {
            a next = it.next();
            if (aVar == null || Math.abs(aVar.f3072b - j) > Math.abs(next.f3072b - j)) {
                aVar = next;
            }
        }
        if (aVar == null) {
            return null;
        }
        return aVar.f3073c;
    }

    public String a(String str) {
        try {
            return com.apm.insight.l.i.c(this.d.getAbsolutePath());
        } catch (Throwable unused) {
            return str;
        }
    }

    @Nullable
    public JSONObject a(long j) {
        boolean z;
        String strC;
        File fileD = d(j);
        if (fileD == null) {
            fileD = f(j);
            z = true;
        } else {
            z = false;
        }
        JSONObject jSONObject = null;
        if (fileD != null) {
            try {
                strC = com.apm.insight.l.i.c(fileD.getAbsolutePath());
                try {
                    jSONObject = new JSONObject(strC);
                } catch (Throwable th) {
                    th = th;
                    com.apm.insight.b.a().a("NPTH_CATCH", new IOException("content :" + strC, th));
                    if (jSONObject != null) {
                        try {
                            jSONObject.put("unauthentic_version", 1);
                        } catch (JSONException e) {
                            com.apm.insight.b.a().a("NPTH_CATCH", e);
                        }
                    }
                    return jSONObject;
                }
            } catch (Throwable th2) {
                th = th2;
                strC = null;
            }
        }
        if (jSONObject != null && z) {
            jSONObject.put("unauthentic_version", 1);
        }
        return jSONObject;
    }

    public void a(Map<String, Object> map, JSONArray jSONArray) throws Throwable {
        JSONObject jSONObjectA = Header.a(this.e).a(map);
        if (Header.c(jSONObjectA)) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        a aVarC = c();
        if (aVarC == null) {
            a(jCurrentTimeMillis, jCurrentTimeMillis, jSONObjectA, jSONArray);
            return;
        }
        switch (a(aVarC.b(), jSONObjectA)) {
            case 1:
                a(aVarC.f3071a, jCurrentTimeMillis, jSONObjectA, jSONArray);
                com.apm.insight.l.i.a(aVarC.f3073c);
                break;
            case 2:
                a(jCurrentTimeMillis, jCurrentTimeMillis, jSONObjectA, jSONArray);
                break;
            case 3:
                aVarC.a(jCurrentTimeMillis);
                break;
        }
        c(jCurrentTimeMillis);
    }

    protected String b() {
        try {
            return com.apm.insight.l.i.c(this.f3068c.getAbsolutePath());
        } catch (Throwable unused) {
            return OnekeyLoginConstants.CU_RESULT_SUCCESS;
        }
    }

    @Nullable
    public JSONArray b(long j) {
        String strC;
        File fileE = e(j);
        if (fileE == null) {
            fileE = g(j);
        }
        if (fileE == null) {
            return null;
        }
        try {
            strC = com.apm.insight.l.i.c(fileE.getAbsolutePath());
            try {
                return new JSONArray(strC);
            } catch (Throwable th) {
                th = th;
                com.apm.insight.b.a().a("NPTH_CATCH", new IOException("content :" + strC, th));
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            strC = null;
        }
    }

    protected void b(String str) {
        try {
            com.apm.insight.l.i.a(this.f3068c, str, false);
        } catch (Throwable unused) {
        }
    }

    public void c(String str) {
        try {
            com.apm.insight.l.i.a(this.d, str, false);
        } catch (Throwable unused) {
        }
    }
}
