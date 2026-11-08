package com.apm.insight.nativecrash;

import com.apm.insight.l.o;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {
    private static final Pattern i = Pattern.compile("^pid:\\s(.*),\\stid:\\s(.*),\\sname:\\s(.*)\\s+>>>\\s(.*)\\s<<<$");
    private static final Pattern j = Pattern.compile("^signal\\s(.*),\\scode\\s(.*),\\sfault\\saddr\\s(.*)$");
    private static final Pattern k = Pattern.compile("^Abort message: (.*)$");
    private static final Pattern l = Pattern.compile("^Crash message: (.*)$");
    private static final Pattern m = Pattern.compile("^    \\/(\\w*)\\/.*\\/(.*\\.so)\\s\\(BuildId: ([a-f0-9]*)\\)$");

    /* renamed from: a, reason: collision with root package name */
    private String f2994a;

    /* renamed from: b, reason: collision with root package name */
    private String f2995b;

    /* renamed from: c, reason: collision with root package name */
    private String f2996c;
    private String d;
    private String e;
    private String f;
    private String g;
    private Map<String, String> h = new HashMap();

    public d(File file) throws Throwable {
        b(o.b(file));
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x0194, code lost:
    
        r9 = r1.readLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0198, code lost:
    
        if (r9 == null) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x019a, code lost:
    
        r0 = r9.contains("BuildId:");
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01a0, code lost:
    
        if (r0 != 0) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01a3, code lost:
    
        r9 = com.apm.insight.nativecrash.d.m.matcher(r9);
        r0 = r9.find();
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ad, code lost:
    
        if (r0 != 0) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01b0, code lost:
    
        r0 = r9.group(1);
        r2 = r9.group(2);
        r9 = r9.group(3);
        r0 = r0.equals("data");
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01c2, code lost:
    
        if (r0 == 0) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01c4, code lost:
    
        r0 = r8.h;
        r0.put(r2, r9);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.util.Map, java.util.Map<java.lang.String, java.lang.String>] */
    /* JADX WARN: Type inference failed for: r0v20, types: [int] */
    /* JADX WARN: Type inference failed for: r0v21, types: [int] */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.io.File r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 492
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.nativecrash.d.b(java.io.File):void");
    }

    public String a() {
        return this.g;
    }

    public void a(File file) throws Throwable {
        File fileB = o.b(file);
        if (fileB.exists()) {
            fileB.renameTo(new File(fileB.getAbsoluteFile() + ".old"));
        }
        NativeImpl.a(file);
        b(o.b(file));
    }

    public Map<String, String> b() {
        return this.h;
    }

    public String c() {
        StringBuilder sb = new StringBuilder();
        if (this.e != null) {
            sb.append(this.e);
        }
        if (this.f != null) {
            sb.append(this.f);
        }
        if (this.g != null) {
            sb.append(this.g);
        }
        return sb.toString();
    }
}
