package com.ta.utdid2.b.a;

import com.ta.utdid2.b.a.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    private static final Object b = new Object();
    private File a;

    /* renamed from: a, reason: collision with other field name */
    private final Object f15a = new Object();

    /* renamed from: a, reason: collision with other field name */
    private HashMap<File, a> f16a = new HashMap<>();

    public d(String str) {
        if (str != null && str.length() > 0) {
            this.a = new File(str);
            return;
        }
        throw new RuntimeException("Directory can not be empty");
    }

    private File a(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException("File " + str + " contains a path separator");
    }

    private File a() {
        File file;
        synchronized (this.f15a) {
            file = this.a;
        }
        return file;
    }

    private File b(String str) {
        return a(a(), str + ".xml");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0084 A[Catch: Throwable -> 0x0087, TRY_ENTER, TRY_LEAVE, TryCatch #7 {Throwable -> 0x0087, blocks: (B:31:0x0054, B:54:0x0084), top: B:83:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x008a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.ta.utdid2.b.a.b a(java.lang.String r5, int r6) throws java.lang.Throwable {
        /*
            r4 = this;
            java.io.File r5 = r4.b(r5)
            java.lang.Object r0 = com.ta.utdid2.b.a.d.b
            monitor-enter(r0)
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r1 = r4.f16a     // Catch: java.lang.Throwable -> Lab
            java.lang.Object r1 = r1.get(r5)     // Catch: java.lang.Throwable -> Lab
            com.ta.utdid2.b.a.d$a r1 = (com.ta.utdid2.b.a.d.a) r1     // Catch: java.lang.Throwable -> Lab
            if (r1 == 0) goto L19
            boolean r2 = r1.d()     // Catch: java.lang.Throwable -> Lab
            if (r2 != 0) goto L19
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lab
            return r1
        L19:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lab
            java.io.File r0 = a(r5)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L2a
            r5.delete()
            r0.renameTo(r5)
        L2a:
            boolean r0 = r5.exists()
            r2 = 0
            if (r0 == 0) goto L87
            boolean r0 = r5.canRead()
            if (r0 == 0) goto L87
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51 org.xmlpull.v1.XmlPullParserException -> L58
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51 org.xmlpull.v1.XmlPullParserException -> L58
            java.util.HashMap r3 = com.ta.utdid2.b.a.e.a(r0)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L52 org.xmlpull.v1.XmlPullParserException -> L59
            r0.close()     // Catch: java.lang.Exception -> L48 org.xmlpull.v1.XmlPullParserException -> L4a java.lang.Throwable -> L4c
            r0.close()     // Catch: java.lang.Throwable -> L46
        L46:
            r2 = r3
            goto L87
        L48:
            r2 = r3
            goto L52
        L4a:
            r2 = r3
            goto L59
        L4c:
            r5 = move-exception
            r2 = r0
            goto L7c
        L4f:
            r5 = move-exception
            goto L7c
        L51:
            r0 = r2
        L52:
            if (r0 == 0) goto L87
            r0.close()     // Catch: java.lang.Throwable -> L87
            goto L87
        L58:
            r0 = r2
        L59:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L73
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L73
            int r0 = r3.available()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L74
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L74
            r3.read(r0)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L74
            goto L76
        L68:
            r5 = move-exception
            r2 = r3
            goto L6d
        L6b:
            r5 = move-exception
            r2 = r0
        L6d:
            if (r2 == 0) goto L72
            r2.close()     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L72
        L72:
            throw r5     // Catch: java.lang.Throwable -> L4f
        L73:
            r3 = r0
        L74:
            if (r3 == 0) goto L82
        L76:
            r3.close()     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L82
            goto L82
        L7a:
            r5 = move-exception
            r2 = r3
        L7c:
            if (r2 == 0) goto L81
            r2.close()     // Catch: java.lang.Throwable -> L81
        L81:
            throw r5
        L82:
            if (r3 == 0) goto L87
            r3.close()     // Catch: java.lang.Throwable -> L87
        L87:
            java.lang.Object r3 = com.ta.utdid2.b.a.d.b
            monitor-enter(r3)
            if (r1 == 0) goto L92
            r1.a(r2)     // Catch: java.lang.Throwable -> L90
            goto La7
        L90:
            r5 = move-exception
            goto La9
        L92:
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r0 = r4.f16a     // Catch: java.lang.Throwable -> L90
            java.lang.Object r0 = r0.get(r5)     // Catch: java.lang.Throwable -> L90
            r1 = r0
            com.ta.utdid2.b.a.d$a r1 = (com.ta.utdid2.b.a.d.a) r1     // Catch: java.lang.Throwable -> L90
            if (r1 != 0) goto La7
            com.ta.utdid2.b.a.d$a r1 = new com.ta.utdid2.b.a.d$a     // Catch: java.lang.Throwable -> L90
            r1.<init>(r5, r6, r2)     // Catch: java.lang.Throwable -> L90
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r6 = r4.f16a     // Catch: java.lang.Throwable -> L90
            r6.put(r5, r1)     // Catch: java.lang.Throwable -> L90
        La7:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L90
            return r1
        La9:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L90
            throw r5
        Lab:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lab
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.d.a(java.lang.String, int):com.ta.utdid2.b.a.b");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File a(File file) {
        return new File(file.getPath() + ".bak");
    }

    private static final class a implements b {
        private static final Object c = new Object();
        private Map a;

        /* renamed from: a, reason: collision with other field name */
        private WeakHashMap<b.InterfaceC0118b, Object> f17a;
        private final File b;

        /* renamed from: c, reason: collision with other field name */
        private final int f18c;

        /* renamed from: c, reason: collision with other field name */
        private final File f19c;
        private boolean j = false;

        a(File file, int i, Map map) {
            this.b = file;
            this.f19c = d.a(file);
            this.f18c = i;
            this.a = map == null ? new HashMap() : map;
            this.f17a = new WeakHashMap<>();
        }

        @Override // com.ta.utdid2.b.a.b
        public boolean b() {
            return this.b != null && new File(this.b.getAbsolutePath()).exists();
        }

        public void a(boolean z) {
            synchronized (this) {
                this.j = z;
            }
        }

        public boolean d() {
            boolean z;
            synchronized (this) {
                z = this.j;
            }
            return z;
        }

        public void a(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.a = map;
                }
            }
        }

        @Override // com.ta.utdid2.b.a.b
        public Map<String, ?> getAll() {
            HashMap map;
            synchronized (this) {
                map = new HashMap(this.a);
            }
            return map;
        }

        @Override // com.ta.utdid2.b.a.b
        public String getString(String str, String str2) {
            String str3;
            synchronized (this) {
                str3 = (String) this.a.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
            }
            return str3;
        }

        @Override // com.ta.utdid2.b.a.b
        public long getLong(String str, long j) {
            synchronized (this) {
                Long l = (Long) this.a.get(str);
                if (l != null) {
                    j = l.longValue();
                }
            }
            return j;
        }

        /* renamed from: com.ta.utdid2.b.a.d$a$a, reason: collision with other inner class name */
        public final class C0119a implements b.a {
            private final Map<String, Object> b = new HashMap();
            private boolean k = false;

            public C0119a() {
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str, String str2) {
                synchronized (this) {
                    this.b.put(str, str2);
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str, int i) {
                synchronized (this) {
                    this.b.put(str, Integer.valueOf(i));
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str, long j) {
                synchronized (this) {
                    this.b.put(str, Long.valueOf(j));
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str, float f) {
                synchronized (this) {
                    this.b.put(str, Float.valueOf(f));
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str, boolean z) {
                synchronized (this) {
                    this.b.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str) {
                synchronized (this) {
                    this.b.put(str, this);
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a b() {
                synchronized (this) {
                    this.k = true;
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public boolean commit() {
                boolean z;
                ArrayList arrayList;
                HashSet<b.InterfaceC0118b> hashSet;
                boolean zE;
                synchronized (d.b) {
                    z = a.this.f17a.size() > 0;
                    arrayList = null;
                    if (z) {
                        arrayList = new ArrayList();
                        hashSet = new HashSet(a.this.f17a.keySet());
                    } else {
                        hashSet = null;
                    }
                    synchronized (this) {
                        if (this.k) {
                            a.this.a.clear();
                            this.k = false;
                        }
                        for (Map.Entry<String, Object> entry : this.b.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            if (value == this) {
                                a.this.a.remove(key);
                            } else {
                                a.this.a.put(key, value);
                            }
                            if (z) {
                                arrayList.add(key);
                            }
                        }
                        this.b.clear();
                    }
                    zE = a.this.e();
                    if (zE) {
                        a.this.a(true);
                    }
                }
                if (z) {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        String str = (String) arrayList.get(size);
                        for (b.InterfaceC0118b interfaceC0118b : hashSet) {
                            if (interfaceC0118b != null) {
                                interfaceC0118b.a(a.this, str);
                            }
                        }
                    }
                }
                return zE;
            }
        }

        @Override // com.ta.utdid2.b.a.b
        public b.a a() {
            return new C0119a();
        }

        private FileOutputStream a(File file) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException unused) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    return new FileOutputStream(file);
                } catch (FileNotFoundException unused2) {
                    return null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean e() throws IOException {
            if (this.b.exists()) {
                if (!this.f19c.exists()) {
                    if (!this.b.renameTo(this.f19c)) {
                        return false;
                    }
                } else {
                    this.b.delete();
                }
            }
            try {
                FileOutputStream fileOutputStreamA = a(this.b);
                if (fileOutputStreamA == null) {
                    return false;
                }
                e.a(this.a, fileOutputStreamA);
                fileOutputStreamA.close();
                this.f19c.delete();
                return true;
            } catch (Exception unused) {
                if (this.b.exists()) {
                    this.b.delete();
                }
                return false;
            }
        }
    }
}
