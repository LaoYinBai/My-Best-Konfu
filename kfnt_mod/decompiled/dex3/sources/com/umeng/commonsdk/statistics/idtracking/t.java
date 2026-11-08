package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class t extends a {
    private static final String a = "uuid";
    private static final String e = "yosuid";
    private static final String f = "23346339";
    private Context b;
    private String c;
    private String d;

    public t(Context context) {
        super(a);
        this.b = null;
        this.c = null;
        this.d = null;
        this.b = context;
        this.c = null;
        this.d = null;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editorEdit;
        try {
            if (!FieldManager.allow(com.umeng.commonsdk.utils.b.z) || TextUtils.isEmpty(a("ro.yunos.version", "")) || this.b == null || (sharedPreferences = PreferenceWrapper.getDefault(this.b)) == null) {
                return null;
            }
            String string = sharedPreferences.getString(e, "");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            this.d = b(f);
            if (!TextUtils.isEmpty(this.d) && this.b != null && sharedPreferences != null && (editorEdit = sharedPreferences.edit()) != null) {
                editorEdit.putString(e, this.d).commit();
            }
            return this.d;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0121 A[PHI: r0
  0x0121: PHI (r0v7 javax.net.ssl.HttpsURLConnection) = (r0v4 javax.net.ssl.HttpsURLConnection), (r0v10 javax.net.ssl.HttpsURLConnection) binds: [B:61:0x011f, B:38:0x00f2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0106 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0110 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String b(java.lang.String r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.t.b(java.lang.String):java.lang.String");
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }
}
