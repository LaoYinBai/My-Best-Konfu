package b.b.a.k;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g implements n {

    /* renamed from: a, reason: collision with root package name */
    public static String f1129a;

    /* renamed from: b, reason: collision with root package name */
    public static String f1130b;

    /* renamed from: c, reason: collision with root package name */
    public static volatile String f1131c;
    public static String[] d;
    public final Context e;
    public b.b.a.j.c f;
    public final b.b.a.j.a g;
    public final String h;
    public final b.b.a.f.g i;

    public g(Context context, b.b.a.f.g gVar, b.b.a.j.a aVar) {
        this.i = gVar;
        this.h = gVar.f1070b.t() ? "_local" : "";
        this.e = context.getApplicationContext();
        f fVar = new f();
        this.g = aVar;
        this.f = new b.b.a.j.l(this.e, gVar.f1070b.P());
        this.f.d = this.g;
        if (!gVar.f1070b.s()) {
            new Thread(new e(fVar)).start();
        }
        a(gVar.f1070b.u());
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(boolean r8) throws java.lang.Throwable {
        /*
            r7 = this;
            java.lang.String r0 = b.b.a.k.g.f1129a
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto Lb
            java.lang.String r8 = b.b.a.k.g.f1129a
            return r8
        Lb:
            android.content.Context r0 = r7.e
            r1 = 0
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Exception -> L19
            java.lang.String r2 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r2)     // Catch: java.lang.Exception -> L19
            goto L1e
        L19:
            r0 = move-exception
            r0.printStackTrace()
            r0 = r1
        L1e:
            boolean r2 = b.b.a.k.x.a(r0)     // Catch: java.lang.Exception -> Lb1
            if (r2 == 0) goto L35
            java.lang.String r2 = "9774d56d682e549c"
            boolean r2 = r2.equals(r0)     // Catch: java.lang.Exception -> Lb1
            if (r2 == 0) goto L2d
            goto L35
        L2d:
            b.b.a.j.c r8 = r7.f     // Catch: java.lang.Exception -> Lb1
            java.lang.String r8 = r8.b(r1, r0)     // Catch: java.lang.Exception -> Lb1
            goto Lb8
        L35:
            android.content.Context r2 = r7.e     // Catch: java.lang.Exception -> Lb1
            java.lang.String r3 = "snssdk_openudid"
            r4 = 0
            android.content.SharedPreferences r2 = r2.getSharedPreferences(r3, r4)     // Catch: java.lang.Exception -> Lb1
            java.lang.String r3 = "openudid"
            java.lang.String r3 = r2.getString(r3, r1)     // Catch: java.lang.Exception -> Lb1
            boolean r5 = b.b.a.k.x.a(r3)     // Catch: java.lang.Exception -> Lb1
            if (r5 != 0) goto Laa
            java.security.SecureRandom r3 = new java.security.SecureRandom     // Catch: java.lang.Exception -> Lb1
            r3.<init>()     // Catch: java.lang.Exception -> Lb1
            java.math.BigInteger r5 = new java.math.BigInteger     // Catch: java.lang.Exception -> Lb1
            r6 = 80
            r5.<init>(r6, r3)     // Catch: java.lang.Exception -> Lb1
            r3 = 16
            java.lang.String r3 = r5.toString(r3)     // Catch: java.lang.Exception -> Lb1
            char r4 = r3.charAt(r4)     // Catch: java.lang.Exception -> Lb1
            r5 = 45
            if (r4 != r5) goto L69
            r4 = 1
            java.lang.String r3 = r3.substring(r4)     // Catch: java.lang.Exception -> Lb1
        L69:
            int r4 = r3.length()     // Catch: java.lang.Exception -> Lb1
            int r4 = 13 - r4
            if (r4 <= 0) goto L87
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb1
            r5.<init>()     // Catch: java.lang.Exception -> Lb1
        L76:
            if (r4 <= 0) goto L80
            r6 = 70
            r5.append(r6)     // Catch: java.lang.Exception -> Lb1
            int r4 = r4 + (-1)
            goto L76
        L80:
            r5.append(r3)     // Catch: java.lang.Exception -> Lb1
            java.lang.String r3 = r5.toString()     // Catch: java.lang.Exception -> Lb1
        L87:
            if (r8 == 0) goto L9c
            java.lang.String r8 = "openudid.dat"
            java.lang.String r8 = a(r8, r1)     // Catch: java.lang.Exception -> Lb1
            b.b.a.j.a r1 = r7.g     // Catch: java.lang.Exception -> Lb1
            java.lang.String r8 = r1.b(r8, r3)     // Catch: java.lang.Exception -> Lb1
            boolean r1 = b.b.a.k.x.a(r8)     // Catch: java.lang.Exception -> Lb1
            if (r1 == 0) goto L9c
            goto L9d
        L9c:
            r8 = r3
        L9d:
            android.content.SharedPreferences$Editor r1 = r2.edit()     // Catch: java.lang.Exception -> Lb1
            java.lang.String r2 = "openudid"
            r1.putString(r2, r8)     // Catch: java.lang.Exception -> Lb1
            r1.commit()     // Catch: java.lang.Exception -> Lb1
            goto Lb8
        Laa:
            b.b.a.j.a r8 = r7.g     // Catch: java.lang.Exception -> Lb1
            r8.b(r3, r1)     // Catch: java.lang.Exception -> Lb1
            r8 = r3
            goto Lb8
        Lb1:
            r8 = move-exception
            java.lang.String r1 = ""
            b.b.a.k.v.a(r1, r8)
            r8 = r0
        Lb8:
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto Lcb
            java.lang.StringBuilder r8 = b.a.a.a.a.a(r8)
            java.lang.String r0 = r7.h
            r8.append(r0)
            java.lang.String r8 = r8.toString()
        Lcb:
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto Ld3
            b.b.a.k.g.f1129a = r8
        Ld3:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.k.g.a(boolean):java.lang.String");
    }

    public String[] b() {
        String[] strArr = d;
        if (strArr != null && strArr.length > 0) {
            return strArr;
        }
        try {
            String[] strArrA = this.f.a((String[]) null, b.b.a.a.b.c(this.e));
            if (strArrA == null) {
                strArrA = new String[0];
            }
            for (int i = 0; i < strArrA.length; i++) {
                strArrA[i] = strArrA[i] + this.h;
            }
            d = strArrA;
            return strArrA;
        } catch (Exception e) {
            v.a("", e);
            return null;
        }
    }

    public String c() {
        if (!TextUtils.isEmpty(f1131c)) {
            return f1131c;
        }
        f1131c = this.f.d("", "");
        return f1131c;
    }

    public void a(String str) {
        if (!x.b(str) || x.a(str, f1131c)) {
            return;
        }
        f1131c = this.f.d(str, f1131c);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.lang.String r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.k.g.a(java.lang.String, java.lang.String):java.lang.String");
    }

    public String a() throws Throwable {
        if (!TextUtils.isEmpty(f1130b)) {
            return f1130b;
        }
        try {
            SharedPreferences sharedPreferences = this.e.getSharedPreferences("snssdk_openudid", 0);
            String string = sharedPreferences.getString("clientudid", null);
            if (!x.a(string)) {
                string = UUID.randomUUID().toString();
                String strC = this.g.c(a("clientudid.dat", (String) null), string);
                if (x.a(strC)) {
                    string = strC;
                }
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString("clientudid", string);
                editorEdit.commit();
            } else {
                this.g.c(string, null);
            }
            if (!TextUtils.isEmpty(string)) {
                string = string + this.h;
            }
            f1130b = string;
            return string;
        } catch (Exception e) {
            v.a("", e);
            return "";
        }
    }

    public void a(Account account) {
        b.b.a.j.a aVar = this.g;
        if (aVar != null) {
            aVar.a(account);
        }
    }

    public void b(String str) {
        this.f.c(str);
        v.a("DeviceParamsProvider#clear clearKey=" + str + " sDeviceId=" + f1131c + " mCacheHandler.loadDeviceId()=" + this.f.d("", ""));
    }

    public void a(Context context, String str) {
        v.a("DeviceParamsProvider#clearDidAndIid clearKey=" + str + " sDeviceId=" + f1131c + " mCacheHandler.loadDeviceId()=" + this.f.d("", ""));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f1131c = null;
        String str2 = "clear_key_prefix" + str;
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.i.f1070b.P(), 0);
        if (!sharedPreferences.getBoolean(str2, false)) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putBoolean(str2, true);
            if (sharedPreferences.contains(com.bytedance.b.a.c.h.x)) {
                editorEdit.remove(com.bytedance.b.a.c.h.x);
            }
            if (sharedPreferences.contains("install_id")) {
                editorEdit.remove("install_id");
            }
            editorEdit.apply();
            this.f.c(com.bytedance.b.a.c.h.x);
            if (v.f1150b) {
                v.a("clearKey : " + str + " :clear installId and deviceId finish");
                return;
            }
            return;
        }
        if (v.f1150b) {
            v.a("clearKey : " + str + " : is already cleared");
        }
    }
}
