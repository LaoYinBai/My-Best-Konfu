package com.sigmob.sdk.common.a;

import android.text.TextUtils;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.umeng.message.proguard.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static Map<String, String> a;

    public static class a {
        String a;
        String b;

        /* renamed from: com.sigmob.sdk.common.a.b$a$a, reason: collision with other inner class name */
        public static class C0017a {
            private String a = null;
            private Map<String, String> b = null;
            private Map<String, String> c = null;
            private boolean d;

            public C0017a a(String str) {
                this.a = str;
                return this;
            }

            public C0017a a(String str, String str2) {
                if (this.b == null) {
                    this.b = new HashMap();
                }
                this.b.put(str, str2);
                return this;
            }

            public C0017a a(Map<String, String> map) {
                this.c = map;
                return this;
            }

            public a a() {
                StringBuilder sb = new StringBuilder("create table if not exists ");
                sb.append(this.a);
                sb.append(" ( ");
                List listB = b.b(this.b);
                if (listB.size() > 1) {
                    Iterator it = b.b(this.c).iterator();
                    while (it.hasNext()) {
                        sb.append((String) it.next());
                        sb.append(" ,");
                    }
                    sb.append(" primary key ( ");
                    Iterator<String> it2 = this.b.keySet().iterator();
                    while (it2.hasNext()) {
                        sb.append(it2.next());
                        sb.append(it2.hasNext() ? " ," : " )");
                    }
                } else {
                    sb.append(this.d ? String.format("id integer primary key AUTOINCREMENT", new Object[0]) : String.format("%s primary key ", listB.get(0)));
                    this.c.remove(this.b.keySet().iterator().next());
                    for (String str : b.b(this.c)) {
                        sb.append(" ,");
                        sb.append(str);
                        sb.append(" ");
                    }
                }
                sb.append(" ); ");
                a aVar = new a();
                aVar.b = sb.toString();
                aVar.a = this.a;
                return aVar;
            }
        }
    }

    /* renamed from: com.sigmob.sdk.common.a.b$b, reason: collision with other inner class name */
    public static class C0018b {
        String a;
        String b;
        Map<String, Object> c;
        List<Object> d;

        /* renamed from: com.sigmob.sdk.common.a.b$b$a */
        public static class a {
            private String a = null;
            private Map<String, Object> b = null;

            public C0018b a() {
                List<Object> listAsList = Arrays.asList(this.b.keySet().toArray());
                StringBuilder sb = new StringBuilder("insert or replace into ");
                sb.append(this.a);
                StringBuilder sb2 = new StringBuilder(l.s);
                StringBuilder sb3 = new StringBuilder(l.s);
                int i = 0;
                while (i < listAsList.size()) {
                    sb2.append(listAsList.get(i));
                    sb3.append("?");
                    i++;
                    if (i < listAsList.size()) {
                        sb2.append(", ");
                        sb3.append(", ");
                    }
                }
                sb2.append(l.t);
                sb3.append(l.t);
                sb.append((CharSequence) sb2);
                sb.append(" values ");
                sb.append((CharSequence) sb3);
                C0018b c0018b = new C0018b();
                c0018b.a = this.a;
                c0018b.d = listAsList;
                c0018b.c = this.b;
                c0018b.b = sb.toString();
                return c0018b;
            }

            public void a(String str) {
                this.a = str;
            }

            public void a(Map<String, Object> map) {
                this.b = map;
            }
        }
    }

    public static class c {
        String a;
        String b;
        Map<String, Object> c;
        String d;

        public static class a {
            private String a = null;
            private Map<String, Object> b = null;
            private String c = null;

            public c a() {
                StringBuilder sb = new StringBuilder("update ");
                sb.append(this.a);
                sb.append(" set ");
                Iterator<String> it = this.b.keySet().iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    sb.append(next + " = " + this.b.get(next));
                    if (it.hasNext()) {
                        sb.append(", ");
                    }
                }
                if (!TextUtils.isEmpty(this.c)) {
                    sb.append(" " + this.c);
                }
                c cVar = new c();
                cVar.a = this.a;
                cVar.c = this.b;
                cVar.d = this.c;
                cVar.b = sb.toString();
                return cVar;
            }

            public void a(String str) {
                this.a = str;
            }

            public void a(Map<String, Object> map) {
                this.b = map;
            }

            public void b(String str) {
                this.c = str;
            }
        }

        public String a() {
            return this.b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> b(Map<String, String> map) {
        String str;
        ArrayList arrayList = new ArrayList(map.size());
        for (String str2 : map.keySet()) {
            String lowerCase = map.get(str2).toLowerCase();
            char c2 = 65535;
            int iHashCode = lowerCase.hashCode();
            if (iHashCode != 104431) {
                if (iHashCode != 3327612) {
                    if (iHashCode != 3556653) {
                        if (iHashCode == 2111392325 && lowerCase.equals("java.lang.string")) {
                            c2 = 2;
                        }
                    } else if (lowerCase.equals("text")) {
                        c2 = 3;
                    }
                } else if (lowerCase.equals(PushMultiProcessSharedProvider.LONG_TYPE)) {
                    c2 = 1;
                }
            } else if (lowerCase.equals("int")) {
                c2 = 0;
            }
            switch (c2) {
                case 0:
                case 1:
                    str = PushMultiProcessSharedProvider.INT_TYPE;
                    break;
                case 2:
                case 3:
                    str = "text";
                    break;
                default:
                    str = "blob";
                    break;
            }
            arrayList.add(String.format("%s %s ", str2, str));
        }
        return arrayList;
    }
}
