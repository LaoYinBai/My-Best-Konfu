package com.apm.insight.nativecrash;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.apm.insight.l.i;
import com.apm.insight.l.k;
import com.apm.insight.l.o;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    public static class a extends C0087c {
        a(File file) {
            super(file);
            this.f2992b = "Total FD Count:";
            this.f2993c = ":";
            this.d = -2;
        }
    }

    public static class b extends C0087c {
        b(File file) {
            super(file);
            this.f2992b = "VmSize:";
            this.f2993c = "\\s+";
            this.d = -1;
        }
    }

    /* renamed from: com.apm.insight.nativecrash.c$c, reason: collision with other inner class name */
    public static class C0087c {

        /* renamed from: a, reason: collision with root package name */
        protected File f2991a;

        /* renamed from: b, reason: collision with root package name */
        protected String f2992b;

        /* renamed from: c, reason: collision with root package name */
        protected String f2993c;
        protected int d;

        public C0087c(File file) {
            this.f2991a = file;
        }

        public int a() throws Throwable {
            int i = -1;
            if (!this.f2991a.exists() || !this.f2991a.isFile()) {
                return -1;
            }
            BufferedReader bufferedReader = null;
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(this.f2991a));
                    int i2 = -1;
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line != null) {
                                int iA = a(line);
                                if (iA != -1) {
                                    i2 = iA;
                                    break;
                                }
                                i2 = iA;
                            } else {
                                break;
                            }
                        } catch (Throwable th) {
                            th = th;
                            i = i2;
                            bufferedReader = bufferedReader2;
                            com.apm.insight.b.a().a("NPTH_CATCH", th);
                            if (bufferedReader != null) {
                                k.a(bufferedReader);
                            }
                            return i;
                        }
                    }
                    k.a(bufferedReader2);
                    return i2;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }

        public int a(String str) throws NumberFormatException {
            int i = this.d;
            if (!str.startsWith(this.f2992b)) {
                return i;
            }
            try {
                i = Integer.parseInt(str.split(this.f2993c)[1].trim());
            } catch (NumberFormatException e) {
                com.apm.insight.b.a().a("NPTH_CATCH", e);
            }
            if (i < 0) {
                return -2;
            }
            return i;
        }
    }

    public static class d extends C0087c {
        d(File file) {
            super(file);
        }

        @NonNull
        public HashMap<String, List<String>> b() {
            JSONArray jSONArrayB;
            HashMap<String, List<String>> map = new HashMap<>();
            try {
                jSONArrayB = i.b(this.f2991a.getAbsolutePath());
            } catch (IOException unused) {
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
            if (jSONArrayB == null) {
                return map;
            }
            for (int i = 0; i < jSONArrayB.length(); i++) {
                String strOptString = jSONArrayB.optString(i);
                if (!TextUtils.isEmpty(strOptString) && strOptString.startsWith("[tid:0") && strOptString.endsWith("sigstack:0x0]")) {
                    int iIndexOf = strOptString.indexOf("[routine:0x");
                    String strSubstring = iIndexOf > 0 ? strOptString.substring(iIndexOf + "[routine:0x".length(), strOptString.indexOf(93, "[routine:0x".length() + iIndexOf)) : "unknown addr";
                    List<String> arrayList = map.get(strSubstring);
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                        map.put(strSubstring, arrayList);
                    }
                    arrayList.add(strOptString);
                }
            }
            return map;
        }
    }

    public static class e extends C0087c {
        e(File file) {
            super(file);
        }

        @NonNull
        public JSONArray a(HashMap<String, List<String>> map) {
            JSONArray jSONArrayB;
            List<String> list;
            JSONArray jSONArray = new JSONArray();
            if (map.isEmpty()) {
                return jSONArray;
            }
            try {
                jSONArrayB = i.b(this.f2991a.getAbsolutePath());
            } catch (IOException unused) {
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
            if (jSONArrayB == null) {
                return jSONArray;
            }
            for (int i = 0; i < jSONArrayB.length(); i++) {
                String strOptString = jSONArrayB.optString(i);
                if (!TextUtils.isEmpty(strOptString)) {
                    String strSubstring = strOptString.substring(2, strOptString.indexOf(":"));
                    if (map.containsKey(strSubstring) && (list = map.get(strSubstring)) != null) {
                        Iterator<String> it = list.iterator();
                        while (it.hasNext()) {
                            jSONArray.put(it.next() + " " + strOptString);
                        }
                        map.remove(strSubstring);
                    }
                }
            }
            Iterator<List<String>> it2 = map.values().iterator();
            while (it2.hasNext()) {
                Iterator<String> it3 = it2.next().iterator();
                while (it3.hasNext()) {
                    jSONArray.put(it3.next() + "  0x000000:unknown");
                }
            }
            return jSONArray;
        }
    }

    public static class f extends C0087c {
        f(File file) {
            super(file);
            this.f2992b = "Total Threads Count:";
            this.f2993c = ":";
            this.d = -2;
        }
    }

    public static int a(String str) {
        return new a(o.b(str)).a();
    }

    @NonNull
    public static JSONArray a(File file, File file2) {
        return new e(file2).a(new d(file).b());
    }

    public static int b(String str) {
        return new f(o.c(str)).a();
    }

    public static int c(String str) {
        return new b(o.d(str)).a();
    }
}
