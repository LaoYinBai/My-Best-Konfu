package c.a.a.a.a;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.JProtect;
import org.json.JSONObject;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ai implements af {
    private static int j = 2000;
    private static String k = b.h;
    private static String l = b.f2075a;
    private static String m = b.i;

    /* renamed from: a, reason: collision with root package name */
    public String f2057a;

    /* renamed from: b, reason: collision with root package name */
    public String f2058b;

    /* renamed from: c, reason: collision with root package name */
    public String f2059c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i = "";

    /* JADX WARN: Removed duplicated region for block: B:31:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0088 A[Catch: all -> 0x00a9, TryCatch #0 {all -> 0x00a9, blocks: (B:2:0x0000, B:4:0x000a, B:6:0x0012, B:8:0x0032, B:10:0x003e, B:32:0x0084, B:34:0x0088, B:35:0x008d, B:36:0x0092, B:37:0x0097, B:19:0x005b, B:22:0x0065, B:25:0x006f, B:28:0x0079, B:38:0x009b, B:39:0x009e), top: B:44:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008d A[Catch: all -> 0x00a9, TryCatch #0 {all -> 0x00a9, blocks: (B:2:0x0000, B:4:0x000a, B:6:0x0012, B:8:0x0032, B:10:0x003e, B:32:0x0084, B:34:0x0088, B:35:0x008d, B:36:0x0092, B:37:0x0097, B:19:0x005b, B:22:0x0065, B:25:0x006f, B:28:0x0079, B:38:0x009b, B:39:0x009e), top: B:44:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0092 A[Catch: all -> 0x00a9, TryCatch #0 {all -> 0x00a9, blocks: (B:2:0x0000, B:4:0x000a, B:6:0x0012, B:8:0x0032, B:10:0x003e, B:32:0x0084, B:34:0x0088, B:35:0x008d, B:36:0x0092, B:37:0x0097, B:19:0x005b, B:22:0x0065, B:25:0x006f, B:28:0x0079, B:38:0x009b, B:39:0x009e), top: B:44:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0097 A[Catch: all -> 0x00a9, TryCatch #0 {all -> 0x00a9, blocks: (B:2:0x0000, B:4:0x000a, B:6:0x0012, B:8:0x0032, B:10:0x003e, B:32:0x0084, B:34:0x0088, B:35:0x008d, B:36:0x0092, B:37:0x0097, B:19:0x005b, B:22:0x0065, B:25:0x006f, B:28:0x0079, B:38:0x009b, B:39:0x009e), top: B:44:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected static c.a.a.a.a.ai a(android.content.Intent r11) {
        /*
            java.lang.String r11 = r11.getDataString()     // Catch: java.lang.Throwable -> La9
            boolean r0 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> La9
            if (r0 != 0) goto La9
            java.lang.String r0 = c.a.a.a.a.ai.m     // Catch: java.lang.Throwable -> La9
            boolean r0 = r11.startsWith(r0)     // Catch: java.lang.Throwable -> La9
            if (r0 == 0) goto La9
            c.a.a.a.a.ai r0 = new c.a.a.a.a.ai     // Catch: java.lang.Throwable -> La9
            r0.<init>()     // Catch: java.lang.Throwable -> La9
            r0.h = r11     // Catch: java.lang.Throwable -> La9
            java.lang.String r1 = c.a.a.a.a.ai.m     // Catch: java.lang.Throwable -> La9
            int r1 = r1.length()     // Catch: java.lang.Throwable -> La9
            java.lang.String r11 = r11.substring(r1)     // Catch: java.lang.Throwable -> La9
            java.lang.String r11 = java.net.URLDecoder.decode(r11)     // Catch: java.lang.Throwable -> La9
            java.lang.String r1 = "[&]"
            java.lang.String[] r11 = r11.split(r1)     // Catch: java.lang.Throwable -> La9
            int r1 = r11.length     // Catch: java.lang.Throwable -> La9
            r2 = 0
            r3 = 0
        L30:
            if (r3 >= r1) goto L9e
            r4 = r11[r3]     // Catch: java.lang.Throwable -> La9
            java.lang.String r5 = "[=]"
            java.lang.String[] r4 = r4.split(r5)     // Catch: java.lang.Throwable -> La9
            int r5 = r4.length     // Catch: java.lang.Throwable -> La9
            r6 = 2
            if (r5 != r6) goto L9b
            r5 = r4[r2]     // Catch: java.lang.Throwable -> La9
            r7 = -1
            int r8 = r5.hashCode()     // Catch: java.lang.Throwable -> La9
            r9 = -1279545600(0xffffffffb3bbaf00, float:-8.7396984E-8)
            r10 = 1
            if (r8 == r9) goto L79
            r9 = -807062458(0xffffffffcfe53446, float:-7.6908165E9)
            if (r8 == r9) goto L6f
            r9 = 3530173(0x35ddbd, float:4.946826E-39)
            if (r8 == r9) goto L65
            r6 = 1408027618(0x53eccbe2, float:2.0340657E12)
            if (r8 == r6) goto L5b
            goto L83
        L5b:
            java.lang.String r6 = "noncestr"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> La9
            if (r5 == 0) goto L83
            r5 = 1
            goto L84
        L65:
            java.lang.String r8 = "sign"
            boolean r5 = r5.equals(r8)     // Catch: java.lang.Throwable -> La9
            if (r5 == 0) goto L83
            r5 = 2
            goto L84
        L6f:
            java.lang.String r6 = "package"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> La9
            if (r5 == 0) goto L83
            r5 = 3
            goto L84
        L79:
            java.lang.String r6 = "prepayid"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> La9
            if (r5 == 0) goto L83
            r5 = 0
            goto L84
        L83:
            r5 = -1
        L84:
            switch(r5) {
                case 0: goto L97;
                case 1: goto L92;
                case 2: goto L8d;
                case 3: goto L88;
                default: goto L87;
            }     // Catch: java.lang.Throwable -> La9
        L87:
            goto L9b
        L88:
            r4 = r4[r10]     // Catch: java.lang.Throwable -> La9
            r0.g = r4     // Catch: java.lang.Throwable -> La9
            goto L9b
        L8d:
            r4 = r4[r10]     // Catch: java.lang.Throwable -> La9
            r0.f = r4     // Catch: java.lang.Throwable -> La9
            goto L9b
        L92:
            r4 = r4[r10]     // Catch: java.lang.Throwable -> La9
            r0.d = r4     // Catch: java.lang.Throwable -> La9
            goto L9b
        L97:
            r4 = r4[r10]     // Catch: java.lang.Throwable -> La9
            r0.f2059c = r4     // Catch: java.lang.Throwable -> La9
        L9b:
            int r3 = r3 + 1
            goto L30
        L9e:
            java.lang.String r11 = c.a.a.a.a.ax.a()     // Catch: java.lang.Throwable -> La9
            r0.e = r11     // Catch: java.lang.Throwable -> La9
            c.a.a.a.a.ai r11 = r0.c()     // Catch: java.lang.Throwable -> La9
            return r11
        La9:
            r11 = 0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a.a.ai.a(android.content.Intent):c.a.a.a.a.ai");
    }

    public static ai a(Intent intent, String str) {
        ai aiVarB = b(intent);
        if (aiVarB == null) {
            aiVarB = a(intent);
        }
        if (aiVarB != null) {
            aiVarB.a(str);
        }
        return aiVarB;
    }

    protected static ai b(Intent intent) {
        Bundle extras = intent.getExtras();
        ComponentName component = intent.getComponent();
        if (component == null) {
            return null;
        }
        try {
            if (!component.getPackageName().equalsIgnoreCase(k) || !component.getClassName().contains(l) || extras == null) {
                return null;
            }
            ai aiVar = new ai();
            aiVar.f2057a = extras.getString(b.f2076b);
            aiVar.f2058b = extras.getString(b.f2077c);
            aiVar.d = extras.getString(b.d);
            aiVar.e = extras.getString(b.e);
            aiVar.f = extras.getString(b.r);
            aiVar.f2059c = extras.getString(b.f);
            aiVar.g = extras.getString(b.g);
            aiVar.h = extras.toString();
            return aiVar.c();
        } catch (Throwable unused) {
            return null;
        }
    }

    private ai c() {
        if (TextUtils.isEmpty(this.f2059c)) {
            this.f2059c = "null";
            return null;
        }
        String str = this.f2057a;
        if (str == null) {
            str = "";
        }
        this.f2057a = str;
        String str2 = this.f2058b;
        if (str2 == null) {
            str2 = "";
        }
        this.f2058b = str2;
        String str3 = this.d;
        if (str3 == null) {
            str3 = "";
        }
        this.d = str3;
        String str4 = this.e;
        if (str4 == null) {
            str4 = "";
        }
        this.e = str4;
        String str5 = this.f;
        if (str5 == null) {
            str5 = "";
        }
        this.f = str5;
        String str6 = this.g;
        if (str6 == null) {
            str6 = "";
        }
        this.g = str6;
        String str7 = this.h;
        if (str7 == null) {
            str7 = "";
        }
        this.h = str7;
        return this;
    }

    @Override // c.a.a.a.a.af
    public JSONObject a() {
        JSONObject jSONObjectA = a(true, true, true, true, true, true, true);
        try {
            if (jSONObjectA.toString().length() > j) {
                jSONObjectA = a(false, true, true, true, true, true, true);
            }
            if (jSONObjectA.toString().length() > j) {
                jSONObjectA = a(false, false, true, true, true, true, true);
            }
            if (jSONObjectA.toString().length() > j) {
                jSONObjectA = a(false, false, false, true, true, true, true);
            }
            if (jSONObjectA.toString().length() > j) {
                jSONObjectA = a(false, false, false, false, true, true, true);
            }
            if (jSONObjectA.toString().length() > j) {
                jSONObjectA = a(false, false, false, false, false, true, true);
            }
            if (jSONObjectA.toString().length() > j) {
                jSONObjectA = a(false, false, false, false, false, false, true);
            }
            int length = jSONObjectA.toString().length();
            if (length > j) {
                if (length - j > this.i.length()) {
                    return a(false, false, false, false, false, false, false);
                }
                this.i = this.i.substring(0, this.i.length() - (length - j));
                return a(false, false, false, false, false, false, true);
            }
        } catch (Throwable unused) {
        }
        return jSONObjectA;
    }

    protected JSONObject a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        JSONObject jSONObject = new JSONObject();
        if (z2) {
            try {
                jSONObject.put(b.j, this.f2057a);
            } catch (Throwable unused) {
            }
        }
        if (z3) {
            jSONObject.put(b.k, this.f2058b);
        }
        jSONObject.put(b.l, this.f2059c);
        if (z4) {
            jSONObject.put(b.m, this.d);
        }
        jSONObject.put(b.n, this.e);
        if (z5) {
            jSONObject.put(b.o, this.f);
        }
        if (z6) {
            jSONObject.put(b.p, this.g);
        }
        if (z) {
            jSONObject.put(b.q, this.h);
        }
        if (z7) {
            jSONObject.put("st", this.i);
        }
        return jSONObject;
    }

    public void a(String str) {
        this.i = str;
    }

    @Override // c.a.a.a.a.af
    public int b() {
        return 1;
    }
}
